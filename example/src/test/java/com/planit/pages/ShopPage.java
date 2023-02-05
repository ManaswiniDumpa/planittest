package com.planit.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ShopPage {

	WebDriver driver;

	public ShopPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public Float getPriceOfToyFromShop(String toyName)
	{
		WebElement priceElement=driver.findElement(By.xpath("//div[@class='products ng-scope']/ul/li/div/h4[text()='" + toyName + "']/following-sibling::p/span"));
		Float price=Float.parseFloat(priceElement.getText().substring(1));
		return price;
	}

	public void clickOnToyAndStorePrice(String toyName, int noOfToys) {
		String toyNameToadd=toyName;
		WebElement buyButton = driver.findElement(By.xpath(
				"//div[@class='products ng-scope']/ul/li/div/h4[text()='" + toyName + "']/following-sibling::p/a"));
		for (int i = 1; i <= noOfToys; i++) {
			buyButton.click();
			Map<String, Float> priceList = new HashMap<String, Float>();
			priceList.putIfAbsent(toyName,getPriceOfToyFromShop(toyNameToadd));
			
			
		}
		
		

		
	}
	public Map<String,Float> getPriceList(){
		List<WebElement> toyNameElementList=driver.findElements(By.xpath("//div[@class='products ng-scope']/ul/li/div/h4"));
		int totalNoOfToys=toyNameElementList.size();
		Map<String, Float> priceList=new HashMap <String,Float>();
		for (int i=1;i<=totalNoOfToys;i++) {
			WebElement toyNameElement=driver.findElement(By.xpath("//div[@class='products ng-scope']/ul/li[" + i + "]/div/h4"));
			WebElement priceElement=driver.findElement(By.xpath("//div[@class='products ng-scope']/ul/li["+i+"]/div/h4/following-sibling::p/span"));
			priceList.putIfAbsent(toyNameElement.getText(), Float.parseFloat(priceElement.getText().substring(1)));
		}
		return priceList;
		
	}

	

}
