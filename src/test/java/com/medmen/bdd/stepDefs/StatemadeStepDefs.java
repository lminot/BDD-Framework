package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.helperMethods.BaseTest;
import com.medmen.bdd.pages.statemade.EffectsPage;
import com.medmen.bdd.pages.statemade.ProductsPage;
import com.medmen.bdd.pages.statemade.StateMadeLandingPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatemadeStepDefs implements BaseTest {

  private WebDriver driver = DriverConfig.getDriver();
  private StateMadeLandingPage stateMadePage = new StateMadeLandingPage(driver);
  private EffectsPage effectsPage = new EffectsPage(driver);

  @Then("^I am taken to the statemade landing page$")
  public void i_am_taken_to_the_statemade_landing_page() {
    assertTrue(stateMadePage.isInitialized());
  }

  @Then("^the page has valid buttons")
  public void the_page_has_valid_links() {
    assertTrue(stateMadePage.linksArePresent());
  }

  @Then("^the page is displayed correctly$")
  public void the_page_is_displayed_correctly() {
    assertEquals(
        "cannabis made in your state—for your desired state.", stateMadePage.getTitleText());
  }

  @When("^I am on the statemade page$")
  public void i_am_on_the_statemade_page() {
    navigationObj.navigateTo("https://medmen:AXPqt3EURBVBGATb@staging.medmen.com/statemade/");
  }

  @When("^I select the effect button$")
  public void i_select_the_effect_button() {
    navigationObj.navigateTo(
        "https://medmen:AXPqt3EURBVBGATb@staging.medmen.com/statemade/effects/");
//todo fix this
//     stateMadePage.clickEffectButton();

  }

  @When("^I am taken to the effects page$")
  public void i_am_taken_to_the_effects_page() {
    //EffectsPage effectsPage = new EffectsPage(driver);
    assertTrue(effectsPage.isInitialized());
  }

  @Then("^all the various effects are displayed$")
  public void all_the_various_effects_are_displayed() {
    //EffectsPage effectsPage = new EffectsPage(driver);
    assertTrue(effectsPage.linksArePresent());
  }

  @When("^I select the product type button$")
  public void i_select_the_product_type_button() throws Throwable {
    navigationObj.navigateTo(
        "https://medmen:AXPqt3EURBVBGATb@staging.medmen.com/statemade/products/");
  }

  @When("^I am taken to the product type  page$")
  public void i_am_taken_to_the_product_type_page() throws Throwable {
    ProductsPage productsPage = new ProductsPage(driver);

    assertEquals(
        "Our expertly designed, industry-leading products are grown in your state, for your desired state. enjoy the  [statemade]  effect you want, the way you want it.",
        productsPage.getProductsDescText());
  }

  @Then("^all the various product types are displayed$")
  public void all_the_various_product_types_are_displayed() throws Throwable {
    ProductsPage productsPage = new ProductsPage(driver);
    assertTrue(productsPage.productsArePresent());
  }

  @And("^I select the \"([^\"]*)\" product$")
  public void iSelectTheProduct(String productType) throws Throwable {
    if(productType.equals("Max")){
      effectsPage.selectMax();
    }
  }

  @Then("^the proper \"([^\"]*)\" page is displayed$")
  public void theProperPageIsDisplayed(String productType) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }
}
