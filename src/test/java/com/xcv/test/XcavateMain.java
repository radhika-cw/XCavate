package com.xcv.test;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.xc.test.AES;

public class XcavateMain {

	WebDriver driver;
	final String secretKey = "abcdefghsk";

	public void setup(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		// browser = "chrome";
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

	public void testLoginXC() throws InterruptedException, IOException {
		InputStream input = new FileInputStream("src/main/resources/login.properties");
		Properties property = new Properties();
		property.load(input);
		String encryptedusername = property.getProperty("username");
		String decryptedusername = AES.decrypt(encryptedusername, secretKey);

		String encryptedpwd = property.getProperty("password");
		String decryptedpwd = AES.decrypt(encryptedpwd, secretKey);
		driver.manage().window().maximize();
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
				// username.sendKeys("radhika+y@cloudwaveinc.com");
				WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
				password.sendKeys(decryptedpwd);
				WebElement Login = driver.findElement(By.xpath("//*[@id=\"Login\"]"));
				Login.click();

				// driver.close(); //closing child window
				driver.switchTo().window(parentWindow); // cntrl to parent window
			}
		}

		Thread.sleep(10000);
		// driver.close();
	}

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

	@Test
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

	public void AddUserdetails() throws InterruptedException, IOException {
		Thread.sleep(2000);
		InputStream input = new FileInputStream("src/main/resources/login.properties");
		Properties property = new Properties();
		property.load(input);
		WebElement sfusername = driver.findElement(By.id("InputUsername"));
		// manageusers.isEnabled();
		if (sfusername.isEnabled()) {
			Thread.sleep(2000);
			sfusername.sendKeys(property.getProperty("sfusername"));
		} else {
			System.out.println("SF Username Textbox is not enabled");
		}
		WebElement email = driver.findElement(By.id("InputUserEmail"));
		// manageusers.isEnabled();
		if (email.isEnabled()) {
			email.sendKeys(property.getProperty("sfemail"));
		} else {
			System.out.println("Email Textbox is not enabled");
		}

		WebElement role = driver.findElement(By.id("InputRoles"));
		Select dropdown = new Select(role);
		dropdown.selectByVisibleText(property.getProperty("role"));

		WebElement orgs = driver.findElement(By.id("InputDataRole"));
		Select dropdown2 = new Select(orgs);
		dropdown2.selectByVisibleText(property.getProperty("orgs"));

		WebElement status = driver.findElement(By.id("InputStatus"));
		Select dropdown3 = new Select(status);
		dropdown3.selectByVisibleText(property.getProperty("status"));
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

			// throw new IllegalStateException();
		} else {

			System.out.println("No issues");
			WebElement cancelbutton = driver
					.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/div/div[3]/button[1]"));
			cancelbutton.click();
			Thread.sleep(10000);
		}

		Thread.sleep(10000);
	}

	public void ManageOrgsVerify() throws InterruptedException {

		WebElement manageorgs = driver.findElement(By.xpath("/html/body/header/nav[1]/div/div[2]/div/div[2]/a/span"));
		// manageusers.isEnabled();
		if (manageorgs.isEnabled()) {
			Thread.sleep(2000);
			manageorgs.click();
			Thread.sleep(2000);
		} else {
			System.out.println("Manage Orgs is not enabled");
		}

	}

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

	public void CreateNewOrg() throws InterruptedException, IOException {
		InputStream input = new FileInputStream("src/main/resources/login.properties");
		Properties property = new Properties();
		property.load(input);
		WebElement orgname = driver.findElement(By.id("add-connection-orgname"));
		// manageusers.isEnabled();
		if (orgname.isEnabled()) {
			Thread.sleep(2000);
			orgname.sendKeys(property.getProperty("orgNickname"));
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs Nick Name TextBox is not enabled");
		}

		WebElement orgid = driver.findElement(By.id("add-connection-orgid"));
		// manageusers.isEnabled();
		if (orgid.isEnabled()) {
			Thread.sleep(2000);
			orgid.sendKeys(property.getProperty("orgid"));
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs Id TextBox is not enabled");
		}

		WebElement orgsusername = driver.findElement(By.id("add-connection-username"));
		// manageusers.isEnabled();
		if (orgsusername.isEnabled()) {
			Thread.sleep(2000);
			orgsusername.sendKeys(property.getProperty("orgsusername"));
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs UserName TextBox is not enabled");
		}

		WebElement orgspassword = driver.findElement(By.id("add-connection-password"));
		// manageusers.isEnabled();
		if (orgspassword.isEnabled()) {
			Thread.sleep(2000);
			orgspassword.sendKeys(property.getProperty("orgspassword"));
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs Password TextBox is not enabled");
		}

		WebElement consumerkey = driver.findElement(By.id("add-connection-consumerKey"));
		// manageusers.isEnabled();
		if (consumerkey.isEnabled()) {
			Thread.sleep(2000);
			consumerkey.sendKeys(property.getProperty("consumerkey"));
			Thread.sleep(2000);
		} else {
			System.out.println("Orgs ConsumerKey TextBox is not enabled");
		}

		WebElement consumersecret = driver.findElement(By.id("add-connection-consumerSecret"));
		// manageusers.isEnabled();
		if (consumersecret.isEnabled()) {
			Thread.sleep(2000);
			consumersecret.sendKeys(property.getProperty("consumersecret"));
			Thread.sleep(20000);
		} else {
			System.out.println("Orgs ConsumerSecret TextBox is not enabled");
		}
		WebElement submit = driver
				.findElement(By.xpath("/html/body/div[3]/div/div[4]/div/div/div[2]/div[3]/div/div[6]/button[2]"));
		// manageusers.isEnabled();
		if (submit.isEnabled()) {
			Thread.sleep(2000);
			submit.click();
			Thread.sleep(20000);
		} else {
			WebElement cancel = driver
					.findElement(By.xpath("/html/body/div[3]/div/div[4]/div/div/div[2]/div[3]/div/div[6]/button[1]"));
			cancel.click();
			Thread.sleep(20000);

		}

	}

	public void testlogs() throws InterruptedException, IOException {
		InputStream input = new FileInputStream("src/main/resources/login.properties");
		Properties property = new Properties();
		property.load(input);
		WebElement OrgName = driver.findElement(By.xpath("//input[contains(@id,'searchOrgInput')]"));
		OrgName.click();
		// WebElement OrgOption = driver.findElement(By.xpath("//*[contains(text(),
		// 'EEE')]"));
		WebElement OrgOption = driver
				.findElement(By.xpath("//*[contains(text(), '" + property.getProperty("orgoption") + "')]"));
		OrgOption.click();

		WebElement logtype = driver.findElement(By.xpath(
				"/html/body/div[3]/div/div/div[1]/div/div[1]/search-audit-box/div[2]/div/div/div[2]/div/div/select"));
		Select dropdown = new Select(logtype);
		// dropdown.selectByVisibleText("LOGS");
		dropdown.selectByVisibleText(property.getProperty("logtype"));
		Thread.sleep(5000);

		WebElement Logs = driver.findElement(By.xpath("//input[contains(@id,'searchObjectInput')]"));
		Logs.click();
		// WebElement LogsOption = driver.findElement(By.xpath("//*[contains(text(),
		// 'EmailMessage')]"));

		WebElement LogsOption = driver
				.findElement(By.xpath("//*[contains(text(), '" + property.getProperty("logsoption") + "')]"));
		LogsOption.click();

		WebElement Browse = driver.findElement(
				By.xpath("/html/body/div[3]/div/div/div[1]/div/div[1]/search-audit-box/div[2]/div/div/div[4]/button"));
		Browse.click();
		Thread.sleep(5000);

	}

	public void DriverCloseMethod() throws InterruptedException, IOException {

		driver.close();
	}
}
