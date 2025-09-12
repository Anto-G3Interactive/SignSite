package accountmanagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInAccountManagement extends Initialstep
{
	public ElementsInAccountManagement(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
// Open Account Management Page
	public void SettingsMenuDropdown()
	{
		driver.findElement(By.xpath("//span[text()= 'Settings']/..")).click();
	}
	
	public void AccountMangementMenuButton()
	{
		try
		{
			driver.findElement(By.xpath("//div[@to= 'account']/..")).click();
		}
		catch(Exception e)
		{
			SettingsMenuDropdown();
			driver.findElement(By.xpath("//div[@to= 'account']/..")).click();
		}
	}
	
// Account Management Page
	
	public String ConfirmationMessage()
	{
		return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
	}
	
	public void UploadImage(String Path)
	{
		driver.findElement(By.xpath("//input[@id= 'imageUpload']")).sendKeys(Path);
	}
	
	public void DeleteImage()
	{
		driver.findElement(By.xpath("(//div[@class= 'avatar-upload']//label)[1]")).click();
	}
	
	
	// General Informations
	
		public void EnterBusinessName(String Name) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'company_name']"));
			ClearAndEnterValue(e, Name);
		}
		
		public void EnterBusinessWebsite(String Website) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'website']"));
			ClearAndEnterValue(e, Website);
		}
		
		public void EnterAustralianBusinessNumber(String Number) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'abn']"));
			ClearAndEnterValue(e, Number);
		}
		
		public void EnterLabel(String Label) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'label']"));
			ClearAndEnterValue(e, Label);
		}
		
		public void SelectCountry(String Country)
		{
			driver.findElement(By.xpath("//label[@for= 'country']/..//div")).click();
			SelectDropdownValue(driver, testcase, Country);
		}
		
		public void SelectState(String State)
		{
			driver.findElement(By.xpath("//label[@for= 'state']/..//div")).click();
			SelectDropdownValue(driver, testcase, State);
		}
		
		public void EnterCity(String City) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'city']"));
			ClearAndEnterValue(e, City);
		}
		
		public void EnterStreet1(String Street1) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'street1']"));
			ClearAndEnterValue(e, Street1);
		}
		public void EnterStreet2(String Street2) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'street2']"));
			ClearAndEnterValue(e, Street2);
		}
		
		public void EnterSuburb(String Suburb) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'suburb']"));
			ClearAndEnterValue(e, Suburb);
		}
		
		public void EnterPostcode(String Postcode) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'postcode']"));
			ClearAndEnterValue(e, Postcode);
		}
	
		
	// Contact Details
		
		public void EnterBusinessPhoneNumber(String PhoneNumber) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'company_phone']"));
			ClearAndEnterValue(e, PhoneNumber);
		}
		
		public void EnterBusinessEmailAddress(String Email) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'company_email']"));
			ClearAndEnterValue(e, Email);
		}
		
		
	//  Regional Preferences
		
		public void SelectTimezone(String Timezone) throws InterruptedException
		{
			driver.findElement(By.xpath("//label[@for= 'timezone']/..//div")).click();;
			SelectDropdownValue(driver, testcase, Timezone);
		}
		
		public void SelectCurrency(String Currency)
		{
			driver.findElement(By.xpath("//label[@for= 'currency']/..//div")).click();
			SelectDropdownValue(driver, testcase, Currency);
		}
		
		public void EnterCurrencySymbol(String CurrencySymbol) throws InterruptedException
		{
			WebElement e= driver.findElement(By.xpath("//input[@id= 'currency_symbol']"));
			ClearAndEnterValue(e, CurrencySymbol);
		}
		
		public void SelectCurrencyPosition(String PrefixOrSuffix)
		{
			driver.findElement(By.xpath("//div[@id= 'currency_position']")).click();
			SelectDropdownValue(driver, testcase, PrefixOrSuffix);
		}
		
		public void SelectDecimalSeparator(String FullStopOrComma)
		{
			driver.findElement(By.xpath("//div[@id= 'decimal_sep']")).click();
			SelectDropdownValue(driver, testcase, FullStopOrComma);
		}
		
		public void SelectGroupingSeparator(String CommaOrSpace)
		{
			driver.findElement(By.xpath("//div[@id= 'grouping_sep']")).click();
			SelectDropdownValue(driver, testcase, CommaOrSpace);
		}
		
	public void SaveButton()
	{
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
	}
}










