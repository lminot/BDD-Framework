package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverUtil;
import com.medmen.bdd.helperMethods.BaseTest;
import com.medmen.bdd.pages.Cart;
import com.medmen.bdd.pages.CreateAccountPage;
import com.medmen.bdd.pages.StorePage;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class AccountsStepDefs implements BaseTest {

  private WebDriver driver = DriverUtil.getDefaultDriver();

  @Given("^I navigate to the \"([^\"]*)\" create account page$")
  public void i_navigate_to_create_account_page(String env) {
    String url;
    if (env.toLowerCase().contains("stage")) {
      url = "https://menu-staging.medmen.com/register";
    } else {
      url = "https://menu.medmen.com/register";
    }
    navigationObj.navigateTo(url);
  }

  @Given("^I enter valid information in all required fields$")
  public void i_enter_valid_information_in_all_required_fields() {
    CreateAccountPage createAccountPage = new CreateAccountPage(driver);
    createAccountPage.enterEmailAddress("lucien.minot@medmen.com");
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

  @When("^I click the Create Account button$")
  public void i_click_the_Create_Account_button() {
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
    int expectedNum = itemNum;
    System.out.println("!!!!!!!!!");

    //System.out.println(driver.findElement(By.xpath("//div[@class='o-product-grid']//div[2]//div[1]//div[1]//div[2]//button[1]")).getText());
    ///html/body/div[1]/div/div[3]/div

    for (int i = 1; itemNum > 0; i++) {
      if (driver
          .findElement(
              By.xpath("//div[@class='o-product-grid']//div[" + i + "]//div[1]//div[1]//div[2]//button[1]"))
          .getText()
          .contains("Add")) {
        System.out.println(
            driver
                .findElement(
                    By.xpath(
                        "//div[@class='o-product-grid']//div[" + i + "]//div[1]//div[1]//div[2]//button[1]"))
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

    @Then("^I am taken to the Sign In page$")
    public void iAmTakenToTheSignInPage() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
