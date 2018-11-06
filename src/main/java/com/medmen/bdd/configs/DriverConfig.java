package com.medmen.bdd.configs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.util.ObjectUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverConfig {

  static final Logger logger = LogManager.getLogger(DriverConfig.class);

  private static long DEFAULT_WAIT = 20;
  private static String GRID_URL = "http://localhost:4444/wd/hub";
  private static String driverName = System.getProperty("browser", "firefox");
  private static String browserLocation = System.getProperty("browserLocation", "local");
  private static String isHeadless = System.getProperty("headless", "true");

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
        logger.error("Exception: Invalid environment " + browserLocation);
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
    switch (driverName.toLowerCase()) {
      case "phantomjs":
        try {
          driver = new PhantomJSDriver(capabilities);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
        return driver;
      case "chrome":
        ChromeOptions chromeOptions = new ChromeOptions();
        if (Boolean.valueOf(isHeadless)) {
          chromeOptions.addArguments("--headless");
        }
        try {
          driver = new ChromeDriver(chromeOptions);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
        return driver;
      default:
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.valueOf(isHeadless)) {
          options.addArguments("-headless", "-safe-mode");
        }
        try {
          driver = new FirefoxDriver(options);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
        return driver;
    }
  }

  private static DesiredCapabilities setDesiredCapabilities() {
    DesiredCapabilities capabilities;
    if (driverName.toLowerCase().contains("chrome")) {
      capabilities = DesiredCapabilities.chrome();
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
        driver.close();
        driver.quit();
      } catch (NoSuchMethodError nsme) {
        // in case quit fails
        logger.error("DriverConfigs.closeDriver: NoSuchMethodError", nsme);
        nsme.printStackTrace();
      } catch (NoSuchSessionException nsse) { // in case close fails
        logger.error("DriverConfigs.closeDriver: NoSuchSessionException", nsse);
        nsse.printStackTrace();
      } catch (SessionNotCreatedException snce) {
        logger.error("DriverConfigs.closeDriver: SessionNotCreatedException", snce);
        snce.printStackTrace();
      } // in case close fails
      driver = null;
    } else {
      logger.warn("DriverConfigs.closeDriver Driver already closed...");
    }
  }
}
