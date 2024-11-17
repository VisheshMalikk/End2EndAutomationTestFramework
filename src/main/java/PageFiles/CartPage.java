package PageFiles;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.CommonMethods;

public class CartPage extends CommonMethods {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class='cartSection']//h3")
	List<WebElement> cartProducts;

	@FindBy(how = How.XPATH, using = "//button[text()='Checkout']")
	WebElement checkOutBtn;
	

	public boolean fnVerifyProductAvailableInCartList(String productName) {
		boolean match = false;
		for (WebElement webElement : cartProducts) {
			if (webElement.getText().equalsIgnoreCase(productName)) {
				match = true;
			} else {
				match = false;
			}
		}
		return match;
	}
	
	public CheckoutPage goToCheckOut() {
		checkOutBtn.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	

}
