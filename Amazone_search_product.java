package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazone_search_product {

	public static void main(String[] args) throws InterruptedException {
		//setting the chrome driver exe file path
				System.setProperty("webdriver.chrome.driver", "D:\\Selenium-jars\\chromedriver_win32\\chromedriver.exe");
				
				//creating and lunching the chrome browser
				
				WebDriver driver=new ChromeDriver();
				
				//entering the url to search in chrome
				driver.get("https://www.amazon.fr");
				//this will maximize the window
				driver.manage().window().maximize();
				 WebElement element= driver.findElement(By.id("twotabsearchtextbox"));
				 element.sendKeys("boat earphones");
				 Thread.sleep(4000);
				 WebElement search=driver.findElement(By.id("nav-search-submit-button"));
				 search.click();
				 Thread.sleep(4000);
				 
				 
				 
				 
				
				

	}

}
