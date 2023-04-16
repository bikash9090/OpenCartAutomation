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
	
	Logger log = LogManager.getLogger(PageBase.class.getName());

	public PageBase(WebDriver driver) {
		this.driver = driver;		
		action = new Actions(driver);
	}

	public void click(WebElement element) {
		element.click();
		log.debug("Performed click action.");
	}

	public void flashAndclick(WebElement element) {
		flash(element);
		element.click();
		log.debug("Performed flash and click action.");
	}

	public void flash(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver; // downcasting

		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: solid 5px red')", element);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
		log.debug("Flashed on the given element.");
	}

	public void moveToElement(WebElement element) {
		flash(element);
		action.moveToElement(element).build().perform();
		log.debug("Moved to given element.");
	}

	public void scrolToElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
		log.debug("Scrolled to given element.");

	}
}
