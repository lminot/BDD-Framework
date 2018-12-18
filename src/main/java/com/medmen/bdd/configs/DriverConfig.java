package com.medmen.bdd.configs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverConfig {

    static final Logger logger = LogManager.getLogger(DriverConfig.class);

    private static long DEFAULT_WAIT = 20;
    private static EnvironmentConfig environmentConfig = new EnvironmentConfig();

    private static String GRID_URL = "http://localhost:4444/wd/hub";
    private static String driverName = environmentConfig.getBrowser();
    private static String browserLocation = environmentConfig.getBrowserLocation();
    private static String isHeadless = environmentConfig.getHeadless();

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (!ObjectUtils.isEmpty(driver)) {
            return driver;
        }

        switch (browserLocation) {
            case "remote":
                try {
                    driver = new RemoteWebDriver(new URL(GRID_URL), setDesiredCapabilities());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case "local":
                driver = chooseDriver(setDesiredCapabilities());
                break;

            default:
                logger.error("Exception: Invalid environment configuration: " + browserLocation);
                break;
        }
        return manageDriver(driver);
    }

    private static WebDriver manageDriver(WebDriver driver) {
        driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
        if (browserLocation.equals("local")) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver chooseDriver(DesiredCapabilities capabilities) {
        WebDriver driver = null;
        switch (driverName.toLowerCase()) {
            case "phantomjs":
                try {
                    driver = new PhantomJSDriver(capabilities);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (Boolean.valueOf(isHeadless)) {
                    chromeOptions.addArguments("--window-size=1920,1080");
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--headless");
                }
                try {
                    driver = new ChromeDriver(chromeOptions);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
            default:
                FirefoxOptions options = new FirefoxOptions();
                if (Boolean.valueOf(isHeadless)) {
                    options.addArguments("-headless", "-safe-mode");
                }
                try {
                    //comment the below for full firefox Selenium
                    disableFireFoxLogging();
                    driver = new FirefoxDriver(options);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
                break;
        }
        return driver;
    }

    private static void disableFireFoxLogging() {
        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
    }

    private static DesiredCapabilities setDesiredCapabilities() {
        DesiredCapabilities capabilities;
        if (driverName.toLowerCase().contains("chrome")) {
            capabilities = DesiredCapabilities.chrome();
            capabilities.setJavascriptEnabled(true);
        } else {
            capabilities = DesiredCapabilities.firefox();
        }

        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("takesScreenshot", true);
        return capabilities;
    }

    public static void closeDriver() {
        if (!ObjectUtils.isEmpty(driver)) {
            try {
                driver.manage().deleteAllCookies();
                driver.quit();
            } catch (Exception ex) {
                // in case quit fails
                logger.error("Error quitting driver :", ex);
                ex.printStackTrace();
            }
            driver = null;
        } else {
            logger.warn("Driver already quit...");
        }
    }
}
