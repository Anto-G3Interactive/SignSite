package materials;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import basepack.Initialstep;

public class ValidationCheck extends Initialstep
{
	
// To check the validations of the fields	
		@Test (priority= 1)
		public void Validatefields() throws InterruptedException, IOException
		{
			testcase= extentReport.createTest("Verify the validations of fields in Material page");
			
			String Category= "rubber"; // Enter a valid Category
			String MaterialName= "Cat";
			String Unit= "Ton"; // Enter a valid unit
			String InvalidCost= "0";
			String InvalidMarkup= "-1";
			String InvalidPrice = "0.0-1";
			
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			driver.findElement(By.xpath("//span[text()= 'Products']")).click();
			driver.findElement(By.xpath("//div[text()= 'Material']")).click();
			driver.findElement(By.xpath("//button[contains(text(), 'Add New Material')]")).click();
			driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
			driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
			driver.findElement(By.xpath("//label[text()= 'Material Name']/following-sibling::input")).sendKeys(MaterialName);
			driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
			driver.findElement(By.xpath("//div[@class= 'css-41imnh-menu']//div[text()= '"+ Unit +"']")).click();
			driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(InvalidCost);
			driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(InvalidMarkup);
			driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::input")).sendKeys(InvalidPrice);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[text()= 'Save']")).click();
			String costvalidation= driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::p")).getText();
			String markupvalidation= driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::p")).getText();
			String pricevalidation= driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::p")).getText();
			Thread.sleep(2000);
			if((costvalidation.contains("Cost must be greater than 0")) && 
			   (markupvalidation.contains("Markup must be a positive number")))
			{
				testcase.log(PASS, "The validation messages are displayed for the fields");
				takescreenshot(driver, "Validation Messages dispalyed");
			}
			else if(driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::p")).isDisplayed() &&
			        driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::p")).isDisplayed() &&
			        driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::p")).isDisplayed())
			{
				testcase.log(FAIL, "The validation messages are displayed. But not as per the client's requirement");
				testcase.log(INFO, ("The actual validation for cost field is: " + costvalidation + ". But the expected is: Cost must be greater than 0."));
				testcase.log(INFO, ("The actual validation for markup field is: " + markupvalidation + ". But the expected is: Markup must be a positive number."));
				testcase.log(INFO, ("The actual validation for price field is: " + pricevalidation + ". But the expected is: Price must be greater than cost."));
				takescreenshot(driver, "Vaildation not as per client's requirement");
			}
			else
			{
				testcase.log(FAIL, "The validations of the field/fields are not validated");
				takescreenshot(driver, "Vaildation not checked");
			}
		}	

		
// To validate whether accept duplicate Material	
		@Test (priority= 2)
		public void addmaterial() throws InterruptedException, IOException
		{
			testcase= extentReport.createTest("Verify whether accepts dulicate Material");
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
			driver.findElement(By.xpath("//span[text()= 'Products']")).click();
			driver.findElement(By.xpath("//div[text()= 'Material']")).click();
			Thread.sleep(2000);
			String Category= driver.findElement(By.xpath("(//div[@data-field= 'category'])[2]")).getText();
			String MaterialName= driver.findElement(By.xpath("(//div[@data-field= 'name'])[2]")).getText();
			String Unit= driver.findElement(By.xpath("(//div[@data-field= 'unit'])[2]")).getText();
			String Cost= driver.findElement(By.xpath("(//div[@data-field= 'cost'])[2]")).getText();
			String Markup= driver.findElement(By.xpath("(//div[@data-field= 'markup'])[2]")).getText();
			driver.findElement(By.xpath("//button[contains(text(), 'Add New Material')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
			driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
			driver.findElement(By.xpath("//label[text()= 'Material Name']/following-sibling::input")).sendKeys(MaterialName);
			driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
			driver.findElement(By.xpath("//div[text()= '"+ Unit +"']")).click();
			driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(String.valueOf(Cost));
			driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(String.valueOf(Markup));
			driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::input"));
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[text()= 'Save']")).click();
			Thread.sleep(1500);
			String materialvalidation= driver.findElement(By.xpath("//label[text()= 'Name']/following-sibling::p")).getText();
			
			if(materialvalidation.contains("The name has already been taken"))
			{
				testcase.log(PASS, "Duplicate Material not accepted");
				takescreenshot(driver, "Duplicate Material not accepted");
			}
			else if(driver.findElement(By.xpath("//label[text()= 'Name']/following-sibling::p")).isDisplayed())
			{
				testcase.log(PASS, "Duplicate Material not accepted. But the wrong validation is displayed");
				testcase.log(INFO, "The actual validation is: "+ materialvalidation +"But the expected validation is: The name has already been taken.");
				takescreenshot(driver, "Duplicate Material not accepted. But wrong validation disaplyed");
			}
			else
			{
				testcase.log(FAIL, "Dulicate Material accepted");
				takescreenshot(driver, "Dulicate Material accepted");
			}
		}
		
}
