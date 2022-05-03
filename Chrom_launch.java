package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrom_launch {

	public static void main(String[] args) {
		
		
		//setting the chrome driver exe file path
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium-jars\\chromedriver_win32\\chromedriver.exe");
		
		//creating and lunching the chrome browser
		
		WebDriver driver=new ChromeDriver();
		
		//entering the url to search in chrome
		driver.get("http://www.google.com");
		//this will maximize the window
		driver.manage().window().maximize();
		
		String title=driver.getTitle();
		
		if(title.equalsIgnoreCase("google")) {
			System.out.println("test succesful");
		}else {
			System.out.println("test failed");
		}
		
		//closing the browser
		driver.quit();
		
		

	}

}
