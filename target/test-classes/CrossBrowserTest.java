package com.xc.test;
import java.io.FileInputStream;
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
	
//	@Test
//	public void Test1() throws InterruptedException, IOException{
//		
//		InputStream input = new FileInputStream ("C:/Users/Myanaganti/XCavate-workspace/XCavateProject/property.txt");
//		Properties property = new Properties();
//		property.load(input);
//		
//		//driverManager = DriverManagerFactory.getDriverManager(DriverType.FIREFOX);
//		//driver = driverManager.getWebDriver();
//		driver.get("https://mobile.xcavate.us/landing");
//		//Assert.assertEquals("XCavate - Landing",driver.getTitle());	
//		Thread.sleep(2000);
//	    WebElement Loginbutton = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/div/div/div[3]/div[1]/div[2]/img"));
//	    Loginbutton.click();
//	    Thread.sleep(10000);
//	    String MainWindow=driver.getWindowHandle();  
//        
//        // To handle all new opened window.    
//            Set<String> s1=driver.getWindowHandles();  
//        Iterator<String> i1=s1.iterator();  
//          
//        while(i1.hasNext())   
//        {  
//            String ChildWindow=i1.next();  
//              
//            if(!MainWindow.equalsIgnoreCase(ChildWindow))   
//            {      
//                 
//                    // Switching to Child window
//                    driver.switchTo().window(ChildWindow);                                                                                                            
//                    driver.findElement(By.id("username"))
//                    .sendKeys(property.getProperty("username")); 
//                    driver.findElement(By.name("pw")).sendKeys(property.getProperty("password"));
//                    
//                    driver.findElement(By.name("Login")).click();   
//                                 
//   // Closing the Child Window.
//                        //driver.close();  
//            }  
//        }  
//        // Switching to Par
//        
//        Thread.sleep(10000);
//        driver.switchTo().window(MainWindow);
//        
//        
//	    
//	   // driverManager.quitWebDriver();
//				
//	}
//
//}
//
//
//}
