package bksoft.OpenCartAutomation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import bksoft.OpenCartAutomation.utils.PageActionUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyLocators extends PageActionUtil{
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		String url = "https://demo.opencart.com/";

		WebDriverManager.edgedriver().setup();
		//EdgeOptions eo = new EdgeOptions();

		driver = new EdgeDriver();

		driver.get(url);
		driver.manage().window().maximize();
//-----------------------------------------------------------------------------//

		HomePage hp = new HomePage(driver);
		
		List<WebElement> li = hp.getProductCategories();
		for(WebElement el:li) {
			System.out.println(el.getText());
		}

//-----------------------------------------------------------------------------//
		Thread.sleep(4000);
		driver.quit();

	}
}
