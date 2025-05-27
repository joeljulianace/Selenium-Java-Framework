package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public WebDriver getWebDriver() {
        return webDriver.get();
    }

    public BrowserUtility(WebDriver webDriver) {
        this.webDriver.set(webDriver);
    }

    public BrowserUtility(String browserName) {
        logger.info("Launching browser: " + browserName);
        if(browserName.equalsIgnoreCase("chrome")) {
            webDriver.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("edge")) {
            webDriver.set(new EdgeDriver());
        } else {
            logger.error("Invalid option....Kindly select chrome or edge only.");
            System.err.println("Invalid option....Kindly select chrome or edge only.");
        }
    }

    public BrowserUtility(Browser browser) {
        logger.info("Launching browser: " + browser.toString());
        if(browser == Browser.CHROME) {
            webDriver.set(new ChromeDriver());
        } else if (browser == Browser.EDGE) {
            webDriver.set(new EdgeDriver());
        } else if(browser == Browser.FIREFOX) {
            webDriver.set(new FirefoxDriver());
        }
    }

    public BrowserUtility(Browser browser, boolean isHeadless) {
        logger.info("Launching browser: " + browser.toString());
        if(browser == Browser.CHROME) {
            if(isHeadless) {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless"); //launch in headless mode
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                webDriver.set(new ChromeDriver(chromeOptions));
            } else {
                webDriver.set(new ChromeDriver());
            }
        } else if (browser == Browser.EDGE) {
            if(isHeadless) {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--disable-gpu");
                webDriver.set(new EdgeDriver(edgeOptions));
            } else {
                webDriver.set(new EdgeDriver());
            }
        } else if(browser == Browser.FIREFOX) {
            if(isHeadless) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                webDriver.set(new FirefoxDriver(firefoxOptions));
            } else {
                webDriver.set(new FirefoxDriver());
            }
        }
    }

    public void goToWebsite(String url) {
        webDriver.get().get(url);
        logger.info("Navigating to url: " + url);
    }

    public void maximizeWindow() {
        webDriver.get().manage().window().maximize();
    }

    public void clickOn(By locator){
        logger.info("Finding element with locator: " + locator);
        WebElement webElement = webDriver.get().findElement(locator); //Finding the element and is not synchronized
        logger.info("Element found, performing click action");
        webElement.click();
    }

    public void enterText(By locator, String textToEnter){
        logger.info("Finding element with locator: " + locator);
        WebElement webElement = webDriver.get().findElement(locator);
        logger.info("Element found and entering text: " + textToEnter);
        webElement.sendKeys(textToEnter);
    }

    public String getVisibleText(By locator){
        logger.info("Finding element with locator: " + locator);
        WebElement webElement = webDriver.get().findElement(locator);
        logger.info("Element found, getting text from element: " + webElement.getText());
        return webElement.getText();
    }

    public String takeScreenShot(String fileName) {
        TakesScreenshot screenshot = (TakesScreenshot) webDriver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = simpleDateFormat.format(date);
        String path = "./screenshot/" + fileName + "_" + timeStamp + ".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public void quit(){
        if(webDriver.get() != null) {
            webDriver.get().quit();
        }
    }
}
