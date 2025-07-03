package materials;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class MandatoryCheck extends Initialstep
{
	@Test
	public void Mandatorycheck() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Mandatory fields in Labour page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Material']")).click();
		driver.findElement(By.xpath("//button[text()= ' Add New Material']")).click();
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String categoryvalidation= driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::p")).getText();
		String materialvalidation= driver.findElement(By.xpath("//label[text()= 'Material Name']/following-sibling::p")).getText();
		String unitvalidation= driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::p")).getText();
		String costvalidation= driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::p")).getText();
		String markupvalidation= driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::p")).getText();
		String pricevalidation= driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::p")).getText();
		if((categoryvalidation.contains("Please select a category.")) && 
		   (materialvalidation.contains("Material name is required.")) && 
		   (unitvalidation.contains("Please select a unit.")) && 
		   (costvalidation.contains("Cost must be greater than 0")) && 
		   (markupvalidation.contains("Markup must be a positive number"))&& 
		   (pricevalidation.contains("Price must be greater than cost")))
		{
			testcase.log(PASS, "The validation messages are displayed for the fields");
			takescreenshot(driver, "Validation Messages dispalyed");
		}
		else if(driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::p")).isDisplayed() &&
				driver.findElement(By.xpath("//label[text()= 'Material Name']/following-sibling::p")).isDisplayed() &&
				driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::p")).isDisplayed() &&
				driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::p")).isDisplayed() &&
				driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::p")).isDisplayed() &&
				driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::p")).isDisplayed())
		{
			testcase.log(FAIL, "The Mandatory fields are not accepting blank. But the validation messages are not as per requirement");
			testcase.log(INFO, "The actual Category field validation is: "+ categoryvalidation + ". And expected is: Please select a category.");
			testcase.log(INFO, "The actual Category field validation is: "+ materialvalidation+ ". And expected is: Material name is required.");
			testcase.log(INFO, "The actual Category field validation is: "+ unitvalidation+ ". And expected is: Please select a unit.");
			testcase.log(INFO, "The actual Category field validation is: "+ costvalidation+ ". And expected is: Cost must be greater than 0.");
			testcase.log(INFO, "The actual Category field validation is: "+ markupvalidation+ ". And expected is: Markup must be a positive number.");
			testcase.log(INFO, "The actual Category field validation is: "+ pricevalidation+ ". And expected is: Price must be greater than cost.");
			takescreenshot(driver, "Mandatory Validation Messages dispalyed. But not as per requirement");
		}
		else
		{
			testcase.log(FAIL, "Mandatory fields are accepting blanks");
			takescreenshot(driver, "Mandatory fields accepts blank");
		}
	}
}
