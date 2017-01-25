package util;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class AppiumTest  {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File app = new File("../app/build/outputs/apk/app-debug.apk");
        //capabilities.setCapability("avd","Nexus 5X API 25");
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("platformVersion", "7.1");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.quantityandconversion.hackernews");
        capabilities.setCapability("appActivity", ".MainActivity");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest(){
        final WebDriverWait wait = new WebDriverWait(driver, 10);

        final WebElement txt =  driver.findElement(By.id("top_story_count"));
        final String txtValue = txt.getText();

        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver webDriver) {
                return !txtValue.equals(webDriver.findElement(By.id("top_story_count")).getText());
            }
        });

        Integer.parseInt(txt.getText());
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }
}