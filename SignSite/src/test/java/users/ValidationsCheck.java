package users;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class ValidationsCheck extends Initialstep
{
	ElementsInUsersPage EIU;
	@Test
	public void addNewUser() throws IOException, InterruptedException
	{
		EIU= new ElementsInUsersPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify the validations of fields");
		testcase.log(INFO, "Navigating to the Add User page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		String FirstUser= OpenAddUsersPage();
		MandatoryCheck();
		Thread.sleep(1500);
		DuplicateTest(FirstUser);

	}
	
	public String OpenAddUsersPage() throws InterruptedException
	{
		EIU.RolesAndUsersDropdownMenu();
		EIU.UsersMenuButton();
		Thread.sleep(1500);
		String s= EIU.FirstRowEmailData();
		EIU.AddNewUserButton();
		return s;
	}
	
	public void MandatoryCheck() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Mandatory fields");
		EIU.SaveButton();
		Thread.sleep(1000);
		
		if(driver.findElement(By.xpath("//p[text()= 'Please enter the name']")).isDisplayed() &&
			driver.findElement(By.xpath("//p[text()= 'Enter a valid email']")).isDisplayed() &&
			driver.findElement(By.xpath("//p[text()= 'Role selection is required']")).isDisplayed())
		{
			testcase.log(PASS, "Mandatory fields are not accepting blank values");
			takescreenshot(driver, "Mandatory Checked");
		}
		else
		{
			testcase.log(FAIL, "Mandatory fields accepts blank values");
			takescreenshot(driver, "Mandatory Check Failed");
		}		
	}
	
	public void DuplicateTest(String s) throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Duplicate email test");
		EIU.EnterName("New");
		EIU.EnterEmail(s);
		EIU.SelectRole("01 All Roles");
		EIU.SaveButton();
		EIU.ProceedWithNewButton();
		Thread.sleep(1000);
		
		if(EIU.ConfirmationMessage().toLowerCase().contains("email address is already in use"))
		{
			testcase.log(PASS, "Duplicate Email ID is not accepted in same Company. The '"+ EIU.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Duplicate Checked");
		}
		else
		{
			testcase.log(FAIL, "Duplicate Email ID is accepted in same Company");
			takescreenshot(driver, "Duplicate Check Failed");
		}
	}
}
