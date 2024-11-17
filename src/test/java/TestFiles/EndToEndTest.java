package TestFiles;

import java.io.IOException;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageFiles.CartPage;
import PageFiles.CheckoutPage;
import PageFiles.ConfirmationPage;
import PageFiles.OrdersPage;
import PageFiles.ProductCataloguePage;
import TestComponents.BaseTest;

public class EndToEndTest extends BaseTest {
	
	String productName = "ADIDAS ORIGINAL";
	
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void OrderItemEnd2EndFlow(HashMap<String, String> map) throws IOException {
		
		ProductCataloguePage cataloguePage = landingPage.loginApplication(map.get("email"), map.get("password"));
		cataloguePage.fnClickOnSpecificProductFromCatalogue(map.get("productName"));
		CartPage cartPage = cataloguePage.goToCartPage();
		boolean match = cartPage.fnVerifyProductAvailableInCartList(map.get("productName"));
		Assert.assertTrue(match);
		System.out.println("Passed : Product is available under Cart page : " + productName);
		CheckoutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry();
		ConfirmationPage confirmPage = checkoutPage.placeOrder();
		String msg = confirmPage.fnVerifyConfirmationMsg();
		Assert.assertEquals(msg, "THANKYOU FOR THE ORDER.");
	}
	
	
	// Do not make your test so big with multiple Validations
	@Test(dependsOnMethods = {"OrderItemEnd2EndFlow"})
	public void OrderHistoryTest() {
		ProductCataloguePage cataloguePage = landingPage.loginApplication("Visheshmalikk@gmail.com", "1@Neem2345");
		OrdersPage orderPage = cataloguePage.goToOrdersPage();
		boolean flag = orderPage.fnVerifyProductPresentInOrder(productName);
		Assert.assertTrue(flag);
	}
	
	/*
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"visheshmalikk@gmail.com", "1@Neem2345", "ADIDAS ORIGINAL"}, {"Mohit@gmail.com", "1@Hanu2345", "IPHONE 13 PRO"}};
	} */
	
	
	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "visheshmalikk@gmail.com");
		map1.put("password", "1@Neem2345");
		map1.put("productName", "ADIDAS ORIGINAL");
		
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("email", "Mohit@gmail.com");
		map2.put("password", "1@Hanu2345");
		map2.put("productName", "IPHONE 13 PRO");
		return new Object[][] {{map1}, {map2}};
	}


}
