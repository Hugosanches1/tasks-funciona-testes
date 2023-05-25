package br.com.tasks.funcional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAlicacao() {
		System.setProperty("webdriver.chrome.driver", "C:\\Desenvolvimento\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAlicacao();
		try {
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("descricao Automacao");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
		driver.findElement(By.id("saveButton")).click();
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", mensagem);
		}finally {
		driver.quit();
		}
	}
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void naoDeveSalvarTarefaSemData() {
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
	public void deveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAlicacao();
		try {
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("descricao Automacao");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		driver.findElement(By.id("saveButton")).click();
		String mensagem = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", mensagem);
		}finally {
		driver.quit();
		}
	}

}
