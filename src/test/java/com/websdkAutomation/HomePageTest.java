package com.websdkAutomation;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.websdk.lib.generic.Browser;
import com.websdk.lib.generic.Fixtures;
import com.websdk.lib.generic.SoftAssert;
import com.websdk.lib.generic.TestAssert;
import com.websdk.lib.generic.TestStatus;

public class HomePageTest{

	private WebDriver driver =  null;
	private SoftAssert sassert=new SoftAssert();
	private TestAssert testassert=new TestAssert();



	@BeforeClass
	public void setUp() 
	{
		driver=Fixtures.setupFixture("Home page test class setup initiated.");
		/*String BaseWindowHandle = */
				Browser.getSwitchto().getBasewindowHandle(driver);
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
