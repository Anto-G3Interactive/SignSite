package labour;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditLabour extends Initialstep
{
	String Searchvalue= "WedApr";
	String Category= "Cat-5"; // Enter a valid Category
	String LabourName=  generateDummyName();
	String Unit= "Man-Day"; // Enter a valid unit
	double Cost= 10;
	double Markup= 2;
	double Price = Cost*(1+Markup/100);

	@Test
	public void editlabour() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Labour functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Labour']")).click();
		driver.findElement(By.xpath("//input[@name= '#0']")).sendKeys(Searchvalue);
		Thread.sleep(1500);
		String Editbtn= "MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary css-1vmnopk";
		driver.findElement(By.xpath("//button[@class= '"+ Editbtn+ "']")).click();
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
		WebElement Labourfield= driver.findElement(By.xpath("//label[text()= 'Labour Name']/following-sibling::input"));
		Labourfield.click();
		Labourfield.sendKeys(Keys.CONTROL,"a");
		Labourfield.sendKeys(LabourName);
		driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Unit +"']")).click();
		WebElement Costfield= driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input"));
		Costfield.click();
		Costfield.sendKeys(Keys.CONTROL,"a");
		Costfield.sendKeys(String.valueOf(Cost));
		WebElement Markupfield= driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input"));
		Markupfield.click();
		Markupfield.sendKeys(Keys.CONTROL,"a");	
		Markupfield.sendKeys(String.valueOf(Markup));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details updated successfully"))
		{
			testcase.log(PASS, "Labour edited successfully and the '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Labour Edited");
		}
		else
		{
			testcase.log(FAIL, "Labour did not edited. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Labour did not edited");
		}
	}
}
