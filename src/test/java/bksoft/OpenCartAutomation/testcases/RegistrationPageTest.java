package bksoft.OpenCartAutomation.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;
import bksoft.OpenCartAutomation.pages.RegisterPage;
import bksoft.OpenCartAutomation.utils.ExcelUtils;
import bksoft.OpenCartAutomation.utils.ScreenshotUtils;

public class RegistrationPageTest extends TestBase{
	HomePage hp;
	RegisterPage reg;
	ScreenshotUtils capture;
	ExcelUtils excel;
	

	@BeforeClass
	public void initialization() {
		setUpDriver();
		hp = new HomePage(driver);
		reg = new RegisterPage(driver);
		capture = new ScreenshotUtils(driver);
		excel = new ExcelUtils();
	}
	
	@AfterClass
	public void tearDown() {
		tearDownDriver();
	}
	
	@DataProvider()
	public Object[][] credentials(){
		return excel.getTestData("UserInformation"); //Passing the sheet name of Excel file as parameter.
	}
	
	@Test(dataProvider = "credentials",description = "User registration form.")
	public void userRegistrationTest(String fname,String lname,String email,String pwd,String subs) throws InterruptedException {
		hp.clickOnMyAccount();
		hp.clickOnRegister();
		reg.enterFirstName(fname);
		reg.enterLastName(lname);
		reg.enterEmail(email);
		reg.enterPassword(pwd);
		reg.subscribeToNewsletter(subs);
		reg.acceptTermsAndConditions();
		reg.clickOnContinue();
	}
}
