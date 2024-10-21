package utilities;


import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Screen;

public class SikuliScreenshot {

	
public static void capture(String imageName, WebDriver driver) {
	
	String path = "D:\\Testing_data_files\\sikuli_Capture\\"+imageName+".png";
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	Screen screen = new Screen();
	
	screen.getImage().save(path);
	
	System.out.println("Screenshot Saved in path!" + "\n");
}
	
	
}
