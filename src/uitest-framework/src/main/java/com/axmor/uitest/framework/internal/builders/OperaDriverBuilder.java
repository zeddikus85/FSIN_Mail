package com.axmor.uitest.framework.internal.builders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public final class OperaDriverBuilder extends AbstractDriverBuilder<OperaOptions> {
    @Override
    protected OperaOptions createOptions() {
        return new OperaOptions();
    }

    @Override
    protected WebDriver buildLocalDriver() {
        return new OperaDriver(getOptions());
    }
}
