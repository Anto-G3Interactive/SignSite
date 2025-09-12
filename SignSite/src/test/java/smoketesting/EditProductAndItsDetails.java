package smoketesting;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.Test;
import basepack.Initialstep;
import category.ElementsInCategoryPage;
import labour.ElementsInLabourPage;
import machinery.ElementsInMachineryPage;
import materials.ElementsInMaterialsPage;
import products.ElementsInProductBulkDiscountPage;
import products.ElementsInProductPage;

public class EditProductAndItsDetails extends Initialstep
{
	ElementsInCategoryPage EIC;
	ElementsInMaterialsPage EIMat;
	ElementsInMachineryPage EIMach;
	ElementsInLabourPage EIL;
	ElementsInProductPage EIP;
	ElementsInProductBulkDiscountPage EIBD;
	
	String CategoryName= "To Be Updated "+generateDummyName();
	String MaterialName= "To Be Updated "+generateDummyName();
	String MachineryName= "To Be Updated "+generateDummyName();
	String LabourName= "To Be Updated "+generateDummyName();
	String ProductName= "To Be Updated "+generateDummyName();
	String Path= "D:\\Images\\Robot.gif";
	String SearchValue= "To Be Updated";
	
	
// Edit Category	
	
	@Test (priority= 1)
	public void EditCategories() throws InterruptedException, IOException
	{
		EIC= new ElementsInCategoryPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Edit Category Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		EIC.ProductDropdownMenu();
		EIC.CategoryMenuButton();
		Thread.sleep(1500);
		updateCategories("Material", 1);
		Thread.sleep(1500);
		updateCategories("Machinery", 2);
		Thread.sleep(1500);
		updateCategories("Labour", 3);
		Thread.sleep(1500);
		updateCategories("Product", 4);
	}
	
	public void updateCategories(String Type, int Index) throws InterruptedException, IOException
	{
		EIC.Searchfield(SearchValue);
		Thread.sleep(1500);
		try
		{
			EIC.EditButton(Index);
		}
		catch(Exception e)
		{
			AddCategory(Type);
			EIC.CategoryMenuButton();
			EIC.Searchfield("To Be Updated");
			Thread.sleep(1500);
			EIC.EditButton(Index);
		}
		EIC.EnterCategoryName(CategoryName);
		EIC.SelectType(Type);
		EIC.SaveButton();
		Thread.sleep(1500);
		SuccessOrFail("Category");
	}
	public void AddCategory(String Type) throws InterruptedException
	{
		EIC.AddNewCategoryButton();
		EIC.EnterCategoryName(SearchValue);
		EIC.SelectType(Type);
		EIC.SaveButton();
	}
	
	
// Edit Material	
	
	@Test (priority= 2)
	public void EditMaterial() throws InterruptedException, IOException
	{
		EIMat= new ElementsInMaterialsPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Edit Material Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		EIMat.MaterialMenuButton();
		EIMat.SearchField(SearchValue);
		Thread.sleep(1250);
		try
		{
			EIMat.EditButton(1);
		}
		catch(Exception e)
		{
			AddMaterial();
			EIMat.MaterialMenuButton();
			EIMat.SearchField(SearchValue);
			Thread.sleep(1250);
			EIMat.EditButton(1);
		}
		EIMat.SelectMaterialCategory(CategoryName);
		EIMat.EnterMaterialName(MaterialName);
		EIMat.SelectUnit("Ton");
		EIMat.EnterCost("324");
		EIMat.EnterMarkup("16");
		EIMat.EnterDescription("Description Updated Through Automation on "+ generateDummyName());
		EIMat.EnterPODescription("PO Description Updated Through Automation Testing on "+ generateDummyName());
		EIMat.PerLIUnit();
		Thread.sleep(1500);
		EIMat.Save();
		Thread.sleep(1000);
		SuccessOrFail("Material");
	}
	public void AddMaterial() throws InterruptedException, IOException
	{
		EIMat.AddNewMaterial();
		Thread.sleep(1000);
		EIMat.SelectMaterialCategory("Automation");
		EIMat.EnterMaterialName(SearchValue);
		EIMat.SelectUnit("Ton");
		EIMat.EnterCost("324");
		EIMat.EnterMarkup("16");
		Thread.sleep(1500);
		EIMat.Save();
	}
	
	
// Edit Machinery	
	
	@Test (priority= 3)
	public void EditMachinery() throws InterruptedException, IOException
	{
		EIMach= new ElementsInMachineryPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Edit Machinery Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		EIMach.MachineryMenuButton();
		EIMach.SearchField(SearchValue);
		Thread.sleep(1250);
		try
		{
			EIMach.EditButton(1);
		}
		catch(Exception e)
		{
			AddMachinery();
			EIMach.MachineryMenuButton();
			EIMach.SearchField(SearchValue);
			Thread.sleep(1250);
			EIMach.EditButton(1);
		}
		EIMach.SelectMachineryCategory(CategoryName);
		EIMach.EnterMachineryName(MachineryName);
		EIMach.SelectUnit("Day");
		EIMach.EnterCost("156");
		EIMach.EnterMarkup("32");
		Thread.sleep(1500);
		EIMach.Save();
		Thread.sleep(1000);
		SuccessOrFail("Machinery");
	}
	public void AddMachinery() throws InterruptedException, IOException
	{
		EIMach.AddNewMachineryButton();
		Thread.sleep(1000);
		EIMach.SelectMachineryCategory("Automation");
		EIMach.EnterMachineryName(SearchValue);
		EIMach.SelectUnit("Day");
		EIMach.EnterCost("156");
		EIMach.EnterMarkup("32");
		Thread.sleep(1500);
		EIMach.Save();
	}
	
	
// Edit Labor	
	
	@Test (priority= 4)
	public void EditLabour() throws IOException, InterruptedException
	{
		EIL= new ElementsInLabourPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Edit Labour Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIL.LabourMenuButton();
		Thread.sleep(1000);
		EIL.SearchField(SearchValue);
		Thread.sleep(1250);
		try
		{
			EIL.EditButton(1);
		}
		catch(Exception e)
		{
			AddLabour();
			EIL.LabourMenuButton();
			Thread.sleep(1000);
			EIL.SearchField(SearchValue);
			Thread.sleep(1250);
			EIL.EditButton(1);
		}
		EIL.SelectLabourCategory(CategoryName);
		EIL.EnterLabourName(LabourName);
		EIL.SelectUnit("Shift");
		EIL.EnterCost("856");
		EIL.EnterMarkup("12");
		Thread.sleep(1500);
		EIL.Save();
		Thread.sleep(1000);
		SuccessOrFail("Labour");
	}
	public void AddLabour() throws InterruptedException
	{
		EIL.AddNewLabourButton();
		Thread.sleep(1000);
		EIL.SelectLabourCategory("Automation");
		EIL.EnterLabourName(SearchValue);
		EIL.SelectUnit("Shift");
		EIL.EnterCost("856");
		EIL.EnterMarkup("12");
		Thread.sleep(1500);
		EIL.Save();
	}
	
	
// Edit Product	
	
	@Test (priority= 5)
	public void EditProduct() throws InterruptedException, AWTException, IOException
	{
		EIP= new ElementsInProductPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Edit Labour Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIP.ProductsMenuButton();
		EIP.SearchField(SearchValue);
		Thread.sleep(1000);
		try
		{
			EIP.EditProduct(1);
		}
		catch(Exception e)
		{
			AddProductandItsDetails();
			EIP.ProductsMenuButton();
			EIP.SearchField(SearchValue);
			Thread.sleep(1000);
			EIP.EditProduct(1);
		}
		EIP.EnterProductName(ProductName);
		EIP.SelectProductCategory(CategoryName);
		EIP.EnterDescription("Automation Smoke Testing");
		EIP.UploadProductImage(Path);
		EIP.SaveButton();
		Thread.sleep(1250);
		SuccessOrFail("Product");
		
	// Edit Material
		EIP.EditMaterialsButton();
		EIP.EditMaterialQuantity("2");
		EIP.EditMaterialLIQuantity("5");
		EIP.ConfirmEditsButton();
		Thread.sleep(1500);
		
	// Edit Machinery
		EIP.EditMachineryButton();
		EIP.EditMachineryQuantity("2");
		EIP.EditMachineryLIQuantity("5");
		EIP.ConfirmEditsButton();
		Thread.sleep(1500);
		
	// Edit Labor
		EIP.EditLabourButton();
		EIP.EditLabourQuantity("2");
		EIP.EditLabourLIQuantity("4");
		EIP.ConfirmEditsButton();
		Thread.sleep(1500);
	}
	public void AddProductandItsDetails() throws InterruptedException, AWTException
	{
		EIP.AddNewProductButton();
		Thread.sleep(1500);
		EIP.EnterProductName(SearchValue);
		EIP.SelectProductCategory("Automation");
		EIP.EnterDescription("Automation Smoke Testing");
		EIP.UploadProductImage("D:\\Images\\Robot.gif");
		EIP.SaveButton();
		Thread.sleep(1500);
		
	// Product Details
		EIP.AddMaterialsButton();
		EIP.SelectMaterial("Automation");
		Thread.sleep(1250);
		EIP.SaveMateial();
		Thread.sleep(1500);
		
		EIP.AddMachineryButton();
		EIP.SelectMachinery("Automation");
		Thread.sleep(1250);
		EIP.SaveMachinery();
		Thread.sleep(1500);
		
		EIP.AddLabourButton();
		EIP.SelectLabour("Automation");
		Thread.sleep(1250);
		EIP.SaveLabour();
		Thread.sleep(1500);
	}
	
// Edit Discount	
	
	@Test (dependsOnMethods= "EditProduct")
	public void EditProductDiscount() throws InterruptedException, IOException
	{
		EIBD= new ElementsInProductBulkDiscountPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Edit Discount Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIP.EnableorDisableBulkDiscount("Enable");
		Thread.sleep(1250);
		EIBD.BulkDiscountTab();
		try
		{
			EIBD.EditDiscountButton();
		}
		catch(Exception e)
		{
			AddProductDiscount();
			EIBD.EditDiscountButton();
		}
		EIBD.EnterMinimumQuantity("1");
		EIBD.EnterMaximumQuantity("10");
		EIBD.EnterDiscountPercentage("10");
		EIBD.SaveDiscount();
		Thread.sleep(1250);
		SuccessOrFail("Discount");
	}
	public void AddProductDiscount() throws InterruptedException
	{
		EIBD.BulkDiscountTab();
		EIBD.AddNewDiscountButton();
		Thread.sleep(1000);
		EIBD.EnterMinimumQuantity("5");
		EIBD.EnterMaximumQuantity("12");
		EIBD.EnterDiscountPercentage("8");
		Thread.sleep(1250);
		EIBD.SaveDiscount();
	}
	
	public void SuccessOrFail(String TypeOrDetail) throws IOException
	{
		if(EIC.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, TypeOrDetail+" updated and the '"+ EIC.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, TypeOrDetail+" updated successfully");
		}
		else
		{
			testcase.log(FAIL, "Failed to update the '"+ TypeOrDetail +". The '"+ EIC.ConfirmationMessage() +"' message is dispalyed.");
			takescreenshot(driver, "Failed to update the '"+ TypeOrDetail);
		}
	}
}





