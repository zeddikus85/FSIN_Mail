package com.axmor.uitest.framework.internal.builders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.axmor.uitest.framework.config.EnableOption;
import com.axmor.uitest.framework.config.ExecutionMode;
import com.axmor.uitest.framework.config.TestConfig;
import com.axmor.uitest.framework.internal.DriverContext;
import com.axmor.uitest.framework.internal.SelenoidUrl;
import com.google.common.base.Strings;

public abstract class AbstractDriverBuilder<T extends AbstractDriverOptions<?>> implements DriverBuilder {
    private TestConfig config;
    private DriverContext context;
    private T options;

    public TestConfig config() {
        return config;
    }

    public DriverContext context() {
        return context;
    }

    public T getOptions() {
        return options;
    }

    abstract T createOptions();

    void addAdditionalOption(String name, Object value) {
        getOptions().setCapability(name, value);
    }

    @Override
    public DriverContext build(String testName, TestConfig config) {
        this.config = config;

        options = createOptions();
        options.setAcceptInsecureCerts(true);

        context = new DriverContext();
        context.setRunName(testName);

        if (config.engine().executionMode() == ExecutionMode.LOCAL) {
            configureLocalDriverOptions();
            context.setDriver(buildLocalDriver());
        } else {
            if (Strings.isNullOrEmpty(config.engine().remoteEngineUrl())) {
                throw new IllegalArgumentException(
                        "Параметр engine.remoteEngineUrl не указан в конфигурационном файле");
            }

            configureRemoteDriverOptions();
            context.setDriver(buildRemoteDriver());
        }

        return context;
    }

    void configureLocalDriverOptions() {
    }

    void configureRemoteDriverOptions() {
        addAdditionalOption("name", context.runName());

        if (config.engine().enableVnc() != EnableOption.DISABLED) {
            addAdditionalOption("enableVnc", true);
        }
        if (config.engine().enableVideo() != EnableOption.DISABLED) {
            addAdditionalOption("enableVideo", true);
            context.setVideoName(context.runName() + ".mp4");
            addAdditionalOption("videoName", context.videoName());
            context.setNeedWarmUp(true);
        }
        if (config.engine().enableLog() != EnableOption.DISABLED) {
            addAdditionalOption("enableLog", true);
            context.setLogName(context.runName() + ".log");
            addAdditionalOption("logName", context.logName());
        }
    }

    WebDriver buildLocalDriver() {
        throw new UnsupportedOperationException(
                config.engine().engineName() + " не поддерживает исполнение в локальном режиме");
    }

    WebDriver buildRemoteDriver() {
        return new RemoteWebDriver(
                SelenoidUrl.gatewayUrl(config.engine().remoteEngineUrl()), options);
    }
}
