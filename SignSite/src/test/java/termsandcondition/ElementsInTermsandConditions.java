package termsandcondition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import basepack.Initialstep;

public class ElementsInTermsandConditions extends Initialstep
{
	
	public ElementsInTermsandConditions(WebDriver driver, ExtentTest testcase)
	{
		this.driver= driver;
		this.testcase= testcase;
	}
	
	
// Open Terms&Conditions Page
	
	public void SettingsDropdownMenu()
	{
		driver.findElement(By.xpath("//span[text()= 'Settings']/..")).click();
	}
	
	public void TermsandConditionsButton()
	{
		try
		{
			driver.findElement(By.xpath("//div[@to= 'document_term']/..")).click();
		}	
		catch(Exception e)
		{
			SettingsDropdownMenu();
			driver.findElement(By.xpath("//div[@to= 'document_term']/..")).click();
		}
	}
	
// Terms&Conditions Page
	
	public String ConfirmationMessage()
	{
		return driver.findElement(By.xpath("//div[@role= 'status']")).getText();
	}
	
	public void ViewInvoiceTermsandConditions()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'Invoice')]/..//button[text()= 'View']")).click();
	}
	
	public WebElement InvoiceModal()
	{
		return driver.findElement(By.xpath("//div[@role='dialog']//p[text()= 'Invoice Terms & Conditions']"));
	}
	
	public void ViewPOTermsandConditions()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'PO')]/..//button[text()= 'View']")).click();
	}
	
	public WebElement PurchaseOrderModal()
	{
		return driver.findElement(By.xpath("//div[@role='dialog']//p[text()= 'Purchase Order (PO) Terms & Conditions']"));
	}
	
	public void ViewQuoteTermsandConditions()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'Quote')]/..//button[text()= 'View']")).click();
	}
	
	public WebElement QuoteModal()
	{
		return driver.findElement(By.xpath("//div[@role='dialog']//p[text()= 'Quote Terms & Conditions']"));
	}
	
	public void ViewSalesOrderTermsandConditions()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'Sales Order')]/..//button[text()= 'View']")).click();
	}
	
	public WebElement SalesOrderModal()
	{
		return driver.findElement(By.xpath("//div[@role='dialog']//p[text()= 'Sales Order Terms & Conditions']"));
	}
	
	public void EditInvoiceButton()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'Invoice')]/..//button[contains(@class, 'css-1cz1f60')]")).click();
	}
	
	public void EditPurchaseOrderButton()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'PO')]/..//button[contains(@class, 'css-1cz1f60')]")).click();
	}
	
	public void EditQuoteButton()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'Quote')]/..//button[contains(@class, 'css-1cz1f60')]")).click();
	}
	
	public void EditSalesOrderButton()
	{
		driver.findElement(By.xpath("//p[contains(text(), 'Sales Order')]/..//button[contains(@class, 'css-1cz1f60')]")).click();
	}
	
	public void EnterTextInTextArea(String TandC) throws InterruptedException
	{
		WebElement e= driver.findElement(By.xpath("//div[contains(@class, 'ql-editor')]"));
		ClearAndEnterValue(e, TandC);
	}
	
	public void SaveButton()
	{
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
	}
	
	public void CancelButton()
	{
		driver.findElement(By.xpath("//button[@type= 'reset']")).click();
	}
	
	public void CloseModalButton()
	{
		driver.findElement(By.xpath("//button[contains(@class, 'css-1fntcqw')]")).click();
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
		
		public void ClickAndAddImage(String Path) throws InterruptedException, AWTException
		{
			driver.findElement(By.xpath("//button[contains(@class, 'ql-image')]")).click();
			Thread.sleep(1250);
			StringSelection selection= new StringSelection(Path); // Enter a valid file path
		   	Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		   	Thread.sleep(1000);
				Robot r= new Robot();
				r.keyPress(KeyEvent.VK_CONTROL);
				r.keyPress(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_V);
				r.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
		}
		
		public void ClickAndAddVideoLink(String Link)
		{
			driver.findElement(By.xpath("")).click();
			driver.findElement(By.xpath("//a[contains(@class, 'ql-preview')]/..//input")).sendKeys(Link);
			driver.findElement(By.xpath("//a[contains(@class, 'ql-action')]")).click();
		}
}









