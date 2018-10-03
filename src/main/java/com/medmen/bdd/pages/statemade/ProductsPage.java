package com.medmen.bdd.pages.statemade;

import com.medmen.bdd.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends PageObject {

  @FindBy(xpath = "//div[@class='statemade-products-header']")
  private WebElement productsTitleTxt;

  @FindBy(xpath = "//div[@class='statemade-products-note']")
  private WebElement productsDescTxt;

  @FindBy(xpath = "//div[contains(text(),'pens')]")
  private WebElement pensBtn;

  @FindBy(xpath = "//div[contains(text(),'drops')]")
  private WebElement dropsBtn;

  @FindBy(xpath = "//div[contains(text(),'flower')]")
  private WebElement flowerBtn;

  @FindBy(xpath = "//div[contains(text(),'prerolls')]")
  private WebElement prerollsBtn;

  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return productsTitleTxt.isDisplayed() && productsDescTxt.isDisplayed();
  }

  public boolean productsArePresent() {
    return (pensBtn.isDisplayed()
        && dropsBtn.isDisplayed()
        && flowerBtn.isDisplayed()
        && prerollsBtn.isDisplayed());
  }

  public String getProductsDescText() {
    return productsDescTxt.getText();
  }

  public void selectPens() {
    pensBtn.click();
  }

  public void selectDrops() {
    dropsBtn.click();
  }

  public void selectFlower() {
    flowerBtn.click();
  }

  public void selectPrerolls() {
    prerollsBtn.click();
  }
}
