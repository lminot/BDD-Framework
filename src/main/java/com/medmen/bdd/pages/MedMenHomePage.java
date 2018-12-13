package com.medmen.bdd.pages;

import com.medmen.bdd.configs.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.medmen.bdd.helperMethods.BaseTest.navigationObj;

public class MedMenHomePage extends PageObject {

    @FindBy(xpath = "//*[@class='c-header__logo']")
    private WebElement logo;

    @FindBy(xpath = "//a[contains(text(),'Stores')]")
    private WebElement topNavStores;

    @FindBy(xpath = "//a[contains(text(),'Who We Are')]")
    private WebElement topNavWhoWeAre;

    @FindBy(xpath = "//a[contains(text(),'Investors')]")
    private WebElement topNavInvestors;

    @FindBy(xpath = "//a[contains(text(),'Blog')]")
    private WebElement topNavBlog;

    @FindBy(xpath = "//a[contains(text(),'[statemade]')]")
    private WebElement topNavStatemade;

    @FindBy(xpath = "//h3[@class='c-heading-38 u-color-red']")
    private WebElement footerNewsletterTitle;

    @FindBy(xpath = "//p[@class='c-heading-24 u-spacing-40']")
    private WebElement footerNewsletterInfo;

    @FindBy(xpath = "//input[@placeholder='Email Address']")
    private WebElement footerEmailInput;

    @FindBy(xpath = "//*[@id=\"newsletter\"]/div/div/form/div/button")
    private WebElement footerEmailSubmit;

    @FindBy(xpath = "//p[@class='c-signup__disclaimer']")
    private WebElement footerEmailDisclaimer;

    @FindBy(xpath = "//p[@class='js-newsletter__success u-text-center']")
    private WebElement footerEmailSignUpSuccess;

    @FindBy(xpath = "//input[@placeholder='Email Address']")
    private WebElement newsletterEmailTextBoxXpath;

    private String ageGateXpath = "//body/div[2]";
    private String newsletterSubmitXpath = "//h3[@class='c-heading-38 u-color-red']";
    private String newsletterSignUpSuccessTextXpath = "//p[@class='js-newsletter__success u-text-center']";

    private long timeoutInSeconds = 60;
    private WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), timeoutInSeconds);

    public MedMenHomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return logo.isDisplayed();
    }

    public void selectStatemade() {
        if (topNavStatemade.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(topNavStatemade));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ageGateXpath)));
            topNavStatemade.click();
        }
    }

    public void selectWhoWeAre() {
        if (topNavWhoWeAre.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(topNavWhoWeAre));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ageGateXpath)));
            topNavWhoWeAre.click();
        }
    }

    public void selectStores() {
        if (topNavStores.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(topNavStores));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(ageGateXpath)));
            topNavStores.click();
        }
    }

    public void enterNewsletterEmail(String email) {
        if (footerEmailInput.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(footerEmailInput));
            footerEmailInput.sendKeys(email);
        }
    }

    public void scrollToKeepInTouch() {
        navigationObj.scrollToElement("xpath", newsletterSubmitXpath);
        wait.until(ExpectedConditions.elementToBeClickable(newsletterEmailTextBoxXpath));
    }

    public void submitNewsletter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(newsletterSubmitXpath)));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newsletterSubmitXpath)));
        footerEmailSubmit.click();
    }

    public String getSuccessText() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newsletterSignUpSuccessTextXpath)));
        return footerEmailSignUpSuccess.getText();
    }
}
