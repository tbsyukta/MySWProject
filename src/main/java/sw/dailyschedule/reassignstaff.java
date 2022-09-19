package sw.dailyschedule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class reassignstaff {

	public static WebDriver driver;

	@BeforeSuite
	public void setupdriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\techbrain\\Downloads\\chromedriver.exe");

		driver = new ChromeDriver();

		// CMD print stmt of driver setup successfully
		System.out.println("driver setup successfully");

		// maximize the window
		driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// pass URL to chromedriver
		driver.get("https://wakemed-new.dev.myshiftwizard.com/ShiftWizard.aspx");
		System.out.println("URL setup successfully");
	}

	@BeforeTest
	public void login() {

		// Pass User name value
		WebElement username = driver.findElement(By.xpath("//input[@id='txtUserName']"));
		username.sendKeys("matrixsadmin");

		// Pass User password Value
		WebElement password = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		password.sendKeys("Password!1");

		// wait for few seconds to load and till value
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// click on login button
		WebElement clockin = driver.findElement(By.xpath("//input[@id='btnLogin1']"));
		clockin.click();

		// CMD print stmt of use logged in successfully
		System.out.println("SuperUser logged in");

	}

	
	@Test(priority = 1)
	public void reassignshift() throws InterruptedException {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Actions actions = new Actions(driver);

		WebElement maindropdown = driver.findElement(By.xpath("//*[@id='select2-ddlDepartment-container']"));
		maindropdown.click();

		List<WebElement> comList = driver.findElements(By.xpath("//li[@class='select2-results__option']"));

		for (WebElement e : comList) {
			String eText = e.getText();
			// System.out.println(eText);
			if (eText.equalsIgnoreCase("CICU")) {
				e.click();
				break;
			}
		}

		WebElement schedule = driver.findElement(By.xpath("//a[normalize-space()='Schedule']"));
		actions.moveToElement(schedule).perform();

		driver.switchTo().frame("mainframe");
		driver.switchTo().frame("TabContent");

		WebElement reassignshift = driver.findElement(By.xpath("//img[contains(@title,'Reassign staff member')]"));
		reassignshift.click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		List<WebElement> depatlist = driver
				.findElements(By.xpath("//*[contains(@id,'ctl00_MainContent_ASPxGridView1_DXGroupRow')]/td[2]"));

		List<WebElement> datelist = driver
				.findElements(By.xpath("//*[contains(@id,'ctl00_MainContent_ASPxGridView1_DXGroupRow')]/td[3]"));

		List<WebElement> depNames = driver
				.findElements(By.xpath("//*[contains(@id,'MainContent_ASPxGridView1_DXGroupRow')]//td[2]"));
	
		int k = 0;
		for (WebElement depName : depNames) {
			String depNameTxt = depName.getText();
			System.out.println(depNameTxt);
			if (depNameTxt.equalsIgnoreCase("Department: Raleigh Childrens ED - CED")) {
				WebElement arrow = driver.findElement(
						By.xpath("//*[contains(@id,'MainContent_ASPxGridView1_DXGroupRow" + k + "')]//td[1]"));
				Thread.sleep(2000);
				System.out.println("clicked");
				arrow.click();
				System.out.println("clicked2");
				Thread.sleep(1000);
				break;
			}
			k++;
		}
		
//		List<WebElement> listDates = driver
//				.findElements(By.xpath("//*[contains(@id,'MainContent_ASPxGridView1_DXGroupRow')]//td[3]"));
//
//		for (WebElement listDate : listDates) {
//			String listDateTxt = listDate.getText();
//			System.out.println(listDateTxt);
//			if (listDateTxt.equalsIgnoreCase("Date: 8/13/2022")) {
//
//			}
//		}

		List<WebElement> roughDates = driver
				.findElements(By.xpath("//*[contains(@id,'ctl00_MainContent_ASPxGridView1_DXGroupRow')]"));
		int j = 0;
		// WebElement actualDate = null;
		for (WebElement roughDate : roughDates) {
			String roughDateTxt = roughDate.getText();
			System.out.println(roughDateTxt);
			System.out.println(j);
			try {
				WebElement actualDate = driver
						.findElement(By.xpath("//*[@id='ctl00_MainContent_ASPxGridView1_DXGroupRow" + j + "']//td[2]"));
				if (roughDateTxt.contains("Date: 9/10/2022")) {
					actualDate.click();
					break;
				}
			} catch (Throwable e) {
				System.out.println("skipping the test");
			}

			j++;

		}
		Thread.sleep(1000);
		WebElement reasBtn = driver
				.findElement(By.xpath("//*[@id='ctl00_MainContent_ASPxGridView1_DXCBtn0_CD']/../.."));
		reasBtn.click();
	}
}
