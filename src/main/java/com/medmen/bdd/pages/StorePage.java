package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StorePage extends PageObject{


    @FindBy(xpath = "//li[@class='c-header__nav-item u-hidden-less-than-md']//button[@type='button']")
    private WebElement cart;

    @FindBy(xpath = "//h1[@class='c-store-info-desktop u-spacing-none u-spacing-20-less-than-sm']//*[@class='c-icon c-icon--store']")
    private WebElement storeIcon;

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/h1[1]/div[1]/a[1]/div[1]")
    private WebElement storeName;

    @FindBy(xpath = "//input[@placeholder='Search All Products by Brand, Categories, or Keywords']")
    private WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchSubmit;

    @FindBy(xpath = "//div[@class='o-product-grid']")
    private WebElement productsGrid;

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return storeIcon.isDisplayed();
    }

    public void selectCart(){
        cart.click();
    }
}
