package machinery;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DuplicateMachineryTest extends Initialstep 
{
	ElementsInMachineryPage EIM;
    
    String Unit= "Day";
    String Cost= "12.33";
    String Markup= "10";
    
    
    @Test
    public void DuplicateTest() throws InterruptedException, IOException
    {
    	EIM = new ElementsInMachineryPage(driver, testcase);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        testcase= extentReport.createTest("Verify the duplicate Machinery functionality");
        
        EIM.ProductDropdownMenu();
        EIM.MachineryMenuButton();
        
        String DuplicateCategory= driver.findElement(By.xpath("(//div[@data-field= 'category'])[2]")).getText();
        String DuplicateMachinery= driver.findElement(By.xpath("(//div[@data-field= 'name'])[2]")).getText();
    	EIM.AddNewMachineryButton();
    	EIM.SelectMachineryCategory(DuplicateCategory);
    	EIM.EnterMachineryName(DuplicateMachinery);
    	EIM.SelectUnit(Unit);
    	EIM.EnterCost(Cost);
    	EIM.EnterMarkup(Markup);
    	EIM.Save();
    	Thread.sleep(1000);
    	if(driver.findElement(By.xpath("//p[text()= 'The name has already been taken.']")).isDisplayed())
    	{
    		testcase.log(PASS, "Machinery is not accepting the duplicate");
    		takescreenshot(driver,"Machinery not accepting duplicate");
    	}
    	else
    	{
    		testcase.log(PASS, "Machinery accepts duplicates");
    		takescreenshot(driver,"Machinery accepts duplicates");
    	}
    	
    } 
}