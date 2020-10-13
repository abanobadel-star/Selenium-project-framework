package com.pixelogic.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends PageBase {  //class inherent from the parent class Test Base

	public Homepage(WebDriver driver) {  //this is constructor
		super(driver);
		
	}
	
	@FindBy(xpath="(//a[@id='dropdownCurrency'])[2]")
	WebElement myaccountselect;
	
	@FindBy(linkText="Login")
	 WebElement selectlogin;
	
	@FindBy(linkText="Sign Up")
	WebElement selectsignup;
	
	public void selectLoginORSingup() //method to select user can lofin or signup
	{
		
		myaccountselect.click();
		
	}
	
	public void clickonloginpage() //method  to click on login link to redirect user to login page
	{
		selectlogin.click();
	}
	
	public void clickonsignuppage() //method  to click on signup link to redirect user to signup page
	{
		//clickonbutton(selectsignup);
		selectsignup.click();
	}
	

}
