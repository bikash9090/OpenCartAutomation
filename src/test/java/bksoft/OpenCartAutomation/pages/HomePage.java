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

	/*------------------------------Constructor----------------------------------*/

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	/*-----------------------Lazy initialization methods--------------------------*/

	/*-------------------------Generic action methods-----------------------------*/

	public List<WebElement> getCurrencyList() {
		return currencyList;
	}

}
