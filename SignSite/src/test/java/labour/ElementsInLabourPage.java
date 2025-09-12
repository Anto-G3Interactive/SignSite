package labour;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInLabourPage extends Initialstep
{
    WebDriver driver;
    ExtentTest testcase;
    
    public ElementsInLabourPage(WebDriver driver, ExtentTest testcase) 
    {
        this.driver = driver;
        this.testcase = testcase;
    }

// Open Labour Page
    public void ProductDropdownMenu() 
    {
        driver.findElement(By.xpath("//span[text()='Products']")).click();
    }  
    public void LabourMenuButton() 
    {
    	try
    	{
    		driver.findElement(By.xpath("//div[@to='labour']/..")).click();
    	}
    	catch(Exception e)
    	{
    		ProductDropdownMenu();
    		driver.findElement(By.xpath("//div[@to='labour']/..")).click();
    	}
    }
    
// Elements of Labour Page
    public void SearchField(String SearchValue) throws InterruptedException
    {
        WebElement e= driver.findElement(By.xpath("//input[@name='#0']"));
        ClearAndEnterValue(e, SearchValue);
    }
    
    public void SelectCategory() throws InterruptedException
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select category']")).click();
    	Thread.sleep(1000);
    	String category= driver.findElement(By.xpath("(//div[@data-field= 'category'])[2]")).getText();
    	driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ category +"']")).click();
    }
    
    public void SelectStatus()
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select Status']")).click();
        String status= driver.findElement(By.xpath("(//div[@data-field= 'status'])[2]//button")).getText();
        driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ status +"']")).click();
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
    
    public void ChangeStatusButtons()
    {
    	WebElement e= driver.findElement(By.xpath("//button[contains (text(), 'Active') or contains(text(), 'Inactive')]"));
    	testcase.log(INFO, "Current status is: "+ e.getText());
    	e.click();
    	testcase.log(INFO, "Status updated to: "+ e.getText());
    }
    
    public String ConfirmationMessage()
	{
		return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
	}
    
    // Pagination
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
    	if (MovePage.contains("Next"))
    	{
    		driver.findElement(By.xpath("//button[@title= 'Go to next page']")).click();
    	}
    	else
    	{
    		driver.findElement(By.xpath("//button[@title= 'Go to previous page']")).click();
    	}
    }
    
    // Column Chooser
    public void ColumnChooserMenu(String HideOrShow)
    {
    	driver.findElement(By.xpath("(//button[@class= 'btn btn-primary-600']/../../..//button)[2]")).click();
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

// Add New Labour page
    public void AddNewLabourButton()
    {
        driver.findElement(By.xpath("//button[text()= ' Add New Labour']")).click();
    }
    
    public void SelectLabourCategory(String Category)
    {
    	driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
    	SelectDropdownValue(driver, testcase, Category);
    }
    
    public void EnterLabourName(String Name) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'name']"));
    	ClearAndEnterValue(e, Name);
    }
    
    public void SelectUnit (String unit)
    {
    	driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
    	SelectDropdownValue(driver, testcase, unit);
    }
    
    public void EnterCost(String cost) throws InterruptedException 
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'cost']"));
    	ClearAndEnterValue(e, cost);
    }
    
    public void EnterMarkup(String Markup) throws InterruptedException 
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'markup']"));
    	ClearAndEnterValue(e, Markup);
    }
    
    public void Cancel()
    {
    	driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
    }
    
    public void Save()
    {
    	driver.findElement(By.xpath("//button[@type= 'submit']")).click();
    }
    
}
