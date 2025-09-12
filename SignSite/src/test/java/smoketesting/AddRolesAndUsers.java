package smoketesting;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;
import roles.ElementsInRolesPage;
import users.ElementsInUsersPage;

public class AddRolesAndUsers extends Initialstep
{
	ElementsInRolesPage EIR;
	ElementsInUsersPage EIU;
	
	String Name= "Automation Testing"+ generateDummyName();
	String Email= "Automation"+ generateDummyName()+"@gamil.com";
	String Phone= "1234567890";
	String Title= "Automation Title";
	String Initials= "Automation Initial";
	String Role= "Automation";
	String Image= "D:\\Images\\Wow.gif";
	
	
// Roles
	
	@Test (priority= 1)
	public void AddRoles() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Add Roles Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIR= new ElementsInRolesPage(driver, testcase);
		
		EIR.RolesAndUsersDropdownMenu();
		EIR.RolesMenuButton();
		Thread.sleep(1500);
		EIR.AddNewRoleButton();
		EIR.EnterRoleName(Name);
		EIR.ClickOnAllPermissionsButton("Enable");
		EIR.SaveRoleButton();
		Thread.sleep(1500);
		if(EIR.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "New Role created and the '"+ EIR.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Role Created");
		}
		else
		{
			testcase.log(FAIL, "Role did not created and the '"+ EIR.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Role did not Created");
		}
	}
	
	
// Users	
	
	@Test (priority= 2)
	public void AddUsers() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Add Users Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIU= new ElementsInUsersPage(driver, testcase);
		
		EIU.UsersMenuButton();
		EIU.AddNewUserButton();
		Thread.sleep(1500);
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
		EIU.UploadAvatar(Image);
		EIU.SaveButton();
		Thread.sleep(1500);
		try
		{
			if(EIU.ConfirmationMessage().toLowerCase().contains("success"))
			{
				testcase.log(PASS, "New User created and the '"+ EIU.ConfirmationMessage() +"' message is displayed");
				takescreenshot(driver, "User Created");
			}
			else
			{
				testcase.log(FAIL, "User did not created and the '"+ EIR.ConfirmationMessage() +"' message is displayed");
				takescreenshot(driver, "User did not Created");
			}
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}
	}
}




