package bksoft.OpenCartAutomation.listeners;

import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import bksoft.OpenCartAutomation.base.DriverFactory;
import bksoft.OpenCartAutomation.utils.ScreenshotUtils;

public class ExtentReportListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.init();
	public static ExtentTest test;
	ScreenshotUtils scrObj;
	WebDriver driver;

	@Override
	public synchronized void onStart(ITestContext context) {
	        
		System.out.println("The test suite started successful...");
		
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		extent.flush();
		System.out.println("Flushed successful...");
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();

		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");

		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");

		ExtentTest extentTest = extent.createTest(methodName, result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);

	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " passed!"));
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " failed!"));
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		driver = DriverFactory.getDriver();
		scrObj = new ScreenshotUtils(driver);
		String imgPath = scrObj.getScreenshot(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		
		test.getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " skipped!"));
		
		driver = DriverFactory.getDriver();
		scrObj = new ScreenshotUtils(driver);
		String imgPath = scrObj.getScreenshot(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		
		test.getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	@Override
	public synchronized void onTestFailedWithTimeout(ITestResult result) {
		System.out.println(("onTestFailedWithTimeout for " + result.getMethod().getMethodName()));
	}
	
	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
