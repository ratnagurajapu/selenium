package com.simplilearn.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.BeforeSuite;





import com.simplilearn.pages.BasePage;

public class Base {
	
	private static WebDriver driver;
	private static WebDriverWait wait;
	private Properties props = new Properties();
	private String appURL;
	
	//protected BasePage basePage;
	private static Logger Log = LogManager.getLogger(Base.class.getName());
	
	private static final int TIMEOUT = 15;
	private static final int POLLING = 100;
	private static final String path="/src/main/resources";
	private static final String filename="project.properties";
	//Read the browser from properties file
	//@BeforeSuite
	public void initialize() throws IOException
	{
		String completePath= System.getProperty("user.dir")+path+"/"+filename;
		
		FileInputStream fs = new FileInputStream(completePath);
		props.load(fs);
		String browserName=props.getProperty("browser");
		appURL = props.getProperty("url");
		
		
		System.out.println(browserName);
		System.out.println(appURL);
		
		
		if (browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\seleniumdrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			Log.info("Chrome Driver is initialized");
		}
		else if (browserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","C:\\seleniumdrivers\\geckodriver.exe");
			
			driver = new FirefoxDriver();
			Log.info("Firefox Driver is initialized");
		}
		driver.get(appURL);
		Log.info("Navigated to home page");
		wait = new WebDriverWait(driver, TIMEOUT, POLLING);
		BasePage basePage = new BasePage();
		basePage.setWebDriver(driver, wait);
		
	}
	
	//@AfterSuite
	public void afterSuite()
	{
		if(driver != null){
			driver.close();
			driver.quit();
		}
	}
	
	
	public WebDriver getDriver(){
		return driver;
	}
	
	
	
	public String getAppURL()
	{
		return appURL;
	}
	
	
}
