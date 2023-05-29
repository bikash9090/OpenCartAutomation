package bksoft.OpenCartAutomation.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import bksoft.OpenCartAutomation.utils.PropertiesUtil;

public class ExtentManager {
	private static ExtentReports extentReports;
	private static PropertiesUtil readconfig;

	public static ExtentReports init() {
		readconfig = new PropertiesUtil();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		Path path = Paths.get(readconfig.getReportPath());
		String reportName = "Extent-Test-Report-" + timeStamp + ".html";
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		
		extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(readconfig.getReportPath() + reportName);
		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Customer Name", "CBT");
		extentReports.setSystemInfo("Application", "OpenCart");
		extentReports.setSystemInfo("Module", "User");
		extentReports.setSystemInfo("Sub Module", "Customers");
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environemnt", "QA");
		extentReports.setSystemInfo("Build#", "1.0");
		extentReports.setSystemInfo("Team", "OpenCart QA Team");

		return extentReports;
	}
}
