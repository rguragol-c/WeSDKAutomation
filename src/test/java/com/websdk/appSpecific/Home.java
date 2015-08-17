package com.websdk.appSpecific;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.websdk.lib.generic.ConfigProperties;
import com.websdk.lib.generic.TestStatus;
import com.websdk.lib.generic.Browser;
import com.websdk.lib.generic.ElementTags;
import com.websdk.lib.generic.LocateBy;
import com.websdk.lib.generic.Log;

public class Home 
{

	private static WebElement categoryLink;
	
	public static String getLoginUrl()
	{
		return "http://" + Browser.getPropertyFromConfigFile(ConfigProperties.URL);
	}
	
	public static void LaunchwebsdkURL(WebDriver driver) 
	{
		driver.manage().window().maximize();
		driver.get(getLoginUrl());		
		
		//Browser.getElements().element(driver, ElementTags.txtUserName).clear();
		
	}
	
}


