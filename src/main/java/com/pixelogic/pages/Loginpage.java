package com.pixelogic.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends PageBase{   //class inherent from the parent class Test Base

	public Loginpage(WebDriver driver) { //this is constructor
		super(driver);
		
	}
	
	@FindBy(name="username")  //to find element
	WebElement usernametxtbox;
	
	@FindBy(name="password")
	WebElement passwordtxtbox;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginbutton;
	
	public void userlogin(String email, String password) //method to login module
	{
		setTextElementText(usernametxtbox,email);  //to send text to  email element
		setTextElementText(passwordtxtbox, password); //to send text to password element
		loginbutton.click();   //to click on login button
		//clickonbutton(loginbutton);
	}

	

}
