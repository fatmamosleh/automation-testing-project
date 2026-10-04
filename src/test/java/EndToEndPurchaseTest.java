import Pages.CartCheckoutPage;
import Pages.LoginPage;
import Pages.OrdersPage;
import Pages.ProductBrowsing;
import Pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import support.BaseTest;
import support.FailureScreenshotListener;
import support.TestDataFactory;

@Listeners(FailureScreenshotListener.class)
public class EndToEndPurchaseTest extends BaseTest {
    private static final String PRODUCT = "Proteus Fitness Jackshirt";

    @Test(groups = "e2e")
    public void registeredCustomerCompletesPurchaseAndSeesOrder() {
        TestDataFactory.Customer customer = TestDataFactory.uniqueCustomer();
        RegistrationPage registration = new RegistrationPage(driver);
        LoginPage login = new LoginPage(driver);
        ProductBrowsing catalog = new ProductBrowsing(driver);
        CartCheckoutPage checkout = new CartCheckoutPage(driver);

        driver.get(baseUrl);
        registration.register(customer.firstName(), customer.lastName(), customer.email(), customer.password());
        Assert.assertTrue(registration.isLoggedIn(), "Registration did not log in the new customer");

        login.signOut();
        login.signIn(customer.email(), customer.password());
        Assert.assertTrue(login.isLoggedIn(), "The generated customer could not sign in");

        catalog.openMenCategory();
        catalog.filterJackets();
        catalog.sortByPriceLowToHigh();
        catalog.openProduct(PRODUCT);
        Assert.assertEquals(catalog.getProductTitle(), PRODUCT);

        checkout.chooseProductOptions("M", "Orange");
        checkout.addProductToCart();
        checkout.openCart();
        Assert.assertEquals(checkout.getCartProductName(), PRODUCT);
        Assert.assertTrue(checkout.getCartOptions().contains("Size: M"));
        Assert.assertTrue(checkout.getCartOptions().contains("Color: Orange"));

        checkout.proceedToCheckout();
        checkout.completeShipping(customer.firstName(), customer.lastName(), customer.email());
        checkout.completePayment();
        String orderNumber = checkout.placeOrder();
        Assert.assertTrue(orderNumber.startsWith("ORD"), "Order number was not generated");

        OrdersPage orders = new OrdersPage(driver);
        orders.openOrders();
        Assert.assertTrue(orders.containsOrder(orderNumber), "Order was not saved in My Orders");
    }
}
