package com.axmor.uitest.framework.core;


import com.axmor.uitest.framework.Driver;
import com.axmor.uitest.framework.TestResultWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestResultWatcher.class)
public abstract class TestCore {
    @BeforeEach
    void beforeEachTest(TestInfo testInfo) {
        Driver.initialize(testInfo);
    }
}
