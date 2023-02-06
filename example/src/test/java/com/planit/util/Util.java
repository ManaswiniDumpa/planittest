package com.planit.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


import com.planit.base.BaseClass;

public class Util extends BaseClass {
	public static  WebDriver driver;
	
	

	
	public Util() {
		driver=getdriver();
		// TODO Auto-generated constructor stub
	}
	public static boolean elementExists(WebElement element) throws Exception {
		try {
	        element.isDisplayed();
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
		 

		}
	public static boolean elementDoesNotExists(WebElement element) {
		if (!(element.isDisplayed())) {
			return true;
		}
		return false;
			
		}
	

	public static void waitForThirtySeconds(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element)); // you can use any other By like id, cssselector, name,
																// linktext etc
	}

	public static void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public static void scrollIntoView(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
