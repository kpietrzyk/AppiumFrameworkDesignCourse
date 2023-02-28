package org.boredkris.pageObjects.android;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.boredkris.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidActions {
    AndroidDriver driver;

    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }


    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;
    // driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Bored Kris");


    @AndroidFindBy(xpath="//android.widget.RadioButton[@text=\"Female\"]")
    private WebElement femaleOption;
    // driver.findElement(By.xpath("//android.widget.RadioButton[@text=\"Female\"]")).click();

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text=\"Male\"]")
    private WebElement maleOption;

    @AndroidFindBy(id="android:id/text1")
    private WebElement countrySelection;
    // driver.findElement(By.id("android:id/text1")).click();

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;
    //driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

    // METHODS
    public void setNameField(String name){
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void setGender(String gender){
        if(gender.contains("female")){
            femaleOption.click();
        }else {
            maleOption.click();
        }
    }

    public void setCountrySelection(String countryName){
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }

    public void setActivity(){
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
        driver.startActivity(activity);

    }

    public ProductCatalogue submitForm() {
        shopButton.click();
        return new ProductCatalogue(driver);
    }


}
