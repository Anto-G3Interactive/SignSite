package users;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditUser extends Initialstep
{
	String SearchVaule= "User Automation";
	String Name= "User Automation Edit";
	String Phone= "546314633640";
	String Title= "Automation Edit";
	String Initial= "Automation Edit";
	String Role= "User Testing";
	String ImagePath= "D:\\Images\\error-img.png";
	String Designer= "Disable";
	String ProjectManager= "Enable";
	String ProductionManager= "Disable";
	String SalesRep= "Enable";
	
	
	ElementsInUsersPage EIU;
	@Test
	public void editUserDetails() throws IOException, InterruptedException
	{
		EIU= new ElementsInUsersPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify edit User functionality");
		testcase.log(INFO, "Navigating to the Update User page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		try
		{
			OpenUserPage();
			Thread.sleep(1500);
			UpdateUserDetails();
			Thread.sleep(1500);
			SaveUserDetails();
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}
		
	}
	
	public void OpenUserPage() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Opening the Users Page");
		
		EIU.RolesAndUsersDropdownMenu();
		EIU.UsersMenuButton();
		Thread.sleep(1500);
		EIU.SearchField(SearchVaule);
		
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
	
	public void UpdateUserDetails() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Update user details");
		
		EIU.EditButton();
		
		EIU.EnterName(Name);
		EIU.EnterPhoneNumber(Phone);
		EIU.EnterTitle(Title);
		EIU.EnterInitials(Initial);
		EIU.SelectRole(Role);
		try
		{
			EIU.DeleteAvatar();
			EIU.DeletedMessageAndClickOnOkButton();
		}
		catch(Exception e){	}
		Thread.sleep(1000);
		EIU.UploadAvatar(ImagePath);
		EIU.DesignerCheckBoxClick(Designer);
		EIU.ProjectManagerCheckBoxClick(ProjectManager);
		EIU.ProductionManagerCheckBoxClick(ProductionManager);
		EIU.SalesRepCheckBoxClick(SalesRep);
		
		testcase.log(INFO, "User details updated to the fields");
		takescreenshot(driver, "User Details Updated");
	}
	
	public void SaveUserDetails() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Save the updated User Details");
		
		EIU.SaveButton();
		Thread.sleep(1000);
		
		if(EIU.ConfirmationAlert().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "User details updated and the '"+ EIU.ConfirmationAlert() +"' message is dispalyed");
			takescreenshot(driver, "User Details updated");
		}	
		else
		{
			testcase.log(PASS, "Failed to update the user. The '"+ EIU.ConfirmationAlert() +"' message is displayed");
			takescreenshot(driver, "Failed to update the user");
		}
	}
	
}









