package stepdefs;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class AmazonMethods {
	static String xpathToFind, expectedproductname, addedProductName = "";

	public AndroidDriver skipLogin(AndroidDriver driver) throws MalformedURLException {
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();
		return driver;
	}

	public AndroidDriver searchForProduct(AndroidDriver driver, String searchProduct) {
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/chrome_search_hint_view")).click();
		WebDriverWait wc = new WebDriverWait(driver, Duration.ofSeconds(2));
		wc.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")));
		WebElement search = driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text"));
		System.out.println("productname : " + searchProduct);
		search.sendKeys(searchProduct);

		Actions keyboard = new Actions(driver);
		keyboard.keyDown(search, Keys.ENTER).build().perform();
		driver.hideKeyboard();
		return driver;
	}

	public AndroidDriver selectForProduct(AndroidDriver driver, String searchSpecificProduct) {
		// System.out.println("Getting product : " + searchSpecificProduct);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}

		List<WebElement> allproducts = driver.findElements(By.xpath("//android.widget.Button[@index='1']"));

		// System.out.println("allproducts : " + allproducts.size());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}

		for (WebElement product : allproducts) {

			if (product.getText().equals(searchSpecificProduct)) {
				product.click();
				break;
			}
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		return driver;
	}

	public AndroidDriver clickOnProduct(AndroidDriver driver, String productName) {

		// System.out.println("Inside clickOnProduct");
		xpathToFind = "//android.view.View[contains(@text,'" + productName + "')]";
		List<WebElement> searchelements = driver.findElements(By.xpath(xpathToFind));

		while (searchelements.size() == 0) {
			// System.out.println("inside searchelement loop");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			scrollUp(driver);
			searchelements = driver.findElements(By.xpath(xpathToFind));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		WebElement searchelement = driver.findElement(By.xpath(xpathToFind));
		searchelement.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		expectedproductname = searchelement.getText();
		return driver;

	}

	public void addToCart(AndroidDriver driver) {
		// System.out.println("Inside addToCart");
		List<WebElement> webelements = driver.findElements(By.xpath("//android.widget.Button[@text='Add to Cart']"));

		while (webelements.size() == 0) {
			scrollUp(driver);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			webelements = driver.findElements(By.xpath("//android.widget.Button[@text='Add to Cart']"));
		}
		WebElement addToCartBtn = driver.findElement(By.xpath("//android.widget.Button[@text='Add to Cart']"));
		addToCartBtn.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void scrollUp(AndroidDriver driver) {
		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();

		int startX = (int) (0.5 * width);
		int startY = (int) (0.9 * height);

		int endX = startX;
		int endY = (int) (0.2 * height);

		TouchAction action = new TouchAction(driver);

		action.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
