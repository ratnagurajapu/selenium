package com.simplilearn.pages;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class WelcomePage extends BasePage{
	private static Logger Log = LogManager.getLogger(WelcomePage.class.getName());
	
	public WelcomePage() {
		
		PageFactory.initElements(driver, this);
		Log.info("BasePage is initialized");
	}
	
	
	@FindBy(how=How.XPATH, using="//p[@class='info-account']") 
	WebElement welcomeMsg;
	
	@FindBy(how=How.CSS, using="a.logout")
	WebElement signOut;
	
	@FindBy(how=How.XPATH, using="//a[@class='sf-with-ul'][contains(text(),'Women')]") 
	WebElement womenMenu;
	
	
	@FindBy(how=How.XPATH, using="//a[@class='sf-with-ul'][contains(text(),'Dresses')]") 
	WebElement dressesMenu;
	
	@FindBy(how=How.XPATH, using="//a[@class='subcategory-name'][contains(text(),'Summer Dresses')]")
	WebElement sumSubMenu;
		
	//li[@class='sfHoverForce']//ul//li//a[contains(text(),'Blouses')]
	@FindBy(how=How.XPATH, using="//ul[@class='submenu-container clearfix first-in-line-xs']//li//ul//a[contains(text(),'Summer Dresses')]")
	WebElement sumDressSubMenu;	
	
	@FindBy(how=How.XPATH, using="//div[@class='product-container']") 
	List<WebElement> products;
	
	
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Proceed to checkout')]") 
	WebElement checkout;
	
	@FindBy(how=How.XPATH, using="//a[@class='button btn btn-default standard-checkout button-medium']") 
	WebElement mainWinCheckout;
	
	
	@FindBy(how=How.XPATH, using="//button[@name='processAddress']") 
	WebElement processAddr;
	
	@FindBy(how=How.ID, using="cgv")
	WebElement tcCheckBox;
	
	@FindBy(how=How.XPATH, using="//button[@name='processCarrier']") 
	WebElement processCarrier;
	
	@FindBy(how=How.XPATH, using="//a[@class='cheque']") 
	WebElement payByCheck;
	
	@FindBy(how=How.XPATH, using="//button[@class='button btn btn-default button-medium']") 
	WebElement confirmOrder;
	
	@FindBy(how=How.XPATH, using="//div[@id='layer_cart']//div[@class='clearfix']")
	WebElement cart;
	
	@FindBy(how=How.XPATH, using="//p[@class='alert alert-success']")
	WebElement checkOutSuccessMsg;
	
	@FindBy(how=How.XPATH, using="//button[@class='btn btn-default btn-pinterest']")
	WebElement pinterest;
	
	@FindBy(how=How.XPATH, using="//ul[@class='submenu-container clearfix first-in-line-xs']//ul//li//a[contains(text(),'T-shirts')]")
	WebElement tshirtSubMenu;
	
	@FindBy(how=How.XPATH, using="//div[@id='subcategories']//li[1]//div[1]//a[1]")
	WebElement TShirt;
	
	@FindBy(how=How.XPATH, using="//input[@id='layered_category_7']")
			//div[@id='subcategories']//li[2]//div[1]//a[1]")
	WebElement Blouse;
	
	@FindBy(how=How.PARTIAL_LINK_TEXT, using="review")
	WebElement reviewLnk;
	
	@FindBy(how=How.ID, using="comment_title")
	WebElement title;
	
	@FindBy(how=How.ID, using="content")
	WebElement content;
	
	@FindBy(how=How.XPATH, using="//div[@class='fancybox-skin']")
	WebElement reviewBox;
	
	@FindBy(how=How.ID, using="submitNewMessage")
	WebElement sendBtn;
	//
	@FindBy(how=How.XPATH, using="//span[contains(text(),'OK')]")
	WebElement okBtn;
	
	@FindBy(how=How.XPATH, using="//p[contains(text(),'comment')]")
	WebElement reviewConfirmText;
	
	public String getWelcomeMsg(){
		waitForElementToAppear(welcomeMsg);
		Log.info("Welcome message is displayed");
		return welcomeMsg.getText();
	}
	
	public boolean verifySignOutExists(){
		waitForElementToAppear(signOut);
		Log.info("Sign Out exists");
		return signOut.isDisplayed();
	}
	
	public LoginPage signOff(){
		waitForElementToAppear(signOut);
		Log.info("Signing off");
		signOut.click();
		return new LoginPage();
	}
	
	public void clickOnSummerDresses(){
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", dressesMenu);
		Log.info("Clicked on Dresses");
		
		waitForElementToAppear(sumSubMenu);
		sumSubMenu.click();
		Log.info("Clicked on Summer Dresses");
		//sumDressSubMenu.click();
		//a.moveToElement(sumDressSubMenu).click().build().perform();
		//driver.findElement(By.linkText("Summer Dresses")).click();
		
	}
	
	public void clickOnTShirtViaHover(){
		waitForElementToAppear(womenMenu);
		Actions a = new Actions(driver);
		a.moveToElement(womenMenu).perform();
		a.moveToElement(tshirtSubMenu).click().build().perform();	
	}
	
	public void clickOnTShirt(){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", womenMenu);
		Log.info("Clicked on Women Menu");
		
		waitForElementToAppear(TShirt);
		TShirt.click();
		Log.info("Clicked on TShirt");
	}
	
	public void clickOnBlouse(){
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click();", Blouse);
		
		Log.info("Clicked on Blouse");
	}
	public void clickOnProduct() {
		// waitForElementToAppear(products.get(0));
		if (products.size() > 0) {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();", products.get(0));
			
			Actions a = new Actions(driver);
			a.moveToElement(products.get(0)).perform();
			//Thread.sleep(5000);
			jse.executeScript("arguments[0].click();",driver.findElements(By.xpath("//a[@class='product_img_link']"))
					.get(0));
			
			//a.moveToElement(driver.findElements(By.xpath("//a[@class='product_img_link']"))
				//	.get(0)).click().build().perform();
			//driver.findElements(By.xpath("//a[@class='product_img_link']"))
			//		.get(0).click();
		}
	}

	
	public void clickOnProductViaChrome(){
		if (products.size() > 0) {
			Actions a = new Actions(driver);
			a.moveToElement(products.get(0)).perform();
			driver.findElements(By.xpath("//a[@class='product_img_link']"))
					.get(0).click();
		}
	}
	public void writeReview(String titleStr, String commentStr) throws IOException, InterruptedException{
		waitForElementToAppear(reviewLnk);
		reviewLnk.click();
		Log.info("Clicked on Review from product page");
		waitForElementToAppear(reviewBox);
		Actions a = new Actions(driver);
		a.moveToElement(reviewBox).perform();
		title.sendKeys(titleStr);
		content.sendKeys(commentStr);
		Log.info("Entered title and comment");
		sendBtn.click();
		
		waitForElementToAppear(reviewBox);
		a.moveToElement(reviewBox).perform();
		waitForElementToAppear(okBtn);
		
		System.out.println(reviewConfirmText.getText());
		okBtn.click();
		
		
				
	}
	
	public void addToCart_to_be_deleted(String[] items){
		waitForElementToAppear(products.get(0));
		List<String> productNames= Arrays.asList(items);
        int j=0;
        for(int i=0; i<products.size(); i++){
        	String[] names= products.get(i).getText().split("\n");
        	
        	String productName=names[0].trim();
        	System.out.println("The productName is "+ productName);
        	
        	if(productNames.contains(productName)){
        		System.out.println("Matched product "+ productName);
        		Actions a = new Actions(driver);
        		a.moveToElement(products.get(i)).perform();
        		driver.findElements(By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']")).get(i).click();
        		//li[@class='ajax_block_product col-xs-12 col-sm-4 col-md-3 first-in-line first-item-of-tablet-line first-item-of-mobile-line']//a[@class='product-name'][contains(text(),'Faded Short Sleeve T-shirts')]
        		j++;
        		if (j == productNames.size())
        			break;
        	}
        	
        }
	}
	
	
	public void addToCart(String[] items) throws InterruptedException{
		Log.info("Adding to cart..");
		System.out.println("The number of products :"+ products.size());
		List<String> productNames= Arrays.asList(items);
		System.out.println("The productNames are: "+ productNames);
        int j=0;
        for(int i=0; i<products.size(); i++){
        	
        	String[] names= products.get(i).getText().split("\n");
        	
        	String productName=names[0].trim();
        	System.out.println("The productName and i are "+ productName+"\t"+i);
        	
        	if(productNames.contains(productName)){
        		System.out.println("Matched product and j are: "+ productName+"\t"+j);
        		JavascriptExecutor jse = (JavascriptExecutor)driver;
        		jse.executeScript("arguments[0].scrollIntoView();", products.get(i));
        		Actions a = new Actions(driver);
        		a.moveToElement(products.get(i)).perform();
        		jse.executeScript("arguments[0].click();",driver.findElements(By.xpath("//a[@class='button ajax_add_to_cart_button btn btn-default']"))
    					.get(i));
        		
        		j++;
        		if (j == products.size())
        			break;
        	}
        	
        }
        
        Thread.sleep(1000);
        /*
        try {
			screenShot("AddToCart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.error("ScreenShot failed", e);
			
		}
        Log.info("Screenshot taken..");
        */
	}
	
	
	public CheckoutPage checkOut(){
        
		waitForElementToAppear(cart);
        Actions a= new Actions(driver);
        a.moveToElement(cart).perform();
        System.out.println("Move to element passed");
        checkout.click();
        Log.info("Clicked on checkout from cart page");
        return new CheckoutPage();
        
		
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
			Log.info("Test passed " + result.getName());
		
		
	}

	
}
