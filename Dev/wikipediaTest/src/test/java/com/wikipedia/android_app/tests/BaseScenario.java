package com.wikipedia.android_app.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import util.ConfigParser;

import java.io.File;
import java.net.URL;

/**
 * Created by  on 04/10/2016.
 */
public class BaseScenario {

    static String userName = "";
    static String accessKey = "";
    AppiumDriver driver;

    @BeforeTest
    protected void setup() throws Exception {

        File filePath = new File(System.getProperty("user.dir"));
        File appDir = new File(filePath, "/app");
        File app = new File(appDir, "app-alpha-debug.apk");

        DesiredCapabilities dc = new DesiredCapabilities();
        String env = ConfigParser.getProperty("env");
        dc.setCapability("noReset", true);

        if (env.equalsIgnoreCase("remote")) {
            dc.setCapability("platformName", "Android");
            dc.setCapability("platformVersion", "8.0");
            dc.setCapability("deviceName", ConfigParser.getProperty("deviceName"));
            // dc.setCapability("app", ConfigParser.getProperty("appId"));
            dc.setCapability("appPackage", "");
            dc.setCapability("appActivity", "");
            driver = new AndroidDriver(new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"), dc);
        } else {
            dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
            dc.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigParser.getProperty("deviceName"));
            // dc.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "");
            dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "");
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), dc);
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
