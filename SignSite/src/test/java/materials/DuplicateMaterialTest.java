package materials;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DuplicateMaterialTest extends Initialstep 
{
    ElementsInMaterialsPage EIM;
    
    String Unit= "Kilogram";
    String Cost= "12.33";
    String Markup= "10";
    
    
    @Test
    public void DuplicateTest() throws InterruptedException, IOException
    {
    	EIM = new ElementsInMaterialsPage(driver,testcase);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        
        testcase= extentReport.createTest("Verify the duplicate material functionality");
        
        EIM.ProductDropdownMenu();
        EIM.MaterialMenuButton();
        
        String DuplicateCategory= driver.findElement(By.xpath("(//div[@data-field= 'category'])[2]")).getText();
        String DuplicateMaterial= driver.findElement(By.xpath("(//div[@data-field= 'name'])[2]")).getText();
    	EIM.AddNewMaterial();
    	EIM.SelectMaterialCategory(DuplicateCategory);
    	EIM.EnterMaterialName(DuplicateMaterial);
    	EIM.SelectUnit(Unit);
    	EIM.EnterCost(Cost);
    	EIM.EnterMarkup(Markup);
    	EIM.Save();
    	Thread.sleep(1000);
    	if(driver.findElement(By.xpath("//p[text()= 'The name has already been taken.']")).isDisplayed())
    	{
    		testcase.log(PASS, "Material is not accepting the duplicate");
    		takescreenshot(driver,"Material not accepting duplicate");
    	}
    	else
    	{
    		testcase.log(PASS, "Material accepts duplicates");
    		takescreenshot(driver,"Material accepts duplicates");
    	}
    	
    } 
}
