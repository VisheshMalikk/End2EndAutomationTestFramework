package TestFiles;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws Exception {

		String statusPass = "";

		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/client");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.id("userEmail")).sendKeys("Visheshmalikk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("1@Neem2345");
		driver.findElement(By.name("login")).click();

		List<WebElement> products = driver.findElements(By.xpath("//section[@id='products']//div[@class='card']//h5//b"));

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getText().equalsIgnoreCase(productName)) {
				driver.findElements(By.xpath("//button[text()=' Add To Cart']")).get(i).click();
			}
		}

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> productListUnderCart = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));
		for (WebElement product : productListUnderCart) {
			if (product.getText().equalsIgnoreCase(productName)) {
				statusPass = "Pass";
			} else {
				statusPass = "Fail";
			}
		}
		Assert.assertEquals(statusPass, "Pass", "Product is not in the Cart List");
		System.out.println("Product is in the cart list : " + productName);

		// click Checkout button
		driver.findElement(By.xpath("//div[contains(@class, 'subtotal')]//button")).click();

		// payment page
		Actions action = new Actions(driver);

		action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "indi").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class, 'results list-group')]")));

		action.sendKeys(Keys.PAGE_DOWN).build().perform();

		List<WebElement> countryDropdownList = driver.findElements(By.xpath("//section[contains(@class, 'results list-group')]//button"));

		for (int i = 0; i < countryDropdownList.size(); i++) {
			if (countryDropdownList.get(i).getText().equalsIgnoreCase("India")) {
				countryDropdownList.get(i).click();
				break;
			}
		}

		System.out.println("Country is selected successfully .....");
		
		
		
		// Place Order product
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String thankyouOrderMessage = driver.findElement(By.xpath("//h1[contains(text(), 'Thank')]")).getText().toUpperCase().trim();
		Assert.assertEquals(thankyouOrderMessage, "THANKYOU FOR THE ORDER.", "Product Oder Successfully message is not displaying");
		
		System.out.println("Thank you order message : " + thankyouOrderMessage);
		
		Thread.sleep(5000);
		driver.close();

	}

}
