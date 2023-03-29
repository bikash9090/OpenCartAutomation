package bksoft.OpenCartAutomation.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends VerifyLocators {

	/*------------------------------Locators-----------------------------------*/

	@FindBy(xpath = "//*[@id=\"form-currency\"]/div/a/span")
	WebElement currency;

	@FindBys({ @FindBy(xpath = "//ul[@data-popper-placement=\"bottom-start\"]"), @FindBy(tagName = "li") })
	List<WebElement> currencyList;

	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[1]/span")
	WebElement phone;

	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[2]/div/a/span")
	WebElement myAccount;

	@FindBys({ @FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[2]/div/ul"), @FindBy(tagName = "li") })
	List<WebElement> myAccoutOptions;
	
	@FindBy(xpath = "//*[@id=\"wishlist-total\"]/span")
	WebElement wishList;
	
	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[4]/a/span")
	WebElement shoppingCart;
	
	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[5]/a/span")
	WebElement checkout;
	
	@FindBy(xpath = "//*[@id=\"logo\"]/a/img")
	WebElement logo;
	
	@FindBy(xpath = "//input[@type=\"text\" and @name=\"search\"]")
	WebElement searchBar;
	
	@FindBy(xpath = "//*[@id=\"search\"]/button")
	WebElement searchBtn;
	
	@FindBy(xpath = "//*[@id=\"header-cart\"]/div/button")
	WebElement items;
	
	@FindBys({})
	WebElement productCategory;

	/*------------------------------Constructor----------------------------------*/

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	/*-----------------------Lazy initialization methods--------------------------*/

	/*-------------------------Generic action methods-----------------------------*/

	public List<WebElement> getCurrencyList() {
		return currencyList;
	}
	
	public List<WebElement> getAccoountOptions() {
		return myAccoutOptions;
	} 

}
