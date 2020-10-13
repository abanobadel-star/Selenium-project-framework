package com.pixelogic.pages;


import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Registerpage extends PageBase { //this class "chlid" inherent from page Base class "parent"

	public JavascriptExecutor js;
	public Registerpage(WebDriver driver) { //this is constructor
		super(driver);
		js=((JavascriptExecutor)driver);
	}

	@FindBy(name="firstname")    //to find element 
	public WebElement firstnametxtbox;

	@FindBy(name="lastname")
	 public WebElement lastnametxtbox;

	@FindBy(name="phone")
	public WebElement phonenumbertxtbox;

	@FindBy(name="email")
	public WebElement emailtxtbox;

	@FindBy(name="password")
	public WebElement passwordtxtbox;

	@FindBy(name="confirmpassword")
	public WebElement confirmpasswordtxtbox;

	@FindBy(xpath="(//button[@type='submit'])[1]")	
	public WebElement signupbutton;

	@FindBy(xpath="(//a[@id='dropdownCurrency'])[2]")
	public WebElement selectlogoutoraccount;
	
	@FindBy(linkText="Logout")
	public WebElement logout;
	  
	//@FindBy(css="div.resultsignup")
	@FindBy(xpath="//div[@class='alert alert-danger']")
	 public WebElement validationmessage;
	
	@FindBy(linkText="Home")
	public WebElement validssuccessregister;

	// method for user registration 
	public void userregistration(String fname, String lname,String phone,  String email,String password)   
	{
		setTextElementText(firstnametxtbox, fname);
		setTextElementText(lastnametxtbox, lname);
		setTextElementText(phonenumbertxtbox, phone);
		setTextElementText(emailtxtbox, randomestring()+"@gmail.com"); //to generate random email
		//setTextElementText(emailtxtbox, email);
		setTextElementText(passwordtxtbox,password);
		setTextElementText(confirmpasswordtxtbox, password);
		js.executeScript("arguments[0].click();",signupbutton);
	}

	

	public void userlogout() throws InterruptedException //method to logout user
	{

		selectlogoutoraccount.click();
		Thread.sleep(500);
		logout.click();
		Thread.sleep(500);
	}
	public String getErrorvalidationMessage()  //method to return text 

	{
		return validationmessage.getText();
	}
	
	public static String randomestring() //method to generate random string add to email"@gmail.com"
	  {
	    String generatedstring=RandomStringUtils.randomAlphabetic(8);
	    return(generatedstring);
	   }
	
}
