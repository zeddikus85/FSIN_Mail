package com.axmor.uitest.framework.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class TestAppConfig {
    private final String projectName;
    private final String environmentName;
    private final String url;
    private final String userLogin;
    private final String userPassword;
    private final String adminLogin;
    private final String adminPassword;
    private final String packageUrl;

    @JsonCreator
    public TestAppConfig(
            @JsonProperty("projectName") String projectName,
            @JsonProperty("environmentName") String environmentName,
            @JsonProperty("url") String url,
            @JsonProperty("userLogin") String userLogin,
            @JsonProperty("userPassword") String userPassword,
            @JsonProperty("adminLogin") String adminLogin,
            @JsonProperty("adminPassword") String adminPassword,
            @JsonProperty("packageUrl") String packageUrl) {
        this.projectName = ConfigHelper.getString("app.projectName", projectName, true);
        this.environmentName = ConfigHelper.getEnvironmentName(environmentName);
        this.url = ConfigHelper.getString("app.url", url, false);
        this.userLogin = ConfigHelper.getString("app.userLogin", userLogin, false);
        this.userPassword = ConfigHelper.getString("app.userPassword", userPassword, false);
        this.adminLogin = ConfigHelper.getString("app.adminLogin", adminLogin, false);
        this.adminPassword = ConfigHelper.getString("app.adminPassword", adminPassword, false);
        this.packageUrl = ConfigHelper.getString("app.packageUrl", packageUrl, false);
    }

    public String projectName() {
        return projectName;
    }

    public String environmentName() {
        return environmentName;
    }

    public String url() {
        return url;
    }

    public String userLogin() {
        return userLogin;
    }

    public String userPassword() {
        return userPassword;
    }

    public String adminLogin() {
        return adminLogin;
    }

    public String adminPassword() {
        return adminPassword;
    }

    public String packageUrl() {
        return packageUrl;
    }
}
