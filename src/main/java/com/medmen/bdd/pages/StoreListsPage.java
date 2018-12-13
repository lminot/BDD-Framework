package com.medmen.bdd.pages;

import com.medmen.bdd.configs.DriverConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreListsPage extends PageObject {

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[1]//li[1]")
    private WebElement storesListCalifornia;

    @FindBy(xpath = "//a[contains(text(),'Los Angeles - Beverly Hills')]")
    private WebElement beverlyHillsStore;

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[2]//li[2]")
    private WebElement storesListNevada;

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[3]//li[3]")
    private WebElement storesListNewYork;

    @FindBy(xpath = "//a[contains(text(),'Shop Now')]")
    private WebElement shopNowButton;

    public StoreListsPage(WebDriver driver) {
        super(driver);
    }

    private long timeoutInSeconds = 60;
    private WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), timeoutInSeconds);

    public void selectBeverlyHillsStore() {
        if (beverlyHillsStore.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(beverlyHillsStore));
            beverlyHillsStore.click();
        }
    }

    public void selectShopNow() {
        if (shopNowButton.isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(shopNowButton));
            shopNowButton.click();
        }
    }
}
