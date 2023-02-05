package com.planit.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	public static WebDriver driver ;
	
	public void beforeSuite() {
		if (driver == null) {
			System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get("https://jupiter.cloud.planittesting.com");
		}
		// Perform Basic Operations
		// driver.manage().deleteAllCookies();
		

	}
	public static  WebDriver getdriver(){


		
			try {
				if(driver==null)
{					System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
					driver = new ChromeDriver();

					driver.get("https://jupiter.cloud.planittesting.com");

}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

return driver;
}

	@AfterMethod
	
	  public static void quit()
	{ driver.quit(); 
	driver=null; 
	  // we destroy the driver object after quit operation
	}
	public static void close() {
	  driver.close(); driver=null; // we destroy the driver object after quit operation 
	  }
	 

}
