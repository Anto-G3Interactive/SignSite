package roles;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInRolesPage extends Initialstep
{
	WebDriver driver;
	ExtentTest testcase;

	public ElementsInRolesPage(WebDriver driver, ExtentTest testcase)
	{
		this.testcase= testcase;
		this.driver= driver;
	}

// Open Roles Page
	public void RolesAndUsersDropdownMenu()
	{
		driver.findElement(By.xpath("//span[text()= 'Roles And Users']/..")).click();
	}
	
	public void RolesMenuButton()
	{
		try
		{
			driver.findElement(By.xpath("//div[@to= 'role']/..")).click();
		}
		catch(Exception e)
		{
			RolesAndUsersDropdownMenu();
			driver.findElement(By.xpath("//div[@to= 'role']/..")).click();
		}
	}

// Role Access Page
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
    
// Add New Role    
    public void AddNewRoleButton()
    {
    	driver.findElement(By.xpath("//button[@class= 'btn btn-primary-600']")).click();
    }
    
    public void EnterRoleName(String name) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'title']"));
    	ClearAndEnterValue(e, name);
    }
    
    public void ClickOnAllPermissionsButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()= 'Select All Permissions']/..//input[@type= 'checkbox']"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
		    public void enableOrDisableCheck(String EnableOrDisable, WebElement e)
		    {
		    	if(EnableOrDisable.toLowerCase().contains("enable"))
		    	{
		    		if(!e.isSelected()) {e.click();}
		    	}
		    	else if(EnableOrDisable.toLowerCase().contains("disable"))
		    	{
		    		if(e.isSelected()) {e.click();}
		    	}
		    	else 
		    	{
		            throw new IllegalArgumentException("Input must be either 'enable' or 'disable'.");
		        }
		    }
    
    public void ClickOnAllQuotesRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Quotes']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllSalesOrderRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Sales Order']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllJobsRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Jobs']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllCustomerRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Customer']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllRolesofRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Roles']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllUsersRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Users']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllProductsRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Products']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllCategoryRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Category']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllMaterialRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Material']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllMachineryRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Machinery']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllLabourRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Labour']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllAccountManagementRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Account Management']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllTermsAndConditionsRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Terms And Conditions']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllEmailTemplatesRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Email Templates']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void ClickOnAllFormBuilderRolesButton(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("((//td[text()= 'Form Builder']/..)//input[@type= 'checkbox'])[1]"));
    	enableOrDisableCheck(EnableOrDisable, e);
    }
    
    public void SelectRolesAndAccessPerPage(String page, String roles) throws InterruptedException
    {
    	boolean disable = roles.toLowerCase().contains("disable");

        String[] roleTypes = {"view", "add", "edit", "delete"};
        
        for (String role : roleTypes) 
        {
            if (roles.toLowerCase().contains(role)) 
            {
                String xpath = String.format("(//td[text()= '%s']/..)//label[text()= '%s']/..//input[@type= 'checkbox']", page, role);
                WebElement checkbox = driver.findElement(By.xpath(xpath));
                
                if (checkbox.isEnabled()) 
                {
                    if (disable && checkbox.isSelected()) 
                    {
                        checkbox.click();
                    }
                    else if (!disable && !checkbox.isSelected()) 
                    {
                        checkbox.click();
                    }
                }
            }
        }

        Thread.sleep(1000);
    }
    
    public void QuotesPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {    	
    	SelectRolesAndAccessPerPage("Quotes", rolesforpage);
    }
    
    public void SalesOrderPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {    	
    	SelectRolesAndAccessPerPage("Sales Order", rolesforpage);
    }
    
    public void JobsPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {    	
    	SelectRolesAndAccessPerPage("Jobs", rolesforpage);
    }
    
    public void CustomerPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Customer", rolesforpage);  	
    }
    
    public void RolesPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Roles", rolesforpage);    
    }
    
    public void UsersPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Users", rolesforpage);
    }
    
    public void ProductsPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Products", rolesforpage);
    }
    
    public void CategoryPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Category", rolesforpage);
    }
    
    public void MaterialPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Material", rolesforpage);
    }
    
    public void LabourPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Labour", rolesforpage);
    }
    
    public void MachineryPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Machinery", rolesforpage);
    }
    
    public void AccountManagementPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Account Management", rolesforpage);
    }
    
    public void TermsandConditionsSelectPageRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Terms and Conditions", rolesforpage);
    }
    
    public void EmailTemplatesPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Email Templates", rolesforpage);
    }
    
    public void FormBuilderPageSelectRolesAndAccess(String rolesforpage) throws InterruptedException
    {
    	SelectRolesAndAccessPerPage("Form Builder", rolesforpage);
    }
    
    public void CancelRoleButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
    }
    
    public void SaveRoleButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Save']")).click();
    }
    
    public String ConfirmationMessage()
    {
    	return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
    }
    
    public String DuplicateRole()
    {
    	return driver.findElement(By.xpath("(//div[@data-field= 'title'])[2]")).getText();
    }

	public WebElement DeleteConfirmation() 
	{
		return driver.findElement(By.xpath("//div[contains(text(), 'uccess')]"));
	}
}









