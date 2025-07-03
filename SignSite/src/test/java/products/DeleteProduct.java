package products;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class DeleteProduct extends Initialstep
{
	
	String SearchValue= "TueApr"; // Which product should be deleted
	
	@Test
	public void editproducts() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Delete Product funtionality");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[text()= 'Products']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class= 'icon-field']//input[@type= 'text']")).sendKeys(SearchValue);
		Thread.sleep(1500);
		WebElement Delete= driver.findElement(By.xpath("(//div[@role= 'gridcell']//button[@tabindex= '0'])[3]"));
		JavascriptExecutor js=  (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth", Delete);
		Thread.sleep(1500);
		Delete.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()=  'Yes, delete it!']")).click();
		Thread.sleep(2000);
		WebElement Deleted= driver.findElement(By.xpath("//div[@id= 'swal2-html-container']"));
		if(Deleted.isDisplayed())
		{
			testcase.log(PASS, "The Product deleted succesfully and the '" + Deleted.getText() + "' confirmation message is displayed");
			takescreenshot(driver, "Product Deleted");
		}
		else
		{
			testcase.log(FAIL, "Product did not deleted");
			takescreenshot(driver, "Not able to delete a Product");
		}
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()= 'OK']")).click();
	}

}
