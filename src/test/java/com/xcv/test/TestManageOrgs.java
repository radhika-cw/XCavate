package com.xcv.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.testng.annotations.Test;

public class TestManageOrgs {
	XcavateMain login =new XcavateMain();
	
	@Test(priority=1)
	public void TestingSetup() throws Exception {	
		InputStream input = new FileInputStream("src/main/resources/login.properties");
		Properties property = new Properties();
		property.load(input);
	login.setup(property.getProperty("browser"));
	}
	@Test(priority=2)
	public void TestingLoginXC() throws Exception {		
	login.testLoginXC();	
	}
	@Test(priority=3)
	public void ManageOrgsVerify() throws Exception {		
	login.ManageOrgsVerify();	
	}
	@Test(priority=4)
	public void NewOrgAdd() throws Exception {		
	login.NewOrgAdd();	
	}
	@Test(priority=5)
	public void CreateNewOrg() throws Exception {		
	login.CreateNewOrg();	
	}
	@Test(priority=6)
	public void DriverCloseMethod() throws Exception {		
	login.DriverCloseMethod();	
	}		

}
