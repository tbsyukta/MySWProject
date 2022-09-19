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

public class findshiftcolor {

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
//		WebElement maindropdown = driver.findElement(By.xpath("//*[@id='select2-ddlDepartment-container']"));
//		maindropdown.click();
//
//		// get list of department
//		List<WebElement> comList = driver.findElements(By.xpath("//li[@class='select2-results__option']"));
//
//		// compare with "CED" department and click on CED department
//		for (WebElement e : comList) {
//			String eText = e.getText();
//			// System.out.println(eText);
//			if (eText.equalsIgnoreCase("CICU")) {
//				e.click();
//				break;
//			}
//		}

	}

	@Test(priority = 1)
	public void shiftcolor() throws InterruptedException {

		Actions actions = new Actions(driver);
		// click on add shift button
	//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.switchTo().frame("mainframe");

		List<WebElement> shiftList = driver.findElements(
				By.xpath("//div[contains(@id,'ASPxScheduler1_aptsBlock_ctl00_0_appointmentBackground')]"));
		List<WebElement> shiftdetails = driver
				.findElements(By.xpath("//span[contains(@id,'ASPxScheduler1_aptsBlock_ctl00_0_lblTitle')]"));
		List<WebElement> datelist = driver
				.findElements(By.xpath("//td[contains(@class,'dxscDateCellHeader_ShiftwizardDevxtheme')]"));
		
		// compare with "CED" department and click on CED department
		for (int i= 0;i<datelist.size();i++) {
			String date = datelist.toString();
			//System.out.println("shift color : " + colorText); 
		for (WebElement shiftd : shiftdetails) {
			
			System.out.println(date);
			WebElement colordiv = driver.findElement(
					By.xpath("//div[contains(@id,'ASPxScheduler1_aptsBlock_ctl00_0_appointmentBackground')]"));
			actions.moveToElement(colordiv);
			String colorText = colordiv.getCssValue("background-color");
			System.out.println("shift color: " + colorText);
			String sText = shiftd.getText();

			System.out.println("shift details");
			System.out.println(sText);
			System.out.println(" ");	
		
			}}
			
//		}
		// driver.switchTo().defaultContent();
//		List<String> list2 = new ArrayList<String>();
//		List<WebElement> datelist = driver
//				.findElements(By.xpath("//td[contains(@class,'dxscDateCellHeader_ShiftwizardDevxtheme')]"));
//		
//	
//		for (WebElement hrcolumnSingle : datelist) {
//			String hrcolumnTxt = hrcolumnSingle.getText();
//			
//			list2.add(hrcolumnTxt);
//			
//		}
//		System.out.println(list2);
//		
//		
//		WebElement addclick = driver.findElement(By.xpath("//img[contains(@title,'Add Icon')]"));
////
//		for (int i=0;i<list2.size();i++) {
//			
//		//	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//td[contains(@class,'dxscDateCellHeader_ShiftwizardDevxtheme')]")));
//			Thread.sleep(2000);
//			
//			System.out.println(dText);
//			Thread.sleep(2000);
//			if (dText.equals("28 September 2022")) {
//				addclick.click();
//				driver.switchTo().defaultContent();
//				driver.switchTo().frame("popupmain_CIF-1");
//				WebElement selecticon = driver
//						.findElement(By.xpath("//input[contains(@id,'MainContent_rdUserScheduleType_0')]"));
//				selecticon.click();
//				WebElement notes = driver
//						.findElement(By.xpath("//textarea[contains(@id,'MainContent_txtUserScheduleNotes')]"));
//				notes.sendKeys("test for 120922");
//				WebElement savebtn = driver.findElement(By.xpath("//input[contains(@id,'MainContent_btnSubmit')]"));
//				savebtn.click();
//
//			}
//
//		}

	}

}
