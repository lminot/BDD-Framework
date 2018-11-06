package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.MedMenHomePage;
import com.medmen.bdd.pages.MedMenHomePageOverlay;
import com.medmen.bdd.utils.FileLoaderUtils;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.medmen.bdd.helperMethods.BaseTest.navigationObj;
import static org.junit.Assert.assertTrue;

public class EmailSignUpStepDefs {

  private MedMenHomePageOverlay medmenHomePageOverlay;
  private WebDriver driver;
  private static String email;
  private static String environment;

  @And("^I enter a valid email address into the Newsletter sign up box$")
  public void iEnterAValidEmailAddressIntoTheNewsletterSignUpBox() {
    driver = DriverConfig.getDriver();
    medmenHomePageOverlay = new MedMenHomePageOverlay(driver);

    FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    environment = System.getProperty("env", "stage");
    if (environment.toLowerCase().contains("localhost")) {
      email = fileLoaderUtils.getValueFromPropertyFile("local.properties", "email");
    } else if (environment.toLowerCase().contains("stage")) {
      email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "email");
    } else if (environment.toLowerCase().contains("prod")) {
      email = fileLoaderUtils.getValueFromPropertyFile("prod.properties", "email");
    }
    medmenHomePageOverlay.enterEmailAddress(email);
  }
}
