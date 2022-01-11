package com.testcases;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.Logs.Log;
import com.TestBase.TestBase;
import com.Tudu.ActionDriver.Action;
import com.TuduApp.pages.loginPage;
import com.aventstack.extentreports.ExtentTest;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;



public class loginPageTest extends TestBase {
	
	//ExtentManager EM=new ExtentManager();
	loginPage lp;
	public ExtentTest test;
	//public ExtentHtmlReporter htmlReporter;
	//public ExtentReports extent;
	//public ExtentTest test;
	
	Action action=new Action();
	
	@BeforeMethod
	public void setup() throws Throwable {
	invoke();
	Log.startTestCase("loginPageTest");
	}
		
	@Test (priority=1,groups= {"vikesh2"},description="verifying login with page title verification")
	@Description("Scenario:- verifying login with page title verification")
	@Story("verify with Valid login details")
	@Step("verifing Title for page")
		
	public void Login_TC_001() throws Throwable {
		
		//report.createTest("loginPageTest");
		
		System.out.println("Launched Browser");
				
			Log.info("Ready to enter username and password");
						
		lp = new loginPage(driver);
			String uname=prop.getProperty("username");
			String pwd=	prop.getProperty("password");
					
			lp.login(uname,pwd );
			
		//driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
			Thread.sleep(9000);
			Log.info("Get Title of login page");
		String Title=driver.getTitle();
		
		System.out.println(Title);
		Log.info("Verifing Title ");
		
		Assert.assertEquals(Title, "TUDU1");
		Thread.sleep(2000);
		
	}
	
	@Test(enabled=false,dataProvider = "Login", dataProviderClass = loginPage.class)
	public void Login_TC_002(String uname, String password) throws Throwable {
		lp = new loginPage(driver);
		lp.Uname(uname);
		lp.pwd(password);
		lp.submit();
		
	}
	
	@Test(enabled=false)
	public void validateForgotPassword() {
		lp = new loginPage(driver);
		lp.forgetPassword();
		
	}
	
	@Test(enabled=false)
	public void validateSignUp() {		
		lp = new loginPage(driver);
		lp.signUp();
		
	}
	
	@AfterMethod
	public void teardown() {
		Log.endTestCase("loginPageTest");
		
		driver.close();
	}
		
	
}



