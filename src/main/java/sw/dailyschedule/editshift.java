package sw.dailyschedule;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class editshift {

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
	public void editshift() throws InterruptedException {

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

		WebElement editshift = driver.findElement(By.xpath(
				"(//a[contains(@id,'ASPxGridView1_DXCBtn3')]//img[contains(@title,'Edit existing shift.')])[1]"));
		editshift.click();
		Thread.sleep(6000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		// select location
		WebElement sloc = driver
				.findElement(By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_cmbLocation']//td[2]"));
		sloc.click();

		List<WebElement> loclist = driver
				.findElements(By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_cmbLocation_DDD_L_LBT']//tr"));

		for (WebElement l : loclist) {
			String lText = l.getText();
			//System.out.println(lText);
			if (lText.equalsIgnoreCase("Home Health")) {
				l.click();
				break;
			}
		}

		
		Thread.sleep(3000);

		// select department
		WebElement sdept = driver.findElement(By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_cmbDepartment']"));
		sdept.click();
		Thread.sleep(3000);

//		WebElement sdept1 = driver.findElement(By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_cmbDepartment']"));
//		sdept1.click();

		List<WebElement> deptlist = driver
				.findElements(By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_cmbDepartment_DDD_L_LBT']//tr"));

		for (WebElement d : deptlist) {
			String dText = d.getText();
			//System.out.println(dText);
			if (dText.equalsIgnoreCase("Community Case Management")) {
				d.click();
				break;
			}
		}

		String month = "September 2022";
		String day = "10";

		try {

			driver.findElement(By.xpath("(//table[contains(@class,'dxeButtonEditSys')]//tr[1]//td[2])[3]")).click();

		} catch (StaleElementReferenceException e) {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//table[contains(@class,'dxeButtonEditSys')]//tr[1]//td[2])[3]")).click();

		}

		Thread.sleep(4000);

		while (true) {
			String text = driver.findElement(By.xpath("//span[@id='MainContent_ASPxCallbackPanel1_deDate_DDD_C_T']"))
					.getText();
			if (text.equals(month)) {

				break;

			} else {

				driver.findElement(By.xpath("//*[@id='MainContent_ASPxCallbackPanel1_deDate_DDD_C_NMCImg']")).click();
			}
		}

		driver.findElement(
				By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_deDate_DDD_C_mt']//tr[3]/td[contains(text(),"
						+ day + ")]"))
				.click();

		Thread.sleep(2000);

		WebElement stime = driver.findElement(
				By.xpath("(//table[@class='dxeButtonEditSys dxeButtonEdit_ShiftwizardDevxtheme']//tr//td[3])[3]"));
		stime.click();

		List<WebElement> stimelist = driver
				.findElements(By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_StartTime_DDD_L_LBT']//tr//td"));

		for (WebElement st : stimelist) {
			String sText = st.getText();
			//System.out.println(sText);
			if (sText.equalsIgnoreCase("19:15")) {
				st.click();
				break;
			}
		}

		Thread.sleep(2000);

		WebElement etime = driver.findElement(By.xpath("//td[@id='MainContent_ASPxCallbackPanel1_EndTime_B-1']"));
		etime.click();

		List<WebElement> etimelist = driver
				.findElements(By.xpath("//table[@id='MainContent_ASPxCallbackPanel1_EndTime_DDD_L_LBT']//td"));

		for (WebElement et : etimelist) {
			String eText = et.getText();
			//System.out.println(eText);
			if (eText.equalsIgnoreCase("20:15")) {
				et.click();
				break;
			}
		}

		Thread.sleep(2000);

		WebElement position = driver.findElement(By.xpath("//td[contains(@id,'cmbPosition_B-1')]"));
		position.click();

		List<WebElement> positionlist = driver
				.findElements(By.xpath("//table[contains(@id,'cmbPosition_DDD_L_LBT')]//td"));

		for (WebElement pos : positionlist) {
			Thread.sleep(2000);
			String pText = pos.getText();
			
			System.out.println(pText);Thread.sleep(2000);
			if (pText.equalsIgnoreCase("RN-C")) {
				pos.click();
				break;
			}
		}

		Thread.sleep(2000);

		WebElement category = driver.findElement(By.xpath("//td[contains(@id,'cmbCategory_B-1')]"));
		category.click();

		List<WebElement> categorylist = driver
				.findElements(By.xpath("//table[contains(@id,'cmbCategory_DDD_L_LBT')]//td"));

		for (WebElement cate : categorylist) {
			String cText = cate.getText();
			//System.out.println(cText);
			if (cText.equalsIgnoreCase("Class")) {
				cate.click();
				break;
			}
		}

		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//div[contains(@id,'AddShiftEdit_CD')]")).click();
		
		

	}

}
