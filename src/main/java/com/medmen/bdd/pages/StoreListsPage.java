package com.medmen.bdd.pages;

import com.medmen.bdd.configs.DriverConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoreListsPage extends PageObject {

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[2]//li[2]")
    private WebElement storesListCalifornia;

    @FindBy(xpath = "//a[contains(text(),'Los Angeles - Beverly Hills')]")
    private WebElement beverlyHillsStore;

    @FindBy(xpath = "//a[contains(text(),'San Diego - Kearny Mesa')]")
    private WebElement kearnyMesaStore;

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[4]//li[4]")
    private WebElement storesListNevada;

    @FindBy(xpath = "//a[contains(text(),'Shop Now')]")
    private WebElement shopNowButton;

    private String homepageXpath = "//div[@class='c-home-hero-wrap']";

    public StoreListsPage(WebDriver driver) {
        super(driver);
    }

    private long timeoutInSeconds = 60;
    private WebDriverWait wait = new WebDriverWait(DriverConfig.getDriver(), timeoutInSeconds);

    public void selectBeverlyHillsStore() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(homepageXpath)));
        wait.until(ExpectedConditions.visibilityOf(storesListCalifornia));
        wait.until(ExpectedConditions.textToBePresentInElement(beverlyHillsStore, "Los Angeles - Beverly Hills"));
        wait.until(ExpectedConditions.elementToBeClickable(beverlyHillsStore));
        beverlyHillsStore.click();
    }

    public void selectKearnyMesaStore() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(homepageXpath)));
        wait.until(ExpectedConditions.visibilityOf(storesListCalifornia));
        wait.until(ExpectedConditions.textToBePresentInElement(kearnyMesaStore, "San Diego - Kearny Mesa"));
        wait.until(ExpectedConditions.elementToBeClickable(kearnyMesaStore));
        kearnyMesaStore.click();
    }

    public void selectShopNow() {
        wait.until(ExpectedConditions.elementToBeClickable(shopNowButton));
        shopNowButton.click();
    }
}
