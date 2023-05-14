package bksoft.OpenCartAutomation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bksoft.OpenCartAutomation.base.PageBase;

public class ProductsListPage extends PageBase {

	// List all product titles.
	@FindBy(xpath = "//div[@id=\"product-list\"]/descendant::div[@class=\"description\"]/h4/a")
	List<WebElement> prodTitles;

	// Product description relative xpath. Called after the product found on the.
	String xpathForProdDesc = "./following::p[1]";

	String xpathForProdPrice = "./following::div[1]/span[@class=\"price-new\"]";

	String xpathForProdTax = "./following::div[1]/span[@class=\"price-tax\"]";
	
	String prodAddToCart = "./following::i[@class=\"fas fa-shopping-cart\"]";
	
	String prodAddToWishList = "./following::i[@class=\"fas fa-heart\"]";
	
	String compareThisProduct = "./following::i[@class=\"fas fa-exchange-alt\"]";

	public ProductsListPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickonProduct(String product) {
		for (WebElement ptitle : prodTitles) {
			if (ptitle.getText().contains(product)) {
				click(ptitle);
				break;
			}
		}
	}

	public void getProductDescription(String product) {
		for (WebElement ptitle : prodTitles) {
			if (ptitle.getText().contains(product)) {
				String desc = ptitle.findElement(By.xpath(xpathForProdDesc)).getText();
				System.out.println(desc);
				break;
			}
		}
	}

	public void getProductPriceAndTax(String product) {
		for (WebElement ptitle : prodTitles) {
			if (ptitle.getText().contains(product)) {
				String price = ptitle.findElement(By.xpath(xpathForProdPrice)).getText();
				String tax = ptitle.findElement(By.xpath(xpathForProdTax)).getText().split(":")[1];

				System.out.println("Price of the product [" + product + "] is : " + price);
				System.out.println("Tax of the product   [" + product + "] is : " + tax);
				break;
			}
		}
	}

	public void addToCart(String product) {
		for (WebElement ptitle : prodTitles) {
			if (ptitle.getText().contains(product)) {
				ptitle.findElement(By.xpath(prodAddToCart)).click();
				break;
			}
		}
	}
	
	public void addToWishList(String product) {
		for (WebElement ptitle : prodTitles) {
			if (ptitle.getText().contains(product)) {
				ptitle.findElement(By.xpath(prodAddToWishList)).click();
				break;
			}
		}
	}
	
	public void compareThisProduct(String product) {
		for (WebElement ptitle : prodTitles) {
			if (ptitle.getText().contains(product)) {
				ptitle.findElement(By.xpath(compareThisProduct)).click();
				break;
			}
		}
	}

}
