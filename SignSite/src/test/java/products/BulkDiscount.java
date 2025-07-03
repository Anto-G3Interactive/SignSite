package products;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class BulkDiscount extends Initialstep
{
	String SearchValue= "SatMar";
	String MinimumQuantity1= "6";
	String MaximumQuantity1= "12";
	String Discount1= "12";
	String EditMinimumQuantity= "5";
	String EditMaximumQuantity= "10";
	String EditDiscount= "10";
	
	
	@Test
	public void editproducts() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the BulkDiscount functionality of the Products");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()= 'Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class= 'icon-field']//input[@type= 'text']")).sendKeys(SearchValue);
		Thread.sleep(1500);
		WebElement edit= driver.findElement(By.xpath("(//div[@role= 'gridcell']//button[@tabindex= '0'])[2]"));
		JavascriptExecutor js=  (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", edit);
		Thread.sleep(1500);
		edit.click();
		Thread.sleep(2000);
		WebElement Checkbox= driver.findElement(By.xpath("//input[@id= 'bulk_discount']"));
		if (Checkbox.isSelected()){}
		else{Checkbox.click();}
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("//button[text()= 'Bulk Discount']")).click();
		driver.findElement(By.xpath("//button[text()= ' Add New Discount']")).click();
		driver.findElement(By.xpath("//input[@id= 'discounts[0].minimum']")).sendKeys(MinimumQuantity1);
		driver.findElement(By.xpath("//input[@id= 'discounts[0].maximum']")).sendKeys(MaximumQuantity1);
		driver.findElement(By.xpath("//input[@id= 'discounts[0].discount']")).sendKeys(Discount1);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details saved successfully"))
		{
			testcase.log(PASS, "Bulk Discount added successfully and the '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Bulk Discount added");
		}
		else if(confirmation.contains("Discount % has already been added for these values"))
		{
			testcase.log(INFO, "Bulk Discount already exist in this particular range. So the '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Bulk Discount already exist in this range");
			driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
		}
		else
		{
			testcase.log(FAIL, "Bulk Discount did not added. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Bulk Discount not added");
		}
		Thread.sleep(2000);
		
// Edit Bulk Discount functionality		
		testcase= extentReport.createTest("Verify the Edit BulkDiscount functionality");
		
		driver.findElement(By.xpath("(//div[@data-field= 'action']//button[@tabindex= '0'])[1]")).click();
		WebElement Minimum= driver.findElement(By.xpath("//input[@id= 'minimum']"));
		Minimum.sendKeys(Keys.CONTROL, "a");
		Minimum.sendKeys(Keys.BACK_SPACE);
		Minimum.sendKeys(EditMinimumQuantity);	
		WebElement Maximum= driver.findElement(By.xpath("//input[@id= 'maximum']"));
		Maximum.sendKeys(Keys.CONTROL, "a");
		Maximum.sendKeys(Keys.BACK_SPACE);
		Maximum.sendKeys(EditMaximumQuantity);
		WebElement Discount= driver.findElement(By.xpath("//input[@id= 'discount']"));
		Discount.sendKeys(Keys.CONTROL, "a");
		Discount.sendKeys(Keys.BACK_SPACE);
		Discount.sendKeys(EditDiscount);	
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmationEdit= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(confirmationEdit.contains("Details updated successfully"))
		{
			testcase.log(PASS, "Bulk Discount edited successfully and the '"+ confirmationEdit + "' message is dispalyed");
			takescreenshot(driver, "Bulk Discount added");
		}
		else if(confirmationEdit.contains("Discount % has already been added for these values"))
		{
			testcase.log(INFO, "Bulk Discount already exist in this particular range. So the '"+ confirmationEdit + "' message is dispalyed");
			takescreenshot(driver, "Bulk Discount already exist in this range");
			driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
		}
		else
		{
			testcase.log(FAIL, "Bulk Discount did not edited. The '"+ confirmationEdit + "' message is dispalyed");
			takescreenshot(driver, "Bulk Discount not edited");
		}
	}

}

