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

	@FindBys({ @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul"), @FindBy(xpath = "./li/a") })
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
	}

	/*-------------------------Generic actions methods-----------------------------*/

	public void selectCurrency(String cr) {
		Boolean flag = false;
		log.info("Selecting currency");
		flashAndclick(currency);
		
		for (WebElement cur : currencyList) {
			if (cur.getText().toLowerCase().contains(cr)) {
				
				flashAndclick(cur);
				flag = true;
				break;
			}
		}
		if (!flag) {
			log.error("Currency entered not found!");
			throw new NoSuchElementException("Category : " + cr + " not found!");
		}
	}

	public void clickOnMyAccount() {
		log.info("clicking on My Account.");
		flashAndclick(myAccount);
	}

	public void clickOnRegister() {
		log.info("Clicking on register.");
		flashAndclick(register);
	}

	public void clickOnLogin() {
		log.info("Clicking on login.");
		flashAndclick(login);
	}

	public void clickOnWishList() {
		log.info("Clicking on Wish List.");
		flashAndclick(wishList);
	}

	public void clickOnShoppingCart() {
		log.info("Clicking on Shopping Cart.");
		flashAndclick(shoppingCart);
	}

	public void clickOnCheckout() {
		log.info("Clicking on Checkout.");
		flashAndclick(checkout);
	}

	public void clickOnLogo() {
		log.info("Clicking on logo.");
		flashAndclick(logo);
	}

	public void searchItem(String query) {
		log.info("Entering query to search bar.");
		searchBar.sendKeys(query);
		
		flashAndclick(searchBtn);
		log.info("Flashed and clicked on search button.");
	}

	public void clickOnCartItems() {
		flashAndclick(items);
		log.info("Flash and clicked on cart item : "+items);
	}

	public void clickOnCategory(String cat) {
		Boolean flag = false;
		log.info("Clicking on product category : "+cat);
		
		for (WebElement category : productCategories) {
			if (category.getText().equalsIgnoreCase(cat)) {
				flashAndclick(category);
				flag = true;
				break;
			}
		}
		if (!flag) {
			log.error("Category entered not found!");
			throw new NoSuchElementException("Category : " + cat + " not found!");
		}

	}

	public void hoverOverCategory(String cat) {
		Boolean flag = false;
		log.info("Hovering on category : "+cat);
		
		for (WebElement category : productCategories) {
			if (category.getText().toLowerCase().contains(cat)) {

				subCategories = category.findElement(By.xpath("./following::ul[1]"))
						.findElements(By.tagName("li"));
				moveToElement(category);
				flag = true;
				break;
			}
		}
		if (!flag) {
			log.error("Category not found!");
			throw new NoSuchElementException("Category : " + cat + " not found!");
		}
	}

	public void clickOnSubCategory(String subCat) {
		Boolean flag = false;
		log.info("Clicking on sub category : "+subCat);
		
		for (WebElement subElement : subCategories) {
			if (subElement.getText().toLowerCase().contains(subCat)) {
				flashAndclick(subElement);
				flag = true;
				break;
			}
		}
		if (!flag) {
			log.error("SubCategory not found!");
			throw new NoSuchElementException("Subcategory : " + subCat + " not found!");
		}
	}

	public void clickOnFeaturedProductTitle(String fprod) {
		Boolean flag = false;
		log.info("Clicking on featured product.");
		
		for (WebElement featured : featuredProductTitles) {
			System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {

				flashAndclick(featured);
				flag = true;
				break;
			}
		}
		if (!flag) {
			log.error("Featured product not found!");
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public String getFeaturedProductProductTitle(String fprod) {
		Boolean flag = false;
		String fprodTitle = null;
		log.info("Getting titles of featured product : "+fprod);
		
		for (WebElement featured : featuredProductTitles) {
			System.out.println(featured.getText());
			if (featured.getText().toLowerCase().contains(fprod)) {

				fprodTitle = featured.getText();
				flag = true;
				break;
			}
		}
		if (!flag) {
			log.error("Featured product not found!");
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
		return fprodTitle;
	}

	public String getFeaturedProductDescription(String fprod) {
		String description = null;
		Boolean flag = false;
		log.info("Getting description of featured product : "+fprod);
		
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
			log.error("Featured product not found!");
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
		return description;
	}

	public String getFeaturedProductPrice(String fprod) {
		String price = null;
		Boolean flag = false;
		log.info("Getting the price of featured prouduct : "+fprod);
		
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
			log.error("Featured product not found!");
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}

		return price;
	}

	public void clickOnFeaturedProductAddToCart(String fprod) {
		Boolean flag = false;
		log.info("Clikcing on featured product add to cart.");
		
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
			log.error("Featured product not found!");
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public void clickOnFeaturedProductAddToWishList(String fprod) {
		Boolean flag = false;
		log.info("Clicking on featured product add to wish list.");
		
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
			log.error("Featured product not found!");
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public void clickOnFeaturedProductCompareThisProduct(String fprod) {
		Boolean flag = false;
		log.info("Click on Featured product compare this product.");
		
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
			log.error("Featured product not found!");
			throw new NoSuchElementException("Product name : " + fprod + " not found!");
		}
	}

	public void getHyperLinks(String heading) {
		Boolean flag = false;
		log.info("Getting the hyperlinks.");
		
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
			log.error("Hyperlink heading not found!");
			throw new NoSuchElementException("Hyperlink heading : " + heading + " not found!");
		}
	}

	public void clickOnHyperLink(String heading, String hyperLink) {
		Boolean flag = false;
		
		getHyperLinks(heading);
		log.info("Clicking on hyperlink.");
		
		for (WebElement link : hyperLinks) {
			System.out.println(link.getText());
			if (link.getText().toLowerCase().contains(hyperLink)) {
				flashAndclick(link);
				flag = true;
				break;
			}

		}
		if (!flag) {
			log.error("Hyperlink heading not found!");
			throw new NoSuchElementException("Hyperlink : " + heading + " not found!");
		}
	}

}
