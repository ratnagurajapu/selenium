package com.simplilearn.pages;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage{
	private static Logger Log = LogManager.getLogger(CheckoutPage.class.getName());
	
	public CheckoutPage() {
		
		PageFactory.initElements(driver, this);
		Log.info("CheckOut Page is initialized");
	}
	
	
	@FindBy(how=How.CSS, using="a.logout")
	WebElement signOut;
	
	
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
	
		
	public String processCheckOut(){
        
		waitForElementToAppear(mainWinCheckout);
       
    	mainWinCheckout.click();
    	processAddr.click();
    	tcCheckBox.click();
    	processCarrier.click();
    	payByCheck.click();
    	confirmOrder.click();
    	
    	return checkOutSuccessMsg.getText();
    	
		
	}

	
}
