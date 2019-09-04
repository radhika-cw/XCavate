package com.xc.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class XCavateLogin {
	WebDriver driver;
	final String secretKey = "abcdefghsk";

	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		// browser = "firefox";
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Myanaganti\\Desktop\\Xcavate\\Automation\\chromedriver\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Myanaganti\\Desktop\\Xcavate\\Automation\\chromedriver\\chromedriver.exe");
			// create chrome instance
			driver = new ChromeDriver();
		}
		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {
			// set path to Edge.exe
			System.setProperty("webdriver.edge.driver", ".\\MicrosoftWebDriver.exe");
			// create Edge instance
			driver = new EdgeDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void testLoginXC() throws InterruptedException, IOException {
		InputStream input = new FileInputStream("src/main/resources/login.properties");
		Properties property = new Properties();
		property.load(input);
		String encryptedusername = property.getProperty("username");
		String decryptedusername = AES.decrypt(encryptedusername, secretKey);

		String encryptedpwd = property.getProperty("password");
		String decryptedpwd = AES.decrypt(encryptedpwd, secretKey);

		driver.get("https://mobile.xcavate.us/landing");
		WebElement SalesforeceLogin = driver.findElement(By.id("salesforce-login"));
		SalesforeceLogin.click();
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				// <!--Perform your operation here for new window-->

				Thread.sleep(2000);
				WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]"));
				username.isEnabled();
				username.sendKeys(decryptedusername);
				WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
				password.sendKeys(decryptedpwd);
				WebElement Login = driver.findElement(By.xpath("//*[@id=\"Login\"]"));
				Login.click();

				// driver.close(); //closing child window
				driver.switchTo().window(parentWindow); // cntrl to parent window
			}
		}

		Thread.sleep(1000);
		driver.close();
	}
}
