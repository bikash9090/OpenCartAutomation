package bksoft.OpenCartAutomation.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import bksoft.OpenCartAutomation.utils.PropertiesUtil;

public class TestBase {

	private PropertiesUtil readconfig = new PropertiesUtil();
	public WebDriver driver;
	
	public void setUpDriver(){
		
			try {
				driver = DriverFactory.getDriverInstance().initializeDriver();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		
		driver.get(readconfig.getUrl());
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
	}
	
	public void tearDownDriver() {

		if (driver != null) {	
			
			DriverFactory.getDriverInstance().quitDriver();
		}
	}
}
