package labour;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddLabour extends Initialstep
{
	String Category= "Cat-5"; // Enter a valid Category
	String LabourName=  generateDummyName();
	String Unit= "Shift"; // Enter a valid unit
	double Cost= 10;
	double Markup= 2;
	double Price = Cost*(1+Markup/100);
	String NewCategory= generateDummyName();

// Add Labour with exiting category
	@Test (priority= 1)
	public void addlabour() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Labour functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Labour']")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'Add New Labour')]")).click();
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Labour Name']/following-sibling::input")).sendKeys(LabourName);
		driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Unit +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(String.valueOf(Cost));
		driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(String.valueOf(Markup));
		driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::input"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details saved successfully"))
		{
			testcase.log(PASS, "Labour added successfully and the '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Labour added");
		}
		else
		{
			testcase.log(FAIL, "Labour did not added. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Labour did not added");
		}
	}
	
// Add Labour with new Category
	@Test (priority= 2)
	public void AddLabourWithNewCategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Labour functionality with new category");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Labour']")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'Add New Labour')]")).click();
		driver.findElement(By.xpath("//div[@class='col-sm-6']//button[@type= 'button']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//label[text()= 'Category Name']/following-sibling::input")).sendKeys(NewCategory);
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String status= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(status.contains("Details saved successfully"))
		{
			testcase.log(PASS, "New Category added. The '"+ status + "' message is dispalyed");
			takescreenshot(driver, "New Category added");
		}
		else
		{
			testcase.log(FAIL, "New Category did not added. The '"+ status + "' message is dispalyed");
			takescreenshot(driver, "New Category did not added");
		}
		driver.findElement(By.xpath("//label[text()= 'Labour Name']/following-sibling::input")).sendKeys(LabourName);
		driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Unit +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(String.valueOf(Cost));
		driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(String.valueOf(Markup));
		driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::input"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details saved successfully"))
		{
			testcase.log(PASS, "Labour added successfully with new category and the '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Labour added with new category");
		}
		else
		{
			testcase.log(FAIL, "Labour did not added. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Labour did not added");
		}
	}

}
