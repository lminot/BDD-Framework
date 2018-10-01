package com.medmen.bdd.helperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class ProgressMethods extends SelectElementByType implements BaseTest
{
	/** Method to wait 
	 * @param time : String : Time to wait
	 * @throws NumberFormatException
	 * @throws InterruptedException
	 */
	public void wait(String time) throws NumberFormatException, InterruptedException
	{
		//sleep method takes parameter in milliseconds
		Thread.sleep(Integer.parseInt(time)*1000);
	}
	
	/**Method to Explicitly wait for element to be displayed
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 * @param duration : String : Time to wait for element to be displayed
	 */
	public void waitForElementToDisplay(String accessType,String accessName,String duration)
	{
		By byEle = getElementByType(accessType, accessName);
		WebDriverWait wait = (new WebDriverWait(driver,Integer.parseInt(duration)*1000));
		wait.until(visibilityOfElementLocated(byEle));
	}
		
	/** Method to Explicitly wait for element to be enabled=click
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 * @param duration : String : Time to wait for element to be clickable
	 */
	public void waitForElementToClick(String accessType,String accessName,String duration)
	{
		By byEle = getElementByType(accessType, accessName);
		WebDriverWait wait = (new WebDriverWait(driver,Integer.parseInt(duration)*1000));
		wait.until(elementToBeClickable(byEle));
	}
	
}
