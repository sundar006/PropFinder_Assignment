package PropFinder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Scenario2 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.propertyfinder.bh/");
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//label[contains(text(),'Show commercial properties only')]")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'filter-form-component-variant__row')])[1]//button[3]")));
		driver.findElement(By.xpath("(//div[contains(@class,'filter-form-component-variant__row')])[1]//button[3]")).click();
		driver.findElement(By.xpath("//span[text()='Offices']/..")).click();
		
		//Get the count of All properties
				List<String> Actual = new ArrayList<String>();
				WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(30));
				WebElement element1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'styles_desktop_aggregation')]/li//following-sibling::span")));
				List<WebElement> results = driver.findElements(By.xpath("//ul[contains(@class, 'styles_desktop_aggregation')]/li//following-sibling::span"));		
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
				WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(30));
				WebElement element2 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='Search results count']")));
				String total = driver.findElement(By.xpath("//span[@aria-label='Search results count']")).getText();
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
				
				//Open first result
				driver.findElement(By.xpath("(//ul[@aria-label='Properties']/li)[1]")).click();
				//Verify the details are present
				//Price
				boolean price = driver.findElement(By.xpath("(//div[@class='property-price'])[3]")).isDisplayed();
				 if(price) {
					 System.out.println("Price element is present");
				 }
				 else {
					 System.out.println("Price element is not present");
				 }
				 //Description
				 boolean description = driver.findElement(By.xpath("//div[@class='property-page__description']")).isDisplayed();
				 if(description) {
					 System.out.println("Description Text is present");
				 }
				 else {
					 System.out.println("Description is not present");
				 }
				 //Location
				 boolean location = driver.findElement(By.xpath("//div[@class='property-location']")).isDisplayed();
				 if(location) {
					 System.out.println("Location is present");
				 }
				 else {
					 System.out.println("Location is not present");
				 }
				 //PropertySize
				 boolean propSize = driver.findElement(By.xpath("//ul[@class='property-facts']/li[2]")).isDisplayed();
				 if(propSize) {
					 System.out.println("PropertySize is present");
				 }
				 else {
					 System.out.println("PropertySize is not present");
				 }
				 //Bathrooms
				 boolean bathrooms = driver.findElement(By.xpath("//ul[@class='property-facts']/li[3]")).isDisplayed();
				 if(bathrooms) {
					 System.out.println("Bathrooms text is present");
				 }
				 else {
					 System.out.println("Bathrooms text is not present");
				 }

	}

}
