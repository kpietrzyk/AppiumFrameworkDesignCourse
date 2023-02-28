package org.boredkris.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.boredkris.utils.AndroidActions;

import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;

    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productList;
    // List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));

    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalAmount;
    // driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl"))


    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    private WebElement terms;
    // driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));

    @AndroidFindBy(id="android:id/button1")
    private WebElement acceptTerms;
    // driver.findElement(By.id("android:id/button1"))

    @AndroidFindBy(className="android.widget.CheckBox")
    private WebElement checkBox;
    // driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement proceed;
    // driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"))

    // METHODS
    public List<WebElement> getProductList(){
        return productList;
    }

    public double getProductsSum(){
        int count = productList.size();
        double totalSum = 0;
        for(int i = 0; i < count; i++){
            String amountString = productList.get(i).getText();
            double price = getFormattedAmount(amountString);
            totalSum += price;
        }
        return totalSum;
    }


    public double getTotalAmountDisplayed() {
        return getFormattedAmount(totalAmount.getText());
    }


    public void acceptTermsConditions() {
        longPressAction(terms);
        acceptTerms.click();
    }

    public void submitOrder() throws InterruptedException {
        checkBox.click();
        proceed.click();
        Thread.sleep(6000);

    }
}
