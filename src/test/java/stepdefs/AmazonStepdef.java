package stepdefs;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import io.cucumber.java.en.*;
import junit.framework.Assert;

public class AmazonStepdef {
	AndroidDriver driver = AmazonHooks.driver;
	AmazonMethods amMethods = new AmazonMethods();

	@Given("I want to skip login to amazon application")
	public void i_want_to_skip_login_to_amazon_application() throws MalformedURLException {
		driver = amMethods.skipLogin(driver);

	}

	@Given("I search for product {string}")
	public void i_search_for_product(String searchProduct) {

		driver = amMethods.searchForProduct(driver, searchProduct);
	}

	@Given("I select search for {string}")
	public void i_select_search_for(String searchSpecificProduct) {
		driver = amMethods.selectForProduct(driver, searchSpecificProduct);

	}

	@Given("I click on the product to add {string}")
	public void i_click_on_the_product_to_add(String productName) {
		driver = amMethods.clickOnProduct(driver, productName);
	}

	@When("I click on AddToCart button")
	public void i_click_on_addToCart_button() {
		amMethods.addToCart(driver);
	}
}
