package com.simplilearn.testpages;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.simplilearn.pages.CheckoutPage;
import com.simplilearn.pages.HomePage;
import com.simplilearn.pages.LoginPage;
import com.simplilearn.pages.WelcomePage;
import com.simplilearn.util.Base;
import com.simplilearn.util.ExcelReader;

/**
 * @author rgurajapu
 * Tests for purchasing products and writing review
 */
public class WelcomePageTests extends Base
{
	private String email, password;
	
	private static final String CHECKOUT_SUCCESS_MSG="Your order on My Store is complete.";
	private static final String[] products= {"Printed Summer Dress", "Printed Chiffon Dress"};	
	private static final String[] tshirts= {"Faded Short Sleeve T-shirts"};
	private static final String[] blouses= {"Blouse"};
	
	private static Logger Log = LogManager.getLogger(WelcomePageTests.class.getName());
	
	public WelcomePageTests() throws Exception{
		getLoginCreds();
	}
	public void getLoginCreds() throws Exception{
		ExcelReader.setExcelFile();
		email =ExcelReader.getCellData(1, 1);
		password =ExcelReader.getCellData(1, 2);
		
	}
	
	@BeforeClass
	public void beforeSuite() throws IOException
	{
			Base b= new Base();
			b.initialize();
		
	}
	
	/**
	 * @throws InterruptedException
	 *  Purchase 3 products each from different category, checkout, and place theorder
	 *  Ensure that order is placed by verifying the message disaplyed
	 */
	@Test 
	public void purchaseProducts() throws InterruptedException{
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		WelcomePage wp=loginPage.signin(email, password);
		
		wp.clickOnSummerDresses();
		String[] products= {"Printed Summer Dress", "Printed Chiffon Dress"};
		wp.addToCart(products);
		CheckoutPage cp=wp.checkOut();
		String result=cp.processCheckOut();
		wp.signOff();
		Assert.assertEquals(result, CHECKOUT_SUCCESS_MSG);
	}
	
	
	@Test 
	public void purchaseDiffProducts() throws InterruptedException{
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		WelcomePage wp=loginPage.signin(email, password);
		
		wp.clickOnSummerDresses();
		wp.addToCart(products);
		wp.clickOnTShirt();
		wp.addToCart(tshirts);
		wp.clickOnBlouse();
		wp.addToCart(blouses);
		CheckoutPage cp=wp.checkOut();
		
		String result=cp.processCheckOut();
		wp.signOff();
		Assert.assertEquals(result, CHECKOUT_SUCCESS_MSG);
	}
	/**
	 * Write a review test
	 * @throws IOException 
	 */
	@Test 
	public void writeReviewTest() throws InterruptedException, IOException{
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		WelcomePage wp=loginPage.signin(email, password);
		wp.clickOnSummerDresses();
		//wp.clickOnTShirt();
		wp.clickOnProduct();
		
		wp.writeReview("review", "The product fit and look was very good");
		wp.signOff();
		
	}
	
	/**
	 * Click on Log Off button and verify sign in link
	 */
	@Test
	public void signOutTest() 
	{
		
		HomePage homePage = new HomePage();
		LoginPage loginPage = homePage.signin();
		
		System.out.println("The email is: "+email);
		System.out.println("The password is: "+password);
		WelcomePage wp=loginPage.signin(email, password);
		LoginPage p =wp.signOff();
		Assert.assertTrue(p.verifySignInExists());
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
