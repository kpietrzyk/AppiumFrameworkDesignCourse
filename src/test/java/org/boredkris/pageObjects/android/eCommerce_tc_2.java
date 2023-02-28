package org.boredkris.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.boredkris.pageObjects.testUtils.AndroidBaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eCommerce_tc_2 extends AndroidBaseTest {

    @BeforeMethod
    public void preSetup(){

        //screen to home page
        //Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        driver.startActivity(activity);

    }
    @Test
    public void FillForm_ErrorValidation() throws InterruptedException {

        // driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bored Kris");
        // hide keyboard
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Female\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("android:id/text1")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Argentina\"));")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name");
       // Assert.assertEquals(toastMessage, "Please your name");
        Thread.sleep(3000);

    }
    @Test
    public void FillForm_PositiveFlow() throws InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bored Kris");
        // hide keyboard
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Female\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("android:id/text1")).click();

        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"Argentina\"));")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElements(By.xpath("//android.widget.Toast[1]")).size()<1);

    }
}
