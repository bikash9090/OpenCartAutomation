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

public class HomePageTest extends TestBase {
	HomePage hp;
	Logger log = LogManager.getLogger(HomePageTest.class.getName());

	@BeforeClass
	public void initialization() {
		setUpDriver();
		hp = new HomePage(driver);
	}

	@AfterClass
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
		System.out.println(currency);

		Assert.assertTrue(price.contains(getCurrencySymbol(currency)));
	}

	// All category name on HomePage.
	@DataProvider(name = "categoriesData")
	public String[][] categories() {
		return new String[][] { { "Tablets" }, { "Software" }, { "Phones & PDAs" }, { "Cameras" } };
	}

	@Test(dataProvider = "categoriesData")
	public void verifyCategoryPages(String cat) throws InterruptedException {

		
		hp.clickOnCategory(cat);
		hp.waitForPageLoad(1000);
		hp.navigateBack();

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

	@Test(dataProvider = "subCategoriesData")
	public void verifySubCategoryPages(String category, String subcategory) {
		hp.hoverOverCategory(category);
		hp.clickOnSubCategory(subcategory);
		//hp.navigateBack();
	}

	// { "MP3 Players" },
	
	@DataProvider(name = "featuredProductTitlesData")
	public String[][] featuredProductTitles() {
		return new String[][] { { "MacBook" },{ "iphone" },{ "Apple Cinema" },{ "Canon EOS 5D" }, };
		
	}

	@Test(dataProvider = "featuredProductTitlesData")
	public void featuredProductDetails(String title) throws InterruptedException {
		hp.clickOnFeaturedProductTitle(title);
		hp.navigateBack();
		Thread.sleep(1000);
		hp.reloadPage();
		Thread.sleep(1000);
	}
	
	
	
	@Test()
	public void validatingHomepageLocatorsFunctionality() throws InterruptedException {

		// hp.selectCurrency("dollar");
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
		hp.hoverOverCategory("Desktops");
		// hp.clickOnSubCategory("printers");
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
