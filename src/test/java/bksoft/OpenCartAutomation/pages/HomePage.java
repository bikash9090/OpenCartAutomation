package bksoft.OpenCartAutomation.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import bksoft.OpenCartAutomation.base.PageBase;

public class HomePage extends PageBase {

	Logger log = LogManager.getLogger(HomePage.class.getName());

	WebDriver driver;

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

	private List<WebElement> subCategories;

	@FindBys({ @FindBy(xpath = "//*[@id=\"content\"]/div[2]"),
			@FindBy(xpath = "./descendant::div[@class=\"description\"]"), @FindBy(xpath = "./child::h4/a") })
	private List<WebElement> featuredProductTitles;

	@FindBys({ @FindBy(tagName = "footer"), @FindBy(xpath = "./descendant::div[@class=\"col-sm-3\"]/h5")

	})
	private List<WebElement> hyperLinkHeadings;

	private List<WebElement> hyperLinks;

	/*------------------------------Constructor----------------------------------*/

	public HomePage(WebDriver driver) {
		super(driver);

		PageFactory.initElements(driver, this);
		log.info("HomePage object created.");

	}

	/*-------------------------Generic actions methods-----------------------------*/

	public void selectCurrency(String cr) {
		Boolean flag = false;
		flashAndclick(currency);
		for (WebElement cur : currencyList) {
			if (cur.getText().toLowerCase().contains(cr)) {
				flashAndclick(cur);
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new NoSuchElementException("Category : " + cr + " not found!");
		}
	}

	public void clickOnMyAccount() {
		flashAndclick(myAccount);
	}

	public void clickOnRegister() {
		flashAndclick(register);
	}

	public void clickOnLogin() {
		flashAndclick(login);
	}

	public void clickOnWishList() {
		flashAndclick(wishList);
	}

	public void clickOnShoppingCart() {
		flashAndclick(shoppingCart);
	}

	public void clickOnCheckout() {
		flashAndclick(checkout);
	}

	public void clickOnLogo() {
		flashAndclick(logo);
	}

	public void searchItem(String query) {
		searchBar.sendKeys(query);
		flashAndclick(searchBtn);
	}

	public void clickOnCartItems() {
		flashAndclick(items);
	}

	public void clickOnCategory(String cat) {
		Boolean flag = false;
		for (WebElement category : productCategories) {
			if (category.getText().equalsIgnoreCase(cat)) {
				flashAndclick(category);
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new NoSuchElementException("Category : " + cat + " not found!");
		}

	}

	public void hoverOverCategory(String cat) {
		Boolean flag = false;
		for (WebElement category : productCategories) {
			System.out.println(category.getText());
			if (category.getText().toLowerCase().contains(cat)) {

				subCategories = category.findElement(By.xpath("./following-sibling::div[1]"))
						.findElements(By.tagName("li"));
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new NoSuchElementException("Category : " + cat + " not found!");
		}
	}

	public void clickOnSubCategory(String subCat) {
		Boolean flag = false;
		for (WebElement subElement : subCategories) {
			if (subElement.getText().toLowerCase().contains(subCat)) {
				flashAndclick(subElement);
				break;
			}
		}
		if (!flag) {
			throw new NoSuchElementException("Subcategory : " + subCat + " not found!");
		}
	}

	public void clickOnFeaturedProductTitle(String fprod) {
		Boolean flag = false;
		for (WebElement featured : featuredProductTitles) {
			System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {

				flashAndclick(featured);
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public String getFeaturedProductProductTitle(String fprod) {
		Boolean flag = false;
		String fprodTitle = null;
		for (WebElement featured : featuredProductTitles) {
			System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {

				fprodTitle = featured.getText();
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
		return fprodTitle;
	}

	public String getFeaturedProductDescription(String fprod) {
		String description = null;
		Boolean flag = false;
		for (WebElement featured : featuredProductTitles) {
			scrolToElement(featured);
			// System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {
				flash(featured.findElement(By.xpath("./following::p[1]")));
				String des = featured.findElement(By.xpath("./following::p[1]")).getText();
				System.out.println(des);
				flag = true;
				break;
			}
		}
		if (!flag) {
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
		return description;
	}

	public String getFeaturedProductPrice(String fprod) {
		String price = null;
		Boolean flag = false;
		for (WebElement featured : featuredProductTitles) {
			scrolToElement(featured);
			// System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {
				flash(featured
						.findElement(By.xpath("./following::div[@class='price']/child::span[@class=\"price-new\"]")));
				price = featured
						.findElement(By.xpath("./following::div[@class='price']/child::span[@class=\"price-new\"]"))
						.getText();
				System.out.println(price);
				flag = true;
				break;
			}

		}
		if (!flag) {
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}

		return price;
	}

	public void clickOnFeaturedProductAddToCart(String fprod) {
		Boolean flag = false;
		for (WebElement featured : featuredProductTitles) {
			scrolToElement(featured);
			// System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {

				flashAndclick(featured.findElement(By.xpath(
						"./following::div[@class=\"button-group\"]/child::button[@aria-label=\"Add to Cart\"]")));
				flag = true;
				break;
			}

		}
		if (!flag) {
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public void clickOnFeaturedProductAddToWishList(String fprod) {
		Boolean flag = false;
		for (WebElement featured : featuredProductTitles) {
			scrolToElement(featured);
			// System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {

				flashAndclick(featured.findElement(By.xpath(
						"./following::div[@class=\"button-group\"]/child::button[@aria-label=\"Add to Wish List\"]")));
				flag = true;
				break;
			}

		}
		if (!flag) {
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public void clickOnFeaturedProductCompareThisProduct(String fprod) {
		Boolean flag = false;
		for (WebElement featured : featuredProductTitles) {
			scrolToElement(featured);
			// System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {

				flashAndclick(featured.findElement(By.xpath(
						"./following::div[@class=\"button-group\"]/child::button[@aria-label=\"Compare this Product\"]")));
				flag = true;
				break;
			}

		}
		if (!flag) {
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public void getHyperLinks(String heading) {
		Boolean flag = false;
		for (WebElement hyperHeading : hyperLinkHeadings) {
			scrolToElement(hyperHeading);
			if (hyperHeading.getText().toLowerCase().contains(heading)) {
				flash(hyperHeading);
				hyperLinks = hyperHeading.findElement(By.xpath("./following-sibling::ul"))
						.findElements(By.tagName("a"));
				System.out.println(hyperHeading.getText());
				flag = true;
				break;
			}

		}
		if (!flag) {
			throw new NoSuchElementException("Hyperlink heading : " + heading + " not found!");
		}
	}

	public void clickOnHyperLink(String heading, String hyperLink) {
		Boolean flag = false;
		getHyperLinks(heading);
		for (WebElement link : hyperLinks) {
			System.out.println(link.getText());
			if (link.getText().toLowerCase().contains(hyperLink)) {
				flashAndclick(link);
				flag = true;
				break;
			}

		}
		if (!flag) {
			throw new NoSuchElementException("Hyperlink : " + heading + " not found!");
		}
	}

}
