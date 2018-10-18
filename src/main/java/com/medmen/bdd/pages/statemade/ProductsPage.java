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

  @FindBy(xpath = "//img[@class='statemade-products-pens']")
  private WebElement pensPhoto;

  @FindBy(xpath = "//div[contains(text(),'pens')]")
  private WebElement pensBtn;

  @FindBy(xpath = "//img[@class='statemade-products-drops']")
  private WebElement dropsPhoto;

  @FindBy(xpath = "//div[contains(text(),'drops')]")
  private WebElement dropsBtn;

  @FindBy(xpath = "//img[@class='statemade-products-flower']")
  private WebElement flowerPhoto;

  @FindBy(xpath = "//div[contains(text(),'flower')]")
  private WebElement flowerBtn;

  @FindBy(xpath = "//img[@class='statemade-products-preroll']")
  private WebElement prerollsPhoto;

  @FindBy(xpath = "//div[contains(text(),'pre-rolls')]")
  private WebElement prerollsBtn;

  @FindBy(xpath = "//*[@id=\"hero\"]/div[33]")
  private WebElement effectsList;

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

  public boolean productsPhotosArePresent() {
    return (pensPhoto.isDisplayed()
            && dropsPhoto.isDisplayed()
            && flowerPhoto.isDisplayed()
            && prerollsPhoto.isDisplayed());
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
