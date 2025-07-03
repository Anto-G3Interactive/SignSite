package roles;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DeleteRoleTest extends Initialstep
{
	ElementsInRolesPage EIR;
	
	String ToBeDeleted= "TueJun";
	
	@Test
    public void DeleteRole() throws InterruptedException, IOException 
    {
    	EIR = new ElementsInRolesPage(driver, testcase);
    	testcase= extentReport.createTest("Verify the Delete Role functionality");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	EIR.RolesAndUsersDropdownMenu();
    	EIR.RolesMenuButton();
    	EIR.SearchField(ToBeDeleted);
    	Thread.sleep(1500);
    	EIR.DeleteButton();
    	Thread.sleep(1000);
    	
    	if(EIR.DeleteConfirmation().isDisplayed())
    	{
    		testcase.log(PASS, "Role Deleted and the '"+ EIR.DeleteConfirmation().getText()+ "' message is displayed");
        	takescreenshot(driver,"Role Deleted");
    	}
    	else
    	{
    		testcase.log(FAIL, "Role Did not Deleted");
        	takescreenshot(driver,"Role did not deleted");
    	}
    	driver.findElement(By.xpath("//button[text()= 'OK']")).click();
	}
}
