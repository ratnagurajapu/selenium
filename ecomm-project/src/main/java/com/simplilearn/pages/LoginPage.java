package com.simplilearn.pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
	private static Logger Log = LogManager.getLogger(LoginPage.class.getName());
	
	public LoginPage() {
		
		PageFactory.initElements(driver, this);
		Log.info("Login Page is initialized");
	}
	
	//Locators
	@FindBy(how=How.ID, using="email_create") 
	WebElement signUpEmail;
	
	@FindBy(how=How.ID, using="SubmitCreate") 
	WebElement createAccountBtn;
	
	@FindBy(how=How.ID, using="email") 
	WebElement email;
	
	@FindBy(how=How.ID, using="passwd") 
	WebElement password;
	
	@FindBy(how=How.ID, using="SubmitLogin") 
	WebElement signInButton;
	
	@FindBy(how=How.XPATH, using="//div[@class='alert alert-danger']") 
	WebElement errorAlert;
	
	@FindBy(how=How.CSS, using="a.login")
	WebElement signin;
	
	
	public CreateAccountPage signup(String email){
		waitForElementToAppear(signUpEmail);
		signUpEmail.sendKeys(email);
		createAccountBtn.click();
		Log.info("In Login Page:signup with email---");
		return new CreateAccountPage();
	}
	
	public WelcomePage signin(String emailStr, String passwd){
		waitForElementToAppear(email);
		email.sendKeys(emailStr);
		password.sendKeys(passwd);
		signInButton.click();
		Log.info("Sign in with email and password");
		return new WelcomePage();
	}
	public WebElement signinFailure(String emailStr, String passwd){
		waitForElementToAppear(email);
		email.sendKeys(emailStr);
		password.sendKeys(passwd);
		signInButton.click();
		Log.info("Sign in with valid email and invalid password");
		return errorAlert;
	}
	
	public boolean verifySignInExists(){
		waitForElementToAppear(signin);
		Log.info("Sign In exists");
		return signin.isDisplayed();
	}
	
	
}
