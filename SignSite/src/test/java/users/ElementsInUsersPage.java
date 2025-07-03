package users;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInUsersPage extends Initialstep
{
	ElementsInUsersPage(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}

// Open Users Page
	public void RolesAndUsersDropdownMenu()
	{
		driver.findElement(By.xpath("//span[text()= 'Roles And Users']/..")).click();
	}
	
	public void UsersMenuButton()
	{
		driver.findElement(By.xpath("//div[@to= 'user']/..")).click();
	}

// Users Page Elements
	public void SearchField(String SearchValue) throws InterruptedException
    {
        WebElement e= driver.findElement(By.xpath("//input[@name='#0']"));
        e.click();
        e.sendKeys(Keys.CONTROL, "a");
        Thread.sleep(1000);
        e.sendKeys(SearchValue);
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
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public String DeletedMessageAndClickOnOkButton() throws IOException
    {
    	String s= driver.findElement(By.xpath("//div[text()= 'Successfully deleted.']")).getText();
    	takescreenshot(driver, "Deleted Successfully");
    	driver.findElement(By.xpath("//button[text()= 'OK']")).click();
    	return s;
    }
    
    public void ChangeStatusButtons()
    {
    	WebElement e= driver.findElement(By.xpath("//button[contains (text(), 'Active') or contains(text(), 'Inactive')]"));
    	testcase.log(INFO, "Current status is: "+ e.getText());
    	e.click();
    	testcase.log(INFO, "Status updated to: "+ e.getText());
    }
    
    public String ConfirmationAlert()
    {
    	return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
    }
    
    public WebElement UsersPageHeading()
    {
    	return driver.findElement(By.xpath("//h6[text()= 'Users']"));
    }
    

    
    // Pagination
    public void RowsPerPage(String RPP)
    {
    	driver.findElement(By.xpath("//div[@aria-haspopup='listbox' and not(contains(@id, 'mui-component-select-company_id'))]")).click();
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
    
    // Data from first Row
    public String FirstColumnData()
    {
    	return driver.findElement(By.xpath("(//div[@data-field= 'first_name'])[2]")).getText();
    }
    
    public String FirstRowEmailData()
    {
    	return driver.findElement(By.xpath("(//div[@data-field= 'email'])[2]")).getText();
    }
    
	
// Add New User
    
    public void AddNewUserButton()
    {
    	driver.findElement(By.xpath("//button[@class= 'btn btn-primary-600']")).click();
    }
    
    public void EnterName(String Name)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'name']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Name);
    }
    
    public void EnterEmail(String Email)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'email']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Email);
    }
    
    public void EnterPhoneNumber(String Number)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'phone']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Number);
    }
    
    public void EnterTitle(String Title)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'title']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Title);
    }
    
    public void EnterInitials(String Initial)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'initials']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Initial);
    }
    
    public void SelectRole(String Role)
    {
    	driver.findElement(By.xpath("//input[@role= 'combobox']")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Role +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given value is not exist, selected the first value from dropdown");
    	}
    }
    
    public void SalesRepCheckBoxClick(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'responsibility_1']"));
    	if(!e.isSelected() && EnableOrDisable.toLowerCase().contains("enable"))
    	{
    		e.click();
    	}
    	else if(e.isSelected() && EnableOrDisable.toLowerCase().contains("disable"))
    	{
    		e.click();
    	}
    }
    
    public void ProductionManagerCheckBoxClick(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'responsibility_2']"));
    	if(!e.isSelected() && EnableOrDisable.toLowerCase().contains("enable"))
    	{
    		e.click();
    	}
    	else if(e.isSelected() && EnableOrDisable.toLowerCase().contains("disable"))
    	{
    		e.click();
    	}
    }
	
    public void ProjectManagerCheckBoxClick(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'responsibility_3']"));
    	if(!e.isSelected() && EnableOrDisable.toLowerCase().contains("enable"))
    	{
    		e.click();
    	}
    	else if(e.isSelected() && EnableOrDisable.toLowerCase().contains("disable"))
    	{
    		e.click();
    	}
    }
    
    public void DesignerCheckBoxClick(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'responsibility_4']"));
    	if(!e.isSelected() && EnableOrDisable.toLowerCase().contains("enable"))
    	{
    		e.click();
    	}
    	else if(e.isSelected() && EnableOrDisable.toLowerCase().contains("disable"))
    	{
    		e.click();
    	}
    }
    
    public void UploadAvatar(String Path)
    {
    	driver.findElement(By.xpath("//input[@id= 'imageUpload']")).sendKeys(Path);
    }
    
    public void DeleteAvatar() throws InterruptedException 
    {
    	driver.findElement(By.xpath("//label[contains(@class, 'text-danger-600')]")).click();
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void CancelButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
    }
    
    public void SaveButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Save']")).click();
    }
    
    public void ProceedWithNewButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Proceed with New']")).click();
    }
    
    public void ProceedWithExistingButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Proceed with Existing']")).click();
    }
    
}









