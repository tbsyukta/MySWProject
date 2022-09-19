package sw.dailyschedule;

import java.time.Duration;
import java.util.List;

import org.asynchttpclient.spnego.SpnegoTokenGenerator;
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

public class followscenario {

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
	public void follopath() throws InterruptedException {

//			driver.findElement(By.xpath("//a[normalize-space()='Schedule']")).click();
//			Actions actions = new Actions(driver);
//	    	WebElement menuOption = driver.findElement(By.xpath("//div[@id='nav-quicklinks-dropdown-listsA3']//a[normalize-space()='Daily Schedule']"));
//	    	actions.moveToElement(menuOption).perform();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Actions actions = new Actions(driver);

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

//		WebElement schedule = driver.findElement(By.xpath("//a[normalize-space()='Schedule']"));
//		actions.moveToElement(schedule).perform();
//		Thread.sleep(4000);

		// switch to first frame
		driver.switchTo().frame("mainframe");
		// switch to second frame
		driver.switchTo().frame("TabContent");

		// click on add shift button
		WebElement addshift = driver.findElement(By.xpath("(//div[contains(@id,'AddShift')])[2]"));
		addshift.click();
		Thread.sleep(2000);

		// pop up will open
		// switch to default frame
		driver.switchTo().defaultContent();
		// switch to pop up frame
		driver.switchTo().frame("popupmain_CIF-1");

		// click on shift type dropdown
		WebElement shifttxt = driver
				.findElement(By.xpath("(//input[@class='dxeEditArea_ShiftwizardDevxtheme dxeEditAreaSys'])[1]"));
		shifttxt.click();
		Thread.sleep(2000);

		// String ShiftText = "C23";
		// get list of shift tyope
//		List<WebElement> shiftList = driver
//				.findElements(By.xpath("//table[@id='MainContent_cmbShifttypelist_DDD_L_LBT']//tr"));
//
//		//for loop for shift type matches with given string 
//		for (WebElement esss : shiftList) {
//		
//			//get list of each namestring in variable 
//			String eshiftText = esss.getText();
//			
//			//Thread.sleep(2000);
//			
//			System.out.println(eshiftText);
//			
//			//compare
//			if (eshiftText.equalsIgnoreCase("ShiftNumber01")) {
//				Thread.sleep(2000);
//				esss.click();//click on match string 
//				
//				System.out.println("inside if loop ");
//				break;
//
//			}
//			

		List<WebElement> typelist = driver
				.findElements(By.xpath("//table[@id='MainContent_cmbShifttypelist_DDD_L_LBT']//tr"));
		OuterLoop:
		for (WebElement typee : typelist) {
			String sstText = typee.getText();
			System.out.println(sstText);
			if (sstText.equalsIgnoreCase("DISASTER-AM")) {
				typee.click();
				break;

			}
		
		

		// if condition false then goto else part
		
		
		
		else  {
				//switch to default frame
				driver.switchTo().defaultContent();
				//close popup of add shift 
				driver.findElement(By.xpath("//div[@id='popupmain_HCB-1']/img")).click();
				//switch to default frame
				driver.switchTo().defaultContent();

				//xpath of template menu 
				WebElement template = driver.findElement(By.xpath("(//a[contains(text(),'Templates')])[1]"));
				//xpath of schedule item menu  
				WebElement shceitems = driver.findElement(By.xpath("(//a[contains(text(),'Schedule Items')])[1]"));
				//xpath of shift type menu
				WebElement shifttype = driver.findElement(By.xpath("(//a[contains(text(),'Shift Types')])[1]"));
		
				
				actions.moveToElement(template).perform();
				actions.moveToElement(shceitems).perform();
				shifttype.click();//click on shift type menu
				Thread.sleep(3000);

				//switch to frame of page
				driver.switchTo().frame("mainframe");

				//click on first row's add icon
				driver.findElement(By.xpath("//a[@id='ctl00_MainContent_ASPxGridView1_DXCBtn1']")).click();

				//wait 
				new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
						.elementToBeClickable(By.xpath("//td[contains(@id,'DXEFL_DXEditor2_B-1')]//img")));

				//xpath of shift status drop down
				driver.findElement(By.xpath("//td[contains(@id,'DXEFL_DXEditor2_B-1')]//img")).click();

				//get status options in list
				List<WebElement> status = driver
						.findElements(By.xpath("//div[contains(@id,'DXEditor2_DDD_L_D')]//table[2]//tr//td"));

				//for loop for status 
				for (WebElement s : status) {
					String sText = s.getText();
					// System.out.println(eText);
					
					//if condition to compare status string with drop down values
					if (sText.equalsIgnoreCase("Active")) {
						s.click();
						break;

					}
				}
				
				Thread.sleep(1000);
				
				//send key to shift name 
				driver.findElement(By.xpath("//input[contains(@id,'DXEFL_DXEditor13_I')]")).sendKeys("ShiftNumber02");
				
				//send key to shift description
				driver.findElement(By.xpath("//input[contains(@id,'DXEFL_DXEditor3_I')]"))
						.sendKeys("ShiftNumber02Desc");
				
				//send key to sort number 
				driver.findElement(By.xpath("//input[contains(@id,'DXEFL_DXEditor5_I')]")).sendKeys("020202");

				//click on clock in drop down
				driver.findElement(By.xpath("//td[contains(@id,'DXEFL_DXEditor12_B-1')]//img")).click();

				//get values of dro pdown in list
				List<WebElement> clcokintime = driver.findElements(By.xpath(
						"//table[contains(@id,'DXEditor12_DDD_L_LBT')]//td[contains(@class,'ShiftwizardDevxtheme')]"));

				//for loop for selecting particular time
				for (WebElement cti : clcokintime) {
					String ctiText = cti.getText();
					// System.out.println(eText);
					//if condition to compare time
					if (ctiText.equalsIgnoreCase("00:15")) {
						cti.click();
						break;

					}
				}

				new WebDriverWait(driver, Duration.ofSeconds(25)).until(
						ExpectedConditions.elementToBeClickable(By.xpath("//td[contains(@id,'DXEditor6_B-1')]//img")));

				driver.findElement(By.xpath("//td[contains(@id,'DXEditor6_B-1')]//img")).click();

				List<WebElement> clcokouttime = driver.findElements(By.xpath(
						"//table[contains(@id,'DXEditor6_DDD_L_LBT')]//td[contains(@class,'ShiftwizardDevxtheme')]"));

				for (WebElement cto : clcokouttime) {
					String ctoText = cto.getText();
					// System.out.println(eText);
					if (ctoText.equalsIgnoreCase("02:15")) {
						cto.click();
						break;

					}

				}

				Thread.sleep(3000);

				driver.findElement(By.xpath("//td[contains(@id,'DXEditor10_B-1')]//img")).click();

				List<WebElement> category = driver
						.findElements(By.xpath("//table[contains(@id,'DXEditor10_DDD_L_LBT')]//tr"));

				for (WebElement cate : category) {
					String cateText = cate.getText();
					// System.out.println(eText);
					if (cateText.equalsIgnoreCase("Class")) {
						cate.click();
						break;

					}

				}
				Thread.sleep(3000);

				driver.findElement(By.xpath("//td[contains(@id,'DXEFL_DXEditor14_B-1')]")).click();

				List<WebElement> self = driver
						.findElements(By.xpath("//table[contains(@id,'DXEditor14_DDD_L_LBT')]//tr"));

				for (WebElement selff : self) {
					String selfText = selff.getText();
					// System.out.println(eText);
					if (selfText.equalsIgnoreCase("Yes")) {
						selff.click();
						break;

					}

				}

				Thread.sleep(3000);
				driver.findElement(By.xpath("//td[contains(@id,'DXEFL_DXEditor15_B-1')]")).click();

				List<WebElement> list = driver
						.findElements(By.xpath("//table[contains(@id,'DXEFL_DXEditor15_DDD_L_LBT')]//tr"));

				for (WebElement nlist : list) {
					String nText = nlist.getText();
					// System.out.println(eText);
					if (nText.equalsIgnoreCase("Yes")) {
						nlist.click();
						break;

					}

				}

				driver.findElement(By.xpath("//a[contains(@id,'DXEFL_DXCBtn100')]//img")).click();
				driver.findElement(By.xpath("//div[contains(@id,'DXPEForm_HCB-1')]//img")).click();

				Thread.sleep(3000);
				driver.switchTo().defaultContent();
				
				WebElement schedule = driver.findElement(By.xpath("//a[normalize-space()='Schedule']"));
				WebElement dailyschedule = driver.findElement(By.xpath("(//a[contains(text(),'Daily Schedule')])[1]"));

				actions.moveToElement(schedule).perform();
				dailyschedule.click();

				driver.switchTo().frame("mainframe");
				driver.switchTo().frame("TabContent");

				WebElement adddshift = driver.findElement(By.xpath("(//div[contains(@id,'AddShift')])[2]"));
				addshift.click();
				Thread.sleep(2000);

				driver.switchTo().defaultContent();
				driver.switchTo().frame("popupmain_CIF-1");

				WebElement shiffttxt = driver.findElement(
						By.xpath("(//input[@class='dxeEditArea_ShiftwizardDevxtheme dxeEditAreaSys'])[1]"));
				shifttxt.click();
				Thread.sleep(2000);

				break OuterLoop;
			
		}
//			driver.findElement(By.xpath("//td[contains(@id,'cmbShiftBeginTime_B-1')]")).click();
//			
//			List<WebElement> stime = driver
//					.findElements(By.xpath("//table[contains(@id,'cmbShiftBeginTime_DDD_L_LBT')]//tr"));
//
//			for (WebElement sttime : stime) {
//				String sText = sttime.getText();
//				// System.out.println(eText);
//				if (sText.equalsIgnoreCase("00:15")) {
//					stime.click();
//					break;
//
//				}
//
			//}

//			driver.findElement(By.xpath("//td[contains(@id,'cmbShiftEndTime_B-1')]")).click();
//			
//			List<WebElement> etime = driver
//					.findElements(By.xpath("//table[contains(@id,'cmbShiftEndTime_DDD_L_LBT')]//tr"));
//
//			for (WebElement endtime : etime) {
//				String eText1 = endtime.getText();
//				// System.out.println(eText);
//				if (eText1.equalsIgnoreCase("03:15")) {
//					endtime.click();
//					break;
//}}}
		
		
	
				}

			driver.switchTo().defaultContent();
			// switch to pop up frame
			driver.switchTo().frame("popupmain_CIF-1");
			
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//input[contains(@name,'ctl00$MainContent$txt3')]")).sendKeys("2");
			
			driver.findElement(By.id("MainContent_btnAddShift")).click();
			driver.findElement(By.xpath("//div[contains(@id,'popupmain_HCB-1')]//img")).click();
			
			Thread.sleep(3000);
			
			System.out.println("Test");

}}
