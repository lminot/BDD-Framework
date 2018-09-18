package com.medmen.bdd.helperMethods;

import com.medmen.bdd.configs.DriverConfigs;
import org.openqa.selenium.WebDriver;

public class JavascriptHandlingMethods implements BaseTest {
	protected WebDriver driver = DriverConfigs.getDriver();
	/**Method to handle alert
	 * @param decision : String : Accept or dismiss alert
	 */
	public void handleAlert(String decision)
	{
		if(decision.equals("accept"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
}
