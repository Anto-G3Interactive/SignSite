package users;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DeleteUserTest extends Initialstep
{
	ElementsInUsersPage EIU;
	
	String ToBeDeleted= "To Be Deleted";
	
	@Test
    public void DeleteUser() throws InterruptedException, IOException 
    {
    	EIU= new ElementsInUsersPage(driver, testcase);
    	testcase= extentReport.createTest("Verify the Delete Users functionality");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	EIU.RolesAndUsersDropdownMenu();
    	EIU.UsersMenuButton();
    	EIU.SearchField(ToBeDeleted);
    	Thread.sleep(1500);
    	EIU.DeleteButton(1);
    	Thread.sleep(1000);
    	takescreenshot(driver, "User Deleted");
    	EIU.DeletedMessageAndClickOnOkButton("User");
	}
}









