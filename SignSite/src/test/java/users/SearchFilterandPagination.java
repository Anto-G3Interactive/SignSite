package users;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class SearchFilterandPagination extends Initialstep
{
	ElementsInUsersPage EIU;
	
	String SearchValue= "User Automation";
    String RowsPerPage= "25"; //Enter 10/25/50/100
    String MoveToPage= "Next"; // Enter Next or Previous

    @Test (priority= 1)
    public void Pagination() throws InterruptedException, IOException 
    {
    	EIU = new ElementsInUsersPage(driver, testcase);
    	testcase= extentReport.createTest("Verify the pagination functionality");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	EIU.RolesAndUsersDropdownMenu();
    	EIU.UsersMenuButton();
    	
    	EIU.RowsPerPage(RowsPerPage);
    	EIU.NextAndPrevious(MoveToPage);
    	
    	testcase.log(INFO, "Able to choose rows per page and able to move to next/previous page");
    } 
    
    @Test (priority= 2)
    public void SearchandFilter() throws InterruptedException, IOException 
    {
        testcase= extentReport.createTest("Verify the Search and Filter functionalities");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        try
    	{
        	EIU.UsersMenuButton();
    	}
    	catch (Exception e)
    	{
    		EIU.RolesAndUsersDropdownMenu();
    		EIU.UsersMenuButton();
    	}
        Thread.sleep(1500);
        EIU.SearchField(SearchValue);
        EIU.SelectStatus();
        
        if(EIU.FirstColumnData().toLowerCase().contains(SearchValue.toLowerCase()))
        {
        	testcase.log(PASS, "Search and Filter functionality works properly");
        	takescreenshot(driver,"Search and Filter");
        }
        else
        {
        	testcase.log(FAIL, "Search and Filter not working properly");
        	takescreenshot(driver,"Search and Filter not working properly");
        }
    }
    
    @Test (priority= 3)
    public void ColumChooser() throws InterruptedException, IOException
    {
    	testcase= extentReport.createTest("Verify the basic functionality of column chooser");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	try
    	{
    		EIU.UsersMenuButton();
    	}
    	catch (Exception e)
    	{
    		EIU.RolesAndUsersDropdownMenu();
    		EIU.UsersMenuButton();
    	}
    	
    	EIU.ColumnChooserMenu("Hide All");
    	Thread.sleep(1000);
    	if(EIU.ConfirmationMessage().toLowerCase().contains("success"))
    	{
    		testcase.log(PASS, "Hide column is working fine");
    		Thread.sleep(500);
    		takescreenshot(driver, "Hide All Columns");
    	}
    	else
    	{
    		testcase.log(FAIL, "Hide column did not working properly");
    		Thread.sleep(500);
    		takescreenshot(driver, "Hide All Columns test failed");
    	}
    	Thread.sleep(3500);
    	
    	EIU.ColumnChooserMenu("Show All");
    	Thread.sleep(1000);
    	if(EIU.ConfirmationMessage().toLowerCase().contains("success"))
    	{
    		testcase.log(PASS, "Show column is working fine");
    		Thread.sleep(500);
    		takescreenshot(driver, "Show All Columns");
    	}
    	else
    	{
    		testcase.log(FAIL, "Show column did not working properly");
    		Thread.sleep(500);
    		takescreenshot(driver, "Show All Columns test failed");
    	}
    	
    }

}
