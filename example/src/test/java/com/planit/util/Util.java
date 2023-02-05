package com.planit.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import com.planit.base.BaseClass;

public class Util extends BaseClass {
	public  WebDriver driver;

	
	public boolean elementExists(WebElement element) {
		if (element==null) {
			return false;
		}
		return true;
			
		}
	public boolean elementDoesNotExists(WebElement element) {
		if (element==null) {
			return true;
		}
		return false;
			
		}
	

	public  void waitForThirtySeconds(WebElement element,WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element)); // you can use any other By like id, cssselector, name,
																// linktext etc
	}

	public  void clickUsingJavaScript(WebElement element, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public  void scrollIntoView(WebElement element,WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
