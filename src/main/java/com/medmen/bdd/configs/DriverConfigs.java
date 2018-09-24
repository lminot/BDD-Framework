package com.medmen.bdd.configs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.DriverManagerType.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class DriverConfigs {

  static final Logger logger = LogManager.getLogger(DriverConfigs.class);

  public static String preferredDriver = System.getProperty("browser", "Firefox");
  public static boolean headless = Boolean.valueOf(System.getProperty("headless"));
  public static String OPERATING_SYSTEM = System.getProperty("os.name").toLowerCase();
  public static long DEFAULT_WAIT = 20;
  protected static WebDriver driver;

  public static WebDriver getDriver() {
    return chooseDriver(preferredDriver.toLowerCase());
  }

  public static WebDriver chooseDriver(String driverName) {

//    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
//    System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
//    System.setProperty("phantomjs.binary.path", "phantomjs.exe");
//    Capabilities chromeCapabilities = DesiredCapabilities.chrome();
//    Capabilities firefoxCapabilities = DesiredCapabilities.firefox();

    if (driver != null) {
      return driver;
    }

    if (driverName.toLowerCase().equals("chrome")) {

      ChromeOptions chromeOptions = new ChromeOptions();
      if (OPERATING_SYSTEM.contains("linux")) {
        System.setProperty("webdriver.chrome.driver", "/root/chromedriver");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.setBinary("chromedriver");
      }
      if (headless) {
        chromeOptions.addArguments("--headless");
      }

      WebDriverManager.getInstance(CHROME).setup();
      driver = new ChromeDriver(chromeOptions);

    } else if (driverName.toLowerCase().equals("firefox")) {
      driver = defaultDriver();

    } else if (driverName.toLowerCase().equals("phantomjs")) {
      WebDriverManager.getInstance(PHANTOMJS).setup();
      driver = new PhantomJSDriver(setPhantomJsCapabilities());

    } else if (driverName.toLowerCase().equals("edge")) {
      WebDriverManager.getInstance(EDGE).setup();
      driver = new EdgeDriver();
    } else {
      logger.info(
          "Unable to locate browser: "
              + driverName.toLowerCase()
              + "\nUsing default Firefox driver");
      driver = defaultDriver();
    }

    manageDriver();
    return driver;
  }

  private static WebDriver defaultDriver() {

    FirefoxOptions options = new FirefoxOptions();
    if (OPERATING_SYSTEM.contains("linux")) {
      System.setProperty("webdriver.gecko.driver","geckodriver" );
      options.setBinary("geckodriver");
      options.setCapability("marionette", false);
    }

    if (headless) {
      options.addArguments("-headless", "-safe-mode");
    }
    WebDriverManager.getInstance(FIREFOX).setup();
    return new FirefoxDriver(options);
  }

  private static DesiredCapabilities setPhantomJsCapabilities() {
    DesiredCapabilities capabilities;
    capabilities = DesiredCapabilities.firefox();
    capabilities.setJavascriptEnabled(true);
    capabilities.setCapability("takesScreenshot", true);
    return capabilities;
  }

  private static WebDriver manageDriver() {
    driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    return driver;
  }

  public static WebElement waitAndGetElementByCssSelector(
      WebDriver driver, String selector, int seconds) {
    By selection = By.cssSelector(selector);
    return (new WebDriverWait(driver, seconds))
        .until( // ensure element is visible!
            visibilityOfElementLocated(selection));
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
