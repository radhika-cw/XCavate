package com.xc.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ManageUsers {

	WebDriver driver;
	final String secretKey = "abcdefghsk";

	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		//browser = "chrome";
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

	@Test(priority = 1)
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

		Thread.sleep(10000);
		//driver.close();
	}

	@Test(priority = 2)
	public void ManageUsersverify() throws InterruptedException {

		WebElement manageusers = driver.findElement(By.className("icon-manage-users"));
		// manageusers.isEnabled();
		if (manageusers.isEnabled()) {
			Thread.sleep(2000);
			manageusers.click();
			Thread.sleep(2000);
		} else {
			System.out.println("Manage Users is not enabled");
		}

	}

	@Test(priority = 3)
	public void AddUsers() throws InterruptedException {

		WebElement addusers = driver.findElement(By.className("icon-add-user"));
		// manageusers.isEnabled();
		if (addusers.isEnabled()) {
			Thread.sleep(2000);
			addusers.click();
		} else {
			System.out.println("Add Users is not enabled");
		}

	}

	@Test(priority = 4)
	public void AddUserdetails() throws InterruptedException {
		Thread.sleep(2000);
		WebElement sfusername = driver.findElement(By.id("InputUsername"));
		// manageusers.isEnabled();
		if (sfusername.isEnabled()) {
			Thread.sleep(2000);
			sfusername.sendKeys("radhikayanaganti");
		} else {
			System.out.println("SF Username Textbox is not enabled");
		}
		WebElement email = driver.findElement(By.id("InputUserEmail"));
		// manageusers.isEnabled();
		if (email.isEnabled()) {
			email.sendKeys("radhikayanaganti@cloudwave.com");
		} else {
			System.out.println("Email Textbox is not enabled");
		}

		WebElement role = driver.findElement(By.id("InputRoles"));
		Select dropdown = new Select(role);
		dropdown.selectByVisibleText("OrgAdmin");

		WebElement orgs = driver.findElement(By.id("InputDataRole"));
		Select dropdown2 = new Select(orgs);
		dropdown2.selectByVisibleText("IIT");

		WebElement status = driver.findElement(By.id("InputStatus"));
		Select dropdown3 = new Select(status);
		dropdown3.selectByVisibleText("Active");
		Thread.sleep(5000);

		// btn btn-primary
		driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div[3]/button[3]")).click();

		System.out.println(
				driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div")).getText());
		/*
		 * if (alert1.getText().
		 * contains("User limit exists. Only 10 number of users is allowed.")) {
		 * System.out.println("Alert Found"); }else {
		 * System.out.println("Alert not found"); }
		 */

		if (driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div")).isEnabled()) {
			System.out.println(driver
					.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div")).getText());
			System.out.println(
					"Something is not correct please check if you are giving the proper information and Please check if you have enough storage");
			Thread.sleep(10000);
			WebElement cancelbutton = driver
					.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div[3]/button[1]"));
			cancelbutton.click();
			Thread.sleep(10000);
			throw new IllegalStateException();
		} else {

			System.out.println("No issues");
		}

		Thread.sleep(10000);
	}

	@AfterTest
	public void DriverCloseMethod() throws InterruptedException, IOException {

		driver.close();
	}
}
