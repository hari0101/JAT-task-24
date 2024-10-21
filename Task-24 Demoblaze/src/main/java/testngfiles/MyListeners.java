package testngfiles;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class MyListeners implements ITestListener {
	
		//Making the report pre-defined classes as class level
		ExtentSparkReporter spark;
		ExtentReports extent;
		ExtentTest test;
		
		String parmValue;
		//Overriding all the listener interface methods
		@Override
		public void onStart(ITestContext context) {
			
			//Creating the HTML page using ExtentSparkReporter
			spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\demoreport.html");
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Test Report");
			spark.config().setReportName("DEMO Test");
			
			//Adding the information to the extent report
			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("HostName", "Hari Prasath");
			extent.setSystemInfo("System", "HP");
			extent.setSystemInfo("OS", "Windows");
			
			//Gets the parameter value from the Testng file
			parmValue = context.getCurrentXmlTest().getParameter("Browser");
		}
		
		@Override
		public void onTestSuccess (ITestResult result) {
				
				//Based on the parameter value device is assigned in the extent reports
				if(parmValue.equalsIgnoreCase("chrome")) {
				test = extent.createTest(result.getName()).assignAuthor("HARI").assignCategory("SMOKE TEST").assignDevice("Chrome 130.0.6723 on Windows");
				test.log(Status.PASS, "Test Execution PASSED " + result.getName());
				}
				
				else if(parmValue.equalsIgnoreCase("firefox")) {
					test = extent.createTest(result.getName()).assignAuthor("HARI").assignCategory("SMOKE TEST").assignDevice("Firefox 131.0.3 on Windows");
					test.log(Status.PASS, "Test Execution PASSED " + result.getName());
				}
				else if(parmValue.equalsIgnoreCase("edge")) {
					test = extent.createTest(result.getName()).assignAuthor("HARI").assignCategory("SMOKE TEST").assignDevice("Edge 130.0.2849.46 on Windows");
					test.log(Status.PASS, "Test Execution PASSED " + result.getName());
				}
		
		}
		
		@Override
		public void onTestFailure (ITestResult result) {
			
			//If test case fails this log will be created in extent report
			test = extent.createTest(result.getName()).assignAuthor("Hari").assignCategory("SMOKE TEST").assignDevice("Chrome 129.0");
			test.log(Status.FAIL, "Test Execution FAILED  " + result.getName());
			
			
		}
		
		@Override
		public void onTestSkipped (ITestResult result) {
			
			//If test case Skips this log will be created in extent report
			test = extent.createTest(result.getName());
			test.log(Status.SKIP, "Test SKIPPED " + result.getName());
			
		}
		
		@Override
		public void onFinish (ITestContext context) {
			
			//flush helps to output all the logs to the extent report
			extent.flush();
			
		}
		
	
	
}
