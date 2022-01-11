package com.testcases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.Logs.Log;
import com.TestBase.TestBase;
import com.TuduApp.pages.loginPage;
import com.TuduApp.pages.signupPage;

import io.qameta.allure.Description;
import io.qameta.allure.Step;

public class signUpTest extends TestBase {

	loginPage lp;
	signupPage su;
	

	@BeforeMethod
	public void setup() throws Throwable {

		invoke();
		pageObj();
		Log.startTestCase("signUpTest");
	}

	public void pageObj() throws Throwable {
		lp = new loginPage(driver);
		lp.signUp();
		
		su = new signupPage(driver);
	
	}

		
	@Test(priority=0,groups= {"vikesh2"},description="Verifing sign up page details",dataProvider = "signup", dataProviderClass = signupPage.class)
	@Description("Scenario:- Adding Sign up details")
	@Step("sign up details filled - {0},{1},{2},{3},{4},{5},{6},{7},{8}")
		public void signUpDetails(String fName, String lName, String orgName, String claimUrl, String orgEmail,
			String workEmail, String website, String signUpPassword) throws Throwable {
		 	 
		 su.signUpFirstName(fName);	
		 su.signUpLastName(lName);
		 su.signUporganizationName(orgName);
		 su.signUpclaimYourUrl(claimUrl); 
		 su.signUporganizationEmail(orgEmail);
		 su.signUpWorkEmail(workEmail); 
		 su.signUpwebsiteUrl(website);
		 su.signUpPassword(signUpPassword);
		 Thread.sleep(3000);
		 su.createMyAccount();
		 Thread.sleep(2000);
		 
		}
	
	@AfterMethod
	public void teardown() {
		Log.endTestCase("signUpTest");
		driver.close();
	}

	
}
