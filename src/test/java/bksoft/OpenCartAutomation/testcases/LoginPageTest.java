package bksoft.OpenCartAutomation.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;
import bksoft.OpenCartAutomation.pages.LoginPage;

public class LoginPageTest extends TestBase{
	
	HomePage hp;
	LoginPage login;
	

	@BeforeClass
	public void initialization() {
		setUpDriver();
		hp = new HomePage(driver);
		login = new LoginPage(driver);
	}

	@AfterClass
	public void tearDown() {
		tearDownDriver();
	}
	
	@Test
	public void loginToAccountTest() throws InterruptedException {
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		login.enterEmail("ABC@gamil.com");
		login.enterPassword("Pupun&5454");
		login.clickOnLogin();
		Thread.sleep(5000);
	}
	

}
