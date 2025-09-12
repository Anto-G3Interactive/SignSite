package users;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInUsersPage extends Initialstep
{
	public ElementsInUsersPage(WebDriver driver, ExtentTest testcase)
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
		try
		{
			driver.findElement(By.xpath("//div[@to= 'user']/..")).click();
		}
		catch(Exception e)
		{
			RolesAndUsersDropdownMenu();
			driver.findElement(By.xpath("//div[@to= 'user']/..")).click();
		}
	}

// Users Page Elements
	public void SearchField(String SearchValue) throws InterruptedException
    {
        WebElement e= driver.findElement(By.xpath("//input[@name='#0']"));
        ClearAndEnterValue(e, SearchValue);
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
    
    public void EnterName(String Name) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'name']"));
    	ClearAndEnterValue(e, Name);
    }
    
    public void EnterEmail(String Email) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'email']"));
    	ClearAndEnterValue(e, Email);
    }
    
    public void EnterPhoneNumber(String Number) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'phone']"));
    	ClearAndEnterValue(e, Number);
    }
    
    public void EnterTitle(String Title) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'title']"));
    	ClearAndEnterValue(e, Title);
    }
    
    public void EnterInitials(String Initial) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'initials']"));
    	ClearAndEnterValue(e, Initial);
    }
    
    public void SelectRole(String Role)
    {
    	driver.findElement(By.xpath("//input[@role= 'combobox']")).click();
    	SelectDropdownValue(driver, testcase, Role);
    }
    
    public void ClickOnSalesRepCheckBox(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()='Sales Rep']/..//input[@name= 'responsibilities']"));
    	SelectCheckboxes(e, EnableOrDisable);
    }
    
    public void SelectCheckboxes(WebElement e, String EnableOrDisable)
    {
    	if(!e.isSelected() && EnableOrDisable.toLowerCase().contains("enable"))
    	{
    		e.click();
    	}
    	else if(e.isSelected() && EnableOrDisable.toLowerCase().contains("disable"))
    	{
    		e.click();
    	}
    }
    
    public void ClickOnProductionManagerCheckBox(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()='Production Manager']/..//input[@name= 'responsibilities']"));
    	SelectCheckboxes(e, EnableOrDisable);
    }
	
    public void ClickOnProjectManagerCheckBox(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()='Project Manager']/..//input[@name= 'responsibilities']"));
    	SelectCheckboxes(e, EnableOrDisable);
    }
    
    public void ClickOnDesignerCheckBox(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()='Designer']/..//input[@name= 'responsibilities']"));
    	SelectCheckboxes(e, EnableOrDisable);
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









