package com.medmen.bdd.configs;

public class EnvironmentConfig {
    //todo control env setting here
    public static String getEnvironment() {
        return System.getProperty("env", "stage");
    }

    public static String getBrowser() {
        return System.getProperty("browser", "firefox");
    }

    public static String getHeadless() {
        return System.getProperty("headless", "true");
    }

    public static String getBrowserLocation() {
        return System.getProperty("browserLocation", "local");
    }

}
