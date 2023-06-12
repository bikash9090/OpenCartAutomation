package bksoft.OpenCartAutomation.listeners;

import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

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

		String testName;
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();

		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");

		String className = qualifiedName.substring(mid + 1, last);

		/*-------------------------Retrieving the testName from @Test(testName)--------------------------*/
		Test testAnnotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
		if (testAnnotation != null) {
			testName = testAnnotation.testName();
		} else {
			testName = "NoDefined";
		}
		
		/*-----------------------Retrieving the parameters of method---------------------------*/
		Object[] parameters = result.getParameters();
		StringBuilder parameterValues = new StringBuilder();

		for (Object parameter : parameters) {
			parameterValues.append(parameter).append(", ");
		}

		String parameterString = parameterValues.toString().replaceAll(", $", "");// Remove the trailing comma and space
		String methodNamewithParam = testName + "_" + methodName + "('" + parameterString + "')"; // Combined the testName+methodname+methodParameters

		System.out.println(methodNamewithParam + " started!");

		/*---------------------Create extent test in extent report----------------------------*/
		test = extent.createTest(methodNamewithParam, result.getMethod().getDescription());
		System.out.println(result.getTestName());

		test.assignCategory(result.getTestContext().getSuite().getName());
		test.assignCategory(className);

	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		/*---------------------Adding Success status to extent test------------------------*/
		test.log(Status.PASS, "Test Passed");
		System.out.println("Test Passed!");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		/*--------------------Capture screenshot and add to extent test------------------------*/
		scrObj = new ScreenshotUtils(DriverFactory.getDriver());
		String imgPath = scrObj.getScreenshot(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		
		/*---------------------Adding failed log details to extent test------------------------*/
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
		test.getModel().setEndTime(getTime(result.getEndMillis())); //Adding test end time to report.

		System.out.println("Test failed!");

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		/*---------------------Capture screenshot and add to extent test------------------------*/
		scrObj = new ScreenshotUtils(DriverFactory.getDriver());
		String imgPath = scrObj.getScreenshot(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		
		/*----------------------Adding skipped log details to extent test-----------------------*/
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
		test.getModel().setEndTime(getTime(result.getEndMillis())); //Adding test end time to report.
		
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " skipped!"));
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
