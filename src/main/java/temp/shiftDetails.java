package temp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class shiftDetails {

	public static WebDriver driver;

	@BeforeSuite
	public void setupdriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\techbrain\\Downloads\\chromedriver.exe");

		driver = new ChromeDriver();

		// CMD print stmt of driver setup successfully
		// System.out.println("driver setup successfully");

		// maximize the window
		driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// pass URL to chromedriver
		driver.get("https://wakemed-new.dev.myshiftwizard.com/ShiftWizard.aspx");
		// System.out.println("URL setup successfully");
	}

	@BeforeTest
	public void login() {

		// Pass User name value
		WebElement username = driver.findElement(By.xpath("//input[@id='txtUserName']"));
		username.sendKeys("StaffLogin");

		// Pass User password Value
		WebElement password = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		password.sendKeys("Password!1");

		// wait for few seconds to load and till value
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// click on login button
		WebElement Login = driver.findElement(By.xpath("//input[@id='btnLogin1']"));
		Login.click();

		// CMD print stmt of use logged in successfully
		// System.out.println("SuperUser logged in");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	public void shiftcolor() throws InterruptedException {

		Actions actions = new Actions(driver);

		driver.switchTo().frame("mainframe");
	
		List<WebElement> shiftdetails = driver
				.findElements(By.xpath("//span[contains(@id,'ASPxScheduler1_aptsBlock_ctl00_0_lblTitle')]"));
		
		WebElement datename = driver.findElement(By.xpath("//td[@class='dxscDateCellHeader_ShiftwizardDevxtheme']"));

//		for (WebElement shift : shiftdetails) {
//			String datedetails = shift.getText();
//
//			actions.moveToElement(datename);
//			String d = datename.getText();
//
//			System.out.println("shift date is : " + d);
//			System.out.println("Shift Details is : " + datedetails);
//			System.out.println();
//
//		}
		
		//to find list of shift span information 
		//div[contains(@id,'ASPxScheduler1_aptsBlock_AptDiv')]//span
		//span[contains(@id,'ASPxScheduler1_aptsBlock')]
		
		
		List<String> arr = new ArrayList<String>();

		List<WebElement> test = driver
				.findElements(By.xpath("//span[contains(@id,'ASPxScheduler1_aptsBlock_ctl00_0_lblTitle')]"));
		List<WebElement> shiftList = driver.findElements(
				By.xpath("//div[@id='ASPxScheduler1_aptsBlock_ctl00_0_appointmentBackground_0']"));
		List<String> arr2 = new ArrayList<String>();
		
		List<WebElement> datelist = driver
				.findElements(By.xpath("//td[contains(@class,'dxscDateCellHeader_ShiftwizardDevxtheme')]"));
		
		for(WebElement t:datelist)
		{
			String d=t.getAttribute("title");
			arr2.add(d);
			System.out.println("datelist : " +datelist);
		}
		
		//test.addAll(shiftList);
		
		for(WebElement g:test)
		{
			String t=g.getText();
		arr.add(t);
	//	System.out.println("shift : " +t);
		}
		
//		for(int i=0;i<arr.size();i++)
//		{
//			for (int j=0;j<.size();j++) {
//				WebElement colordiv = driver.findElement(
//						By.xpath("//div[@id='ASPxScheduler1_aptsBlock_ctl00_0_appointmentBackground_0']"));
//				actions.moveToElement(colordiv);
//				String colorText = colordiv.getCssValue("background-color");
//				System.out.println("shift color: " + colorText);
//		
//				String dateli = arr.get(i);
//				
//				System.out.println("Shift Date is : " + dateli);
//				System.out.println();
//
//			}
//		}
		
		
	

	}

}
