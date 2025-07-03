package products;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditDetails extends Initialstep
{
	
	String SearchValue= "SatMar";
	String MaterialQuantity= "2";
	String MaterialLIQunatity= "3";
	String MachineryQuantity= "2";
	String MachineryLIQunatity= "3";
	String LabourQuantity= "2";
	String LabourLIQunatity= "3";
	
	@Test
	public void editproducts() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the EditDetails funtionality of the Product");
		
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
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//div[@class= 'col-4 text-start']")));

		
		String EditDetailsbtn= "MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary css-1vmnopk";
// Edit Materials
		testcase.log(INFO, "Edit Materials");
		
		driver.findElement(By.xpath("(//button[@class= '"+ EditDetailsbtn +"'])[1]" )).click();
		Thread.sleep(1500);
		EditMaterial();
		Thread.sleep(1500);
		String s= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(s.contains("Details saved successfully"))
		{
			testcase.log(PASS, "Material Edited Successfully");
			takescreenshot(driver, "Material Edited");
		}
		else
		{
			testcase.log(FAIL, "Material did not edited");
			takescreenshot(driver, "Material did not edited");
		}
		Thread.sleep(2000);
		
		
// Edit Machineries
		testcase.log(INFO, "Edit Machineries");		
		driver.findElement(By.xpath("(//button[@class= '"+ EditDetailsbtn +"'])[2]" )).click();
		Thread.sleep(1500);
		EditMachinery();
		Thread.sleep(1500);
		String s2= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(s2.contains("Details saved successfully"))
		{
			testcase.log(PASS, "Machinery Edited Successfully");
			takescreenshot(driver, "Machinery Edited");
		}
		else
		{
			testcase.log(FAIL, "Machinery did not edited");
			takescreenshot(driver, "Machinery did not edited");
		}
		Thread.sleep(2000);
		
// Edit Labour		
		testcase.log(INFO, "Edit Labour");
		driver.findElement(By.xpath("(//button[@class= '"+ EditDetailsbtn +"'])[3]" )).click();
		Thread.sleep(1500);
		EditLabour();
		Thread.sleep(1500);
		String s3= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(s3.contains("Details saved successfully"))
		{
			testcase.log(PASS, "Labour Edited Successfully");
			takescreenshot(driver, "Labour Edited");
		}
		else
		{
			testcase.log(FAIL, "Labour did not edited");
			takescreenshot(driver, "Labour did not edited");
		}
		Thread.sleep(2000);
		
	}
	
	public void EditMaterial() throws InterruptedException
	{
		WebElement Quantity= driver.findElement(By.xpath("(//input[@class= 'form-control'])[1]"));
		Quantity.click();
		Quantity.sendKeys(Keys.CONTROL ,"a");
		Thread.sleep(500);
		Quantity.sendKeys(Keys.BACK_SPACE);
		Quantity.sendKeys(MaterialQuantity);
		
		WebElement LIQuantity= driver.findElement(By.xpath("(//input[@class= 'form-control'])[2]"));
		LIQuantity.click();
		LIQuantity.sendKeys(Keys.CONTROL ,"a");
		Thread.sleep(500);
		LIQuantity.sendKeys(Keys.BACK_SPACE);
		LIQuantity.sendKeys(MaterialLIQunatity);
		
		driver.findElement(By.xpath("//button[@class= 'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-qatwap']")).click();
	}
	
	public void EditMachinery() throws InterruptedException
	{
		WebElement Quantity= driver.findElement(By.xpath("(//input[@class= 'form-control'])[1]"));
		Quantity.click();
		Quantity.sendKeys(Keys.CONTROL ,"a");
		Thread.sleep(500);
		Quantity.sendKeys(Keys.BACK_SPACE);
		Quantity.sendKeys(MachineryQuantity);
		
		WebElement LIQuantity= driver.findElement(By.xpath("(//input[@class= 'form-control'])[2]"));
		LIQuantity.click();
		LIQuantity.sendKeys(Keys.CONTROL ,"a");
		Thread.sleep(500);
		LIQuantity.sendKeys(Keys.BACK_SPACE);
		LIQuantity.sendKeys(MachineryLIQunatity);
		
		driver.findElement(By.xpath("//button[@class= 'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-qatwap']")).click();
	}
	
	public void EditLabour() throws InterruptedException
	{
		WebElement Quantity= driver.findElement(By.xpath("(//input[@class= 'form-control'])[1]"));
		Quantity.click();
		Quantity.sendKeys(Keys.CONTROL ,"a");
		Thread.sleep(500);
		Quantity.sendKeys(Keys.BACK_SPACE);
		Quantity.sendKeys(LabourQuantity);
		
		WebElement LIQuantity= driver.findElement(By.xpath("(//input[@class= 'form-control'])[2]"));
		LIQuantity.click();
		LIQuantity.sendKeys(Keys.CONTROL ,"a");
		Thread.sleep(500);
		LIQuantity.sendKeys(Keys.BACK_SPACE);
		LIQuantity.sendKeys(LabourLIQunatity);
		
		driver.findElement(By.xpath("//button[@class= 'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-qatwap']")).click();
	}

}