package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LambdaTestUtility {
    public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
    private static ThreadLocal<WebDriver> webDriverLocal = new ThreadLocal<>();
    private static ThreadLocal<DesiredCapabilities> desiredCapabilitiesLocal = new ThreadLocal<>();


        public static WebDriver initializeLambdaTestSession(String browser, String testName) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("browserName", browser);
            desiredCapabilities.setCapability("browserVersion", "136");
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("username", "joeljulianace");
            ltOptions.put("accessKey", "WzvkknOYb4B34C5bVNpMhJamnym4ZXb75jUrMmccJzkpZ7iHGq");
            ltOptions.put("build", "Selenium 4");
            ltOptions.put("name", testName);
            ltOptions.put("platformName", "Windows 10");
            ltOptions.put("seCdp", true);
            ltOptions.put("selenium_version", "4.32.0");
            ltOptions.put("w3c", true);
            desiredCapabilities.setCapability("LT:Options", ltOptions);
            desiredCapabilitiesLocal.set(desiredCapabilities);
            WebDriver webDriver = null;
            try {
                webDriver = new RemoteWebDriver(new URL(HUB_URL), desiredCapabilitiesLocal.get());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            webDriverLocal.set(webDriver);

            return webDriverLocal.get();
        }

        public static void quitSession() {
            if(webDriverLocal.get() != null) {
                webDriverLocal.get().quit();
            }
        }
}
