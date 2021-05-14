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

public class AssignTheInsident {

	public static void main(String[] args) throws InterruptedException {

		/*
		 * Assign the incident 1. Launch ServiceNow application 2. Login with valid
		 * credentials username as admin and password as India@123 3. Enter Incident in
		 * filter navigator and press enter" 4. click on open and Search for the
		 * existing incident and click on the incident need to click open after entering
		 * incident in the filter navigator 5. Assign the incident to Software group 6.
		 * Update the incident with Work Notes 7. Verify the Assignment group and
		 * Assigned for the incident Assignment group and Assigned to should be Software
		 * and ITIL User
		 * 
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
		search.sendKeys("INC0011125", Keys.ENTER);
		driver.findElement(By.xpath("//input[@title='Mark record for List Action']/following-sibling::label[1]")).click();
		
		//5. Assign the incident to Software group
		driver.findElement(By.xpath("//table[@id='incident_table']/thead[1]/tr[1]/th[10]/span[1]/i[1]")).click();
		driver.findElement(By.xpath("//div[@item_id='f55c67f00a0a0b3e0000729159a6ed20']")).click();
		
		WebElement incidentcategory = driver.findElement(By.id("incident.category")); 
		new Select(incidentcategory).selectByIndex(2);
		
		driver.findElement(By.xpath("//button[@id='lookup.incident.assignment_group']//span[1]")).click();
		Set<String> nextwindow = driver.getWindowHandles();
		List<String> secondwindow = new ArrayList<String>(nextwindow);
		driver.switchTo().window(secondwindow.get(1));
		
		WebElement search1 = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		search1.sendKeys("software",Keys.ENTER);
		search1.getText();
		driver.findElement(By.linkText("Software")).click();
		
		//Thread.sleep(5000);
		
		driver.switchTo().window(secondwindow.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("lookup.incident.assigned_to")).click();
		
		nextwindow = driver.getWindowHandles();
		secondwindow = new ArrayList<String>(nextwindow);
		driver.switchTo().window(secondwindow.get(1));
		WebElement assiqnto = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		assiqnto.sendKeys("ITIL User",Keys.ENTER);
		//driver.findElement(By.linkText("ITIL User")).click();
		
		driver.switchTo().window(secondwindow.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("updated by prakash");
		
		//6. Update the incident with Work Notes
		driver.findElement(By.id("incident.work_notes")).sendKeys("create to software group");
		driver.findElement(By.xpath("//button[@id='sysverb_post_update']")).click();
		
		driver.findElement(By.xpath("//label[@class='checkbox-label']")).click();
		String assiqn = driver.findElement(By.linkText("Software")).getText();
		System.out.println(assiqn);
		
		
		
		//7. Verify the Assignment group and Assigned for the incident Assignment group and 
		//Assigned to should be Software and ITIL User
		
		if(search1.equals(assiqn)) {
			System.out.println("Assiqned group is correct");
		}
		else {
			System.out.println("Assiqned group is not correct");
		}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
