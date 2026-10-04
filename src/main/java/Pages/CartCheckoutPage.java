package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartCheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By cartItemName = By.cssSelector(".cart-item-name");

    public CartCheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void chooseProductOptions(String size, String color) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".size-option[data-size='" + size + "']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".color-option[data-color='" + color + "']"))).click();
    }

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("addToCartBtn"))).click();
        Alert confirmation = wait.until(ExpectedConditions.alertIsPresent());
        confirmation.accept();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("cartCount"), "1"));
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.cart-link"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemName));
    }

    public String getCartProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemName)).getText().trim();
    }

    public String getCartOptions() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".cart-item-options"))).getText().trim();
    }

    public void proceedToCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("proceedToCheckout"))).click();
        wait.until(ExpectedConditions.urlContains("checkout.html"));
    }

    public void completeShipping(String first, String last, String email) {
        replace(By.id("shipFirstName"), first);
        replace(By.id("shipLastName"), last);
        replace(By.id("shipEmail"), email);
        replace(By.id("shipAddress"), "123 Test Street");
        replace(By.id("shipCity"), "Test City");
        new Select(driver.findElement(By.id("shipState"))).selectByValue("CA");
        replace(By.id("shipZip"), "12345");
        replace(By.id("shipPhone"), "5550123456");
        driver.findElement(By.cssSelector("#shippingForm button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentForm")));
    }

    public void completePayment() {
        replace(By.id("cardNumber"), "4111111111111111");
        replace(By.id("cardName"), "Fatma Test");
        replace(By.id("cardExpiry"), "12/30");
        replace(By.id("cardCvv"), "123");
        driver.findElement(By.cssSelector("#paymentForm button[type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("placeOrder")));
    }

    public String placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("placeOrder"))).click();
        wait.until(ExpectedConditions.urlContains("order-confirmation.html"));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderNumber"))).getText().trim();
    }

    private void replace(By locator, String value) {
        var element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }
}
