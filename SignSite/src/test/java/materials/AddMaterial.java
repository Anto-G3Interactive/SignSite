package materials;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import basepack.Initialstep;

public class AddMaterial extends Initialstep
{
	
	String Category= "rubber"; // Enter a valid Category Name
	String MaterialName= generateDummyName();
	String Unit= "Ton"; // Enter a valid Unit
	double Cost= 12.00;
	double Markup= 10;
	double PriceEquation= Cost*(1+Markup/100);
	String Description= "New Description";
	String PODescription= "New P.O. Description";
	String NewCategory= generateDummyName();
	
// Add Material
	@Test (priority= 1)
	public void addmaterial() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Material functionality");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.findElement(By.xpath("//span[text()= 'Products']")).click();
		driver.findElement(By.xpath("//div[text()= 'Material']")).click();
		driver.findElement(By.xpath("//button[text()= ' Add New Material']")).click();
		driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Category +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Material Name']/following-sibling::input")).sendKeys(MaterialName);
		driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Unit +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(String.valueOf(Cost));
		driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(String.valueOf(Markup));
		String PriceActualValue= Price();
		driver.findElement(By.xpath("//label[text()= 'Description']/following-sibling::textarea")).sendKeys(Description);
		driver.findElement(By.xpath("//label[text()= 'P.O Description']/following-sibling::textarea")).sendKeys(PODescription);
		driver.findElement(By.xpath("//input[@id= 'is_linear_unit']")).click();
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
		
		if(confirmation.contains("Details saved successfully"))
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
	
	
// Add material with New Category
	
	@Test (priority=2)
	public void AddMaterialWithNewCategory() throws InterruptedException, IOException
	{
		testcase= extentReport.createTest("Verify the Add Material functionality by addning new Category");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		try
		{
			driver.findElement(By.xpath("//div[text()= 'Material']")).click();
		}
		catch (Exception e)
		{
			driver.findElement(By.xpath("//span[text()= 'Products']")).click();
			driver.findElement(By.xpath("//div[text()= 'Material']")).click();
		}
		driver.findElement(By.xpath("//button[text()= ' Add New Material']")).click();
		
		driver.findElement(By.xpath("//div[@class='col-sm-6']//button[@type= 'button']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//label[text()= 'Category Name']/following-sibling::input")).sendKeys(NewCategory);
		driver.findElement(By.xpath("//div[@role= 'dialog']//button[text()= 'Save']")).click();
		Thread.sleep(1500);
		String status= driver.findElement(By.xpath("//div[@role= 'status']")).getText();
		if(status.contains("Details saved successfully"))
		{
			driver.findElement(By.xpath("//label[text()= 'Category']/following-sibling::div")).click();
			String s= driver.findElement(By.xpath("(//div[@class= 'css-gbbed2-singleValue'])[1]")).getText();
			testcase.log(PASS, "New Category is added. The added Category is: '"+ s);
			takescreenshot(driver, "New Category added");
		}
		else
		{
			testcase.log(FAIL, "New Category did not added. The '"+ status + "' message is dispalyed");
			takescreenshot(driver, "New Category did not added");
		}
		driver.findElement(By.xpath("//label[text()= 'Material Name']/following-sibling::input")).sendKeys(MaterialName);
		driver.findElement(By.xpath("//label[text()= 'Unit']/following-sibling::div")).click();
		driver.findElement(By.xpath("//div[text()= '"+ Unit +"']")).click();
		driver.findElement(By.xpath("//label[text()= 'Cost']/following-sibling::input")).sendKeys(String.valueOf(Cost));
		driver.findElement(By.xpath("//label[text()= 'Markup (%)']/following-sibling::input")).sendKeys(String.valueOf(Markup));
		String PriceActualValue= Price();
		driver.findElement(By.xpath("//label[text()= 'Description']/following-sibling::textarea")).sendKeys(Description);
		driver.findElement(By.xpath("//label[text()= 'P.O Description']/following-sibling::textarea")).sendKeys(PODescription);
		driver.findElement(By.xpath("//input[@id= 'is_linear_unit']")).click();
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
		
		if(confirmation.contains("Details saved successfully"))
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
	
	
	public String Price()
	{		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String av= (String) js.executeScript("return document.querySelector('input[name=\"price\"]').value");
        System.out.println(av);
		return av;
	}

}
