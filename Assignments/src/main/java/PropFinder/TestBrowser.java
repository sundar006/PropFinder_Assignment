package PropFinder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBrowser {
	 
	public static void main(String[] args) {
		
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
		driver.get("https://www.propertyfinder.bh/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("(//div[contains(@class, \"propertyTypeId\")])[1]")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Villa')]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-qs='HomePageSearchFormComponent']/div/div[3]/div[3]/div[1]/div[1]")));
		
		driver.findElement(By.xpath("//div[@data-qs='HomePageSearchFormComponent']/div/div[3]/div[3]/div[1]/div[1]")).click();
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[contains(@class,\"text-field__input\")])[1]")));
		//driver.findElement(By.xpath("(//input[contains(@class,\"text-field__input\")])[1]")).click();
		driver.findElement(By.xpath("(//input[contains(@class,\"text-field__input\")])[1]")).sendKeys("300000");
		WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(30));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("((//div[contains(@class,\"filter-form-component-variant__row\")])[1]/button)[3]")));
		driver.findElement(By.xpath("((//div[contains(@class,\"filter-form-component-variant__row\")])[1]/button)[3]")).click();
		
		//Get the count of All properties
		List<String> Actual = new ArrayList<String>();
		List<WebElement> results = driver.findElements(By.xpath("//ul[contains(@class, \"styles_desktop_aggregation\")]/li//following-sibling::span"));		
		for(WebElement e: results) {
			String counts = e.getText();
			String updated = counts.replaceAll("[\\[\\](){}]","");
			Actual.add(updated);
			//System.out.println(updated);
		}
			//Convert to int
			List<Integer> intList = new ArrayList<Integer>(Actual.size());
			for(String curr : Actual)
			{
				intList.add(Integer.parseInt(curr));
			}
			//System.out.println(intList);
			
			int totalProps = intList.stream().mapToInt(Integer::valueOf).sum();
			//System.out.println(totalProps);
		
			//Total Prop count
			String total = driver.findElement(By.xpath("//span[@aria-label=\"Search results count\"]")).getText();
			//System.out.println(total);
			String finalss = total.replaceAll("[^0-9.]", "");
			int finalNumber = Integer.parseInt(finalss);
			//System.out.println(finalNumber);
			
			if(totalProps == finalNumber)
			{
				System.out.println("Count matched");
			}
			else {
				System.out.println("Count not matched");
			}
			
			Assert.assertEquals(totalProps, finalNumber);

}
	
}
