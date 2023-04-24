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

	public void click(WebElement element) {
		element.click();
		log.debug("Clicked successful...");
	}

	public void flashAndclick(WebElement element) {
		flash(element);
		click(element);
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
		
		log.debug("Flashed successful...");
	}

	public void moveToElement(WebElement element) {
		flash(element);
		action.moveToElement(element).build().perform();
	}

	public void scrolToElement(WebElement element) {

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
	}
	
	public void navigateBack() {
		driver.navigate().back();
	}
	
	public void reloadPage() {
		driver.navigate().refresh();
	}
}
