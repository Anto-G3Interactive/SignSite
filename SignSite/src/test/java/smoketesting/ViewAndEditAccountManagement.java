package smoketesting;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import accountmanagement.ElementsInAccountManagement;
import basepack.Initialstep;

public class ViewAndEditAccountManagement extends Initialstep
{
	ElementsInAccountManagement EIAM;
	
	String ImagePath= "D:\\Images\\Robot.gif";
	String Unique= generateDummyName();
	String BusinessName= "Automation Business "+ Unique;
	String BusinessWebsite= "www."+ Unique + ".com";
	String ABN= "2563";
	String Label= "Automation Lable"+ Unique;
	String Country= "Australia";
	String State= "Queensland";
	String City= "Brisbane";
	String Street1= "Street1 Updated on "+ Unique;
	String Street2= "Street2 Updated on "+ Unique;
	String Suburb= "Brisbane";
	String Postcode= "4563";
	String BusinessPhoneNumber= "1234567890";
	String Timezone= "Australia/Brisbane";
	String Currency= "Australian Dollar (AUD)";
	String CurrencySymbol= "$";
	String CurrencyPosition= "Prefix";
	String DecimalSeparator= ".";
	String GroupingSeparator= ",";
	
	@Test(priority= 1)
	public void ViewAndEditAccountManagementDetails() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the View And Edit Functionality of Account Mangement");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIAM= new ElementsInAccountManagement(driver, testcase);
		
		EIAM.SettingsMenuDropdown();
		EIAM.AccountMangementMenuButton();
		EIAM.DeleteImage();
		Thread.sleep(1000);
		EIAM.UploadImage(ImagePath);
		EIAM.EnterBusinessName(BusinessName);
		EIAM.EnterBusinessWebsite(BusinessWebsite);
		EIAM.EnterAustralianBusinessNumber(ABN);
		EIAM.EnterLabel(Label);
		EIAM.SelectCountry(Country);
		EIAM.SelectState(State);
		EIAM.EnterCity(City);
		EIAM.EnterStreet1(Street1);
		EIAM.EnterStreet2(Street2);
		EIAM.EnterSuburb(Suburb);
		EIAM.EnterPostcode(Postcode);
		EIAM.EnterBusinessPhoneNumber(BusinessPhoneNumber);
		EIAM.SelectTimezone(Timezone);
		EIAM.SelectCurrency(Currency);
		EIAM.EnterCurrencySymbol(CurrencySymbol);
		EIAM.SelectCurrencyPosition(CurrencyPosition);
		EIAM.SelectDecimalSeparator(DecimalSeparator);
		EIAM.SelectGroupingSeparator(GroupingSeparator);
		Thread.sleep(1000);
		EIAM.SaveButton();
		if(EIAM.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Account Details updated and the '"+ EIAM.ConfirmationMessage() +"' message is displayed.");
			takescreenshot(driver, "Account Updated");
		}
		else
		{
			testcase.log(FAIL, "Failed to Update Account Details. The '"+ EIAM.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Failed to update Account Details");
		}
	}
}









