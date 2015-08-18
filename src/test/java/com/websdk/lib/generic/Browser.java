package com.websdk.lib.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser 
{
	private static Elements elements = new Elements();
	private static Actions actions = new Actions();
	private static DriverWait waits = new DriverWait();
	private static SwitchTo switchto = new SwitchTo();
	private static GlobalSetup GlobalSetup = new GlobalSetup();
	
	public static String FireFox = "firefox";
	public static String InternetExplorer = "internetexplorer";
	public static String Chrome = "chrome";
	
	private static Properties props= new Properties();
	private static DesiredCapabilities dc = null;
	private static String PropertyFilePath=System.getProperty("user.dir")
			+ "/configurations/config.properties";
	public static WebDriver driver = null;
	
	public static Elements getElements() {
		return elements;
	}
	
	public static Actions getActions() {
		return actions;
	}
	
	public static DriverWait getWaits() {
		return waits;
	}
	
	public static SwitchTo getSwitchto() {
		return switchto;
	}
	
	public static GlobalSetup getGlobalSetup() {
		return GlobalSetup;
	}

	public static String getPropertyFromConfigFile(String Property)
	{
		try {
			props.load(new FileInputStream(PropertyFilePath));
			return props.getProperty(Property);
		} catch (Exception e) {
			Log.error(e.getMessage());
		}
		return null; 
	}
	
	
	public static WebDriver intializeWebDriver()
	{
		return intializeWebDriver(getPropertyFromConfigFile(ConfigProperties.BROWSER));
	}
	
	public static WebDriver intializeWebDriver(String Browser)
	{
		Log.info("Starting Webdriver... " );
		if (Browser.equalsIgnoreCase(FireFox))
		{
			driver = Firefox.getFireFoxDriver();
		}else if(Browser.equalsIgnoreCase(InternetExplorer))
		{
			driver =  IE.getIEDriver();
		}else if(Browser.equalsIgnoreCase(Chrome))
		{
			driver =  Chromium.getChromeDriver();
		}
		Log.info("Webdriver initialized...");
		return driver;
	}
	
	// store screenshots
	public static void takeScreenShot(String fileName) {
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")
					+ "\\Screenshots\\" + fileName + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static class Firefox 
	{
		public static WebDriver getFireFoxDriver()
		{
			Log.info("Starting Firefox driver...");
			dc= DesiredCapabilities.firefox();
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
			firefoxProfile.setPreference("browser.download.folderList", 2);
			firefoxProfile.setPreference("browser.download.dir",
					System.getProperty("user.dir")+"\\testdata\\Downloads\\");
			firefoxProfile.setPreference(
					"browser.helperApps.neverAsk.saveToDisk", "video/mp4,text/plain");
			dc.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			return new FirefoxDriver(dc);
		}
	}
	
	static class IE
	{
		public static WebDriver getIEDriver()
		{
			Log.info("Starting Internet Explorer driver...");
			setIEDriverPath();
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
			dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
			dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true); 
			dc.setJavascriptEnabled(true); 
			return new InternetExplorerDriver(dc);
		}
		
		private static void setIEDriverPath()
		{
			File file =null;
	        if (new File("C:\\Program Files (x86)").isDirectory()) {
	        	file= new File(System.getProperty("user.dir") + File.separator + "utilities" + File.separator + "IEDriverServer_x64.exe");
	        } else {
	        	file= new File(System.getProperty("user.dir") + File.separator + "utilities" + File.separator + "IEDriverServer_x86.exe");
	        }
	        System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
		}
		
	}
	
	static class Chromium
	{
		public static WebDriver getChromeDriver()
		{
			Log.info("Starting chrome driver...");
			File file =null;
			file= new File(System.getProperty("user.dir") + File.separator + "utilities" + File.separator + "chromedriver.exe");
			System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
			return new ChromeDriver();
			
		}
	}

	
}
