package org.boredkris.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumUtils {
    AppiumDriver driver;
    public AppiumDriverLocalService service;


    public Double getFormattedAmount(String amount)
    {
        return Double.parseDouble(amount.substring(1));

    }

    public List<HashMap<String, String>> getJasonData(String jsonFilePath) throws IOException {
        // convert json file content to json string
//        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "src\\test\\java\\org\\boredkris\\pageObjects\\testData\\eCommerce.json"),
//                StandardCharsets.UTF_8);
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath),
                StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
    }


    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\krzys\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .build();
        service.start();
        return service;
    }




    public void waitForElementToApear(WebElement element, AppiumDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((element), "text", "Cart"));
    }

//    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
//
//        File source = driver.getScreenshotAs(OutputType.FILE);
//        String destinationFile = System.getProperty("user.dir")+"\\reports\\" + testCaseName + ".png";
//        FileUtils.copyFile(source, new File(destinationFile));
//        return destinationFile;
//    }
public static String captureScreenshot(AppiumDriver driver, String screenName) throws IOException {

    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    String dest = System.getProperty("user.dir")+ "//reports//Screenshots//"+screenName+".png";
    File target = new File(dest);
    FileUtils.copyFile(src, target);
    System.out.println("Screenshot captured.");
    return dest;
}

}
