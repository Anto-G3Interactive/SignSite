package accountmanagement;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class MandatoryCheck extends Initialstep
{
	String BusinessEmailAddress= "123";
	String CurrencyPosition= "Select your Currency Position";
	String DecimalSeparator= ".";
	String GroupingSeparator= ",";
	
	@Test
	public void editaccount() throws InterruptedException, AWTException, IOException
	{
		testcase= extentReport.createTest("Verify the Mandatory fields in Account page");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Settings']")).click();
		driver.findElement(By.xpath("//div[text()= 'Account Management']")).click();
		
		WebElement cn= driver.findElement(By.xpath("//input[@id= 'company_name']"));
		cn.click();
		cn.sendKeys(Keys.CONTROL, "a");
		cn.sendKeys(Keys.BACK_SPACE);
		WebElement ws= driver.findElement(By.xpath("//input[@id= 'website']"));
		ws.click();
		ws.sendKeys(Keys.CONTROL, "a");
		ws.sendKeys(Keys.BACK_SPACE);
		WebElement abn= driver.findElement(By.xpath("//input[@id= 'abn']"));
		abn.click();
		abn.sendKeys(Keys.CONTROL, "a");
		abn.sendKeys(Keys.BACK_SPACE);
		WebElement lb= driver.findElement(By.xpath("//input[@id= 'label']"));
		lb.click();
		lb.sendKeys(Keys.CONTROL, "a");
		lb.sendKeys(Keys.BACK_SPACE);
		driver.findElement(By.xpath("//label[text()= 'Country']/following-sibling::div")).click();
		Thread.sleep(1500);
//		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ Country +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'State']/following-sibling::div")).click();
		Thread.sleep(1500);
//		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ State +"']")).click();
		WebElement cy= driver.findElement(By.xpath("//input[@id= 'city']"));
		cy.click();
		cy.sendKeys(Keys.CONTROL, "a");
		cy.sendKeys(Keys.BACK_SPACE);
		WebElement st1= driver.findElement(By.xpath("//input[@id= 'street1']"));
		st1.click();
		st1.sendKeys(Keys.CONTROL, "a");
		st1.sendKeys(Keys.BACK_SPACE);
		WebElement st2= driver.findElement(By.xpath("//input[@id= 'street2']"));
		st2.click();
		st2.sendKeys(Keys.CONTROL, "a");
		st2.sendKeys(Keys.BACK_SPACE);
		WebElement sb= driver.findElement(By.xpath("//input[@id= 'suburb']"));
		sb.click();
		sb.sendKeys(Keys.CONTROL, "a");
		sb.sendKeys(Keys.BACK_SPACE);
		WebElement pc= driver.findElement(By.xpath("//input[@id= 'postcode']"));
		pc.click();
		pc.sendKeys(Keys.CONTROL, "a");
		pc.sendKeys(Keys.BACK_SPACE);
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//button[text()= 'Cancel']")));
		Thread.sleep(1500);
		WebElement cp= driver.findElement(By.xpath("//input[@id= 'company_phone']"));
		cp.click();
		cp.sendKeys(Keys.CONTROL, "a");
		cp.sendKeys(Keys.BACK_SPACE);
		WebElement ce= driver.findElement(By.xpath("//input[@id= 'company_email']"));
		ce.click();
		ce.sendKeys(Keys.CONTROL, "a");
		ce.sendKeys(BusinessEmailAddress);
		driver.findElement(By.xpath("//label[text()= 'Timezone']/following-sibling::div")).click();
		Thread.sleep(1500);
//		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ Timezone +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Currency']/following-sibling::div")).click();
		Thread.sleep(1500);
//		driver.findElement(By.xpath("//div[@class= 'css-1nmdiq5-menu']//div[text()= '"+ Currency +"']")).click();
		WebElement cs= driver.findElement(By.xpath("//input[@id= 'currency_symbol']"));
		cs.click();
		cs.sendKeys(Keys.CONTROL, "a");
		cs.sendKeys(Keys.BACK_SPACE);
		
		WebElement cryp= driver.findElement(By.xpath("//select[@id= 'currency_position']"));				
		js.executeScript("arguments[0].querySelector('option[value=\"\"]').removeAttribute('disabled');", cryp);
		new Select(cryp).selectByVisibleText(CurrencyPosition);
		WebElement ds= driver.findElement(By.xpath("//select[@id= 'decimal_sep']"));
		js.executeScript("arguments[0].querySelector('option[value=\"\"]').removeAttribute('disabled');", ds);
		new Select(ds).selectByVisibleText(DecimalSeparator);
		WebElement gs= driver.findElement(By.xpath("//select[@id= 'grouping_sep']"));
		js.executeScript("arguments[0].querySelector('option[value=\"\"]').removeAttribute('disabled');", gs);
		new Select(gs).selectByVisibleText(GroupingSeparator);
		
		cs.click();
//		driver.findElement(By.xpath("//button[text()= 'Save']")).click();		// Save Button
		
		Thread.sleep(1500);
		
		if((driver.findElement(By.xpath("//label[text()= 'Business Name']/following-sibling::p")).isDisplayed()) &&
		   (driver.findElement(By.xpath("//label[text()= 'City']/following-sibling::p")).isDisplayed()) &&
		   (driver.findElement(By.xpath("//label[text()= 'Street 1']/following-sibling::p")).isDisplayed()) &&
		   (driver.findElement(By.xpath("//label[text()= 'Postcode']/following-sibling::p")).isDisplayed()) &&
		   (driver.findElement(By.xpath("//label[text()= 'Business Email Address']/following-sibling::p")).isDisplayed()) &&
		   (driver.findElement(By.xpath("//label[text()= 'Currency Symbol/Format']/following-sibling::p")).isDisplayed()) &&
		   (driver.findElement(By.xpath("//label[text()= 'Decimal Separator']/following-sibling::p")).isDisplayed()) &&
		   (driver.findElement(By.xpath("//label[text()= 'Grouping Separator']/following-sibling::p")).isDisplayed()))
		{
			testcase.log(PASS, "The validation messages are dispalyed on fields");
			takescreenshot(driver, "Validation Displayed");
		}
		else
		{
			testcase.log(FAIL, "Valiation messages are not dispalyed");
			takescreenshot(driver, "Validation did not dispalyed");
		}
	}
}
