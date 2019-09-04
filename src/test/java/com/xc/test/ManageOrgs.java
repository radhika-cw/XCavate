package com.xc.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

public class ManageOrgs {

	WebDriver driver;
	String browser = "chrome";
	@BeforeTest
	@Parameters("browser")
	public void setup() throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		browser = "chrome";
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
	public void login() throws InterruptedException, IOException {

		InputStream input = new FileInputStream("C:/Users/Myanaganti/XCavate-workspace/XCavateProject/property.txt");
		Properties property = new Properties();
		property.load(input);

		// driverManager = DriverManagerFactory.getDriverManager(DriverType.FIREFOX);
		// driver = driverManager.getWebDriver();
		driver.manage().window().maximize();
		driver.get("https://mobile.xcavate.us/landing");
		// Assert.assertEquals("XCavate - Landing",driver.getTitle());
		Thread.sleep(2000);
		WebElement Loginbutton = driver
				.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/img"));
		Loginbutton.click();
		Thread.sleep(10000);
		String MainWindow = driver.getWindowHandle();

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.id("username")).sendKeys(property.getProperty("username"));
				driver.findElement(By.name("pw")).sendKeys(property.getProperty("password"));

				driver.findElement(By.name("Login")).click();

				// Closing the Child Window.
				// driver.close();
			}
		}
		// Switching to Par

		Thread.sleep(10000);
		driver.switchTo().window(MainWindow);

		// driverManager.quitWebDriver();

	}

	@Test(priority = 2)
	public void ManageOrgsVerify() throws InterruptedException {

		WebElement manageorgs = driver.findElement(By.xpath("/html/body/header/nav[1]/div/div[2]/div/div[2]/a/span"));
		// manageusers.isEnabled();
		if (manageorgs.isEnabled()) {
			Thread.sleep(2000);
			manageorgs.click();
			Thread.sleep(20000);
		} else {
			System.out.println("Manage Orgs is not enabled");
		}

	}

	@Test(priority = 3)
	public void NewOrgAdd() throws InterruptedException {

		WebElement orgsaddButton = driver
				.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/div/div[2]/div/div/div[2]/button"));
		// manageusers.isEnabled();
		if (orgsaddButton.isEnabled()) {
			Thread.sleep(2000);
			orgsaddButton.click();
			Thread.sleep(20000);
		} else {
			System.out.println("Manage Orgs Add Button is not enabled");
		}

	}

	// add-connection-orgname
	@Test(priority = 4)
	public void CreateNewOrg() throws InterruptedException {

		WebElement orgname = driver.findElement(By.id("add-connection-orgname"));
		// manageusers.isEnabled();
		if (orgname.isEnabled()) {
			Thread.sleep(2000);
			orgname.sendKeys("Radhikaorg");
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs Nick Name TextBox is not enabled");
		}

		WebElement orgid = driver.findElement(By.id("add-connection-orgid"));
		// manageusers.isEnabled();
		if (orgid.isEnabled()) {
			Thread.sleep(2000);
			orgid.sendKeys("Radhikaorgid");
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs Id TextBox is not enabled");
		}

		WebElement orgsusername = driver.findElement(By.id("add-connection-username"));
		// manageusers.isEnabled();
		if (orgsusername.isEnabled()) {
			Thread.sleep(2000);
			orgsusername.sendKeys("Radhikayanaganti");
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs UserName TextBox is not enabled");
		}

		WebElement orgspassword = driver.findElement(By.id("add-connection-password"));
		// manageusers.isEnabled();
		if (orgspassword.isEnabled()) {
			Thread.sleep(2000);
			orgspassword.sendKeys("sgwkjdwlkjdgslkd");
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs Password TextBox is not enabled");
		}

		WebElement consumerkey = driver.findElement(By.id("add-connection-consumerKey"));
		// manageusers.isEnabled();
		if (consumerkey.isEnabled()) {
			Thread.sleep(2000);
			consumerkey.sendKeys("Hfatdtayadbkalrtuisd");
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs ConsumerKey TextBox is not enabled");
		}

		WebElement consumersecret = driver.findElement(By.id("add-connection-consumerSecret"));
		// manageusers.isEnabled();
		if (consumersecret.isEnabled()) {
			Thread.sleep(2000);
			consumersecret.sendKeys("haktgdshsfdsjdopdnd");
			Thread.sleep(20000);
		} else {
			System.out.println("Orgs ConsumerSecret TextBox is not enabled");
		}

	}

	@AfterTest
	public void DriverCloseMethod() throws InterruptedException, IOException {

		driver.close();
	}
}
