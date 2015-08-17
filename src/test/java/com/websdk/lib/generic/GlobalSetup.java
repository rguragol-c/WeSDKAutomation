package com.websdk.lib.generic;

import org.openqa.selenium.WebDriver;



public class GlobalSetup 
{
	private boolean setup = false;
	private WebDriver driver;
	@SuppressWarnings("unused")
	private Log log = new Log();
	public void GlobalSetupMethod()
	{
		if(isSetup()==false)
		{
			Log.info("Framework logging Started...");
			Log.info("Running GlobalSetup Method...");
			
			setDriver(Browser.intializeWebDriver());
			
			
			Log.info("Completed GlobalSetup Method...");
			setSetup(true);
		}else
		{
			setDriver(Browser.intializeWebDriver());
		}
	}
	
	public WebDriver getDriver() {
		GlobalSetupMethod();
		return driver;
	}
	
	private void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	private boolean isSetup() {
		return setup;
	}

	private void setSetup(boolean setup) {
		this.setup = setup;
	}

}
