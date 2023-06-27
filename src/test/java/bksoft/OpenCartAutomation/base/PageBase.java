package bksoft.OpenCartAutomation.base;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageBase {

	protected WebDriver driver;
	private Actions action;
	private JavascriptExecutor jsx;
	private WebDriverWait wait;
	private int fluentTimeoutinSec = 10;
	private int fluentPollinginMilli = 500;
	private int webDriverWaitinSec = 10;

	Logger log = LogManager.getLogger(PageBase.class.getName());

	public PageBase(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		jsx = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(webDriverWaitinSec));
	}
	
	protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void fluentWaitForElementToBeVisible(WebElement element) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(fluentTimeoutinSec))
                .pollingEvery(Duration.ofMillis(fluentPollinginMilli))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void fluentWaitForElementToBeClickable(WebElement element) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(fluentTimeoutinSec))
                .pollingEvery(Duration.ofMillis(fluentPollinginMilli))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }
	

	protected void click(WebElement element) {
		element.click();
	}

	protected void flashAndclick(WebElement element) {
		scrolToElement(element);
		flash(element);
		click(element);
	}

	protected void enterText(WebElement element, String keyword) {
		element.sendKeys(keyword);
		log.debug("Given text entered successful...");
	}

	protected void flash(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver; // downcasting

		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 5px red')", element);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);

	}

	protected void moveToElement(WebElement element) {
		flash(element);
		action.moveToElement(element).build().perform();
	}

	protected void scrolToElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

	}

	public void waitForPageLoad(int timeOut) {

		log.info("Waiting for page to load of milisecond : " + timeOut);
		long endTime = System.currentTimeMillis() + timeOut;

		while (System.currentTimeMillis() < endTime) {

			String pageState = jsx.executeScript("return document.readyState").toString();
			if (pageState.equals("complete")) {
				log.debug("page DOM is fully loaded now....");
				break;
			}
		}
	}

	public void navigateForward() {
		driver.navigate().forward();
		log.debug("Navigate forward successful...");
	}

	public void navigateBack() {
		driver.navigate().back();
		log.debug("Navigate back successful...");
	}

	public void reloadPage() {
		driver.navigate().refresh();
		log.debug("Page reload successful...");
	}

	public String getTitle() {
		String title = driver.getTitle();
		log.info("Title of the page is : [" + title + "]");
		return title;
	}
	
	public String getUrl() {
		String url = driver.getCurrentUrl();
		log.info("URL of the page is : [" + url + "]");
		return url;
	}
}
