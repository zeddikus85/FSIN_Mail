package com.axmor.uitest.framework.internal.builders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeDriverBuilder extends AbstractDriverBuilder<ChromeOptions> {
    @Override
    protected ChromeOptions createOptions() {
        return new ChromeOptions();
    }

    @Override
    protected void configureRemoteDriverOptions() {
        super.configureRemoteDriverOptions();
        addAdditionalOption("showChromedriverLog", true);
    }

    @Override
    protected WebDriver buildLocalDriver() {
        return new ChromeDriver(getOptions());
    }
}
