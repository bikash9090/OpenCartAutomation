package bksoft.OpenCartAutomation.listeners;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static void main(String args[]) {

		ExtentReports extent = new ExtentReports();
		File path = new File(".\\reports\\reports.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		extent.attachReporter(sparkReporter);

		extent.createTest("Test1", "Only for testing purpose")
		.log(Status.FAIL, "This is info 1")
		.log(Status.INFO, "This is info 2")
		.log(Status.PASS, "This is info 3")
		.log(Status.WARNING, "This is info 4")
		.log(Status.SKIP, "This is info 5")
		.log(Status.PASS, "This is info 6");

		extent.flush();
	}
}
