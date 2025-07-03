package products;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class MandatoryCheck extends Initialstep 
{
	String ProductName= generateDummyName();
	String ProductCategory= "paper"; //Please enter valid category name
	String Description= "The new product";
	String ProductImage= "E:\\Nature.jpg";
	
	@Test
	public void mandatorycheck() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Mandatory fields");
		testcase.log(INFO, "To verify the 'Product Name' and 'Product Category' fields");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()= 'Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()= ' Add New Product']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(1500);
		
		if(driver.findElement(By.xpath("//label[text()= 'Product Name']/..//p")).isDisplayed()&&
		driver.findElement(By.xpath("//label[text()= 'Product Category']/..//p")).isDisplayed())
		{
			testcase.log(PASS, "The validation message dispalyed for the 'Product Name' and 'Product Category' fields");
			takescreenshot(driver, "Validation for 'Product Name' and 'Product Category' fields");
		}
		else
		{
			testcase.log(PASS, "The validation message did dispalyed for 'Product Name' and 'Product Category' fields");
			takescreenshot(driver, "Validation for 'Product Name' and 'Product Category' fields did not displayed");
		}

// Adding new Product
		driver.findElement(By.xpath("//label[text()= 'Product Name']/following-sibling::input")).sendKeys(ProductName);
		driver.findElement(By.xpath("//label[text()= 'Product Category']/following-sibling::div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(), '"+ ProductCategory +"')]")).click();
		driver.findElement(By.xpath("//label[contains(text(), 'Description')]/following-sibling::textarea")).sendKeys(Description);
		WebElement PImage= driver.findElement(By.xpath("//div[@role= 'presentation']//input"));
		PImage.sendKeys(ProductImage);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(1500);
		
// Materials fields		
		testcase.log(INFO, "To verify the Materials fields in Products");
		
		driver.findElement(By.xpath("//div[text()= 'materials']/..//button[@type= 'button']")).click(); // Add Materials button
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Save']")).click();
		Thread.sleep(1500);

		if(driver.findElement(By.xpath("//label[text()= 'Category']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Material']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Unit']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Cost']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//div[@role= 'dialog']//label[text()= 'Markup (%)']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Price']/..//p")).isDisplayed())
		{
			testcase.log(PASS, "The validation message dispalyed for the Material fields");
			takescreenshot(driver, "Validation for Material fields");
		}
		else
		{
			testcase.log(PASS, "The validation message did dispalyed for Material fields");
			takescreenshot(driver, "Validation for Material fields did not displayed");
		}
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Cancel']")).click();
		Thread.sleep(1500);
		

// Machinery fields		
		testcase.log(INFO, "To verify the Machinery fields in Products");

		driver.findElement(By.xpath("//div[text()= 'machineries']/..//button[@type= 'button']")).click(); // Add Machineries button
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Save']")).click();
		Thread.sleep(1500);

		if(driver.findElement(By.xpath("//label[text()= 'Category']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Name']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Unit']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Cost']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//div[@role= 'dialog']//label[text()= 'Markup (%)']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Price']/..//p")).isDisplayed())
		{
			testcase.log(PASS, "The validation message dispalyed for the Machinery fields");
			takescreenshot(driver, "Validation for Machinery fields");
		}
		else
		{
			testcase.log(PASS, "The validation message did dispalyed for Machinery fields");
			takescreenshot(driver, "Validation for Machinery fields did not displayed");
		}
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Cancel']")).click();
		Thread.sleep(1500);
		
		
// Labour fields		
		testcase.log(INFO, "To verify the Labour fields in Products");

		driver.findElement(By.xpath("//div[text()= 'labours']/..//button[@type= 'button']")).click(); // Add Labours button
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		
		if(driver.findElement(By.xpath("//label[text()= 'Category']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Labour']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Unit']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Cost']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//div[@role= 'dialog']//label[text()= 'Markup (%)']/..//p")).isDisplayed()&&
		   driver.findElement(By.xpath("//label[text()= 'Price']/..//p")).isDisplayed())
		{
			testcase.log(PASS, "The validation message dispalyed for the Labour fields");
			takescreenshot(driver, "Validation for Labour fields");
		}
		else
		{
			testcase.log(PASS, "The validation message did dispalyed for Labour fields");
			takescreenshot(driver, "Validation for Labour fields did not displayed");
		}
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Cancel']")).click();
		Thread.sleep(1500);
	}
}
