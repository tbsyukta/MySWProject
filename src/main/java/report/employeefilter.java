package report;

import java.time.Duration;
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

public class employeefilter {

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

	@Test
	public void empbday() throws InterruptedException {
		Actions actions = new Actions(driver);

		WebElement report = driver.findElement(By.xpath("(//a[contains(text(),'Reports')])[1]"));
		// xpath of schedule item menu
		WebElement scheduling = driver.findElement(By.xpath("(//a[contains(text(),'Scheduling')])[2]"));
		// xpath of shift type menu
		WebElement empbday = driver.findElement(By.xpath("(//a[contains(text(),'Employee Birthdays')])[1]"));

		actions.moveToElement(report).perform();
		actions.moveToElement(scheduling).perform();
		empbday.click();// click on shift type menu

		Thread.sleep(3000);

		driver.switchTo().frame("mainframe");

		WebElement input = driver.findElement(By.xpath("//input[@id='reportrange']"));
		input.click();

		WebElement today = driver.findElement(By.xpath("//div[@class='ranges']//li[1]"));
		today.click();

		WebElement greport1 = driver.findElement(By.xpath("//input[@id='MainContent_Button1']"));
		greport1.click();

		System.out.println("today date data");
		

		// WebElement month =
		// driver.findElement(By.xpath("(//tr[contains(@id,'ctl00_MainContent_ASPxGridView1_DXDataRow')])//td[2]"));

		List<WebElement> user = driver
				.findElements(By.xpath("(//tr[contains(@id,'ctl00_MainContent_ASPxGridView1_DXDataRow')])//td[2]"));
		List<WebElement> date = driver
				.findElements(By.xpath("(//tr[contains(@id,'ctl00_MainContent_ASPxGridView1_DXDataRow')])//td[3]"));

		for (WebElement m : user) {
			String mText = m.getText();
			System.out.println(mText);

		}
		for (WebElement d : date) {
			String dText = d.getText();
			System.out.println(dText);

		}

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='reportrange']")));

		input.click();

		WebElement yesterday = driver.findElement(By.xpath("//div[@class='ranges']//li[2]"));
		yesterday.click();

		greport1.click();

		System.out.println("yesterday date data");

		try {

			for (WebElement m : user) {
				String mText = m.getText();
				System.out.println(mText);

			}
			for (WebElement d : date) {
				String dText = d.getText();
				System.out.println(dText);

			}
		} catch (Exception e) {
			System.out.println("No data to display");

		}
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='reportrange']")));

		input.click();

		WebElement thisweek = driver.findElement(By.xpath("//div[@class='ranges']//li[2]"));
		thisweek.click();

		greport1.click();

		System.out.println("this week date data");

			for (WebElement t : user) {
				String mText = t.getText();
				System.out.println(mText);

			}
			for (WebElement dt : date) {
				String dText = dt.getText();
				System.out.println(dText);

			
		}
	}

}
