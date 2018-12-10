package com.medmen.bdd.stepDefs.ui;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.AgeGatePage;
import com.medmen.bdd.pages.ExitPop;
import com.medmen.bdd.pages.MedMenHomePage;
import com.medmen.bdd.utils.FileLoaderUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.medmen.bdd.helperMethods.BaseTest.navigationObj;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmailSignUpStepDefs {

    private AgeGatePage ageGatePage;
    private ExitPop exitPop;
    private MedMenHomePage medMenHomePage;
    private WebDriver driver;
    private static String email;
    private static String environment;
    private long timeoutInSeconds = 5000;
    private String keepInTouchTitleXpath = "//h3[@class='c-heading-38 u-color-red']";

    private WebDriverWait wait;

    @When("^I enter a valid email address into the \"([^\"]*)\" sign up box$")
    public void i_enter_a_valid_email_address_into_the_sign_up_box(String newsletterKey) throws InterruptedException {
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
        } else if (newsletterKey.contains("Get 10% Off Your First Purchase.")) {
            //todo exit pop flow

        } else if (newsletterKey.contains("Keep in Touch")) {
            wait = new WebDriverWait(driver, timeoutInSeconds);

            String newsletterEmailTextBoxXpath = "//input[@placeholder='Email Address']";
            navigationObj.scrollToElement("xpath", keepInTouchTitleXpath);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newsletterEmailTextBoxXpath)));
            medMenHomePage.enterNewsletterEmail(email);
        }
    }

    @When("^I scroll down the page$")
    public void i_scroll_down_the_page() throws Throwable {
        //todo find a way to trigger exit pop
//    navigationObj.scrollPage(
//            "down");

        navigationObj.scrollToElement("xpath", keepInTouchTitleXpath);
    }

    @Then("^the \"([^\"]*)\" text is displayed$")
    public void the_text_is_displayed(String confirmationText) {
        String newsletterSignUpSuccessTextXpath = "//p[@class='js-newsletter__success u-text-center']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newsletterSignUpSuccessTextXpath)));
        if (confirmationText.contains("Thank you")) {
            assertEquals(confirmationText, medMenHomePage.getSuccessText());
        }
    }

    @When("^I click the newsletter email submit button$")
    public void i_click_the_submit_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(keepInTouchTitleXpath)));
        medMenHomePage.submitNewsletter();
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
