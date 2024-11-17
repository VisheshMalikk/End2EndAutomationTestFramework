package PageFiles;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.CommonMethods;

public class OrdersPage extends CommonMethods {

	WebDriver driver;

	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//table[contains(@class, 'table')]/tbody//tr/td[2]")
	List<WebElement> ordersProductsList;

	public boolean fnVerifyProductPresentInOrder(String productName) {
		boolean match = false;
		for (WebElement product : ordersProductsList) {
			if (product.getText().equalsIgnoreCase(productName)) {
				match = true;
				break;
			} else {
				match = false;
			}
		}
		return match;
	}

}
