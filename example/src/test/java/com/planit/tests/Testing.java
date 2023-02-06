package com.planit.tests;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.planit.base.BaseClass;
import com.planit.pages.CartPage;
import com.planit.pages.ContactPage;
import com.planit.pages.HomePage;
import com.planit.pages.ShopPage;
import com.planit.util.ExcelUtil;
import com.planit.util.Util;

public class Testing extends BaseClass {
	@Test
	public void TC01_errorMessagevalidation() throws InterruptedException {
		WebDriver driver = BaseClass.getdriver();
		HomePage homePage = new HomePage(driver);
		String ActualTitle = "Jupiter Toys";
		String ExpectedTitle = driver.getTitle();
		//Validating Title
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		
		// 1. Navigating to Contact page
		homePage.getContact().click();
		driver.manage().window().maximize();
		ContactPage contactPage = new ContactPage(driver);
		Thread.sleep(1000);
		Assert.assertTrue(contactPage.getSubmitButton().isDisplayed());
		
		//2. Click on submit button
		Util.clickUsingJavaScript(contactPage.getSubmitButton());
		
		//3. Validating error Messages
		String expectedHeaderMessageIncomplete = contactPage.getHeaderMessageIncomplete().getText();
		String actualHeaderMessageIncomplete="We welcome your feedback - but we won't get it unless you complete the form correctly.";
		Assert.assertEquals(actualHeaderMessageIncomplete, expectedHeaderMessageIncomplete);    
		String actualForenameIncomeplete = contactPage.getForenameerror().getText();
		String expectedForenameIncomeplete="Forename is required";
		Assert.assertEquals(actualForenameIncomeplete, expectedForenameIncomeplete);
		String actualEmailIncomeplete = contactPage.getEmailerror().getText();
		String expectedEmailIncomeplete="Email is required";
		Assert.assertEquals(actualEmailIncomeplete, expectedEmailIncomeplete);
		String actualMessageIncomeplete = contactPage.getMessageerror().getText();
		String expectedMessageIncomeplete="Message is required";
		Assert.assertEquals(actualMessageIncomeplete, expectedMessageIncomeplete);
		
		//4.Populating Mandatory fields
		contactPage.getForename().sendKeys("test");
		contactPage.getSurname().sendKeys("test");
		contactPage.getEmail().sendKeys("test");
		contactPage.getTelephone().sendKeys("1234");
		contactPage.getMessage().sendKeys("test");
		contactPage.getSubmitButton().click();
		Thread.sleep(20000);
		
		//5.Validate errors are gone
		Assert.assertFalse(Util.elementDoesNotExists(contactPage.getForenameerror()));
		Assert.assertFalse(Util.elementDoesNotExists(contactPage.getEmailerror()));
		Assert.assertFalse(Util.elementDoesNotExists(contactPage.getMessageerror()));
		
		
		//6.Validate success message
		String actualSuccessMessage = contactPage.getSuccessMessage().getText();
		String expectedSuccessMessage = "Thanks test, we appreciate your feedback.";
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
	
	}
	

	// TC-2
	@Test
	@DataProvider(name = "ContactDetails")
	public void TC02_validMessageValidation() throws IOException, InterruptedException {

		WebDriver driver = BaseClass.getdriver();
		HomePage homePage = new HomePage(driver);
		//Title Validation
		String ActualTitle = "Jupiter Toys";
		String ExpectedTitle = driver.getTitle();
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		Thread.sleep(10000);
		// 1.Navigate to Contact Page
		homePage.getContact().click();
		driver.manage().window().maximize();
		String path = "./src/test/resources/excelFiles/ContactDetails.xlsx";
		ExcelUtil xlutil = new ExcelUtil(path);
		String Sheet = "Sheet1";
		int rowCount = xlutil.getRowCount(Sheet);
		// Loop to enter data 5 times
		for (int i = 1; i <= rowCount; i++)

		{
			String Forename = xlutil.getCellData(Sheet, i, 0);
			String Surname = xlutil.getCellData(Sheet, i, 1);
			String Email = xlutil.getCellData(Sheet, i, 2);
			String Telephone = xlutil.getCellData(Sheet, i, 3);
			String Message = xlutil.getCellData(Sheet, i, 4);

			// Passing parameters

			ContactPage contactPage = new ContactPage(driver);
			contactPage.fillContactDetails(Forename, Surname, Email, Telephone, Message);

			// submitting data by clicking on submit button
			Util.clickUsingJavaScript(contactPage.getSubmitButton());
			Thread.sleep(15000);
			//Success message validation
			String actualSuccessMessage = contactPage.getSuccessMessage().getText();
			String expectedSuccessMessage = "Thanks" + " " + Forename + ", we appreciate your feedback.";
			Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
			Util.clickUsingJavaScript(contactPage.getBackButton());

		}

	}

	@Test
	public void TC03_CartTotal() throws InterruptedException {
		WebDriver driver = BaseClass.getdriver();
		HomePage homePage = new HomePage(driver);
		CartPage cartPage = new CartPage(driver);
		String ActualTitle = "Jupiter Toys";
		String ExpectedTitle = driver.getTitle();
		//Validate page title
		Assert.assertEquals(ExpectedTitle, ActualTitle);
		// 1.navigate to shop page
		homePage.getShop().click();
		ShopPage shopPage = new ShopPage(driver);
		// 2.click on buy button based on toy name and number mentioned
		Thread.sleep(5000);
		shopPage.clickOnToyAndStorePrice("Stuffed Frog", 2);
		shopPage.clickOnToyAndStorePrice("Fluffy Bunny", 5);
		shopPage.clickOnToyAndStorePrice("Valentine Bear", 3);
		Map <String, Float> prices=shopPage.getPriceList();
		homePage.getCart().click();
		Thread.sleep(15000);
		
		//3.Verify subTotal
		cartPage.validateSubTotal();
		//4.Verify the price for each product
		cartPage.validatePrices(prices.get("Stuffed Frog"), cartPage.getPriceOfToyFromCart("Stuffed Frog"));
		cartPage.validatePrices(prices.get("Fluffy Bunny"), cartPage.getPriceOfToyFromCart("Fluffy Bunny"));
		cartPage.validatePrices(prices.get("Valentine Bear"), cartPage.getPriceOfToyFromCart("Valentine Bear"));
		
		
		
		//5.verify total
		cartPage.validateTotal();
		String[] splitArray = cartPage.getTotalFooter().getText().split(":");
		float expectedTotal = Float.parseFloat(splitArray[1].trim());
		Assert.assertEquals(cartPage.validateTotal(), expectedTotal);

	}
}
