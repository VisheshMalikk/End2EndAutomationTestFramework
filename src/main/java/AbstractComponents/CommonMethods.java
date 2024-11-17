package AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageFiles.CartPage;
import PageFiles.OrdersPage;

public class CommonMethods {

	WebDriver driver;

	// this class will be the parent of all page classes because it holds all reuseable methods
	public CommonMethods(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.XPATH, using="//button[@routerlink='/dashboard/cart']")
	WebElement cartPageLink;
	
	@FindBy(how = How.XPATH, using="//button[@routerlink='/dashboard/myorders']")
	WebElement ordersPageLink;

	public void waitForElementToAppear(By loc) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}

	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(By loc) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(loc));
	}
	
	public CartPage goToCartPage() {
		cartPageLink.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	
	public OrdersPage goToOrdersPage() {
		ordersPageLink.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
