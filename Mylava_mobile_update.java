import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Retailer_update4 {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner sc = new Scanner(new File("E:\\open_incident_09-05-22_01_33.csv"));
		// parsing a CSV file into the constructor of Scanner class

		Map<String, String> retailer_details = new HashMap<>();
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			str = str.replaceAll(",", " ");
			String[] split_str = str.split(" ");
			String incident_id = split_str[0];
			String retailer_code_mobile = split_str[1] + " " + split_str[2];
			retailer_details.put(incident_id, retailer_code_mobile);
		}

		// setting the chrome driver exe file path
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe");
		// creating and lunching the chrome browser
		WebDriver driver = new ChromeDriver();
		// entering the url to search in chrome
		driver.get("https://mylava.indlava.com/login");
		// this will maximize the window
		driver.manage().window().maximize();
		// login with mylava nhq admin
		WebElement useridInput = driver.findElement(By.id("userid"));
		useridInput.sendKeys("nhqadmin");
		WebElement passInput = driver.findElement(By.name("pass"));
		passInput.sendKeys("nhqadmin");
		WebElement btnLogin = driver.findElement(By.id("btnLogin"));
		btnLogin.click();

		// getting the value of map one by one

		for (Map.Entry<String, String> entry : retailer_details.entrySet()) {
			// getting the retailer update window
			driver.get("https://mylava.indlava.com/UpdateUser/update_mobile");
			String incident = entry.getKey();
			String r_code_mobile = entry.getValue();
			String[] code_mobile = r_code_mobile.split(" ");
			System.out.println("incident id=" + incident + " code=" + code_mobile[0] + " mobile=" + code_mobile[1]);

			WebElement inputRetailer = driver.findElement(By.id("retailer_code"));
			// inputing retailer code
			inputRetailer.sendKeys(code_mobile[0]);
			// searching retailer edit_btn
			WebElement btn_submit = driver.findElement(By.id("btnSubmit"));
			btn_submit.click();
			WebElement btn_edit = driver.findElement(By.id("edit_btn"));
			btn_edit.click();

			// retailer_mobile_no
			WebElement inputRetailer_mobile = driver.findElement(By.id("retailer_mobile_no"));
			// inputing retailer mobile number and clear the previous number
			inputRetailer_mobile.clear();
			inputRetailer_mobile.sendKeys(code_mobile[1]);// update_no
			WebElement btn_update_no = driver.findElement(By.id("update_no"));
			btn_update_no.click();
			driver.switchTo().alert().accept();
			WebElement update_messeses = driver.findElement(By.className("alert"));
			String messege = update_messeses.getText();
			System.out.println("key-" + entry.getKey() + "" + "value-" + entry.getValue() + " " + messege);

			// closing ticket

			driver.get("https://mylava.indlava.com/AssignedIncidents/open");
			WebElement incident_search_box = driver.findElement(By.id("incident_id"));
			incident_search_box.sendKeys(incident);
			// btnSearch
			WebElement incident_search_btn = driver.findElement(By.id("btnSearch"));
			incident_search_btn.click();
			// fa-eye
			WebElement incident_eye_btn = driver.findElement(By.className("fa-eye"));
			incident_eye_btn.click();

			// comments
			WebElement incident_textarea = driver.findElement(By.id("comments"));
			incident_textarea.sendKeys(messege);

			// resolve
			WebElement resolve_btn = driver.findElement(By.id("resolve"));
			resolve_btn.click();

		}

		driver.close();
		driver.quit();

	}

}
