package bksoft.OpenCartAutomation.testcases;

import org.testng.annotations.Test;

import bksoft.OpenCartAutomation.base.TestBase;
import bksoft.OpenCartAutomation.pages.HomePage;

public class HomePageTest extends TestBase {
	HomePage hp;

	@Test
	public void validatingHomepageLocatorsFunctionality() throws InterruptedException {
		hp = new HomePage(driver);

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
		// hp.clickOnCategory("desktops");
		 hp.hoverOverCategory("components");
		 hp.clickOnSubCategory("printers");
		//hp.clickOnFeaturedProduct("MacBook");

		Thread.sleep(6000);
	}

}
