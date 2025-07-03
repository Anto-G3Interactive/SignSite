package customers;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DeleteCustomer extends Initialstep
{
	
	String BusinessName= "Customer To Be Deleted"; // Customer to Delete
	
	ElementsInCustomerPage EIC;
	@Test
	public void addandDeleteCustomer() throws InterruptedException, IOException
	{
		EIC= new ElementsInCustomerPage(driver, testcase);
		testcase= extentReport.createTest("Verify the Delete Funtionality of Customer");
		testcase.log(INFO, "Adding a new customer and deleteing it");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIC.CustomerMenuButton();
		Thread.sleep(1500);
		EIC.AddNewCustomerButton();
		AddCustomerToDelete(); // Comment this line and test case log if the customer is already exist
		Thread.sleep(1500);
		DeleteNewCustomer();
		
	}
	
// Add New Customer to Delete
	public void AddCustomerToDelete() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Adding a new Customer");
		
		String TradingName= "New Trade";
		String ContactType= "Primary";
		String ContactName= "Automation Testing";
		String ContactEmail= generateDummyName() + "@gamil.com";
		String ContactPhone= "9876543210";
		String AddressType= "Primary";
		String SearchOnline= "123";
		
		EIC.EnterBusinessName(BusinessName);
		EIC.EnterTradingName(TradingName);
		EIC.SelectContactType(ContactType);
		EIC.EnterContactName(ContactName);
		EIC.EnterContactEmail(ContactEmail);
		EIC.EnterContactPhone(ContactPhone);
		EIC.SelectAddressType(AddressType);
		EIC.EnterSearchOnlineAndChooseAddress(SearchOnline);
		
		Thread.sleep(1500);
		EIC.ClickOnSaveButton("1");
		
		if (EIC.ConfirmationAlert().toLowerCase().contains("successfully"))
		{
			testcase.log(PASS, "A New customer is added successfully and the '" + EIC.ConfirmationAlert() + "' message is displayed");
			Thread.sleep(1000);
			takescreenshot(driver, "New Customer");
		}
		else
		{
			testcase.log(FAIL, "Not able to add a New Customer. The '" + EIC.ConfirmationAlert() + "' message is displayed");
			takescreenshot(driver, "Not able to add a New Customer");
		}
		Thread.sleep(1500);
		EIC.ClickOnCancelButton("1");
	}
	
	public void DeleteNewCustomer() throws InterruptedException, IOException
	{
		EIC.SearchField(BusinessName);
		Thread.sleep(2000);
		String SucessMessage= EIC.DeleteActionWithSuccessMessage();
		Thread.sleep(1500);
		if(SucessMessage.toLowerCase().contains("success"))
		{
			testcase.log(PASS, "The Customer deleted and the '" + SucessMessage + "' message is displayed");
			takescreenshot(driver, "Customer Deleted");
		}
		else
		{
			testcase.log(FAIL, "Customer did not deleted");
			takescreenshot(driver, "Not able to delete a Customer");
		}
		Thread.sleep(1000);
		EIC.OKButtonAfterDelete();;
	}
}