package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExitPop extends PageObject {

    @FindBy(xpath = "//h1[@class='c-exit-pop__title']")
    private WebElement exitPopTitle;

    @FindBy(xpath = "    private WebElement exitPopTitle;\n")
    private WebElement exitPopMessage;

    @FindBy(xpath = "//input[@placeholder='Email address']")
    private WebElement exitPopEmailField;

    @FindBy(xpath = "//form[@class='c-signup__form js-email__form']//button[@class='c-signup__submit']")
    private WebElement exitPopSubmit;

    @FindBy(xpath = "//p[@class='c-exit-pop__disclaimer']")
    private WebElement exitPopDisclaimer;

    @FindBy(xpath = "//img[@src='/static/img/global/close-grey.svg']")
    private WebElement exitPopClose;


    public ExitPop(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized() {
        return exitPopTitle.isDisplayed();
    }
}
