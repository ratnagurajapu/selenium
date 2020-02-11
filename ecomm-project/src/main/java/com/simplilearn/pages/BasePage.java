package com.simplilearn.pages;


import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;


public class BasePage {
	
	private static final String path="/ScreenShots/";
	
	protected static WebDriver driver;
	protected static WebDriverWait webDriverWait;
	private static Logger Log = LogManager.getLogger(BasePage.class.getName());
	public BasePage()
	{
		PageFactory.initElements(driver, this);
		//WebDriverWait webWait = new WebDriverWait();
		Log.info("BasePage is initialized");
	}
	
	public void setWebDriver(WebDriver driver, WebDriverWait w){
		BasePage.driver= driver;		
		BasePage.webDriverWait = w;
		
	}
	
	protected void waitForElementToAppear(WebElement state){
		
		webDriverWait.until(ExpectedConditions.visibilityOf(state));
		
	}
	protected boolean isElementVisible(WebElement ele) {
	    try {

	        if (ele.isEnabled()) {

	            return true;

	        } else {

	            return false;
	        }

	    } catch (Exception e) {

	        e.printStackTrace();
	        return false;
	    }
	}
	
	protected void screenShot(String filename) throws IOException {
	       
	    
	    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
        Date date = new Date();
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String completePath= System.getProperty("user.dir")+path+filename+"-"+dateFormat.format(date)+".png";
        //FileUtils.copyFile(scrFile, new File("C:\\Users\\rgurajapu\\Selenium\\ecomm-project\\ScreenShots\\"+filename+"-"+dateFormat.format(date)+".png"));
        FileUtils.copyFile(scrFile, new File(completePath));
         
	}

}
