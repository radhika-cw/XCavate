package com.xc.test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest {
	
	WebDriver driver;
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		//browser = "firefox";
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Myanaganti\\Desktop\\Xcavate\\Automation\\chromedriver\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Myanaganti\\Desktop\\Xcavate\\Automation\\chromedriver\\chromedriver.exe");
			//create chrome instance
			driver = new ChromeDriver();
		}
		//Check if parameter passed as 'Edge'
				else if(browser.equalsIgnoreCase("Edge")){
					//set path to Edge.exe
					System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
					//create Edge instance
					driver = new EdgeDriver();
				}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void Test1() throws InterruptedException, IOException{
		
		//InputStream input = new FileInputStream ("C:/Users/Myanaganti/XCavate-workspace/XCavateProject/property.txt");
		//Properties property = new Properties();
		//property.load(input);
		
		//driverManager = DriverManagerFactory.getDriverManager(DriverType.FIREFOX);
		//driver = driverManager.getWebDriver();
		driver.get("https://mobile.xcavate.us/landing");
		
		//String driver = driver.getTitle();
		driver.close();
				
	}
	
	
}


