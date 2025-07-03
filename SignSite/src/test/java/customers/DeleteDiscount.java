package customers;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DeleteDiscount extends Initialstep
{
	@Test
	public void AddDiscountWithCategoryName() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify Discount functionality of the Customer by deleting it");
		
		String SearchContent= "WedMar";
		int SerialNoOfDiscount= 1;
		
		driver.findElement(By.xpath("//span[text()= 'Clients']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()= 'Customers']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class= 'icon-field']//input[@type= 'text']")).sendKeys(SearchContent);
		Thread.sleep(1000);
		Editbtn();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()=  'Discount']")).click();
		Thread.sleep(2000);
		int i= SerialNoOfDiscount - 1;
		String No= String.valueOf(i);
		driver.findElement(By.xpath("(//div[@data-rowindex= '" + No + "']//button[@type= 'button'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
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
			takescreenshot(driver, "Not able to delete a discount");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()= 'OK']")).click();
	}
	
	
	public void Editbtn() throws InterruptedException
	{
		WebElement Edit= driver.findElement(By.xpath("(//div[@data-field= 'action']//button[@tabindex= '0'])[1]"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", Edit);
		Thread.sleep(2000);
		Edit.click();
		Thread.sleep(2000);
	}

}
