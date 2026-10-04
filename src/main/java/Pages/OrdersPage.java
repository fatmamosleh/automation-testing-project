package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrdersPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openOrders() {
        String current = driver.getCurrentUrl();
        String root = current.substring(0, current.lastIndexOf('/') + 1);
        driver.get(root + "account.html#orders");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ordersContainer")));
    }

    public boolean containsOrder(String orderNumber) {
        By order = By.xpath("//*[@id='ordersContainer']//*[contains(normalize-space(), '"
                + orderNumber + "')]");
        return wait.until(driver -> !driver.findElements(order).isEmpty());
    }
}
