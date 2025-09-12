package customers;

import java.io.IOException;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditCustomer extends Initialstep
{
	String SearchContent= "Automation"; // Enter a valid Business Name
	String TradingName= "Edit Test" + generateDummyName();
	String EditContactName= "Edit Test" + generateDummyName();
	String EditContactPhone= "98765432100";
	String SelectContactType= "Secondary";
	String AddContactName= "Anist";
	String AddContactEmail= generateDummyName() + "@gamil.com";
	String AddContactPhone= "654987321012";
	String EditStreetAddress= "29-141, Puthuparakkal Vilai";
	String EditUnitNumber= "555";
	String EditPostCode= "629152";
	String AddAddressType= "Ship";
	String SearchOnline= "456";
	String AddUnitNumber= "123";
	String EditABN= "456";
	
	ElementsInCustomerPage EIC;
	@Test
	public void editcustomer() throws InterruptedException, IOException
	{
		EIC= new ElementsInCustomerPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify Edit functionality of the Customer");
		testcase.log(INFO, "Navigating to the Customers page");
		
		try
		{
			EIC.CustomerMenuButton();
			EIC.SearchField(SearchContent);
			Thread.sleep(1500);
			EIC.EditButton(1);
			Thread.sleep(1500);
			EditCustomerDetails();
			Thread.sleep(1500);
			SaveChanges();			
		}
		
		catch(Exception e)
		{
			testcase.log(FAIL, "Failed To Run Script");
			takescreenshot(driver, "Failed");
		}
	}
	
	public void EditCustomerDetails() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Update Customer Details");
		
		EIC.EnterTradingName(TradingName);
		EIC.EditPrimaryContactButton();
		EIC.EnterContactName(EditContactName);
		EIC.EnterContactPhone(EditContactPhone);
		EIC.ClickOnPopUpSaveButton();
		Thread.sleep(1000);
			if(EIC.ConfirmationMessage().toLowerCase().contains("successfully"))
			{
				testcase.log(PASS, "Contact edited and the '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(1000);
				takescreenshot(driver, "Edited The Contact");
			}
			else
			{
				testcase.log(FAIL, "Failed to update the contact details. The '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(500);
				takescreenshot(driver, "Failed to update the contact");
			}
		EIC.AddNewContactButton();
		EIC.SelectContactType(SelectContactType);
		EIC.EnterContactName(AddContactName);
		EIC.EnterContactEmail(AddContactEmail);
		EIC.EnterContactPhone(AddContactPhone);
		EIC.ClickOnPopUpSaveButton();
		Thread.sleep(1000);
			if(EIC.ConfirmationMessage().toLowerCase().contains("successfully"))
			{
				testcase.log(PASS, "Contact added and the '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(500);
				takescreenshot(driver, "Added the Contact");
			}
			else
			{
				testcase.log(FAIL, "Failed to add the contact details. The '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(500);
				takescreenshot(driver, "Failed to add the contact");
			}
		EIC.EditAddressButton();
		EIC.EnterStreetAddress(EditStreetAddress);
		EIC.EnterUnitNumber(EditUnitNumber);
		EIC.EnterPostcode(EditPostCode);
		EIC.ClickOnPopUpSaveButton();
		Thread.sleep(1000);
			if(EIC.ConfirmationMessage().toLowerCase().contains("successfully"))
			{
				testcase.log(PASS, "Address edited and the '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(500);
				takescreenshot(driver, "Edited The Address");
			}
			else
			{
				testcase.log(FAIL, "Failed to update the Address details. The '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(500);
				takescreenshot(driver, "Failed to update the Address");
			}
		EIC.AddNewAddressButton();
		EIC.SelectAddressType(AddAddressType);
		EIC.EnterSearchOnlineAndChooseAddress(SearchOnline);
		EIC.EnterUnitNumber(AddUnitNumber);
		EIC.ClickOnPopUpSaveButton();
		Thread.sleep(1000);
			if(EIC.ConfirmationMessage().toLowerCase().contains("successfully"))
			{
				testcase.log(PASS, "Address added and the '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(500);
				takescreenshot(driver, "Added a new Address");
			}
			else
			{
				testcase.log(FAIL, "Failed to add the address details. The '" + EIC.ConfirmationMessage() + "' message is displayed");
				Thread.sleep(500);
				takescreenshot(driver, "Failed to add a address");
			}
		EIC.EnterABN(EditABN);
	}
	
	public void SaveChanges() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Save the updated Customer");
		
		EIC.SaveButton();
		Thread.sleep(1000);
		if (EIC.ConfirmationMessage().toLowerCase().contains("successfully"))
		{
			testcase.log(PASS, "A customer is edited successfully and the '" + EIC.ConfirmationMessage() + "' message is displayed");
			Thread.sleep(1000);
			takescreenshot(driver, "Edited Customer");
		}
		else
		{
			testcase.log(FAIL, "Not able to Edit a New Customer. The '" + EIC.ConfirmationMessage() + "' message is displayed");
			Thread.sleep(1000);
			takescreenshot(driver, "Not able to Edit a New Customer");
		}
	}
}
