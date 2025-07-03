package customers;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import basepack.Initialstep;

public class AddDiscount extends Initialstep
{
	
// To add Discount, by selecting the Category Name
	@Test (priority= 1)
	public void AddDiscountWithCategoryName() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify Discount functionality of the Customer");
		testcase.log(Status.INFO, "Adding Discount using the Category Name");
		
		String SearchContent= "Automation";
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
		driver.findElement(By.xpath("//button[text()=  ' Add New Discount']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		Thread.sleep(1000);
		
		List<WebElement> elements = driver.findElements(By.tagName("(//div[@class= 'css-10wo9uf-option'])"));
		for (WebElement element : elements) 
		{
			System.out.println(element.getText());
		}
				
		driver.findElement(By.xpath("//div[@role= 'listbox']//div[contains(text(), '" + CategoryName +"')]")).click();
		driver.findElement(By.xpath("//input[@id= 'discount']")).sendKeys(Discount);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(2000);
		WebElement Confirmation= driver.findElement(By.xpath("//div[@role= 'status']"));
		if ((Confirmation.getText().contains("Details saved successfully.")))
		{
			testcase.log(PASS, "A New Discount is added successfully and the '" + Confirmation.getText() + "' message is displayed");
			takescreenshot(driver, "New Discount");
		}
		else
		{
			if(Confirmation.isDisplayed())
			{
				testcase.log(FAIL, "Not able to add a New Discount. The '" + Confirmation.getText() + "' Message is displayed");
				takescreenshot(driver, "Not able to add a New Discount");
			}
			else
			{
				testcase.log(FAIL, "Not able to add a New Discount");
				takescreenshot(driver, "Not able to add a New Discount");
			}
		}
			
	}
	
	
// To add Discount, by selecting the Category Index
	@Test (priority=2)
	public void AddDiscountWithCategoryIndex() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify Discount functionality of the Customer by adding discount");
		testcase.log(Status.INFO, "Adding Discount using the Category Index");
		
		String SearchContent= "WedMar";
		String Discount= "12.34";
		String CategoryIndex= "[1]"; // Enter valid index of the Category needs to be selected from drop-down. Index will starts from [1]
		
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
		driver.findElement(By.xpath("//button[text()=  ' Add New Discount']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class= 'css-10wo9uf-option'])" + CategoryIndex)).click();
		driver.findElement(By.xpath("//input[@id= 'discount']")).sendKeys(Discount);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(2000);
		WebElement Confirmation= driver.findElement(By.xpath("//div[@role= 'status']"));
		if ((Confirmation.getText().contains("Details Saved successfully")))
		{
			testcase.log(PASS, "A New Discount is added successfully and the '" + Confirmation.getText() + "' message is displayed");
			takescreenshot(driver, "New Discount");
		}
		else
		{
			if(Confirmation.isDisplayed())
			{
				testcase.log(FAIL, "Not able to add a New Discount. The '" + Confirmation.getText() + "' Message is displayed");
				takescreenshot(driver, "Not able to add a New Discount");
			}
			else
			{
				testcase.log(FAIL, "Not able to add a New Discount");
				takescreenshot(driver, "Not able to add a New Discount");
			}
		}
		
		
		
	}
	

	
	public void Editbtn() throws InterruptedException
	{
		WebElement Edit= driver.findElement(By.xpath("(//div[@data-field= 'action']//button[@tabindex= '0'])[1]"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", Edit);
		Thread.sleep(3000);
		Edit.click();
	}
	
	
}

