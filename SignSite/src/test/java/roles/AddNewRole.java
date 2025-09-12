package roles;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddNewRole extends Initialstep
{
	String RoleName= generateDummyName();
	String JobsPageAccess= "Enable: View, Add, Edit, Delete"; // Enter valid access which needs to be Enabled/Disabled
	String CustomerPageAccess= "Enable: View, Add, Edit, Delete";
	String RolesPageAccess= "Enable: View, Add, Edit, Delete";
	String UsersPageAccess= "Enable: View, Add, Edit, Delete";
	String ProductsPageAccess= "Enable: View, Add, Edit, Delete";
	String CategoryPageAccess= "Enable: View, Add, Edit, Delete";
	String MaterialPageAccess= "Enable: View, Add, Edit, Delete";
	String LabourPageAccess= "Enable: View, Add, Edit, Delete";
	String MachineryPageAccess= "Enable: View, Add, Edit, Delete";
	String AccountManagementPageAccess= "Disable: View, Edit, Delete";
	String TermsandConditionsPageAccess= "Enable: View, Edit";
	String EmailTemplatesPageAccess= "Enable: View, Add, Edit, Delete";
	
	
	
	ElementsInRolesPage EIR;
	
	@Test
	public void addnewRole() throws InterruptedException, IOException
	{
		EIR= new ElementsInRolesPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify Add Roles functionality");
		testcase.log(INFO, "Navigating to the Add New Roles Page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try
		{
			OpenRolesPage();
			Thread.sleep(1500);
			AddRolesandAccess();
			Thread.sleep(1500);
			SaveRole();	
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}
	}
	
	public void OpenRolesPage() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Open the Add Roles Page");
		EIR.RolesAndUsersDropdownMenu();
		EIR.RolesMenuButton();
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
	}
	
	public void AddRolesandAccess() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Add Roles and Access to the page");
		
		EIR.EnterRoleName(RoleName);
		EIR.JobsPageSelectRolesAndAccess(JobsPageAccess);
		EIR.CustomerPageSelectRolesAndAccess(CustomerPageAccess);
		EIR.RolesPageSelectRolesAndAccess(RolesPageAccess);
		EIR.UsersPageSelectRolesAndAccess(UsersPageAccess);
		EIR.ProductsPageSelectRolesAndAccess(ProductsPageAccess);
		EIR.CategoryPageSelectRolesAndAccess(CategoryPageAccess);
		EIR.MaterialPageSelectRolesAndAccess(MaterialPageAccess);
		EIR.LabourPageSelectRolesAndAccess(LabourPageAccess);
		EIR.MachineryPageSelectRolesAndAccess(MachineryPageAccess);
		EIR.AccountManagementPageSelectRolesAndAccess(AccountManagementPageAccess);
		EIR.TermsandConditionsSelectPageRolesAndAccess(TermsandConditionsPageAccess);
		EIR.EmailTemplatesPageSelectRolesAndAccess(EmailTemplatesPageAccess);
		
		testcase.log(INFO, "Roles and Permissions selected");
		takescreenshot(driver, "Roles and Permissions Selected");
	}
	
	public void SaveRole() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Verify by save the Role");
		
		EIR.SaveRoleButton();
		Thread.sleep(1500);
		
		if(EIR.ConfirmationMessage().contains("successfully"))
		{
			testcase.log(PASS, "New Role is added successfully and the '"+ EIR.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "New Role added");
		}
		else
		{
			testcase.log(FAIL, "New Role did not added");
			takescreenshot(driver, "Role did not added");
		}
	}
}










