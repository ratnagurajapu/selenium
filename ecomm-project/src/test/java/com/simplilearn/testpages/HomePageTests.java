package com.simplilearn.testpages;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.LoginPage;
import com.simplilearn.util.Base;

/**
 * @author rgurajapu
 * Tests related to Home Page of automation practice site
 */
public class HomePageTests extends Base {
	private final static String TEXT_TO_SEARCH = "printed";
	private final static String NO_OF_PRODUCTS = "5";
	private final static String MANUFACTURER = "Fashion Manufacturer";
	private final static String EMAIL = "test@simplilearn.com";
	private final static String PASSWORD = "Test1234";
	
	private static Logger Log = LogManager.getLogger(HomePageTests.class.getName());

	/**
	 * Initialize browser before the test suite
	 * @throws IOException
	 */
	@BeforeSuite
	public void beforeSuite() throws IOException {
		Base b = new Base();
		b.initialize();
	}

	//@AfterSuite
	public void afterSuite() {
		Base b = new Base();
		b.afterSuite();
	}

	/**
	 *  Click on sign in link from home page
	 *  
	 */
	@Test
	public void signinTest() {
		HomePage homePage = new HomePage();
		homePage.signin();
	}

	/**
	 * Functional Area: Search
	 * Type any word in search field and verify number of items displayed
	 */
	@Test
	public void searchTest() {
		HomePage homePage = new HomePage();
		homePage.search(TEXT_TO_SEARCH);
		String productOutStr = homePage.getSearchOutputStr();
		String expectedSearchOut = "Showing 1 - " + NO_OF_PRODUCTS + " of "
				+ NO_OF_PRODUCTS + " items";
		Assert.assertEquals(productOutStr, expectedSearchOut);
	}
	
	/**
	 * Functional Area: Search
	 * Type any manufacturer in search field and verify number of items displayed
	 * @throws Exception 
	 */
	@Test
	public void searchByManufacturer() {
		HomePage homePage = new HomePage();
		homePage.search(TEXT_TO_SEARCH);
		homePage.selectManufacturer(MANUFACTURER);
		String manuOutStr = homePage.getManufacturerOutputStr();
		// String expectedSearchOut =
		// "Showing 1 - "+NO_OF_PRODUCTS+" of "+NO_OF_PRODUCTS+" items";
		Assert.assertTrue(manuOutStr.contains(MANUFACTURER.toUpperCase()),
				"Manufacturer product list not displayed");
	}

	/**
	 * Share product via Pinterest
	 * @throws InterruptedException
	 */
	@Test
	public void pinItTest() throws InterruptedException {
		HomePage homePage = new HomePage();
		homePage.clickOnAnyProduct();
		String[] creds = homePage.pinIt(EMAIL, PASSWORD);
		if (creds != null && creds.length > 1) {
			Assert.assertEquals(creds[0], EMAIL);
			Assert.assertEquals(creds[1], PASSWORD);
		}
	}

	/**
	 * Share product via facebook
	 */
	@Test
	public void fbTest() {
		HomePage homePage = new HomePage();
		homePage.clickOnAnyProduct();
		String[] creds = homePage.fbLogin(EMAIL, PASSWORD);
		if (creds != null && creds.length > 1) {
			Assert.assertEquals(creds[0], EMAIL);
			Assert.assertEquals(creds[1], PASSWORD);
		}
	}

	/**
	 * Share product via Google
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	@Test
	public void googleTest() {
		HomePage homePage = new HomePage();
		homePage.clickOnAnyProduct();
		String result = homePage.googleLogin(EMAIL);
		if (result != null) {
			Assert.assertEquals(result, EMAIL);
			
		}
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
