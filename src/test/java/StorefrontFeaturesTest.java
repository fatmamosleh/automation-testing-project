import Pages.ProductBrowsing;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import support.BaseTest;
import support.FailureScreenshotListener;

import java.util.List;

@Listeners(FailureScreenshotListener.class)
public class StorefrontFeaturesTest extends BaseTest {
    private ProductBrowsing catalog;

    @BeforeMethod(alwaysRun = true)
    public void openHomePage() {
        driver.get(baseUrl);
        catalog = new ProductBrowsing(driver);
    }

    @Test(priority = 1, groups = "smoke")
    public void homePageShowsCoreNavigation() {
        Assert.assertTrue(driver.getTitle().contains("Luma"));
        Assert.assertFalse(driver.findElements(By.cssSelector("nav.main-nav a")).isEmpty());
        Assert.assertTrue(driver.findElement(By.id("signInLink")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("a.cart-link")).isDisplayed());
    }

    @Test(priority = 2, groups = "smoke")
    public void menJacketsFilterReturnsProducts() {
        catalog.openMenCategory();
        catalog.filterJackets();
        Assert.assertFalse(catalog.getProductNames().isEmpty(), "The Jackets filter returned no products");
        Assert.assertTrue(catalog.getProductNames().contains("Proteus Fitness Jackshirt"));
    }

    @Test(priority = 3, groups = "smoke")
    public void productsCanBeSortedByAscendingPrice() {
        catalog.openMenCategory();
        catalog.filterJackets();
        catalog.sortByPriceLowToHigh();
        List<Double> prices = catalog.getProductPrices();
        Assert.assertEquals(prices, prices.stream().sorted().toList(), "Products are not sorted low to high");
    }

    @Test(priority = 4, groups = "smoke")
    public void selectedProductPageOpens() {
        catalog.openMenCategory();
        catalog.filterJackets();
        catalog.openProduct("Proteus Fitness Jackshirt");
        Assert.assertEquals(catalog.getProductTitle(), "Proteus Fitness Jackshirt");
    }
}
