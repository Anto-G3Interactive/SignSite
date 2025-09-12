package smoketesting;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Test;
import basepack.Initialstep;
import roles.ElementsInRolesPage;
import users.ElementsInUsersPage;

public class DeleteRolesAndUsers extends Initialstep
{
	ElementsInRolesPage EIR;
	ElementsInUsersPage EIU;
	
	String RoleToBeDeleted= "To Be Deleted";
	String UserToBeDeleted= "To Be Deleted";
	String Unique= generateDummyName();
	
// Roles
	
	@Test (priority= 1)
	public void DeleteRoles() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Delete Roles Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIR= new ElementsInRolesPage(driver, testcase);
		
		EIR.RolesAndUsersDropdownMenu();
		EIR.RolesMenuButton();
		EIR.SearchField(RoleToBeDeleted);
		Thread.sleep(1500);
		try {EIR.DeleteButton(1);}
		catch (Exception e)
		{
			AddRole(); // To add the role
			EIR.RolesMenuButton();
			EIR.SearchField(RoleToBeDeleted+Unique);
			Thread.sleep(1500);
			EIR.DeleteButton(1);
		}
		Thread.sleep(1000);
		takescreenshot(driver, "Role Deleted");
		Thread.sleep(1000);
		EIR.DeletedMessageAndClickOnOkButton("Role");
	}
	public void AddRole() throws InterruptedException
	{
		EIR.AddNewRoleButton();
		EIR.EnterRoleName(RoleToBeDeleted+Unique);
		EIR.ClickOnAllPermissionsButton("Enable");
		Thread.sleep(1000);
		EIR.SaveRoleButton();
		Thread.sleep(1500);
	}
	
	
// Users
	
	@Test (priority= 2)
	public void DeleteUsers() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Edit Users Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIU= new ElementsInUsersPage(driver, testcase);
		
		try{EIU.UsersMenuButton();}
		catch(Exception e){EIU.RolesAndUsersDropdownMenu();
			EIU.UsersMenuButton();}
		EIU.SearchField(UserToBeDeleted);
		Thread.sleep(1500);
		try {EIU.DeleteButton(1);}
		catch (Exception e)
		{
			AddUser();
			driver.navigate().refresh();
			Thread.sleep(1250);
			EIU.RolesAndUsersDropdownMenu();
			EIU.UsersMenuButton();
			Thread.sleep(1500);
			EIU.SearchField(UserToBeDeleted+Unique);
			Thread.sleep(1500);
			EIU.DeleteButton(1);
		}
		Thread.sleep(1000);
		takescreenshot(driver, "User Deleted");
		Thread.sleep(1000);
		EIU.DeletedMessageAndClickOnOkButton("User");
	}
	public void AddUser() throws InterruptedException
	{
		EIU.AddNewUserButton();
		Thread.sleep(1500);
		EIU.EnterName(UserToBeDeleted+Unique);
		EIU.EnterEmail("ToBeDeleted"+ Unique +"@gmail.com");
		EIU.EnterPhoneNumber("1234567890");
		EIU.EnterTitle(UserToBeDeleted);
		EIU.EnterInitials(UserToBeDeleted);
		EIU.SelectRole("Automation");
		EIU.ClickOnSalesRepCheckBox("Enable");
		EIU.ClickOnProductionManagerCheckBox("Enable");
		EIU.ClickOnProjectManagerCheckBox("Enable");
		EIU.ClickOnDesignerCheckBox("Enable");
		EIU.SaveButton();
		Thread.sleep(2500);
	}
}




