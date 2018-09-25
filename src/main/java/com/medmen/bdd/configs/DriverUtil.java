package com.medmen.bdd.configs;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverUtil {
  static final Logger logger = LogManager.getLogger(DriverUtil.class);

  public static long DEFAULT_WAIT = 20;
  public static String GRID_URL = "http://localhost:4444/wd/hub";
  public static String driverName = System.getProperty("browser", "Firefox");
  protected static WebDriver driver;
  static String currentPath = System.getProperty("user.dir");
  static Properties prop = new Properties();
  public static DesiredCapabilities capability;


  public static DesiredCapabilities getCapability(InputStream input) {
    DesiredCapabilities capability = new DesiredCapabilities();

    try {
      prop.load(input);
      // set capabilities
      Enumeration<Object> enuKeys = prop.keys();
      while (enuKeys.hasMoreElements()) {
        String key = (String) enuKeys.nextElement();
        String value = prop.getProperty(key);
        capability.setCapability(key, value);
      }
      input.close();
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return capability;
  }

  public static WebDriver getDefaultDriver() {
    if (driver != null) {
      return driver;
    }

    String environment = System.getProperty("env", "desktop");
    String platform = "";
    String config = System.getProperty("config", "");

    //todo clean me up

    if (!config.isEmpty()) {
      try {
        environment = config.split("_")[0].toLowerCase();
        platform = config.split("_")[1].toLowerCase();
        InputStream input =
            new FileInputStream(
                currentPath + "/src/main/java/browserConfigs/" + config + ".properties");
        capability = getCapability(input);
      } catch (Exception e) {
        logger.error(
            "Exception : File not present or Invalid config file name " + config + ".properties",
            e.getMessage());
      }
    }

    switch (environment) {
      case "local":
        if (platform.equals("android")) driver = androidDriver(capability);
        else if (platform.equals("ios")) driver = iosDriver(capability);
        else {
          logger.error("unsupported platform");
        }
        break;

      case "remote":
        try {
          driver = new RemoteWebDriver(new URL(GRID_URL), setDesiredCapabilities(capability));
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
        break;

      case "browserstack":
        driver = browserStackDriver();
        break;

      case "saucelab":
        driver = sauceLabsDriver();
        break;

      case "desktop":
        manageDriver(setDesiredCapabilities(capability));
        break;

      default:
        logger.error("Exception : Invalid platform " + environment);
    }
    return driver;
  }

  private static void manageDriver(DesiredCapabilities capabilities) {
    driver = chooseDriver(capabilities);
    driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  private static DesiredCapabilities setDesiredCapabilities(DesiredCapabilities capabilities) {
    if (driverName.toLowerCase().contains("firefox")) {
      capabilities = DesiredCapabilities.firefox();
    } else if (driverName.toLowerCase().contains("chrome")) {
      capabilities = DesiredCapabilities.chrome();
    }

    capabilities.setJavascriptEnabled(true);
    capabilities.setCapability("takesScreenshot", true);
    return capabilities;
  }

  private static WebDriver sauceLabsDriver() {
    URL remoteDriverURL;
    RemoteWebDriver remoteDriver = null;

    try {
      InputStream input =
          new FileInputStream(currentPath + "/src/main/java/platformConfigs/saucelab.properties");
      prop.load(input);

      String url =
          prop.getProperty("protocol")
              + "://"
              + prop.getProperty("username")
              + ":"
              + prop.getProperty("access_key")
              + prop.getProperty("url");

      input.close();
      prop.clear();
      remoteDriverURL = new URL(url);
      remoteDriver = new RemoteWebDriver(remoteDriverURL, capability);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return remoteDriver;
  }

  private static WebDriver browserStackDriver() {
    URL remoteDriverURL = null;
    try {
      InputStream input =
          new FileInputStream(
              currentPath + "/src/main/java/platformConfigs/browserstack.properties");
      prop.load(input);

      String url =
          prop.getProperty("protocol")
              + "://"
              + prop.getProperty("username")
              + ":"
              + prop.getProperty("access_key")
              + prop.getProperty("url");

      input.close();
      prop.clear();
      remoteDriverURL = new URL(url);
    } catch (Exception e) {
      logger.error(e.getMessage());
    }
    return new RemoteWebDriver(remoteDriverURL, capability);
  }

  private static WebDriver androidDriver(DesiredCapabilities capabilities) {
    String port = "4723";
    try {
      driver = new AndroidDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return driver;
  }

  private static WebDriver iosDriver(DesiredCapabilities capabilities) {
    String port = "4723";
    try {
      driver = new IOSDriver(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return driver;
  }

  private static WebDriver chooseDriver(DesiredCapabilities capabilities) {
//    String driverName = System.getProperty("browser", "Firefox");
    boolean headless = Boolean.valueOf(System.getProperty("headless"));
//    Boolean.valueOf(System.getProperty("headless"));

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
        if (headless) {
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
        if (headless) {
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

  public static WebElement waitAndGetElementByCssSelector(
      WebDriver driver, String selector, int seconds) {
    By selection = By.cssSelector(selector);
    return (new WebDriverWait(driver, seconds))
        .until( // ensure element is visible!
            ExpectedConditions.visibilityOfElementLocated(selection));
  }

  public static void closeDriver() {
    if (driver != null) {
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
