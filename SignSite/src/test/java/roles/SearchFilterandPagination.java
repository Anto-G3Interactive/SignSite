package roles;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class SearchFilterandPagination extends Initialstep
{
	ElementsInRolesPage EIR;
	
	String SearchValue= "147";
    String RowsPerPage= "25"; //Enter 10/25/50/100
    String MoveToPage= "Next"; // Enter Next or Previous

    @Test (priority= 1)
    public void Pagination() throws InterruptedException, IOException 
    {
    	EIR = new ElementsInRolesPage(driver, testcase);
    	testcase= extentReport.createTest("Verify the pagination functionality");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	EIR.RolesAndUsersDropdownMenu();
    	EIR.RolesMenuButton();
    	
    	EIR.RowsPerPage(RowsPerPage);
    	EIR.NextAndPrevious(MoveToPage);
    	
    	testcase.log(INFO, "Able to choose rows per page and able to move to next/previous page");
    } 
    
    @Test (priority= 2)
    public void SearchandFilter() throws InterruptedException, IOException 
    {
        testcase= extentReport.createTest("Verify the Search and Filter functionalities");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        try
    	{
        	EIR.RolesMenuButton();
    	}
    	catch (Exception e)
    	{
    		EIR.RolesAndUsersDropdownMenu();
    		EIR.RolesMenuButton();
    	}
        Thread.sleep(1500);
        EIR.SearchField(SearchValue);
        EIR.SelectStatus();
        
        if((driver.findElement(By.xpath("(//div[@data-field= 'title'])[2]")).getText()).contains(SearchValue))
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
    		EIR.RolesMenuButton();
    	}
    	catch (Exception e)
    	{
    		EIR.RolesAndUsersDropdownMenu();
    		EIR.RolesMenuButton();
    	}
    	
    	EIR.ColumnChooserMenu("Hide All");
    	Thread.sleep(1000);
    	if(EIR.ConfirmationAlert().toLowerCase().contains("success"))
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
    	
    	EIR.ColumnChooserMenu("Show All");    
    	Thread.sleep(1000);
    	if(EIR.ConfirmationAlert().toLowerCase().contains("success"))
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
