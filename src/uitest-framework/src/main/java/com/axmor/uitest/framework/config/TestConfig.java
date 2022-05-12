package com.axmor.uitest.framework.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class TestConfig {
    private final TestAppConfig app;
    private final TestEngineConfig engine;

    @JsonCreator
    public TestConfig(@JsonProperty("app") TestAppConfig app, @JsonProperty("engine") TestEngineConfig engine) {
        this.app = app;
        this.engine = engine;
    }

    public TestAppConfig app() {
        return app;
    }

    public TestEngineConfig engine() {
        return engine;
    }
}
