package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.SearchProductPage;
import utilities.Utils;

public class SearchTest {
    WebDriver driver;
    SearchProductPage search;
  
  // I was dealing with some synchronization issues so I decided to use multiple classes to deal with it
    
  @BeforeTest  
  public void startSession() {
	  driver = Utils.startBrowser("chrome");
	  search = PageFactory.initElements(driver, SearchProductPage.class);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void searchProductTestCase() {
	  driver.get("https://www.webstaurantstore.com/");
	  search.searchForProduct("stainless work table");
	  search.searchForTheWordTableInTheFirstFourResultsPages();
	  search.addToCartTheLastTableInTheLastPage();
	  search.goToCheckoutAndEmptyCart();
  }
  
  @AfterTest
  public void endSession(){
  	driver.close();
  	driver.quit();
  }
}
