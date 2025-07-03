package category;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditCategory extends Initialstep
{
	String Searchvalue= "Tester";
	String Status= "Machinery";
	String Name= generateDummyName();
	
	@Test
	public void editcategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verifying Edit Category functionality");
		testcase.log(INFO, "Navigating to the Edit Category Page");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		OpenCategoryPage();
		Thread.sleep(1000);
		SearchAndClickOnEdit();
		Thread.sleep(1000);
		UpdateCategory();
		Thread.sleep(1000);
		SaveCategory();
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
	
	public void SearchAndClickOnEdit() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Open Edit Category");
		
			driver.findElement(By.xpath("//input[@name= '#0']")).sendKeys(Searchvalue);
			Thread.sleep(1000);
			try
			{
				driver.findElement(By.xpath("(//div[@class= 'MuiBox-root css-xq4uvd']//button)[1]")).click();
			}
			catch (Exception e)
			{
				driver.findElement(By.xpath("//input[@name= '#0']")).clear();
				driver.findElement(By.xpath("(//div[@class= 'MuiBox-root css-xq4uvd']//button)[1]")).click();
				testcase.log(INFO, "Since the Search did not produced result, editing the first category in list");
			}
		if(driver.findElement(By.xpath("//p[text()= 'Edit Category']")).isDisplayed())
		{
			testcase.log(PASS, "The Edit Category is displayed");
			takescreenshot(driver, "Edit Category is displayed");
		}
		else
		{
			testcase.log(FAIL, "Edit Category did not displayed");
			takescreenshot(driver, "Edit Category did not displayed");
		}
		
	}	

	public void UpdateCategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Update Category data");
		
		WebElement Cname= driver.findElement(By.xpath("//input[@id = 'title']"));
		Cname.click();
		Cname.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1500);
		Cname.sendKeys(Name);
		driver.findElement(By.xpath("(//label[text()= 'Select Type']/..//div)[1]")).click();
		try 
		{
			driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Status +"']")).click();
		}
		catch (Exception e)
		{
			driver.findElement(By.xpath("//div[@role= 'option']")).click();
			testcase.log(INFO, "Since the value entered in the 'Status' is doesnot exist, selecting first value from the dropdown");
		}
		testcase.log(INFO, "The Category details were updated to the fields");		
	}
	
	public void SaveCategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Save updated Category");
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(1000);
		String EditSaved= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(EditSaved.contains("successfully"))
		{
			testcase.log(PASS, "The Category updated and the '" + EditSaved + "' confirmation message is displayed");
			takescreenshot(driver, "Category Edited");
		}
		else
		{
			testcase.log(FAIL, "Category did not edited");
			takescreenshot(driver, "Not able to edit Category");
		}
	}
	
}
