import com.sun.webkit.Timer;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleMapsTest {
    AppiumDriver<MobileElement> driver;



    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        String platform = System.getProperty("platform", "Android");

        if (platform.equalsIgnoreCase("Android")) {
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "Android Emulator");
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("appPackage", "com.google.android.apps.maps");
            caps.setCapability("appActivity", "com.google.android.maps.MapsActivity");
            caps.setCapability("automationName", "UiAutomator2");

            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } else if (platform.equalsIgnoreCase("iOS")) {
            caps.setCapability("platformName", "iOS");
            caps.setCapability("platformVersion", "15.0");
            caps.setCapability("deviceName", "iPhone 13");
            caps.setCapability("automationName", "XCUITest");
            caps.setCapability("app", "com.apple.Maps");

            driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }

    @Test(priority = 1)
    public void skipControl() throws InterruptedException {
        MobileElement skipButton = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.Button"));
        skipButton.click();
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void mainScreenComponentsControl() throws InterruptedException {
        MobileElement searchBox = driver.findElement(By.id("com.google.android.apps.maps:id/search_omnibox_text_box"));
        MobileElement currentLocationButton = driver.findElement(By.id("com.google.android.apps.maps:id/mylocation_button"));
        MobileElement directionsButton = driver.findElement(By.id("com.google.android.apps.maps:id/on_map_directions_button"));

        Assert.assertTrue(searchBox.isDisplayed());
        Assert.assertTrue(currentLocationButton.isDisplayed());
        Assert.assertTrue(directionsButton.isDisplayed());
        Thread.sleep(3000);
    }
    @Test(priority = 3)
    public void navigationComponentsControl() throws InterruptedException{

        MobileElement destinationButton = driver.findElement(By.id("com.google.android.apps.maps:id/on_map_directions_button"));
        destinationButton.click();
        Thread.sleep(3000);

        MobileElement driving = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Driving mode\"]"));
        MobileElement transit = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Transit mode\"]"));
        MobileElement walking = driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Walking mode\"]"));

        Assert.assertTrue(driving.isDisplayed());
        Assert.assertTrue(transit.isDisplayed());
        Assert.assertTrue(walking.isDisplayed());
        Thread.sleep(3000);

    }


    @Test(priority = 4)
    public void runDestination() throws InterruptedException {


        MobileElement destinationStart = driver.findElement(By.id("com.google.android.apps.maps:id/directions_startpoint_textbox"));
        destinationStart.click();
        Thread.sleep(3000);

        MobileElement destinationInputUmraniye = driver.findElement(By.id("com.google.android.apps.maps:id/search_omnibox_edit_text"));
        destinationInputUmraniye.sendKeys("ümraniye");
        Thread.sleep(3000);

        MobileElement pressInput = driver.findElement(By.id("com.google.android.apps.maps:id/compass_container"));
        pressInput.click();
        Thread.sleep(3000);

        MobileElement destinationFinish = driver.findElement(By.id("com.google.android.apps.maps:id/directions_endpoint_textbox"));
        destinationFinish.click();
        Thread.sleep(3000);

        MobileElement destinationInputBelgrad = driver.findElement(By.id("com.google.android.apps.maps:id/search_omnibox_edit_text"));
        destinationInputBelgrad.sendKeys("Belgrad Ormani");
        Thread.sleep(3000);

        MobileElement inputEnter = driver.findElement(By.id("com.google.android.apps.maps:id/compass_container"));
        inputEnter.click();
        Thread.sleep(10000);


        MobileElement starttButton = driver.findElement(By.id("com.google.android.apps.maps:id/start_button"));
        starttButton.click();
        Thread.sleep(5000);

        MobileElement backButtonToEnd = driver.findElement(By.id("com.google.android.apps.maps:id/start_section"));
        backButtonToEnd.click();
    }
}
