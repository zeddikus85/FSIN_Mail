package com.axmor.uitest.framework.internal;

import org.openqa.selenium.WebDriver;

public final class DriverContext {
    private WebDriver driver;
    private String runName;
    private boolean needWarmUp;
    private String videoName;
    private String logName;

    public WebDriver driver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String runName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public boolean needWarmUp() {
        return needWarmUp;
    }

    public void setNeedWarmUp(boolean needWarmUp) {
        this.needWarmUp = needWarmUp;
    }

    public String videoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String logName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }
}
