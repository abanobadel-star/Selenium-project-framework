package utilities;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class Helper {


	public static void takescreenshot(WebDriver driver ,String screenshotname)   //driver to get screenshotand name it 
	{
		Path destination= Paths.get("./Screenshots",screenshotname+".png");
		try {
			Files.createDirectories(destination.getParent());
			FileOutputStream out=new FileOutputStream(destination.toString());     //to save image and put it in file called out
			out.write(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {

			System.out.println("Excpetion when taking screenshot"+e.getMessage());

		}

	}

}


