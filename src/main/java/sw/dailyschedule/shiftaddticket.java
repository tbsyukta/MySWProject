package sw.dailyschedule;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class shiftaddticket {

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
	public void deptschedule() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
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

		driver.findElement(By.xpath("//div[contains(@id,'MainContent_ActionShift_CD')]")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[contains(@id,'MainContent_ctl01_ctl15')]")).click();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@id,'MainContent_ShiftButton')]")).click();

		Thread.sleep(3000);
		List<WebElement> shifttype = driver.findElements(By.xpath("//div[contains(@id,'ShiftButtons')]//span"));

		int maxShift = shifttype.size();
		Random random0 = new Random();
		int randomshifttype = random0.nextInt(maxShift);
		Thread.sleep(3000);
		WebElement shift = shifttype.get(randomshifttype);
		shift.click();
		String shiftname = shift.getAttribute("value");
		System.out.println("shift name of id : " + randomshifttype);
		System.out.println("shift name: " + shiftname);
		Thread.sleep(3000);

		driver.findElement(By.xpath("//div[contains(@id,'MainContent_PositionButton')]")).click();
		Thread.sleep(3000);

		List<WebElement> productElems = driver.findElements(By.xpath("//div[contains(@id,'PositionButtons')]//span"));
		int maxProducts = productElems.size();
		Random random = new Random();
		int randomProduct = random.nextInt(maxProducts);
		Thread.sleep(3000);
		productElems.get(randomProduct).click();

		Thread.sleep(3000);

		String dateforaddshift = "5";

		List<WebElement> staffname = driver.findElements(
				By.xpath("//table[contains(@id,'MainContent_DeptScheduleCallback_deptschedule')]//tbody//tr"));

		// table[contains(@id,'MainContent_DeptScheduleCallback_deptschedule')]//tbody//tr[56]//td[5]

		// WebElement loading =
		// driver.findElement(By.xpath("//*[@id='MainContent_LoadingPanel']//tr"));
		int maxStaff = staffname.size();
		Random random1 = new Random();
		int randomStaff = random1.nextInt(maxStaff);
		WebElement staffid = staffname.get(randomStaff);
		System.out.println("id of staff user random : " + randomStaff);

		List<WebElement> datelist = driver
				.findElements(By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']//tbody//tr//td"));

		// table[contains(@id,'MainContent_DeptScheduleCallback_deptschedule')]//tbody//tr[56]//td[5]

		// WebElement loading =
		// driver.findElement(By.xpath("//*[@id='MainContent_LoadingPanel']//tr"));
//		int randomdatemax = datelist.size();
//		Random randomdate1 = new Random();
//		int randomdate = randomdate1.nextInt(randomdatemax);
//		WebElement dateno = datelist.get(randomdate);
////		System.out.println("id of staff user random : " + randomdate);
//		Thread.sleep(2000);
//		actions.moveToElement(staffid);
//		dateno.click();

		Thread.sleep(3000);
		WebElement cols = driver
				.findElement(By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']//tbody//tr["+ randomStaff + "]//td[" + dateforaddshift + "]"));
		Thread.sleep(3000);
		cols.click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@id='MainContent_ExitShiftButton']")).click();
		Thread.sleep(4000);

//		List<WebElement> staffspan = driver
//				.findElements(By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']/tbody/tr/td/span"));

		// int staffspan1 = staffspan.size();
//		Random random3 = new Random();
//		int randomStaff1 = random3.nextInt(staffspan1);

		Thread.sleep(4000);

//		WebElement test = staffname.get(randomStaff);
		System.out.println("test variable : " + randomStaff);
		Thread.sleep(3000);

		WebElement colsforrightclick = driver
				.findElement(By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']//tbody//tr["
						+ randomStaff + "]//td[" + dateforaddshift + "]"));
		Thread.sleep(3000);

		actions.contextClick(colsforrightclick).perform();

		Thread.sleep(3000);

		WebElement addicon = driver.findElement(By.xpath("/html/body/ul/li[9]/span"));
		addicon.click();

		Thread.sleep(3000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		WebElement availableicon = driver
				.findElement(By.xpath("//*[@id=\"MainContent_rdUserScheduleType\"]/tbody/tr[3]/td[1]/label"));
		availableicon.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		// driver.findElement(By.xpath("//*[@id='MainContent_rdUserScheduleType']/tbody/tr[1]/td[1]/label")).click();

		Thread.sleep(3000);
		driver.findElement(By.id("MainContent_txtUserScheduleNotes")).sendKeys("Test");
		Thread.sleep(3000);
		driver.findElement(By.id("MainContent_btnSubmit")).click();

		Thread.sleep(3000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainframe");

		WebElement editshift = driver
				.findElement(By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']//tbody//tr["
						+ randomStaff + "]//td[" + dateforaddshift + "]"));
////		Thread.sleep(3000);

		Thread.sleep(3000);
		actions.contextClick(editshift).perform();
//
//		JavascriptExecutor js1 = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,1000)");
//		// driver.findElement(By.xpath("//*[@id='MainContent_rdUserScheduleType']/tbody/tr[1]/td[1]/label")).click();
//		Thread.sleep(2000);
//		

		WebElement editshiftmenu = driver.findElement(By.xpath("/html/body/ul/li[7]/span"));
		WebElement singleshiftlabel = driver.findElement(By.xpath("/html/body/ul/li[7]/ul/li"));

		actions.moveToElement(editshiftmenu);
		singleshiftlabel.click();// click on shift type menu

		System.out.println("id of random span shift 3 " + randomStaff);

		List<WebElement> editshiftlist = driver.findElements(
				By.xpath("//li[contains(@class,'context-menu-submenu context-menu-visible')]//ul//spann"));
		Thread.sleep(5000);

		if (!editshiftlist.equals(null)) {
			for (WebElement shiftnamelist : editshiftlist) {
				String typeText = shiftnamelist.getText();
				Thread.sleep(2000);
				System.out.println("get value from loop : " + typeText);
				Thread.sleep(2000);
				WebElement contcatepath = driver.findElement(By.xpath(
						"//li[@class='context-menu-item']//span[contains(text(),'Edit Shift " + shiftname + "')]"));
				/// html/body/ul/li[7]/ul/li[contains(text(),"+typeText+")]
				Thread.sleep(2000);
				if (typeText.contains(shiftname)) {
					actions.moveToElement(contcatepath).click().build().perform();

					// contcatepath.click();
					System.out.println("click on it that same shift  : " + contcatepath);
				}

			}

			Thread.sleep(5000);
			String monthyear = "June 2022";
			String day = "24";
			// String date = "2";
			final String month = "August";
			System.out.println("Printing visible date");
			WebElement setValueDate = driver.findElement(By.id("MainContent_ASPxCallbackPanel1_deDate_I"));
			System.out.println(setValueDate.getAttribute("value"));
			WebElement SetValue = driver
					.findElement(By.xpath("//td[@id='MainContent_ASPxCallbackPanel1_deDate_B-1']//img"));
			// String SetValueString = SetValue.getText();
			SetValue.click();
			WebElement monthYear = driver
					.findElement(By.xpath("//span[@id='MainContent_ASPxCallbackPanel1_deDate_DDD_C_T']"));
			List<WebElement> workingDates = driver.findElements(
					By.xpath("//*[contains(@id,'deDate_DDD_C_mt')]//td[@class='dxeCalendarDay_ShiftwizardDevxtheme']"));
			List<WebElement> selectedDate = driver.findElements(By.xpath(
					"//*[contains(@id,'deDate_DDD_C_mt')]//td[contains(@class,'dxeCalendarSelected_ShiftwizardDevxtheme')]"));
			List<WebElement> currentWeekends = driver.findElements(By.xpath(
					"//*[contains(@id,'deDate_DDD_C_mt')]//td[@class='dxeCalendarDay_ShiftwizardDevxtheme dxeCalendarWeekend_ShiftwizardDevxtheme']"));

			workingDates.addAll(selectedDate);
			workingDates.addAll(currentWeekends);

			String[] monthYearArr = monthYear.getText().split(" ");

			if (!month.equalsIgnoreCase(monthYearArr[0])) {
				monthYear.click();
			}

			List<WebElement> allMonths = driver
					.findElements(By.xpath("//td[contains(@id,'MainContent_ASPxCallbackPanel1_deDate_DDD_C_FNP_M')]"));

//		List<WebElement> allmonths = driver
//				.findElements(By.xpath("//td[contains(@id,'MainContent_ASPxCallbackPanel1_deDate_DDD_C_FNP_M')]"));
			System.out.println("All the  months list");
			for (WebElement allmonthlist : allMonths) {

				System.out.println(allmonthlist.getText());
			}

			List<WebElement> selectedmonths = driver.findElements(
					By.xpath("//td[contains(@class,' dxeCalendarFastNavMonthSelected_ShiftwizardDevxtheme')]"));
			System.out.println("All the selected months list");
			for (WebElement selectlist : selectedmonths) {

				System.out.println(selectlist.getText());
			}

			allMonths.addAll(selectedmonths);

			System.out.println("All the added months list");

			for (WebElement e : allMonths) {
				System.out.println(e.getText());
			}

			for (WebElement e : allMonths) {
				String singMonth = e.getAttribute("innerHTML");

				if (month.contains(singMonth)) {
					System.out.println(singMonth);
					// wait.until(ExpectedConditions.elementToBeSelected(e));
					Thread.sleep(2000);
					e.click();

				}

			}
			WebElement calOkButton = driver.findElement(By.id("MainContent_ASPxCallbackPanel1_deDate_DDD_C_FNP_BO"));
			calOkButton.click();

			Thread.sleep(2000);
			for (WebElement e1 : workingDates) {
				if (e1.getText().equals(day)) {
					e1.click();
					break;
				}

			}
		} else {
			System.out.println("list is null");
		}

	}

}
