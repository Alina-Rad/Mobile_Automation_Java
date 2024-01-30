package org.mobile.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

class TestDroidAppTest {
    public String radioButtonAskMom ="com.bitbar.testdroid:id/radio2";
    public String nameField = "com.bitbar.testdroid:id/editText1";
    public String answerButton ="com.bitbar.testdroid:id/button1";

    @Test(description="Ask mom for help")
    public void askMomForHelp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3a API 30");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/xyz/Documents/Mobile_Automation/src/test/resources/bitbar-sample-app.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability("autoAcceptAlerts", true);
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        String host = "http://127.0.0.1:4723/wd/hub";
        AndroidDriver driver = new AndroidDriver( new URL(host), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.id(radioButtonAskMom)).click();
        driver.findElement(By.id(nameField)).sendKeys("Your Mother");
        driver.findElement(By.id(answerButton)).click();

    }
}
