package bksoft.OpenCartAutomation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import bksoft.OpenCartAutomation.utils.PageActionsUtil;

public class HomePage extends PageActionsUtil {

	WebDriver driver;
	Actions action;

	/*------------------------------Locators-----------------------------------*/

	@FindBy(xpath = "//*[@id=\"form-currency\"]/div/a/span")
	private WebElement currency;

	@FindBys({ @FindBy(xpath = "//ul[@data-popper-placement=\"bottom-start\"]"), @FindBy(tagName = "li") })
	private List<WebElement> currencyList;

	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[1]/span")
	private WebElement phone;

	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[2]/div/a/span")
	private WebElement myAccount;

	@FindBy(linkText = "Register")
	private WebElement register;

	@FindBy(linkText = "Login")
	private WebElement login;

	@FindBy(xpath = "//*[@id=\"wishlist-total\"]/span")
	private WebElement wishList;

	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[4]/a/span")
	private WebElement shoppingCart;

	@FindBy(xpath = "//*[@id=\"top\"]/div[2]/div[2]/ul/li[5]/a/span")
	private WebElement checkout;

	@FindBy(xpath = "//*[@id=\"logo\"]/a/img")
	private WebElement logo;

	@FindBy(xpath = "//input[@type=\"text\" and @name=\"search\"]")
	private WebElement searchBar;

	@FindBy(xpath = "//*[@id=\"search\"]/button")
	private WebElement searchBtn;

	@FindBy(xpath = "//*[@id=\"header-cart\"]/div/button")
	private WebElement items;

	@FindBys({ @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul"), @FindBy(xpath = "./li/*[1]") })
	private List<WebElement> productCategories;

	// WebElement foundCategory;
	// WebElement subCategory =
	// foundCategory.findElement(By.xpath("./following-sibling::div[1]"));
	List<WebElement> subcategoryElements;

	private List<WebElement> subCategories;

	@FindBys({ @FindBy(xpath = "//*[@id=\"content\"]/div[2]"),
			@FindBy(xpath = "./descendant::div[@class=\"description\"]"), @FindBy(xpath = "./child::h4") })
	private List<WebElement> featuredProductTitles;
	
	@FindBy(how = How.LINK_TEXT, using = "")
	private WebElement link;

	/*------------------------------Constructor----------------------------------*/

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
	}

	/*-----------------------Lazy initialization methods--------------------------*/
	public void clickByhref(WebElement element) {
		this.link = element;
	}

	/*-------------------------Generic actions methods-----------------------------*/

	public void selectCurrency(String cr) {
		currency.click();
		for (WebElement cur : currencyList) {
			if (cur.getText().toLowerCase().contains(cr)) {
				cur.click();
				break;
			}
		}
	}

	public void clickOnMyAccount() {
		myAccount.click();
	}

	public void clickOnRegister() {
		register.click();
	}

	public void clickOnLogin() {
		login.click();
	}

	public void clickOnWishList() {
		wishList.click();
	}

	public void clickOnShoppingCart() {
		shoppingCart.click();
	}

	public void clickOnCheckout() {
		checkout.click();
	}

	public void clickOnLogo() {
		logo.click();
	}

	public void searchItem(String query) {
		searchBar.sendKeys(query);
		searchBtn.click();
	}

	public void clickOnCartItems() {
		items.click();
	}

	public void clickOnCategory(String cat) {
		for (WebElement category : productCategories) {
			if (category.getText().equalsIgnoreCase(cat)) {
				category.click();
				break;
			}
		}
	}

	public void hoverOverCategory(String cat) {
		for (WebElement category : productCategories) {
			System.out.println(category.getText());
			if (category.getText().toLowerCase().contains(cat)) {

				action.moveToElement(category).build().perform();
				subCategories = category.findElement(By.xpath("./following-sibling::div[1]"))
						.findElements(By.tagName("li"));
				break;
			}
		}
	}

	public void clickOnSubCategory(String subCat) {
		for (WebElement subElement : subCategories) {
			if (subElement.getText().toLowerCase().contains(subCat)) {
				subElement.click();
				break;
			}
		}
	}

	public void clickOnFeaturedProduct(String fprod) {
		for (WebElement featured : featuredProductTitles) {
			System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {
				clickByhref(featured);
				break;
			}
		}
	}

}
