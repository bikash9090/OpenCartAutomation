package bksoft.OpenCartAutomation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends VerifyLocators {

	/*------------------------------Locators----------------------------------*/
	
	@FindBy(xpath = "//*[@id=\"form-currency\"]/div/a/span")
	WebElement currency;

	@FindBy(xpath = "//ul[@data-popper-placement=\"bottom-start\"]")
	WebElement curUl;
	
	
	
	
	
	
	
	
	/*------------------------------Constructor----------------------------------*/
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	/*----------------------Lazy initialization methods--------------------------*/
	public void currencyClick() {
		currency.click();
		List<WebElement> currencyList = curUl.findElements(By.tagName("li"));
		for(WebElement cur:currencyList) {
			System.out.println(cur.getText());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*-------------------------Generic action methods-----------------------------*/
	
}
