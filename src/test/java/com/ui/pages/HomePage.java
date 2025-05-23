package com.ui.pages;

import com.constants.Browser;
import static com.constants.Environment.*;

import com.constants.Environment;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class HomePage extends BrowserUtility {
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(), 'Sign in')]");
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName, isHeadless); //calls the parent class constructor from the child class constructor
//        goToWebsite(readProperty(QA, "URL"));
        goToWebsite(JSONUtility.readJson(QA).getUrl());
        maximizeWindow();
    }

    //Page functions
    //Do not return void in POM
    public LoginPage goToLoginPage(){
        logger.info("Navigating to login page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getWebDriver());
        return loginPage;
    }
}
