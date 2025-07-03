package termsandcondition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditQuotes extends Initialstep
{
 
	String Quotes1= "Acceptance of Purchase Order";
	String QuotesLink= "http://13.210.33.250/www.loremipsum.com";
	String Quotes2= "The Next Line";
	StringSelection Imagepath= new StringSelection("E:\\Nature.jpg");
	
	@Test
	public void editQuotes() throws InterruptedException, AWTException, IOException
	{
		testcase= extentReport.createTest("Verify the Edit Invoice functionality");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Settings']")).click();
		driver.findElement(By.xpath("//div[text()= 'Terms and Conditions']")).click();
		driver.findElement(By.xpath("(//p[text()= 'Quote Terms & Conditions']/..//button[@type= 'button'])[1]")).click();
		WebElement textarea= driver.findElement(By.xpath("//div[@class= 'ql-editor']"));
		textarea.click();
		textarea.sendKeys(Keys.CONTROL, "a");
		textarea.sendKeys(Quotes1);
		Thread.sleep(2000);
		textarea.sendKeys(Keys.CONTROL, "a");
		driver.findElement(By.xpath("//button[@class= 'ql-link']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class= 'ql-tooltip ql-editing']//input[@type= 'text']")).sendKeys(QuotesLink);
		Thread.sleep(1500);
		driver.findElement(By.xpath("//a[@class= 'ql-action']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[contains(@class, 'ql-bold')]")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span[@data-value= 'large']/../..")).click();
		driver.findElement(By.xpath("//span[@data-value= 'small']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span[@class= 'ql-background ql-picker ql-color-picker']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//select/following-sibling::span//span[@data-value= '#ffff00']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("(//button[@class= 'ql-header'])[2]")).click();
		Thread.sleep(1500);
		textarea.click();
		textarea.sendKeys(Keys.ENTER);
		Thread.sleep(1500);
		textarea.sendKeys(Quotes2);
		Thread.sleep(1500);
		textarea.sendKeys(Keys.CONTROL, "a");
		driver.findElement(By.xpath("(//button[contains(@class, 'ql-list')])[1]")).click();
		Thread.sleep(1500);
		textarea.click();
		driver.findElement(By.xpath("//button[@class= 'ql-image']")).click();
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(Imagepath, null);
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details updated successfully"))
		{
			testcase.log(PASS, "Quotes Edited successfully and the '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Quotes edited");
		}
		else
		{
			testcase.log(FAIL, "Quotes did not edited. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Quotes did not edited");
		}
	}
}
