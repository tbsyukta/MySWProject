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

public class assignstaff {
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
			if (eText.equalsIgnoreCase("CICU")) {
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
					}
					catch(Throwable t) {
						
						WebElement confirmyes_second = driver.findElement(By.xpath("//div[contains(@id,'btnAddOK_CD')]"));
						confirmyes_second.click();
					}
					
					
					Thread.sleep(2000);
					
					driver.switchTo().defaultContent();
					
					WebElement cancel = driver.findElement(By.xpath("//div[contains(@id,'popupmain_HCB-1')]//img"));
					cancel.click();
					
					Thread.sleep(2000);
					
					driver.switchTo().frame("mainframe");
					// switch to second frame
					driver.switchTo().frame("TabContent");
					Thread.sleep(2000);

					hrd++;
					
					System.out.println("req: " + req + ", hrd: " + hrd);
				}
			}
			j = j + 4;
		}

	}

}
