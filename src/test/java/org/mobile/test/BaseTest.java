package org.mobile.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
public class BaseTest {
    String host = "http://127.0.0.1:4723/wd/hub";
    protected AndroidDriver driver;
    WebDriverWait wait;
    Properties properties;

    @BeforeSuite
    public void setUp() throws MalformedURLException {
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("/Users/xyz/Documents/Mobile_Automation/src/test/resources/cofig.properties")){
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3a API 30");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/xyz/Documents/Mobile_Automation/src/test/resources/com.xamarin.acquaintnative.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability("autoAcceptAlerts", true);
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("fullReset",true);
        driver = new AndroidDriver( new URL(host), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @AfterSuite
    public void tearDown(){
       driver.quit();
    }
}
