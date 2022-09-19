package sw.dailyschedule;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class replacestaffsecond {

	public static WebDriver driver;

	@BeforeSuite
	public void setupdriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\techbrain\\Downloads\\chromedriver.exe");
		// WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		// CMD print stmt of driver setup successfully
		System.out.println("driver setup successfully");

		// maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
	public void replaceshift() throws InterruptedException {

//		driver.findElement(By.xpath("//a[normalize-space()='Schedule']")).click();
//		Actions actions = new Actions(driver);
//    	WebElement menuOption = driver.findElement(By.xpath("//div[@id='nav-quicklinks-dropdown-listsA3']//a[normalize-space()='Daily Schedule']"));
//    	actions.moveToElement(menuOption).perform();

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

		WebElement replaceshift = driver.findElement(By.xpath("//img[contains(@title,'another person.')]"));
		replaceshift.click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		List<WebElement> stafflist = driver
				.findElements(By.xpath("//*[contains(@id,'MainContent_gridUnassignedStaff_DXDataRow')]/td[2]"));

		int i = 0;
		for (WebElement name : stafflist) {
			String nameText = name.getText();
			System.out.println(nameText);
			WebElement plus = driver.findElement(By.id("MainContent_gridUnassignedStaff_DXCBtn" + i + "Img"));
			if (nameText.equalsIgnoreCase("Ahmed, Mariam")) {
				plus.click();
				break;
			}
			i++;
		}

	}

}
