package com.simplilearn.pages;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.simplilearn.util.Account;

public class CreateAccountPage extends BasePage {
	private static Logger Log = LogManager.getLogger(CreateAccountPage.class.getName());
	//WebDriver driver;
	public CreateAccountPage() {
		PageFactory.initElements(driver, this);
		Log.info("Create Account Page is initialized");
	}
	//Locators
	@FindBy(how=How.ID, using="id_gender1")
	WebElement male;
	
	@FindBy(how=How.ID, using="id_gender2")
	WebElement female;
	
	@FindBy(how=How.ID, using="customer_firstname") 
	WebElement firstName;
	
	@FindBy(how=How.ID, using="customer_lastname") 
	WebElement lastName;
	
	@FindBy(how=How.ID, using="email") 
	WebElement email;
	
	@FindBy(how=How.ID, using="passwd") 
	WebElement password;
	//passwd
	
	@FindBy(how=How.ID, using="firstname") 
	WebElement addrFname;
	
	@FindBy(how=How.ID, using="lastname") 
	WebElement addrLname;
	
	@FindBy(how=How.ID, using="address1") 
	WebElement address1;
	
	@FindBy(how=How.ID, using="address2") 
	WebElement address2;
		
	@FindBy(how=How.ID, using="city")
	WebElement city;
	
	//id_state
	@FindBy(how=How.ID, using="id_state")
	WebElement state;
	
	
	@FindBy(how=How.ID,using="postcode")
	WebElement postcode;
	
	//id_country
	@FindBy(how=How.ID, using="id_country")
	WebElement country;
	
	@FindBy(how=How.ID, using="phone_mobile")
	WebElement mobile;
	
	@FindBy(how=How.ID, using="alias")
	WebElement addrAlias;
	
	@FindBy(how=How.ID, using="submitAccount")
	WebElement submit;
	
	@FindBy(how=How.XPATH, using="//div[@class='alert alert-danger']")
	WebElement errorAlert;
	
	public WelcomePage createAccountSuccess(Account acct){
		setGender(acct.getGender());
		firstName.sendKeys(acct.getFirstName());
		lastName.sendKeys(acct.getLastName());
		password.sendKeys(acct.getPassword());
		addrFname.sendKeys(acct.getAddrFname());
		addrLname.sendKeys(acct.getAddrLname());
		address1.sendKeys(acct.getAddress1());
		city.sendKeys(acct.getCity());
		mobile.sendKeys(acct.getMobile());
		setState(acct.getState());
		postcode.sendKeys(acct.getPostalCode().toString());
		Log.info("In create account: entering values");
		submit.click();
		return new WelcomePage();
	}
	
	public String createAccountFailure(Account acct) throws IOException, InterruptedException{
		setGender(acct.getGender());
		firstName.sendKeys(acct.getFirstName());
		lastName.sendKeys(acct.getLastName());
		password.sendKeys(acct.getPassword());
		addrFname.sendKeys(acct.getAddrFname());
		addrLname.sendKeys(acct.getAddrLname());
		address1.sendKeys(acct.getAddress1());
		city.sendKeys(acct.getCity());
		mobile.sendKeys(acct.getMobile());
		setState(acct.getState());
		postcode.sendKeys(acct.getPostalCode().toString());
		setCountry(acct.getCountry());
		Log.info("In create account: entering values");
		submit.click();
		
		if (errorAlert != null)
			return errorAlert.getText();
		else
			return "No Alert is thrown";
		
	}
	
	public void setState(String stateStr){
		
		Log.info("Setting the state dropdown");
		Select stateDropDown= new Select(state);
		stateDropDown.selectByVisibleText(stateStr);
		
	}
	public void setCountry(String countryStr){
		
		Log.info("Setting the country dropdown");
		Select stateDropDown= new Select(country);
		stateDropDown.selectByVisibleText(countryStr);
		
	}
	public void setGender(String gender){
		waitForElementToAppear(male);
		Log.info("Setting the gender radio button");
		if (gender.equalsIgnoreCase("male")){
			male.click();
		}else if (gender.equalsIgnoreCase("female")){
			female.click();
		}
	}
	
}
