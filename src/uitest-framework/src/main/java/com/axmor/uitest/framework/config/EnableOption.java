package com.axmor.uitest.framework.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EnableOption {
    @JsonProperty("disabled")
    DISABLED,

    @JsonProperty("failedOnly")
    FAILED_ONLY,

    @JsonProperty("always")
    ALWAYS
}
