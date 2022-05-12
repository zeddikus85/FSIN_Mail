package com.axmor.uitest.framework.internal;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.TestInfo;

import com.axmor.uitest.framework.config.TestConfig;
import com.axmor.uitest.framework.internal.builders.AndroidDriverBuilder;
import com.axmor.uitest.framework.internal.builders.ChromeDriverBuilder;
import com.axmor.uitest.framework.internal.builders.ChromeMobileDriverBuilder;
import com.axmor.uitest.framework.internal.builders.DriverBuilder;
import com.axmor.uitest.framework.internal.builders.FirefoxDriverBuilder;
import com.axmor.uitest.framework.internal.builders.OperaDriverBuilder;

public final class DriverFactory {

    public static DriverContext buildDriver(TestConfig config, TestInfo testInfo) {
        DriverBuilder builder;

        switch (config.engine().engineName().toLowerCase()) {
            case "chrome":
                builder = new ChromeDriverBuilder();
                break;
            case "opera":
                builder = new OperaDriverBuilder();
                break;
            case "firefox":
                builder = new FirefoxDriverBuilder();
                break;
            case "chromemobile":
                builder = new ChromeMobileDriverBuilder();
                break;
            case "android":
                builder = new AndroidDriverBuilder();
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый драйвер: " + config.engine().engineName());
        }

        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd.HHmm").format(LocalDateTime.now());
        String testName = config.app().projectName() + "." +
                          config.app().environmentName() + "." +
                          testInfo.getTestClass().map(Class::getSimpleName).orElse(null) + "." +
                          testInfo.getTestMethod().map(Method::getName).orElse(null) + "." +
                          timestamp;

        return builder.build(testName, config);
    }
}
