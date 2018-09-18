package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart extends PageObject{


    @FindBy(xpath = "//li[@class='c-header__nav-item u-hidden-less-than-md']//button[@type='button']")
    private WebElement cart;

    @FindBy(xpath = "//div[@class='c-store-info__icon']//*[@class='c-icon c-icon--store']")
    private WebElement storeIcon;

    @FindBy(xpath = "//span[@class='u-font-bold']")
    private WebElement storeName;

    @FindBy(xpath = "//*[@class='c-icon c-icon--close']")
    private WebElement close;

    @FindBy(xpath = "//h4[@class='c-cart-drawer__header c-heading-18 u-font-bold u-spacing-none']")
    private WebElement cartDrawer;

    @FindBy(xpath = "//div[@class='c-cart-item c-cart-item--small']")
    private WebElement cartItem;

    @FindBy(xpath = "//a[@class='c-button c-button--primary']")
    private WebElement checkout;

    public Cart(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return cartDrawer.isDisplayed();
    }

    public void clickCheckout(){
        checkout.click();
    }

}
