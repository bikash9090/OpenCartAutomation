package bksoft.OpenCartAutomation.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import bksoft.OpenCartAutomation.utils.PageActionsUtil;

public class PageBase {

	protected WebDriver driver;
	PageActionsUtil pageUtil;
	Actions action;
	JavascriptExecutor jsx;

	Logger log = LogManager.getLogger(PageBase.class.getName());

	public PageBase(WebDriver driver) {
		this.driver = driver;		
		action = new Actions(driver);
		jsx = (JavascriptExecutor) driver;
	}

	protected void click(WebElement element) {
		element.click();
		log.debug("Clicked successful...");
	}

	protected void flashAndclick(WebElement element) {
		flash(element);
		click(element);
	}
	
	protected void enterText(WebElement	element,String keyword) {
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
		
		log.debug("Flashed successful...");
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

		long endTime = System.currentTimeMillis() + timeOut;

		while (System.currentTimeMillis() < endTime) {

			String pageState = jsx.executeScript("return document.readyState").toString();
			if (pageState.equals("complete")) {
				System.out.println("page DOM is fully loaded now.....");
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
}
