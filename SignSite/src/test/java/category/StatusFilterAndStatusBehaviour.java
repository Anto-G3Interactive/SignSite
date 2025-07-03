package category;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class StatusFilterAndStatusBehaviour extends Initialstep
{
	
	@Test
	public void StatusFunctionality() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verifying the Status Functionality");
		testcase.log(INFO, "Navigating to the Category page");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		OpenCategoryPage();
		Thread.sleep(1500);
		StatusFilter();
		Thread.sleep(1500);
		StatusBehaviour();
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

	public void StatusFilter() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verifying Status filter functionality");
		
		WebElement RowsPerPage= driver.findElement(By.xpath("//div[@aria-haspopup= 'listbox']"));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth', block:'center'});", RowsPerPage);
		Thread.sleep(1500);
		RowsPerPage.click();
		driver.findElement(By.xpath("//li[@data-value= '100']")).click();
		Thread.sleep(1500);
		WebElement Dropdown= driver.findElement(By.xpath("//input[@placeholder= 'Select Status']"));
		js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth', block:'center'});", Dropdown);
		Dropdown.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[text()= 'Active']")).click();
		try
		{
			WebElement WrongElement= driver.findElement(By.xpath("//button[text()= 'Inactive']"));
			if(WrongElement.isDisplayed())
			{
				testcase.log(FAIL, "When choosing Active filter, InActive Category is displayed");
				js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth', block:'center'});", WrongElement);
				Thread.sleep(1250);
				takescreenshot(driver, "Inactive Category is displayed");
			}
		}
		catch(Exception e)
		{
			testcase.log(PASS, "When choosing Active filter, Active Categories are displayed");
			takescreenshot(driver, "Active Categories displayed");
		}
		
		js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth', block:'center'});", Dropdown);
		Dropdown.click();
		driver.findElement(By.xpath("//li[text()= 'Inactive']")).click();
		try
		{
			WebElement WrongElement= driver.findElement(By.xpath("//button[text()= 'Active']"));
			if(WrongElement.isDisplayed())
			{
				testcase.log(FAIL, "When choosing Inactive filter, Active Category is displayed");
				js.executeScript("arguments[0].scrollIntoView({behaviour:'smooth', block:'center'});", WrongElement);
				Thread.sleep(1250);
				takescreenshot(driver, "Active Category is displayed");
			}
		}
		catch(Exception e)
		{
			testcase.log(PASS, "When choosing Inactive filter, Inactive Categories are displayed");
			takescreenshot(driver, "Inactive Categories displayed");
		}
		Actions act= new Actions(driver);
		act.moveToElement(Dropdown).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title= 'Clear']")).click();
	}

	public void StatusBehaviour() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verifying the Behaviour of Status");
		
		String Name= driver.findElement(By.xpath("(//div[@data-rowindex='0']//div[@role= 'gridcell'])[2]")).getText();
		String CategoryType= driver.findElement(By.xpath("(//div[@data-rowindex='0']//div[@role= 'gridcell'])[3]")).getText();
		WebElement CurrentStatus= driver.findElement(By.xpath("(//div[@data-rowindex='0']//div[@role= 'gridcell'])[4]//button"));
		String InitialStatus= CurrentStatus.getText();
		if(InitialStatus.equalsIgnoreCase("Inactive"))
		{
			CurrentStatus.click();
		}
		testcase.log(INFO, "Validating the following Active Category:"
				+ " Category Name is: "+ Name +"; and Category Type is: "+ CategoryType);

		driver.findElement(By.xpath("//div[text()= '"+ CategoryType +"']/..")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[contains(text(), 'Add New')]")).click();
		driver.findElement(By.xpath("//label[contains(text(), 'Category')]/..//input/../../..")).click();
		try
		{
			if(driver.findElement(By.xpath("//div[text()= '"+ Name +"']")).isDisplayed())
			{
				testcase.log(PASS, "When the Category is active, it displayed under the "+ CategoryType);
			}
		}
		catch (Exception e)
		{
			testcase.log(FAIL, "Eventhough the status is active, it did not displayed in other pages");
		}
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@to= 'category']/..")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name= '#0']")).sendKeys(Name);
		Thread.sleep(2500);
		driver.findElement(By.xpath("(//div[@data-rowindex='0']//div[@role= 'gridcell'])[4]//button")).click();
		Thread.sleep(1250);
		testcase.log(INFO, "Validating the following Inactive Category:"
				+ " Category Name is: "+ Name +"; and Category Type is: "+ CategoryType);
		
		driver.findElement(By.xpath("//div[text()= '"+ CategoryType +"']/..")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[contains(text(), 'Add New')]")).click();
		driver.findElement(By.xpath("//label[contains(text(), 'Category')]/..//input/../../..")).click();
		try
		{
			if(driver.findElement(By.xpath("//div[text()= '"+ Name +"']")).isDisplayed())
			{
				driver.findElement(By.xpath("//div[text()= '"+ Name +"']")).click();
				testcase.log(FAIL, "When the Category is Inactive, it displayed under the "+ CategoryType);
				Thread.sleep(1500);
				takescreenshot(driver, "In Active Category is displayed");
			}
		}
		catch (Exception e)
		{
			testcase.log(PASS, "Eventhough the status is Inactive, it displayed in other pages");
		}
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@to= 'category']/..")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//input[@name= '#0']")).sendKeys(Name);
		Thread.sleep(2500);
		if(InitialStatus.equals(driver.findElement(By.xpath("(//div[@data-rowindex='0']//div[@role= 'gridcell'])[4]//button")).getText()))
		{
			testcase.log(INFO, "The status reverted to its initial Status");
		}
		else
		{
			driver.findElement(By.xpath("(//div[@data-rowindex='0']//div[@role= 'gridcell'])[4]//button")).click();
			testcase.log(INFO, "The status reverted to its initial Status");
		}
	}
}