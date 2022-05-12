package com.axmor.uitest.framework.internal;

import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidUrl {
    public static URL gatewayUrl(String baseUrl) {
        try {
            return new URL(new URL(baseUrl), "/wd/hub");
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static URL videoUrl(String baseUrl, String videoName) {
        try {
            return new URL(new URL(baseUrl), "/video/" + videoName);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static URL logUrl(String baseUrl, String logName) {
        try {
            return new URL(new URL(baseUrl), "/logs/" + logName);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
