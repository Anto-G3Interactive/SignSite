package products;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInProductBulkDiscountPage extends Initialstep
{
	WebDriver driver;
	ExtentTest testcase;
	
	public ElementsInProductBulkDiscountPage(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
// Bulk Discount Tab
    public void BulkDiscountTab()
    {
    	driver.findElement(By.xpath("//button[@id= 'simple-tab-1']")).click();
    }
    
    public void ColumnChooserMenu(String HideOrShow)
    {
    	driver.findElement(By.xpath("(//button[@class= 'btn btn-primary-600']/../..//button)[2]")).click();
    	if(HideOrShow.contains("Hide All"))
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
        
    public void EditProduct(int index)
    {
    	driver.findElement(By.xpath("(//button[contains(@class,'css-1ia3zz3')])["+ index +"]")).click();
    }
    
    public void DeleteProduct() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//div[@data-rowindex= '0']//button[@type= 'button' and @style])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void RowsPerPage(String RPP)
    {
    	driver.findElement(By.xpath("//div[@aria-haspopup= 'listbox']")).click();
    	try
    	{
    		driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ RPP +"']")).click();
    	}
    	catch (Exception e)
    	{
    		driver.findElement(By.xpath("//li[@role= 'option']"));
    		testcase.log(INFO, "Since the Rows pre page entered is not exist, first option is selected from the dropdown");
    	}
    }
    
    public void NextAndPrevious(String MovePage)
    {
    	try
    	{
	    	if (MovePage.contains("Next"))
	    	{
	    		driver.findElement(By.xpath("//button[@title= 'Go to next page']")).click();
	    	}
	    	else
	    	{
	    		driver.findElement(By.xpath("//button[@title= 'Go to previous page']")).click();
	    	}
    	}
    	catch (Exception e)
    	{
    		testcase.log(INFO, "Number of page is less to check the pagination");
    	}
    }
    
// Add New Discount
    public void AddNewDiscountButton()
    {
    	driver.findElement(By.xpath("//button[@class='btn btn-primary-600']")).click();
    }
    
    public void EnterMinimumQuantity(String Min) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your minimum quantity']"));
    	ClearAndEnterValue(e, Min);
    }
    
    public void EnterMaximumQuantity(String Max) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your maximum quantity']"));
    	ClearAndEnterValue(e, Max);
    }
    
    public void EnterDiscountPercentage(String Discount) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[contains(@placeholder, 'Enter your discount')]"));
    	ClearAndEnterValue(e, Discount);
    }
    
    public void EditDiscountButton()
    {
    	driver.findElement(By.xpath("//button[contains(@class, 'css-1rh62m4')]")).click();
    	
    }
    
    public void DeleteDiscountButton() throws InterruptedException
    {
    	driver.findElement(By.xpath("//button[contains(@class, 'css-vacoer')]")).click();
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void DeletedMessageAndClickOnOkButton(String Data) throws IOException
    {
    	String s= driver.findElement(By.xpath("//div[contains(text(), 'uccess')]")).getText();
    	driver.findElement(By.xpath("//button[text()= 'OK']")).click();
    	testcase.log(PASS, Data +" "+ s);
    }
    
    public void CancelDiscount()
    {
    	driver.findElement(By.xpath("//button[@type= 'reset']")).click();
    }
    
    public void SaveDiscount()
    {
    	driver.findElement(By.xpath("//button[@type= 'submit']")).click();
    }
}
