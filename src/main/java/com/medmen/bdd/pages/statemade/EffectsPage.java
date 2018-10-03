package com.medmen.bdd.pages.statemade;

import com.medmen.bdd.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EffectsPage extends PageObject {

  @FindBy(xpath = "//div[@class='effects-desired-txt']")
  private WebElement desiredStateTxt;

  @FindBy(xpath = "//div[@class='effects-list-title'][contains(text(),'max')]")
  private WebElement maxOption;

  @FindBy(xpath = "//div[@class='effects-list-title'][contains(text(),'joy')]")
  private WebElement joyOption;

  @FindBy(xpath = "//div[@class='effects-list-title'][contains(text(),'zen')]")
  private WebElement zenOption;

  @FindBy(xpath = "//div[@class='effects-list-title'][contains(text(),'ebb')]")
  private WebElement ebbOption;

  @FindBy(xpath = "//div[@class='effects-list-title'][contains(text(),'zzz')]")
  private WebElement zzzOption;

  @FindBy(xpath = "//div[@class='effects-list-title list-effect-drk']")
  private WebElement oneOption;

  @FindBy(xpath = "//div[@class='effects-list-title'][contains(text(),'cbd')]")
  private WebElement cbdOption;

  public EffectsPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return desiredStateTxt.isDisplayed();
  }

  public boolean linksArePresent() {
    return (maxOption.isDisplayed()
        && joyOption.isDisplayed()
        && zenOption.isDisplayed()
        && ebbOption.isDisplayed()
        && zzzOption.isDisplayed()
        && oneOption.isDisplayed()
        && cbdOption.isDisplayed());
  }

  public String getDesiredEffectText() {
    return desiredStateTxt.getText();
  }

  public void selectMax() {
    maxOption.click();
  }

  public void selectJoy() {
    joyOption.click();
  }

  public void selectZen() {
    zenOption.click();
  }

  public void selectEbb() {
    ebbOption.click();
  }

  public void selectZzz() {
    zzzOption.click();
  }

  public void selectOne() {
    oneOption.click();
  }

  public void selectCbd() {
    cbdOption.click();
  }
}
