package bksoft.OpenCartAutomation.testcases;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;

public class HomePageTest extends TestBase {
	HomePage hp;

	@BeforeMethod
	public void beforClassSetUp() {
		setUpDriver();
		hp = new HomePage(driver);
	}

	@AfterMethod
	public void afterClassTearDown() {
		tearDownDriver();
	}

	@DataProvider(name = "currencyData")
	private Object[][] getCurrencyData() {
		// Currencies
		return new Object[][] { { "euro" }, { "pound" }, { "dollar" } };
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
	public void testSelectCurrency(String currency) {
		String product = "iphone";

		hp.selectCurrency(currency);
		String price = hp.getFeaturedProductPrice(product);

		if (price.contains(getCurrencySymbol(currency))) {
			AssertJUnit.assertTrue("Price does not contain currency symbol", true);
		} else {
			AssertJUnit.assertFalse("Price contains currency symbol", false);
		}
	}

	@Test()
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
