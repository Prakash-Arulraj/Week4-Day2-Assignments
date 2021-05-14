package week4.day2.Assignments;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UpdateExistingIncident {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * Update existing incident 1. Launch ServiceNow application 2. Login with valid
		 * credentials username as admin and password as India@123 " 3. Enter Incident
		 * in filter navigator and press enter " 4. Search for the existing incident and
		 * click on the incident 5. Update the incidents with Urgency as High and State
		 * as In Progress 6. verify the priority and state
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
				.sendKeys("Number"); // search.sendKeys("Number");
		Thread.sleep(2000);
		WebElement search = driver.findElement(By.xpath("//input[@placeholder='Search']"));
		search.sendKeys("INC0011254", Keys.ENTER);
		driver.findElement(By.xpath("//input[@title='Mark record for List Action']/following-sibling::label[1]"))
				.click();

		// 5. Update the incidents with Urgency as High and State as In Progress
		driver.findElement(By.xpath("//table[@id='incident_table']/thead[1]/tr[1]/th[7]/span[1]/i[1]")).click();
		driver.findElement(By.xpath("//div[@item_id='f55c67f00a0a0b3e0000729159a6ed20']")).click();

		WebElement incidentstate = driver.findElement(By.id("incident.state"));
		new Select(incidentstate).selectByIndex(2);
		Thread.sleep(3000);
		/*
		 * WebElement incidentstate1 =
		 * driver.findElement(By.xpath("//select[@choice='3']")); new
		 * Select(incidentstate1).selectByValue("Text")
		 */;
		String statetext = incidentstate.getAttribute("value");
		System.out.println(statetext);

		WebElement incidentpriority = driver.findElement(By.id("incident.priority"));
		new Select(incidentpriority).selectByIndex(3);

		WebElement incidenturgency = driver.findElement(By.id("incident.urgency"));
		new Select(incidenturgency).selectByIndex(1);
		driver.findElement(By.xpath("//button[@id='sysverb_post_update']")).click();

		// 6. verify the priority and state
		driver.findElement(By.xpath("//label[@class='checkbox-label']")).click();
		String state1 = driver.findElement(By.xpath("//td[text()='In Progress']")).getText();

		if (statetext.equals(state1)) {
			System.out.println("state is equal");

		} else {
			System.out.println("state is not equal");
		}

		if (incidentpriority.equals("3-Moderate")) {
			System.out.println("priority is equal");
		} else {
			System.out.println("priority is not equal");
		}

	}

}
