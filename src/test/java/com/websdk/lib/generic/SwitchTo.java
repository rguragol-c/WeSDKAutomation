package com.websdk.lib.generic;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class SwitchTo 
{
	private String AlertMessage= null;
	
	public String getBasewindowHandle(WebDriver driver)
	{
		return driver.getWindowHandle();
	}
	
	public void switchToPopupWindow(WebDriver driver)
	{
		String basewindowhandle= getBasewindowHandle(driver);
		driver.switchTo().window(driver.getWindowHandles().iterator().next().toString());
		if (basewindowhandle.equals(getBasewindowHandle(driver)))
		{
			TestStatus.fail("Popup window was not present");
		}
	}
	
	public void switchToPopupWindow(WebDriver driver,String WindowTitle)
	{
		String basewindowhandle= getBasewindowHandle(driver);
		 Set<String> WindowHandles= driver.getWindowHandles();
		 for (String handle : WindowHandles) {
			 driver.switchTo().window(handle);
			if (basewindowhandle.equals(getBasewindowHandle(driver)))
			{
				 if (driver.getTitle().equals(WindowTitle))
				 {
					 break;
				 }
			}
		}
	}
	
	public void closePopupWindow(WebDriver driver, String BaseWindowHandle)
	{
		if (!BaseWindowHandle.equals(getBasewindowHandle(driver)))
		{
			driver.close();
			driver.switchTo().window(BaseWindowHandle);
		}
	}
	
	public Alert alert(WebDriver driver)
	{
		try
		{
			Alert a = driver.switchTo().alert();
			setAlertMessage(a.getText());
			return a;
		}catch(Exception e)
		{
			Log.debug("No Alert Present");
			setAlertMessage("No Alert Present");
		}
		return null;
	}

	public String getAlertMessage() {
		return AlertMessage;
	}

	private void setAlertMessage(String alertMessage) {
		AlertMessage = alertMessage;
	}
}
