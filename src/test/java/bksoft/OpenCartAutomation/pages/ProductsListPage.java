package bksoft.OpenCartAutomation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import bksoft.OpenCartAutomation.base.PageBase;

public class ProductsListPage extends PageBase {

	@FindBys({ @FindBy(xpath = "//div[@id=\"product-list\"]/descendant::div[@class=\"description\"]"),
			@FindBy(tagName = "a") })
	List<WebElement> productTitles;

	@FindBys({ @FindBy(xpath = "//div[@id=\"product-list\"]/descendant::div[@class=\"description\"]"),
			@FindBy(tagName = "p") })
	List<WebElement> productDescription;

	public ProductsListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void getListOfProducts() {

	}

}
