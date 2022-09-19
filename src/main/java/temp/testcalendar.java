package temp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testcalendar {

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
		username.sendKeys("matrixsadmin");

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
		WebElement clockin = driver.findElement(By.xpath("//input[@id='btnLogin1']"));
		clockin.click();

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
			if (eText.equalsIgnoreCase("CED")) {
				e.click();
				break;
			}
		}

	}

	@Test(priority = 1)
	public void deptschedule() throws InterruptedException {

		Actions actions = new Actions(driver);
		// click on add shift button

		WebElement schedule = driver.findElement(By.xpath("(//a[contains(text(),'Schedule')])[1]"));
		// xpath of schedule item menu
		WebElement Deptschedule = driver.findElement(By.xpath("(//a[contains(text(),'Department Schedule')])[2]"));
		// xpath of shift type menu

		actions.moveToElement(schedule).perform();
		Deptschedule.click();// click on shift type menu

		driver.switchTo().frame("mainframe");
		Thread.sleep(3000);

		WebElement shiftname = driver.findElement(
				By.xpath("//*[@id=\"MainContent_DeptScheduleCallback_deptschedule\"]/tbody/tr[1]/td[2]/span"));
		actions.contextClick(shiftname).perform();

		WebElement editmainmenu = driver.findElement(
				By.xpath("//li[@class='context-menu-item context-menu-submenu']/span[contains(text(),'Edit Shift')]"));
		// xpath of schedule item menu
		WebElement submenu = driver.findElement(By.xpath(
				"(//li[@class='context-menu-item context-menu-submenu']//span[contains(text(),'Edit Shift')])[2]"));
		// xpath of shift type menu

		actions.moveToElement(editmainmenu).perform();
		submenu.click();// click on shift type menu

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		String monthyear = "August 2022";
		String day = "31";
		String date = "2";
		String month = "June";

		WebElement SetValue = driver
				.findElement(By.xpath("//td[@id='MainContent_ASPxCallbackPanel1_deDate_B-1']//img"));
		String SetValueString = SetValue.getText();
	}

}
