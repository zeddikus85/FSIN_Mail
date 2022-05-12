package com.axmor.uitest.framework.internal.builders;

import com.axmor.uitest.framework.config.TestConfig;
import com.axmor.uitest.framework.internal.DriverContext;

public interface DriverBuilder {
    DriverContext build(String testName, TestConfig config);
}
