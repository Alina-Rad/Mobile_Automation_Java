package org.mobile.test;

import com.beust.ah.A;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ContactsListTest extends BaseTest {
    public String addNewContactButton = "com.xamarin.acquaintnative:id/acquaintanceListFloatingActionButton";
    public String name = "com.xamarin.acquaintnative:id/firstNameField";
    public String lastname = "com.xamarin.acquaintnative:id/lastNameField";
    public String company = "com.xamarin.acquaintnative:id/companyField";
    public String title= "com.xamarin.acquaintnative:id/jobTitleField";
    public String phone= "com.xamarin.acquaintnative:id/phoneNumberField";
    public String email ="com.xamarin.acquaintnative:id/emailField";
    public String street ="com.xamarin.acquaintnative:id/streetField";
    public String city="com.xamarin.acquaintnative:id/cityField";
    public String state ="com.xamarin.acquaintnative:id/stateField";
    public String zipcode = "com.xamarin.acquaintnative:id/stateField";
    public String saveButton ="com.xamarin.acquaintnative:id/acquaintanceSaveButton";
    public String appOldOkButton = "android:id/button1";
    public String findName ="com.xamarin.acquaintnative:id/nameTextView";
    public String editButton = "com.xamarin.acquaintnative:id/acquaintanceEditButton";
    public String deleteButton ="com.xamarin.acquaintnative:id/acquaintanceDeleteButton";
    public String deleteName = "//android.widget.TextView[@text=\"Floyd Bell\"]";
    public String deletedNameText;

    @Test
    public void createContact(){
        driver.findElement(By.id(appOldOkButton)).click();
        driver.findElement(By.id(addNewContactButton)).click();
        driver.findElement(By.id(name)).sendKeys(properties.getProperty("name"));
        driver.findElement(By.id(lastname)).sendKeys(properties.getProperty("surname"));
        driver.findElement(By.id(company)).sendKeys(properties.getProperty("company"));
        driver.findElement(By.id(title)).sendKeys(properties.getProperty("jobTittle"));
        driver.findElement(By.id(phone)).sendKeys(properties.getProperty("phone"));
        driver.findElement(By.id(email)).sendKeys(properties.getProperty("email"));
        driver.findElement(By.id(street)).sendKeys(properties.getProperty("street"));
        driver.findElement(By.id(city)).sendKeys(properties.getProperty("city"));
        driver.findElement(By.id(state)).sendKeys(properties.getProperty("country"));
        driver.findElement(By.id(zipcode)).sendKeys(properties.getProperty("zipCode"));
        driver.findElement(By.id(saveButton)).click();
    }

    @Test(dependsOnMethods = "createContact")
    public void editContact(){
        driver.findElement(By.id(findName)).click();
        driver.findElement(By.id(editButton)).click();
        driver.findElement(By.id(name)).clear();
        driver.findElement(By.id(name)).sendKeys("Sasha");
        driver.findElement(By.id(lastname)).clear();
        driver.findElement(By.id(lastname)).sendKeys("Nova");
        driver.findElement(By.id(company)).clear();
        driver.findElement(By.id(company)).sendKeys("Genius");
        driver.findElement(By.id(title)).clear();
        driver.findElement(By.id(title)).sendKeys("Junior QA Engineer");
        driver.findElement(By.id(phone)).clear();
        driver.findElement(By.id(phone)).sendKeys("+28989499004");
        driver.findElement(By.id(email)).clear();
        driver.findElement(By.id(email)).sendKeys("test@test.com");
        driver.findElement(By.id(street)).clear();
        driver.findElement(By.id(street)).sendKeys("charivna st 110");
        driver.findElement(By.id(city)).clear();
        driver.findElement(By.id(city)).sendKeys("Warsaw");
        driver.findElement(By.id(state)).clear();
        driver.findElement(By.id(state)).sendKeys("Po");
        driver.findElement(By.id(saveButton)).click();
        driver.navigate().back();
    }

    @Test (dependsOnMethods = {"createContact","editContact"})
    public void deleteContact() {
        driver.findElement(By.id(findName)).click();
        deletedNameText = driver.findElement(By.xpath(deleteName)).getText();
        driver.findElement(By.id(deleteButton)).click();
        driver.findElement(By.id(appOldOkButton)).click();
        List <WebElement> namesWorkers = Collections.singletonList(driver.findElement(By.id(findName)));
        boolean isDeletedNamePresent = namesWorkers.stream().noneMatch(namesWorker->namesWorker.getText().equals(deletedNameText));
        if (isDeletedNamePresent){
            System.out.println("Your test is passed!");
        }
        else System.out.println("Your test is failed!");
    }
}
