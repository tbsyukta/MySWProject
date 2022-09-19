package sw.dailyschedule.manage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class incremetnreq {

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
			if (eText.equalsIgnoreCase("CED")) {
				e.click();
				break;
			}
		}
		// switch to first frame
		driver.switchTo().frame("mainframe");
		// switch to second frame
		driver.switchTo().frame("TabContent");

		// click on add shift button

		WebElement manageshift = driver.findElement(By.xpath("//div[contains(@id,'ManageShifts_CD')]"));
		manageshift.click();
		Thread.sleep(2000);

		Thread.sleep(2000);

		List<Integer> list1 = new ArrayList<Integer>();

		List<WebElement> reqcolumn = driver
				.findElements(By.xpath("//tr[contains(@id,'MainShiftGrid_DXDataRow')]//td[4]"));
		for (WebElement reqcolumnSingle : reqcolumn) {
			String reqcolumnTxt = reqcolumnSingle.getText();
			int reqcolumnInt = Integer.parseInt(reqcolumnTxt);
			list1.add(reqcolumnInt);
		}

		List<Integer> list2 = new ArrayList<Integer>();

		List<WebElement> hrcolumn = driver
				.findElements(By.xpath("//tr[contains(@id,'MainShiftGrid_DXDataRow')]//td[5]"));
		for (WebElement hrcolumnSingle : hrcolumn) {
			String hrcolumnTxt = hrcolumnSingle.getText();
			int hrcolumnInt = Integer.parseInt(hrcolumnTxt);
			list2.add(hrcolumnInt);
		}

		System.out.println("before update list req" +list1);
		System.out.println("before update list hrd" +list2);
		int l = 1;

		for (int i = 0; i < list1.size(); i++) {
			int req = list1.get(i);
			int hrd = list2.get(i);
			if (hrd == 0 && req == 0) {

				for (int k = 1; k <= 2; k++) {
					WebElement clickonincreaseicon = driver
							.findElement(By.xpath("//a[contains(@id,'MainShiftGrid_DXCBtn" + l + "')]//img"));
					clickonincreaseicon.click();
					Thread.sleep(2000);

					req++;

				}
				list1.set(i, req);

			}
			l = l + 4;

		}

		System.out.println("after update list req" +list1);
		System.out.println("after update list hrd" +list2);
		

		// System.out.println(list1);

//		List<Integer> list3 = new ArrayList<Integer>();
//
//		List<WebElement> reqcolumn1 = driver
//				.findElements(By.xpath("//tr[contains(@id,'MainShiftGrid_DXDataRow')]//td[4]"));
//		for (WebElement reqcolumnSingle1 : reqcolumn1) {
//			String reqcolumnTxt1 = reqcolumnSingle1.getText();
//			int reqcolumnInt1 = Integer.parseInt(reqcolumnTxt1);
//			list3.add(reqcolumnInt1);
//		}
//
//		List<Integer> list4 = new ArrayList<Integer>();
//
//		List<WebElement> hrcolumn1 = driver
//				.findElements(By.xpath("//tr[contains(@id,'MainShiftGrid_DXDataRow')]//td[5]"));
//		for (WebElement hrcolumnSingle1 : hrcolumn1) {
//			String hrcolumnTxt1 = hrcolumnSingle1.getText();
//			int hrcolumnInt1 = Integer.parseInt(hrcolumnTxt1);
//			list4.add(hrcolumnInt1);
//		}

		int j = 3;

		for (int i = 0; i < list1.size(); i++) {
			int req = list1.get(i);
			int hrd = list2.get(i);

			for (int k = 0; k < req; k++) {
				if (hrd < req) {

					WebElement clickonpeopleicon = driver
							.findElement(By.xpath("//a[contains(@id,'MainShiftGrid_DXCBtn" + j + "')]//img"));
					clickonpeopleicon.click();

					Thread.sleep(2000);

					driver.switchTo().defaultContent();
					driver.switchTo().frame("popupmain_CIF-1");

					WebElement clickaddicon = driver
							.findElement(By.xpath("//a[contains(@id,'UnassignedStaff_DXCBtn0')]//img"));
					clickaddicon.click();

					Thread.sleep(2000);

					try {

						WebElement confirmyes = driver.findElement(
								By.xpath("//div[contains(@id,'ctl00_MainContent_popupAddReq_btnDeleteOK_CD')]"));
						confirmyes.click();

					} catch (Throwable t) {

						WebElement confirmyes_second = driver
								.findElement(By.xpath("//div[contains(@id,'btnAddOK_CD')]"));
						confirmyes_second.click();

					}

					Thread.sleep(2000);

					driver.switchTo().defaultContent();

					WebElement cancel = driver.findElement(By.xpath("//div[contains(@id,'popupmain_HCB-1')]//img"));
					cancel.click();

//				WebElement cancel = driver.findElement(By.xpath("//div[contains(@id,'popupmain_HCB-1')]//img"));
//				cancel.click();

					Thread.sleep(2000);

					driver.switchTo().frame("mainframe");
					// switch to second frame
					driver.switchTo().frame("TabContent");
					Thread.sleep(2000);

					hrd++;

//					System.out.println("req: " + req + ", hrd: " + hrd);
				}
			}
			j = j + 4;
		}
		System.out.println("after req > hrd  list red " +list1);
		System.out.println("after req > hrd  list hrd " +list2);
		
		int s = 1;

		for (int i = 0; i < list1.size(); i++) {
			int req = list1.get(i);
			int hrd = list2.get(i);

//				
			if (hrd > req) {

				for (int h = req; h < hrd; h++) {

					WebElement clickonincreaseicon = driver
							.findElement(By.xpath("//a[contains(@id,'MainShiftGrid_DXCBtn" + s + "')]//img"));
					clickonincreaseicon.click();
					Thread.sleep(2000);

				}
			}
			s = s + 4;

		}
		
		System.out.println("after req < hrd  list red " +list1);
		System.out.println("after req < hrd  list hrd " +list2);
//		List<Integer> list5 = new ArrayList<Integer>();
//
//		List<WebElement> reqcolumn2 = driver
//				.findElements(By.xpath("//tr[contains(@id,'MainShiftGrid_DXDataRow')]//td[4]"));
//		for (WebElement reqcolumnSingle2 : reqcolumn2) {
//			String reqcolumnTxt2 = reqcolumnSingle2.getText();
//			int reqcolumnInt2 = Integer.parseInt(reqcolumnTxt2);
//			list5.add(reqcolumnInt2);
//		}
//
//		List<Integer> list6 = new ArrayList<Integer>();
//
//		List<WebElement> hrcolumn2 = driver
//				.findElements(By.xpath("//tr[contains(@id,'MainShiftGrid_DXDataRow')]//td[5]"));
//		for (WebElement hrcolumnSingle2 : hrcolumn2) {
//			String hrcolumnTxt2 = hrcolumnSingle2.getText();
//			int hrcolumnInt2 = Integer.parseInt(hrcolumnTxt2);
//			list6.add(hrcolumnInt2);
//		}

	}

}
