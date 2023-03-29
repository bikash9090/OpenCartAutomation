package bksoft.OpenCartAutomation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import bksoft.OpenCartAutomation.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	PropertiesUtil readconfig = new PropertiesUtil();
	static WebDriver driver;

	@BeforeMethod
	public void setUp() {

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
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
