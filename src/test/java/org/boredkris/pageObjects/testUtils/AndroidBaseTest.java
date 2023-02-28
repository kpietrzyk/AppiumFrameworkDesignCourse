package org.boredkris.pageObjects.testUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.boredkris.pageObjects.android.FormPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.boredkris.utils.AppiumUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

public class AndroidBaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public FormPage formPage;



    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws IOException {
        // Start appium server
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\java\\org\\boredkris\\resources\\data.properties");
        // parameter from MAVEN
        String ipAddress = System.getProperty("ipAddress") !=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
        prop.load(fis);
       // String ipAddress = prop.getProperty("ipAddress");
        int port = Integer.parseInt(prop.getProperty("port"));

        service = startAppiumServer(ipAddress,port);

        // AndroidDriver, IOSDriver
        // Appium code -> Appium Server -> Mobile
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("AndroidDeviceName"));
        options.setChromedriverExecutable("C:\\WebDrivers\\ChromeDriver\\chromedriver_win32_74_0_3729\\chromedriver.exe");

       // options.setCapability("browserName", "Chrome");
       // options.setApp("C:\\Users\\krzys\\OneDrive\\-COURSES-\\Appium - Mobile Testing\\secondTry\\AppiumIntro\\src\\test\\java\\resources\\ApiDemos-debug.apk");
        options.setApp(System.getProperty("user.dir") +"\\src\\test\\java\\org\\boredkris\\pageObjects\\resources\\General-Store.apk");
        // org/boredkris/pageObjects/resources/General-Store.apk

        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);

    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        //Stop appium
        driver.quit();
        service.stop();
    }
}
