package testexecution;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Jenkinsdemo {
	
	public static WebDriver driver;
	
	@Test
	public void browserChrome() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.google.com/");
		
	}
	
	@Test
	public void browserFireFox() {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.google.com/");
		WebElement inputfield = driver.findElement(By.id("APjFq"));  //fails
		Actions act = new Actions(driver);
		
		act.moveToElement(inputfield).build().perform();
	}
	

}
