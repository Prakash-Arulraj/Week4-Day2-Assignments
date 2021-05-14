package week4.day2.Assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewIncidentwoFilling {

	public static void main(String[] args) throws InterruptedException {
		/*Create new incident without filling mandatory field	
		 * 1. Launch ServiceNow application	 
		 * 2. Login with valid credentials username as admin and password as India@123	 
		 * 3. Enter Incident in filter navigator and press enter"	
		 * 4.  Click on Create new option and Create new incident without filling the manadatory fields (Short description & Caller)	
		 * 5. Verify the error message displayed on the top	"The following mandatory fields are not filled in: Short description, Caller" error message should be displayed
         
         */
         
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// 1. Launch ServiceNow application
		driver.get("https://dev103117.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// 2. Login with valid credentials username as admin and password as India@123
		driver.switchTo().frame("gsft_main");
		Thread.sleep(2000);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();

		// 3. Enter Incident in filter navigator and press enter
		driver.findElement(By.id("filter")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By.xpath("//span[text()='Incident']")).click();
		driver.findElement(By.xpath("//div[text()='Create New']")).click();
		
		//4.  Click on Create new option and Create new incident without filling the manadatory fields (Short description & Caller)
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_insert")).click();
		
		//5. Verify the error message displayed on the top	
		//"The following mandatory fields are not filled in: Short description, Caller" error message should be displayed
		WebElement error = driver.findElement(By.xpath("//div[contains(@class,'outputmsg outputmsg_error')]"));
		System.out.println(error.getText());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
