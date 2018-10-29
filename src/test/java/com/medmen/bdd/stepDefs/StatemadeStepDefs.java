package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.helperMethods.BaseTest;
import com.medmen.bdd.pages.statemade.EffectsPage;
import com.medmen.bdd.pages.statemade.ProductsPage;
import com.medmen.bdd.pages.statemade.StateMadeLandingPage;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatemadeStepDefs implements BaseTest {

  private WebDriver driver;
  private StateMadeLandingPage stateMadePage;
  private EffectsPage effectsPage;
  private List<String> effectsList = Arrays.asList("max", "joy", "zen", "ebb", "zzz", "one", "cbd");
  private List<String> productsList = Arrays.asList("all", "pens", "drops", "flower", "prerolls");

  @Then("^I am taken to the statemade landing page$")
  public void i_am_taken_to_the_statemade_landing_page() {
    driver = DriverConfig.getDriver();
    stateMadePage = new StateMadeLandingPage(driver);
    assertTrue(stateMadePage.isInitialized());
  }

  @Then("^the page has valid buttons")
  public void the_page_has_valid_links() {
    driver = DriverConfig.getDriver();
    stateMadePage = new StateMadeLandingPage(driver);
    assertTrue(stateMadePage.linksArePresent());
  }

  @Then("^the page is displayed correctly$")
  public void the_page_is_displayed_correctly() {
    driver = DriverConfig.getDriver();
    stateMadePage = new StateMadeLandingPage(driver);
    assertEquals(
        "cannabis made in your state—for your desired state.", stateMadePage.getTitleText());
  }

  @When("^I am on the statemade page$")
  public void i_am_on_the_statemade_page() {
    navigationObj.navigateTo(CommonStepDefs.getBaseUrl() + "/statemade/");
  }

  @When("^I select the effect button$")
  public void i_select_the_effect_button() {
    driver = DriverConfig.getDriver();
    stateMadePage = new StateMadeLandingPage(driver);
    // todo fix this for stage auth
    stateMadePage.clickEffectButton();
  }

  @When("^I am taken to the effects page$")
  public void i_am_taken_to_the_effects_page() {
    driver = DriverConfig.getDriver();
    effectsPage = new EffectsPage(driver);
    assertTrue(effectsPage.isInitialized());
  }

  @Then("^all the various effects are displayed$")
  public void all_the_various_effects_are_displayed() {
    driver = DriverConfig.getDriver();
    effectsPage = new EffectsPage(driver);
    assertTrue(effectsPage.linksArePresent());
  }

  @When("^I select the product type button$")
  public void i_select_the_product_type_button() {
    // todo fix this for stage auth
    driver = DriverConfig.getDriver();
    stateMadePage = new StateMadeLandingPage(driver);
    stateMadePage.clickProductTypeButton();
  }

  @When("^I am taken to the product type page$")
  public void i_am_taken_to_the_product_type_page() {
    ProductsPage productsPage = new ProductsPage(driver);
    assertTrue(productsPage.isInitialized());
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
    navigationObj.navigateTo(CommonStepDefs.getBaseUrl() + "/statemade/menu/");
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

  @Then("^the all products effect description is properly displayed$")
  public void the_all_products_effect_description_is_properly_displayed() {

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

  @When("^I select the \"([^\"]*)\" product$")
  public void i_select_the_product(String productType) {
    String productXpathLoc = "//*[@id=\"hero\"]/div[32]//a[contains(@data-product, '%s')]";

    if (productType.equals("pens")) {
      assertTrue(
          assertionObj.isElementDisplayed("xpath", String.format(productXpathLoc, productType)));
      clickObj.click("xpath", String.format(productXpathLoc, productType));
    } else if (productType.equals("drops")) {
      assertTrue(
          assertionObj.isElementDisplayed("xpath", String.format(productXpathLoc, productType)));
      clickObj.click("xpath", String.format(productXpathLoc, productType));

    } else if (productType.equals("flowers")) {
      assertTrue(
          assertionObj.isElementDisplayed("xpath", String.format(productXpathLoc, productType)));
      clickObj.click("xpath", String.format(productXpathLoc, productType));

    } else if (productType.equals("pre-rolls")) {
      assertTrue(
          assertionObj.isElementDisplayed("xpath", String.format(productXpathLoc, productType)));
      clickObj.click("xpath", String.format(productXpathLoc, productType));
    }
  }

  @And("^I select each effect and verify their descriptions$")
  public void i_select_each_effect_and_verify_their_descriptions() {
    for (String effect : effectsList) {
      String effectXpathLoc =
          "//*[@id=\"hero\"]/div[33]//a[contains(@data-effect, '" + effect + "')]";

      assertTrue(assertionObj.isElementDisplayed("xpath", effectXpathLoc));
      clickObj.click("xpath", effectXpathLoc);
      checkPenDescription(effect);
    }
  }

  private void checkPenDescription(String effect) {
    String penEffectName =
        "//div[@class='statemade-products-wrapper products-type-default products-%s-pens toggle-js']//h1[contains(text(),'%s')]";
    String vaporizerPen =
        "//div[@class='statemade-products-wrapper products-type-default products-%s-pens toggle-js']//h4[contains(text(),'vaporizer pen')]";
    String penInfo1 =
        "//div[@class='statemade-products-wrapper products-type-default products-%s-pens toggle-js']//div//div[@class='products-info']";
    String penInfo2 =
        "//div[@class='statemade-products-wrapper products-type-default products-"
            + effect
            + "-pens toggle-js']//div//div[@class='products-info'][2]";

    assertTrue(
        assertionObj.isElementDisplayed("xpath", String.format(penEffectName, effect, effect)));
    assertEquals(
        assertionObj.getElementText("xpath", String.format(penEffectName, effect, effect)), effect);

    assertTrue(assertionObj.isElementDisplayed("xpath", String.format(vaporizerPen, effect)));
    assertEquals(
        assertionObj.getElementText("xpath", String.format(vaporizerPen, effect)), "vaporizer pen");

    assertEquals(
        assertionObj.getElementText("xpath", String.format(penInfo1, effect)),
        "[statemade] "
            + effect
            + " pens are the perfect combination of innovation and design. each vaporizer pen contains a state\u00AD-of-\u00ADthe-art battery designed to last the life of the pen and a modern exterior metal casing worthy of display.");
    assertEquals(
        assertionObj.getElementText("xpath", String.format(penInfo2, effect)),
        "+ calibrated for "
            + effect
            + "\n"
            + "+ square end prevents roll-away\n"
            + "+ light ports activate upon inhale\n"
            + "+ reservoir displays fill level");
  }

  private void checkEffectType(String productHeaderText, String effect) {
    productHeaderText = String.format(productHeaderText, effect);
    String effectXpathLoc =
        "//*[@id=\"hero\"]/div[33]//a[contains(@data-effect, '" + effect + "')]";
    assertTrue(assertionObj.isElementDisplayed("xpath", effectXpathLoc));
    clickObj.click("xpath", effectXpathLoc);
    assertEquals(assertionObj.getElementText("xpath", productHeaderText), effect);
  }

  @And("^I navigate through the various effects$")
  public void iNavigateThroughTheVariousEffects() {
    // this is a pass through step
  }
}
