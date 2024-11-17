package PageFiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.CommonMethods;

public class CheckoutPage extends CommonMethods {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Select Country']")
	WebElement selectCountryInputField;

	By countriesListSection = By.xpath("//section[contains(@class, 'ta-results list-group')]");

	@FindBy(how = How.XPATH, using = "//section[contains(@class, 'ta-results list-group')]/button")
	List<WebElement> countriesList;

	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;

	public void selectCountry() {
		selectCountryInputField.sendKeys("India");
		waitForElementToAppear(countriesListSection);
		for (WebElement country : countriesList) {
			if (country.getText().equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}
	}

	public ConfirmationPage placeOrder() {
		Actions action = new Actions(driver);
		action.moveToElement(placeOrderBtn).click().build().perform();
		ConfirmationPage confirmPage = new ConfirmationPage(driver);
		return confirmPage;
	}

}
