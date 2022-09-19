package ShiftwizardExcel;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class login {

	
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

	@Test(dataProviderClass=DataUtil.class, dataProvider="dp2")
	public void login(Hashtable<String,String> data) {

		// Pass User name value
		WebElement username = driver.findElement(By.xpath("//input[@id='txtUserName']"));
		username.clear();
		username.sendKeys(data.get("username"));  //username

		// Pass User password Value
		WebElement password = driver.findElement(By.xpath("//input[@id='txtPassword']"));
		password.clear();
		password.sendKeys(data.get("pass")); //pass

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
		//System.out.println("SUperUser logged in");
		//System.out.println(user + pwd+exp);

		String txt = data.get("status");
		
		String e = "valid";
		//String validation=driver.getTitle();
		
		if(e.equalsIgnoreCase(txt))
		{
			
				driver.findElement(By.xpath("//span[@id='sam']")).click();
				driver.findElement(By.xpath("//a[@id='log']")).click();
				
		//		Assert.assertTrue(true);
				System.out.println("correct !");
				
			}
			else {
				
			//	Assert.assertTrue(false);
				System.out.println("Password or Username incorrect !");
			}

	}
	
}
