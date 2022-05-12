package com.axmor.uitest.framework.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axmor.uitest.framework.config.TestConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public final class TestEnvironment {
    private static final Logger LOG = LoggerFactory.getLogger(TestEnvironment.class);
    private static TestConfig config;

    private static void initialize() {
        File dir = new File("").getAbsoluteFile();
        File file;
        do {
            if (dir == null) {
                throw new RuntimeException("Не найден test-config.json");
            }
            file = new File(dir, "test-config.json");
            dir = dir.getParentFile();
        } while (!file.exists());

        try {
            LOG.info("Найден файл конфигурации: " + file);
            String content = Files.readString(file.toPath());
            config = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(content, TestConfig.class);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    private static InputStream getURLInputStream(URL url) throws IOException {
        if (url.getProtocol().equals("file")) {
            File file = new File(url.getPath());
            while (!file.exists()) {
                file = file.getParentFile();
                if (file == null) {
                    throw new FileNotFoundException("Не найден test-config.json");
                }
            }
            return new FileInputStream(file);
        } else {
            return url.openConnection().getInputStream();
        }
    }

    public static TestConfig configuration() {
        if (config == null) {
            initialize();
        }
        return config;
    }
}
