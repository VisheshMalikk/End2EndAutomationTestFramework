package PageFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.CommonMethods;

public class ConfirmationPage extends CommonMethods {

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH, using="//h1[text()=' Thankyou for the order. ']")
	WebElement confirmationMsg;
	
	
	public String fnVerifyConfirmationMsg() {
		return confirmationMsg.getText();
	}
	
	
	
}
