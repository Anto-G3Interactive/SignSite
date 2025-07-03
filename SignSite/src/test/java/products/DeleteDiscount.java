package products;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DeleteDiscount extends Initialstep
{
	
	String SearchValue= "SatMar";
	int SlNo= 1; // Serial No of Discount that needs to be deleted
	
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
		Thread.sleep(1500);
		int i= SlNo*2;
		String IndexofDelete= String.valueOf(i);
		driver.findElement(By.xpath("(//div[@data-field= 'action']//button[@tabindex= '0'])["+ IndexofDelete +"]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[text()=  'Yes, delete it!']")).click();
		Thread.sleep(2000);
		WebElement Deleted= driver.findElement(By.xpath("//div[@id= 'swal2-html-container']"));
		if(Deleted.isDisplayed())
		{
			testcase.log(PASS, "The Discount deleted succesfully and the '" + Deleted.getText() + "' confirmation message is displayed");
			takescreenshot(driver, "Discount Deleted");
		}
		else
		{
			testcase.log(FAIL, "Discount did not deleted");
			takescreenshot(driver, "Not able to delete a Discount");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()= 'OK']")).click();
		
	}
}
