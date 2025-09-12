package smoketesting;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;
import roles.ElementsInRolesPage;
import users.ElementsInUsersPage;

public class EditRolesAndUsers extends Initialstep
{
	ElementsInRolesPage EIR;
	ElementsInUsersPage EIU;
	
	String Name= "To Be Updated"+ generateDummyName();
	String Email= "ToBeUpdated"+ generateDummyName()+"@gamil.com";
	String Phone= "1234567890";
	String Title= "Updated Title";
	String Initials= "Updated Initial";
	String Role= "To Be Updated";
	String Image= "D:\\Images\\Robot.gif";
	
	
// Roles
	
	@Test (priority= 1)
	public void EditRole() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Edit Roles Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIR= new ElementsInRolesPage(driver, testcase);
		
		EIR.RolesAndUsersDropdownMenu();
		EIR.RolesMenuButton();
		EIR.SearchField("To Be Updated");
		Thread.sleep(1500);
		EIR.EditButton(1);
		Thread.sleep(1000);
		EIR.EnterRoleName(Name);
		EIR.ClickOnAllPermissionsButton("Disable");
		EIR.QuotesPageSelectRolesAndAccess("Enable: view");
		EIR.SalesOrderPageSelectRolesAndAccess("Enable: View");
		EIR.JobsPageSelectRolesAndAccess("Enable: View");
		EIR.ClickOnAllCustomerRolesButton("Enable");
		EIR.ClickOnAllRolesofRolesButton("Disable");
		EIR.ClickOnAllUsersRolesButton("Disable");
		EIR.ProductsPageSelectRolesAndAccess("Enable: Add, View, Edit");
		EIR.CategoryPageSelectRolesAndAccess("Enable: Add, View, Edit");
		EIR.MaterialPageSelectRolesAndAccess("Enable: Add, View");
		EIR.MachineryPageSelectRolesAndAccess("Enable: Add, View");
		EIR.LabourPageSelectRolesAndAccess("Enable: Add, View");
		EIR.AccountManagementPageSelectRolesAndAccess("Disable");
		EIR.TermsandConditionsSelectPageRolesAndAccess("Diable");
		EIR.EmailTemplatesPageSelectRolesAndAccess("Enable: View");
		EIR.FormBuilderPageSelectRolesAndAccess("Disable");
		EIR.SaveRoleButton();
		Thread.sleep(1000);
		if(EIR.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Role updated and the '"+ EIR.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Role Updated");
		}
		else
		{
			testcase.log(FAIL, "Role did not updated and the '"+ EIR.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Role did not Updated");
		}
	}
	
	
// Users
	
	@Test (priority= 2)
	public void EditUsers() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Edit Users Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIU= new ElementsInUsersPage(driver, testcase);
		
		EIU.UsersMenuButton();
		EIU.SearchField("To Be Updated");
		Thread.sleep(1500);
		EIU.EditButton(1);
		EIU.DeleteAvatar();
		Thread.sleep(1000);
		EIU.EnterName(Name);
		EIU.EnterEmail(Email);
		EIU.EnterPhoneNumber(Phone);
		EIU.EnterTitle(Title);
		EIU.EnterInitials(Initials);
		EIU.SelectRole(Role);
		EIU.ClickOnSalesRepCheckBox("Enable");
		EIU.ClickOnProductionManagerCheckBox("Enable");
		EIU.ClickOnProjectManagerCheckBox("Enable");
		EIU.ClickOnDesignerCheckBox("Enable");
		EIU.SaveButton();
		Thread.sleep(1000);
		try
		{
			if(EIU.ConfirmationMessage().toLowerCase().contains("success"))
			{
				testcase.log(PASS, "User updated and the '"+ EIU.ConfirmationMessage() +"' message is displayed");
				takescreenshot(driver, "User updated");
			}
			else
			{
				testcase.log(FAIL, "User did not updated and the '"+ EIU.ConfirmationMessage() +"' message is displayed");
				takescreenshot(driver, "User did not updated");
			}
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}
	}
}




