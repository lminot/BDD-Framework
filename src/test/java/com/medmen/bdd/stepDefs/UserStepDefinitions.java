package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfigs;
import com.medmen.bdd.pages.Cart;
import com.medmen.bdd.pages.MedMenHomePageOverlay;
import com.medmen.bdd.pages.StorePage;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.medmen.bdd.helperMethods.BaseTest;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class UserStepDefinitions implements BaseTest {

  private WebDriver driver = DriverConfigs.getDriver();

  @Given("^I click \"([^\"]*)\" on  the age verification prompt$")
  public void i_click_on_the_age_verification_prompt(String ageCheck) {
    MedMenHomePageOverlay medmenHomePageOverlay = new MedMenHomePageOverlay(driver);
    assertTrue(medmenHomePageOverlay.isInitialized());
    if (ageCheck.contains("YES")) {
      medmenHomePageOverlay.selectYesImTwentyOne();
    } else {
      medmenHomePageOverlay.selectNoImTwentyOne();
    }
  }

  @Given("^I click the enter button$")
  public void i_click_the_enter_button() {
    MedMenHomePageOverlay medmenHomePageOverlay = new MedMenHomePageOverlay(driver);
    assertTrue(medmenHomePageOverlay.isInitialized());
    medmenHomePageOverlay.clickEnter();
  }

  @When("^I select \"([^\"]*)\" items to add to cart$")
  public void i_select_items_to_add_to_cart(int itemNum) {
    StorePage storePage = new StorePage(driver);
    assertTrue(storePage.isInitialized());
    for (int i = 0; i < itemNum; i++) {
      clickObj.clickForcefully(
          "xpath",
          "/html[1]/body[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/button[1]");
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
    public void iAmTakenToTheSignInPage() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

  @After
  public final void tearDown() {
    DriverConfigs.closeDriver();
  }


}
