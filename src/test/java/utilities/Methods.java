package utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static utilities.Hooks.appiumDriver;

public class Methods {



    public static WebElement waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofMillis(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void click(By locator) {
        waitForElement(locator).click();
    }


    public void sendKeys(By locator, String text) {
        waitForElement(locator).sendKeys(text);
    }

    public void swipeVertically(int startPercentage, int endPercentage) {
        Dimension screenSize = appiumDriver.manage().window().getSize();
        int height = screenSize.getHeight();
        int width = screenSize.getWidth();

        int startX = width / 2;
        int startY = (int) (height * startPercentage / 100);
        int endX = width / 2;
        int endY = (int) (height * endPercentage / 100);

        new TouchAction(appiumDriver)
                .press(PointOption.point(startX, startY))  // Press at start position
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))  // Wait for half a second
                .moveTo(PointOption.point(endX, endY))  // Move to end position
                .release()  // Release at end position
                .perform();  // Execute the action
    }

    public void scrollToAndClick(By locator) {
        WebElement element = waitForElement(locator);
        ((JavascriptExecutor) appiumDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }


    public String getText(By locator) {
        return waitForElement(locator).getText();
    }


    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
