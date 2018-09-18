package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends PageObject {

  @FindBy(xpath = "//h1[@class='o-account__title-borderless']")
  private WebElement title;

  @FindBy(
      xpath =
          "//body//div[@id='__next']//div[@class='o-wrap']//div[@class='o-container']//div[@class='o-account']//div//div[@class='o-account__cta']//button[1]")
  private WebElement facebookSignIn;

  @FindBy(xpath = "//body//div[@id='__next']//div[@class='o-wrap']//div[@class='o-container']//div[@class='o-account']//div//div[@class='o-account__cta']//button[2]")
  private WebElement googleSignIn;

  @FindBy(xpath = "//input[@id='email']")
  private WebElement emailBox;

  @FindBy(xpath = "//input[@id='password']")
  private WebElement passwordBox;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement signInButton;

    @FindBy(xpath = "//a[@class='c-button c-button--hollow o-sign-in__sign-in-button']")
    private WebElement createAccountButton;

  public SignInPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return title.isDisplayed();
  }

}
