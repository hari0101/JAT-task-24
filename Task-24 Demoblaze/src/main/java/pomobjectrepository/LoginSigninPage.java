package pomobjectrepository;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSigninPage {
	
	//Global variables to access all the methods
	public WebDriver driver;
	public Alert alert;
	
	//Constructor to automatically initialize webdriver here.
	public LoginSigninPage(WebDriver driver){
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators from demoblaze_Signup
	@FindBy(id ="signin2") private WebElement sign_Up;
	@FindBy(how = How.ID, using = "sign-username") private WebElement signUp_username_Field;
	@FindBy(id = "sign-password") private WebElement signUp_password_Field;
	@FindBy(how = How.XPATH, using = "//button[text()='Sign up']") private WebElement signUp_Button;
	
	//Locators from demoblaze_SignIn
	@FindBy(id = "login2") private WebElement sign_In;
	@FindBy(how = How.ID, using = "loginusername") private WebElement signIn_username_Field;
	@FindBy(id = "loginpassword") private WebElement signIn_password_Field;
	@FindBy(how = How.XPATH ,using = "//button[text()='Log in']") private WebElement logIn_button;
	
	//Locator homepage
	@FindBy(css ="#nameofuser") private WebElement homepage;
	
	//Demoblaze SignUp method
	public String sign_Up_Blaze(String username, String password) {
		
		sign_Up.click();
		signUp_username_Field.sendKeys(username);
		signUp_password_Field.sendKeys(password);
		signUp_Button.click();
		WebDriverWait sleep = new WebDriverWait(driver, Duration.ofSeconds(20));
		sleep.until(ExpectedConditions.alertIsPresent());
		alert = driver.switchTo().alert();
		String alert_Text = alert.getText();
		alert.accept();
		return alert_Text;
	}
	
	//Demoblaze SignIn method
	public void sign_In_Blaze(String username, String password) {
		
		WebDriverWait sleep = new WebDriverWait(driver, Duration.ofSeconds(20));
		sign_In.click();
		signIn_username_Field.sendKeys(username);
		signIn_password_Field.sendKeys(password);
		sleep.until(ExpectedConditions.elementToBeClickable(logIn_button));
		logIn_button.click();
		
		sleep.until(ExpectedConditions.visibilityOf(homepage));		
		System.out.println("Login to the DemoBlaze Successfully" + "\n");
		System.out.println("Text of username --> " + homepage.getText());
	}
}
