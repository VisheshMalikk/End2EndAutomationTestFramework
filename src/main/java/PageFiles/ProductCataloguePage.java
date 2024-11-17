package PageFiles;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.CommonMethods;

// to use reusable methods from CommonMethods class we can create object of that class and use common methods but but we will use OOPs concept inheritance 
//to extends methods from CommonMethods class because it don't take any memory 

public class ProductCataloguePage extends CommonMethods {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By cataloguePageProductsSection = By.xpath("//section[@id='products']");

	By toastMessage = By.xpath("//div[@id ='toast-container']");

	By spinningIcon = By.xpath("//div[@class='ng-animating']");

	@FindBy(how = How.XPATH, using = "//section[@id='products']//div[@class='card']//h5//b")
	List<WebElement> cataloguePageProducts;
	
	@FindBy(how = How.XPATH, using = "//section[@id='products']//div[@class='card']//button[text()=' Add To Cart']")
	List<WebElement> AddToCartBtn;
	
	
	
	

	public List<WebElement> getProductListFromCataloguePage() {
		waitForElementToAppear(cataloguePageProductsSection);
		return cataloguePageProducts;
	}

	// function click on the product from catalogue page
	public void fnClickOnSpecificProductFromCatalogue(String productName) {
		for (int i = 0; i < getProductListFromCataloguePage().size(); i++) {
			if (getProductListFromCataloguePage().get(i).getText().equalsIgnoreCase(productName)) {
				AddToCartBtn.get(i).click();
				waitForElementToAppear(toastMessage);
				waitForElementToDisappear(spinningIcon);
				break;
			}
		}
	}

}
