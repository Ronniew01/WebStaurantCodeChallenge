package utilities;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Utils {
	static WebDriver driver;
	
	public static WebDriver startBrowser(String browserType) {
		switch (browserType) {
		  case "chrome":
			  WebDriverManager.chromedriver().setup();
			  driver = new ChromeDriver();
		    break;
		  case "edge":
			  WebDriverManager.edgedriver().setup();
			  driver = new EdgeDriver();
		    break;
		  case "firefox":
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
		    break;
		  default:
			  WebDriverManager.chromedriver().setup();
			  driver = new ChromeDriver();
		} 
		return driver;
	}
	
	public static void clickElement(WebElement element) {
		waitFor(element);
		element.click();
	}
	
	public static void waitFor(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
