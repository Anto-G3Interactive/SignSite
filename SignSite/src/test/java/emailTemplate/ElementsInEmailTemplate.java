package emailTemplate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInEmailTemplate extends Initialstep
{
	ElementsInEmailTemplate(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
	public void ClickOnSettingMenu()
	{
		driver.findElement(By.xpath("//span[text()= 'Settings']/..")).click();
	}
	
	public void ClickOnEmailTemplateMenu()
	{
		try
		{
			driver.findElement(By.xpath("//div[text()= 'Email Templates']/..")).click();
		}
		catch(Exception e)
		{
			ClickOnSettingMenu();
			driver.findElement(By.xpath("//div[text()= 'Email Templates']/..")).click();
		}
	}
	
	
	
}
