package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgeGatePage extends PageObject {

  @FindBy(xpath = "//img[@class='c-age-gate__logo u-img-respond']")
  private WebElement logo;

  @FindBy(xpath = "//span[@class='c-checkbox__ui']")
  private WebElement rememeberMe;

  @FindBy(xpath = "//button[@class='c-age-gate__option-btn js-age-gate__yes']")
  private WebElement twentyOneYes;

  @FindBy(xpath = "//button[@class='c-age-gate__option-btn js-age-gate__no']")
  private WebElement twentyOneNo;

  @FindBy(xpath = "//input[@placeholder='Email Address (optional)']")
  private WebElement enterEmailAddressBox;

  @FindBy(xpath = "//button[@class='c-signup__submit c-signup__submit--420']")
  private WebElement enterButton;

  public AgeGatePage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return logo.isDisplayed();
  }

  public void clickRemeberMe() {
    rememeberMe.click();
  }

  public void selectYesImTwentyOne() {
    twentyOneYes.click();
  }

  public void selectNoImTwentyOne() {
    twentyOneNo.click();
  }

  public void enterEmailAddress(String emailAddress) {
    this.enterEmailAddressBox.clear();
    this.enterEmailAddressBox.sendKeys(emailAddress);
  }

  public void clickEnter() {
    enterButton.click();
  }
}
