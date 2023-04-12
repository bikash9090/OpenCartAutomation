package bksoft.OpenCartAutomation.testcases;

import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;

public class HomePageTest extends TestBase {
	HomePage hp;

	@Test()
	public void validatingHomepageLocatorsFunctionality() throws InterruptedException {
		hp = new HomePage(driver);

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
