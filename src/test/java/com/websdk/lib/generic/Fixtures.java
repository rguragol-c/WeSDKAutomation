package com.websdk.lib.generic;

import org.openqa.selenium.WebDriver;
import com.websdk.appSpecific.Home;



public class Fixtures 
{
	public static WebDriver setupFixture(String message)
	{
		WebDriver driver = Browser.getGlobalSetup().getDriver();
		Log.info(message);
		Browser.getWaits().wait(driver);
		
		Home.LaunchwebsdkURL(driver);
		Log.info("Fixture Setup completed");
		return driver;
		
	}
	
	public static void tearDownFixture(WebDriver driver)
	{
		Log.info("Fixture teardown started.");
		
		driver.quit();
		Log.info("Quit Webdriver.");
		Log.info("Fixture teardown completed.");
	}
}
