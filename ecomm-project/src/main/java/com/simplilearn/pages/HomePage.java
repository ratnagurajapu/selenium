package com.simplilearn.pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class HomePage extends BasePage {
	private static Logger Log = LogManager.getLogger(HomePage.class.getName());

	public HomePage() {

		PageFactory.initElements(driver, this);
		Log.info("HomePage is initialized");
	}

	@FindBy(how = How.XPATH, using = "//a[@class='login']")
	WebElement signin;

	@FindBy(how = How.ID, using = "search_query_top")
	WebElement searchText;

	@FindBy(how = How.XPATH, using = "//button[@name='submit_search']")
	WebElement searchBtn;

	@FindBy(how = How.XPATH, using = "//div[@class='top-pagination-content clearfix']//div[@class='product-count'][contains(text(),'Showing')]")
	WebElement productCountStr;

	@FindBy(how = How.XPATH, using = "//select[@name='manufacturer_list']")
	WebElement manufacturer;

	@FindBy(how = How.CSS, using = "h1.page-heading.product-listing")
	WebElement manufacturerStr;

	// h1[@class='page-heading product-listing']
	@FindBy(how = How.XPATH, using = "//div[@class='product-container']")
	List<WebElement> products;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-default btn-pinterest']")
	WebElement pinterest;

	@FindBy(how = How.XPATH, using = "//button[contains(@class, 'facebook')]")
	WebElement fb;

	@FindBy(how = How.XPATH, using = "//button[contains(@class, 'google-plus')]")
	WebElement google;
	
	@FindBy(how = How.XPATH, using = "//input[@id='identifierId']")
	WebElement gmailId;
	
	@FindBy(how = How.XPATH, using = "//button[@class='RCK Hsu mix F10 xD4 GmH adn Il7 Jrn hNT iyn BG7 gn8 L4E kVc']")
	WebElement pinLogin1;
	
	@FindBy(how = How.XPATH, using = "//button[@class='RCK Hsu mix F10 xD4 GmH adn Il7 Jrn hNT iyn BG7 gn8 L4E kVc']")
	List<WebElement> pinLogins;
	
	@FindBy(how = How.XPATH, using = "//div[@class='tBJ dyH iFc SMy yTZ pBj tg7 mWe']")
	WebElement pinLogin2;
	
	@FindBy(how = How.ID, using = "email")
	WebElement emailField;
	
	@FindBy(how = How.ID, using = "password")
	WebElement pinPassword;
	
	@FindBy(how = How.ID, using = "pass")
	WebElement fbPassword;
	
	public LoginPage signin() {
		waitForElementToAppear(signin);
		Log.info("Before clicking on Sign In from home page");
		signin.click();
		return new LoginPage();
	}

	public void search(String str) {
		waitForElementToAppear(searchText);
		Log.info("Searching for text: " + str);
		searchText.sendKeys(str);
		searchBtn.click();
	}

	public String getSearchOutputStr() {
		waitForElementToAppear(productCountStr);
		return productCountStr.getText();
	}

	public String getManufacturerOutputStr() {
		waitForElementToAppear(manufacturerStr);
		return manufacturerStr.getText();
	}

	public void selectManufacturer(String manuStr)  {
		// waitForElementToAppear(manufacturer);
		Log.info("Searching for manufacturer: " + manuStr);
		manufacturer.click();
		Select s = new Select(manufacturer);
		s.selectByVisibleText(manuStr);
		
	}

	public void clickOnAnyProduct() {
		Log.info("Click on any product.. ");
		// waitForElementToAppear(products.get(0));
		if (products.size() > 0) {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView();", products.get(0));
			
			Actions a = new Actions(driver);
			a.moveToElement(products.get(0)).perform();
			
			jse.executeScript("arguments[0].click();",driver.findElements(By.xpath("//a[@class='product_img_link']"))
					.get(0));
			
			//a.moveToElement(driver.findElements(By.xpath("//a[@class='product_img_link']"))
				//	.get(0)).click().build().perform();
			//driver.findElements(By.xpath("//a[@class='product_img_link']"))
			//		.get(0).click();
		}
	}

	public String[] pinIt(String email, String passwd) throws InterruptedException {
		String[] result = new String[2];
		waitForElementToAppear(pinterest);
		pinterest.click();
		Log.info("Clicked on Pinterest.. ");
		System.out.println("The number of win handles: "
				+ driver.getWindowHandles().size());
		String mainWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String pinterestWindow = itr.next();
			if (!mainWindow.equals(pinterestWindow)) {
				driver.switchTo().window(pinterestWindow);
				Log.info("Switched to pinterest window.. ");
				// Wait for Pinterest Login button to appear
					
				Thread.sleep(5000);
				if(pinLogins.size()>0){
					pinLogin1.click();
				}else {
					pinLogin2.click();
				}
				// Enter email and password
				emailField.sendKeys(email);
				pinPassword.sendKeys(passwd);
				
				result[0]= emailField.getAttribute("value");
				result[1]= pinPassword.getAttribute("value");
				
				System.out.println("The email and password are :" + result[0]
						+ "\t" + result[1]);
				driver.close();
			}
		}
		// This is to switch to the main window
		driver.switchTo().window(mainWindow);
		return result;
	}

	public String[] fbLogin(String email, String passwd) {
		String[] result = new String[2];
		waitForElementToAppear(fb);
		fb.click();
		System.out.println("The number of win handles: "
				+ driver.getWindowHandles().size());
		String mainWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String fbWindow = itr.next();
			if (!mainWindow.equals(fbWindow)) {
				driver.switchTo().window(fbWindow);
				Log.info("Switched to facebook window.. ");
				// Enter email and password
				waitForElementToAppear(emailField);
				emailField.sendKeys(email);
				fbPassword.sendKeys(passwd);
				
				result[0]=emailField.getAttribute("value");
				result[1]=fbPassword.getAttribute("value");
				System.out.println("The email and password are :" + result[0]
						+ "\t" + result[1]);
				driver.close();
			}
		}
		// This is to switch to the main window
		driver.switchTo().window(mainWindow);
		return result;
	}

	public String googleLogin(String email) {
		String result = new String();
		waitForElementToAppear(google);
		google.click();
		System.out.println("The number of win handles: "
				+ driver.getWindowHandles().size());
		String mainWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String googleWindow = itr.next();
			if (!mainWindow.equals(googleWindow)) {
				driver.switchTo().window(googleWindow);	
				Log.info("Switched to google window.. ");
				// Enter email and password
				waitForElementToAppear(gmailId);
				gmailId.sendKeys(email);
				
				result=gmailId.getAttribute("value");
				
				System.out.println("The email entered is :" + result);
						
				driver.close();
			}
		}
		// This is to switch to the main window
		driver.switchTo().window(mainWindow);
		return result;
	}

	
	
}
