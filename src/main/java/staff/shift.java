package staff;

import java.text.ParseException;
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

public class shift {

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

		// click on department dropdown
		WebElement maindropdown = driver.findElement(By.xpath("//*[@id='select2-ddlDepartment-container']"));
		maindropdown.click();

		// get list of department
		List<WebElement> comList = driver.findElements(By.xpath("//li[@class='select2-results__option']"));

		// compare with "CED" department and click on CED department
		for (WebElement e : comList) {
			String eText = e.getText();
			// System.out.println(eText);
			if (eText.equalsIgnoreCase("ED-A")) {
				e.click();
				break;
			}
		}
	}

	@Test(priority = 1)
	public void shiftcolor() throws InterruptedException, ParseException {

		Actions actions = new Actions(driver);

		driver.switchTo().frame("mainframe");

//		
		Thread.sleep(2000);
		String ShiftText;

		List<WebElement> ShiftList = driver
				.findElements(By.xpath("//div[contains(@id,'ASPxScheduler1_aptsBlock_AptDiv')]"));
	
		for (WebElement Shift : ShiftList) {
			try {
				System.out.println("before click event");
			Shift.click();
			System.out.println("after click event 1");
			Thread.sleep(4000);
			
			//driver.switchTo().defaultContent();
			
			driver.switchTo().frame("popupmain_CIF-1");
			System.out.println("default2");
			
			System.out.println("after swithcing frame");
			WebElement shiftdate = driver
					.findElement(By.xpath("//td[@class=' alert alert-info']//span[contains(text(),'2022')]"));
			String ShiftDateText = shiftdate.getText();
			
			System.out.println("after getting date ");
			// System.out.println("shift details are: " + ShiftText);
			System.out.println("shift date which is get from shift banner: " + ShiftDateText);
			System.out.println(" ");
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			
			WebElement shiftbannerclose = driver.findElement(By.xpath("//div[@id='popupmain_HCB-1']//img"));
			shiftbannerclose.click();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("mainframe");
			} catch (Exception e) {			
				System.out.println("did not find anything !!!");
			}
		}

	}

}
