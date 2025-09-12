package roles;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditRole extends Initialstep
{
	String SearchValue= "147";
//	String RoleName= generateDummyName();
	String JobsPageAccess= "Enable: View, Add, Edit, Delete"; // Enter valid access which needs to be Enabled/Disabled
	String CustomerPageAccess= "Disable: View, Add, Edit, Delete";
	String RolesPageAccess= "Disable: View, Add, Edit, Delete";
	String UsersPageAccess= "Disable: View, Add, Edit, Delete";
	String ProductsPageAccess= "Enable: View, Add, Edit, Delete";
	String CategoryPageAccess= "Disable: View, Add, Edit, Delete";
	String MaterialPageAccess= "Disable: View, Add, Edit, Delete";
	String LabourPageAccess= "Disable: View, Add, Edit, Delete";
	String MachineryPageAccess= "Disable: View, Add, Edit, Delete";
	String AccountManagementPageAccess= "Disable: View, Edit, Delete";
	String TermsandConditionsPageAccess= "Disable: View, Edit";
	String EmailTemplatesPageAccess= "Disable: View, Add, Edit, Delete";
	
	ElementsInRolesPage EIR;
	
	@Test
	public void editRole() throws InterruptedException, IOException
	{
		EIR= new ElementsInRolesPage(driver, testcase);
		testcase= extentReport.createTest("Verify Edit Roles functionality");
		testcase.log(INFO, "Navigating to the Add New Roles Page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try
		{
			OpenRolesPage();
			Thread.sleep(1500);
			EditRoles();
			Thread.sleep(1500);
			UpdateRole();
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
		EIR.SearchField(SearchValue);
		Thread.sleep(1500);
		EIR.EditButton(1);
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
	
	public void EditRoles() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Edit Roles and Access for the pages");
		
//		EIR.EnterRoleName(RoleName);
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
		
		testcase.log(INFO, "Roles and Permissions updated");
		takescreenshot(driver, "Roles and Permissions updated");
	}
	
	public void UpdateRole() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Verify update Role functionality");
		
		EIR.SaveRoleButton();
		Thread.sleep(1500);
		
		if(EIR.ConfirmationMessage().contains("successfully"))
		{
			testcase.log(PASS, "Role is edited successfully and the '"+ EIR.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Role updated");
		}
		else
		{
			testcase.log(FAIL, "The Role did not updated");
			takescreenshot(driver, "Role did not updated");
		}
	}
	
}
