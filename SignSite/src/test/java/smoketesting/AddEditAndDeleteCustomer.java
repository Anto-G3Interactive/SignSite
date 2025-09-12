package smoketesting;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Test;

import basepack.Initialstep;
import customers.ElementsInCustomerPage;

public class AddEditAndDeleteCustomer extends Initialstep
{
	ElementsInCustomerPage EIC;
	
	String Unique= generateDummyName();
	String BusinessName= "Automation Testing ";
	String TradingName= "Automation Trading Name ";
	String ContactType= "Primary";
	String ContactName= "Automation Contact ";
	String ContactEmail= "AutomationEmail"+ Unique +"@gmail.com";
	String ContactPhone= "1234567890";
	String AddressType= "Primary";
	String SearchOnline= "Primary";
	String Terms= "Term 1";
	String Industry= "Technology";
	String Tax= "VAT";
	String SaleRep= "Automation User";
	String ABN= "354";
	String Notes= "Automation Testing Notes ";
	String SpecialNotes= "Automation Special Notes ";
	String EditUnique= generateDummyName();
	String ToBeDeleted= "To Be Deleted";
	
	
// Add Customer	
	
	@Test (priority= 1)
	public void AddCustomer() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To verify the add customer functionality");
		EIC= new ElementsInCustomerPage(driver, testcase);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		try
		{
			EIC.CustomerMenuButton();
			EIC.AddNewCustomerButton();
			Thread.sleep(1000);
			EIC.EnterBusinessName(BusinessName+Unique);
			EIC.EnterTradingName(TradingName+Unique);		
			EIC.DoNotSupplyToggleButton("Enable");
			EIC.DoNotCallToggleButton("Enable");
			EIC.SelectContactType(ContactType);
			EIC.EnterContactName(ContactName+Unique);
			EIC.EnterContactEmail(ContactEmail);
			EIC.EnterContactPhone(ContactPhone);
			EIC.ClickOnLoginCheckbox("Enable");
			EIC.SelectAddressType(AddressType);
			EIC.EnterSearchOnlineAndChooseAddress(SearchOnline);
			EIC.SelectTerms(Terms);
			EIC.SelectIndustry(Industry);
			EIC.SelectTax(Tax);
			EIC.SelectSalesRep(SaleRep);
			EIC.EnterNotes(Notes+Unique);
			EIC.EnterSpecialNotes(SpecialNotes+Unique);
			Thread.sleep(1000);
			EIC.SaveButton();
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}
		
		if(EIC.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Customer created and the '"+ EIC.ConfirmationMessage() +"' message is dispalyed");
			takescreenshot(driver, "Customer Created");
		}
		else
		{
			testcase.log(FAIL, "Customer did not created. The '"+ EIC.ConfirmationMessage() +"' is displayed");
			takescreenshot(driver, "Customer did not created");
		}
	}
	
	
// Edit Customer	
	
	@Test (dependsOnMethods= "AddCustomer")
	public void EditCustomer() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To verify the edit customer functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		try
		{
			EIC.CustomerMenuButton();
			EIC.SearchField(BusinessName);
			Thread.sleep(1500);
			EIC.EditButton(1);
			EIC.EnterBusinessName(ToBeDeleted + EditUnique);
			EIC.EnterTradingName(ToBeDeleted + EditUnique);
			
			EIC.AddNewContactButton();
			EIC.SelectContactType("Secondary");
			EIC.EnterContactName("To Be Deleted"+EditUnique);
			EIC.EnterContactEmail("ToBeDeleted"+ EditUnique +"@gmail.com");
			EIC.ClickOnPopUpSaveButton();
			Thread.sleep(1000);
			
			EIC.AddNewAddressButton();
			EIC.SelectAddressType("Install");
			EIC.EnterSearchOnlineAndChooseAddress("Secondary");
			Thread.sleep(1000);
			EIC.ClickOnPopUpSaveButton();
			Thread.sleep(1000);
			
			EIC.AddNewAddressButton();
			EIC.SelectAddressType("Billing");
			EIC.EnterStreetAddress("Billing Street");
			EIC.EnterUnitNumber("125");
			EIC.EnterSuburb("Brisbane");
			EIC.SelectCountry("Australia");
			EIC.SelectState("Queensland");
			EIC.EnterPostcode("6285");
			Thread.sleep(1000);
			EIC.ClickOnPopUpSaveButton();
			Thread.sleep(1000);
			
			EIC.SelectTerms("Term 2");
			EIC.SelectIndustry("Finance");
			EIC.SelectTax("Sales Tax");
			EIC.SelectSalesRep("To Be Updated");
			EIC.EnterABN("629");
			EIC.EnterNotes("Smoke Testing "+ EditUnique);
			EIC.EnterSpecialNotes("Smoke Testing "+ EditUnique);
			Thread.sleep(1000);
			EIC.SaveButton();
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}
		
		if(EIC.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Customer updated and the '"+ EIC.ConfirmationMessage() +"' message is dispalyed");
			takescreenshot(driver, "Customer updated");
		}
		else
		{
			testcase.log(FAIL, "Customer did not updated. The'"+ EIC.ConfirmationMessage() +"' is displayed");
			takescreenshot(driver, "Customer did not updated");
		}
	}
	
	
// Delete Customer
	
	@Test (dependsOnMethods= "EditCustomer")
	public void DeleteCustomer() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To verify the delete customer functionality"); EIC= new ElementsInCustomerPage(driver, testcase);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIC.CustomerMenuButton();
		EIC.SearchField(ToBeDeleted);
		Thread.sleep(1200);
		String s= EIC.DeleteActionWithReturnOfSuccessMessage();
		if(s.toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Customer deleted and the '"+ s +"' message is dispalyed");
			takescreenshot(driver, "Customer Deleted");
		}
		else
		{
			testcase.log(FAIL, "Customer did not deleted. The'"+ s +"' is displayed");
			takescreenshot(driver, "Customer did not deleted");
		}
	}
}





