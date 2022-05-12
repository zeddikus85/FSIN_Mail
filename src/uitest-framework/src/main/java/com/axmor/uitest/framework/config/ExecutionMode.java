package com.axmor.uitest.framework.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ExecutionMode {
    @JsonProperty("local")
    LOCAL,

    @JsonProperty("remote")
    REMOTE
}
