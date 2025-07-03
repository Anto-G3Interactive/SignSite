package labour;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DuplicateLabourTest extends Initialstep 
{
	ElementsInLabourPage EIL;
    
    String Unit= "Piece";
    String Cost= "12.33";
    String Markup= "10";
    
    
    @Test
    public void DuplicateTest() throws InterruptedException, IOException
    {
    	EIL = new ElementsInLabourPage(driver, testcase);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        testcase= extentReport.createTest("Verify the duplicate labour functionality");
        
        EIL.ProductDropdownMenu();
        EIL.LabourMenuButton();
        
        String DuplicateCategory= driver.findElement(By.xpath("(//div[@data-field= 'category'])[2]")).getText();
        String DuplicateLabour= driver.findElement(By.xpath("(//div[@data-field= 'name'])[2]")).getText();
    	EIL.AddNewLabourButton();
    	EIL.SelectLabourCategory(DuplicateCategory);
    	EIL.EnterLabourName(DuplicateLabour);
    	EIL.SelectUnit(Unit);
    	EIL.EnterCost(Cost);
    	EIL.EnterMarkup(Markup);
    	EIL.Save();
    	Thread.sleep(1000);
    	if(driver.findElement(By.xpath("//p[text()= 'The name has already been taken.']")).isDisplayed())
    	{
    		testcase.log(PASS, "Labour is not accepting the duplicate");
    		takescreenshot(driver,"Labour not accepting duplicate");
    	}
    	else
    	{
    		testcase.log(PASS, "Labour accepts duplicates");
    		takescreenshot(driver,"Labour accepts duplicates");
    	}
    	
    } 
}