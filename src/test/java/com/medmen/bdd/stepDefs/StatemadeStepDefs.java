package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.helperMethods.BaseTest;
import com.medmen.bdd.pages.statemade.EffectsPage;
import com.medmen.bdd.pages.statemade.ProductsPage;
import com.medmen.bdd.pages.statemade.StateMadeLandingPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatemadeStepDefs implements BaseTest {

  private WebDriver driver = DriverConfig.getDriver();
  private StateMadeLandingPage stateMadePage = new StateMadeLandingPage(driver);
  private EffectsPage effectsPage = new EffectsPage(driver);
  private String baseUrl = CommonStepDefs.getBaseUrl();
  private List<String> effectsList = Arrays.asList("max", "joy", "zen", "ebb", "zzz", "one", "cbd");
  private List<String> productsList = Arrays.asList("all", "pens", "drops", "flower", "prerolls");

  @Before
  public void clearCookies() {
    driver.manage().deleteAllCookies();
  }

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
    navigationObj.navigateTo(baseUrl + "/statemade/");
  }

  @When("^I select the effect button$")
  public void i_select_the_effect_button() {
    // todo fix this for stage auth
    stateMadePage.clickEffectButton();
  }

  @When("^I am taken to the effects page$")
  public void i_am_taken_to_the_effects_page() {
    assertTrue(effectsPage.isInitialized());
  }

  @Then("^all the various effects are displayed$")
  public void all_the_various_effects_are_displayed() {
    assertTrue(effectsPage.linksArePresent());
  }

  @When("^I select the product type button$")
  public void i_select_the_product_type_button() {
    // todo fix this for stage auth
    stateMadePage.clickProductTypeButton();
  }

  @When("^I am taken to the product type page$")
  public void i_am_taken_to_the_product_type_page() {
    ProductsPage productsPage = new ProductsPage(driver);
    assertTrue(productsPage.isInitialized());
    System.out.println("check me !!!" + productsPage.getProductsDescText());

    // todo investigate if the strange text below is a bug
    assertEquals(
        "Our expertly designed, industry\u00AD-leading products"
            + " are grown in your state, for your desired state. enjoy the  "
            + "[statemade]  effect you want, the way you want it.",
        productsPage.getProductsDescText());
  }

  @Then("^all the various product types are displayed$")
  public void all_the_various_product_types_are_displayed() {
    ProductsPage productsPage = new ProductsPage(driver);
    assertTrue(productsPage.productsPhotosArePresent());
    assertTrue(productsPage.productsArePresent());
  }

  @When("^I am on the statemade menu page$")
  public void i_am_on_the_statemade_menu_page() {
    navigationObj.navigateTo(baseUrl + "/statemade/menu/");
  }

  @Then("^the bottom nav is fully functional$")
  public void the_bottom_nav_is_fully_functional() {
    for (String effect : effectsList) {
      String effectXpathLoc =
          "//*[@id=\"hero\"]/div[33]//a[contains(@data-effect, '" + effect + "')]";
      assertTrue(assertionObj.isElementDisplayed("xpath", effectXpathLoc));
      clickObj.click("xpath", effectXpathLoc);

      for (String product : productsList) {
        String productXpathLoc =
            "//*[@id=\"hero\"]/div[32]//a[contains(@data-product, '" + product + "')]";
        assertTrue(assertionObj.isElementDisplayed("xpath", productXpathLoc));
        clickObj.click("xpath", productXpathLoc);
      }
    }
  }

  @Then("^each effect description is properly displayed$")
  public void each_effect_description_is_properly_displayed() {

    String productHeaderText =
        "//*[@id=\"hero\"]//*[contains(@class, 'statemade-products-all-%s')]/div[@class='statemade-products-all-header']";

    String productSubHeaderText =
        "//*[@id=\"hero\"]//*[contains(@class, 'statemade-products-all-%s')]/div[@class='statemade-products-all-subheader']";

    String productNotesText =
        "//*[@id=\"hero\"]//*[contains(@class, 'statemade-products-all-%s')]//div[@class='statemade-products-note']";

    for (String effect : effectsList) {
      if (effect.equals("max")) {

        checkEffectType(productHeaderText, effect);
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productSubHeaderText, effect)),
            "made for activity");
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productNotesText, effect)),
            "[statemade] max brings creative energy to everyday experiences. enjoy a euphoric yet carefree state.\n"
                + "taste + smell: sweet with notes of citrus and pine");
      } else if (effect.equals("joy")) {

        checkEffectType(productHeaderText, effect);
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productSubHeaderText, effect)),
            "made for happiness");
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productNotesText, effect)),
            "[statemade] joy is positively uplifting. rise higher until you reach your desired state of bliss.\n"
                + "taste + smell: sweet with notes of berry and cloves");

      } else if (effect.equals("zen")) {

        checkEffectType(productHeaderText, effect);
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productSubHeaderText, effect)),
            "made for enlightenment");
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productNotesText, effect)),
            "[statemade] zen will bring you the peace you seek. enter a higher state of consciousness.\n"
                + "taste + smell: earthy with notes of vanilla and moss");

      } else if (effect.equals("ebb")) {

        checkEffectType(productHeaderText, effect);
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productSubHeaderText, effect)),
            "made for fluidity");
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productNotesText, effect)),
            "[statemade] ebb will take you from rushed to relaxed. set a comfortable pace for your current state.\n"
                + "taste + smell: earthy with notes of eucalyptus and mint");

      } else if (effect.equals("zzz")) {

        checkEffectType(productHeaderText, effect);
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productSubHeaderText, effect)),
            "made for rest");
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productNotesText, effect)),
            "[statemade] zzz is your ticket to dreamland and the solid sleep you desire. ease into a state of slumber.\n"
                + "taste + smell: earthy with notes of hash and cocoa");

      } else if (effect.equals("one")) {

        checkEffectType(productHeaderText, effect);
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productSubHeaderText, effect)),
            "made for balance");
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productNotesText, effect)),
            "[statemade] one is a harmonious 1:1 ratio of THC and CBD—giving you the best of both worlds.");

      } else if (effect.equals("cbd")) {

        checkEffectType(productHeaderText, effect);

        assertEquals(
            assertionObj.getElementText("xpath", String.format(productSubHeaderText, effect)),
            "made for wellness");
        assertEquals(
            assertionObj.getElementText("xpath", String.format(productNotesText, effect)),
            "[statemade] cbd is for those who seek relief without the high. this effect is designed to ease a variety of conditions—from pain to anxiety.");
      }
    }
  }

  private void checkEffectType(String productHeaderText, String effect) {
    productHeaderText = String.format(productHeaderText, effect);
    String effectXpathLoc =
        "//*[@id=\"hero\"]/div[33]//a[contains(@data-effect, '" + effect + "')]";
    assertTrue(assertionObj.isElementDisplayed("xpath", effectXpathLoc));
    clickObj.click("xpath", effectXpathLoc);
    assertEquals(assertionObj.getElementText("xpath", productHeaderText), effect);
  }
}
