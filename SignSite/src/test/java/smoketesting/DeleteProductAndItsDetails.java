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

public class DeleteProductAndItsDetails extends Initialstep
{
	ElementsInCategoryPage EIC;
	ElementsInMaterialsPage EIMat;
	ElementsInMachineryPage EIMach;
	ElementsInLabourPage EIL;
	ElementsInProductPage EIP;
	ElementsInProductBulkDiscountPage EIBD;
	
	String Category= "To Be Deleted"+ generateDummyName();
	String Material= "To Be Deleted"+ generateDummyName();
	String Machinery= "To Be Deleted"+ generateDummyName();
	String Labour= "To Be Deleted"+ generateDummyName();
	String Product= "To Be Deleted"+ generateDummyName();

	
// Category	
	
	@Test (priority= 1)
	public void DeleteCategories() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Delete Category Functionality");
		testcase.log(INFO, "Creating Categories and deleting it");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		EIC= new ElementsInCategoryPage(driver, testcase);
		
		EIC.ProductDropdownMenu();
		EIC.CategoryMenuButton();
		Thread.sleep(1000);
		AddCategory("Material");
		AddCategory("Machinery");
		AddCategory("Labour");
		AddCategory("Product");
		for (int i = 0; i < 4; i++) 
		{
			EIC.Searchfield(Category);
			Thread.sleep(1000);
			EIC.DeleteButton(1);
			Thread.sleep(1500);
			takescreenshot(driver, "Category Deleted");
			Thread.sleep(1500);
			EIC.DeletedMessageAndClickOnOkButton("Category ");
		}
	}
	public void AddCategory(String Type) throws InterruptedException
	{
		EIC.AddNewCategoryButton();
		EIC.EnterCategoryName(Category);
		EIC.SelectType(Type);
		EIC.SaveButton();
	}
	
	
// Material	
	
	@Test (priority= 2)
	public void DeleteMaterial() throws InterruptedException, IOException
	{
		EIMat= new ElementsInMaterialsPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Delete Material Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIMat.MaterialMenuButton();
		AddMaterial();
		Thread.sleep(1000);
		EIMat.SearchField(Material);
		Thread.sleep(1500);
		EIMat.DeleteButton(1);
		Thread.sleep(1500);
		takescreenshot(driver, "Material Deleted");
		Thread.sleep(1000);
		EIMat.DeletedMessageAndClickOnOkButton("Material ");
	}
	public void AddMaterial() throws InterruptedException, IOException
	{
		EIMat.AddNewMaterial();
		Thread.sleep(1000);
		EIMat.SelectMaterialCategory("Automation");
		EIMat.EnterMaterialName(Material);
		EIMat.SelectUnit("Ton");
		EIMat.EnterCost("324");
		EIMat.EnterMarkup("16");
		EIMat.EnterDescription("Smoke Testing using Automation Tool");
		EIMat.EnterPODescription("PO Description for Automation Testing");
		EIMat.PerLIUnit();
		Thread.sleep(1500);
		EIMat.Save();
	}
	

// Machinery
	
	@Test (priority= 3)
	public void DeleteMachinery() throws InterruptedException, IOException
	{
		EIMach= new ElementsInMachineryPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Delete Machinery Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIMach.MachineryMenuButton();
		AddMachinery();
		Thread.sleep(1000);
		EIMach.SearchField(Machinery);
		Thread.sleep(1500);
		EIMach.DeleteButton(1);
		Thread.sleep(1500);
		takescreenshot(driver, "Machinery Deleted");
		Thread.sleep(1000);
		EIMach.DeletedMessageAndClickOnOkButton("Machinery ");
	}
	public void AddMachinery() throws InterruptedException, IOException
	{
		EIMach.AddNewMachineryButton();
		Thread.sleep(1000);
		EIMach.SelectMachineryCategory("Automation");
		EIMach.EnterMachineryName(Machinery);
		EIMach.SelectUnit("Day");
		EIMach.EnterCost("156");
		EIMach.EnterMarkup("32");
		Thread.sleep(1500);
		EIMach.Save();
	}
	
	
// Labor	
	
	@Test (priority= 4)
	public void DeleteLabour() throws InterruptedException, IOException
	{
		EIL= new ElementsInLabourPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Delete Labour Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIL.LabourMenuButton();
		AddLabour();
		Thread.sleep(1000);
		EIL.SearchField(Labour);
		Thread.sleep(1500);
		EIL.DeleteButton(1);
		Thread.sleep(1500);
		takescreenshot(driver, "Labour Deleted");
		Thread.sleep(1000);
		EIL.DeletedMessageAndClickOnOkButton("Labour ");
	}
	public void AddLabour() throws InterruptedException
	{
		EIL.AddNewLabourButton();
		Thread.sleep(1000);
		EIL.SelectLabourCategory("Automation");
		EIL.EnterLabourName(Labour);
		EIL.SelectUnit("Shift");
		EIL.EnterCost("856");
		EIL.EnterMarkup("12");
		Thread.sleep(1500);
		EIL.Save();
	}
	
	
// Product Details
	
	@Test (priority= 5)
	public void DeleteProductDetails() throws InterruptedException, AWTException, IOException
	{
		EIP= new ElementsInProductPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Delete Product Details Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIP.ProductsMenuButton();
		AddProductandItsDetails();
		EIP.DeleteMaterial();
		Thread.sleep(1500);
		takescreenshot(driver, "Mateial Deleted from Product");
		Thread.sleep(1000);
		EIP.DeletedMessageAndClickOnOkButton("Material ");
		Thread.sleep(1500);
		EIP.DeleteMachinery();
		Thread.sleep(1500);
		takescreenshot(driver, "Machinery Deleted from Product");
		Thread.sleep(1000);
		EIP.DeletedMessageAndClickOnOkButton("Machinery ");
		Thread.sleep(1500);
		EIP.DeleteLabour();
		Thread.sleep(1500);
		takescreenshot(driver, "Labour Deleted from Product");
		Thread.sleep(1000);
		EIP.DeletedMessageAndClickOnOkButton("Labour ");
	}
	
	
// Discount
	
	@Test (dependsOnMethods= "DeleteProductDetails")
	public void DeleteDicounts() throws InterruptedException, IOException
	{
		EIBD= new ElementsInProductBulkDiscountPage(driver, testcase);
		testcase= extentReport.createTest("To Verify the Product Discount Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		EIP.EnableorDisableBulkDiscount("Enable");
		EIBD.BulkDiscountTab();
		AddProductDiscount();
		EIBD.DeleteDiscountButton();
		Thread.sleep(1500);
		takescreenshot(driver, "Discount Deleted from Product");
		Thread.sleep(1500);
		EIBD.DeletedMessageAndClickOnOkButton("Discount ");
	}
	

// Product
	
	@Test (priority= 7)
	public void DeleteProduct() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Delete Product Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		
		EIP.ProductsMenuButton();
		EIP.SearchField(Product);
		Thread.sleep(1500);
		EIP.DeleteProduct(1);
		Thread.sleep(1500);
		takescreenshot(driver, "Product Deleted");
		Thread.sleep(1500);
		EIP.DeletedMessageAndClickOnOkButton("Product ");
	}
	
	public void AddProductandItsDetails() throws InterruptedException, AWTException
	{
		EIP.AddNewProductButton();
		Thread.sleep(1500);
		EIP.EnterProductName(Product);
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
}
