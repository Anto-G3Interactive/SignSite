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

public class AddProdcutandItsDetails extends Initialstep
{
	ElementsInCategoryPage EIC;
	ElementsInMaterialsPage EIMat;
	ElementsInMachineryPage EIMach;
	ElementsInLabourPage EIL;
	ElementsInProductPage EIP;
	ElementsInProductBulkDiscountPage EIBD;
	
	String CategoryName= "Automation Categories"+generateDummyName();
	String MaterialName= "Automation Material"+generateDummyName();
	String MachineryName= "Automation Machinery"+generateDummyName();
	String LabourName= "Automation Labour"+generateDummyName();
	String ProductName= "Automation Product"+generateDummyName();
	String Path= "D:\\Images\\Robot.gif";
	
	
// Add Category	
	
	@Test (priority= 1)
	public void AddCategory() throws InterruptedException, IOException
	{
		EIC= new ElementsInCategoryPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Add Category Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIC.ProductDropdownMenu();
		EIC.CategoryMenuButton();
		Thread.sleep(1500);
		AddCategories("Material");
		Thread.sleep(1500);
		AddCategories("Machinery");
		Thread.sleep(1500);
		AddCategories("Labour");
		Thread.sleep(1500);
		AddCategories("Product");
	}
	
	public void AddCategories(String Type) throws InterruptedException, IOException
	{
		EIC.AddNewCategoryButton();
		EIC.EnterCategoryName(CategoryName);
		EIC.SelectType(Type);
		EIC.SaveButton();
		Thread.sleep(1500);
		SuccessOrFail(Type+ " Category");
	}
	
	
// Add Material	
	
	@Test (dependsOnMethods= "AddCategory")
	public void AddMaterial() throws InterruptedException, IOException
	{
		EIMat= new ElementsInMaterialsPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Add Material Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIMat.MaterialMenuButton();
		EIMat.AddNewMaterial();
		Thread.sleep(1000);
		EIMat.SelectMaterialCategory(CategoryName);
		EIMat.EnterMaterialName(MaterialName);
		EIMat.SelectUnit("Ton");
		EIMat.EnterCost("324");
		EIMat.EnterMarkup("16");
		EIMat.EnterDescription("Smoke Testing using Automation Tool");
		EIMat.EnterPODescription("PO Description for Automation Testing");
		EIMat.PerLIUnit();
		Thread.sleep(1500);
		EIMat.Save();
		Thread.sleep(1000);
		SuccessOrFail("Material");
	}
	
	
// Add Machinery	
	
	@Test (dependsOnMethods= "AddCategory")
	public void AddMachinery() throws InterruptedException, IOException
	{
		EIMach= new ElementsInMachineryPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Add Machinery Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIMach.MachineryMenuButton();
		EIMach.AddNewMachineryButton();
		Thread.sleep(1500);
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
	
	
// Add Labor
	
	@Test (dependsOnMethods= "AddCategory")
	public void AddLabour() throws InterruptedException, IOException
	{
		EIL= new ElementsInLabourPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Add Labour Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIL.LabourMenuButton();
		EIL.AddNewLabourButton();
		Thread.sleep(1500);
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
	
	
// Add Product	
	
	@Test (dependsOnMethods= {"AddMaterial", "AddMachinery", "AddLabour"})
	public void AddProduct() throws InterruptedException, AWTException, IOException
	{
		EIP= new ElementsInProductPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Add Product Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIP.ProductsMenuButton();
		EIP.AddNewProductButton();
		Thread.sleep(1500);
		EIP.EnterProductName(ProductName);
		EIP.SelectProductCategory(CategoryName);
		EIP.EnterDescription("Automation Smoke Testing");
		EIP.UploadProductImage(Path);
		EIP.SaveButton();
		Thread.sleep(1250);
		SuccessOrFail("Product");
		Thread.sleep(1500);
		
	// Product Details
		EIP.AddMaterialsButton();
		EIP.SelectMaterialCategory(CategoryName);
		EIP.SelectMaterial(MaterialName);
		Thread.sleep(1500);
		EIP.SaveMateial();
		Thread.sleep(1250);
		SuccessOrFail("Product Material");
		
		EIP.AddMachineryButton();
		EIP.SelectMachineryCategory(CategoryName);
		EIP.SelectMachinery(MachineryName);
		Thread.sleep(1500);
		EIP.SaveMachinery();
		Thread.sleep(1250);
		SuccessOrFail("Product Machinery");
		
		EIP.AddLabourButton();
		EIP.SelectLabourCategory(CategoryName);
		EIP.SelectLabour(LabourName);
		Thread.sleep(1500);
		EIP.SaveLabour();
		Thread.sleep(1250);
		SuccessOrFail("Product Labour");
		
	// Custom Materials, Machineries and Labors
		
		EIP.AddMaterialsButton();
		EIP.EnableCustomItemMaterial();
		Thread.sleep(1000);
		EIP.SelectMaterialCategory(CategoryName);
		EIP.EnterMaterialName("Custom Material"+ generateDummyName());
		EIP.SelectMaterialUnit("Liter");
		EIP.EnterMaterialCost("231");
		EIP.EnterMaterialMarkup("23");
		EIP.EnterMaterialDescription("Custom Material Through Automation");
		EIP.SaveMateial();
		Thread.sleep(1250);
		SuccessOrFail("Custom Material");
		
		EIP.AddMachineryButton();
		EIP.EnableCustomItemMachinery();
		Thread.sleep(1000);
		EIP.SelectMachineryCategory(CategoryName);
		EIP.EnterMachineryName("Custom Machinery"+ generateDummyName());
		EIP.SelectMachineryUnit("Week");
		EIP.EnterMachineryCost("354");
		EIP.EnterMachineryMarkup("45");
		EIP.SaveMachinery();
		Thread.sleep(1250);
		SuccessOrFail("Custom Machinery");
		
		EIP.AddLabourButton();
		EIP.EnableCustomItemLabour();
		Thread.sleep(1000);
		EIP.SelectLabourCategory(CategoryName);
		EIP.EnterLabourName("Custom Labour"+ generateDummyName());
		EIP.SelectLabourUnit("Shift");
		EIP.EnterLabourCost("873");
		EIP.EnterLabourMarkup("28");
		EIP.SaveLabour();
		Thread.sleep(1250);
		SuccessOrFail("Custom Labour");		
	}
	
	
// Add Discount	
	
	@Test (dependsOnMethods= "AddProduct")
	public void AddProductDiscount() throws InterruptedException, IOException
	{
		EIBD= new ElementsInProductBulkDiscountPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Product Discount Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIP.EnableorDisableBulkDiscount("Enable");
		Thread.sleep(1250);
		EIBD.BulkDiscountTab();
		EIBD.AddNewDiscountButton();
		Thread.sleep(1000);
		EIBD.EnterMinimumQuantity("5");
		EIBD.EnterMaximumQuantity("12");
		EIBD.EnterDiscountPercentage("8");
		EIBD.SaveDiscount();
		Thread.sleep(1250);
		SuccessOrFail("Discount");
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
			testcase.log(FAIL, "Failed to add the '"+ TypeOrDetail +". The '"+ EIC.ConfirmationMessage() +"' message is dispalyed.");
			takescreenshot(driver, "Failed to add the '"+ TypeOrDetail);
		}
	}
	
}
