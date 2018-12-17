package com.medmen.bdd.configs;

import com.medmen.bdd.utils.FileLoaderUtils;

public class EnvironmentConfig {

    public static String configFile;
    private static FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();

    public static String getEnvironment() {
        String environment = (System.getProperty("env") == null ||
                System.getProperty("env").isEmpty()) ? "stage" : System.getProperty("env");
        return environment;
    }

    public static String getBrowser() {
        String browser = (System.getProperty("browser") == null ||
                System.getProperty("browser").isEmpty()) ? "firefox" : System.getProperty("browser");
        return browser;
    }

    public static String getHeadless() {
        String headless = (System.getProperty("headless") == null ||
                System.getProperty("headless").isEmpty()) ? "true" : System.getProperty("headless");
        return headless;
    }

    public static String getBrowserLocation() {
        String browserLocation = (System.getProperty("browserLocation") == null ||
                System.getProperty("browserLocation").isEmpty()) ? "local" : System.getProperty("browserLocation");
        return browserLocation;
    }

    public static String getConfigFile() {
        String env;

        if (System.getProperty("env") == null || System.getProperty("env").isEmpty()) {
            return "stage.properties";
        } else {
            env = System.getProperty("env");
            switch (env) {
                case "local":
                    configFile = "local.properties";
                    break;
                case "dev":
                    configFile = "local.properties";
                    break;
                case "stage":
                    configFile = "stage.properties";
                    break;
                case "prod":
                    configFile = "prod.properties";
                    break;
                default:
                    configFile = "stage.properties";
                    break;
            }
            return configFile;
        }
    }


    public static String getStaticBaseUrl() {
        return fileLoaderUtils.getValueFromPropertyFile(getConfigFile(), "medmen.static.ui.url");
    }

    public static String getMenuBaseUrl() {
        return fileLoaderUtils.getValueFromPropertyFile(getConfigFile(), "medmen.menu.ui.url");
    }

    public static String getMedmenApiBaseUrl() {
        return fileLoaderUtils.getValueFromPropertyFile(getConfigFile(), "medmen.api.base.url");
    }

    public static String getEmailAddress() {
        return fileLoaderUtils.getValueFromPropertyFile(getConfigFile(), "email");
    }
}
