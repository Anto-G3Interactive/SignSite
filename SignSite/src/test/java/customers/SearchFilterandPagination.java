package customers;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class SearchFilterandPagination extends Initialstep
{
	
	String SearchValue= "Automation Testing";
    String RowsPerPage= "10";
    String MoveToPage= "Next"; // Enter Next or Previous

    ElementsInCustomerPage EIC;
    
    @Test (priority= 1)
    public void Pagination() throws InterruptedException, IOException 
    {
    	EIC = new ElementsInCustomerPage(driver, testcase);
    	testcase= extentReport.createTest("Verify the pagination functionality");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	EIC.CustomerMenuButton();
    	
    	EIC.RowsPerPage(RowsPerPage);
    	EIC.NextAndPrevious(MoveToPage);
    	
    	testcase.log(INFO, "Able to choose rows per page and able to move to next/previous page");
    } 
    
    @Test (priority= 2)
    public void SearchandFilter() throws InterruptedException, IOException 
    {
        testcase= extentReport.createTest("Verify the Search and Filter functionalities");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        EIC.CustomerMenuButton();
        Thread.sleep(1500);
        EIC.SearchField(SearchValue);
        EIC.SelectStatusOfFirstRow();
        
        if(EIC.FirstMainColumnValueToSearch().contains(SearchValue))
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
    	
    	EIC.CustomerMenuButton();
    	
    	EIC.ColumnChooserMenu("Hide All");
    	if(EIC.ConfirmationAlert().contains("Details saved successfully."))
    	{
    		testcase.log(PASS, "Hide column is working fine");
    		Thread.sleep(1000);
    		takescreenshot(driver, "Hide All Columns");
    	}
    	else
    	{
    		testcase.log(FAIL, "Hide column did not working properly");
    		Thread.sleep(1000);
    		takescreenshot(driver, "Hide All Columns test failed");
    	}
    	
    	EIC.ColumnChooserMenu("Show All");
    	if(EIC.ConfirmationAlert().contains("Details saved successfully."))
    	{
    		testcase.log(PASS, "Show column is working fine");
    		Thread.sleep(1000);
    		takescreenshot(driver, "Show All Columns");
    	}
    	else
    	{
    		testcase.log(FAIL, "Show column did not working properly");
    		Thread.sleep(1000);
    		takescreenshot(driver, "Show All Columns test failed");
    	}
    	
    }

}