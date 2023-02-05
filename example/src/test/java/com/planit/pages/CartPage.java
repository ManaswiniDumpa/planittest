package com.planit.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

	WebDriver driver;

	@FindBy(xpath = "/html/body/div[2]/div/form/table/tfoot/tr[1]/td/strong")
	private WebElement totalFooter;

	public WebElement getTotalFooter() {
		return totalFooter;
	}

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void validateSubTotal() {
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr"));
		int rowCount = rows.size();
		for (int i = 1; i <= rowCount; i++) {
			WebElement priceElement = driver.findElement(
					By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[2]"));
			String priceText = priceElement.getText().substring(1);
			float price = Float.parseFloat(priceText);
			WebElement quantityElement = driver.findElement(
					By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[3]/input"));
			float quantity = Float.parseFloat(quantityElement.getAttribute("value"));
			WebElement subTotalElement = driver.findElement(
					By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[4]"));
			float expectedSubTotal = price * quantity;

			float actualSubTotal = Float.parseFloat(subTotalElement.getText().substring(1));
			Assert.assertEquals(Math.ceil(actualSubTotal), Math.ceil(expectedSubTotal));
		}
	}

public Float getPriceOfToyFromCart(String toyName) {
	List<WebElement> rows = driver
			.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr"));
	int rowCount = rows.size();
		for (int i = 1; i <= rowCount; i++) {
		WebElement toyNameElement = driver.findElement(
				By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[1]"));
		WebElement priceElement = driver.findElement(
				By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[2]"));
		if(toyNameElement.getText().equalsIgnoreCase(toyName)) {
			return Float.parseFloat(priceElement.getText().substring(1));
		}
	
	}
		return (float)0;
}
		public void validatePrices(Float shopPrice, Float cartPrice) {
			assertEquals(shopPrice, cartPrice);
		}

	
	
	public Float validateTotal() {
	
		float sum = 0;
		float actualTotal = 0;
		List<WebElement> rows = driver
				.findElements(By.xpath("//table[@class='table table-striped cart-items']/tbody/tr"));
		int rowCount = rows.size();
		for (int i = 1; i <= rowCount; i++) {

			WebElement subTotalElement = driver.findElement(
					By.xpath("//table[@class='table table-striped cart-items']/tbody/tr[" + i + "]/td[4]"));
			sum = Float.parseFloat(subTotalElement.getText().substring(1));
			actualTotal = actualTotal + sum;
		}
		return actualTotal;

	}

}
