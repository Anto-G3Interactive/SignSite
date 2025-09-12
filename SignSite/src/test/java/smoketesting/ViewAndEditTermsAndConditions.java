package smoketesting;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import basepack.Initialstep;
import termsandcondition.ElementsInTermsandConditions;

public class ViewAndEditTermsAndConditions extends Initialstep
{
	ElementsInTermsandConditions EITC;
	
	String Unique= generateDummyName();
	String Invoice= "Invoice Terms and Conditions added for Automation Testing on "+ Unique;
	String PurchaseOrder= "Purchase Order Terms and Conditions added for Automation Testing on "+ Unique;
	String Quote= "Quote Terms and Conditions added for Automation Testing on "+ Unique;
	String SalesOrder= "Sales Order Terms and Conditions added for Automation Testing on "+ Unique;
	
// View Terms & Conditions	
	
	@Test (priority= 1)
	public void ViewTermsandConditions() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To View Terms & Conditions");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EITC= new ElementsInTermsandConditions(driver, testcase);
		
		EITC.SettingsDropdownMenu();
		EITC.TermsandConditionsButton();
		EITC.ViewInvoiceTermsandConditions();
		ViewVerification(EITC.InvoiceModal(), "Invoice");
		Thread.sleep(1000);
		EITC.CancelButton();
		Thread.sleep(1000);
		EITC.ViewPOTermsandConditions();
		ViewVerification(EITC.PurchaseOrderModal(), "Purchase Order");
		Thread.sleep(1000);
		EITC.CancelButton();
		Thread.sleep(1000);
		EITC.ViewQuoteTermsandConditions();
		ViewVerification(EITC.QuoteModal(), "Quote");
		Thread.sleep(1000);
		EITC.CancelButton();
		Thread.sleep(1000);
		EITC.ViewSalesOrderTermsandConditions();
		ViewVerification(EITC.SalesOrderModal(), "Sales Order");
		Thread.sleep(1000);
		EITC.CancelButton();
		Thread.sleep(1000);
	}
	public void ViewVerification(WebElement e, String modal) throws IOException
	{
		try
		{
			if(e.isDisplayed())
			{
				testcase.log(PASS, "The '"+ e.getText() +"' pop-up is displayed");
				takescreenshot(driver, e.getText()+" pop-up is displayed");
			}
		}
		catch(Exception ex)
		{
			testcase.log(FAIL, "The '"+ modal +"' pop-up did not displayed");
			takescreenshot(driver, modal+" pop-up did not displayed");
		}
	}
	
	
// Edit Terms & Conditions	
	
	@Test (priority= 2)
	public void EditTermsandConditions() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To verify Edit funcitonalities of Terms & Conditions");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EITC= new ElementsInTermsandConditions(driver, testcase);
		
		EITC.TermsandConditionsButton();
		EITC.EditInvoiceButton();
		EITC.EnterTextInTextArea(Invoice);
		EITC.SaveButton();
		VerifyTheSuccessMessage();
		Thread.sleep(2000);
		EITC.EditPurchaseOrderButton();
		EITC.EnterTextInTextArea(PurchaseOrder);
		EITC.SaveButton();
		VerifyTheSuccessMessage();
		Thread.sleep(2000);
		EITC.EditQuoteButton();
		EITC.EnterTextInTextArea(Quote);
		EITC.SaveButton();
		VerifyTheSuccessMessage();
		Thread.sleep(2000);
		EITC.EditSalesOrderButton();
		EITC.EnterTextInTextArea(SalesOrder);
		EITC.SaveButton();
		VerifyTheSuccessMessage();
	}
	public void VerifyTheSuccessMessage() throws IOException
	{
		if(EITC.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Terms & Conditions updated and the '"+ EITC.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Terms & Conditions Updated");
		}
		else
		{
			testcase.log(FAIL, "Terms & Conditions did not updated. The '"+ EITC.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Terms & Conditions did not Updated");
			EITC.CloseModalButton();
			softAssert.assertTrue(false, "The '"+ EITC.ConfirmationMessage() +"' message is displayed");
		}
	}
}
