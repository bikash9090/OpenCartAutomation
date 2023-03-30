package bksoft.OpenCartAutomation.testcases;

import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;

public class HomePageTest extends TestBase {
	HomePage hp;

	@Test
	public void validatingHomepageLocatorsFunctionality() throws InterruptedException {
		hp = new HomePage(driver);
		
		hp.hoverOverCategory("Desktops");
		//hp.clickOnSubCategory();
		Thread.sleep(3000);
		
	}

}
