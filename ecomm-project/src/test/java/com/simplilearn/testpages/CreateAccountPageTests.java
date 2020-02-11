package com.simplilearn.testpages;

import java.io.IOException;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.simplilearn.pages.CreateAccountPage;
import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.LoginPage;
import com.simplilearn.pages.WelcomePage;
import com.simplilearn.util.Account;
import com.simplilearn.util.Base;
import com.simplilearn.util.ExcelReader;
import com.simplilearn.util.CommonFunctions;

/**
 * All the tests related to create account page
 * @author rgurajapu
 *
 */
public class CreateAccountPageTests extends Base
{
	
	
	private static final String WELCOME_MSG="Welcome to your account. Here you can manage all of your personal information and orders.";
	private static final String INVALID_COUNTRY_MSG= "Country is invalid";
	private static final String INVALID_POSTALCODE_MSG= "The Zip/Postal code you've entered is invalid. It must follow this format: 00000";
	
	private static Logger Log = LogManager.getLogger(CreateAccountPageTests.class.getName());
	
	CommonFunctions cmn= new CommonFunctions();
	@BeforeTest
	public void beforeSuite() throws IOException
	{
		Base b= new Base();
		b.initialize();
	}
	/**
	 * Testcase: Account->Create a new account with EthnicStore
	 * Success if you get a verifying  message
	 * @throws Exception
	 */
	@Test
	public void createAccountTest() throws Exception
	{
		
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		ExcelReader.setExcelFile();
		String email =ExcelReader.getCellData(1, 1);
		email=cmn.generateRandomEmail(email);
		CreateAccountPage acctPage = loginPage.signup(email);
		
		Account acct=loadAccountData(2);
		WelcomePage wPage=acctPage.createAccountSuccess(acct);
		String welcomeMsg= wPage.getWelcomeMsg();
		Assert.assertEquals(welcomeMsg, WELCOME_MSG);
		wPage.signOff();
	}
	
	@Test
	/* Verify the alert when country field
	 * is left blank.
	 **/ 
	public void createAccountTestNoCountry() throws Exception
	{
		ExcelReader.setExcelFile();
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		ExcelReader.setExcelFile();
		String email =ExcelReader.getCellData(1, 1);
		email=cmn.generateRandomEmail(email);
		CreateAccountPage acctPage = loginPage.signup(email);
		//Thread.sleep(3000);
		Account acct=loadAccountData(3);
		String error =acctPage.createAccountFailure(acct);
		Assert.assertTrue(error.contains(INVALID_COUNTRY_MSG));
	}
	
	@Test
	/* Verify the alert when postalcode field
	 * is invalid.
	 **/ 
	public void createAccountTestInvalidPostalCode() throws Exception
	{
		ExcelReader.setExcelFile();
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		ExcelReader.setExcelFile();
		String email =ExcelReader.getCellData(1, 1);
		email=cmn.generateRandomEmail(email);
		CreateAccountPage acctPage = loginPage.signup(email);
		//Thread.sleep(3000);
		Account acct=loadAccountData(4);
		String error =acctPage.createAccountFailure(acct);
		Assert.assertTrue(error.contains(INVALID_POSTALCODE_MSG));
	}
	
	/**
	 * @param row
	 * @return
	 * @throws Exception
	 * Method to load account data from excel to Account Object
	 */
	public Account loadAccountData(int row) throws Exception
	{
		Account acct = new Account();
		ExcelReader.setExcelFile();
		String gender =ExcelReader.getCellData(row, 1);
		
		String firstName =ExcelReader.getCellData(row, 2);
		String lastName =ExcelReader.getCellData(row, 3);
		String password =ExcelReader.getCellData(row, 5);
		String addrFname =ExcelReader.getCellData(row, 6);
		
		String addrLname =ExcelReader.getCellData(row, 7);
		
		String address1 =ExcelReader.getCellData(row, 8);
		String city =ExcelReader.getCellData(row, 9);
		String state =ExcelReader.getCellData(row, 10);
		String mobile =ExcelReader.getCellData(row, 11);
		Integer postalCode =ExcelReader.getCellDataInt(row, 12);
		System.out.println("The postalcode is "+ postalCode);
		String country= ExcelReader.getCellData(row, 13);
		
		acct.setGender(gender);
		acct.setFirstName(firstName);
		acct.setLastName(lastName);
		acct.setPassword(password);
		acct.setAddrFname(addrFname);
		acct.setAddrLname(addrLname);
		acct.setAddress1(address1);
		acct.setCity(city);
		acct.setState(state);
		acct.setMobile(mobile);	
		acct.setPostalCode(postalCode);
		acct.setCountry(country);
		
		return acct;
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
