package termsandcondition;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class ViewFunctionality extends Initialstep
{

	@Test
	public void viewAllTerms() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the view functionality of Terms & Conditions");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Settings']")).click();
		driver.findElement(By.xpath("//div[text()= 'Terms and Conditions']")).click();
		driver.findElement(By.xpath("//p[text()= 'Invoice Terms & Conditions']/..//button[text()= 'View']")).click();
		Boolean Invoice= driver.findElement(By.xpath("//p[text()= 'Invoice Terms & Conditions']/../../../div[@role= 'dialog']")).isDisplayed();
		Thread.sleep(2000);
		takescreenshot(driver, "Invoice");
		driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
		driver.findElement(By.xpath("//p[text()= 'Purchase Order (PO) Terms & Conditions']/..//button[text()= 'View']")).click();
		Boolean PurchaseOrder= driver.findElement(By.xpath("//p[text()= 'Purchase Order (PO) Terms & Conditions']/../../../div[@role= 'dialog']")).isDisplayed();
		Thread.sleep(2000);
		takescreenshot(driver, "Purchase Order");
		driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
		driver.findElement(By.xpath("//p[text()= 'Quote Terms & Conditions']/..//button[text()= 'View']")).click();
		Boolean Quote= driver.findElement(By.xpath("//p[text()= 'Quote Terms & Conditions']/../../../div[@role= 'dialog']")).isDisplayed();
		Thread.sleep(2000);
		takescreenshot(driver, "Quote");
		driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();
		driver.findElement(By.xpath("//p[text()= 'Sales Order Terms & Conditions']/..//button[text()= 'View']")).click();
		Boolean SalesOrder= driver.findElement(By.xpath("//p[text()= 'Sales Order Terms & Conditions']/../../../div[@role= 'dialog']")).isDisplayed();
		Thread.sleep(2000);
		takescreenshot(driver, "Sales Order");
		driver.findElement(By.xpath("//button[text()= 'Cancel']")).click();

		if(Invoice && PurchaseOrder && Quote && SalesOrder)
		{
			testcase.log(PASS, "The view fuctionality of Terms and Condition are working fine");
		}
		else
		{
			testcase.log(FAIL, "Unable to view the Terms and Conditions");
			takescreenshot(driver, "Unable to view terms and conditions");
		}

	}		

}
