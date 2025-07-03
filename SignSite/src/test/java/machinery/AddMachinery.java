package machinery;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddMachinery extends Initialstep
{

	String Category= "repair work"; // Enter a valid Category
	String MachineryName= generateDummyName();
	String Units= "Day"; // Enter a valid Unit
	String Cost= "12.00";
	String Markup= "10";
	String NewCategory= generateDummyName();

// To verify add material functionality
	@Test
	public void addmachinery() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Material functionality");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Machinery']")).click();
		driver.findElement(By.xpath("//button[text()= ' Add New Machinery']")).click();
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Machinery Name']/following-sibling::input")).sendKeys(MachineryName);
		driver.findElement(By.xpath("//label[text()= 'Units']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Units +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(Cost);
		driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(Markup);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details saved successfully"))
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
	
// To Verify Add Material functionality with new Category
	
	@Test (priority=2)
	public void AddMaterialWithNewCategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Material functionality by addning new Category");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Machinery']")).click();
		driver.findElement(By.xpath("//button[text()= ' Add New Machinery']")).click();
		
		driver.findElement(By.xpath("//div[@class='col-sm-6']//button[@type= 'button']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//label[text()= 'Category Name']/following-sibling::input")).sendKeys(NewCategory);
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String status= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(status.contains("Details saved successfully"))
		{
			driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
			String s= driver.findElement(By.xpath("(//div[@class= 'css-gbbed2-singleValue'])[1]")).getText();
			testcase.log(PASS, "New Category is added. The added Category is: '"+ s);
			takescreenshot(driver, "New Category added");
		}
		else
		{
			testcase.log(FAIL, "New Category did not added. The '"+ status + "' message is dispalyed");
			takescreenshot(driver, "New Category did not added");
		}
		driver.findElement(By.xpath("//label[text()= 'Machinery Name']/following-sibling::input")).sendKeys(MachineryName);
		driver.findElement(By.xpath("//label[text()= 'Units']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Units +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(Cost);
		driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(Markup);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details saved successfully"))
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
}
