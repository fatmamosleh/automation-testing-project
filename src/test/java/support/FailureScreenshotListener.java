package support;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.IConfigurationListener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FailureScreenshotListener implements ITestListener, IConfigurationListener {
    @Override
    public void onTestFailure(ITestResult result) {
        capture(result);
    }

    @Override
    public void onConfigurationFailure(ITestResult result) {
        capture(result);
    }

    private void capture(ITestResult result) {
        if (!(result.getInstance() instanceof BaseTest test)) return;
        WebDriver driver = test.getDriver();
        if (!(driver instanceof TakesScreenshot screenshotDriver)) return;

        Path directory = Path.of("target", "failure-screenshots");
        String suffix = String.valueOf(System.currentTimeMillis());
        Path destination = directory.resolve(result.getMethod().getMethodName() + "-" + suffix + ".png");
        try {
            Files.createDirectories(directory);
            Files.copy(screenshotDriver.getScreenshotAs(OutputType.FILE).toPath(), destination,
                    StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Failure screenshot: " + destination.toAbsolutePath());
        } catch (IOException exception) {
            System.err.println("Could not save failure screenshot: " + exception.getMessage());
        }
    }
}
