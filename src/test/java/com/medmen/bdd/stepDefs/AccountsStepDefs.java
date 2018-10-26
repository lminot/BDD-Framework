package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.helperMethods.BaseTest;
import com.medmen.bdd.pages.Cart;
import com.medmen.bdd.pages.CreateAccountPage;
import com.medmen.bdd.pages.StorePage;
import com.medmen.bdd.utils.FileLoaderUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class AccountsStepDefs implements BaseTest {

  private WebDriver driver;
  public String menuBaseUrl = "https://menu-staging.medmen.com";

  @BeforeClass
    public void setMenuBaseUrl() {
      //todo fix me
      //menuBaseUrl = "https://menu-staging.medmen.com";

      driver = DriverConfig.getDriver();
    }

  @Given("^I navigate to the create account page$")
  public void i_navigate_to_the_create_account_page() {
    navigationObj.navigateTo(menuBaseUrl + "/register");
  }

  @Given("^I enter valid information in all required fields$")
  public void i_enter_valid_information_in_all_required_fields() {
    FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    String email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "user.password");

    CreateAccountPage createAccountPage = new CreateAccountPage(DriverConfig.getDriver());
    createAccountPage.enterEmailAddress(email);
    createAccountPage.enterPassword("popeye123");
    createAccountPage.genderSet("male");
    createAccountPage.enterFirstName("Testing");
    createAccountPage.enterLastName("Guy");
    createAccountPage.enterDobMonth("January");
    createAccountPage.enterDobDay("27");
    createAccountPage.enterDobYear("1981");
    createAccountPage.enterZipCode("90210");
    createAccountPage.enterCity("Culver City");
    createAccountPage.enterState(2);
    createAccountPage.enterCountry(1);
    createAccountPage.enterPhoneNum("8008749200");
    createAccountPage.selectPerfStore(1);
    createAccountPage.checkDataPolicy();
    createAccountPage.checkTermsOfService();
  }

  @When("^I click the create account button$")
  public void i_click_the_create_account_button() {
    CreateAccountPage createAccountPage = new CreateAccountPage(driver);
    createAccountPage.clickCreateAccount();
  }

  @When("^leave all required fields empty$")
  public void leave_all_required_fields_empty() {
    navigationObj.hoverOverElement("css", ".c-button--primary");
  }

  @Then("^I should see validation error text on all mandatory fields$")
  public void i_should_see_validation_error_text_on_all_mandatory_fields() {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^I am directed to my Store Page$")
  public void i_am_directed_to_my_Store_Page() {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^I am I have a logged in account$")
  public void i_am_I_have_a_logged_in_account() {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I select \"([^\"]*)\" items to add to cart$")
  public void i_select_items_to_add_to_cart(int itemNum) {
    StorePage storePage = new StorePage(driver);
    assertTrue(storePage.isInitialized());

    for (int i = 1; itemNum > 0; i++) {
      if (driver
          .findElement(
              By.xpath(
                  "//div[@class='o-product-grid']//div["
                      + i
                      + "]//div[1]//div[1]//div[2]//button[1]"))
          .getText()
          .contains("Add")) {
        System.out.println(
            driver
                .findElement(
                    By.xpath(
                        "//div[@class='o-product-grid']//div["
                            + i
                            + "]//div[1]//div[1]//div[2]//button[1]"))
                .getText());
        clickObj.clickForcefully(
            "xpath",
            "//div[@class='o-product-grid']//div[" + i + "]//div[1]//div[1]//div[2]//button[1]");
        itemNum--;
      }
    }
    storePage.selectCart();
  }

  @When("^I click checkout$")
  public void i_click_checkout() {
    Cart cart = new Cart(driver);
    assertTrue(cart.isInitialized());
    cart.clickCheckout();
  }
}
