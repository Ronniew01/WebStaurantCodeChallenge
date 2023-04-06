package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utilities.Utils;

public class SearchProductPage extends Utils{
  
	WebDriver driver;
	
	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath ="//input[@type='text']") WebElement searchBox;
	@FindBy(xpath ="//button[@value='Search']") WebElement searchButton;
	@FindBy(xpath ="//a[@data-testid='itemDescription']") List<WebElement> searchResults;
	@FindBy(xpath = "//nav[@aria-label='pagination']//li[contains(@class,'rounded-r-md')]//a") WebElement nextPageButton;
	@FindBy(xpath = "//nav[@aria-label='pagination']//li[@class='inline-block leading-4 align-top']//a[contains(@aria-label, 'page 9')]") WebElement lastPageButton;
	@FindBy(xpath = "//input[@name='addToCartButton']") List<WebElement> addToCartButton;
	@FindBy(xpath = "//div[@class='notification__content']") WebElement notificationContent;
	@FindBy(xpath = "//div[@class='notification__action']//a[text()='View Cart']") WebElement viewCartButton;
	@FindBy(xpath = "//button[contains(@class, 'emptyCartButton')]") WebElement emptyCartButton;
	@FindBy(xpath = "//div[@aria-modal='true']//button[text()='Empty']") WebElement emptyCartModalButton;
	@FindBy(xpath = "//div[@class='empty-cart__text']//p[1]") WebElement emptyCartHeader;

	public void searchForProduct(String product) {
		searchBox.sendKeys(product);
		clickElement(searchButton);
	}
	
	public void searchForTheWordTableInTheFirstFourResultsPages() { 
		/*Used a soft assert so that assertion can be ran after going through the first four pages
		 * did it this way to avoid Stale Element Reference exception since page is reloading after clicking next page button 
		*/
		SoftAssert softAssert = new SoftAssert(); 
		
		for(WebElement searchResult : searchResults) {
			  softAssert.assertTrue(searchResult.getText().contains("Table"));
		  }
		  clickElement(nextPageButton);
		  
		  for(WebElement searchResult : searchResults) {
			  softAssert.assertTrue(searchResult.getText().contains("Table"));
		  }
		  clickElement(nextPageButton);
		  
		  for(WebElement searchResult : searchResults) {
			  softAssert.assertTrue(searchResult.getText().contains("Table"));
		  }
		  clickElement(nextPageButton);
		  
		  for(WebElement searchResult : searchResults) {
			  softAssert.assertTrue(searchResult.getText().contains("Table"));
		  }
		  
		  softAssert.assertAll();
	}
	
	public void addToCartTheLastTableInTheLastPage() {
		lastPageButton.click();
		clickElement(addToCartButton.get(addToCartButton.size()-1));
		
		//Check if item added to cart notification is displayed after
		waitFor(notificationContent);
		Assert.assertTrue(notificationContent.isDisplayed());
	}
	
	public void goToCheckoutAndEmptyCart() {
		clickElement(viewCartButton);
		clickElement(emptyCartButton);
		clickElement(emptyCartModalButton);
		
		//Check if cart is empty after emptying it
		waitFor(emptyCartHeader);
		Assert.assertTrue(emptyCartHeader.isDisplayed());
	}
}
