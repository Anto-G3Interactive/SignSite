package category;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class MandatoryCheck extends Initialstep
{
	@Test
	public void mandatorycheck() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verifying Mandatory fields");
		
		OpenCategoryPage();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()= ' Add New Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(1000);
		
		if(driver.findElement(By.xpath("//input[@id = 'title']/following-sibling::p")).isDisplayed()&&
			driver.findElement(By.xpath("//label[text()= 'Select Type']/following-sibling::p")).isDisplayed())
		{
			testcase.log(PASS, "The Mandatory fields are not accepting the blank value in Category Page");
			takescreenshot(driver, "Mandatory checked");
		}
		else
		{
			testcase.log(FAIL, "Validation Messages are not dispalyed");
			takescreenshot(driver, "Mandatory check failed");
		}
	}
	
	public void OpenCategoryPage() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Open the Category Page");
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@to= 'category']/..")).click();
		
		if(driver.findElement(By.xpath("//h6[text()= 'Category']")).isDisplayed())
		{
			testcase.log(PASS, "The Category page is displayed");
			takescreenshot(driver, "Category Page");
		}
		else
		{
			testcase.log(PASS, "The Category page did not displayed");
			takescreenshot(driver, "Category Page did not displayed");
		}
	}
}
