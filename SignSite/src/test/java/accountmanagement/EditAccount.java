package accountmanagement;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditAccount extends Initialstep
{
	StringSelection selection= new StringSelection("E:\\Nature.jpg"); // Enter a valid file path
	String BusinessName= "ISH Technologies";
	String BusinessWebsite= "www.ishtechnologies.com.au";
	String AustralianBusinessNumber= "565465465";
	String Label= "TT";
	String Country= "Australia"; // Enter a Valid Country Name
	String State= "Queensland"; // Enter a valid State Name
	String City= "Brisbane";
	String Street1= "8/300";
	String Street2= "";
	String Suburb= "Brisbane";
	String Postcode= "4000";
	String BusinessPhoneNumber= "045655656757";
	String BusinessEmailAddress= "ishtechdeveloper6@gmail.com";
	String Timezone= "Australia/Brisbane"; // Enter a valid Country Name
	String Currency= "Australian Dollar (AUD)";
	String CurrencySymbolOrFormat= "$";
	String CurrencyPosition= "Suffix";
	String DecimalSeparator= ".";
	String GroupingSeparator= ",";
	
	@Test
	public void editaccount() throws InterruptedException, AWTException, IOException
	{
		testcase= extentReport.createTest("Verify the Edit Account functionality");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Settings']")).click();
		driver.findElement(By.xpath("//div[text()= 'Account Management']")).click();
		driver.findElement(By.xpath("//label[@for='imageUpload']")).click();
		Thread.sleep(2000);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		Robot r= new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		WebElement cn= driver.findElement(By.xpath("//input[@id= 'company_name']"));
		cn.click();
		cn.sendKeys(Keys.CONTROL, "a");
		cn.sendKeys(BusinessName);
		WebElement ws= driver.findElement(By.xpath("//input[@id= 'website']"));
		ws.click();
		ws.sendKeys(Keys.CONTROL, "a");
		ws.sendKeys(BusinessWebsite);
		WebElement abn= driver.findElement(By.xpath("//input[@id= 'abn']"));
		abn.click();
		abn.sendKeys(Keys.CONTROL, "a");
		abn.sendKeys(AustralianBusinessNumber);
		WebElement lb= driver.findElement(By.xpath("//input[@id= 'label']"));
		lb.click();
		lb.sendKeys(Keys.CONTROL, "a");
		lb.sendKeys(Label);
		driver.findElement(By.xpath("//label[text()= 'Country']/following-sibling::div")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ Country +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'State']/following-sibling::div")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ State +"']")).click();
		WebElement cy= driver.findElement(By.xpath("//input[@id= 'city']"));
		cy.click();
		cy.sendKeys(Keys.CONTROL, "a");
		cy.sendKeys(City);
		WebElement st1= driver.findElement(By.xpath("//input[@id= 'street1']"));
		st1.click();
		st1.sendKeys(Keys.CONTROL, "a");
		st1.sendKeys(Street1);
		WebElement st2= driver.findElement(By.xpath("//input[@id= 'street2']"));
		st2.click();
		st2.sendKeys(Keys.CONTROL, "a");
		st2.sendKeys(Street2);
		WebElement sb= driver.findElement(By.xpath("//input[@id= 'suburb']"));
		sb.click();
		sb.sendKeys(Keys.CONTROL, "a");
		sb.sendKeys(Suburb);
		WebElement pc= driver.findElement(By.xpath("//input[@id= 'postcode']"));
		pc.click();
		pc.sendKeys(Keys.CONTROL, "a");
		pc.sendKeys(Postcode);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//button[text()= 'Cancel']")));
		Thread.sleep(1500);
		WebElement cp= driver.findElement(By.xpath("//input[@id= 'company_phone']"));
		cp.click();
		cp.sendKeys(Keys.CONTROL, "a");
		cp.sendKeys(BusinessPhoneNumber);
		WebElement ce= driver.findElement(By.xpath("//input[@id= 'company_email']"));
		ce.click();
		ce.sendKeys(Keys.CONTROL, "a");
		ce.sendKeys(BusinessEmailAddress);
		driver.findElement(By.xpath("//label[text()= 'Timezone']/following-sibling::div")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ Timezone +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Currency']/following-sibling::div")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ Currency +"']")).click();
		WebElement cs= driver.findElement(By.xpath("//input[@id= 'currency_symbol']"));
		cs.click();
		cs.sendKeys(Keys.CONTROL, "a");
		cs.sendKeys(CurrencySymbolOrFormat);
		new Select(driver.findElement(By.xpath("//select[@id= 'currency_position']"))).selectByVisibleText(CurrencyPosition);
		new Select(driver.findElement(By.xpath("//select[@id= 'decimal_sep']"))).selectByVisibleText(DecimalSeparator);
		new Select(driver.findElement(By.xpath("//select[@id= 'grouping_sep']"))).selectByVisibleText(GroupingSeparator);
		
//		driver.findElement(By.xpath("//button[text()= 'Save']")).click();		// Save Button
		
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details updated successfully"))
		{
			testcase.log(PASS, "Account updated successfully and the '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Account Updated");
		}
		else
		{
			testcase.log(FAIL, "Account did not updated. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Account did not updated");
		}
	}
}
