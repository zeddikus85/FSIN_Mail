package com.axmor.uitest.framework.internal.builders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxDriverBuilder extends AbstractDriverBuilder<FirefoxOptions> {
    @Override
    protected FirefoxOptions createOptions() {
        return new FirefoxOptions();
    }

    @Override
    protected WebDriver buildLocalDriver() {
        return new FirefoxDriver(getOptions());
    }
}
