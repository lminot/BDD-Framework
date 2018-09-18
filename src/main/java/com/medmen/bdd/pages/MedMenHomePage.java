package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MedMenHomePage extends PageObject{


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

    @FindBy(xpath = "//a[@class='c-header__nav-link'][contains(text(),'Newsroom')]")
    private WebElement topNavNewsroom;

    public MedMenHomePage(WebDriver driver) {
        super(driver);
    }
}
