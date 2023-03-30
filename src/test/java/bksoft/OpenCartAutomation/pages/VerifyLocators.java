package bksoft.OpenCartAutomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import bksoft.OpenCartAutomation.utils.PageActionsUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyLocators extends PageActionsUtil{
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		String url = "https://demo.opencart.com/";

		WebDriverManager.edgedriver().setup();
		//EdgeOptions eo = new EdgeOptions();

		driver = new EdgeDriver();

		driver.get(url);
		driver.manage().window().maximize();
//-----------------------------------------------------------------------------//

	
	

//-----------------------------------------------------------------------------//
		Thread.sleep(4000);
		driver.quit();

	}
}
