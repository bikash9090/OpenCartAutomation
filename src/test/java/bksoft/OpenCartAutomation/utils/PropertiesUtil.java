package bksoft.OpenCartAutomation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
	Properties prop;

	public PropertiesUtil() {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return prop.getProperty("appUrl");
	}

	public String getBrowser() {
		return prop.getProperty("browser");
	}

	public String getReportPath() {
		return prop.getProperty("reportPath");
	}

	public String getHeadless() {
		return prop.getProperty("headless");
	}

	public String getIcognito() {
		return prop.getProperty("incognito");
	}
}