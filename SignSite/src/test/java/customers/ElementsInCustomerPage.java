package customers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInCustomerPage extends Initialstep
{
	
	ElementsInCustomerPage(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}

// Customer Page
	public void CustomerMenuButton()
	{
		driver.findElement(By.xpath("//div//span[text()= 'Customer']/..")).click();
	}
	
	public void SearchField(String SearchValue) throws InterruptedException
    {
        WebElement e= driver.findElement(By.xpath("//input[@name='#0']"));
        e.click();
        e.sendKeys(Keys.CONTROL, "a");
        Thread.sleep(1000);
        e.sendKeys(SearchValue);
    }
    
    public void SelectStatusOfFirstRow()
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select Status']")).click();
        String status= driver.findElement(By.xpath("(//div[@data-field= 'status'])[2]//button")).getText();
        driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ status +"']")).click();
    }
	
    public void EditButton()
    {
    	driver.findElement(By.xpath("(//div[@data-rowindex= '0']//button[@type= 'button' and @style])[1]")).click();
    }
    
    public String DeleteActionWithSuccessMessage() throws InterruptedException
    {
    	WebElement Delete= driver.findElement(By.xpath("(//div[@data-rowindex= '0']//button[@type= 'button' and @style])[2]"));
    	JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", Delete);
		Thread.sleep(1000);
		Delete.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    	return driver.findElement(By.xpath("//div[text()= 'Successfully deleted.']")).getText();
    }
    
    public void OKButtonAfterDelete()
    {
    	driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'OK']"));
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
    
    public String FirstMainColumnValueToSearch()
    {
    	return driver.findElement(By.xpath("(//div[@data-field= 'business_name'])[2]")).getText();
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
    
// Add New Customer
	public void AddNewCustomerButton()
    {
    	driver.findElement(By.xpath("//button[@class= 'btn btn-primary-600']")).click();
    }
	
	public WebElement CustomersPage() //When clicking on Add New Customer Button
    {
    	return driver.findElement(By.xpath("//h6[text()= 'Customers']"));
    }
    
    public void ClickOnInfoTab()
    {
    	driver.findElement(By.xpath("//button[@id= 'simple-tab-0']")).click();
    }
    
    public void EnterBusinessName(String BName)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'business_name']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(BName);
    }
    
    public void EnterTradingName(String TName)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'trading_name']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(TName);
    }
    
    public void DoNotSupplyToggleButton(String EnableorDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'do_not_supply']"));
    	if(EnableorDisable.toLowerCase().contains("enable"))
    	{
    		if(!e.isSelected()) {e.click();}
    	}
    	else
    	{
    		if(e.isSelected()) {e.click();}
    	}
    }
    
    public void DoNotCallToggleButton(String EnableorDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'do_not_call']"));
    	if(EnableorDisable.toLowerCase().contains("enable"))
    	{
    		if(!e.isSelected()) {e.click();}
    	}
    	else
    	{
    		if(e.isSelected()) {e.click();}
    	}
    }
        
    // Contact Details
    public void SelectContactType(String ContactType)
    {
    	driver.findElement(By.xpath("//label[text()= 'Contact Type']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ ContactType +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given contact type is not exist, selected first value from dropdown");
    	}
    }
    
    public void EnterContactName(String CName)
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()= 'Contact Name']/..//input"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(CName);
    }
    
    public void EnterContactEmail(String CEmail)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@type='email']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(CEmail);
    }
    
    public void EnableLoginCheckbox()
    {
    	driver.findElement(By.xpath("//label[text()= 'Contact Email']/..//input[@type='checkbox']")).click();
    }
    
    public void EnterContactPhone(String Number)
    {
    	WebElement e= driver.findElement(By.xpath("//input[contains (@id, 'phone')]"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Number);
    }
    
    // Address Details
    public void SelectAddressType(String AddressType)
    {
    	driver.findElement(By.xpath("//label[text()= 'Address Type']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and contains(text(), '"+ AddressType +"')]")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given address type is not exist, selected first value from dropdown");
    	}
    }
    
    public void EnterSearchOnlineAndChooseAddress(String SearchOnline) throws InterruptedException
    {
    	driver.findElement(By.xpath("//input[@id= 'google-autocomplete']")).sendKeys(SearchOnline);
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//div[@class= 'pac-item']")).click();
    }
    
    public void EnterStreetAddress(String SAddress)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your street address']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(SAddress);
    }
    
    public void EnterUnitNumber(String UNumber)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your unit number']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(UNumber);
    }
    
    public void EnterSuburb(String Suburb)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your suburb']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Suburb);
    }
    
    public void SelectCountry(String Country)
    {
    	driver.findElement(By.xpath("//label[text()= 'Country']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Country +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option'and text()= 'Australia']")).click();
    		testcase.log(INFO, "Since the given country is not exist, Australia is selected from dropdown");
    	}
    }
    
    public void SelectState(String State)
    {
    	driver.findElement(By.xpath("//label[text()= 'State']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ State +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given state is not exist, first value is selected from dropdown");
    	}
    }
    
    public void EnterPostcode(String Postcode)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder= 'Enter your postcode']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Postcode);
    }
    
    // Other Details
    public void SelectTerms(String Terms)
    {
    	driver.findElement(By.xpath("//label[text()= 'Terms']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Terms +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given Terms is not exist, first value is selected from dropdown");
    	}
    }
    
    public void SelectIndustry(String Industry)
    {
    	driver.findElement(By.xpath("//label[text()= 'Industry']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Industry +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given Industry is not exist, first value is selected from dropdown");
    	}
    }
    
    public void SelectTax(String Tax)
    {
    	driver.findElement(By.xpath("//label[text()= 'Tax']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Tax +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given Tax is not exist, first value is selected from dropdown");
    	}
    }
    
    public void SelectSalesRep(String SalesRep)
    {
    	driver.findElement(By.xpath("//label[text()= 'Sales Rep']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ SalesRep +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given Sales Rep is not exist, first value is selected from dropdown");
    	}
    }
    
    public void EnterABN(String ABN)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder= 'Enter your ABN']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(ABN);
    }
    
    public void EnterNotes(String Notes)
    {
    	WebElement e= driver.findElement(By.xpath("//textarea[@id= 'notes']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Notes);
    }
    
    public void EnterSpecialNotes(String SNotes)
    {
    	WebElement e= driver.findElement(By.xpath("//textarea[@id= 'special_notes']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(SNotes);
    }
    
    public void ClickOnCancelButton(String Index)
    {
    	driver.findElement(By.xpath("(//button[text()= 'Cancel'])["+ Index +"]")).click();
    }
    
    public void ClickOnSaveButton(String Index) throws InterruptedException
    {
    	WebElement Save= driver.findElement(By.xpath("(//button[text()= 'Save'])["+ Index +"]"));
    	JavascriptExecutor js= (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", Save);
    	Thread.sleep(1000);
    	Save.click();
    }

// Edit Contact Details
    public void AddNewContactButton()
    {
    	driver.findElement(By.xpath("//button[text()= ' Add New Contact']")).click();
    }
    
    public void EditPrimaryContactButton()
    {
    	driver.findElement(By.xpath("(//span[text()= 'primary']/../..//button[@type= 'button'])[1]")).click();
    }
    
    public void DeletePrimaryContactButton() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//span[text()= 'primary']/../..//button[@type= 'button'])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void SwitchToPrimaryContactButton()
    {
    	driver.findElement(By.xpath("(//span[text()= 'secondary']/../..//button[@type= 'button'])[1]")).click();
    }
    
    public void EditSecondaryContactButton()
    {
    	driver.findElement(By.xpath("(//span[text()= 'secondary']/../..//button[@type= 'button'])[2]")).click();
    }
    
    public void DeleteSecondaryContactButton() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//span[text()= 'secondary']/../..//button[@type= 'button'])[3]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
// Edit Address Details
    public void AddNewAddressButton()
    {
    	driver.findElement(By.xpath("//button[text()= ' Add New Address']")).click();
    }
    
    public void EditAddressButton()
    {
    	driver.findElement(By.xpath("((//div[@class= 'row'])[3]//button[@type= 'button'])[1]")).click();
    }
    
    public void DeleteAddressButton() throws InterruptedException
    {
    	driver.findElement(By.xpath("((//div[@class= 'row'])[3]//button[@type= 'button'])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
// Discount Tab
    public void DiscountTab()
	{
		driver.findElement(By.xpath("//button[@id= 'simple-tab-1']")).click();
	}
    
    public void SelectDiscountCategory(String Category)
    {
    	driver.findElement(By.xpath("//label[text()= 'Category']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Category +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given category is not exist, first value is selected from dropdown");
    	}
    }
    
    public void EnterDiscount(String Discount)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'discount']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Discount);
    }
    
// Add Tags    
    public void AddTagsButton() throws InterruptedException
    {
    	JavascriptExecutor js= (JavascriptExecutor)driver;
    	WebElement addtag= driver.findElement(By.xpath("//div[@data-field='tags' and not(contains(@role, 'columnheader'))]"));
		js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", addtag);
		Thread.sleep(1500);
		addtag.click();
    }
    
    public void SelectTags(String TagName) throws InterruptedException
    {
    	driver.findElement(By.xpath("//input[contains(@id, 'react-select-')]")).click();
		Thread.sleep(1500);
		try
		{
			driver.findElement(By.xpath("//div[@role= 'listbox']//div[text()= '"+TagName+"']")).click();
		}
		catch(Exception e)
		{
			driver.findElement(By.xpath("//div[text()= '➕ Add New Tag']")).click();
			driver.findElement(By.xpath("//input[@placeholder= 'Enter tag name']")).sendKeys(generateDummyName());
			driver.findElement(By.xpath("//input[@name= 'colour']")).sendKeys("#00FF00");
			SaveTagsButton();
			testcase.log(INFO, "Sine the give tag is not exist, New Tag is added");
			driver.findElement(By.xpath("//input[contains(@id, 'react-select-')]")).click();
			driver.findElement(By.xpath("//div[@role= 'listbox']")).click();
		}
    }
    
    public void SaveTagsButton()
    {
    	driver.findElement(By.xpath("//button[contains(text(), 'Save Tag')]")).click();
    }
}









