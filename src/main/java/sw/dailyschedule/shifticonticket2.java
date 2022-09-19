package sw.dailyschedule;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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
import org.openqa.selenium.JavascriptExecutor;

public class shifticonticket2 {

	public static WebDriver driver;

	@BeforeSuite
	public void setupdriver() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\techbrain\\Downloads\\chromedriver.exe");

		driver = new ChromeDriver();

		// CMD print stmt of driver setup successfully
	//	System.out.println("driver setup successfully");

		// maximize the window
		driver.manage().window().maximize();

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// pass URL to chromedriver
		driver.get("https://wakemed-new.dev.myshiftwizard.com/ShiftWizard.aspx");
		//System.out.println("URL setup successfully");
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
	//	System.out.println("SuperUser logged in");

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

//		driver.findElement(By.xpath("//div[contains(@id,'MainContent_ActionShift_CD')]")).click();
//
//		Thread.sleep(3000);
//
//		driver.findElement(By.xpath("//div[contains(@id,'MainContent_ctl01_ctl15')]")).click();
//
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//div[contains(@id,'MainContent_ShiftButton')]")).click();
//
//		Thread.sleep(3000);
//		List<WebElement> shifttype = driver.findElements(By.xpath("//div[contains(@id,'ShiftButtons')]//span"));
//
//		int maxShift = shifttype.size();
//		Random random0 = new Random();
//		int randomshifttype = random0.nextInt(maxShift);
//		Thread.sleep(3000);
//		shifttype.get(randomshifttype).click();
//		Thread.sleep(3000);
//		
//		driver.findElement(By.xpath("//div[contains(@id,'MainContent_PositionButton')]")).click();
//		Thread.sleep(3000);
//		
//		List<WebElement> productElems = driver.findElements(By.xpath("//div[contains(@id,'PositionButtons')]//span"));
//		int maxProducts = productElems.size();
//		Random random = new Random();
//		int randomProduct = random.nextInt(maxProducts);
//		Thread.sleep(3000);
//		productElems.get(randomProduct).click();
//
//		Thread.sleep(3000);
//
//		List<WebElement> staffname = driver.findElements(
//				By.xpath("//table[contains(@id,'MainContent_DeptScheduleCallback_deptschedule')]//tbody//tr"));
//	
//		int maxStaff = staffname.size();
//		Random random1 = new Random();
//		int randomStaff = random1.nextInt(maxStaff);
//		Thread.sleep(3000);
//		staffname.get(randomStaff).findElement(
//				(By.xpath("//table[contains(@id,'MainContent_DeptScheduleCallback_deptschedule')]//tbody//td[3]")))
//				.click();
//		Thread.sleep(3000);
//		driver.findElement(By.xpath("//div[@id='MainContent_ExitShiftButton']")).click();
//
//		Thread.sleep(4000);
//		// List<WebElement> staff =
//		// driver.findElements(By.xpath("//table[contains(@id,'MainContent_DeptScheduleCallback_deptschedule')]//tbody//tr"));
//
		List<WebElement> staffspan = driver
				.findElements(By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']/tbody/tr/td/span"));

		int staffspan1 = staffspan.size();
		Random random3 = new Random();
		int randomStaff1 = random3.nextInt(staffspan1);
		Thread.sleep(3000);
		WebElement test = staffspan.get(randomStaff1);
		actions.contextClick(test).perform();
		Thread.sleep(3000);

		System.out.println("id of random span shift 0 " + randomStaff1);

		WebElement addicon = driver.findElement(By.xpath("/html/body/ul/li[9]/span"));
		// WebElement editshift2 =
		// driver.findElement(By.xpath("/html/body/ul/li[7]/ul/li/span"));
		addicon.click();
//		actions.moveToElement(addicon).perform();
		Thread.sleep(3000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		// driver.findElement(By.xpath("//*[@id='MainContent_rdUserScheduleType']/tbody/tr[1]/td[1]/label")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id='MainContent_ddlShifts']")).click();
		Thread.sleep(3000);

		List<WebElement> typelist = driver.findElements(By.xpath("//*[@id='MainContent_ddlShifts']/option"));
		int typelistt = typelist.size();
		Random ranshift = new Random();
		Thread.sleep(2000);
		int randomtype = ranshift.nextInt(typelistt);
		Thread.sleep(3000);
		WebElement typelistselect = typelist.get(randomtype);
		typelistselect.click();
		
		String nameoftype = typelistselect.getText();
	//	System.out.println("name of selected type " +nameoftype);

		//System.out.println("id of random shift type for icon " + randomtype);

		
		
		Thread.sleep(3000);
		driver.findElement(By.id("MainContent_txtUserScheduleNotes")).sendKeys("Test");
		Thread.sleep(3000);
		driver.findElement(By.id("MainContent_btnSubmit")).click();

		Thread.sleep(3000);

		//// here on same shift on which have we added icon need to shift that shift to
		//// next day from selected date
		//// get id or value of random which is selected
		driver.switchTo().defaultContent();
		driver.switchTo().frame("mainframe");

		
		List<WebElement> findidofspan = driver
				.findElements(By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']/tbody/tr/td/span"));

		int idspan = findidofspan.size();
		Random ranid = new Random();
	//	int randomifspan = ranid.nextInt(idspan);
		Thread.sleep(3000);
		//System.out.println("id of random span shift 1 " + randomStaff1);
		WebElement testid = findidofspan.get(randomStaff1);
		//System.out.println("id of random span shift 2 " + randomStaff1);
		actions.contextClick(testid).perform();
		Thread.sleep(3000);
		
	//	WebElement rightclickforshiftedit = driver.findElement((By.xpath("//*[@id='MainContent_DeptScheduleCallback_deptschedule']/tbody/tr/td/span[" + randomStaff1 + "]")));
		//String randomeid = randomStaff1.getText();
		//System.out.println("id of random span shift 3 " + randomStaff1);
		//actions.contextClick(rightclickforshiftedit).perform();
	
		//need to change td and span index which has more shifts 
		//*[@id='MainContent_DeptScheduleCallback_deptschedule']//tbody//tr//td[6]//span
		//*[@id='MainContent_DeptScheduleCallback_deptschedule']//tbody//tr//td[3]//span[2]
		
		
		// "+ randomStaff1+"
		Thread.sleep(3000);
		WebElement editshift = driver.findElement(By.xpath("/html/body/ul/li[7]"));
		// WebElement editshift2 =
		// driver.findElement(By.xpath("/html/body/ul/li[7]/ul/li/span"));

		actions.moveToElement(editshift).perform();
		Thread.sleep(3000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

		List<WebElement> editshiftlist = driver
				.findElements(By.xpath("//ul[@class='context-menu-list']/li/span[contains(text(),'Edit Shift')]"));
		// int shiftlist = editshiftlist.size();
		// Random random4 = new Random();
		Thread.sleep(2000);
		// int randomShift = random4.nextInt(shiftlist);
		Thread.sleep(3000);

		// editshiftlist.get(randomShift).click();
		for (WebElement shifttypelabel : editshiftlist) {
			String typeText = shifttypelabel.getText();
			//System.out.println("get value from loop : " + typeText);
			if (typeText.contains(nameoftype)) {
				shifttypelabel.click();
				//System.out.println("loof of if : " + typeText);
				break;
			} else {

				//System.out.println(typeText);
			}

		}
		//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/ul/li[7]/ul/li")));
		Thread.sleep(3000);

		driver.switchTo().defaultContent();
		driver.switchTo().frame("popupmain_CIF-1");

		WebElement datetext = driver.findElement(By.xpath("//input[@id='MainContent_ASPxCallbackPanel1_deDate_I']"));
		String datetextt = datetext.getAttribute("value");

	//	System.out.println("Data which is already assign " + datetextt);

		String month = "August 2022";
		String day = "29";

		WebElement SetValue= driver.findElement(By.xpath("//td[@id='MainContent_ASPxCallbackPanel1_deDate_B-1']//img"));
		String SetValueString = SetValue.getText();
		System.out.println("alrready set  month and year: " +SetValueString);
		SetValue.click();
		
		
		
		String get_month =datetextt;
		System.out.println("already set month and year : "+get_month);
		
		
		String get_year="";
		String get_day="";
		
		
		
		
		
		
		
		
	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while (true) {
			String text = driver.findElement(By.xpath("//td[@id='MainContent_ASPxCallbackPanel1_deDate_DDD_C_TC']"))
					.getText();
			if (text.equals(month)) {

				break;

			} else {
				driver.findElement(By.xpath("//td[@id='MainContent_ASPxCallbackPanel1_deDate_DDD_C_NMC']//img"))
						.click();
			}
		}

		
		
		

		
		WebElement dateselected = driver.findElement(
				By.xpath("//*[@id='MainContent_ASPxCallbackPanel1_deDate_DDD_C_mt']//tbody//tr[6]//td[contains(text(),'"
						+ day + "')] "));
		dateselected.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@id='MainContent_ASPxCallbackPanel1_deDate_B-1']//img")).click();
		Thread.sleep(2000);

		WebElement datetexts = driver.findElement(By.xpath("//input[@id='MainContent_ASPxCallbackPanel1_deDate_I']"));
		String ss = datetexts.getAttribute("value");
		//System.out.println("Data which is already assign " + ss);

		driver.findElement(By.xpath("//*[@id='MainContent_ASPxCallbackPanel1_AddShiftEdit_CD']/span")).click();
		driver.findElement(By.xpath("//*[@id='MainContent_ASPxCallbackPanel1_btnSplitShift_CD']/span")).click();

	}

}
