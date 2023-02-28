package org.boredkris.pageObjects.android;

import com.aventstack.extentreports.ExtentTest;
import org.boredkris.pageObjects.testUtils.AndroidBaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eCommerce_tc_4_Hybrid extends AndroidBaseTest {
    @Test(dataProvider = "getData", groups = {"Smoke"})
    public void FillForm(HashMap<String, String> input) throws InterruptedException {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
//C:\Users\krzys\OneDrive\-COURSES-\Appium - Mobile Testing\secondTry\AppiumFrameworkDesign\src\test\java\org\boredkris\pageObjects\testData\eCommerce.json
        ProductCatalogue productCatalogue = formPage.submitForm();
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);

        CartPage cartPage = productCatalogue.goToCartPage();
        double totalSum = cartPage.getProductsSum();
        double displayedSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum, displayedSum);
        cartPage.acceptTermsConditions();
        cartPage.submitOrder();

    }

    @BeforeMethod(alwaysRun = true)
    public void preSetup(){
        formPage.setActivity();
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String,String>> data = getJasonData(System.getProperty("user.dir") + "\\src\\test\\java\\org\\boredkris\\pageObjects\\testData\\eCommerce.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};

//        return new Object[][] {   {"Bored Kris", "female", "Argentina"},
//                                  {"Anatoli Wasili", "male", "Estonia"} };
     }
}
