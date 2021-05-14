package week4.day2.Assignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateIncident {

	public static void main(String[] args) throws InterruptedException {
		
		/*
		Create new incident
        1. Launch ServiceNow application	
		2. Login with valid credentials username as admin and password as India@123"
	 	3. Enter Incident in filter navigator and press enter"	
		4. Click on Create new option and fill the manadatory fields	
		5. Verify the newly created incident ( copy the incident number and paste it in search field and enter then verify the instance number created or not)	
		New incident should be created successfully
        */
		
		WebDriverManager.chromedriver().setup();
		
		ChromeDriver driver = new ChromeDriver();
		
		// 1. Launch ServiceNow application
		driver.get("https://dev103117.service-now.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2. Login with valid credentials username as admin and password as India@123"
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("India@123");
		driver.findElement(By.id("sysverb_login")).click();
		
		//3. Enter Incident in filter navigator and press enter
		driver.findElement(By.id("filter")).sendKeys("incident");
		
		//4. Click on Create new option and fill the manadatory fields
		driver.findElement(By.xpath("//div[text()='Create New']")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']//span[1]")).click();
		String number = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("incident number is"+ number);
		
		//switch to next window handle
		Set<String> nextwindow = driver.getWindowHandles();
		List<String> secondwindow = new ArrayList<String>(nextwindow);
		driver.switchTo().window(secondwindow.get(1));
		
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[3]")).click();
		//driver.findElement(By.id("incident.short_description")).sendKeys("test");
		driver.switchTo().window(secondwindow.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("new incident create by prakash");
		
		//submit
		
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		Thread.sleep(3000);
		WebElement forTextNum = driver.findElement(By.xpath("//select[@class='form-control default-focus-outline']")); 
		new Select(forTextNum).selectByIndex(1); 
		driver.findElement(By.xpath("//input[@placeholder='Search']")).sendKeys(number,Keys.ENTER);
		driver.findElement(By.xpath("//label[@class='checkbox-label']"));
		
		String num = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		if(number.equals(num)) {
			System.out.println("incident created successfully");
		}
		else {
			System.out.println("incident not created");
		}
		
		
		
		//INC0011119
		//System Administrator
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
