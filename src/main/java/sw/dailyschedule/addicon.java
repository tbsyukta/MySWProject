package sw.dailyschedule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class addicon {

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

		// driver.findElement(By.xpath("//a[normalize-space()='Schedule']")).click();
		// Actions actions = new Actions(driver);
		// WebElement menuOption =
		// driver.findElement(By.xpath("//div[@id='nav-quicklinks-dropdown-listsA3']//a[normalize-space()='Daily
		// Schedule']"));
		// actions.moveToElement(menuOption).perform();

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
		Thread.sleep(4000);

		driver.switchTo().frame("mainframe");
		driver.switchTo().frame("TabContent");

		WebElement addicon = driver.findElement(By.xpath("//img[contains(@title,'Add New Icon.')]"));
		addicon.click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		WebElement clickdept = driver.findElement(By.xpath("//input[@id='MainContent_rdUserScheduleType_63']"));
		clickdept.click();
		Thread.sleep(2000);

	

		WebElement notes = driver.findElement(By.xpath("//textarea[@id='MainContent_txtUserScheduleNotes']"));
		notes.sendKeys("Testing Icon...");
		
		WebElement savebtn = driver.findElement(By.xpath("//input[@id='MainContent_btnSubmit']"));
		savebtn.click();
		
//		WebElement cancelbtn = driver.findElement(By.xpath("//input[@id='MainContent_btnCancel']"));
//		cancelbtn.click();
	
	}

}