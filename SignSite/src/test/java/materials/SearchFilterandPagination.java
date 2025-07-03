package materials;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class SearchFilterandPagination extends Initialstep 
{
    ElementsInMaterials EIM;

    String SearchValue= "FriMay";
    String RowsPerPage= "10";
    String MoveToPage= "Next"; // Enter Next or Previous
    
    @Test (priority= 2)
    public void SearchandFilter() throws InterruptedException, IOException 
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        testcase= extentReport.createTest("Verify the Search and Filter functionalities");
        EIM.ProductDropdownMenu();
        EIM.MaterialMenuButton();
        Thread.sleep(1500);
        EIM.SearchField().sendKeys(SearchValue);
        EIM.SelectCategory();
        EIM.SelectStatus();
        
        if((driver.findElement(By.xpath("(//div[@data-field= 'name'])[2]")).getText()).contains(SearchValue))
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
    
    @Test (priority= 1)
    public void Pagination() throws InterruptedException, IOException 
    {
    	EIM = new ElementsInMaterials(driver, testcase);
    	testcase= extentReport.createTest("Verify the pagination functionality");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	EIM.ProductDropdownMenu();
        EIM.MaterialMenuButton();
    	
    	EIM.RowsPerPage(RowsPerPage);
    	EIM.NextAndPrevious(MoveToPage);
    	
    	testcase.log(INFO, "Able to choose rows per page and able to move to next/previous page");
    } 
    
    @Test (priority= 3)
    public void ColumChooser() throws InterruptedException, IOException
    {
    	testcase= extentReport.createTest("Verify the basic functionality of column chooser");
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	
    	try
    	{
    		EIM.MaterialMenuButton();
    	}
    	catch (Exception e)
    	{
	    	EIM.ProductDropdownMenu();
	        EIM.MaterialMenuButton();
    	}
    	
    	EIM.ColumnChooserMenu("Hide All");
    	if(driver.findElement(By.xpath("//div[text()= 'Details saved successfully.']")).isDisplayed())
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
    	
    	EIM.ColumnChooserMenu("Show All");
    	if(driver.findElement(By.xpath("//div[text()= 'Details saved successfully.']")).isDisplayed())
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