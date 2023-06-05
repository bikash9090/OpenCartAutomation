package bksoft.OpenCartAutomation.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import bksoft.OpenCartAutomation.utils.PropertiesUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private PropertiesUtil readconfig = new PropertiesUtil();
	private static final DriverFactory instance = new DriverFactory();

	private DriverFactory() {
	}

	public static DriverFactory getDriverInstance() {
		return instance;
	}

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initializeDriver() throws IOException {

		WebDriver driver = null;
		if (tlDriver.get() == null) {

			if (readconfig.getBrowser().equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions ffOptions = setFFOptions();
				driver = new FirefoxDriver(ffOptions);
				tlDriver.set(driver);

			} else if (readconfig.getBrowser().equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = setChromeOptions();
				driver = new ChromeDriver(chromeOptions);
				tlDriver.set(driver);

			} else if (readconfig.getBrowser().equalsIgnoreCase("edge")) {

				WebDriverManager.edgedriver().setup();
				EdgeOptions edgeopt = setEdgeOptions();
				driver = new EdgeDriver(edgeopt);
				tlDriver.set(driver);
			}
		}
		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public void quitDriver() {

		getDriver().quit();
		tlDriver.set(null);
	}

	private ChromeOptions setChromeOptions() {

		ChromeOptions options = new ChromeOptions();

		if (readconfig.getHeadless().equalsIgnoreCase("true")) {
			options.addArguments("--headless");
		} else if (readconfig.getIcognito().equalsIgnoreCase("true")) {
			options.addArguments("--incognito");
		} 
		return options;
	}

	private FirefoxOptions setFFOptions() {

		FirefoxOptions options = new FirefoxOptions();

		if (readconfig.getHeadless().equalsIgnoreCase("true")) {
			options.addArguments("--headless");
		} else if (readconfig.getIcognito().equalsIgnoreCase("true")) {
			options.addArguments("--incognito");
		}

		return options;
	}

	private EdgeOptions setEdgeOptions() {

		EdgeOptions options = new EdgeOptions();

		if (readconfig.getHeadless().equalsIgnoreCase("true")) {
			options.addArguments("--headless");
		} else if (readconfig.getIcognito().equalsIgnoreCase("true")) {
			options.addArguments("--incognito");
		} else {

			options.addArguments("disable-infobars");
		}

		return options;
	}
}
