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

public class UserRegistrationTest extends TestBase { //this class inherent from "TestBase class"

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
		registerobject= new Registerpage(driver);      //take object from register page
		registerobject.userregistration(fname, lname, phone, email, password);    //method to set parameter
		//driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);    //implicit wait to make element load and driver can find it
		Thread.sleep(1000);
		String expectedresult="https://www.phptravels.net/account/";     //expected result from testcase
		String Actualresult=driver.getCurrentUrl();               //actual result from test case
		Assert.assertEquals(Actualresult, expectedresult);     //compare between expected result and actual result
		//Assert.assertEquals(driver.getCurrentUrl(),("https://www.phptravels.net/account/"));

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
}




