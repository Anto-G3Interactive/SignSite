package quotes;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInQuotesPage extends Initialstep
{
	ElementsInQuotesPage(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
// Open Quotes Page
	public void JobsDropdownMenu()
	{
		driver.findElement(By.xpath("//span[text()= 'Jobs']/..")).click();
	}
	
	public void QuotesMenuButton()
	{
		driver.findElement(By.xpath("//div[@to= 'quote']/..")).click();
	}
	
// Quotes Page
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
    	driver.findElement(By.xpath("//button[contains(@class, 'css-1owy3pf')]")).click();
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
    
    public String ConfirmationAlerts()
    {
    	return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
    }
    
    public WebElement UsersPageHeading()
    {
    	return driver.findElement(By.xpath("//h6[text()= 'Quotes']"));
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
    	driver.findElement(By.xpath("//button[contains(@class, 'css-mfslm7')]")).click();
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
    public String MainColumnFirstRowData()
    {
    	return driver.findElement(By.xpath("(//div[@data-field= 'quote_name'])[2]")).getText();
    }
    
    public void QuoteNumberButton()
    {
    	driver.findElement(By.xpath("//button[contains(@class, 'css-1c69r7k')]")).click();
    }
    
// Add New Quotes (Minimal Form)
    public void AddNewQuotesButton()
    {
    	driver.findElement(By.xpath("//button[@class= 'btn btn-primary-600']")).click();
    }
    
    public void SelectCustomer(String Name)
    {
    	driver.findElement(By.xpath("//label[contains(text(), 'Customer')]/..//input")).click();
    	SelectDropdownValue(Name);
    }
    
    public void SelectQuoteStatus(String status)
    {
    	driver.findElement(By.xpath("//label[text()= 'Status']/..//input")).click();
    	SelectDropdownValue(status);
    }
    
    public void SelectQuoteDate(String Date)
    {
    	driver.findElement(By.xpath("//input[@name= 'quote_date']")).sendKeys(Date);
    }
    
    public void SelectExpiryDate(String Date)
    {
    	driver.findElement(By.xpath("//input[@name= 'expiry_date']")).sendKeys(Date);
    }
    
    public void SelectTax(String tax)
    {
    	driver.findElement(By.xpath("//label[text()= 'Tax']/..//input")).click();
    	SelectDropdownValue(tax);
    }
    
    public void ShowAllFieldsButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Show All Fields']")).click();
    }
    
    public void SaveandAddFirstLineItemButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Save & Add First Line Item']")).click();
    }
    
// All Fields for New Quote
    
    public void AddNewCustomersButton()
    {
    	driver.findElement(By.xpath("//label[text()= 'Customers']/../..//button")).click();
    }
    
    public void EnterQuoteTitle(String QTitle) throws InterruptedException
    {
    	 WebElement e= driver.findElement(By.xpath("//input[@id= 'quote_name']"));
    	 ClearAndEnterValue(e, QTitle);
    }
    
    // Contacts  
	    public void SelectPrimaryContact(String Name)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Primary Contact')]/..//input")).click();
	    	SelectDropdownValue(Name);
	    }
	    
	    public void AddNewPrimaryContactButton()
	    {
	    	driver.findElement(By.xpath("//label[text()= 'Primary Contact']/../..//button")).click();
	    }
	    
	    public void SelectInvoiceContact(String Name)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Invoice Contact')]/..//input")).click();
	    	SelectDropdownValue(Name);
	    }
	    
	    public void AddNewInvoiceContactButton()
	    {
	    	driver.findElement(By.xpath("//label[text()= 'Invoice Contact']/../..//button")).click();
	    }
    
    // Address & Shipping   
	    public void SelectBillingAddress(String Address)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Billing Address')]/..//input")).click();
	    	SelectDropdownValue(Address);
	    }
	    
	    public void SelectShippingAddress(String Address)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Shipping Address')]/..//input")).click();
	    	SelectDropdownValue(Address);
	    }
	    
	    public void SelectInstallAddress(String Address)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Install Address')]/..//input")).click();
	    	SelectDropdownValue(Address);
	    }
	    
	    public void EnterBillingAttentionTo(String To) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@id='billing_attend_to']"));
	    	ClearAndEnterValue(e, To);
	    }
	    
	    public void EnterShippingAttentionTo(String To) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@id='shipping_attend_to']"));
	    	ClearAndEnterValue(e, To);
	    }
	    
	    public void EnterInstallAttentionTo(String To) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@id='install_attend_to']"));
	    	ClearAndEnterValue(e, To);
	    }
	    
	    public void SelectShippingMethod(String Method)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Shipping Method')]/..//input")).click();
	    	SelectDropdownValue(Method);
	    }
	    
	    public void AddNewShippingMethodButton()
	    {
	    	driver.findElement(By.xpath("//label[text()= 'Shipping Method']/../..//button")).click();
	    }
    
    // Dates    
	    public void EnterDueDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='due_date']")).sendKeys(Date);
	    }
	    
	    public void EnterNextContactDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='next_contact_date']")).sendKeys(Date);
	    }
	    
	    public void EnterShippingDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='shipping_date']")).sendKeys(Date);
	    }
	    
	    public void EnterInHandDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='shipping_date']")).sendKeys(Date);
	    }
    
    // Payment & Tax    
	    public void SelectTerms(String Terms)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Terms')]/..//input")).click();
	    	SelectDropdownValue(Terms);
	    }
	    
	    public void EnterDownPaymenPercentage(String Percentage) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@id='downpayment_perc']"));
	    	ClearAndEnterValue(e, Percentage);
	    }
    
    // Team Assignments    
	    public void SelectSalesRep(String Tax)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Sales Rep')]/..//input")).click();
	    	SelectDropdownValue(Tax);
	    }
	    
	    public void SelectProductionManager(String Manager)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Production Manager')]/..//input")).click();
	    	SelectDropdownValue(Manager);
	    }
	    
	    public void SelectProjectManager(String Manager)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Project Manager')]/..//input")).click();
	    	SelectDropdownValue(Manager);
	    }
	    
	    public void SelectDesigner(String Designer)
	    {
	    	driver.findElement(By.xpath("//label[contains(text(), 'Designer')]/..//input")).click();
	    	SelectDropdownValue(Designer);
	    }
    
    // Notes    
	    public void EnterSpecialInfo(String Info) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//textarea[@id='special_info']"));
	    	ClearAndEnterValue(e, Info);
	    }
	    
	    public void EnterCustomerNote(String Info) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//textarea[@id='customer_note']"));
	    	ClearAndEnterValue(e, Info);
	    }
    
    // Other
	    public void EnterCustomerPO(String CPO) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@id='customer_po']"));
	    	ClearAndEnterValue(e, CPO);
	    }
	    
	    public void EnterCustomerPODate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='customer_po_date']")).sendKeys(Date);
	    }
	    
	    public void SaveandUpdateFirstLineItemButton()
	    {
	    	driver.findElement(By.xpath("//button[text()= 'Save & Update First Line Item']")).click();
	    }
	    
	    public void CancelButton()
	    {
	    	driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
	    }
    
// View Quotes Page    
    
    public void EditQuoteButton()
    {
    	driver.findElement(By.xpath("//button[text()= 'Edit']")).click();
    }
    
    // Expand Details
	    public void ExpandDetailsButton()
	    {
	    	driver.findElement(By.xpath("//div[@class= 'MuiBox-root css-6kl07i']")).click();
	    }
	    
		    public void ConfirmSelectionButton()
		    {
		    	driver.findElement(By.xpath("//div[@class= 'MuiBox-root css-tzzplu']//button[contains(@class, 'css-mfslm7')]")).click();
		    }
		    
		    public void ClearSelectionButton()
		    {
		    	driver.findElement(By.xpath("//button[contains(@class, 'css-1o3l9i0')]")).click();
		    }
		    
		    public void ClearOrSelectTheDetails(String Data)
		    {
		    	if(Data.equals(""))
		    	{
		    		ClearSelectionButton();
		    	}
		    	else
		    	{
		    		driver.findElement(By.xpath("//input[contains(@class, 'css-qwdxx6')]")).click();
			    	SelectDropdownValue(Data);
		    	}
		    	ConfirmSelectionButton();
		    }
		    
		    public void EditandSelectCustomer(String Customer)
		    {
		    	driver.findElement(By.xpath("//button[contains(@class, 'css-hdws54')]")).click();
		    	ClearOrSelectTheDetails(Customer);
		    }
		    
		    // Customer	    
		    	public void EditandSelectPrimaryContact(String PContact)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Primary Contact']//button")).click();
			    	ClearOrSelectTheDetails(PContact);
			    }
			    
			    public void EditandSelectInvoiceContact(String IContact)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Invoice Contact']//button")).click();
			    	ClearOrSelectTheDetails(IContact);
			    }
		    
			// Status		    
			    public void EditandSelectStatus(String Status)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Status']//button")).click();
			    	ClearOrSelectTheDetails(Status);
			    }
			
		    // Team Assignments		    
			    public void EditandSelectSalesRep(String SRep)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Status']//button")).click();
			    	ClearOrSelectTheDetails(SRep);
			    }
			    
			    public void EditandSelectProductionManager(String ProdManager)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Production Manager']//button")).click();
			    	ClearOrSelectTheDetails(ProdManager);
			    }
			    
			    public void EditandSelectProjectManager(String ProjManager)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Project Manager']//button")).click();
			    	ClearOrSelectTheDetails(ProjManager);
			    }
			    
			    public void EditandSelectDesigner(String Designer)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Designer']//button")).click();
			    	ClearOrSelectTheDetails(Designer);
			    }
			    
			// Address & Shipping
			    public void EditandSelectBillingAddress(String Address)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Billing Address']//button")).click();
			    	ClearOrSelectTheDetails(Address);
			    }
			    
			    public void EditandSelectShippingAddress(String Address)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Shipping Address']//button")).click();
			    	ClearOrSelectTheDetails(Address);
			    }
			    
			    public void EditandSelectInstallAddress(String Address)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Install Address']//button")).click();
			    	ClearOrSelectTheDetails(Address);
			    }
			    
			    public void EditandEnterBillingAttentionTo(String To) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Billing Attention To']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Billing Attention To']/..//input"));
			    	ClearAndEnterValue(e, To);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterShippingAttentionTo(String To) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Shipping Attention To']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Shipping Attention To']/..//input"));
			    	ClearAndEnterValue(e, To);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterInstallAttentionTo(String To) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Install Attention To']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Install Attention To']/..//input"));
			    	ClearAndEnterValue(e, To);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandSelectShippingMethod(String Method)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Shipping Method']//button")).click();
			    	ClearOrSelectTheDetails(Method);
			    }
			    
			// Payment & Tax
			    public void EditandSelectTerms(String Terms)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Terms']//button")).click();
			    	ClearOrSelectTheDetails(Terms);
			    }
			    
			    public void EditandEnterDownpaymentPercentage(String Value, String DollerOrPercentage) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Downpayment Percentage']//button")).click();
			    	if(DollerOrPercentage.toLowerCase().contains("doller")||DollerOrPercentage.equals("$"))
			    	{
			    		driver.findElement(By.xpath("//button[@value= '$']")).click();
			    	}
			    	else
			    	{
			    		driver.findElement(By.xpath("//button[@value= '%']")).click();
			    	}
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Downpayment Percentage']/..//input"));
			    	ClearAndEnterValue(e, Value);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandSelectTax(String Tax)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Tax']//button")).click();
			    	ClearOrSelectTheDetails(Tax);
			    }
			    
			// Notes
			    public void EditandEnterSpecialInfo(String Info) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Special Info']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Special Info']/..//input"));
			    	ClearAndEnterValue(e, Info);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterCustomerNote(String Note) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Customer Note']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Customer Note']/..//input"));
			    	ClearAndEnterValue(e, Note);
			    	ConfirmSelectionButton();
			    }
			    
			// Dates
			    public void EditandEnterQuoteDate(String Date)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Quote Date']//button")).click();
			    	driver.findElement(By.xpath("//div[text()= 'Quote Date']/..//input")).sendKeys(Date);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterDueDate(String Date)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Due Date']//button")).click();
			    	driver.findElement(By.xpath("//div[text()= 'Due Date']/..//input")).sendKeys(Date);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterExpiryDate(String Date)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Expiry Date']//button")).click();
			    	driver.findElement(By.xpath("//div[text()= 'Expiry Date']/..//input")).sendKeys(Date);
			    	ConfirmSelectionButton();
			    }
			    
			// Other
			    public void EditandEnterQuoteTitle(String Title) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Quote Title']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Quote Title']/..//input"));
			    	ClearAndEnterValue(e, Title);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterCustomerPO(String CPO) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Customer PO']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Customer PO']/..//input"));
			    	ClearAndEnterValue(e, CPO);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterCustomerPODate(String Date)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Customer PO Date']//button")).click();
			    	driver.findElement(By.xpath("//div[text()= 'Customer PO Date']/..//input")).sendKeys(Date);
			    	ConfirmSelectionButton();
			    }
			    
			    public void EditandEnterShippingTracking(String STracking) throws InterruptedException
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Shipping Tracking']//button")).click();
			    	WebElement e= driver.findElement(By.xpath("//div[text()= 'Shipping Tracking']/..//input"));
			    	ClearAndEnterValue(e, STracking);
			    	ConfirmSelectionButton();
			    }
		    
	// Send Email
		public void SendEmailButton()
		{
			driver.findElement(By.xpath("//button[text()= 'Send Email']")).click();
		}
		
		public void ToCustomer(String Customer)
		{
			driver.findElement(By.xpath("//div[contains (@id, 'select-customer')]")).click();
			SelectDropdownValue(Customer);
		}
		
		public void CC(String CC)
		{
			driver.findElement(By.xpath("//div[contains(@class, 'css-1kmkvia')]")).click();
			SelectDropdownValue(CC);
		}
		    
	// Items
		public void AddItemsButton()
		{
			driver.findElement(By.xpath("//button[text()= 'Add Items']")).click();
		}
		    
}









