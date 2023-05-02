package bksoft.OpenCartAutomation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bksoft.OpenCartAutomation.base.PageBase;

public class RegisterPage extends PageBase {

	Logger log = LogManager.getLogger(RegisterPage.class.getName());

	// -----------------------------Locators-----------------------------------//

	@FindBy(id = "input-firstname")
	WebElement fname;

	@FindBy(id = "input-lastname")
	WebElement lname;

	@FindBy(id = "input-email")
	WebElement email;

	@FindBy(id = "input-password")
	WebElement pwd;

	@FindBy(id = "input-newsletter-yes")
	WebElement subscribeYes;

	@FindBy(id = "input-newsletter-no")
	WebElement subscribeNo;

	@FindBy(xpath = "//input[@type=\"checkbox\"]")
	WebElement termsAndConditions;

	@FindBy(xpath = "//button[@type=\"submit\"]")
	WebElement submit;

	@FindBy(linkText = "login page")
	WebElement loginPage;

	@FindBy(linkText = "Login")
	WebElement login;

	@FindBy(linkText = "Register")
	WebElement register;

	@FindBy(linkText = "Forgotten Password")
	WebElement forgotPwd;

	@FindBy(linkText = "My Account")
	WebElement myAccount;

	@FindBy(linkText = "Address Book")
	WebElement addressBook;

	@FindBy(linkText = "Wish List")
	WebElement wishList;

	@FindBy(linkText = "Order History")
	WebElement orderHistory;

	@FindBy(linkText = "Downloads")
	WebElement downloads;

	@FindBy(linkText = "Subscriptions")
	WebElement subscriptions;

	@FindBy(linkText = "Reward Points")
	WebElement rewardPoints;

	@FindBy(linkText = "Returns")
	WebElement returns;

	@FindBy(linkText = "Transactions")
	WebElement transactions;

	@FindBy(linkText = "Newsletter")
	WebElement newsLetter;

	// Constructor//

	public RegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// Generic actions//

	public void enterFirstName(String firstName) {
		log.info("Entering text : [" + firstName + "] to First Name.");
		//passing the locator and keyword string to PageBase method.
		enterText(fname, firstName);
	}

	public void enterLastName(String lastName) {
		log.info("Entering text : [" + lastName + "] to Last Name.");
		enterText(lname, lastName);
	}

	public void enterEmail(String emailId) {
		log.info("Entering email id : [" + emailId + "] to E-Mail.");
		enterText(email, emailId);
	}
	
	public void enterPassword(String password) {
		log.info("Entering password [********] to Password.");
		enterText(pwd, password);
	}
	
	public void subscribeToNewsletter(String agree) {
		log.info("Subscribe Newsletter : "+agree);
		if(agree.equalsIgnoreCase("yes")) {
			flashAndclick(subscribeYes);
		} else if (agree.equalsIgnoreCase("no")) {
			flashAndclick(subscribeNo);
		}
	}
	
	public void acceptTermsAndConditions() {
		log.info("Accepting terms & condtions.");
		flashAndclick(termsAndConditions);
	}
	
	public void clickOnContinue() {
		log.info("Clicking and submitting the the user registration form.");
		flashAndclick(submit);
		
	}

}
