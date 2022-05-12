package com.axmor.uitest.framework.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

import org.slf4j.LoggerFactory;

import com.axmor.uitest.framework.internal.DriverFactory;
import com.google.common.base.Strings;

final class ConfigHelper {
    static String getString(String jsonKey, String defaultValue, boolean required) {
        String envVarName = jsonKeyToEnv(jsonKey);
        String envValue = System.getenv(envVarName);
        if (Strings.isNullOrEmpty(envValue)) {
            if (required && Strings.isNullOrEmpty(defaultValue)) {
                throw new IllegalArgumentException("Параметр " + jsonKey + " является обязательным");
            }
            return defaultValue;
        }

        return envValue;
    }

    static ExecutionMode getExecutionMode(String jsonKey, ExecutionMode jsonValue) {
        String envVarName = jsonKeyToEnv(jsonKey);
        String envValue = System.getenv(envVarName);
        if (Strings.isNullOrEmpty(envValue)) {
            return Objects.requireNonNullElse(jsonValue, ExecutionMode.LOCAL);
        } else {
            switch (envValue) {
                case "local": return ExecutionMode.LOCAL;
                case "remote": return ExecutionMode.REMOTE;
                default: throw new IllegalArgumentException(
                        "Неизвестное значение " + jsonKey + ": " + envValue);
            }
        }
    }

    static EnableOption getEnableOption(String envVarName, EnableOption jsonValue) {
        String envValue = System.getenv(envVarName);
        if (Strings.isNullOrEmpty(envValue)) {
            return Objects.requireNonNullElse(jsonValue, EnableOption.DISABLED);
        } else {
            switch (envValue) {
                case "disabled": return EnableOption.DISABLED;
                case "failedOnly": return EnableOption.FAILED_ONLY;
                case "always": return EnableOption.ALWAYS;
                default: throw new IllegalArgumentException(
                        "Неизвестное значение EnableOption: " + envValue);
            }
        }
    }

    static int getInt(String envVarName, Integer jsonValue, int defaultValue) {
        String envValue = System.getenv(envVarName);
        if (Strings.isNullOrEmpty(envValue)) {
            return Objects.requireNonNullElse(jsonValue, defaultValue);
        } else {
            try {
                return Integer.parseInt(envValue);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(
                        "Не получилось конвертировать значение переменной " + envVarName +
                        " в int: " + envValue);
            }
        }
    }

    static double getDouble(String envVarName, Double jsonValue, double defaultValue) {
        String envValue = System.getenv(envVarName);
        if (Strings.isNullOrEmpty(envValue)) {
            return Objects.requireNonNullElse(jsonValue, defaultValue);
        } else {
            try {
                return Double.parseDouble(envValue);
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException(
                        "Не получилось конвертировать значение переменной " + envVarName +
                        " в double: " + envValue);
            }
        }
    }

    public static String getEnvironmentName(String jsonValue) {
        String envName = getString("app.environmentName", jsonValue, false);
        if (Strings.isNullOrEmpty(envName)) {
            try {
                envName = InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
                LoggerFactory.getLogger(DriverFactory.class).warn(ex.getMessage());
                envName = "unknown";
            }
        }
        return envName;
    }

    private static String jsonKeyToEnv(String jsonKey) {
        return "TC_" + jsonKey.replace('.', '_').toUpperCase();
    }
}
