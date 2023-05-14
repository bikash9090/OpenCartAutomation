package bksoft.OpenCartAutomation.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;
import bksoft.OpenCartAutomation.pages.ProductsListPage;

public class HomePageTest extends TestBase {
	HomePage hp;
	ProductsListPage pobj;
	Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeClass
	public void initialization() {
		log.info("Initializing driver object.");
		setUpDriver();
		hp = new HomePage(driver);
		pobj = new ProductsListPage(driver);
	}

	@AfterClass
	public void tearDown() {
		log.info("Destroying driver object.");
		tearDownDriver();
	}

	// All currencies on HomePage.
	@DataProvider(name = "currencyData")
	private String[][] getCurrencyData() {
		// Currencies
		return new String[][] { { "Euro", "€" }, { "Pound", "£" }, { "Dollar", "$" } };
	}

	@Test(dataProvider = "currencyData", priority = 1)
	public void selectCurrencyTest(String currency, String currencySymbol) {
		log.info("TC_001_SELECT CURRENCY TEST.");
		String product = "macbook";

		hp.selectCurrency(currency);
		String price = hp.getFeaturedProductPrice(product);
		
		log.info("Asserting currency selection.");
		Assert.assertTrue(price.contains(currencySymbol));

	}

	// All category name on HomePage.
	@DataProvider(name = "categoriesData")
	public String[][] categories() {
		return new String[][] { { "Tablets" }, { "Software" }, { "Phones & PDAs" }, { "Cameras" } };
	}

	@Test(dataProvider = "categoriesData", priority = 2)
	public void verifyCategoryPages(String cat) throws InterruptedException {
		log.info("TC_002 VERIFY CATEGORY PAGES TEST.");

		hp.clickOnCategory(cat);
		hp.waitForPageLoad(1000);
		hp.navigateBack();
		hp.reloadPage();

	}

	@DataProvider(name = "subCategoriesData")
	public String[][] subCategories() {
		return new String[][] { { "Desktops", "PC" }, { "Desktops", "Mac" }, { "Laptops & Notebooks", "Macs" },
				{ "Laptops & Notebooks", "Windows" }, { "Components", "Mice and Trackballs" },
				{ "Components", "Monitors" }, { "Components", "Printers" }, { "Components", "Scanners" },
				{ "Components", "Web Cameras" }, { "MP3 Players", "test 11" }, { "MP3 Players", "test 12" },
				{ "MP3 Players", "test 15" }, { "MP3 Players", "test 16" }, { "MP3 Players", "test 17" },
				{ "MP3 Players", "test 18" }, { "MP3 Players", "test 19" }, { "MP3 Players", "test 20" },
				{ "MP3 Players", "test 21" }, { "MP3 Players", "test 22" }, { "MP3 Players", "test 23" },
				{ "MP3 Players", "test 24" }, { "MP3 Players", "test 4" }, { "MP3 Players", "test 5" },
				{ "MP3 Players", "test 6" }, { "MP3 Players", "test 7" }, { "MP3 Players", "test 8" },
				{ "MP3 Players", "test 9" }, };
	}

	@Test(dataProvider = "subCategoriesData", priority = 3)
	public void verifySubCategoryPages(String category, String subcategory) {
		log.info("TC_003 VERIFY SUBCATEGORY PAGES TEST.");
		hp.hoverOverCategory(category);
		hp.clickOnSubCategory(subcategory);
		hp.navigateBack();
		hp.reloadPage();
	}

	// { "MP3 Players" },

	@DataProvider(name = "featuredProductTitlesData")
	public String[][] featuredProductTitles() {
		return new String[][] { { "MacBook" }, { "iPhone" }, { "Apple Cinema" }, { "Canon EOS 5D" }, };

	}

	@Test(dataProvider = "featuredProductTitlesData", priority = 4)
	public void featuredProductDetails(String title) throws InterruptedException {
		log.info("TC_004 VERIFY PRODUCT DETAILS TEST.");
		hp.clickOnFeaturedProductTitle(title);
		hp.navigateBack();
		Thread.sleep(1000);
		hp.reloadPage();
		Thread.sleep(1000);
	}

	@DataProvider(name = "hyperlinkdata")
	public String[][] hyperLinks() {
		return new String[][] { { "Information", "Terms & Conditions" }, { "Information", "Delivery Information" },
				{ "Information", "2About Us" }, { "Information", "Privacy Policy" }, };
	}

	@Test(dataProvider = "hyperlinkdata",priority = 5)
	public void verifyHyperLinkPage(String heading, String hyperlink) {
		log.info("TC_005 VERIFY HYPERLINK PAGES TEST.");
		hp.clickOnHyperLink(heading, hyperlink);
		hp.navigateBack();
	}

	@Test()
	public void validatingHomepageLocatorsFunctionality() throws InterruptedException {

		 hp.selectCurrency("Pound");
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
		// hp.hoverOverCategory("Desktops");
		// hp.clickOnSubCategory("printers");
		// hp.clickOnFeaturedProductTitle("canon eos");
		// hp.getFeaturedProductProductTitle("canon eos");
		// hp.getFeaturedProductDescription("canon eos");
		// hp.getFeaturedProductPrice("canon eos");
		// hp.clickOnFeaturedProductAddToCart("iphone");
		// hp.clickOnFeaturedProductAddToWishList("iphone");
		// hp.clickOnFeaturedProductCompareThisProduct("macbook");
		// hp.clickOnHyperLink("extras","brands");

		hp.clickOnCategory("Cameras");
		//pobj.getProductDescription("HTC Touch HD");
		pobj.getProductPriceAndTax("Canon EOS 5D");
		pobj.compareThisProduct("Canon EOS 5D");
		
		
		
		Thread.sleep(3000);
	}
}
