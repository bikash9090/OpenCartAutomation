package bksoft.OpenCartAutomation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bksoft.OpenCartAutomation.base.PageBase;

public class LoginPage extends PageBase {
	
	Logger log = LogManager.getLogger(PageBase.class.getName());

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-password")
	WebElement pwd;

	@FindBy(linkText = "Forgotten Password")
	WebElement forgotPwd;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	WebElement loginBtn;

	@FindBy(linkText = "Continue")
	WebElement register;

	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		log.info("Login page object instantiated.");
	}

	public void enterEmail(String emailId) {
		log.info("Entering mail id ["+emailId+"] in email field.");
		enterText(email, emailId);
	}
	
	public void enterPassword(String password) {
		log.info("Entering password [*****] to password field.");
		enterText(pwd, password);
	}
	
	public void  clickOnLogin() {
		log.info("Clicking on login button.");
		flashAndclick(loginBtn);
	}
	
	public void clickOnForgotPassword() {
		log.info("Clicking on forgot password.");
		flashAndclick(forgotPwd);
	}
	
	public void clickOnRegisterAccount() {
		log.info("Clicking on continue button of register account.");
		flashAndclick(register);
	}

}
