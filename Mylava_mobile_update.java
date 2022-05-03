package com.selenium;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Mylava_mobile_update {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\retailers\\Retailers_new1.txt");
		Scanner reader = new Scanner(file);
		List<String> retailer_details = new ArrayList<String>();

		while (reader.hasNextLine()) {

			String data = reader.nextLine();
			retailer_details.add(data);
		}
		
	
		// setting the chrome driver exe file path
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium-jars\\chromedriver_win32\\chromedriver.exe");
		// creating and lunching the chrome browser
		WebDriver driver = new ChromeDriver();

		// entering the url to search in chrome
		driver.get("https://mylava.indlava.com/login");
		// this will maximize the window
		driver.manage().window().maximize();
		// login with mylava nhq admin
		WebElement useridInput = driver.findElement(By.id("userid"));
		useridInput.sendKeys("admin");
		WebElement passInput = driver.findElement(By.name("pass"));
		passInput.sendKeys("admin");
		WebElement btnLogin = driver.findElement(By.id("btnLogin"));
		btnLogin.click();
		// getting the retailer update window
		driver.get("https://mylava.indlava.com/UpdateUser/update_mobile");
		
		for (int i = 0; i<retailer_details.size(); i++) {
			if (i % 2 == 0) {
				System.out.println(retailer_details.get(i) + "-----" + i);
				WebElement inputRetailer = driver.findElement(By.id("retailer_code"));
				// inputing retailer code
				inputRetailer.sendKeys(retailer_details.get(i));// 9829391489
				// searching retailer edit_btn
				WebElement btn_submit = driver.findElement(By.id("btnSubmit"));
				btn_submit.click();
				WebElement btn_edit = driver.findElement(By.id("edit_btn"));
				btn_edit.click();
			}
			if (i % 2 != 0) {
				System.out.println(retailer_details.get(i) + "-----" + i);

				// retailer_mobile_no
				WebElement inputRetailer_mobile = driver.findElement(By.id("retailer_mobile_no"));
				// inputing retailer mobile number and clear the previous number
				inputRetailer_mobile.clear();
				inputRetailer_mobile.sendKeys(retailer_details.get(i));// update_no
				WebElement btn_update_no = driver.findElement(By.id("update_no"));
				btn_update_no.click();
				driver.switchTo().alert().accept();
			}
			
		}
		
		
		
	}

}
