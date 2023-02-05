package com.planit.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	@FindBy(xpath = "//*[@id='nav-contact']/a")
	private WebElement Contact;

	@FindBy(xpath = "//*[@id='nav-shop']/a")
	private WebElement Shop;

	@FindBy(xpath = "//*[@id='nav-cart']/a")
	private WebElement Cart;

	public WebElement getCart() {
		return Cart;
	}

	public WebElement getShop() {
		return Shop;
	}

	public void setShop(WebElement shop) {
		Shop = shop;
	}

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getContact() {
		return Contact;
	}

}
