package com.medmen.bdd.configs;

public class EnvironmentConfig {

    public static String configFile;

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
        if (System.getProperty("env") == null || System.getProperty("env").isEmpty()) {
            return "stage.properties";
        }
        switch (System.getProperty("env")) {
            case "local":
                configFile = "local.properties";
            case "dev":
                configFile = "local.properties";
            case "stage":
                configFile = "stage.properties";
            case "prod":
                configFile = "prod.properties";
            default:
                configFile = "stage.properties";
        }
        return configFile;
    }

}
