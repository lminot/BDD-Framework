package com.medmen.bdd.stepDefs.ui;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.MedMenHomePage;
import com.medmen.bdd.pages.AgeGatePage;
import com.medmen.bdd.utils.FileLoaderUtils;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;

import static com.medmen.bdd.helperMethods.BaseTest.navigationObj;
import static org.junit.Assert.assertTrue;

public class CommonStepDefs {

  private AgeGatePage medmenHomePage;
  private WebDriver driver;
  private static String environment;
  private static String port;
  public static String baseUrl;

  @Before
  public void setBaseUrl() {
    FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    environment = System.getProperty("env", "stage");
    if (environment.toLowerCase().contains("localhost")) {
      port = fileLoaderUtils.getValueFromPropertyFile("local.properties", "host.port");
      baseUrl = "http://localhost:" + port;
    } else if (environment.toLowerCase().contains("stage")) {
      baseUrl = "https://staging.medmen.com";
    } else if (environment.toLowerCase().contains("prod")) {
      baseUrl = "https://medmen.com";
    }
  }

  @After
  public void clearCookies() {
    if (driver != null) {
      driver.manage().deleteAllCookies();
    }
  }

  @Given("^I navigate to the Medmen homepage$")
  public void i_navigate_to_the_medmen_homepage() {
    navigationObj.navigateTo(getBaseUrl());
  }

  @Given("^I click \"([^\"]*)\" on the age verification prompt$")
  public void i_click_on_the_age_verification_prompt(String ageCheck) {
    driver = DriverConfig.getDriver();
    medmenHomePage = new AgeGatePage(driver);
    assertTrue(medmenHomePage.isInitialized());
    if (ageCheck.contains("YES")) {
      medmenHomePage.selectYesImTwentyOne();
    } else {
      medmenHomePage.selectNoImTwentyOne();
    }
  }

  @Given("^I click the enter button$")
  public void i_click_the_enter_button() {
    driver = DriverConfig.getDriver();
    medmenHomePage = new AgeGatePage(driver);
    assertTrue(medmenHomePage.isInitialized());
    medmenHomePage.clickEnter();
  }

  @When("^I select the statemade option from the top nav$")
  public void i_select_the_statemade_option_from_the_top_nav() {
    driver = DriverConfig.getDriver();
    MedMenHomePage medMenHomePage = new MedMenHomePage(driver);
    assertTrue(medMenHomePage.isInitialized());
    medMenHomePage.selectStatemade();
  }

  public static String getBaseUrl() {
    return baseUrl;
  }
}
