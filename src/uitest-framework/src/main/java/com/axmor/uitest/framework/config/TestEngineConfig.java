package com.axmor.uitest.framework.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class TestEngineConfig {
    private final String engineName;
    private final ExecutionMode executionMode;
    private final String remoteEngineUrl;
    private final EnableOption enableVideo;
    private final EnableOption enableLog;
    private final EnableOption enableVnc;
    private final EnableOption enableScreenshot;
    private final String imageHostingUrl;
    private final int waitSeconds;
    private final double warmUpSeconds;

    @JsonCreator
    public TestEngineConfig(
            @JsonProperty("engineName") String engineName,
            @JsonProperty("executionMode") ExecutionMode executionMode,
            @JsonProperty("remoteEngineUrl") String remoteEngineUrl,
            @JsonProperty("imageHostingUrl") String imageHostingUrl,
            @JsonProperty("enableVideo") EnableOption enableVideo,
            @JsonProperty("enableLog") EnableOption enableLog,
            @JsonProperty("enableVnc") EnableOption enableVnc,
            @JsonProperty("enableScreenshot") EnableOption enableScreenshot,
            @JsonProperty("waitSeconds") Integer waitSeconds,
            @JsonProperty("warmupSeconds") Double warmUpSeconds) {
        this.engineName = ConfigHelper.getString("engine.engineName", engineName, true);
        this.executionMode = ConfigHelper.getExecutionMode("engine.executionMode", executionMode);
        this.remoteEngineUrl = ConfigHelper.getString("engine.remoteEngineUrl", remoteEngineUrl,
                this.executionMode == ExecutionMode.REMOTE);
        this.imageHostingUrl = ConfigHelper.getString("engine.imageHostingUrl", imageHostingUrl, false);
        this.enableVideo = ConfigHelper.getEnableOption("engine.enableVideo", enableVideo);
        this.enableLog = ConfigHelper.getEnableOption("engine.enableLog", enableLog);
        this.enableVnc = ConfigHelper.getEnableOption("engine.enableVnc", enableVnc);
        this.enableScreenshot = ConfigHelper.getEnableOption("engine.enableScreenshot",
                enableScreenshot);
        this.waitSeconds = ConfigHelper.getInt("engine.waitSeconds", waitSeconds, 30);
        this.warmUpSeconds = ConfigHelper.getDouble("engine.warmUpSeconds", warmUpSeconds, 1.5);
    }

    public String engineName() {
        return engineName;
    }

    public ExecutionMode executionMode() {
        return executionMode;
    }

    public String remoteEngineUrl() {
        return remoteEngineUrl;
    }

    public String imageHostingUrl() {
        return imageHostingUrl;
    }

    public EnableOption enableVideo() {
        return enableVideo;
    }

    public EnableOption enableLog() {
        return enableLog;
    }

    public EnableOption enableVnc() {
        return enableVnc;
    }

    public EnableOption enableScreenshot() {
        return enableScreenshot;
    }

    public int waitSeconds() {
        return waitSeconds;
    }

    public double warmUpSeconds() {
        return warmUpSeconds;
    }
}
