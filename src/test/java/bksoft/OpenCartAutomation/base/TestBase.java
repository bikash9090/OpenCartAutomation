package bksoft.OpenCartAutomation.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import bksoft.OpenCartAutomation.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	PropertiesUtil readconfig = new PropertiesUtil();
	public WebDriver driver;
	
	public void setUpDriver() {
		
		WebDriverManager.chromedriver().setup();

		if (readconfig.getBrowser().equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (readconfig.getBrowser().equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (readconfig.getBrowser().equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		driver.get(readconfig.getUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
	}
	
	public void tearDownDriver() {
		driver.quit();
	}
}
