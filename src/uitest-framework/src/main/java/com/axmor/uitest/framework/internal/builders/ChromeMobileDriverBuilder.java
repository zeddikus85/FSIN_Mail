package com.axmor.uitest.framework.internal.builders;

import org.openqa.selenium.WebDriver;

import com.google.common.base.Strings;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public final class ChromeMobileDriverBuilder extends AbstractDriverBuilder<DriverOptions> {
    @Override
    protected DriverOptions createOptions() {
        return new DriverOptions();
    }

    @Override
    protected void configureRemoteDriverOptions() {
        super.configureRemoteDriverOptions();

        String version = (String) getOptions().getCapability("version");
        if (Strings.isNullOrEmpty(version)) {
            version = "mobile";
        } else {
            version = "mobile:" + version;
        }
        addAdditionalOption(MobileCapabilityType.BROWSER_NAME, "chrome");
        addAdditionalOption("version", version);
        addAdditionalOption("desired-skin", "WSVGA");
        addAdditionalOption("desired-screen-resolution", "1024x600");
        addAdditionalOption("showChromedriverLog", true);
    }

    @Override
    protected WebDriver buildLocalDriver() {
        return new AndroidDriver(getOptions());
    }
}
