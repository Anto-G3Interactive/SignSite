package smoketesting;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Test;
import basepack.Initialstep;
import category.ElementsInCategoryPage;

public class AddProdcutandItsDetails extends Initialstep
{
	ElementsInCategoryPage EIC;
	
	String CategoryName= "AutomationCategories"+generateDummyName();
	
	
	
	@Test (priority= 1)
	public void AddCategory() throws InterruptedException, IOException
	{
		EIC= new ElementsInCategoryPage(driver, testcase);
		extentReport.createTest("To Verify the Add Category Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIC.ProductDropdownMenu();
		EIC.CategoryMenuButton();
		Thread.sleep(1500);
		AddCategories("Material");
		Thread.sleep(1500);
		AddCategories("Machinery");
		Thread.sleep(1500);
		AddCategories("Labour");
	}
	
	public void AddCategories(String Type) throws InterruptedException, IOException
	{
		EIC.AddNewCategoryButton();
		EIC.EnterCategoryName(CategoryName);
		EIC.SelectType(Type);
		EIC.SaveButton();
		Thread.sleep(1500);
		SuccessOrFail(Type);
	}
	
	public void SuccessOrFail(String TypeOrDetail) throws IOException
	{
		if(EIC.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, TypeOrDetail+" added and the '"+ EIC.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, TypeOrDetail+" added successfully");
		}
		else
		{
			testcase.log(FAIL, "Failed to add the '"+ TypeOrDetail);
			takescreenshot(driver, "Failed to add the '"+ TypeOrDetail);
		}
	}
}
