package intro;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import config.PropertiesFile;

public class AutoAmazon {

	static String browser;
	static WebDriver driver;

	public static void main(String [] args) {
		
		PropertiesFile pf = new PropertiesFile();
		
		pf.writeProperty("Browser","Edge");
		
		pf.writeProperty("Product", "Alexa");
		
		String val = pf.readProperty("Browser");
		
		String product = pf.readProperty("Product");
		
        setBrowser(val);
        setBrowserConfig();
        runTest(product);
	
	} 

	public static void setBrowser(String val) {
		browser = val;
	}
	public static void setBrowserConfig() {
		
		if(browser.equals("Chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\arun.y\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
		}
		else if(browser.equals("Edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\Users\\arun.y\\chromeDriver\\msedgedriver.exe");
	        driver = new EdgeDriver();
		}
		
	}
	public static void runTest(String product) {
		driver.get("https://amazon.in");
		driver.manage().window().maximize();


		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);

		driver.findElement(By.id("nav-search-submit-button")).click();
		
		if(product.equalsIgnoreCase("Alexa")) {

		driver.findElement(By.partialLinkText("Echo Dot")).click();

		// Now as echo dot is opened in the new tab so we need to shift the control to the new tab

		Set<String> handles = driver.getWindowHandles();

		String current = driver.getWindowHandle();


		for(String actual : handles) {
			if(!actual.equalsIgnoreCase(current)) {
				driver.switchTo().window(actual);
				break;
			}
		}


		System.out.println(driver.getTitle());

		String mrp = driver.findElement(By.cssSelector("#corePrice_desktop > div > table > tbody > tr:nth-child(1) > td.a-span12.a-color-secondary.a-size-base > span.a-price.a-text-price.a-size-base > span:nth-child(2)")).getText();

		String deal = driver.findElement(By.cssSelector("#corePrice_desktop > div > table > tbody > tr:nth-child(2) > td.a-span12 > span.a-price.a-text-price.a-size-medium.apexPriceToPay > span:nth-child(2)")).getText();

		System.out.println(deal +"<><>"+ mrp);
		
		}

		driver.quit();

	}

}
