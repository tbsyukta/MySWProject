package template;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class newtemp {

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
		username.sendKeys("tbs.vishalp@gmail.com");

		// Pass User password Value
		WebElement password = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		password.sendKeys("Password!1365");

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
		
		
		WebElement template = driver.findElement(By.xpath("(//a[contains(text(),'Template')])[1]"));
		// xpath of schedule item menu
		WebElement scheduletemp = driver.findElement(By.xpath("//a[contains(text(),'Schedule Templates')]"));
		// xpath of shift type menu
		
		actions.moveToElement(template).perform();
		scheduletemp.click();// click on shift type menu
		
		driver.switchTo().frame("mainframe");
		
		WebElement addnew = driver.findElement(By.xpath("//a[contains(@id,'MainContent_ASPxPanel1_AddNew')]"));
		addnew.click();
		
		Thread.sleep(1000);

		WebElement tempname = driver.findElement(By.xpath("//input[contains(@id,'DXEFL_DXEditor3_I')]"));
		tempname.sendKeys("test_01");
		Thread.sleep(100);
		
		WebElement weekno = driver.findElement(By.xpath("//input[contains(@id,'ctl00_MainContent_ASPxPanel1_TemplateList_DXEFL_DXEditor4_I')]"));
		
		weekno.clear();
		
		weekno.sendKeys("test");
		
		Thread.sleep(100);
		WebElement notes = driver.findElement(By.xpath("//input[contains(@id,'DXEFL_DXEditor5_I')]"));
		notes.sendKeys("test_01	");
		
//		WebElement clickright = driver.findElement(By.xpath("//img[contains(@id,'DXEFL_DXCBtn2Img')]"));
//		clickright.click();
		
		
		
	}
	
}
