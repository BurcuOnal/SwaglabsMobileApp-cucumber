package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;



public class Hooks {


    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver<MobileElement>> appiumFluentWait;
    private DesiredCapabilities capabilities;
    private URL localUrl;

    @Before
    public void beforeScenario() {
        try {
            logger.info("************************************ BeforeScenario ************************************");
            localUrl = new URL("http://127.0.0.1:4723/wd/hub");

            logger.info("Initializing tests on local Android device/emulator.");
            appiumDriver = new AndroidDriver<>(localUrl, androidCapabilities());

            appiumFluentWait = new FluentWait<>(appiumDriver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofMillis(250))
                    .ignoring(NoSuchElementException.class);

        } catch (MalformedURLException e) {
            logger.error("Malformed URL for Appium server: ", e); // catch improperly formed URL
        } catch (Exception e) {
            logger.error("Error initializing Appium driver: ", e); // if runtime error happens
        }
    }

    private DesiredCapabilities androidCapabilities() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.amerikanhastanesi.connect.uat");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.amerikanhastanesi.connect.MainActivity");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        capabilities.setCapability("appium:waitForIdleTimeout", 30);
        capabilities.setCapability("appium:ignoreUnimportantViews", true);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300); //prevent session termination
        return capabilities;
    }

    @After
    public void afterScenario() {
        logger.info("************************************ AfterScenario ************************************");
        if (appiumDriver != null) {
            appiumDriver.quit();
            logger.info("Appium driver closed successfully.");
        }
    }
}
