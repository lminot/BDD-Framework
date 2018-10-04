package com.medmen.bdd.pages.statemade;

import com.medmen.bdd.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StateMadeLandingPage extends PageObject {

  @FindBy(xpath = "//img[@class='logo-statemade']")
  private WebElement logo;

  @FindBy(
      css =
          "#hero > div.o-container > h1")
  private WebElement fadeInTitle;

  @FindBy(xpath = "//*[@id=\"hero\"]/div[3]/div/a[1]")
  private WebElement effectButton;

  @FindBy(xpath = "//a[@class='statemade-select']")
  private WebElement productTypeButton;

  public StateMadeLandingPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return logo.isDisplayed();
  }

  public boolean linksArePresent() {
      return (effectButton.isDisplayed() && productTypeButton.isDisplayed());
  }

  public String getTitleText() {
      return fadeInTitle.getText();
  }

  public void clickEffectButton() {
      effectButton.click();
  }

  public void clickProductTypeButton() {
    productTypeButton.click();
  }

}
