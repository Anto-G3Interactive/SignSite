package customers;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddNewCustomer extends Initialstep
{
	String BusinessName= "Automation" + generateDummyName();
	String TradingName= "New Trade";
	String DoNotSupply= "Enable";	// Enter Enable or Disable
	String DoNotCall= "Disable";	// Enter Enable or Disable
	String ContactType= "Primary";
	String ContactName= "Automation Testing";
	String ContactEmail= generateDummyName() + "@gamil.com";
	String ContactPhone= "9876543210";
	String AddressType= "Primary";
	String StreetAddress= "39-161, Malaicode";
	String UnitNumber= "456";
	String Suburb= "Kaniyakumari";
	String Country= "India";
	String State= "Tamil Nadu";
	String PostCode= "629150";
	String Terms= "Net 30";
	String Industry= "Technology";
	String Tax= "VAT";
	String SalesRep= "Anto";
	String ABN= "123";
	String Notes= "New Note added for new customer";
	String SpecialNote= "Special note is added for new customer";
	
	ElementsInCustomerPage EIC;
	
	@Test
	public void addnewcustomer() throws InterruptedException, IOException
	{
		EIC= new ElementsInCustomerPage(driver, testcase);
		testcase= extentReport.createTest("Verify the Add Customer functionality");
		testcase.log(INFO, "Navigating to the customer Page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		OpenAddCustomerPage();
		Thread.sleep(1000);
		AddCustomerDetails();
		Thread.sleep(1000);
		SaveCustomer();	
	}
	
	public void OpenAddCustomerPage() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Click on Add Customer Button");
		EIC.CustomerMenuButton();
		Thread.sleep(1000);
		EIC.AddNewCustomerButton();
		if(EIC.CustomersPage().isDisplayed())
		{
			testcase.log(PASS, "Customers Page is dispalyed");
			takescreenshot(driver, "Customers Page");
		}
		else
		{
			testcase.log(FAIL, "Customers Page did not dispalyed");
			takescreenshot(driver, "Customers Page did not displayed");
		}
	}
	
	public void AddCustomerDetails() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Adding the customer Detail");
		
		EIC.EnterBusinessName(BusinessName);
		EIC.EnterTradingName(TradingName);
		EIC.DoNotSupplyToggleButton(DoNotSupply);
		EIC.DoNotCallToggleButton(DoNotCall);
		EIC.SelectContactType(ContactType);
		EIC.EnterContactName(ContactName);
		EIC.EnterContactEmail(ContactEmail);
		EIC.EnterContactPhone(ContactPhone);
		EIC.ClickOnLoginCheckbox("Enable");
		EIC.SelectAddressType(AddressType);
		EIC.EnterStreetAddress(StreetAddress);
		EIC.EnterUnitNumber(UnitNumber);
		EIC.EnterSuburb(Suburb);
		EIC.SelectCountry(Country);
		EIC.SelectState(State);
		EIC.EnterPostcode(PostCode);
		EIC.SelectTerms(Terms);
		EIC.SelectIndustry(Industry);
		EIC.SelectTax(Tax);
		EIC.SelectSalesRep(SalesRep);
		EIC.EnterABN(ABN);
		EIC.EnterNotes(Notes);
		EIC.EnterSpecialNotes(SpecialNote);
		
		testcase.log(INFO, "Customer Details added");
		takescreenshot(driver, "Customer Details added");
	}
	
	public void SaveCustomer() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Save the customer Detail");
		EIC.SaveButton();
		Thread.sleep(1000);
		
		if (EIC.ConfirmationMessage().toLowerCase().contains("successfully"))
		{
			testcase.log(PASS, "A New customer is added successfully and the '" + EIC.ConfirmationMessage() + "' message is displayed");
			takescreenshot(driver, "New Customer");
		}
		else
		{
			testcase.log(FAIL, "Not able to add a New Customer. The '" + EIC.ConfirmationMessage() + "' message is displayed");
			takescreenshot(driver, "Not able to add a New Customer");
		}
	}
}
