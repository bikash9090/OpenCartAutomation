package bksoft.OpenCartAutomation.testcases;

import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;
import bksoft.OpenCartAutomation.pages.ProductsListPage;

public class HomePageTest extends TestBase {
	HomePage hp;
	ProductsListPage prodlist;
	Logger log = LogManager.getLogger(HomePageTest.class.getName());

	/*-----------------------------------------------BeforClass initialization-----------------------------------------------------------*/
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

	/*-------------------------------------------------Home page test cases-------------------------------------------------------------*/

	@DataProvider
	private String[][] currencyData() {
		return new String[][] { { "Euro", "€" }, { "Pound", "£" }, { "Dollar", "$" } };
	}

	@Test(testName = "TC-home-008", description = "Verify the currency list functionality.", dataProvider = "currencyData", priority = 1)
	public void verifyCurrencyList(String currency, String currencySymbol) {

		String product = "macbook";

		log.info("TC-home-008");
		hp.selectCurrency(currency);

		String price = hp.getFeaturedProductPrice(product);

		log.info("Asserting currency selection.");
		assertTrue(price.contains(currencySymbol));
	}

	@Test(testName = "TC-home-009", description = "Verify by clicking on My Account and its sub options.", priority = 2)
	public void verifyMyAccountList() {

		log.info("TC-home-009");
		hp.clickOnMyAccount();
		hp.clickOnLogin();

		log.info("Asserting Login page.");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(hp.getTitle(), "Account 2Login");

		hp.clickOnMyAccount();
		hp.clickOnRegister();

		log.info("Soft Asserting verifyMyAccount");
		softAssert.assertEquals(hp.getTitle(), "Register 2Account");
		softAssert.assertAll();
		hp.navigateBack();

	}

	@Test(testName = "TC-home-011", description = "Verify by clicking on logo.", priority = 3)
	public void clickAndVerifyLogo() {

		log.info("TC-home-011");
		hp.clickOnLogo();

		log.info("Asserting clicking on logo.");
		Assert.assertEquals(hp.getTitle(), "Your Store");
	}

	@DataProvider
	private String[][] fprodData() {
		return new String[][] { { "MacBook" }, { "iPhone" }, { "Apple Cinema 30" }, { "Canon EOS 5D" } };
	}

	@Test(priority = 4, dataProvider = "fprodData", testName = "TC-home-015", description = "Verify by clicking on the image or the titles of featured products.")
	public void clickAndVerifyFeaturedProducts(String prodName) {
		
		log.info("TC-home-015");
		hp.clickOnFeaturedProductTitle(prodName);

		log.info("Asserting clickAndVerifyFeaturedProducts.");
		Assert.assertTrue(hp.getTitle().contains(prodName));

		hp.navigateBack();
	}
}
