package users;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddNewUser extends Initialstep
{
	String Name= "User Automation";
	String Email= generateDummyName() + "@gmail.com";
	String Phone= "1234567890";
	String Title= "Automation Testing";
	String Initials= "Testing";
	String Role= "01 All Roles";
	String ImagePath= "D:\\Images\\error-img.png";
	String Designer= "Enable";
	String ProjectManager= "Enable";
	String ProductionManager= "Enable";
	String SalesRep= "Enable";
	
	ElementsInUsersPage EIU;
	@Test
	public void addNewUser() throws IOException, InterruptedException
	{
		EIU= new ElementsInUsersPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify Add New User");
		testcase.log(INFO, "Navigating to the Add User page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try
		{
			OpenUsersPage();
			Thread.sleep(1500);
			AddNewUserDetails();
			Thread.sleep(1500);
			SaveNewUser();
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}

	}
	
	public void OpenUsersPage() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Opening the Users Page");
		
		EIU.RolesAndUsersDropdownMenu();
		EIU.UsersMenuButton();
		Thread.sleep(1000);
		
		if(EIU.UsersPageHeading().isDisplayed())
		{
			testcase.log(PASS, "Users page is displayed");
			takescreenshot(driver, "Users Page displayed");
		}
		else
		{
			testcase.log(PASS, "Users page did not displayed");
			takescreenshot(driver, "Users Page did not displayed");
		}
	}
	
	public void AddNewUserDetails() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Add New User details");
		
		EIU.AddNewUserButton();
		EIU.UploadAvatar(ImagePath);
		Thread.sleep(1000);
		EIU.EnterName(Name);
		EIU.EnterEmail(Email);
		EIU.EnterPhoneNumber(Phone);
		EIU.EnterTitle(Title);
		EIU.EnterInitials(Initials);
		EIU.SelectRole(Role);
		EIU.ClickOnDesignerCheckBox(Designer);
		EIU.ClickOnProjectManagerCheckBox(ProjectManager);
		EIU.ClickOnProductionManagerCheckBox(ProductionManager);
		EIU.ClickOnSalesRepCheckBox(SalesRep);
		
		testcase.log(INFO, "New User details added to the fields");
		takescreenshot(driver, "User Details");
	}
	
	public void SaveNewUser() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Save the New User");
		
		EIU.SaveButton();
		Thread.sleep(500);
		
		if(EIU.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "New User added successfully and the '"+ EIU.ConfirmationMessage() +"' message is dispalyed");
			takescreenshot(driver, "New User added successfully");
		}
	
		else
		{
			testcase.log(PASS, "Failed to add new user. The '"+ EIU.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Failed to add a new user");
		}
	}

}
