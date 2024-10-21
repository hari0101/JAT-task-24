package testexecution;

import java.time.Duration;
import utilities.SikuliScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pomobjectrepository.LoginSigninPage;

public class TestScript {
	
	public WebDriver driver;
	
	@BeforeClass(groups= {"Smoke_Test"})
	@Parameters({"Browser"})
	public void launchBrowser(String browser) {
		
		//Switching different browser based on testng parameter
		switch(browser.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver();
		break;
		case "firefox" : driver = new FirefoxDriver();
		break;
		case "edge" : driver = new EdgeDriver();
		break;
		default : System.out.println("INVALID BROWSER NAME : Unable to Start the Driver!");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.demoblaze.com/");
		//Screenshot
		SikuliScreenshot.capture("WebPage", driver);
	}
	
	
	
	@Test(priority = 0, groups= {"Smoke_Test"})
	public void signUp_DemoBlaze()  {
		
		//Instaniate the POM file class to the access method
		LoginSigninPage signUpAction = new LoginSigninPage(driver);
		
		//Invoked the method and returns the alert from the demoblaze
		String alertName = signUpAction.sign_Up_Blaze("UserEdge", "UserEdge@123");
		//Screenshot
		SikuliScreenshot.capture("SignUpscreen", driver);
		try {
			//Comparing the alert string values
			Assert.assertEquals(alertName, "Sign up successful.");
		}
		catch(AssertionError ae) {
			System.out.println("Actual and Expected result not true " + ae.getMessage());
		}
		finally {
		
		// Prints output based on the condition	
		 String output = alertName.equals("Sign up successful.") ? "User SignUp successfully!" : "User already exists";
		 System.out.println(output + "\n");
		}
		
	}
	
	@Test(priority = 1, dependsOnMethods = {"signUp_DemoBlaze"}, groups= {"Smoke_Test"})
	public void signIn_DemoBlaze() {
		
		//Instaniate the POM file class to the access method
		LoginSigninPage signInAction = new LoginSigninPage(driver);
		//Invoked the method from the class.
		signInAction.sign_In_Blaze("UserEdge", "UserEdge@123");
		//Screenshot
		SikuliScreenshot.capture("SignInscreen", driver);
	}
	
	@AfterClass
	public void tearDown()
	{
		//Closing the current window browser
		driver.close();
	}
	


}
