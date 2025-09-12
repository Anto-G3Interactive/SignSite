package customers;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddTags extends Initialstep
{
	@Test 
	public void Addtags() throws InterruptedException, IOException
	{
		ElementsInCustomerPage EIC= new ElementsInCustomerPage(driver, testcase);
		testcase= extentReport.createTest("Verify Tag functionality of the Customer");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		String SearchValue= "Automation";
		String TagName= "Completed"; // Enter a valid Tag Name
		String TagName2= "sign";
		
		EIC.CustomerMenuButton();
		EIC.SearchField(SearchValue);
		Thread.sleep(1000);
		
		try
		{
			EIC.AddTagsButton();
		}
		catch(Exception e)
		{
			testcase.log(FAIL, "Not able to find the Add Tags button. Please Add Column from column chooser.");
			takescreenshot(driver, "Add Tags button not exist");
			driver.close();
		}
		
		Thread.sleep(1000);
		EIC.SelectTags(TagName);
		Thread.sleep(1000);
		EIC.SelectTags(TagName2);
		
		EIC.SaveTagsButton();
		Thread.sleep(1500);
		if(EIC.ConfirmationMessage().contains("Tags updated"))
		{
			testcase.log(PASS, "Tag added to the customer and the '"+ EIC.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Tag Added");
		}
		else
		{
			testcase.log(FAIL, "Tag not added");
			takescreenshot(driver, "Tag not added");
		}
		
	}

}
