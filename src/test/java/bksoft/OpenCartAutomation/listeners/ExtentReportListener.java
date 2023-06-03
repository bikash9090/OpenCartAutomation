package bksoft.OpenCartAutomation.listeners;

import java.util.Calendar;
import java.util.Date;

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

		Object[] parameters = result.getParameters();
		StringBuilder parameterValues = new StringBuilder();

		for (Object parameter : parameters) {
			parameterValues.append(parameter).append(", ");
		}

		// Remove the trailing comma and space
		String parameterString = parameterValues.toString().replaceAll(", $", "");

		String className = qualifiedName.substring(mid + 1, last);
		String methodNamewithParam = methodName + "('" + parameterString + "')";

		System.out.println(methodNamewithParam + " started!");

		test = extent.createTest(methodNamewithParam, result.getMethod().getDescription());

		test.assignCategory(result.getTestContext().getSuite().getName());
		test.assignCategory(className);

	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {

		test.log(Status.PASS, "Test Passed");
		System.out.println("Test Passed!");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {

		scrObj = new ScreenshotUtils(DriverFactory.getDriver());

		String imgPath = scrObj.getScreenshot(result.getName());

		test.addScreenCaptureFromPath(imgPath);
		test.getModel().setEndTime(getTime(result.getEndMillis()));

		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

		System.out.println("Test failed!");

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {

		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " skipped!"));

		scrObj = new ScreenshotUtils(DriverFactory.getDriver());
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
