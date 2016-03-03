package com.websdkAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.websdk.lib.generic.Browser;
import com.websdk.lib.generic.ConfigProperties;
import com.websdk.lib.generic.ElementTags;
import com.websdk.lib.generic.Fixtures;
import com.websdk.lib.generic.GenericUtils;
import com.websdk.lib.generic.LocateBy;
import com.websdk.lib.generic.Log;
import com.websdk.lib.generic.SoftAssert;
import com.websdk.lib.generic.TestAssert;
import com.websdk.lib.generic.TestStatus;

//Test  comment

public class HomePageTest{

	private WebDriver driver =  null;
	private SoftAssert sassert=new SoftAssert();
	private TestAssert testassert=new TestAssert();



	@BeforeClass
	public void setUp() 
	{
		driver=Fixtures.setupFixture("Home page test class setup initiated.");
		String BaseWindowHandle = Browser.getSwitchto().getBasewindowHandle(driver);
	}	

	@Test(description="" ,priority=1, enabled=true)
	public void VerifySearchResult() 
	{
		
				
		
		
		sassert.showResults();
		TestStatus.pass("PASSED | TIMS ID : Trv589893c");
	}
	

	@AfterClass(alwaysRun=true)
	public void tearDown()
	{
			
		Fixtures.tearDownFixture(driver);
	}
}
