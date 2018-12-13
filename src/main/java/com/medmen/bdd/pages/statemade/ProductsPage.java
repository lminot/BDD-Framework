package com.medmen.bdd.pages.statemade;

import com.medmen.bdd.configs.DriverConfig;
import com.medmen.bdd.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends PageObject {

    @FindBy(xpath = "//div[@class='statemade-products-header']")
    private WebElement productsTitleTxt;

    @FindBy(xpath = "//div[@class='statemade-products-note']")
    private WebElement productsDescTxt;

    @FindBy(xpath = "//img[@class='statemade-products-pens']")
    private WebElement pensPhoto;

    @FindBy(xpath = "//div[contains(text(),'pens')]")
    private WebElement pensBtn;

    @FindBy(xpath = "//img[@class='statemade-products-drops']")
    private WebElement dropsPhoto;

    @FindBy(xpath = "//div[contains(text(),'drops')]")
    private WebElement dropsBtn;

    @FindBy(xpath = "//img[@class='statemade-products-flower']")
    private WebElement flowerPhoto;

    @FindBy(xpath = "//div[contains(text(),'flower')]")
    private WebElement flowerBtn;

    @FindBy(xpath = "//img[@class='statemade-products-preroll']")
    private WebElement prerollsPhoto;

    @FindBy(xpath = "//div[contains(text(),'pre-rolls')]")
    private WebElement prerollsBtn;

    @FindBy(xpath = "//*[@id=\"hero\"]/div[33]")
    private WebElement effectsList;

    @FindBy(xpath = "//div[@class='statemade-products-wrapper products-type-default products-max-pens toggle-js']//a[@class='btn-find-store'][contains(text(),'Find Your Store')]")
    private WebElement pensFindYourStore;

    @FindBy(xpath = "//input[@placeholder='Enter email for updates.']")
    private WebElement statemadeEmailSignUpfield;

    @FindBy(xpath = "//button[@class='c-statemade-signup__submit']")
    private WebElement statemadeEmailSignUpSubmit;

    @FindBy(xpath = "/html/body/div[1]/div/footer/div[3]/div/div/div[3]/div/h1")
    private WebElement statemadeEmailSignUpThankYouText;

    private long timeoutInSeconds = 60;
    private WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), timeoutInSeconds);

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return productsTitleTxt.isDisplayed() && productsDescTxt.isDisplayed();
    }

    public boolean productsArePresent() {
        return (pensBtn.isDisplayed()
                && dropsBtn.isDisplayed()
                && flowerBtn.isDisplayed()
                && prerollsBtn.isDisplayed());
    }

    public boolean productsPhotosArePresent() {
        return (pensPhoto.isDisplayed()
                && dropsPhoto.isDisplayed()
                && flowerPhoto.isDisplayed()
                && prerollsPhoto.isDisplayed());
    }

    public String getProductsDescText() {
        return productsDescTxt.getText();
    }

    public void selectPens() {
        wait.until(ExpectedConditions.elementToBeClickable(pensBtn));
        pensBtn.click();
    }

    public void selectDrops() {
        wait.until(ExpectedConditions.elementToBeClickable(dropsBtn));
        dropsBtn.click();
    }

    public void selectFlower() {
        wait.until(ExpectedConditions.elementToBeClickable(flowerBtn));
        flowerBtn.click();
    }

    public void selectPrerolls() {
        wait.until(ExpectedConditions.elementToBeClickable(prerollsBtn));
        prerollsBtn.click();
    }

    public void selectPensFindYourStoreLink() {
        wait.until(ExpectedConditions.elementToBeClickable(pensFindYourStore));
        pensFindYourStore.click();
    }

    public void enterStatemadeEmailSignUp(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(statemadeEmailSignUpfield));
        statemadeEmailSignUpfield.sendKeys(email);
    }

    public void selectStatemadeSubmitButton() {
        wait.until(ExpectedConditions.elementToBeClickable(statemadeEmailSignUpSubmit));
        statemadeEmailSignUpSubmit.click();
    }

    public String getStatemadeEmailSignUpThankYouText() {
        wait.until(ExpectedConditions.elementToBeClickable(statemadeEmailSignUpThankYouText));
        return statemadeEmailSignUpThankYouText.getText();
    }
}
