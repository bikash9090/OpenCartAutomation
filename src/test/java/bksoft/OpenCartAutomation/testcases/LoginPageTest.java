package bksoft.OpenCartAutomation.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;
import bksoft.OpenCartAutomation.pages.LoginPage;
import bksoft.OpenCartAutomation.utils.ExcelUtils;

public class LoginPageTest extends TestBase {

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

	@DataProvider
	private Object[][] LoginCredentials() {
		return new ExcelUtils().getTestData("LoginCredentials");// Passing the sheet name of Excel file as parameter.
	}

	@Test(dataProvider = "LoginCredentials", description = "User login form.")
	public void loginToAccountTest(String uname, String pwd) {
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		login.enterEmail(uname);
		login.enterPassword(pwd);
		login.clickOnLogin();
	}
}
