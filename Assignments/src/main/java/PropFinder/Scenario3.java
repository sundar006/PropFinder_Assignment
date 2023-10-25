package PropFinder;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario3 {
	
	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.propertyfinder.bh/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.manage().window().maximize();
		//Search for Bahrain Bay
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[contains(@class, 'autocomplete__input')])[1]")));
		driver.findElement(By.xpath("(//input[contains(@class, 'autocomplete__input')])[1]")).click();
		driver.findElement(By.xpath("(//input[contains(@class, 'autocomplete__input')])[1]")).sendKeys("Bahrain Bay");
		driver.findElement(By.xpath("//button[@data-testid='suggestion']")).click();
		
		WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//div[contains(@class,'filter-form-component-variant__row')])[1]/button)[3]")));
		driver.findElement(By.xpath("((//div[contains(@class,'filter-form-component-variant__row')])[1]/button)[3]")).click();
		
		//Open first result
		WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait3.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ul[@aria-label='Properties']/li)[1]")));
		driver.findElement(By.xpath("(//ul[@aria-label='Properties']/li)[1]")).click();
		
		//Verify Available date is present
		WebElement availDate = driver.findElement(By.xpath("(//span[text()='Available from:']//following::div)[1]"));
		if(availDate.isDisplayed())
		{
		String hasDate = driver.findElement(By.xpath("(//span[text()='Available from:']//following::div)[1]")).getText();
		//System.out.println(hasDate);
		if(hasDate.isBlank())
		{
			System.out.println("Available Date is Blank");
		}
		else {
			System.out.println("Available Date is Present");
		}
		}
		else {
			System.out.println("Available Date is not there for this Property ");
		}

	}

}
