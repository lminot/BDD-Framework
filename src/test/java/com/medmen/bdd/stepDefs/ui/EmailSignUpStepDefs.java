package com.medmen.bdd.stepDefs.ui;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.configs.EnvironmentConfig;
import com.medmen.bdd.pages.AgeGatePage;
import com.medmen.bdd.pages.ExitPop;
import com.medmen.bdd.pages.MedMenHomePage;
import com.medmen.bdd.pages.StoreListsPage;
import com.medmen.bdd.pages.menuSite.MenuStorePage;
import com.medmen.bdd.pages.statemade.ProductsPage;
import com.medmen.bdd.pages.statemade.StateMadeLandingPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.medmen.bdd.helperMethods.BaseTest.navigationObj;
import static org.junit.Assert.assertEquals;

public class EmailSignUpStepDefs {

    private AgeGatePage ageGatePage;
    private ExitPop exitPop;
    private MedMenHomePage medMenHomePage;
    private StateMadeLandingPage stateMadeLandingPage;
    private ProductsPage productsPage;
    private StoreListsPage storeListsPage;
    private MenuStorePage selectedStorePage;
    private WebDriver driver;

    EnvironmentConfig environmentConfig;

    @When("^I enter a valid email address into the \"([^\"]*)\" sign up box$")
    public void i_enter_a_valid_email_address_into_the_sign_up_box(String newsletterKey) {
        driver = DriverConfig.getDriver();
        ageGatePage = new AgeGatePage(driver);
        exitPop = new ExitPop(driver);
        medMenHomePage = new MedMenHomePage(driver);
        productsPage = new ProductsPage(driver);
        environmentConfig = new EnvironmentConfig();

        if (newsletterKey.contains("Age Gate Newsletter")) {
            ageGatePage.enterEmailAddress(environmentConfig.getEmailAddress());
        } else if (newsletterKey.contains("Keep in Touch")) {
            medMenHomePage.scrollToKeepInTouch();
            medMenHomePage.enterNewsletterEmail(environmentConfig.getEmailAddress());
        } else if (newsletterKey.contains("Get 10% Off Your First Purchase.")) {
            exitPop.enterEmailIntoExitPop(environmentConfig.getEmailAddress());
        } else if (newsletterKey.contains("Coming soon to:")) {
            productsPage.enterStatemadeEmailSignUp(environmentConfig.getEmailAddress());
        }
    }

    @Given("^I select the Who We Are top nav link$")
    public void i_select_the_Who_We_Are_top_nav_link() {
        medMenHomePage = new MedMenHomePage(DriverConfig.getDriver());
        medMenHomePage.selectWhoWeAre();
    }

    @When("^I trigger the exit pop overlay$")
    public void i_trigger_the_exit_pop_overlay() throws Throwable {
        triggerExitPop();
    }

    @When("^I click the exit-pop email submit button$")
    public void i_click_the_exit_pop_email_submit_button() {
        exitPop = new ExitPop(DriverConfig.getDriver());
        exitPop.sumbitExitPop();
    }

    @Then("^the exit-pop \"([^\"]*)\" text is displayed$")
    public void the_exit_pop_text_is_displayed(String exitPopConfirmationText) {
        exitPop = new ExitPop(DriverConfig.getDriver());
        assertEquals(exitPopConfirmationText, exitPop.getTankYouText());
    }

    @Then("^the \"([^\"]*)\" text is displayed$")
    public void the_text_is_displayed(String confirmationText) {
        medMenHomePage = new MedMenHomePage(DriverConfig.getDriver());
        if (confirmationText.contains("Thank you")) {
            assertEquals(confirmationText, medMenHomePage.getSuccessText());
        }
    }

    @When("^I click the newsletter email submit button$")
    public void i_click_the_submit_button() {
        medMenHomePage = new MedMenHomePage(DriverConfig.getDriver());
        medMenHomePage.submitNewsletter();
    }

    @Given("^I navigate to the statemade menu page$")
    public void i_navigate_to_the_statemade_menu_page() {
        medMenHomePage = new MedMenHomePage(DriverConfig.getDriver());
        medMenHomePage.selectStatemade();
    }

    @When("^I select a product$")
    public void i_select_a_product() {
        driver = DriverConfig.getDriver();
        stateMadeLandingPage = new StateMadeLandingPage(driver);
        productsPage = new ProductsPage(driver);

        stateMadeLandingPage.clickProductTypeButton();
        productsPage.selectPens();
    }

    @When("^I select the \"([^\"]*)\" link$")
    public void i_select_the_link(String linkText) {
        productsPage = new ProductsPage(DriverConfig.getDriver());
        productsPage.selectPensFindYourStoreLink();
    }

    @When("^I click the statemade newsletter email submit button$")
    public void i_click_the_statemade_newsletter_email_submit_button() {
        productsPage = new ProductsPage(DriverConfig.getDriver());
        productsPage.selectStatemadeSubmitButton();
    }

    @Then("^the statemade \"([^\"]*)\" text is displayed$")
    public void the_statemade_text_is_displayed(String expectedThankYouText) {
        productsPage = new ProductsPage(DriverConfig.getDriver());
        assertEquals(expectedThankYouText, productsPage.getStatemadeEmailSignUpThankYouText());
    }

    @Given("^I navigate to the stores page$")
    public void i_navigate_to_the_stores_page() {
        medMenHomePage = new MedMenHomePage(DriverConfig.getDriver());
        medMenHomePage.selectStores();
    }

    @Given("^I select a store with a menu$")
    public void i_select_a_store_with_a_menu() {
        storeListsPage = new StoreListsPage(DriverConfig.getDriver());
        storeListsPage.selectKearnyMesaStore();
    }

    @Given("^I select the \"([^\"]*)\" button$")
    public void i_select_the_button(String shopNowButtonText) {
        storeListsPage = new StoreListsPage(DriverConfig.getDriver());
        storeListsPage.selectShopNow();
    }

    @Given("^I scroll down the page$")
    public void i_scroll_down_the_page() throws Throwable {
        navigationObj.scrollPage(
                "down");
    }

    @When("^I enter a valid email address into the menu \"([^\"]*)\" sign up box$")
    public void i_enter_a_valid_email_address_into_the_menu_sign_up_box(String keepInTouchTitleText) {
        selectedStorePage = new MenuStorePage(DriverConfig.getDriver());
        environmentConfig = new EnvironmentConfig();
        selectedStorePage.enterFooterEmail(environmentConfig.getEmailAddress());
    }

    @When("^I click the menu site newsletter email submit button$")
    public void i_click_the_menu_site_newsletter_email_submit_button() {
        selectedStorePage = new MenuStorePage(DriverConfig.getDriver());
        selectedStorePage.submitFooterNewsletter();
    }

    @Then("^the menu site \"([^\"]*)\" text is displayed$")
    public void the_menu_site_text_is_displayed(String expectedSuccessText) {
        selectedStorePage = new MenuStorePage(DriverConfig.getDriver());
        assertEquals(expectedSuccessText, selectedStorePage.getSuccessText());
    }

    private void triggerExitPop() throws Exception {
        Thread.sleep(1750);
        navigationObj.scrollPage(
                "down");
        Thread.sleep(1750);
        navigationObj.scrollPage(
                "up");
        Thread.sleep(1750);
        navigationObj.scrollPage(
                "down");
        Thread.sleep(1750);
        navigationObj.scrollPage(
                "up");
    }
}
