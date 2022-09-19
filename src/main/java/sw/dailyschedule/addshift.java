package sw.dailyschedule;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class addshift {

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
	public void addshift() throws InterruptedException {

		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		// wait.until(ExpectedConditions.(By.id("MainContent_ASPxCallbackPanelNotification_AddShiftImg")));
		// WebElement iframe =
		// driver.findElement(By.xpath("//iframe[@id='TabContent']"));
		// driver.switchTo().frame(iframe);
		// driver.switchTo().frame("TabContent");
		driver.switchTo().frame("mainframe");
		driver.switchTo().frame("TabContent");

		Thread.sleep(2000);

		WebElement addshift = driver.findElement(By.xpath("(//div[contains(@id,'AddShift')])[2]"));
		addshift.click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//div//iframe[@id='popupmain_CIF-1']")));

//		WebElement current = driver.switchTo().activeElement();
//		//driver.switchTo().frame("mainframe");
//		WebElement iframen = driver.findElement(By.xpath("//div//iframe[@id='popupmain_CIF-1']"));

		// driver.switchTo().frame(iframen);
		Thread.sleep(2000);

		// Shift Type
		WebElement mainaddshift = driver
				.findElement(By.xpath("(//input[@class='dxeEditArea_ShiftwizardDevxtheme dxeEditAreaSys'])[1]"));
		mainaddshift.click();

		List<WebElement> shiftList = driver
				.findElements(By.xpath("//table[@id='MainContent_cmbShifttypelist_DDD_L_LBT']//tr"));

		for (WebElement e : shiftList) {
			String eText = e.getText();
			// System.out.println(eText);
			if (eText.equalsIgnoreCase("M20")) {
				e.click();
				break;

				
			}

		}

		Thread.sleep(3000);

		// Start Time
		WebElement starttime = driver
				.findElement(By.xpath("(//table[@class='dxeButtonEditSys dxeButtonEdit_ShiftwizardDevxtheme'])[2]"));
		starttime.click();

		WebElement starttime1 = driver
				.findElement(By.xpath("(//table[@class='dxeButtonEditSys dxeButtonEdit_ShiftwizardDevxtheme'])[2]"));
		starttime1.click();

		List<WebElement> shifttime = driver.findElements(
				By.xpath("//table[@id='MainContent_ASPxCallbackPanelShiftTime_cmbShiftBeginTime_DDD_L_LBT']//tr"));

		for (WebElement e : shifttime) {
			String stime = e.getText();
			//System.out.println(stime);
			if (stime.equals("16:15")) {
				e.click();
				break;

			}

		}

		Thread.sleep(4000);

		// End Time
		WebElement endtime = driver
				.findElement(By.xpath("//table[@id='MainContent_ASPxCallbackPanelShiftTime_cmbShiftEndTime']//td[3]"));
		endtime.click();

		Thread.sleep(4000);

		List<WebElement> endttime = driver.findElements(
				By.xpath("//div[@id='MainContent_ASPxCallbackPanelShiftTime_cmbShiftEndTime_DDD_PW-1']//tr//td"));

		for (WebElement t : endttime) {
			String etime = t.getText();
			//System.out.println(etime);

			if (etime.equals("19:15")) {
				t.click();
				break;

			}

		}

		// position select
//		WebElement position = driver
//				.findElement(By.xpath("// table[@id='MainContent_tblMemberPositions']//tr//td[2]"));
//		position.sendKeys("2");

//		Thread.sleep(4000);
//
//		List<WebElement> poslabel = driver
//				.findElements(By.xpath("//table[@id='MainContent_tblMemberPositions']//tr//td[1]"));
//
//		for (WebElement p : poslabel) {
//			String plabel = p.getText();
//			System.out.println(plabel);
//
//			if (plabel.equals("SPV")) {
//				
//				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_tdTable']")));
//				
//				WebElement position1 = driver
//						.findElement(By.xpath("// table[@id='MainContent_tblMemberPositions']//tr//td[2]"));
//				position1.sendKeys("2");
//
//				break;
//	}}
//		Thread.sleep(4000);
		WebElement position1 = driver
				.findElement(By.xpath("//table[@id='MainContent_tblMemberPositions']//tr[11]//td[2]"));
		position1.sendKeys("2");
		
//		
//		
		
		WebElement addbtn = driver
				.findElement(By.xpath("//div[@id='MainContent_btnAddShift']"));
		addbtn.click();
		
		
		
		


	}

}
