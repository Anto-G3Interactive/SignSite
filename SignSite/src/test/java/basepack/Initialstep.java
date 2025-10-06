package basepack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Initialstep 
{
	public WebDriver driver;
	public ExtentReports extentReport= new ExtentReports();
	public ExtentSparkReporter spark;
	public ExtentTest testcase;
	public Status PASS= Status.PASS;
	public Status FAIL= Status.FAIL;
	public Status INFO= Status.INFO;
	public SoftAssert softAssert;
	
	@BeforeClass
	public void Login() throws IOException, InterruptedException
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		testcase= extentReport.createTest("Verifying the Login process");
		driver.get(url);
		testcase.log(Status.INFO, "Navigating to Dashboard Page to confirm the login");
        Thread.sleep(1000);
        String currenturl= driver.getCurrentUrl();
        if(currenturl.equals(url))
        {
	        WebElement email= driver.findElement(By.xpath("//input[@name='email']"));
	        email.clear();
	        email.sendKeys(emailid);
	        WebElement pswd= driver.findElement(By.xpath("//input[@name='password']"));
	        pswd.clear();
	        Thread.sleep(1000);
	        pswd.sendKeys(password);
	        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
	        Thread.sleep(2000);
	        String curl= driver.getCurrentUrl();
	        String otpurl= "http://13.210.33.250/otp-verification";
	        
	        if(dashboardurl.equals(curl) && (driver.findElement(By.xpath("//h6[text()= 'Dashboard']")).isDisplayed()))
	        {
	        	testcase.log(Status.PASS, "Successfully logged-in");
	        }
	        else if(otpurl.equals(curl))
	        {
	        	testcase.log(Status.FAIL, "Please enter the OTP manually and re-run the code");
	        }
	        else
	        {
	        	testcase.log(Status.FAIL, "Login Failed!. Did not redirected to the Dashboard page.");
	        }
        }
        else if (currenturl.equals(dashboardurl) && (driver.findElement(By.xpath("//h6[text()= 'Dashboard']")).isDisplayed()))
        {
        	testcase.log(Status.PASS, "Successfully logged-in");
        }
        
        else
        {
        	testcase.log(Status.FAIL, "Login Failed");
        }
        
		spark= new ExtentSparkReporter("ExtentReport.html");
		extentReport.attachReporter(spark);	
		softAssert = new SoftAssert();
	}

	
	public void takescreenshot(WebDriver driver, String screenshotName) throws IOException 
	{
	    String userHome = System.getProperty("user.home");
	    String downloadsFolder = userHome + File.separator + "Pictures" + File.separator;
	    String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
	    
	    File screenshotLocation = new File(downloadsFolder + screenshotName + currentDateTime + ".png");	    
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    
	    FileUtils.copyFile(screenshot, screenshotLocation);
	    testcase.addScreenCaptureFromPath(screenshotLocation.getAbsolutePath());
	}
	
// Generating unique name method
	public static String generateDummyName() 
	{
		Date d=new Date();
		String s= d.toString().replaceAll(":", "").replaceAll(" ", "");
	    return s;
	}

// Failed to Run Script
	public void FailedToRunScript(Exception e) throws IOException
	{
		try
		{
			String Status= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
			testcase.log(FAIL, "Testcase Failed. The '"+ Status +"' message is displayed.");
			testcase.log(FAIL, "Failed to Run the Script. <br>"+ "Error: " + e.getMessage().split("\\R", 2)[0]);
			takescreenshot(driver, "Failed to Run the Script");
			Assert.fail("Failed to Run the Script."+ "\n"+"\n" + e.getMessage());
		}
		catch(Exception e1)
		{
			testcase.log(FAIL, "Failed to Run the Script. <br>"+ "Error: " + e.getMessage().split("\\R", 2)[0]);
			takescreenshot(driver, "Failed to Run the Script");
			Assert.fail("Failed to Run the Script."+ "\n"+"\n" + e.getMessage());
		}
	}
	
// Selecting Drop-down value which is from API
	public void SelectDropdownValue(WebDriver driver, ExtentTest testcase, String Name)
	{
		try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and contains(text(), '"+ Name +"')]")).click();
    	}
    	catch(Exception e)
    	{
    		try
    		{
	    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
	    		testcase.log(INFO, "Since the given option '"+ Name +"' does not exist, selected the first option from dropdown");
    		}
    		catch(Exception e1)
    		{
    			try
    			{
    				driver.findElement(By.xpath("//li[@role= 'option' and contains(text(), '"+ Name +"')]")).click();
    			}
    			catch(Exception e2)
    			{
    				driver.findElement(By.xpath("//li[@role= 'option']")).click();
    	    		testcase.log(INFO, "Since the given option '"+ Name +"' does not exist, selected the first option from dropdown");
    			}
    		}
    	}
	}
	
// Clearing the field and entering the value into it
	public void ClearAndEnterValue(WebElement e, String Value) throws InterruptedException
	{
		e.click();
		Thread.sleep(500);
        e.sendKeys(Keys.CONTROL, "a");
        Thread.sleep(500);
        e.sendKeys(Value);
	}
	
	@AfterClass
	public void Close() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.close();
		extentReport.flush();
		softAssert.assertAll();
	}
}
