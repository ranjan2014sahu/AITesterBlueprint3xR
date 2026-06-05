package com.sf.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getUrl() {
        return properties.getProperty("salesforce.url");
    }

    public static String getValidUsername() {
        return properties.getProperty("valid.username");
    }

    public static String getValidPassword() {
        return properties.getProperty("valid.password");
    }

    public static String getInvalidUsername() {
        return properties.getProperty("invalid.username");
    }

    public static String getInvalidPassword() {
        return properties.getProperty("invalid.password");
    }

    public static long getImplicitWait() {
        return Long.parseLong(properties.getProperty("implicit.wait"));
    }

    public static long getExplicitWait() {
        return Long.parseLong(properties.getProperty("explicit.wait"));
    }
}
