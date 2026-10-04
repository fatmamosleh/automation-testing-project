package Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By signInLink = By.id("signInLink");
    private final By signOutLink = By.id("signOutLink");
    private final By email = By.id("loginEmail");
    private final By password = By.id("loginPassword");
    private final By submit = By.cssSelector("#loginForm button[type='submit']");
    private final By accountLink = By.id("accountLink");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void signOut() {
        wait.until(ExpectedConditions.elementToBeClickable(signOutLink)).click();
        Alert confirmation = wait.until(ExpectedConditions.alertIsPresent());
        confirmation.accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInLink));
    }

    public void signIn(String userEmail, String userPassword) {
        wait.until(ExpectedConditions.elementToBeClickable(signInLink)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(userEmail);
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
