package emailTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInEmailTemplate extends Initialstep
{
	
	public ElementsInEmailTemplate(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
// Open Email Template Page
	
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
	
	
// Templates Page	
	
    public String ConfirmationMessage() throws InterruptedException
    {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role= 'status']")));
    	return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
    }
    
    public void SearchForTheEmailTemplates(String SearchValue) throws InterruptedException
    {
    	WebElement e= driver.findElement(By.xpath("//input[@name='#0']"));
        ClearAndEnterValue(e, SearchValue);
    }
    
    public void EditButton(int index)
    {
    	driver.findElement(By.xpath("(//button[contains(@class,'css-1ia3zz3')])["+ index +"]")).click();
    }
    
    public void CopyButton(int index) throws InterruptedException
    {
    	driver.findElement(By.xpath("(//button[contains(@class,'css-1tkzdep')])["+ index +"]")).click();
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//button[text()= 'Yes, copy it!']")).click();
    }
    
    public void CopiedMessageAndClickOnOkButton(String Data) throws IOException
    {
    	String s= driver.findElement(By.xpath("(//div[@role= 'dialog']//h2/following-sibling::div)[1]")).getText();
    	driver.findElement(By.xpath("//button[text()= 'OK']")).click();
    	if(s.toLowerCase().contains("success"))
    	{
    		testcase.log(PASS, Data +" "+ s);
    	}
    	else
    	{
    		testcase.log(FAIL, "Failed to copy the "+ Data +". '"+ s + "' message is displayed.");
    	}
    }
    
    public void DeleteButton(int index) throws InterruptedException
    {
    	driver.findElement(By.xpath("(//button[contains(@class, 'css-1ffkwrf')])["+ index +"]")).click();
    	Thread.sleep(1500);
    	driver.findElement(By.xpath("//button[text()= 'Yes, delete it!']")).click();
    }
    
    public void DeletedMessageAndClickOnOkButton(String Data) throws IOException
    {
   	   	String s= driver.findElement(By.xpath("(//div[@role= 'dialog']//h2/following-sibling::div)[1]")).getText();
	   	driver.findElement(By.xpath("//button[text()= 'OK']")).click();
	    if(s.toLowerCase().contains("success"))
	    {
	    	testcase.log(PASS, Data +" deleted and the '"+ s +"' message is displayed");
    	}
    	else
    	{
    		testcase.log(FAIL, "Failed To Delete");
    	}
		driver.findElement(By.xpath("//button[text()= 'OK']")).click();
    }
	
	
// Add New Template	
	
	public void AddNewTemplateButton()
	{
		driver.findElement(By.xpath("//button[@class= 'btn btn-primary-600']")).click();;
	}
	
	public void EnterTemplateName(String Name) throws InterruptedException
	{
		WebElement e= driver.findElement(By.xpath("//input[@name= 'name']"));
		ClearAndEnterValue(e, Name);
	}
	
	public void SelectTemplateCategory(String Category)
	{
		driver.findElement(By.xpath("//label[@for= 'category_id']/..//input")).click();
		SelectDropdownValue(driver, testcase, Category);
	}
	
	public void EnterSubject(String Subject) throws InterruptedException
	{
		WebElement e= driver.findElement(By.xpath("//input[@id= 'subject']"));
		ClearAndEnterValue(e, Subject);
	}
	
	public void EnterMessage(String Message)
	{
		WebElement e= driver.findElement(By.xpath("//div[contains(@class, 'ql-editor')]"));
		e.click();
		e.sendKeys(Keys.END);
		e.sendKeys(Message);
	}
	
	public void ClearMessageField() throws InterruptedException
	{
		WebElement e= driver.findElement(By.xpath("//div[contains(@class, 'ql-editor')]"));
		ClearAndEnterValue(e, "");
	}
	
	public void SelectVariables(String TemplateCategory) throws InterruptedException
	{
		WebElement TextField= driver.findElement(By.xpath("//div[contains(@class, 'ql-editor')]"));
		List<WebElement> elements= driver.findElements(By.xpath("//button[@class='badge']"));
		testcase.log(INFO, "The selected variables are listed below");
		for(WebElement element : elements)
		{
			TextField.click();
			TextField.sendKeys(Keys.END);
			Thread.sleep(500);
			TextField.sendKeys(Keys.ENTER);
			element.click();
			testcase.log(INFO, element.getText());
		}
	}
	
	public void CancelButton()
	{
		driver.findElement(By.xpath("//button[contains(text(), 'Cancel')]")).click();
	}
	
	public void SaveButton()
	{
		driver.findElement(By.xpath("//button[contains(text(), 'Save')]")).click();
	}
	
	
	// Editor Elements
	
			public void SelectFontStyle(String FontStyle)
			{
				try
				{
					driver.findElement(By.xpath("//span[@role= 'button' and @data-value= '"+ FontStyle +"']")).click();
				}
				catch(Exception e)
		    	{
			    	driver.findElement(By.xpath("//span[@role= 'button' and @data-value= 'serif']")).click();
			    	testcase.log(INFO, "Since the given option '"+ FontStyle +"' does not exist, selected the first option from dropdown");
		    	}
			}
			
			public void SelectFontSize(String FontSize)
			{
				try
				{
					driver.findElement(By.xpath("//span[@role= 'button' and @data-value= '"+ FontSize +"']")).click();
				}
				catch(Exception e)
		    	{
			    	driver.findElement(By.xpath("//span[@role= 'button' and @data-value= 'small']")).click();
			    	testcase.log(INFO, "Since the given option '"+ FontSize +"' does not exist, selected the first option from dropdown");
		    	}
			}
			
			public void ClickOnBoldButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-bold')]")).click();
			}
			
			public void ClickOnItalicButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-italic')]")).click();
			}
			
			public void ClickOnUnderlineButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-underline')]")).click();
			}
			
			public void ClickOnStrikeOutButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-strike')]")).click();
			}
			
			public void ClickAndSelectTextColor(String Color)
			{
				driver.findElement(By.xpath("//span[contains(@class, 'ql-color ql-picker')]")).click();
				driver.findElement(By.xpath("")).click();
			}
			
			public void ClickAndSelectBackgroundColor(String Color)
			{
				driver.findElement(By.xpath("//span[contains(@class, 'ql-background ql-picker')]")).click();
				driver.findElement(By.xpath("")).click();
			}
			
			public void ClickOnSubscriptButton()
			{
				driver.findElement(By.xpath("//button[@value= 'sub']")).click();
			}
			
			public void ClickOnSuperscriptButton()
			{
				driver.findElement(By.xpath("//button[@value= 'super']")).click();
			}
			
			public void ClickOnHeader1Or2Button(int value)
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-header') and @value= '"+ value +"']")).click();
			}
			
			public void ClickOnDoubleQuoteButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-blockquote')]")).click();
			}
			
			public void ClickOnCodeBlockButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-code-block')]")).click();
			}
			
			public void ClickOnPointNumberButton()
			{
				driver.findElement(By.xpath("//button[@value= 'ordered']")).click();
			}
			
			public void ClickOnPointsButton()
			{
				driver.findElement(By.xpath("//button[@value= 'bullet']")).click();
			}
			
			public void ClickOnRightOrLeftIndentButton(int Value) // Value Should be -1 Or 1
			{
				driver.findElement(By.xpath("//button[@class= 'ql-indent' and @value= '"+ Value  +"']")).click();
			}
			
			public void ClickOnChangeDirectionOfTextButton()
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-direction')]")).click();
			}
			
			public void ClickAndChangeTheAlignmentButton(String TextPostition) // Enter center or right or justify
			{
				driver.findElement(By.xpath("//span[contains(@class, 'ql-align ql-picker')]")).click();
				driver.findElement(By.xpath("//span[@data-value= '"+ TextPostition +"']")).click();
			}
			
			public void ClickAndInsertLink(String Link)
			{
				driver.findElement(By.xpath("//button[contains(@class, 'ql-link')]")).click();
				driver.findElement(By.xpath("//a[contains(@class, 'ql-preview')]/..//input")).sendKeys(Link);
				driver.findElement(By.xpath("//a[contains(@class, 'ql-action')]")).click();
			}
}









