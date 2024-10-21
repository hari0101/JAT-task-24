package testexecution;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class NewListen implements ITestListener {
	
		//Making the report pre-defined classes as class level
		ExtentSparkReporter spark;
		ExtentReports extent;
		ExtentTest test;
		
		//Overriding all the listener interface methods
		@Override
		public void onStart(ITestContext context) {
			
			spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\demoreport.html");
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Test Report");
			spark.config().setReportName("DEMO Test");
			
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("HostName", "Hari Prasath");
			extent.setSystemInfo("System", "HP");
			extent.setSystemInfo("OS", "Windows");
		}
		
		@Override
		public void onTestSuccess (ITestResult result) {
				
				test = extent.createTest(result.getName());
				test.log(Status.PASS, "Test PASSED" + result.getName());
		}
		
		@Override
		public void onTestFailure (ITestResult result) {
				
			test = extent.createTest(result.getName());
			test.log(Status.FAIL, "Test FAILED" + result.getName());
			
			
		}
		
		@Override
		public void onTestSkipped (ITestResult result) {
			
			test = extent.createTest(result.getName());
			test.log(Status.SKIP, "Test SKIPPED" + result.getName());
			
		}
		
		@Override
		public void onFinish (ITestContext context) {
			
			extent.flush();
			
		}
		
	
	
}
