package bksoft.OpenCartAutomation.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;
import bksoft.OpenCartAutomation.pages.RegisterPage;
import bksoft.OpenCartAutomation.utils.ScreenshotUtils;

public class RegistrationPageTest extends TestBase{
	HomePage hp;
	RegisterPage reg;
	ScreenshotUtils capture;
	

	@BeforeClass
	public void initialization() {
		setUpDriver();
		hp = new HomePage(driver);
		reg = new RegisterPage(driver);
		capture = new ScreenshotUtils(driver);
	}
	
	@AfterClass
	public void tearDown() {
		tearDownDriver();
	}
	
	@Test(description = "User registration.")
	public void userRegistrationTest() {
		hp.clickOnMyAccount();
		hp.clickOnRegister();
		reg.enterFirstName("Bikash");
		reg.enterLastName("sethy");
		reg.enterEmail("ABC@gmail.com");
		reg.enterPassword("Pupun&5454");
		reg.subscribeToNewsletter("No");
		reg.acceptTermsAndConditions();
		reg.clickOnContinue();
	}

}
