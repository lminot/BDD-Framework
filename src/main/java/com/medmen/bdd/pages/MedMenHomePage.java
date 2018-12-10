package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MedMenHomePage extends PageObject {

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

  @FindBy(xpath = "//a[contains(text(),'[statemade]')]")
  private WebElement topNavStatemade;

  @FindBy(xpath = "//h3[@class='c-heading-38 u-color-red']")
  private WebElement footerNewsletterTitle;

  @FindBy(xpath = "//p[@class='c-heading-24 u-spacing-40']")
  private WebElement footerNewsletterInfo;

  @FindBy(xpath = "//input[@placeholder='Email Address']")
  private WebElement footerEmailInput;

//  @FindBy(xpath = "//div[@class='c-signup__form']//button[@class='c-signup__submit']")
  @FindBy(xpath = "//*[@id=\"newsletter\"]/div/div/form/div/button")
  private WebElement footerEmailSubmit;

  @FindBy(xpath = "//p[@class='c-signup__disclaimer']")
  private WebElement footerEmailDisclaimer;

  @FindBy(xpath = "//p[@class='js-newsletter__success u-text-center']")
  private WebElement footerEmailSignUpSuccess;


  public MedMenHomePage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return logo.isDisplayed();
  }

  public void selectStatemade() {
    if (topNavStatemade.isDisplayed()) {
      topNavStatemade.click();
    }
  }

  public void enterNewsletterEmail(String email){
    if (footerEmailInput.isDisplayed()) {
      footerEmailInput.sendKeys(email);
    }
  }

  public void submitNewsletter(){
    footerEmailSubmit.click();
  }

  public String getSuccessText(){
    if (footerEmailSignUpSuccess.isDisplayed()) {
      return footerEmailSignUpSuccess.getText();
    } else {
      return null;
    }
  }
}
