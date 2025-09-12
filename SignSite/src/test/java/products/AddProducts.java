package products;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddProducts extends Initialstep
{	
	String ProductName= "Automation Testing" + generateDummyName();
	String ProductCategory= "paper"; //Please enter valid category name
	String Description= "The new product";
	String ProductImage= "D:\\Images\\Automation.png";

	String NewMaterialCategory= "rubber";
	String NewMaterialName= "eraser";
	String NewMachineryCategory= "repair work";
	String NewMachineryName= "machinery #1";
	String NewLabourCategory= "Cat-5";
	String NewLabourName= "labour 1";
	
	ElementsInProductPage EIP;
	@Test
	public void products() throws IOException, InterruptedException
	{
		EIP= new ElementsInProductPage(driver, testcase);
		
		testcase= extentReport.createTest("Verify the Add Product funtionality");
		testcase.log(INFO, "Navigating to the Product page");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		try
		{
			OpenProductsPage();
			Thread.sleep(1500);
			ProductInfo();
			Thread.sleep(1500);
			BulkDiscount();
			Thread.sleep(1500);
			ProductDetails();	
		}
		catch(Exception e)
		{
			FailedToRunScript(e);
		}
		
	}
	
	public void OpenProductsPage() throws IOException
	{
		testcase= extentReport.createTest("Open the Add Product page");
		
		EIP.ProductDropdownMenu();
		EIP.ProductsMenuButton();
		EIP.AddNewProductButton();
		
		if(EIP.NewProductPage().isDisplayed())
		{
			testcase.log(PASS, "New Product Page is displayed");
			takescreenshot(driver, "New Product Page displayed");
		}
		else
		{
			testcase.log(PASS, "New Product page did not displayed");
			takescreenshot(driver, "New Product Page did not displayed");
		}
	}
	
	public void ProductInfo() throws InterruptedException, AWTException, IOException
	{
		testcase= extentReport.createTest("Add and Save the Product Info");
		
		EIP.EnterProductName(ProductName);
		EIP.SelectProductCategory(ProductCategory);
		EIP.EnterDescription(Description);
		EIP.UploadProductImage(ProductImage);
		EIP.SaveButton();
		Thread.sleep(1500);
		if(EIP.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Product added and the '"+ EIP.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Product Added");
		}
		else
		{
			testcase.log(FAIL, "Not able to add product");
			takescreenshot(driver, "Product not added");
		}
	}
	
	public void BulkDiscount() throws IOException, InterruptedException
	{
		testcase= extentReport.createTest("Enable Bulk Discount Tab");
		
		EIP.EnableorDisableBulkDiscount("Enable");
		
		if(EIP.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Bulk Discount enabled");
			takescreenshot(driver, "Bulk Discount enabled");
		}
		else
		{
			testcase.log(FAIL, "Failed to enable the bulk discount");
			takescreenshot(driver, "Failed to enable the bulk discount");
		}
	}
	
	public void ProductDetails() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Product Details funtionality");
		Thread.sleep(2000);
		
		WebElement e= driver.findElement(By.xpath("(//input[@id= 'unit_cost'])[2]"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", e);
		Thread.sleep(2000);
		AddMaterial();
		Thread.sleep(2000);
		AddMachineries();
		Thread.sleep(2000);
		AddLabour();
	}
	
	public void AddMaterial() throws InterruptedException, IOException
	{
		EIP.AddMaterialsButton();
		EIP.SelectMaterialCategory(NewMaterialCategory);
		EIP.SelectMaterial(NewMaterialName);
		EIP.SaveMateial();
		
		if(EIP.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Material added to the product");
			takescreenshot(driver, "Material added for product");
		}
		else
		{
			testcase.log(FAIL, "Material did not added to the product");
			takescreenshot(driver, "Material did not added");
		}
	}
	
	public void AddMachineries() throws InterruptedException, IOException
	{
		EIP.AddMachineryButton();
		EIP.SelectMachineryCategory(NewMachineryCategory);
		EIP.SelectMachinery(NewMachineryName);
		EIP.SaveMachinery();
		
		if(EIP.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Machinery added to the product");
			takescreenshot(driver, "Machinery added for the product");
		}
		else
		{
			testcase.log(FAIL, "Machinery did not added to the product");
			takescreenshot(driver, "Machinery did not added");
		}
	}
	
	public void AddLabour() throws InterruptedException, IOException
	{
		EIP.AddLabourButton();
		EIP.SelectLabourCategory(NewLabourCategory);
		EIP.SelectLabour(NewLabourName);
		EIP.SaveButton();
		
		if(EIP.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Labour added to the product");
			takescreenshot(driver, "Labour added for the product");
		}
		else
		{
			testcase.log(FAIL, "Labour did not added to the product");
			takescreenshot(driver, "Labour did not added");
		}
		
	}
	
}
