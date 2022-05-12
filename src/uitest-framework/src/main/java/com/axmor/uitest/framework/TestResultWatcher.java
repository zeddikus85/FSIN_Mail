package com.axmor.uitest.framework;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import com.axmor.uitest.framework.config.EnableOption;

public final class TestResultWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        testFinished(TestStatus.SUCCESSFUL);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        testFinished(TestStatus.FAILED);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        testFinished(TestStatus.ABORTED);
    }

    private static void testFinished(TestStatus outcome) {
        if (!Driver.isReady()) {
            return;
        }

        try {
            boolean hasVideo = Driver.videoUrl() != null;
            boolean hasLog = Driver.logUrl() != null;

            if (Driver.engineConfig().enableVideo() == EnableOption.FAILED_ONLY && outcome == TestStatus.SUCCESSFUL) {
                Driver.deleteVideo();
                hasVideo = false;
            }
            if (Driver.engineConfig().enableLog() == EnableOption.FAILED_ONLY && outcome == TestStatus.SUCCESSFUL) {
                Driver.deleteLog();
                hasLog = false;
            }

            if (hasVideo) {
                Driver.logger().info("Recorded video: {}", Driver.videoUrl());
            }
            if (hasLog) {
                Driver.logger().info("Internal log: {}", Driver.logUrl());
            }

            if (Driver.engineConfig().enableScreenshot() == EnableOption.ALWAYS ||
                (Driver.engineConfig().enableScreenshot() == EnableOption.FAILED_ONLY && outcome == TestStatus.FAILED)) {
                String screenshotUrl = Driver.takeScreenshot();
                if (screenshotUrl != null) {
                    Driver.logger().info("Final screenshot: {}", screenshotUrl);
                }
            }
        } finally {
            Driver.quit();
        }
    }

    private enum TestStatus {
        SUCCESSFUL,
        FAILED,
        ABORTED
    }
}
