package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.AgeGatePage;
import com.medmen.bdd.utils.FileLoaderUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class EmailSignUpStepDefs {

  private AgeGatePage ageGatePage;
  private WebDriver driver;
  private static String email;
  private static String environment;

  @When("^I enter a valid email address into the \"([^\"]*)\" sign up box$")
  public void i_enter_a_valid_email_address_into_the_sign_up_box(String newsletterKey) {
    driver = DriverConfig.getDriver();
    ageGatePage = new AgeGatePage(driver);

    FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    environment = System.getProperty("env", "stage");
    if (environment.toLowerCase().contains("localhost")) {
      email = fileLoaderUtils.getValueFromPropertyFile("local.properties", "email");
    } else if (environment.toLowerCase().contains("stage")) {
      email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "email");
    } else if (environment.toLowerCase().contains("prod")) {
      email = fileLoaderUtils.getValueFromPropertyFile("prod.properties", "email");
    }
    if (newsletterKey.contains("Age Gate Newsletter")){
      ageGatePage.enterEmailAddress(email);
    } else if (newsletterKey.contains("Get 10% Off Your First Purchase.")){
      //todo exit pop flow


    } else if (newsletterKey.contains("Keep in Touch")){
      //todo footer sign flow
    }
    ageGatePage.enterEmailAddress(email);
  }


  public static String getEmail() {
    return email;
  }

  public static String getEnvironment() {
    return environment;
  }

  public static void setEmail(String email) {
    EmailSignUpStepDefs.email = email;
  }

  public static void setEnvironment(String environment) {
    EmailSignUpStepDefs.environment = environment;
  }
}
