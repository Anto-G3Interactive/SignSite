package materials;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class EditMaterial extends Initialstep
{
	String Searchvalue= "ThuApr";
	String Category= "powder"; // Enter a valid Category Name
	String Materialname= generateDummyName();
	String Unit= "Ton"; // Enter a valid Unit
	double Cost= 15.10;
	double Markup= 10.24;
	double PriceEquation= Cost*(1+Markup/100);
	String Description= "Edited Description";
	String PODescription= "Edited P.O. Description";
		
	@Test
	public void addmaterial() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Edit Material functionality");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Material']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name= '#0']")).sendKeys(Searchvalue);
		Thread.sleep(2000);
		String Edit= "MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-colorPrimary css-1vmnopk";
		driver.findElement(By.xpath("//button[@class= '"+ Edit +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
		Thread.sleep(1500);
		MaterialName();
		driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[@class= 'css-41imnh-menu']//div[text()= '"+ Unit +"']")).click();
		CostField();
		MarkupField();
		String PriceActualValue= Price();
		MarkupField();
		PODescriptionField();
		PerLIUnit();
		Thread.sleep(2000);
	    String PriceExpectedValue = new DecimalFormat("0.00").format(PriceEquation);
        System.out.println(PriceExpectedValue);
		if(PriceActualValue.equals(String.valueOf(PriceExpectedValue)))
		{
			testcase.log(PASS, "The price value calculated as per the equation [Cost x (1+Markup/100)]");
		}
		else
		{
			testcase.log(FAIL, "The price did not calculated as per the equation. The actual value is: "+ PriceActualValue +". But the expected value is: "+ PriceExpectedValue);
		}
		driver.findElement(By.xpath("//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String confirmation= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		
		if(confirmation.contains("Details updated successfully"))
		{
			testcase.log(PASS, "Material added successfully and the '"+ confirmation + "' message is dispalyed");
		takescreenshot(driver, "Material added");
		}
		else
		{
			testcase.log(FAIL, "Material did not added. The '"+ confirmation + "' message is dispalyed");
			takescreenshot(driver, "Material did not added");
		}
	}
	
	
	public void MaterialName()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Material Name']/following-sibling::input"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(Materialname);
	}
	
	public void CostField()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(String.valueOf(Cost));
	}
	
	public void MarkupField()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(String.valueOf(Markup));
	}
	
	public String Price()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Price']/following-sibling::input"));
		e.click();
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.CONTROL, "a").perform();
	    actions.sendKeys(Keys.CONTROL, "c").perform();
	    String av = e.getDomProperty("value");
	    System.out.println(av);
		return av;
	}
	
	public void DescriptionField()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'Description']/following-sibling::textarea"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(Description);
	}
	
	public void PODescriptionField()
	{
		WebElement e= driver.findElement(By.xpath("//label[text()= 'P.O Description']/following-sibling::textarea"));
		e.click();
		e.sendKeys(Keys.CONTROL, "a");
		e.sendKeys(PODescription);
	}
	
	public void PerLIUnit()
	{
		WebElement e= driver.findElement(By.xpath("//input[@id= 'is_linear_unit']"));
		if (e.isSelected()){}
		else
		{
			e.click();
		}
	}
}
