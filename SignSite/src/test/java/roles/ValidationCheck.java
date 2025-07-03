package roles;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class ValidationCheck extends Initialstep
{
	ElementsInRolesPage EIR;
	
	@Test
	public void ValidationsCheck() throws InterruptedException, IOException
	{
		EIR= new ElementsInRolesPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify Validations on Add Roles page");
		testcase.log(INFO, "Navigating to the Add New Roles Page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try
		{
			String RoleName= OpenRolesPage();
			Thread.sleep(1500);
			SaveRole();	
			Thread.sleep(1500);
			DuplicateTest(RoleName);
			
		}
		catch(Exception e)
		{
			testcase.log(FAIL, "Failed to Run the Script");
			takescreenshot(driver, "Failed to Run the Script");
		}
	}
	
	
	public String OpenRolesPage() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Open the Add Roles Page");
		EIR.RolesAndUsersDropdownMenu();
		EIR.RolesMenuButton();
		Thread.sleep(1000);
		String RoleName= EIR.DuplicateRole();
		EIR.AddNewRoleButton();
		Thread.sleep(1000);
		
		if(driver.findElement(By.xpath("//h6[text()= 'Add Role']")).isDisplayed())
		{
			testcase.log(PASS, "Add Role page is displayed");
			takescreenshot(driver, "Add Role page");
		}
		else
		{
			testcase.log(FAIL, "Add Role page did not displayed");
			takescreenshot(driver, "Add Role page did not displayed");
		}
		return RoleName;
	}
	
	public void SaveRole() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Verify the Mandatory fields");
		
		EIR.SaveRoleButton();
		Thread.sleep(1500);
		
		if(driver.findElement(By.xpath("//p[text()= 'Role is required.']")).isDisplayed() &&
		   driver.findElement(By.xpath("//p[text()= 'At least one permission is required']")).isDisplayed())
		{
			testcase.log(PASS, "Mandatory fields are not accepting the blank");
			takescreenshot(driver, "Mandatory checked");
		}
		else
		{
			testcase.log(FAIL, "Mandatory fields accepts blank value");
			takescreenshot(driver, "Mandatory fields accepts blank");
		}
	}
	
	public void DuplicateTest(String RName) throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Duplicate Role");
		
		EIR.EnterRoleName(RName);
		EIR.CustomerPageSelectRolesAndAccess("Enable: view");
		EIR.SaveRoleButton();
		Thread.sleep(1500);
		
		if(driver.findElement(By.xpath("//p[text()= 'The title has already been taken.']")).isDisplayed())
		{
			testcase.log(PASS, "Duplicate Role did not accepted");
			takescreenshot(driver, "Duplicate Role did not accepted");
		}
		else
		{
			testcase.log(FAIL, "Duplicate Role is accepted");
			takescreenshot(driver, "Duplicate Role is accepted");
		}
	}
	
}
