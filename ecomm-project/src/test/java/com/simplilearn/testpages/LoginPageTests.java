package com.simplilearn.testpages;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.simplilearn.pages.CreateAccountPage;
import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.LoginPage;
import com.simplilearn.pages.WelcomePage;
import com.simplilearn.util.Base;
import com.simplilearn.util.CommonFunctions;
import com.simplilearn.util.ExcelReader;

/**
 * @author rgurajapu
 * Tests related to login page
 */
public class LoginPageTests extends Base
{
	private static Logger Log = LogManager.getLogger(LoginPageTests.class.getName());
	
	private String email, password;
	private static final String INVALID_PASSWORD_MSG="Invalid password";
	private static final String COLOR="255, 255, 255";
	private CommonFunctions cmn= new CommonFunctions();
	public LoginPageTests() throws Exception{
		getLoginCreds();
	}
	public void getLoginCreds(){
		try {
			ExcelReader.setExcelFile();
			email =ExcelReader.getCellData(1, 1);
			password =ExcelReader.getCellData(1, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.error("Excel file read failed", e);
		}
		
		
	}
	
	//@BeforeTest
	public void beforeSuite() throws IOException
	{
		Base b= new Base();
		b.initialize();
	}
	
	@Test
	public void signUpTest()
	{
		
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		//getLoginCreds();
		String emailOut=cmn.generateRandomEmail(email);
		System.out.println("The email is: "+emailOut);
		loginPage.signup(emailOut);
	}
	
	/**
	 * Login to the account with valid credentials 
	 * Check if Log off menu appears on  top
	 */
	@Test
	public void signInTest()
	{
		
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		
		System.out.println("The email is: "+email);
		System.out.println("The password is: "+password);
		WelcomePage wp=loginPage.signin(email, password);
		Assert.assertTrue(wp.verifySignOutExists());
		@SuppressWarnings("unused")
		LoginPage lp=wp.signOff();
	}
	
	/**
	 * @throws InterruptedException
	 * Try Login with invalid credential
	 * Verify if the color of error message and the text is matching with some expectedvalue 
	 */
	@Test
	public void signInFailureTest() throws InterruptedException
	{
		
		HomePage homePage = new HomePage();
		//Thread.sleep(3000);
		LoginPage loginPage = homePage.signin();
		
		WebElement alert=loginPage.signinFailure(email, "test");
		Assert.assertTrue(alert.getText().contains(INVALID_PASSWORD_MSG));
		Assert.assertTrue(alert.getCssValue("color").contains(COLOR));
	}
	
	@SuppressWarnings("static-access")
	@AfterMethod
	public void teardown(ITestResult result) {
	
		if (result.FAILURE == result.getStatus()){	
			Log.info("Testcase failed");
			Log.info(result.getName());
			Log.info(result.getThrowable());
		}
		
		else
			Log.info("TEST PASSED " + result.getName());
		
		
	}
	
}
