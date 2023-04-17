package bksoft.OpenCartAutomation.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;

public class HomePageTest extends TestBase {
	HomePage hp;

	@BeforeMethod
	public void initialization() {
		setUpDriver();
		hp = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		tearDownDriver();
	}

	// All currencies on HomePage.
	@DataProvider(name = "currencyData")
	private String[][] getCurrencyData() {
		// Currencies
		return new String[][] { { "euro" }, { "pound" }, { "dollar" } };
	}

	private String getCurrencySymbol(String currency) {
		// Returns the currency symbol based on currency option
		switch (currency) {
		case "pound":
			return "£";
		case "euro":
			return "€";
		case "dollar":
			return "$";
		default:
			throw new IllegalArgumentException("Invalid currency option: " + currency);
		}
	}

	@Test(dataProvider = "currencyData")
	public void selectCurrencyTest(String currency) {
		String product = "macbook";

		hp.selectCurrency(currency);
		String price = hp.getFeaturedProductPrice(product);

		Assert.assertTrue(price.contains(getCurrencySymbol(currency)));
	}

	// All category name on HomePage.
	@DataProvider(name = "categories")
	public String[][] categoryData() {
		return new String[][] { { "Tablets" }, { "Software" }, { "Phones & PDAs" }, { "Cameras" } };
	}

	@Test(dataProvider = "categories")
	public void verifyCategoryPages(String cat) throws InterruptedException {

		hp.clickOnCategory(cat);
		hp.waitForPageLoad(1000);
		driver.navigate().back();

	}

	@Test(enabled = false)
	public void validatingHomepageLocatorsFunctionality() throws InterruptedException {

		hp.selectCurrency("dollar");
		// hp.clickOnMyAccount();
		// hp.clickOnRegister();
		// hp.clickOnLogin();
		// hp.clickOnWishList();
		// hp.clickOnShoppingCart();
		// hp.clickOnCheckout();
		// hp.clickOnLogo();
		// hp.searchItem("iPhone 13");
		// hp.clickOnCartItems();
		// hp.clickOnCategory("4");
		hp.hoverOverCategory("components");
		hp.clickOnSubCategory("printers");
		// hp.clickOnFeaturedProductTitle("canon eos");
		// hp.getFeaturedProductProductTitle("canon eos");
		// hp.getFeaturedProductDescription("canon eos");
		// hp.getFeaturedProductPrice("canon eos");
		// hp.clickOnFeaturedProductAddToCart("iphone");
		// hp.clickOnFeaturedProductAddToWishList("iphone");
		// hp.clickOnFeaturedProductCompareThisProduct("macbook");
		// hp.clickOnHyperLink("extras","brands");

		Thread.sleep(3000);
	}
}
