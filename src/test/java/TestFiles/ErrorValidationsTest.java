package TestFiles;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageFiles.CartPage;
import PageFiles.ProductCataloguePage;
import TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidations() throws IOException {
		landingPage.loginApplication("Visheshmalikk@gmail.com", "1@Neem12345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}

	@Test
	public void productErrorValidation() throws IOException {
		String productName = "ADIDAS ORIGINAL";
		ProductCataloguePage cataloguePage = landingPage.loginApplication("Visheshmalikk@gmail.com", "1@Neem2345");
		cataloguePage.fnClickOnSpecificProductFromCatalogue(productName);
		CartPage cartPage = cataloguePage.goToCartPage();
		boolean match = cartPage.fnVerifyProductAvailableInCartList("ADIDAS ORIGINAL 20");
		Assert.assertFalse(match);
		
	}
}
