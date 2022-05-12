package com.axmor.uitest.framework;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Files;
import java.util.List;

import org.asynchttpclient.Dsl;
import org.asynchttpclient.request.body.multipart.FilePart;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.axmor.uitest.framework.config.TestAppConfig;
import com.axmor.uitest.framework.config.TestEngineConfig;
import com.axmor.uitest.framework.internal.DriverContext;
import com.axmor.uitest.framework.internal.DriverFactory;
import com.axmor.uitest.framework.internal.SelenoidUrl;
import com.axmor.uitest.framework.internal.TestEnvironment;
import com.axmor.uitest.framework.internal.Wait;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import io.netty.handler.codec.http.HttpResponseStatus;

public final class Driver {
    public static final String VERSION = "1.0.0";

    private static DriverContext driverContext;
    private static Wait waiter;

    static {
        // Redirect java.util.logging to SLF4J
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    public static void initialize(TestInfo testInfo) {
        if (driverContext == null) {
            driverContext = DriverFactory.buildDriver(TestEnvironment.configuration(), testInfo);
            waiter = new Wait(driverContext.driver(), TestEnvironment.configuration().engine().waitSeconds());
            warmUp();
        }
    }

    public static Wait waiter() {
        ensureInitialized();
        return waiter;
    }

    private static void ensureInitialized() {
        if (driverContext == null) {
            throw new IllegalStateException("Необходим вызов Driver.initialize()");
        }
    }

    public static boolean isReady() {
        return driverContext != null;
    }

    public static WebDriver current() {
        ensureInitialized();
        return driverContext.driver();
    }

    public static TestEngineConfig engineConfig() {
        return TestEnvironment.configuration().engine();
    }

    public static TestAppConfig appConfig() {
        return TestEnvironment.configuration().app();
    }

    public static URL videoUrl() {
        if (driverContext.videoName() == null) {
            return null;
        }
        return SelenoidUrl.videoUrl(TestEnvironment.configuration().engine().remoteEngineUrl(), driverContext.videoName());
    }

    public static URL logUrl() {
        if (driverContext.logName() == null) {
            return null;
        }
        return SelenoidUrl.logUrl(TestEnvironment.configuration().engine().remoteEngineUrl(), driverContext.logName());
    }

    public static void deleteVideo() {
        deleteByUrl(videoUrl());
    }

    public static void deleteLog() {
        deleteByUrl(logUrl());
    }

    private static void deleteByUrl(URL url) {
        try {
            var request = HttpRequest
                    .newBuilder(url.toURI())
                    .DELETE()
                    .build();
            HttpClient.newHttpClient().send(request, BodyHandlers.discarding());
        } catch (Exception ex) {
            logger().warn("Не получилось удалить видео", ex);
        }
    }

    public static String takeScreenshot() {
        ensureInitialized();
        try {
            File screenshot = ((TakesScreenshot) current()).getScreenshotAs(OutputType.FILE);
            String imageHostingUrl = TestEnvironment.configuration().engine().imageHostingUrl();

            if (imageHostingUrl == null) {
                String ext = getFileExtension(screenshot.getName());
                File to = new File(driverContext.runName() + Strings.nullToEmpty(ext));
                Files.move(screenshot.toPath(), to.toPath());
                return to.getAbsolutePath();
            }

            URL url = new URL(imageHostingUrl);
            try (var httpClient = Dsl.asyncHttpClient()) {
                var request = httpClient
                        .preparePost(imageHostingUrl)
                        .addBodyPart(new FilePart("file", screenshot))
                        .build();
                var response = httpClient.executeRequest(request).get();
                if (response.getStatusCode() != HttpResponseStatus.OK.code()) {
                    logger().warn("Не получилось загрузить скриншот: {}: {}",
                            response.getStatusCode(), response.getStatusText());
                    return null;
                }
                String relativeUrl = new ObjectMapper()
                        .readTree(response.getResponseBody())
                        .get("filename")
                        .asText();
                return new URL(url, relativeUrl).toString();
            }
        } catch (Exception ex) {
            logger().warn("Не получилось сохранить скриншот", ex);
            return null;
        }
    }

    private static String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        return index < 0 ? null : fileName.substring(index);
    }

    public static void quit() {
        if (driverContext != null && driverContext.driver() != null) {
            warmUp();
            driverContext.driver().quit();
            driverContext = null;
            waiter = null;
        }
    }

    public static void goToPage(String relativeUrl) {
        ensureInitialized();
        var baseUrl = TestEnvironment.configuration().app().url();
        URL url;
        if (!Strings.isNullOrEmpty(baseUrl)) {
            if (!baseUrl.startsWith("http")) {
                baseUrl = "http://" + baseUrl;
            }
            try {
                url = new URL(new URL(baseUrl), relativeUrl);
            } catch (MalformedURLException ex) {
                throw new IllegalArgumentException(String.format(
                        "Не получается сконструировать адрес из '%s' и '%s'", baseUrl, relativeUrl), ex);
            }
        } else {
            if (!relativeUrl.startsWith("http")) {
                relativeUrl = "http://" + relativeUrl;
            }
            try {
                url = new URL(relativeUrl);
            } catch (MalformedURLException ex) {
                throw new IllegalArgumentException(String.format(
                        "Не получается сконструировать адрес из '%s'", relativeUrl), ex);
            }
        }
        current().navigate().to(url);
    }

    public static WebElement findElement(By by) {
        ensureInitialized();
        return waiter.untilElement(driver -> driver.findElement(by));
    }

    public static List<WebElement> findElements(By by, boolean waitAny) {
        ensureInitialized();
        if (waitAny) {
            waiter.until(driver -> !driver.findElements(by).isEmpty());
        }
        return current().findElements(by);
    }

    private static void warmUp() {
        if (driverContext.needWarmUp()) {
            double seconds = TestEnvironment.configuration().engine().warmUpSeconds();
            logger().info("Ожидание " + seconds + " секунд...");
            try {
                Thread.sleep((int) (seconds * 1000));
            } catch (InterruptedException ex) {
                throw new IllegalStateException("Неожиданный вызов Thread.interrupt()", ex);
            }
        }
    }

    public static Logger logger() {
        return LoggerFactory.getLogger("uitest");
    }
}
