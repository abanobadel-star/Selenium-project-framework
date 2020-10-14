package com.pixelogic.tests;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pixelogic.pages.Homepage;
import com.pixelogic.pages.Loginpage;
import com.pixelogic.pages.Registerpage;

import Data.ExcelReader;

public class LoginTest extends TestBase{ //class inherat from TestBase "parent"
	
	public LoginTest() throws IOException { //this is constructor
		super();
		
	}


	Registerpage registerobject;
	Homepage homeobject;
	Loginpage loginobject;
	@DataProvider(name="userdata") //data provider with link between script and execl sheet
	public Object[][] data_for_register() throws IOException  //method to pass data to testcase
	{
		ExcelReader excelreader=new ExcelReader(); //object from class execl reader
		return excelreader.getExcelData(); //to get and return  data from excel sheet

	}

  
	@Test(dependsOnMethods= {"userregister"},dataProvider="userdata") //method to login user
	public void usercanlogin(String fname, String lname,String phone,String email ,String password) throws InterruptedException  
	{
		registerobject= new Registerpage(driver);
		homeobject=new Homepage(driver);
		loginobject = new Loginpage(driver);
		
		homeobject.selectLoginORSingup(); //method to select userlogin or user signup
		homeobject.clickonloginpage();   // method to enter to login page
		loginobject.userlogin(email,password);
		Thread.sleep(1000);
		String expectedresult="https://www.phptravels.net/account/";     //expected result from testcase
		String Actualresult=driver.getCurrentUrl();             //actual result from test case
		Assert.assertEquals(Actualresult, expectedresult);     //compare between expected result and actual result
		//Assert.assertTrue(registerobject.getcurrenturl().equalsIgnoreCase("https://www.phptravels.net/account/"));
		
	}

}
