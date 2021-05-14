package week4.day2.Assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteIncident {

	public static void main(String[] args) throws InterruptedException {
		
		/*Delete Incident	
		1. Launch ServiceNow application	
		2. Login with valid credentials username as admin and password as India@123	
	    3. Enter Incident in filter navigator and press enter
		4. Search for the existing incident and navigate into the incident	
		5. Delete the incident	
		6. Verify the deleted incident	Deleted incident should not be available
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
		driver.findElement(By.id("filter")).sendKeys("incidents", Keys.ENTER);
		driver.findElement(By.xpath("(//div[text()='Incidents'])[2]")).click();
		
		// 4. Search for the existing incident and click on the incident
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//span[@class='input-group-addon input-group-select']//select[1]"))
			  .sendKeys("Number");
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		search.sendKeys("INC0009009", Keys.ENTER);
		driver.findElement(By.xpath("//input[@title='Mark record for List Action']/following-sibling::label[1]")).click();
		
		//5. Delete the incident & 6. Verify the deleted incident Deleted incident should not be available
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		driver.findElement(By.id("sysverb_delete")).click();
		driver.findElement(By.xpath("//div[text()='Delete this record?']")).getText();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='modal-footer'])[2]")).click();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
