package temp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class lockedweeks {

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
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// click on login button
		WebElement clockin = driver.findElement(By.xpath("//input[@id='btnLogin1']"));
		clockin.click();

		// CMD print stmt of use logged in successfully
		System.out.println("SuperUser logged in");

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
			if (eText.equalsIgnoreCase("CICU")) {
				e.click();
				break;
			}
		}
	}

	@Test(priority = 1)
	public void deptaddshift() throws InterruptedException {

		Actions actions = new Actions(driver);
		// click on add shift button

		WebElement schedule = driver.findElement(By.xpath("(//a[contains(text(),'Templates')])[1]"));
		// xpath of schedule item menu
		WebElement Deptschedule = driver.findElement(By.xpath("//a[contains(text(),'Schedule Status')]"));
		// xpath of shift type menu

		actions.moveToElement(schedule).perform();
		Deptschedule.click();// click on shift type menu

		Thread.sleep(3000);

		driver.switchTo().frame("mainframe");
		Thread.sleep(3000);

		List<WebElement> rowlist = driver
				.findElements(By.xpath("//tr[contains(@id,'ctl00_MainContent_ScheduleStatus_DXDataRow')]//td[4]"));
		List<WebElement> rowdate = driver
				.findElements(By.xpath("//tr[contains(@id,'ctl00_MainContent_ScheduleStatus_DXDataRow')]//td[3]"));

		List<Integer> liststatus = new ArrayList<Integer>();

		List<WebElement> statuscolumn = driver
				.findElements(By.xpath("//tr[contains(@id,'ctl00_MainContent_ScheduleStatus_DXDataRow')]//td[4]"));
		for (WebElement statuscolumnSingle : statuscolumn) {
			String statuscolumnTxt = statuscolumnSingle.getText();
			int statuscolumnInt = Integer.parseInt(statuscolumnTxt);
			liststatus.add(statuscolumnInt);
		}

		List<Integer> listdate = new ArrayList<Integer>();

		List<WebElement> datecolumn = driver
				.findElements(By.xpath("//tr[contains(@id,'ctl00_MainContent_ScheduleStatus_DXDataRow')]//td[3]"));
		for (WebElement datecolumnSingle : datecolumn) {
			String datecolumnTxt = datecolumnSingle.getText();
			int datecolumnInt = Integer.parseInt(datecolumnTxt);
			listdate.add(datecolumnInt);
		}

		WebElement singlerowstatus = driver
				.findElement(By.xpath("//tr[contains(@id,'ctl00_MainContent_ScheduleStatus_DXDataRow')][1]//td[4]"));

		WebElement singlerowdate = driver
				.findElement(By.xpath("//tr[contains(@id,'ctl00_MainContent_ScheduleStatus_DXDataRow')][1]//td[3]"));

		String singlerowstatusText = singlerowstatus.getText();
		// String singlerowdateText = singlerowdate.getText();

		int i, j;
		for (i = 0; i < liststatus.size(); i++) {
			for (j = 0; j < i; j++) {
				if (singlerowstatusText.equals("Locked to Staff")) {
					actions.moveToElement(singlerowdate);
					String singlerowdateText = singlerowdate.getText();
					System.out.println("date of locked week : " + singlerowdateText);
				}
				j++;
			}

		}
	}

}
