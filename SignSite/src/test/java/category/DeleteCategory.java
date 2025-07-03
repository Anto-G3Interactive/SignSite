package category;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DeleteCategory extends Initialstep
{
	String Name= "To Delete";
	String Status= "Machinery";
	
	@Test
	public void deletecategory() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Verifying Delete Category functionality");
		testcase.log(INFO, "Navigating to the Category page to add and delete the Category");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		OpenCategoryPage();
		Thread.sleep(1500);
		AddNewCategory();
		Thread.sleep(1500);		
		DeletetheAddedCategory();
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

	public void AddNewCategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Add New Category to delete");
		
		driver.findElement(By.xpath("//button[text()= ' Add New Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id = 'title']")).sendKeys(Name);
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
		testcase.log(INFO, "The Category details were added to the fields");
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(1000);
		String DetailsSaved= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(DetailsSaved.contains("successfully"))
		{
			testcase.log(PASS, "The Category added and the '" + DetailsSaved + "' confirmation message is displayed");
			takescreenshot(driver, "Category Added");
		}
		else
		{
			testcase.log(FAIL, "A new Category did not added");
			takescreenshot(driver, "Not able to add Category");
		}
	}
	
	public void DeletetheAddedCategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Deleting the Category");
		
		driver.findElement(By.xpath("//input[@name= '#0']")).sendKeys(Name);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class= 'MuiBox-root css-xq4uvd']//button)[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()=  'Yes, delete it!']")).click();
		Thread.sleep(2000);
		String Deleted= driver.findElement(By.xpath("//div[@id= 'swal2-html-container']")).getText();
		if(Deleted.contains("successfully"))
		{
			testcase.log(PASS, "The Category deleted and the '" + Deleted + "' confirmation message is displayed");
			takescreenshot(driver, "Category Deleted");
		}
		else
		{
			testcase.log(FAIL, "Category did not deleted");
			takescreenshot(driver, "Not able to delete a Category");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()= 'OK']")).click();
	}	

}
