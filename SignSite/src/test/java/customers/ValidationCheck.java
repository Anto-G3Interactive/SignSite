package customers;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import basepack.Initialstep;

public class ValidationCheck extends Initialstep
{
	ElementsInCustomerPage EIC;
	
// To check the Mandatory fields.
	@Test (priority= 1)
	public void mandatorycheck() throws InterruptedException, IOException
	{
		EIC= new ElementsInCustomerPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify the validation on Mandatory fields");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		EIC.CustomerMenuButton();
		EIC.AddNewCustomerButton();
		EIC.ClickOnSaveButton("1");
		Thread.sleep(1500);
		
		if(driver.findElement(By.xpath("//p[text()= 'Business name is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Contact type is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Name is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Email is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Address type is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Street address is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Suburb is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Country is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'State is required']")).isDisplayed()&&
		driver.findElement(By.xpath("//p[text()= 'Postcode is required']")).isDisplayed())
		{
			testcase.log(PASS, "Validations for all the fields were displayed");
			takescreenshot(driver, "Validations displayed");
		}
		else
		{
			testcase.log(FAIL, "Validations for all the fields were not displayed");
			takescreenshot(driver, "Validations are not displayed");
		}
		
	}
	
// To check by adding invalid date into fields.
	@Test (priority= 2)
	public void InvalidData() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the validations of fields by passing invalid data");
		testcase.log(Status.INFO, "Validating Email, Contact Phone field with invaid Data");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
		String BusinessName= "WedMar26132515IST2025";
		String ContactType= "Primary";
		String ContactName= "Automation Testing";
		String ContactEmail= "abcd"; // Invalid Email
		String ContactPhone= "+abcd"; // Invalid Phone Number
		String AddressType= "Primary";
		String SearchOnline= "131";
		
		EIC.CustomerMenuButton();
		EIC.AddNewCustomerButton();
		Thread.sleep(1000);
		EIC.EnterBusinessName(BusinessName);
		EIC.SelectContactType(ContactType);
		EIC.EnterContactName(ContactName);
		EIC.EnterContactEmail(ContactEmail);
		EIC.EnterContactPhone(ContactPhone);
		EIC.SelectAddressType(AddressType);
		EIC.EnterSearchOnlineAndChooseAddress(SearchOnline);
		EIC.ClickOnSaveButton("1");
		Thread.sleep(1500);
		
		if ( driver.findElement(By.xpath("//p[text()= 'Invalid email format']")).isDisplayed() &&
			driver.findElement(By.xpath("//p[text()= 'Only numbers are allowed']")).isDisplayed())
		{
			testcase.log(PASS, "The validation messages were dispalyed for Email and Contact Phone field for invalid data");
			takescreenshot(driver, "Not accepting Invaild Email and Contact Phone");
		}
		else
		{
			testcase.log(FAIL, "The Email or/and Contact Phone field accepting invalid data");
			takescreenshot(driver, "Accepting Invalid Data");
		}
}

//To check by adding data which are not in acceptable range of fields
	@Test (priority= 3)
	public void NotInRange() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify validation by passing the value that are not in range of fields");
		testcase.log(Status.INFO, "Validating Contact Phone, StreetAddress and Suburb fields");
	
		String BusinessName= "WedMar26132515IST2025";
		String ContactType= "Primary";
		String ContactName= "New Contact";
		String ContactEmail= "anto@gmail.com";
		String ContactPhone= "1234"; // Phone number not between 10 and 15 digit
		String AddressType= "Primary";
		String StreetAddress= "131a"; // Street Address less than 5 Characters
		String Suburb= "1"; // Suburb less than 2 Characters
		String Country= "India";
		String State= "Tamil Nadu";
		String PostCode= "629150";
		
		EIC.CustomerMenuButton();
		EIC.AddNewCustomerButton();
		Thread.sleep(1000);
		EIC.EnterBusinessName(BusinessName);
		EIC.SelectContactType(ContactType);
		EIC.EnterContactName(ContactName);
		EIC.EnterContactEmail(ContactEmail);
		EIC.EnterContactPhone(ContactPhone);
		EIC.SelectAddressType(AddressType);
		EIC.EnterStreetAddress(StreetAddress);
		EIC.EnterSuburb(Suburb);
		EIC.SelectCountry(Country);
		EIC.SelectState(State);
		EIC.EnterPostcode(PostCode);
		EIC.ClickOnSaveButton("1");
		Thread.sleep(1500);
		
		
		if ( driver.findElement(By.xpath("//p[text()= 'Phone number must be between 10 and 15 digits']")).isDisplayed() &&
			driver.findElement(By.xpath("//p[text()= 'Must be at least 5 characters']")).isDisplayed() &&
			driver.findElement(By.xpath("//p[text()= 'Must be at least 2 characters']")).isDisplayed())
		{
			testcase.log(PASS, "The Contact Phone, Street Address and Suburb fields are not accepting the value not in its Range");
			takescreenshot(driver, "Not accepting invaild range");
		}
		else
		{
			testcase.log(FAIL, "The fields accepting invalid range of data");
			takescreenshot(driver, "Accepting Invalid range");
		}
			
	}
	
	@Test (priority= 4)
	public void DuplicateBusinessName() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify Buisness name Field");
		testcase.log(Status.INFO, "Validating Business Name field by passing duplicate Name");
	
		EIC.CustomerMenuButton();
		String BusinessName= EIC.FirstMainColumnValueToSearch(); // Duplicate Name
		String ContactType= "Primary";
		String ContactName= "New Contact";
		String ContactEmail= "anto@gmail.com";
		String ContactPhone= "9123456789";
		String AddressType= "Primary";
		String SearchOnline= "140";
		
		EIC.AddNewCustomerButton();
		Thread.sleep(1000);
		EIC.EnterBusinessName(BusinessName);
		EIC.SelectContactType(ContactType);
		EIC.EnterContactName(ContactName);
		EIC.EnterContactEmail(ContactEmail);
		EIC.EnterContactPhone(ContactPhone);
		EIC.SelectAddressType(AddressType);
		EIC.EnterSearchOnlineAndChooseAddress(SearchOnline);
		EIC.ClickOnSaveButton("1");
		Thread.sleep(1500);
				
		if (EIC.ConfirmationAlert().toLowerCase().contains("already been taken"))
		{
			testcase.log(PASS, "The Business Name not accepting duplictes and the '" + EIC.ConfirmationAlert() + "' message is displayed");
			takescreenshot(driver, "Not accepting duplicate business name");
		}
		else
		{
			testcase.log(FAIL, "The Business Name accepts duplicate name");
			takescreenshot(driver, "Accepting Duplicate Name");
		}
			
	}
}	