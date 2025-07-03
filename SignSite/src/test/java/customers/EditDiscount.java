package customers;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditDiscount extends Initialstep
{
	@Test
	public void AddDiscountWithCategoryName() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify Discount functionality of the Customer by editing it");
		
		String SearchContent= "WedMar";
		int SerialNoOfDiscount= 1;
		String Discount= "12.34";
		String CategoryName= "Tester"; // Enter valid Category Name
		
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
		driver.findElement(By.xpath("//div[@data-rowindex= '" + No + "']//button[@type= 'button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@role= 'listbox']//div[contains(text(), '" + CategoryName +"')]")).click();
		
		WebElement percentage= driver.findElement(By.xpath("//input[@id= 'discount']"));
		percentage.click();
		Thread.sleep(1000);
		percentage.sendKeys(Keys.CONTROL, "a");
		Thread.sleep(1000);
		percentage.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		percentage.sendKeys(Discount);
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(2000);
		WebElement Confirmation= driver.findElement(By.xpath("//div[@role= 'status']"));
		if ((Confirmation.getText().contains("Details saved successfully.")))
		{
			testcase.log(PASS, "Discount edited successfully and the '" + Confirmation.getText() + "' message is displayed");
			takescreenshot(driver, "Edited Discount");
		}
		else
		{
				testcase.log(FAIL, "Not able to edit a Discount");
				takescreenshot(driver, "Not able to edit a Discount");
		}
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
