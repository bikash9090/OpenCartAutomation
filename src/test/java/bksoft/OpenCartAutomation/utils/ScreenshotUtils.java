package bksoft.OpenCartAutomation.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ScreenshotUtils {

	protected WebDriver driver;
	
	public ScreenshotUtils(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getScreenshot(String testname) {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot captureScreenshot = (TakesScreenshot) driver;
		File source = captureScreenshot.getScreenshotAs(OutputType.FILE);
		
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + testname + "_" + timeStamp + ".png";
		System.out.println("Screenshot captured Successful.");

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}
}
