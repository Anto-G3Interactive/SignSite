package category;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInCategoryPage extends Initialstep
{
	WebDriver driver;
	ExtentTest testcase;
	
	public ElementsInCategoryPage(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
// Open Category Page
	
	public void ProductDropdownMenu()
	{
		driver.findElement(By.xpath("//span[text()='Products']")).click();
	}
	
	public void CategoryMenuButton()
	{
		try
		{
			driver.findElement(By.xpath("//div[@to= 'category']/..")).click();
		}
		catch(Exception e)
		{
			ProductDropdownMenu();
			driver.findElement(By.xpath("//div[@to= 'category']/..")).click();
		}
	}

// Elements in  Category Page
	public void Searchfield(String SearchValue) throws InterruptedException
	{
		WebElement e= driver.findElement(By.xpath("//input[@placeholder= 'Search' and @name= '#0']"));
		ClearAndEnterValue(e, SearchValue);
	}
	
	public void SelectStatus(String Status)
	{
		driver.findElement(By.xpath("//input[@placeholder= 'Select Status']")).click();
		SelectDropdownValue(driver, testcase, Status);
	}
	
	public void ColumnChooserMenu(String HideOrShow)
	{
		driver.findElement(By.xpath("//button[contains(@class, 'css-mfslm7')]")).click();
		if(HideOrShow.toLowerCase().contains("hide all"))
    	{
    		driver.findElement(By.xpath("//button[text()= 'Hide All']")).click();
    		driver.findElement(By.xpath("//button[text()= 'Configure Now']")).click();
    	}
    	else
    	{
    		driver.findElement(By.xpath("//button[text()= 'Show All']")).click();
    		driver.findElement(By.xpath("//button[text()= 'Configure Now']")).click();
    	}
	}
	
	public void ChangeStatusButtons()
    {
    	WebElement e= driver.findElement(By.xpath("//button[contains (text(), 'Active') or contains(text(), 'Inactive')]"));
    	testcase.log(INFO, "Current status is: "+ e.getText());
    	e.click();
    	testcase.log(INFO, "Status updated to: "+ e.getText());
    }
	
	public void EditButton(int index)
    {
    	driver.findElement(By.xpath("(//button[contains(@class,'css-1ia3zz3')])["+ index +"]")).click();
    }
	
	public void DeleteButton(int index) throws InterruptedException
    {
    	driver.findElement(By.xpath("(//button[contains(@class, 'css-1ffkwrf')])["+ index +"]")).click();
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
	public void DeletedMessageAndClickOnOkButton(String Data) throws IOException
    {
   	   	String s= driver.findElement(By.xpath("(//div[@role= 'dialog']//h2/following-sibling::div)[1]")).getText();
	   	driver.findElement(By.xpath("//button[text()= 'OK']")).click();
	    if(s.toLowerCase().contains("success"))
	    {
	    	testcase.log(PASS, Data +" deleted and the '"+ s +"' message is displayed");
    	}
    	else
    	{
    		testcase.log(FAIL, "Failed To Delete");
    	}
		driver.findElement(By.xpath("//button[text()= 'OK']")).click();
    }
	
	public String ConfirmationMessage()
	{
		return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
	}
	
// Add New Category
	public void AddNewCategoryButton()
	{
		driver.findElement(By.xpath("//button[@class= 'btn btn-primary-600']")).click();
	}
	
	public void EnterCategoryName(String Name) throws InterruptedException
	{
		WebElement e= driver.findElement(By.xpath("//input[@id= 'title']"));
		ClearAndEnterValue(e, Name);
	}
	
	public void SelectType(String Type)
	{
		driver.findElement(By.xpath("//label[text()= 'Select Type']/..//div")).click();
		try
		{
			driver.findElement(By.xpath("//div[@role= 'option' and contains(text(), '"+ Type +"')]")).click();
		}
		catch(Exception e)
		{
			testcase.log(FAIL, "Please enter a valid Type. The type must be either 'Material' or 'Machinery' or  'Labour' or 'Product'");
		}
	}
	
	public void SaveButton()
	{
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
	}
	
	public void CancelButton()
	{
		driver.findElement(By.xpath("//button[@type= 'reset']")).click();
	}
	
	
}
