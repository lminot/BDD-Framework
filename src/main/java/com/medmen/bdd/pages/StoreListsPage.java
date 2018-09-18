package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StoreListsPage extends PageObject{

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[1]//li[1]")
    private WebElement storesListCalifornia;

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[2]//li[2]")
    private WebElement storesListNevada;

    @FindBy(xpath = "//div[@class='c-stores-app__content']//li[3]//li[3]")
    private WebElement storesListNewYork;

    public StoreListsPage(WebDriver driver) {
        super(driver);
    }
}
