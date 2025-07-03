package products;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditProducts extends Initialstep
{
	
	String SearchValue= "SatMar";
	String ProductCategory= "DIY"; //Please enter valid category name
	String Description = "123";
	String ProductImage= Paths.get(System.getProperty("user.home"), "Downloads", "Truck.jpg").toString();
	
	@Test
	public void editproducts() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Edit Product funtionality");
		
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
		driver.findElement(By.xpath("//label[text()= 'Product Category']/following-sibling::div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(text(), '"+ ProductCategory +"')]")).click();
		driver.findElement(By.xpath("//label[contains(text(), 'Description')]/following-sibling::textarea")).sendKeys(Description);
		WebElement PImage= driver.findElement(By.xpath("//div[@role= 'presentation']//input"));
		PImage.sendKeys(ProductImage);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(1500);
		WebElement confirmation= driver.findElement(By.xpath("//div[@role= 'status']"));
		if((confirmation.getText()).contains("Product saved successfully"))
		{
			testcase.log(PASS, "Product edited successfully and the '"+ confirmation.getText() +"' message is displayed");
			takescreenshot(driver, "Product Edited");
		}
		else
		{
			testcase.log(FAIL, "Not able to edit a product");
			takescreenshot(driver, "Product does not edited");
		}
	}

}
