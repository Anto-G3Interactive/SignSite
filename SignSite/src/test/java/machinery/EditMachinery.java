package machinery;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditMachinery extends Initialstep
{

	String Searchvalue= "ThuApr";
	String Category= "repair work"; // Enter a valid Category
	String Machineryname= generateDummyName();
	String Units= "Week"; // Enter a valid Unit
	String Cost= "12.00";
	String Markup= "10";
	String NewCategory= generateDummyName();

// To verify add material functionality
	@Test
	public void editmachinery() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Edit Machinery functionality");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Machinery']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name= '#0']")).sendKeys(Searchvalue);
		Thread.sleep(2000);
		String Edit= "MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary css-1vmnopk";
		driver.findElement(By.xpath("//button[@class= '"+ Edit +"']")).click();		
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
		MachineryName();
		driver.findElement(By.xpath("//label[text()= 'Units']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Units +"']")).click();
		CostField();
		MarkupField();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details updated successfully"))
		{
			testcase.log(PASS, "Machinery added successfully and the '"+ confirmation + "' message is dispalyed");
		takescreenshot(driver, "Machinery added");
		}
		else
		{
			testcase.log(FAIL, "Machinery did not added. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Machinery did not added");
		}
		
	}
	
	public void MachineryName()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Machinery Name']/following-sibling::input"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(Machineryname);
	}
	
	public void CostField()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(String.valueOf(Cost));
	}
	
	public void MarkupField()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(String.valueOf(Markup));
	}
}
