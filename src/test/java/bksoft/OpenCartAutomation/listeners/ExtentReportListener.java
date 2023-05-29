package bksoft.OpenCartAutomation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportListener implements ITestListener{

	private static ExtentReports extent = ExtentManager.init();
	public static ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("The test suite started successful...");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		System.out.println("Flushed successful...");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test started successful...");
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(methodName,result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());

		extentTest.assignCategory(className);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test successful : "+result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

}
