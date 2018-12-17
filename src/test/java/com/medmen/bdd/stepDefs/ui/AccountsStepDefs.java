package com.medmen.bdd.stepDefs.ui;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.helperMethods.BaseTest;
import com.medmen.bdd.pages.Cart;
import com.medmen.bdd.pages.CreateAccountPage;
import com.medmen.bdd.pages.StorePage;
import com.medmen.bdd.pages.menuSite.MenuStorePage;
import com.medmen.bdd.utils.FileLoaderUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class AccountsStepDefs implements BaseTest {

    private CreateAccountPage createAccountPage;
    private MenuStorePage menuStorePage;
    private StorePage storePage;
    private Cart cart;

    private FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
    private ArrayList<String> storeList = new ArrayList<>();

    private String menuBaseUrl;

    @Given("^I navigate to the create account page$")
    public void i_navigate_to_the_create_account_page() {
        menuBaseUrl = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "medmen.menu.ui.url");
        navigationObj.navigateTo(menuBaseUrl + "/register");
    }

    @Given("^I enter valid information in all required fields$")
    public void i_enter_valid_information_in_all_required_fields() {
        createAccountPage = new CreateAccountPage(DriverConfig.getDriver());

        String email = fileLoaderUtils.getValueFromPropertyFile("stage.properties", "email.dynamic");

        int storeIndex = 1;

        createAccountPage.enterEmailAddress(String.format(email, String.valueOf(System.currentTimeMillis())));
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
        createAccountPage.selectPerfStore(storeIndex);
        createAccountPage.checkDataPolicy();
        createAccountPage.checkTermsOfService();
    }

    @When("^I click the create account button$")
    public void i_click_the_create_account_button() throws InterruptedException {
        createAccountPage = new CreateAccountPage(DriverConfig.getDriver());
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

    @Then("^I am directed to my store page$")
    public void i_am_directed_to_my_store_page() {
        WebDriver driver = DriverConfig.getDriver();
        menuStorePage = new MenuStorePage(driver);
        Assert.assertEquals(menuStorePage.getStoreName(), getStore(0));
    }

    @Then("^I have a logged in account$")
    public void i_have_a_logged_in_account() {
        WebDriver driver = DriverConfig.getDriver();
        menuStorePage = new MenuStorePage(driver);
        Assert.assertEquals(menuStorePage.getUsersAccoutName().trim(), "Hi, Testing");
    }

    @When("^I select \"([^\"]*)\" items to add to cart$")
    public void i_select_items_to_add_to_cart(int itemNum) {
        WebDriver driver = DriverConfig.getDriver();
        storePage = new StorePage(driver);

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
        cart = new Cart(DriverConfig.getDriver());
        assertTrue(cart.isInitialized());
        cart.clickCheckout();
    }

    public String getStore(int storeIndex) {
        storeList.add("Downtown Vegas (Arts District)");
        storeList.add("The Strip (Paradise & Harmon)");
        storeList.add("Beverly Hills");
        storeList.add("Downtown (DTLA)");
        storeList.add("LAX Airport");
        storeList.add("West Hollywood");
        storeList.add("Santa Ana");
        storeList.add("Kearny Mesa");
        storeList.add("Venice Beach Abbot Kinney");
        storeList.add("Lincoln Blvd");

        return storeList.get(storeIndex);
    }
}
