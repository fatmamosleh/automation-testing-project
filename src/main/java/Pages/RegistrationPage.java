package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By signInLink = By.id("signInLink");
    private final By createAccountTab = By.cssSelector(".auth-tab[data-tab='register']");
    private final By firstName = By.id("regFirstName");
    private final By lastName = By.id("regLastName");
    private final By email = By.id("regEmail");
    private final By password = By.id("regPassword");
    private final By submit = By.cssSelector("#registrationForm button[type='submit']");
    private final By accountLink = By.id("accountLink");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void register(String first, String last, String userEmail, String userPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(createAccountTab)).click();
        driver.findElement(firstName).sendKeys(first);
        driver.findElement(lastName).sendKeys(last);
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(userPassword);
        driver.findElement(submit).click();
        Alert success = wait.until(ExpectedConditions.alertIsPresent());
        success.accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountLink));
    }

    public boolean isLoggedIn() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountLink)).isDisplayed();
    }
}
