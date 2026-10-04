package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductBrowsing {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By menLink = By.cssSelector("nav a[href='category-men.html']");
    private final By jacketsFilter = By.cssSelector("input[type='checkbox'][value='jackets']");
    private final By sortSelect = By.id("sortSelect");
    private final By productCards = By.cssSelector("#categoryProducts .product-card");
    private final By productNames = By.cssSelector("#categoryProducts .product-name");
    private final By productPrices = By.cssSelector("#categoryProducts .product-price");
    private final By productTitle = By.id("productName");

    public ProductBrowsing(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void openMenCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(menLink)).click();
        wait.until(ExpectedConditions.urlContains("category-men.html"));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productCards, 0));
    }

    public void filterJackets() {
        wait.until(ExpectedConditions.elementToBeClickable(jacketsFilter)).click();
        wait.until(driver -> !getProductNames().isEmpty()
                && getProductNames().stream().anyMatch(name -> name.contains("Jacket") || name.contains("Jackshirt")));
    }

    public void sortByPriceLowToHigh() {
        WebElement sorter = wait.until(ExpectedConditions.visibilityOfElementLocated(sortSelect));
        new Select(sorter).selectByValue("price-low");
        wait.until(driver -> "price-low".equals(new Select(driver.findElement(sortSelect))
                .getFirstSelectedOption().getAttribute("value")));
    }

    public List<String> getProductNames() {
        return driver.findElements(productNames).stream()
                .map(WebElement::getText).map(String::trim).filter(name -> !name.isEmpty()).toList();
    }

    public List<Double> getProductPrices() {
        return driver.findElements(productPrices).stream()
                .map(WebElement::getText)
                .map(text -> text.replace("$", "").replace(",", "").trim())
                .map(Double::parseDouble).toList();
    }

    public void openProduct(String productName) {
        By product = By.xpath("//div[@id='categoryProducts']//h3[contains(@class,'product-name') and normalize-space()='"
                + productName + "']/ancestor::div[contains(@class,'product-card')]");
        wait.until(ExpectedConditions.elementToBeClickable(product)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
    }

    public String getProductTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle)).getText().trim();
    }
}
