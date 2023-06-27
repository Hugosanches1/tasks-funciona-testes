package br.com.tasks.prod;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class HealthCheckIT {
	
	@Test
	public void healtCheck() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:\\Desenvolvimento\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();		
		try {
			driver.navigate().to("http://192.168.1.17:8001/tasks/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//String version = driver.findElement(By.id("version")).getText();
			//Assert.assertTrue(version.startsWith("build_"));
		}finally {
			driver.quit();
		}
	
	}

}
