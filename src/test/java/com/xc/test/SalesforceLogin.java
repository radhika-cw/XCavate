package com.xc.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SalesforceLogin {
	// private static final String InputStream = null;
	WebDriver driver;

	@Test(invocationCount = 2)
	public void setup() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Myanaganti\\Desktop\\Xcavate\\Automation\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");

		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("Autoxc5+cw@org.com");
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("r@d1731@sf");
		WebElement Login = driver.findElement(By.id("Login"));
		Login.click();
		WebElement user = driver.findElement(By.id("userNavButton"));
		user.click();
		WebElement logout = driver.findElement(By.cssSelector("[title ='Logout']"));
		logout.click();
		Thread.sleep(20000);
		driver.close();
	}

}

/*
 * @Test(invocationCount = 5) public void sum() { int a = 10; int b = 20; int c
 * = a+b; System.out.println("The sum of a and b is: "+ c); }
 * 
 * 
 * }
 */
