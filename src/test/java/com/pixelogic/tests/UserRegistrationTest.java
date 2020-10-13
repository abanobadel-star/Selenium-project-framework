package com.pixelogic.tests;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pixelogic.pages.Homepage;
import com.pixelogic.pages.Loginpage;
import com.pixelogic.pages.Registerpage;

import Data.ExcelReader;

public class UserRegistrationTest extends TestBase { //this class inherat from "TestBase class"

	public UserRegistrationTest() throws IOException { //this is constructor
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

	@Test(priority=1,dataProvider="userdata")   //testcase to validate usercan signup to website
	public void userregister(String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);  //take object from register page
		registerobject.userregistration(fname, lname, phone, email, password);  //method to set parameter
		//driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS); //implicit wait to make element load and driver can find it
		Thread.sleep(1000);
		String expectedresult="https://www.phptravels.net/account/";     //expected result from testcase
		String Actualresult=driver.getCurrentUrl();          //actual result from test case
		Assert.assertEquals(Actualresult, expectedresult);     //compare between expected result and actual result
		//Assert.assertEquals(driver.getCurrentUrl(),("https://www.phptravels.net/account/"));
//		registerobject.userlogout();   //method to logout from account page 
//		loginobject=new Loginpage(driver);
//		loginobject.userlogin(email, password);
//		Thread.sleep(1000);
//		Assert.assertEquals(Actualresult, expectedresult);
//		Assert.assertEquals(driver.getCurrentUrl(),("https://www.phptravels.net/account/"));
//		registerobject.userlogout();
	}
	 @Test(priority=2) //testcase to signUp API request and save the response 
		public void getResponseSignUP() throws ClientProtocolException, IOException {
		 System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(prop.getProperty("URL"));
			HttpResponse response = client.execute(request);
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);

			}
			System.out.println(response.getStatusLine());
			PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "//Response//signUP.html"); //
			pw.write(sb.toString());
			pw.close();
			pw.flush();
		}
	@Test(priority=3,dataProvider="userdata")    //testcase to validate firstname must start with capital letter
	public void validfirstnamewithcaptialletter(String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(fname, lname, phone, email, password);
		Thread.sleep(1000);
		String Expectedresult="First Name Should Start With Capital Letter.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}
	@Test(priority=4,dataProvider="userdata")    //testcase to validate email already exsit 
	public void validemailexsit(String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(fname, lname, phone, email, password);
		Thread.sleep(1000);
		String Expectedresult="Email Already Exists.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}
	@Test(priority=5,dataProvider="userdata")   //testcase to validate lastname not same as firstname
	public void validlastNameNotsameasFirstname  (String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(fname, lname, phone, email, password);
		Thread.sleep(1000);
		String Expectedresult="LastName Must Different To FirstName";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);

	}
	@Test(priority=6,dataProvider="userdata")   //testcase to validate mobile number must be valid number
	public void validMobileNumber  (String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(fname, lname, phone, email, password);
		Thread.sleep(1000);
		String Expectedresult="Phone Number Must be Numbers";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}
	@Test(priority=7,dataProvider="userdata")   //testcase to validate password has at least one capital letter
	public void ValidPasswordHasATLeastOneCapitalLetter  (String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(fname, lname, phone, email, password);
		Thread.sleep(1000);
		String Expectedresult="Password Must Contians At Least One Capital Letter";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}

	@Test(priority=8,dataProvider="userdata")  //testcase to validate password must have at least six character
	public void validpasswordmustatleastsixcharacter  (String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(fname, lname, phone, email, password);
		Thread.sleep(1000);
		String Expectedresult="The Password field must be at least 6 characters in length.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}
	@Test(priority=9,dataProvider="userdata") // testcase to validate password and confirm password match
	public void ValidPasswordAndComfirmPasswordMatch  (String fname, String lname,String phone,String email ,String password) throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(fname, lname, phone, email, password);
		Thread.sleep(1000);
		String Expectedresult="Password not matching with confirm password.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);

	}


	
	/*
	@Test(priority=2)
	public void validfirstnamewithcaptialletter() throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(prop.getProperty("fname2"), prop.getProperty("lname2"), prop.getProperty("phone2"), prop.getProperty("email2"), prop.getProperty("password2"));
		Thread.sleep(1000);
		String Expectedresult="First Name Should Start With Capital Letter.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
		//loginobject=new Loginpage(driver);
	}
	@Test(priority=3)
	public void validemailexsit() throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(prop.getProperty("fname3"), prop.getProperty("lname3"), prop.getProperty("phone3"), prop.getProperty("email3"), prop.getProperty("password3"));
		Thread.sleep(1000);
		String Expectedresult="Email Already Exists.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
		// loginobject=new Loginpage(driver);
	}



	@Test(priority=4)
	public void validlastNameNotsameasFirstname() throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(prop.getProperty("fname4"), prop.getProperty("lname4"), prop.getProperty("phone4"), prop.getProperty("email4"), prop.getProperty("password4"));
		Thread.sleep(1000);
		String Expectedresult="LastName Must Different To FirstName";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}

	@Test(priority=5)
	public void validMobileNumber() throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(prop.getProperty("fname5"), prop.getProperty("lname5"), prop.getProperty("phone5"), prop.getProperty("email5"), prop.getProperty("password5"));
		Thread.sleep(1000);
		String Expectedresult="Phone Number Must be Numbers";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}
	@Test(priority=6)
	public void ValidPasswordHasATLeastOneCapitalLetter() throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistration(prop.getProperty("fname6"), prop.getProperty("lname6"), prop.getProperty("phone6"), prop.getProperty("email6"), prop.getProperty("password6"));
		Thread.sleep(1000);
		String Expectedresult="Password Must Contians At Least One Capital Letter";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}
	@Test(priority=7)
	public void ValidPasswordAndComfirmCasswordMatch() throws InterruptedException 
	{
		registerobject= new Registerpage(driver);
		registerobject.userregistrationconfirmemail
		(prop.getProperty("fname7"), prop.getProperty("lname7"), prop.getProperty("phone7"), prop.getProperty("email7"), prop.getProperty("password7"),prop.getProperty("confirmpassword7"));

		Thread.sleep(1000);
		String Expectedresult="Password not matching with confirm password.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}
	@Test(priority=8)
	public void validpasswordmustatleastsixcharacter() throws InterruptedException 
	{

		registerobject= new Registerpage(driver);
		registerobject.userregistration(prop.getProperty("fname8"), prop.getProperty("lname8"), prop.getProperty("phone8"), prop.getProperty("email8"), prop.getProperty("password8"));
		Thread.sleep(1000);
		String Expectedresult="The Password field must be at least 6 characters in length.";
		String Actualresult=registerobject.getErrorvalidationMessage();
		Assert.assertEquals(Actualresult, Expectedresult);
	}

 @Test(priority=9)
	public void getResponseSignUP() throws ClientProtocolException, IOException {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(prop.getProperty("URL"));
		HttpResponse response = client.execute(request);
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);

		}
		System.out.println(response.getStatusLine());
		PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + "//Response//signUP.html");
		pw.write(sb.toString());
		pw.close();
		pw.flush();
	}*/



}
