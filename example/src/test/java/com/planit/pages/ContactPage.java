package com.planit.pages;

import com.planit.base.BaseClass;
import com.planit.util.Util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage extends BaseClass {

	WebDriver driver;
	Util util = new Util();

	@FindBy(xpath = "//a[contains(text(),'Submit')]")
	private WebElement SubmitButton;

	@FindBy(xpath = "//*[@id='header-message']/div/strong")
	private WebElement HeaderMessage;

	@FindBy(xpath = "//*[@name='forename']")
	private WebElement Forename;

	@FindBy(xpath = "//*[@name='surname']")
	private WebElement Surname;

	@FindBy(xpath = "//*[@name='email']")
	private WebElement Email;

	public WebElement getEmail() {
		return Email;
	}

	@FindBy(xpath = "//*[@name='telephone']")
	private WebElement Telephone;

	@FindBy(xpath = "//*[@name='message']")
	private WebElement Message;

	@FindBy(xpath = "//*[@id='header-message']/div")
	private WebElement HeaderMessageIncomplete;

	@FindBy(xpath = "//*[@id='forename-err']")
	private WebElement Forenameerror;

	@FindBy(xpath = "//*[@id='email-err']")
	private WebElement Emailerror;

	@FindBy(xpath = "//*[@id='message-err']")
	private WebElement Messageerror;

	@FindBy(xpath = "//html/body/div[2]/div/div")
	private WebElement SuccessMessage;

	@FindBy(xpath = "/html/body/div[2]/div/a")
	private WebElement backButton;

	public WebElement getBackButton() {
		return backButton;
	}

	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getSuccessMessage() {
		return SuccessMessage;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHeaderMessage() {
		return HeaderMessage;
	}

	public WebElement getForename() {
		return Forename;
	}

	public WebElement getSurname() {
		return Surname;
	}

	public WebElement getTelephone() {
		return Telephone;
	}

	public WebElement getMessage() {
		return Message;
	}

	public WebElement getHeaderMessageIncomplete() {
		return HeaderMessageIncomplete;
	}

	public WebElement getForenameerror() {
		return Forenameerror;
	}

	public WebElement getEmailerror() {
		return Emailerror;
	}

	public WebElement getMessageerror() {
		return Messageerror;
	}

	public WebElement getSubmitButton() {
		return SubmitButton;
	}

	public void fillContactDetails(String Forename, String Surname, String Email, String Telephone, String Message) {

		getForename().sendKeys(Forename);
		Util.waitForThirtySeconds(getForename());
		getSurname().sendKeys(Surname);
		Util.waitForThirtySeconds(getSurname());
		getEmail().sendKeys(Email);
		Util.waitForThirtySeconds(getEmail());
		getTelephone().sendKeys(Telephone);
		Util.waitForThirtySeconds(getTelephone());
		getMessage().sendKeys(Message);
		Util.waitForThirtySeconds(getMessage());

	}

	public void clearContactDetails(String Forename, String Surname, String Email, String Telephone, String Message) {
		getForename().clear();
		Util.waitForThirtySeconds(getForename());
		getSurname().clear();
		Util.waitForThirtySeconds(getSurname());
		getEmail().clear();
		Util.waitForThirtySeconds(getEmail());
		getTelephone().clear();
		Util.waitForThirtySeconds(getTelephone());
		getMessage().clear();
		Util.waitForThirtySeconds(getMessage());

	}
}
