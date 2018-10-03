package com.medmen.bdd.stepDefs;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.MedMenHomePage;
import com.medmen.bdd.pages.MedMenHomePageOverlay;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.medmen.bdd.helperMethods.BaseTest.navigationObj;
import static org.junit.Assert.assertTrue;

public class CommonStepDefs {

    private WebDriver driver = DriverConfig.getDriver();


    @Given("^I navigate to the \"([^\"]*)\" homepage$")
    public void i_navigate_to_homepage(String env) {
        String url;
        if (env.toLowerCase().contains("stage")) {
            url = "https://medmen:AXPqt3EURBVBGATb@staging.medmen.com";
        } else {
            url = "https://medmen.com";
        }
        navigationObj.navigateTo(url);
    }

    @Given("^I click \"([^\"]*)\" on the age verification prompt$")
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

    @When("^I select the statemade option from the top nav$")
    public void i_select_the_statemade_option_from_the_top_nav() {
        //todo fix me for teh basic auth
//        MedMenHomePage medMenHomePage = new MedMenHomePage(driver);
//        assertTrue(medMenHomePage.isInitialized());
//        medMenHomePage.selectStatemade();
        navigationObj.navigateTo("https://medmen:AXPqt3EURBVBGATb@staging.medmen.com/statemade/");
    }
}
