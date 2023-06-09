package bksoft.OpenCartAutomation.testcases;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;
import bksoft.OpenCartAutomation.pages.ProductsListPage;

public class HomePageTest extends TestBase {
	HomePage hp;
	ProductsListPage prodlist;
	Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeClass
	public void initialization() {
		log.info("Initializing driver object.");
		setUpDriver();
		hp = new HomePage(driver);
		prodlist = new ProductsListPage(driver);
	}

	@AfterClass
	public void tearDown() {
		log.info("Destroying driver object.");
		tearDownDriver();
	}

	@DataProvider
	private String[][] currencyData() {
		return new String[][] { { "Euro", "€" }, { "Pound", "£" }, { "Dollar", "$" } };
	}

	@Test(testName ="TC-home-008",description = "Verify the currency list functionality.", dataProvider = "currencyData")
	public void verifyCurrencyList(String currency, String currencySymbol) {

		String product = "macbook";

		log.info("TC_008_select_currency_test.");
		hp.selectCurrency(currency);

		String price = hp.getFeaturedProductPrice(product);

		log.info("Asserting currency selection.");
		assertTrue(price.contains(currencySymbol));
	}

	@Test(testName = "TC-home-009",description = "Verify by clicking on My Account and its sub options.")
	public void verifyMyAccountList() {
		
		log.info("TC-home-009_VerifyMyAccount test.");
		hp.clickOnMyAccount();
		hp.clickOnLogin();
		
		log.info("Asserting VerifyMyAccount test.");
		assertTrue(hp.getTitle().equalsIgnoreCase("Account Login"));
		hp.navigateBack();
	}

}
