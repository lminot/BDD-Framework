package com.medmen.bdd.stepDefs.ui;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.AgeGatePage;
import com.medmen.bdd.pages.ExitPop;
import com.medmen.bdd.pages.MedMenHomePage;
import com.medmen.bdd.utils.FileLoaderUtils;
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
    private WebDriver driver;
    private static String email;
    private static String environment;

    @When("^I enter a valid email address into the \"([^\"]*)\" sign up box$")
    public void i_enter_a_valid_email_address_into_the_sign_up_box(String newsletterKey) {
        driver = DriverConfig.getDriver();
        ageGatePage = new AgeGatePage(driver);
        exitPop = new ExitPop(driver);
        medMenHomePage = new MedMenHomePage(driver);

        FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
        environment = System.getProperty("env", "stage");
        if (environment.toLowerCase().contains("localhost")) {
            email = fileLoaderUtils.getValueFromPropertyFile("local.properties", "email");
        } else if (environment.toLowerCase().contains("stage")) {
            email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "email");
        } else if (environment.toLowerCase().contains("prod")) {
            email = fileLoaderUtils.getValueFromPropertyFile("prod.properties", "email");
        }

        if (newsletterKey.contains("Age Gate Newsletter")) {
            ageGatePage.enterEmailAddress(email);
        } else if (newsletterKey.contains("Keep in Touch")) {
            medMenHomePage.scrollToKeepInTouch();
            medMenHomePage.enterNewsletterEmail(email);
        } else if (newsletterKey.contains("Get 10% Off Your First Purchase.")) {
            exitPop.enterEmailIntoExitPop(email);
        }
    }

    @Given("^I select the Who We Are top nav link$")
    public void i_select_the_Who_We_Are_top_nav_link() throws InterruptedException {
        medMenHomePage = new MedMenHomePage(DriverConfig.getDriver());
        Thread.sleep(500); //todo fix this sleep
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
        medMenHomePage.submitNewsletter();
    }

    private void triggerExitPop() throws Exception {
        Thread.sleep(2000);
        navigationObj.scrollPage(
                "down");
        Thread.sleep(2000);
        navigationObj.scrollPage(
                "up");
        Thread.sleep(2000);
        navigationObj.scrollPage(
                "down");
        Thread.sleep(2000);
        navigationObj.scrollPage(
                "up");
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
