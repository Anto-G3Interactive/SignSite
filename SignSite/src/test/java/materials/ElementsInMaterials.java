package materials;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import basepack.Initialstep;

public class ElementsInMaterials extends Initialstep
{
    WebDriver driver;
    public ElementsInMaterials(WebDriver driver, ExtentTest testcase) 
    {
        this.driver = driver;
        this.testcase= testcase;
    }

// Open Material Page
    public void ProductDropdownMenu() 
    {
        driver.findElement(By.xpath("//span[text()='Products']")).click();
    }  
    public void MaterialMenuButton() 
    {
        driver.findElement(By.xpath("//div[text()='Material']")).click();
    }
    
// Elements of Material Page
    public WebElement SearchField()
    {
        return driver.findElement(By.xpath("//input[@name='#0']"));
    }
    
    public void SelectCategory() throws InterruptedException
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select Category']")).click();
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
    
    public void EditButton()
    {
    	driver.findElement(By.xpath("(//div[@data-rowindex= '0']//button[@type= 'button' and @style])[1]")).click();
    }
    
    public void DeleteButton() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//div[@data-rowindex= '0']//button[@type= 'button' and @style])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void ChangeStatusButtons()
    {
    	WebElement e= driver.findElement(By.xpath("//button[contains (text(), 'Active') or contains(text(), 'Inactive')]"));
    	testcase.log(INFO, "Current status is: "+ e.getText());
    	e.click();
    	testcase.log(INFO, "Status updated to: "+ e.getText());
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

// Add New Material page
    public void AddNewMaterial()
    {
        driver.findElement(By.xpath("//button[text()= ' Add New Material']")).click();
    }
    
    public void SelectMaterialCategory(String Category)
    {
    	driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[text()= '"+ Category +"' and @role= 'option']")).click();
    	}
    	catch (Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the provided category is not exist, first category is selected from dropdown");
    	}
    }
    
    public void EnterMaterialName(String Name) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'name']"));
    	e.click();
		e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Name);
    }
    
    public void SelectUnit (String unit)
    {
    	driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[text()= '"+ unit +"' and @role= 'option']")).click();
    	}
    	catch (Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the provided category is not exist, first category is selected from dropdown");
    	}
    }
    
    public void EnterCost(String cost) 
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'cost']"));
    	e.click();
		e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(cost);
    }
    
    public void EnterMarkup(String Markup) 
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'markup']"));
    	e.click();
		e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Markup);
    }
    
    public void EnterDescription (String Description)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'description']"));
    	e.click();
		e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Description);
    }
    
    public void EnterPODescription (String PODescription)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'po_description']"));
    	e.click();
		e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(PODescription);
    }
    
    public void PerLIUnit()
    {
    	driver.findElement(By.xpath("//input[@id= 'is_linear_unit']")).click();
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
