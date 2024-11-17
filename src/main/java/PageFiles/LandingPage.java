package PageFiles;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.CommonMethods;

public class LandingPage extends CommonMethods {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		// this keyword refers to the local class instance
		super(driver);
		this.driver = driver;
		// this refers to here page object class
		//It scans the Page Object for any fields that are annotated with the @FindBy annotation and initializes them with references to the corresponding 
		//web elements on the page. initElements() is an overloaded function which can be modified or used in multiple ways.
		PageFactory.initElements(driver, this);
	}
	
	// Regular way to find out webelement
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	// Page Factory pattern
	// We can use @FindBy annotation in 2 different ways
	
	// First way : just use loactor name directly
	//@FindBy(id="userEmail") At the run time it will be constructed like regular way of finding WebElement driver.findElement like this
	//WebElement UserEmail;
	
	// second way : By How
	@FindBy(how = How.ID, using="userEmail")
	WebElement UserEmail;
	
	@FindBy(how=How.ID, using="userPassword")
	WebElement UserPassword;
	
	@FindBy(how=How.NAME, using="login")
	WebElement loginBtn;
	
	@FindBy(how=How.XPATH, using="//div[contains(@class, 'ng-trigger-flyInOut ngx-toastr toast-error')]")
	WebElement errorMsg;
	
	
	public ProductCataloguePage loginApplication(String email, String password) {
		UserEmail.sendKeys(email);
		UserPassword.sendKeys(password);
		loginBtn.click();
		ProductCataloguePage cataloguePage = new ProductCataloguePage(driver);
		return cataloguePage;
	}
	
	public void goToLandingPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
}
