package customers;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInCustomerPage extends Initialstep
{
	
	public ElementsInCustomerPage(WebDriver driver, ExtentTest testcase)
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
        ClearAndEnterValue(e, SearchValue);
    }
    
    public void SelectStatusOfFirstRow()
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select Status']")).click();
        String status= driver.findElement(By.xpath("(//div[@data-field= 'status'])[2]//button")).getText();
        driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ status +"']")).click();
    }
	
    public void EditButton(int index)
    {
    	driver.findElement(By.xpath("(//button[contains(@class,'css-1ia3zz3')])["+ index +"]")).click();
    }
    
    public String DeleteActionWithReturnOfSuccessMessage() throws InterruptedException
    {
    	WebElement Delete= driver.findElement(By.xpath("//button[contains(@class, 'css-vacoer')]"));
    	JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", Delete);
		Thread.sleep(1000);
		Delete.click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    	return driver.findElement(By.xpath("(//div[@role= 'dialog']//h2/following-sibling::div)[1]")).getText();
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
    
    public String ConfirmationMessage() throws InterruptedException
    {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role= 'status']")));
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
    
    public void EnterBusinessName(String BName) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'business_name']"));
    	ClearAndEnterValue(e, BName);
    }
    
    public void EnterTradingName(String TName) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'trading_name']"));
    	ClearAndEnterValue(e, TName);
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
    	SelectDropdownValue(driver, testcase, ContactType);
    }
    
    public void EnterContactName(String CName) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()= 'Contact Name']/..//input"));
    	ClearAndEnterValue(e, CName);
    }
    
    public void EnterContactEmail(String CEmail) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@type='email']"));
    	ClearAndEnterValue(e, CEmail);
    }
    
    public void ClickOnLoginCheckbox(String EnableOrDisable)
    {
    	WebElement e= driver.findElement(By.xpath("//label[text()= 'Login?']/..//input"));
    	if (EnableOrDisable.toLowerCase().contains("disable"))
    	{
    		if(e.isSelected()) {e.click();}
    	}
    	else if (!e.isSelected())
    	{
    		e.click();
    	}
    }
    
    public void EnterContactPhone(String Number) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[contains (@id, 'phone')]"));
    	ClearAndEnterValue(e, Number);
    }
    
  // Address Details
    public void SelectAddressType(String AddressType)
    {
    	driver.findElement(By.xpath("//label[text()= 'Address Type']/..//div")).click();
    	SelectDropdownValue(driver, testcase, AddressType);
    }
    
    public void EnterSearchOnlineAndChooseAddress(String SearchOnline) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'google-autocomplete']"));
    	ClearAndEnterValue(e, SearchOnline);
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//div[@class= 'pac-item']")).click();
    }
    
    public void EnterStreetAddress(String SAddress) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your street address']"));
    	ClearAndEnterValue(e, SAddress);
    }
    
    public void EnterUnitNumber(String UNumber) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your unit number']"));
    	ClearAndEnterValue(e, UNumber);
    }
    
    public void EnterSuburb(String Suburb) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder='Enter your suburb']"));
    	ClearAndEnterValue(e, Suburb);
    }
    
    public void SelectCountry(String Country)
    {
    	driver.findElement(By.xpath("//label[text()= 'Country']/..//div")).click();
    	SelectDropdownValue(driver, testcase, Country);
    }
    
    public void SelectState(String State)
    {
    	driver.findElement(By.xpath("//label[text()= 'State']/..//div")).click();
    	SelectDropdownValue(driver, testcase, State);
    }
    
    public void EnterPostcode(String Postcode) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder= 'Enter your postcode']"));
    	ClearAndEnterValue(e, Postcode);
    }
    
  // Other Details
    public void SelectTerms(String Terms)
    {
    	driver.findElement(By.xpath("//label[text()= 'Terms']/..//div")).click();
    	SelectDropdownValue(driver, testcase, Terms);
    }
    
    public void SelectIndustry(String Industry)
    {
    	driver.findElement(By.xpath("//label[text()= 'Industry']/..//div")).click();
    	SelectDropdownValue(driver, testcase, Industry);
    }
    
    public void SelectTax(String Tax)
    {
    	driver.findElement(By.xpath("//label[text()= 'Tax']/..//div")).click();
    	SelectDropdownValue(driver, testcase, Tax);
    }
    
    public void SelectSalesRep(String SalesRep)
    {
    	driver.findElement(By.xpath("//label[text()= 'Sales Rep']/..//div")).click();
    	SelectDropdownValue(driver, testcase, SalesRep);
    }
    
    public void EnterABN(String ABN) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@placeholder= 'Enter your ABN']"));
    	ClearAndEnterValue(e, ABN);
    }
    
    public void EnterNotes(String Notes) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//textarea[@id= 'notes']"));
    	ClearAndEnterValue(e, Notes);
    }
    
    public void EnterSpecialNotes(String SNotes) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//textarea[@id= 'special_notes']"));
    	ClearAndEnterValue(e, SNotes);
    }
    
    public void ClickOnCancelButton(String Index)
    {
    	driver.findElement(By.xpath("(//button[text()= 'Cancel'])["+ Index +"]")).click();
    }
    
    public void SaveButton() throws InterruptedException
    {
    	WebElement Save= driver.findElement(By.xpath("//button[text()= 'Save']"));
    	JavascriptExecutor js= (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].scrollIntoView(true);", Save);
    	Thread.sleep(1000);
    	Save.click();
    }
    
    public void ClickOnPopUpSaveButton() throws InterruptedException
    {
    	WebElement Save= driver.findElement(By.xpath("(//button[text()= 'Save'])[2]"));
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
    
    public void EnterDiscount(String Discount) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'discount']"));
    	ClearAndEnterValue(e, Discount);
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









