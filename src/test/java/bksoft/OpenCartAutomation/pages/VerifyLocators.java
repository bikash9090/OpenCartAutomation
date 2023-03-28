package bksoft.OpenCartAutomation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyLocators {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		String url = "https://demo.opencart.com/";

		WebDriverManager.firefoxdriver().setup();
		//EdgeOptions eo = new EdgeOptions();

		driver = new FirefoxDriver();

		driver.get(url);
		driver.manage().window().maximize();
//-----------------------------------------------------------------------------//

		HomePage hp = new HomePage();
		
		hp.currency.click();
		List<WebElement> li = hp.getCurrencyList();
		for(WebElement el:li) {
			System.out.println(el.getText());
		}

//-----------------------------------------------------------------------------//
		Thread.sleep(4000);
		driver.quit();

	}
}
