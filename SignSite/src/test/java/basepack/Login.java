package basepack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Login 
{
	WebDriver driver;
	ExtentReports extentReport= new ExtentReports();
	ExtentSparkReporter spark;
	ExtentTest testcase;
	
	@BeforeClass
	public void Loginprocess() throws IOException, InterruptedException
	{
//		To get details from the Config.properties file
		FileInputStream file= new FileInputStream("config.properties");
		Properties properties= new Properties();
		properties.load(file);
		String path= properties.getProperty("Path");
		String url= properties.getProperty("LoginURL");
		String dashboardurl= properties.getProperty("DashboarURL");
		String emailid= properties.getProperty("Email");
		String password= properties.getProperty("Password");
		
		System.setProperty("webdriver.chrome.driver", path);
		driver= new ChromeDriver();
		
		testcase= extentReport.createTest("Verify the Login process");
		driver.get(url);
		testcase.log(Status.INFO, "Navigating to Dashboar Page to confirm the login");
        Thread.sleep(1000);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(emailid);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        if(dashboardurl.equals(driver.getCurrentUrl()))
        {
        	testcase.log(Status.PASS, "Successfully logged-in");
        }
        else
        {
        	testcase.log(Status.FAIL, "Login Failed");
        }
		
		spark= new ExtentSparkReporter("ExtentReport.html");
		extentReport.attachReporter(spark);		
		
	}
	
	
	@Test
	public void loggedin() throws IOException 
	{
		takescreenshot(driver, "Login");
	}
	
	
	void takescreenshot(WebDriver driver, String screenshotName) throws IOException 
	{
	    String userHome = System.getProperty("user.home");
	    String downloadsFolder = userHome + File.separator + "Pictures" + File.separator;
	    String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
	    
	    File screenshotLocation = new File(downloadsFolder + screenshotName + currentDateTime + ".png");
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(screenshot, screenshotLocation);
	    
	    testcase.addScreenCaptureFromPath(screenshotLocation.getAbsolutePath());
	}

	
	
	@AfterClass
	public void Close()
	{
		driver.close();
		extentReport.flush();
	}
	

	

}
