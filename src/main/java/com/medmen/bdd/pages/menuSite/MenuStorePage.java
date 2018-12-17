package com.medmen.bdd.pages.menuSite;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MenuStorePage extends PageObject {

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/h1[1]/div[1]/a[1]/div[1]")
    private WebElement storeName;

    @FindBy(xpath = "//div[@class='u-font-italic c-store-info-OPNHRS']")
    private WebElement storeHours;

    @FindBy(xpath = "//div[@class='c-header__account']//button[@type='button']")
    private WebElement userAccountName;

    @FindBy(xpath = "//h3[@class='c-heading-30 u-color-red']")
    private WebElement footerEmailSignUpTitle;

    @FindBy(xpath = "//input[@placeholder='Email Address']")
    private WebElement footerEmailTextBox;

    @FindBy(xpath = "//button[@class='c-signup__submit']")
    private WebElement footerEmailSubmit;

    @FindBy(xpath = "//p[@class='u-text-center']")
    private WebElement footerEmailSubmitThankYouText;

    private String menuSiteNewsletterSubmitXpath = "//h3[@class='c-heading-30 u-color-red']";
    private String newsletterSignUpSuccessTextXpath = "//p[@class='u-text-center']";


    private long timeoutInSeconds = 60;
    private WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), timeoutInSeconds);

    public MenuStorePage(WebDriver driver) {
        super(driver);
    }

    public void enterFooterEmail(String email) {
        if (footerEmailTextBox.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(footerEmailTextBox));
            footerEmailTextBox.sendKeys(email);
        }
    }

    public String getStoreName(){
        wait.until(ExpectedConditions.visibilityOf(storeName));
        return storeName.getText();
    }

    public String getUsersAccoutName(){
        wait.until(ExpectedConditions.visibilityOf(userAccountName));
        return userAccountName.getText();
    }

    public void submitFooterNewsletter() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menuSiteNewsletterSubmitXpath)));
        footerEmailSubmit.click();
    }

    public String getSuccessText() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newsletterSignUpSuccessTextXpath)));
        return footerEmailSubmitThankYouText.getText();
    }
}
