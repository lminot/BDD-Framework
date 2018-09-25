package com.medmen.bdd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage extends PageObject {

  @FindBy(xpath = "//li[@class='c-header__nav-item u-hidden-less-than-md']//button[@type='button']")
  private WebElement cart;

  @FindBy(
      xpath =
          "//h1[@class='c-store-info-desktop u-spacing-none u-spacing-20-less-than-sm']//*[@class='c-icon c-icon--store']")
  private WebElement storeIcon;

  @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/h1[1]/div[1]/a[1]/div[1]")
  private WebElement storeName;

  @FindBy(xpath = "//h1[@class='o-account__title-borderless']")
  private WebElement title;

  @FindBy(
      xpath =
          "//body//div[@id='__next']//div[@class='o-wrap']//div[@class='o-container c-create-account']//div[@class='o-account']//div//div[@class='o-account__social-button']//button[1]")
  private WebElement faceBookbutton;

  @FindBy(
      xpath =
          "//body//div[@id='__next']//div[@class='o-wrap']//div[@class='o-container c-create-account']//div[@class='o-account']//div//div[@class='o-account__social-button']//button[2]")
  private WebElement googleButton;

  @FindBy(xpath = "//input[@id='email']")
  private WebElement email;

  @FindBy(xpath = "//input[@id='password']")
  private WebElement password;

  @FindBy(xpath = "//form//div[3]//label[1]//span[1]")
  private WebElement specialPromoCheckBox;

  @FindBy(xpath = "//div[@class='c-form-group__block']//label[1]//span[1]")
  private WebElement genderFeamle;

  @FindBy(xpath = "//div[@class='c-form-group__block']//label[2]//span[1]")
  private WebElement genderMale;

  @FindBy(xpath = "//div[@class='c-form-group__block']//label[3]//span[1]")
  private WebElement genderNonbinary;

  @FindBy(xpath = "//input[@id='first_name']")
  private WebElement firstName;

  @FindBy(xpath = "//input[@id='last_name']")
  private WebElement lastName;

  @FindBy(xpath = "//select[@id='dob_month']")
  private WebElement dobMonth;

  @FindBy(xpath = "//select[@id='dob_day']")
  private WebElement dobDay;

  @FindBy(xpath = "//select[@id='dob_year']")
  private WebElement dobYear;

  @FindBy(xpath = "//input[@id='zip']")
  private WebElement zipCode;

  @FindBy(xpath = "//input[@id='city']")
  private WebElement city;

  @FindBy(xpath = "//select[@id='state']")
  private WebElement state;

  @FindBy(xpath = "//select[@id='country']")
  private WebElement country;

  @FindBy(xpath = "//select[@id='preferred_store_id']")
  private WebElement perfStore;

  @FindBy(xpath = "//input[@id='phone_number']")
  private WebElement phoneNum;

  @FindBy(xpath = "//form//div[12]//label[1]//span[1]")
  private WebElement smsCheckbox;

  @FindBy(xpath = "//form//div[12]//label[1]//span[1]")
  private WebElement mmDataPolicyCheckbox;

  @FindBy(xpath = "//div[@class='o-form-field-flex']//div//span[@class='c-form-group__checkmark']")
  private WebElement mmPrivacyPolicyCheckbox;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement createAccount;

  @FindBy(xpath = "//a[@class='c-button c-button--hollow']")
  private WebElement signIn;

  public CreateAccountPage(WebDriver driver) {
    super(driver);
  }

  public boolean isInitialized() {
    return title.isDisplayed();
  }

  public void enterEmailAddress(String email) {
    this.email.clear();
    this.email.sendKeys(email);
  }

  public void enterPassword(String password) {
    this.password.clear();
    this.password.sendKeys(password);
  }

  public void genderSet(String gender) {
    if (gender.toLowerCase().contains("male")) {
      this.genderMale.click();
    } else if (gender.toLowerCase().contains("female")) {
      this.genderFeamle.click();
    } else {
      this.genderNonbinary.click();
    }
  }

  public void enterFirstName(String firstName) {
    this.firstName.clear();
    this.firstName.sendKeys(firstName);
  }

  public void enterLastName(String lastName) {
    this.lastName.clear();
    this.lastName.sendKeys(lastName);
  }

  public void enterDobMonth(String month) {
    Select dropdown = new Select(this.dobMonth);
    dropdown.selectByVisibleText(month);
  }

  public void enterDobDay(String day) {
    Select dropdown = new Select(this.dobDay);
    dropdown.selectByVisibleText(day);
  }

  public void enterDobYear(String year) {
    Select dropdown = new Select(this.dobYear);
    dropdown.selectByVisibleText(year);
  }

  public void enterZipCode(String zipCode) {
    this.zipCode.clear();
    this.zipCode.sendKeys(zipCode);
  }

  public void enterCity(String city) {
    this.city.clear();
    this.city.sendKeys(city);
  }

  public void enterState(int state) {
    Select dropdown = new Select(this.state);
    dropdown.selectByIndex(state);
  }

  public void enterCountry(int country) {
    Select dropdown = new Select(this.country);
    dropdown.selectByIndex(country);
  }

  public void selectPerfStore(int index) {
    Select dropdown = new Select(this.perfStore);
    dropdown.selectByIndex(index);
  }

  public void enterPhoneNum(String phoneNum) {
    this.phoneNum.clear();
    this.phoneNum.sendKeys(phoneNum);
  }

  public void checkDataPolicy() {
    this.mmDataPolicyCheckbox.click();
  }

  public void checkTermsOfService() {
    this.mmPrivacyPolicyCheckbox.click();
  }

  public void clickCreateAccount() {
    this.createAccount.clear();
  }
}
