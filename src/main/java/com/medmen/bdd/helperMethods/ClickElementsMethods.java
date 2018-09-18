package com.medmen.bdd.helperMethods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;


public class ClickElementsMethods extends SelectElementByType implements BaseTest
{
	//SelectElementByType eleType= new SelectElementByType();
	private WebElement element=null;
	
	/** Method to click on an element
	@param accessType : String : Locator type (id, name, class, xpath, css)
	@param accessName : String : Locator value
	*/
	public void click(String accessType, String accessName)
	{
		element = wait.until(presenceOfElementLocated(getelementbytype(accessType, accessName)));
		element.click();
	}
	
	/** Method to forcefully click on an element
	@param accessType : String : Locator type (id, name, class, xpath, css)
	@param accessName : String : Locator value
	*/
	public void clickForcefully(String accessType, String accessName)
	{
		element = wait.until(presenceOfElementLocated(getelementbytype(accessType, accessName)));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",element);
	}
	
	/** Method to Double click on an element
	@param accessType : String : Locator type (id, name, class, xpath, css)
	@param accessValue : String : Locator value
	*/
	public void doubleClick(String accessType, String accessValue)
	{
		element = wait.until(presenceOfElementLocated(getelementbytype(accessType, accessValue)));

		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}
}