package quotes;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInQuotesPage extends Initialstep
{
	public ElementsInQuotesPage(WebDriver driver, ExtentTest testcase)
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
        ClearAndEnterValue(e, SearchValue);
    }
    
    public void SelectStatus()
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select Status']")).click();
        String status= driver.findElement(By.xpath("(//div[@data-field= 'quote_status'])[2]")).getText();
        driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ status +"']")).click();
    }
	
    public void EditButton(int Index)
    {
    	driver.findElement(By.xpath("(//button[contains(@class, 'css-1ia3zz3')])["+ Index +"]")).click();
    }
    
    public void DeleteButton(int index) throws InterruptedException
    {
    	driver.findElement(By.xpath("(//button[contains(@class, 'css-vacoer')])["+ index +"]")).click();
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
    
    public WebElement QuotesPageHeading()
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
    
    public void QuoteNumberButton(int Index)
    {
    	driver.findElement(By.xpath("(//button[contains(@class, 'css-1c69r7k')])["+ Index +"]")).click();
    }
    
// Add New Quotes (Minimal Form)
    public void AddNewQuotesButton()
    {
    	driver.findElement(By.xpath("//button[@class= 'btn btn-primary-600']")).click();
    }
    
    public void SelectCustomer(String Name)
    {
    	driver.findElement(By.xpath("//p[contains(text(), 'Customer')]/..//input")).click();
    	SelectDropdownValue(driver, testcase, Name);
    }
    
    public void SelectQuoteStatus(String status)
    {
    	driver.findElement(By.xpath("//p[text()= 'Status ']/..//input")).click();
    	SelectDropdownValue(driver, testcase, status);
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
    	driver.findElement(By.xpath("//p[contains(text(), 'Tax')]/..//input")).click();
    	SelectDropdownValue(driver, testcase, tax);
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
    	driver.findElement(By.xpath("//p[text()= 'Customer ']/..//button[contains(@class, 'css-wy6pze')]")).click();
    }
    
    public void EnterQuoteTitle(String QTitle) throws InterruptedException
    {
    	 WebElement e= driver.findElement(By.xpath("//input[@name= 'title']"));
    	 ClearAndEnterValue(e, QTitle);
    }
    
    // Contacts  
	    public void SelectPrimaryContact(String Name)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Primary Contact')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Name);
	    }
	    
	    public void AddNewPrimaryContactButton()
	    {
	    	driver.findElement(By.xpath("//p[text()= 'Primary Contact']/..//button[contains(@class, 'css-wy6pze')]")).click();
	    }
	    
	    public void SelectInvoiceContact(String Name)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Invoice Contact')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Name);
	    }
	    
	    public void AddNewInvoiceContactButton()
	    {
	    	driver.findElement(By.xpath("//p[text()= 'Invoice Contact']/..//button[contains(@class, 'css-wy6pze')]")).click();
	    }
    
    // Address & Shipping   
	    public void SelectBillingAddress(String Address)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Billing Address')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Address);
	    }
	    
	    public void AddNewBillingAddressButton()
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Billing Address')]/..//button[contains(@class, 'css-wy6pze')]")).click();
	    }
	    
	    public void SelectShippingAddress(String Address)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Shipping Address')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Address);
	    }
	    
	    public void AddNewShippingAddressButton()
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Shipping Address')]/..//button[contains(@class, 'css-wy6pze')]")).click();
	    }
	    
	    public void SelectInstallAddress(String Address)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Install Address')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Address);
	    }
	    
	    public void AddNewInstallAddressButton()
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Install Address')]/..//button[contains(@class, 'css-wy6pze')]")).click();
	    }
	    
	    public void EnterBillingAttentionTo(String To) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@name='billing_attend_to']"));
	    	ClearAndEnterValue(e, To);
	    }
	    
	    public void EnterShippingAttentionTo(String To) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@name='shipping_attend_to']"));
	    	ClearAndEnterValue(e, To);
	    }
	    
	    public void EnterInstallAttentionTo(String To) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@name='install_attend_to']"));
	    	ClearAndEnterValue(e, To);
	    }
	    
	    public void SelectShippingMethod(String Method)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Shipping Method')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Method);
	    }
	    
	    public void AddNewShippingMethodButton()
	    {
	    	driver.findElement(By.xpath("//p[text()= 'Shipping Method']/..//button[contains(@class, 'css-wy6pze')]")).click();
	    }
    
    // Dates    
	    public void EnterDueDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='due_date']")).sendKeys(Date);
	    }
	    
	    public void EnterExpiryDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='expiry_date']")).sendKeys(Date);
	    }
	    
	    public void EnterNextContactDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='contact_date']")).sendKeys(Date);
	    }
	    
	    public void EnterShippingDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='shipping_date']")).sendKeys(Date);
	    }
	    
	    public void EnterInHandDate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='in_hand_date']")).sendKeys(Date);
	    }
    
    // Payment & Tax    
	    public void SelectTerms(String Terms)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Terms')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Terms);
	    }
	    
	    public void EnterDownPaymenPercentage(String Percentage) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@name='downpayment']"));
	    	ClearAndEnterValue(e, Percentage);
	    }
    
    // Team Assignments    
	    public void SelectSalesRep(String SalesRep)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Sales Rep')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, SalesRep);
	    }
	    
	    public void SelectProductionManager(String Manager)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Production Manager')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Manager);
	    }
	    
	    public void SelectProjectManager(String Manager)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Project Manager')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Manager);
	    }
	    
	    public void SelectDesigner(String Designer)
	    {
	    	driver.findElement(By.xpath("//p[contains(text(), 'Designer')]/..//input")).click();
	    	SelectDropdownValue(driver, testcase, Designer);
	    }
    
    // Notes    
	    public void EnterSpecialInfo(String Info) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//textarea[@name='special_info']"));
	    	ClearAndEnterValue(e, Info);
	    }
	    
	    public void EnterCustomerNote(String Info) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//textarea[@name='customer_note']"));
	    	ClearAndEnterValue(e, Info);
	    }
    
    // Other
	    public void EnterCustomerPO(String CPO) throws InterruptedException
	    {
	    	WebElement e= driver.findElement(By.xpath("//input[@name='customer_po']"));
	    	ClearAndEnterValue(e, CPO);
	    }
	    
	    public void EnterCustomerPODate(String Date)
	    {
	    	driver.findElement(By.xpath("//input[@name='customer_po_date']")).sendKeys(Date);
	    }
	    
	    public void SaveandAddorUpdateFirstLineItemButton()
	    {
	    	driver.findElement(By.xpath("//button[contains(text(), 'Save &')]")).click();
	    }
	    
	    public void CancelButton()
	    {
	    	driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
	    }
    
// View Quotes Page    
    
    public void EditQuoteButton()
    {
    	driver.findElement(By.xpath("//button[contains(@class, 'css-xyc1p3')]")).click();
    }
    
    // Expand Details
	    public void ExpandDetailsButton()
	    {
	    	driver.findElement(By.xpath("//div[@class= 'MuiBox-root css-6kl07i']")).click();
	    }
	    
		    public void ConfirmSelectionButton()
		    {
		    	driver.findElement(By.xpath("//div[@class= 'MuiBox-root css-tzzplu']//button[contains(@class, 'css-1rg4rld')]")).click();
		    }
		    
		    public void ClearSelectionButton()
		    {
		    	driver.findElement(By.xpath("//button[contains(@class, 'css-1o3l9i0')]")).click();
		    }
		    
		    public void ClearOrSelectTheDetailFromDropdown(String Data)
		    {
		    	if(Data.equals(""))
		    	{
		    		ClearSelectionButton();
		    	}
		    	else
		    	{
		    		driver.findElement(By.xpath("//input[contains(@class, 'css-qwdxx6')]")).click();
			    	SelectDropdownValue(driver, testcase, Data);
		    	}
		    	ConfirmSelectionButton();
		    }
		    
		    public void EditandSelectCustomer(String Customer)
		    {
		    	driver.findElement(By.xpath("//button[contains(@class, 'css-hdws54')]")).click();
		    	ClearOrSelectTheDetailFromDropdown(Customer);
		    }
		    
		    // Customer	    
		    	public void EditandSelectPrimaryContact(String PContact)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Primary Contact']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(PContact);
			    }
			    
			    public void EditandSelectInvoiceContact(String IContact)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Invoice Contact']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(IContact);
			    }
		    
			// Status		    
			    public void EditandSelectStatus(String Status)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Status']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(Status);
			    }
			
		    // Team Assignments		    
			    public void EditandSelectSalesRep(String SRep)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Sales Rep']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(SRep);
			    }
			    
			    public void EditandSelectProductionManager(String ProdManager)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Production Manager']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(ProdManager);
			    }
			    
			    public void EditandSelectProjectManager(String ProjManager)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Project Manager']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(ProjManager);
			    }
			    
			    public void EditandSelectDesigner(String Designer)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Designer']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(Designer);
			    }
			    
			// Address & Shipping
			    public void EditandSelectBillingAddress(String Address)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Billing Address']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(Address);
			    }
			    
			    public void EditandSelectShippingAddress(String Address)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Shipping Address']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(Address);
			    }
			    
			    public void EditandSelectInstallAddress(String Address)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Install Address']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(Address);
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
			    	ClearOrSelectTheDetailFromDropdown(Method);
			    }
			    
			// Payment & Tax
			    public void EditandSelectTerms(String Terms)
			    {
			    	driver.findElement(By.xpath("//div[text()= 'Terms']//button")).click();
			    	ClearOrSelectTheDetailFromDropdown(Terms);
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
			    	ClearOrSelectTheDetailFromDropdown(Tax);
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
				driver.findElement(By.xpath("//input[@placeholder= 'Select your Customer']")).click();
				SelectDropdownValue(driver, testcase, Customer);
			}
			
			public void CC(String CC)
			{
				driver.findElement(By.xpath("//input[@placeholder= 'Select your Cc']")).click();
				SelectDropdownValue(driver, testcase, CC);
			}
			
			public void ClickOnRequestReadRecipt(String EnableOrDisable)
			{
				WebElement e= driver.findElement(By.xpath("//label[text()= 'Request Read Reciept']/..//input"));
				boolean shouldEnable = EnableOrDisable.trim().equalsIgnoreCase("enable");

			    if (e.isSelected() != shouldEnable)
			    {
			        e.click();
			    }
			}
			
			public void EmailTemplate(String Template)
			{
				driver.findElement(By.xpath("//p[text()= 'Email Template']/..//input")).click();
				SelectDropdownValue(driver, testcase, Template);
			}
			
			public void Subject(String subject) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//input[@placeholder= 'Enter your Subject']"));
				ClearAndEnterValue(e, subject);
			}
			
			public void Message(String Message) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//div[@data-placeholder= 'Enter Message']"));
				ClearAndEnterValue(e, Message);
			}
			
			public void ClickOnIncludeLinkForPayment(String EnableOrDisable)
			{
				WebElement e= driver.findElement(By.xpath("//label[text()= 'Include Link For Payment']/..//input"));
				boolean shouldEnable = EnableOrDisable.trim().equalsIgnoreCase("enable");

			    if (e.isSelected() != shouldEnable)
			    {
			        e.click();
			    }
			}
			
			public void ClickOnQuotePDFCheckbox(String EnableOrDisable)
			{
				WebElement e= driver.findElement(By.xpath("//p[text()= 'Quote PDF']/../../..//input"));
				boolean shouldEnable = EnableOrDisable.trim().equalsIgnoreCase("enable");

			    if (e.isSelected() != shouldEnable)
			    {
			        e.click();
			    }
			}
			
			public void ClickOnQuoteNoTotalPDFCheckbox(String EnableOrDisable)
			{
				WebElement e= driver.findElement(By.xpath("//p[text()= 'Quote No Total PDF']/../../..//input"));
				boolean shouldEnable = EnableOrDisable.trim().equalsIgnoreCase("enable");

			    if (e.isSelected() != shouldEnable)
			    {
			        e.click();
			    }
			}
			
			public void ClickOnWorkOrderPDFCheckbox(String EnableOrDisable)
			{
				WebElement e= driver.findElement(By.xpath("//p[text()= 'Work Order PDF']/../../..//input"));
				boolean shouldEnable = EnableOrDisable.trim().equalsIgnoreCase("enable");

			    if (e.isSelected() != shouldEnable)
			    {
			        e.click();
			    }
			}
			
			public void ClickOnDownPaymentInvoicePDFCheckbox(String EnableOrDisable)
			{
				WebElement e= driver.findElement(By.xpath("//p[text()= 'Down Payment Invoice PDF']/../../..//input"));
				boolean shouldEnable = EnableOrDisable.trim().equalsIgnoreCase("enable");

			    if (e.isSelected() != shouldEnable)
			    {
			        e.click();
			    }
			}
			
/**			public void UploadAttachments(String Path)
			{
				driver.findElement(By.xpath("//input[@id= 'upload-image']")).sendKeys(Path);
			} **/
			
			public void SendButton()
			{
				driver.findElement(By.xpath("//p[text()= 'Send an Email']/../..//button[contains(@class, 'css-1rz2d4g')]")).click();
			}
			
			public void CloseButtonInSendAnEmail()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'css-1sl3a2d')]")).click();
			}
			
	// Quote Menu
		public void ClickOnThreeDotQuoteMenuButton()
		{
			driver.findElement(By.xpath("//button[contains(@class, 'css-1g49wez')]")).click();
		}
		
		// Record Payment
			public void ClickOnRecordPayment()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'Record Payment')]")).click();
			}
		
		// Convert
			public void ClickOnConvertToSalesOrder()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'To Sales Order')]")).click();
			}
				
				public void EditTransactionInformationButton()
				{
					driver.findElement(By.xpath("//button[contains(text(), 'Close Transaction Information')]")).click();
				}
				
////// Pending
				
				public void CloseTransactionInformationButton()
				{
					driver.findElement(By.xpath("//button[contains(text(), 'Edit Transaction Information')]")).click();
				}
				
				public void ConvertToSalesOrderButton()
				{
					driver.findElement(By.xpath("//button[contains(text(), 'Convert to Sales Order')]")).click();
				}
			
			public void ClickOnConvertToInvoice()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'To Invoice')]")).click();
			}
			
				public void ConvertToInvoiceButton()
				{
					driver.findElement(By.xpath("//button[contains(text(), 'Convert to invoice')]")).click();
				}
			
		// Create Copy
			public void ClickOnCreateCopyButton()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'Create Copy')]")).click();
			}
			
		// Export to PDF
			public void ClickOnExportQuoteToPDF()
			{
				driver.findElement(By.xpath("(//li[contains(text(), 'Quote')])[1]")).click();
			}
			
			public void ClickOnExportToNoTotal()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'No Total')]")).click();
			}
			
			public void ClickOnExportToWorkOrder()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'Work Order')]")).click();
			}
			
			public void ClickOnExportToDownPaymentInvoice()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'Down Payment Invoice')]")).click();
			}
			
		// Export to XLS			
			public void ClickOnExportQuoteToXLS()
			{
				driver.findElement(By.xpath("(//li[contains(text(), 'Quote')])[2]")).click();
			}
		
		// Void and Unvoid			
			public void ClickOnVoidButton()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'Void')]")).click();
			}
			
			public void ClickOnUnvoidButton()
			{
				driver.findElement(By.xpath("//li[contains(text(), 'Unvoid')]")).click();
			}
			
		    
	// Add Items			
		public void AddItemsButton()
		{
			driver.findElement(By.xpath("//button[text()= 'Add Items']")).click();
		}
		
			public void AddItemSelectProduct(String Product)
			{
				driver.findElement(By.xpath("//input[contains(@class, 'css-fvgl9b')]")).click();
				SelectDropdownValue(driver, testcase, Product);
			}
			
			public void AddItemViewProductDetails()
			{
				driver.findElement(By.xpath("//button[@aria-label= 'View the Selected Product']")).click();
			}
			
			public void AddItemEditItemNameAddItem(String Name) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//input[@name= 'itemName']"));
				ClearAndEnterValue(e, Name);
			}
			
			public void AddItemEditQuantity(String Qty) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//input[@name= 'quantity']"));
				ClearAndEnterValue(e, Qty);
			}
			
			public void AddItemEditUnitDiscount(String Discount) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//input[@name= 'unitDiscount']"));
				ClearAndEnterValue(e, Discount);
			}
			
			public void AddItemEditUnitPrice(String Price) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//input[@name= 'unitPrice']"));
				ClearAndEnterValue(e, Price);
			}
			
			public void AddItemAddOrEditDescription(String Description) throws InterruptedException
			{
				driver.findElement(By.xpath("//button[text()= 'Add Description']")).click();
				WebElement e= driver.findElement(By.xpath("//div[@data-placeholder= 'Enter Description']"));
				ClearAndEnterValue(e, Description);
			}
			
			public void AddItemUploadItemImage(String Path)
			{
				driver.findElement(By.xpath("//input[@id= 'upload-image']")).sendKeys(Path);
			}
			
			public void AddItemAdditionlInfroButton()
			{
				driver.findElement(By.xpath("//button[text()= 'Additional Info']")).click();
			}
			
				public void AddItemInternalNotes(String Notes) throws InterruptedException
				{
					WebElement e= driver.findElement(By.xpath("//textarea[@name= 'internalNotes']"));
					ClearAndEnterValue(e, Notes);
				}
				
				public void AddItemDesignDetails(String Details) throws InterruptedException
				{
					WebElement e= driver.findElement(By.xpath("//textarea[@name= 'designDetails']"));
					ClearAndEnterValue(e, Details);
				}
				
				public void AddItemProductionDetails(String Details) throws InterruptedException 
				{
					WebElement e= driver.findElement(By.xpath("//textarea[@name= 'productionDetails']"));
					ClearAndEnterValue(e, Details);
				}
				
				public void AddItemInstallationDetails(String Details) throws InterruptedException 
				{
					WebElement e= driver.findElement(By.xpath("//textarea[@name= 'installationDetails']"));
					ClearAndEnterValue(e, Details);
				}
				
				public void AddItemShippingDetails(String Details) throws InterruptedException 
				{
					WebElement e= driver.findElement(By.xpath("//textarea[@name= 'shippingDetails']"));
					ClearAndEnterValue(e, Details);
				}
				
				public void AddItemShippingTracking(String Tracking) throws InterruptedException 
				{
					WebElement e= driver.findElement(By.xpath("//textarea[@name= 'shippingTracking']"));
					ClearAndEnterValue(e, Tracking);
				}
			
			public void SaveLineItemButton()
			{
				driver.findElement(By.xpath("//p[text()= 'Add New Item']/../..//button[text()= 'Save']")).click();
			}
			
			public void CloseModalbutton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'css-1fntcqw')]")).click();
			}
	
	// Import Products			
		public void ImportProducts()
		{
			driver.findElement(By.xpath("//button[text()='Import Products']")).click();
		}
		
	// Items		
		public void ItemCollapseOrExpand()
		{
			driver.findElement(By.xpath("//button[contains(@class, 'css-mfslm7')]")).click();
		}
		
		// Edit Item Details & Description		
			public void ItemEditName(String name) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("(//div[contains(@class, 'css-1axth77')]//input)[1]"));
				ClearAndEnterValue(e, name);
			}
			
			public void ItemEditQuantity(String Quantity) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("(//div[contains(@class, 'css-1axth77')]//input)[2]"));
				ClearAndEnterValue(e, Quantity);
			}
			
			public void ItemEditUnitDiscount(String Discount) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("(//div[contains(@class, 'css-1axth77')]//input)[3]"));
				ClearAndEnterValue(e, Discount);
			}
			
			public void ItemEditUnitPrice(String UnitPrice) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("(//div[contains(@class, 'css-1axth77')]//input)[4]"));
				ClearAndEnterValue(e, UnitPrice);
			}
			
			public void ItemEditTotalPrice(String TotalPrice) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("(//div[contains(@class, 'css-1axth77')]//input)[5]"));
				ClearAndEnterValue(e, TotalPrice);
			}
			
			public void ItemDescriptionEditButton()
			{
				driver.findElement(By.xpath("//div[@class= 'MuiBox-root css-1jb4d7f']//button[contains(@class, 'css-1rh62m4')]")).click();
			}
			
			public void ItemEditDescritpion(String data) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//div[@class= 'ql-editor']"));
				ClearAndEnterValue(e, data);
			}
			
			public void ExpandAllOrCollapseAllButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'css-qg4pbq')]")).click();
			}
			
			public void AddDescriptionImage(String Path)
			{
				driver.findElement(By.xpath("//input[@type= 'file']")).sendKeys(Path);
			}
			
			public void ItemSaveButton()
			{
				driver.findElement(By.xpath("//div[@class= 'MuiBox-root css-gg4vpm']//button[text()= 'Save']")).click();
			}
			
			// Price Breakdown			
				public void PriceBreakdown()
				{
					driver.findElement(By.xpath("//button[contains(@class, 'css-5n3njm')]")).click();
				}
				
				// Add Material				
					public void ItemsMaterialTab()
					{
						driver.findElement(By.xpath("//button[@role='tab' and text()= 'Material']")).click();
					}
					
					public void ItemsAddMaterialButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Material']/..//button[contains(@class, 'css-e6h54s')]")).click();
					}
					
					public void ItemSelectMaterialCategory(String Category)
					{
						driver.findElement(By.xpath("//input[@placeholder='Select product category']")).click();
					}
					
////// Pending Areas
					
				
				// Edit & Delete Material					
					public void ItemsMaterialDeleteButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Material']/../..//button[contains(@class, 'css-vacoer')]")).click();
					}
					
					public void ItemsMaterialEditButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Material']/../..//button[contains(@class, 'css-1rh62m4')]"));
					}
					
					public void ItemsMaterialEditQuantitiy(String Quantity) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Material']/../..//div[contains(@class, 'css-iz33ar')]//input)[1]"));
						ClearAndEnterValue(e, Quantity);
					}
					
					public void ItemsMaterialEditUnitCost(String Cost) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Material']/../..//div[contains(@class, 'css-iz33ar')]//input)[2]"));
						ClearAndEnterValue(e, Cost);
					}
					
					public void ItemsMaterialEditUnitPrice(String Price) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Material']/../..//div[contains(@class, 'css-iz33ar')]//input)[3]"));
						ClearAndEnterValue(e, Price);
					}
					
					public void ItemsMaterialEditLIQuantity(String LIQuantity) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Material']/../..//div[contains(@class, 'css-iz33ar')]//input)[4]"));
						ClearAndEnterValue(e, LIQuantity);
					}
					
					public void ItemsMaterialSaveEdits()
					{
						driver.findElement(By.xpath("//div[text()= 'Material']/../..//button[contains(@class, 'css-9289wc')]")).click();
					}
					
					public void ItemsMaterialCancelEdits()
					{
						driver.findElement(By.xpath("//div[text()= 'Material']/../..//button[contains(@class, 'css-ttvz9k')]")).click();
					}					
				
				// Add Machinery					
					public void ItemsMachineryTab()
					{
						driver.findElement(By.xpath("//button[@role='tab' and text()= 'Machinery']")).click();
					}
					
					public void ItemAddMachineryButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Machinery']/..//button[contains(@class, 'css-e6h54s')]")).click();
					}
					
					public void ItemSelectMachineryCategory(String Category)
					{
						driver.findElement(By.xpath("//input[@placeholder='Select product category']")).click();
					}
								
////// Pending Areas
				
				// Edit & Delete Machinery					
					public void ItemsMachineryDeleteButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Machinery']/../..//button[contains(@class, 'css-vacoer')]")).click();
					}
				
					public void ItemsMachineryEditButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Machinery']/../..//button[contains(@class, 'css-1rh62m4')]"));
					}
				
					public void ItemsMachineryEditQuantitiy(String Quantity) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Machinery']/../..//div[contains(@class, 'css-iz33ar')]//input)[1]"));
						ClearAndEnterValue(e, Quantity);
					}
					
					public void ItemsMachineryEditUnitCost(String Cost) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Machinery']/../..//div[contains(@class, 'css-iz33ar')]//input)[2]"));
						ClearAndEnterValue(e, Cost);
					}
					
					public void ItemsMachineryEditUnitPrice(String Price) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Machinery']/../..//div[contains(@class, 'css-iz33ar')]//input)[3]"));
						ClearAndEnterValue(e, Price);
					}
					
					public void ItemsMachineryEditLIQuantity(String LIQuantity) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Machinery']/../..//div[contains(@class, 'css-iz33ar')]//input)[4]"));
						ClearAndEnterValue(e, LIQuantity);
					}
					
					public void ItemsMachinerySaveEdits()
					{
						driver.findElement(By.xpath("//div[text()= 'Machinery']/../..//button[contains(@class, 'css-9289wc')]")).click();
					}
					
					public void ItemsMachineryCancelEdits()
					{
						driver.findElement(By.xpath("//div[text()= 'Machinery']/../..//button[contains(@class, 'css-ttvz9k')]")).click();
					}
				

				// Add Labor				
					public void ItemsLabourTab()
					{
						driver.findElement(By.xpath("//button[@role='tab' and text()= 'Labour']")).click();
					}
					
					public void ItemAddLabourButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Labour']/..//button[contains(@class, 'css-e6h54s')]")).click();
					}
					
					public void ItemSelectLabourCategory(String Category)
					{
						driver.findElement(By.xpath("//input[@placeholder='Select product category']")).click();
					}	
			
//////Pending Areas
				
				// Edit & Delete Labor				
					public void ItemsLabourDeleteButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Labour']/../..//button[contains(@class, 'css-vacoer')]")).click();
					}
					
					public void ItemsLabourEditButton()
					{
						driver.findElement(By.xpath("//div[text()= 'Labour']/../..//button[contains(@class, 'css-1rh62m4')]"));
					}
				
					public void ItemsLabourEditQuantitiy(String Quantity) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Labour']/../..//div[contains(@class, 'css-iz33ar')]//input)[1]"));
						ClearAndEnterValue(e, Quantity);
					}
					
					public void ItemsLabourEditUnitCost(String Cost) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Labour']/../..//div[contains(@class, 'css-iz33ar')]//input)[2]"));
						ClearAndEnterValue(e, Cost);
					}
					
					public void ItemsLabourEditUnitPrice(String Price) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Labour']/../..//div[contains(@class, 'css-iz33ar')]//input)[3]"));
						ClearAndEnterValue(e, Price);
					}
					
					public void ItemsLabourEditLIQuantity(String LIQuantity) throws InterruptedException
					{
						WebElement e= driver.findElement(By.xpath("(//div[text()= 'Labour']/../..//div[contains(@class, 'css-iz33ar')]//input)[4]"));
						ClearAndEnterValue(e, LIQuantity);
					}
					
					public void ItemsLabourSaveEdits()
					{
						driver.findElement(By.xpath("//div[text()= 'Labour']/../..//button[contains(@class, 'css-9289wc')]")).click();
					}
					
					public void ItemsLabourCancelEdits()
					{
						driver.findElement(By.xpath("//div[text()= 'Labour']/../..//button[contains(@class, 'css-ttvz9k')]")).click();
					}
			
			
	// Item Menu
		public void ClickItemMoreOptionsButton()
		{
			driver.findElement(By.xpath("//button[contains(@class, 'css-1yj6qc0')]")).click();
		}

//////Pending Areas

		// Create Job
		
		// Copy Line Item
			public void CopyLineItem()
			{
				driver.findElement(By.xpath("//li[text()= 'Copy Line Item']")).click();;
			}
			
			public void CopyLineItemName(String Name) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//input[@name= 'name']"));
				ClearAndEnterValue(e, Name);
			}
			
			public void SelectCopyTo(String CopyTo)
			{
				driver.findElement(By.xpath("//input[@name= 'copyTo']/..//input")).click();
				SelectDropdownValue(driver, testcase, CopyTo);
			}
			
			public void CopyLineItemAssetsCheckbox()
			{
				driver.findElement(By.xpath("//input[@id= 'copyAssets']")).click();
			}
			
			public void CopyLineItemButton()
			{
				driver.findElement(By.xpath("//button[text()= 'Copy Line Item']")).click();
			}
		
//////Pending Areas	
	
		// Delete Line Item
		
		// Role Up Into line 2
		
	// Notes Tab
		public void NotesTab()
		{
			driver.findElement(By.xpath("//button[text()= 'Notes']")).click();
		}
		
		// Add Notes
			public void AddNewNotesButton()
			{
				driver.findElement(By.xpath("//button[text()= 'Add New Note']")).click();
			}
			
			public void EnterNotesText(String Notes) throws InterruptedException
			{
				WebElement e= driver.findElement(By.xpath("//div[@class= 'ql-editor ql-blank']"));
				ClearAndEnterValue(e, Notes);
			}
			
			public void SelectNotesType(String Type)
			{
				driver.findElement(By.xpath("")).click();
				SelectDropdownValue(driver, testcase, Type);
			}
		
	// History Tab
		public void HistoryTab()
		{
			
		}
}









