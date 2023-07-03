package br.com.tasks.funcional;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAlicacao() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:\\Desenvolvimento\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		//DesiredCapabilities cap = DesiredCapabilities.chrome();
		//WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.23:4444/wd/hub"), cap);
		//driver.navigate().to("http://192.168.1.23:4444:8001/tasks");
		driver.navigate().to("http://192.168.1.23:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAlicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("descricao Automacao");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAlicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAlicacao();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("descricao Automacao");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			driver.quit();
		}
	}

	@Test
	public void deveSalvarTarefaComDataPassada() throws MalformedURLException, InterruptedException {
		WebDriver driver = acessarAlicacao();
		try {
			Thread.sleep(000);
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("descricao Automacao");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			driver.findElement(By.id("saveButton")).click();
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			driver.quit();
		}
	}
}
