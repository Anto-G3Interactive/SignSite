package smoketesting;

import java.io.IOException;
import java.time.Duration;
import org.testng.annotations.Test;
import basepack.Initialstep;
import emailTemplate.ElementsInEmailTemplate;

public class AddEditAndDeleteEmailTemplate extends Initialstep
{
	ElementsInEmailTemplate EIET;
	
	String Unique= generateDummyName();
	String TemplateCategory= "Quote";
	String TemplateName= TemplateCategory + " Automation Test Template" + Unique;
	String Subject= "Mail for " + TemplateCategory + " " + Unique;
	
	@Test(priority= 1)
	public void AddEmailTemplate() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Add Email Template Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		EIET= new ElementsInEmailTemplate(driver, testcase);
		
		EIET.ClickOnSettingMenu();
		EIET.ClickOnEmailTemplateMenu();
		EIET.AddNewTemplateButton();
		EIET.EnterTemplateName(TemplateName);
		EIET.SelectTemplateCategory(TemplateCategory);
		EIET.EnterSubject(Subject);
		EIET.EnterMessage("Automation Testing Notes");
		EIET.EnterMessage("");
		EIET.SelectVariables(TemplateCategory);
		EIET.SaveButton();
		if(EIET.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Template created and the '"+ EIET.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Template Created");
		}
		else
		{
			testcase.log(FAIL, "Template did not created. The '"+ EIET.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Template  did not created");
			softAssert.assertTrue(false, "The '"+ EIET.ConfirmationMessage() +"' message is displayed");
		}
	}
	
	String EditTemplateName= "To Be Deleted" + Unique;
	String EditTemplateCategory= "Invoice";
	String EditSubject= "Mail for " + EditTemplateCategory + " " + Unique;
	
	@Test(dependsOnMethods= "AddEmailTemplate")
	public void EditEmailTemplate() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Edit Email Template Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		EIET.ClickOnEmailTemplateMenu();
		EIET.SearchForTheEmailTemplates(TemplateName);
		Thread.sleep(1000);
		EIET.EditButton(1);
		EIET.EnterTemplateName(EditTemplateName);
		EIET.EnterSubject(EditSubject);
		EIET.ClearMessageField();
		EIET.EnterMessage("Edited Email Template"+ Unique);
		EIET.SelectTemplateCategory(EditTemplateCategory);
		EIET.SaveButton();
		if(EIET.ConfirmationMessage().toLowerCase().contains("success"))
		{
			testcase.log(PASS, "Template updated and the '"+ EIET.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Template Updated");
		}
		else
		{
			testcase.log(FAIL, "Template did not updated. The '"+ EIET.ConfirmationMessage() +"' message is displayed");
			takescreenshot(driver, "Template  did not Updated");
			softAssert.assertTrue(false, "The '"+ EIET.ConfirmationMessage() +"' message is displayed");
		}
	}
	
	
	@Test(dependsOnMethods= "EditEmailTemplate")
	public void DeleteEmailTemplate() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("To Verify the Delete Email Template Functionality");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		EIET.ClickOnEmailTemplateMenu();
		EIET.SearchForTheEmailTemplates(EditTemplateName);
		Thread.sleep(1000);
		EIET.DeleteButton(1);
		takescreenshot(driver, "Email Template Delete Test");
		EIET.DeletedMessageAndClickOnOkButton("Email Template");
	}
}









