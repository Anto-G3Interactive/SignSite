package products;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInProductPage extends Initialstep
{
	WebDriver driver;
	ExtentTest testcase;
	
 	ElementsInProductPage(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
// Open Products page
    public void ProductDropdownMenu() 
    {
        driver.findElement(By.xpath("//span[text()='Products']")).click();
    }
    public void ProductsMenuButton()
    {
    	driver.findElement(By.xpath("//div[@to='machinery']/..")).click();
    }
    
// Elements of Products Page
    public void SearchField(String SearchValue)
    {
        WebElement e= driver.findElement(By.xpath("//h6/../..//input[@placeholder='Search']"));
        e.click();
        e.sendKeys(Keys.CONTROL, "a");
        e.sendKeys(SearchValue);
    }
    
    public void SelectCategory() throws InterruptedException
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select product category']")).click();
    	Thread.sleep(1000);
    	String category= driver.findElement(By.xpath("(//div[@data-field= 'category'])[2]")).getText();
    	driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ category +"']")).click();
    }
    
    public void SelectStatus()
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Select Status']")).click();
        String status= driver.findElement(By.xpath("(//div[@data-field= 'status'])[2]//button")).getText();
        driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ status +"']")).click();
    }
    
    public void EditProduct()
    {
    	driver.findElement(By.xpath("(//div[@data-rowindex= '0']//button[@type= 'button' and @style])[1]")).click();
    }
    
    public void DeleteProduct() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//div[@data-rowindex= '0']//button[@type= 'button' and @style])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void ChangeProductStatus(String status)
    {
    	WebElement e= driver.findElement(By.xpath("//button[contains (text(), 'Active') or contains(text(), 'Inactive')]"));
    	testcase.log(INFO, "Current status is: "+ e.getText());
    	e.click();
    	testcase.log(INFO, "Status updated to: "+ e.getText());
    }
    
    // Pagination
    public void RowsPerPage(String RPP)
    {
    	driver.findElement(By.xpath("//div[@aria-haspopup= 'listbox']")).click();
    	try
    	{
    		driver.findElement(By.xpath("//li[@role= 'option' and text()= '"+ RPP +"']")).click();
    	}
    	catch (Exception e)
    	{
    		driver.findElement(By.xpath("//li[@role= 'option']"));
    		testcase.log(INFO, "Since the Rows pre page entered is not exist, first option is selected from the dropdown");
    	}
    }
    
    public void NextAndPrevious(String MovePage)
    {
    	try
    	{
	    	if (MovePage.contains("Next"))
	    	{
	    		driver.findElement(By.xpath("//button[@title= 'Go to next page']")).click();
	    	}
	    	else
	    	{
	    		driver.findElement(By.xpath("//button[@title= 'Go to previous page']")).click();
	    	}
    	}
    	catch (Exception e)
    	{
    		testcase.log(INFO, "Number of page is less to check the pagination");
    	}
    }
    
    // Column Chooser
    public void ColumnChooserMenu(String HideOrShow)
    {
    	driver.findElement(By.xpath("(//button[@class= 'btn btn-primary-600']/../..//button)[2]")).click();
    	if(HideOrShow.contains("Hide All"))
    	{
    		driver.findElement(By.xpath("//button[text()= 'Hide All']")).click();
    		driver.findElement(By.xpath("//button[text()= 'Configure Now']")).click();
    	}
    	else
    	{
    		driver.findElement(By.xpath("//button[text()= 'Show All']")).click();
    		driver.findElement(By.xpath("//button[text()= 'Configure Now']")).click();
    	}
    }
    
    public String ConfirmationAlert()
    {
    	return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
    }
    
    public WebElement NewProductPage()
    {
    	return driver.findElement(By.xpath("//h6[text()= 'New Product']"));
    }

// Add New Products Page
    public void AddNewProductButton()
    {
    	driver.findElement(By.xpath("//button[text()= ' Add New Product']")).click();
    }
    
    // Product Info Tab
    public void ProductInfoTab()
    {
    	driver.findElement(By.xpath("//button[text()= 'Product Info']")).click();
    }
    
    public void EnterProductName(String name)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'name']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(name);
    }
    
    public void SelectProductCategory(String Category)
    {
    	driver.findElement(By.xpath("//label[@for= 'category']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ Category +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given category is not available, selecting first option from dropdown");
    	}
    }
    
    public void EnterDescription(String Description)
    {
    	WebElement e= driver.findElement(By.xpath("//textarea[@id= 'description']"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(Description);
    }
    
    public void UploadProductImage(String path) throws InterruptedException, AWTException
    {
    // 1st Method
//    	driver.findElement(By.xpath("//input[@type= 'file']")).sendKeys(path);
	
    // 2nd Method	
    	driver.findElement(By.xpath("//div[@role= 'presentation']")).click();
    	
    	StringSelection selection= new StringSelection(path); // Enter a valid file path
    	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
    }
    
    public void CancelButton()
    {
    	driver.findElement(By.xpath("//button[@type= 'reset']")).click();
    }
    
    public void SaveButton()
    {
    	driver.findElement(By.xpath("//button[@type= 'submit']")).click();
    }
    
    public void EnableorDisableBulkDiscount(String change) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@type= 'checkbox']"));
    	if(change.equals("Disable"))
    	{
    		if(e.isSelected())
    		{
    			e.click();
    			Thread.sleep(1500);
    		}
    	}
    	else
    	{
    		if(!e.isSelected())
    		{
    			e.click();
    			Thread.sleep(1500);
    		}
    		
    	}
    }
    
    // Add New Product category
    public void AddNewCategoryButton()
    {
    	driver.findElement(By.xpath("//label[@for= 'category']/../..//button")).click();
    }
    
    public void EnterNewCategoryName(String ProductCategory)
    {
    	driver.findElement(By.xpath("//input[@id= 'title']")).sendKeys(ProductCategory);
    }
    
    public void CancelCategoryButton()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Category']/../..//button[@type= 'reset']")).click();
    }
    
    public void SaveCategoryButton()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Category']/../..//button[@type= 'submit']")).click();
    }
    
    // Product Details
    public void AddMaterialsButton()
    {
    	driver.findElement(By.xpath("//div[text()= 'materials']/..//button[@type= 'button']")).click();
    }
    
    public void AddMachineryButton()
    {
    	driver.findElement(By.xpath("//div[text()= 'machineries']/..//button[@type= 'button']")).click();
    }
    
    public void AddLabourButton()
    {
    	driver.findElement(By.xpath("//div[text()= 'labours']/..//button[@type= 'button']")).click();
    }
    
//// Add New Material
    public void EnableCustomItemMaterial()
    {
    	driver.findElement(By.xpath("//input[@id= 'custom']")).click();
    }
    
    public void SelectMaterialCategory(String MaterialCategory)
    {
    	driver.findElement(By.xpath("//label[text()= 'Category']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ MaterialCategory +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given material category is not exist, first option is selected from dropdown");
    	}
    }
    
    public void SelectMaterial(String MaterialName)
    {
    	driver.findElement(By.xpath("//label[contains(text(), 'Material')]/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ MaterialName +"']")).click();
    	}
    	catch(Exception e)
    	{
    		try
    		{
	    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
	    		testcase.log(INFO, "Since the given material is not exist, first option is selected from dropdown");
    		}
    		catch(Exception e1)
    		{
    			testcase.log(INFO, "No option exist for the selected category");
    		}
    	}
    }
    
    public void EnterMaterialName(String MaterialName)
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Enter your material name']")).sendKeys(MaterialName);
    }
    
    public void SelectMaterialUnit(String SelectUnit)
    {
    	driver.findElement(By.xpath("//label[text()= 'Unit']/..//input")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ SelectUnit +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given unit is not exist, first option is selected from dropdown");
    	}
    }

    public void EnterMaterialCost(String cost)
    {
    	driver.findElement(By.xpath("//input[@id= 'cost']")).sendKeys(cost);
    }
    
    public void EnterMaterialMarkup(String markup)
    {
    	driver.findElement(By.xpath("//input[@id= 'markup']")).sendKeys(markup);
    }
    
    public void EnterMaterialDescription(String Description)
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Material']/../..//textarea[@id= 'description']")).sendKeys(Description);
    }
    
    public void PerLIUnitEnableorDisable(String setas)
    {
    	WebElement e= driver.findElement(By.xpath("//input[@id= 'is_linear_unit']"));
    	if(setas.equalsIgnoreCase("Enable"))
    	{
    		if(!e.isSelected()) 
    		{
    			e.click();
    		}
    	}
    	else
    	{
    		if(e.isSelected()) 
    		{
    			e.click();
    		}
    	}
    }
    
    public void CancelMateial()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Material']/../..//button[@type= 'reset']")).click();
    }
    
    public void SaveMateial()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Material']/../..//button[@type= 'submit']")).click();
    }

////Add New Machinery    
    public void EnableCustomItemMachinery()
    {
    	driver.findElement(By.xpath("//input[@id= 'custom']")).click();
    }
    
    public void SelectMachineryCategory(String MachineryCategory)
    {
    	driver.findElement(By.xpath("//label[text()= 'Category']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ MachineryCategory +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given Machinery category is not exist, first option is selected from dropdown");
    	}
    }
    
    public void SelectMachinery(String MachineryName)
    {
    	driver.findElement(By.xpath("//label[contains(text(), 'Name')]/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ MachineryName +"']")).click();
    	}
    	catch(Exception e)
    	{
    		try
    		{
	    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
	    		testcase.log(INFO, "Since the given Machinery is not exist, first option is selected from dropdown");
    		}
    		catch(Exception e1)
    		{
    			testcase.log(INFO, "No option exist for selected category");
    		}
    	}
    }
    
    public void EnterMachineryName(String MaterialName)
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Enter your machinery name']")).sendKeys(MaterialName);
    }
    
    public void SelectMachineryUnit(String SelectUnit)
    {
    	driver.findElement(By.xpath("//label[text()= 'Units']/..//input")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ SelectUnit +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given unit is not exist, first option is selected from dropdown");
    	}
    }

    public void EnterMachineryCost(String cost)
    {
    	driver.findElement(By.xpath("//input[@id= 'cost']")).sendKeys(cost);
    }
    
    public void EnterMachineryMarkup(String markup)
    {
    	driver.findElement(By.xpath("//input[@id= 'markup']")).sendKeys(markup);
    }
    
    public void CancelMachinery()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Machinery']/../..//button[@type= 'reset']")).click();
    }
    
    public void SaveMachinery()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Machinery']/../..//button[@type= 'submit']")).click();
    }
    
////Add New Labour 
    public void EnableCustomItemLabour()
    {
    	driver.findElement(By.xpath("//input[@id= 'custom']")).click();
    }
    
    public void SelectLabourCategory(String LabourCategory)
    {
    	driver.findElement(By.xpath("//label[text()= 'Category']/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ LabourCategory +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given Labour category is not exist, first option is selected from dropdown");
    	}
    }
    
    public void SelectLabour(String LabourName)
    {
    	driver.findElement(By.xpath("//label[contains(text(), 'Labour')]/..//div")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ LabourName +"']")).click();
    	}
    	catch(Exception e)
    	{
    		try
    		{
	    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
	    		testcase.log(INFO, "Since the given Labour is not exist, first option is selected from dropdown");
    		}
    		catch(Exception e1)
    		{
    			testcase.log(INFO, "No option exist for the selected category");
    		}
    	}
    }
    
    public void EnterLabourName(String LabourName)
    {
    	driver.findElement(By.xpath("//input[@placeholder= 'Enter your labour name']")).sendKeys(LabourName);
    }
    
    public void SelectLabourUnit(String SelectUnit)
    {
    	driver.findElement(By.xpath("//label[text()= 'Unit']/..//input")).click();
    	try
    	{
    		driver.findElement(By.xpath("//div[@role= 'option' and text()= '"+ SelectUnit +"']")).click();
    	}
    	catch(Exception e)
    	{
    		driver.findElement(By.xpath("//div[@role= 'option']")).click();
    		testcase.log(INFO, "Since the given unit is not exist, first option is selected from dropdown");
    	}
    }

    public void EnterLabourCost(String cost)
    {
    	driver.findElement(By.xpath("//input[@id= 'cost']")).sendKeys(cost);
    }
    
    public void EnterLabourMarkup(String markup)
    {
    	driver.findElement(By.xpath("//input[@id= 'markup']")).sendKeys(markup);
    }
    
    public void CancelLabour()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Labour']/../..//button[@type= 'reset']")).click();
    }
    
    public void SaveLabour()
    {
    	driver.findElement(By.xpath("//p[text()= 'Add New Labour']/../..//button[@type= 'submit']")).click();
    }

// Edit Product Details
    // Material
    public void EditMaterialsButton()
    {
    	driver.findElement(By.xpath("(//div[text()= 'materials']/../../..//following-sibling::tr//button[@type= 'button'])[1]")).click();
    }
    
    public void EditMaterialQuantity(String quantity)
    {
    	WebElement e= driver.findElement(By.xpath("(//input[@type= 'number' and @class= 'form-control'])[1]"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(quantity);
    }
    
    public void EditMaterialLIQuantity(String LIquantity)
    {
    	WebElement e= driver.findElement(By.xpath("(//input[@type= 'number' and @class= 'form-control'])[2]"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(LIquantity);
    }
    
    //Machinery
    public void EditMachineryButton()
    {
    	driver.findElement(By.xpath("(//div[text()= 'machineries']/../../..//following-sibling::tr//button[@type= 'button'])[1]")).click();
    }
    
    public void EditMachineryQuantity(String quantity)
    {
    	WebElement e= driver.findElement(By.xpath("(//input[@type= 'number' and @class= 'form-control'])[1]"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(quantity);
    }
    
    public void EditMachineryLIQuantity(String LIquantity)
    {
    	WebElement e= driver.findElement(By.xpath("(//input[@type= 'number' and @class= 'form-control'])[2]"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(LIquantity);
    }
    
    // Labour
    public void EditLabourButton()
    {
    	driver.findElement(By.xpath("(//div[text()= 'labours']/../../..//following-sibling::tr//button[@type= 'button'])[1]")).click();
    }
    
    public void EditLabourQuantity(String quantity)
    {
    	WebElement e= driver.findElement(By.xpath("(//input[@type= 'number' and @class= 'form-control'])[1]"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(quantity);
    }
    
    public void EditLabourLIQuantity(String LIquantity)
    {
    	WebElement e= driver.findElement(By.xpath("(//input[@type= 'number' and @class= 'form-control'])[2]"));
    	e.click();
    	e.sendKeys(Keys.CONTROL, "a");
    	e.sendKeys(LIquantity);
    }

// Delete Product Details    
    public void DeleteMaterial() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//div[text()= 'materials']/../../..//following-sibling::tr//button[@type= 'button'])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void DeleteMachinery() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//div[text()= 'machineries']/../../..//following-sibling::tr//button[@type= 'button'])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void DeleteLabour() throws InterruptedException
    {
    	driver.findElement(By.xpath("(//div[text()= 'labours']/../../..//following-sibling::tr//button[@type= 'button'])[2]")).click();
    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }

}



















