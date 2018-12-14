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

    @FindBy(xpath = "//h1[@class='c-store-info-desktop u-spacing-none u-spacing-20-less-than-sm']//div[@class='u-font-bold'][contains(text(),'Beverly Hills')]")
    private WebElement storeName;

    @FindBy(xpath = "//div[@class='u-font-italic c-store-info-OPNHRS']")
    private WebElement storeHours;

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

    public void submitFooterNewsletter() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menuSiteNewsletterSubmitXpath)));
        footerEmailSubmit.click();
    }

    public String getSuccessText() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(newsletterSignUpSuccessTextXpath)));
        return footerEmailSubmitThankYouText.getText();
    }
}
