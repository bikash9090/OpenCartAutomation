package bksoft.OpenCartAutomation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bksoft.OpenCartAutomation.utils.PageActionsUtil;

public class PageBase {

	protected WebDriver driver;
	PageActionsUtil pageUtil;

	public PageBase(WebDriver driver) {
		this.driver = driver;
		pageUtil = new PageActionsUtil(driver);
	}

	public void click(WebElement element) {
		element.click();
	}

	public void flashAndclick(WebElement element) {
		pageUtil.flash(element);
		element.click();
	}

	public void flash(WebElement element) {
		pageUtil.flash(element);
	}
}
