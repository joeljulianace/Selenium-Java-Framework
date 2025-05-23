package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import static org.testng.Assert.*;

import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest3 extends TestBase {

    @Test(description = "Verify valid login",
    groups = {"e2e", "sanity"},
    dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
    dataProvider = "LoginTestDataProvider")
    public void loginTest(User user) {
        assertEquals(homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword())
                .getUserName(), "John Smith");
    }

    @Test(description = "Verify valid login",
            groups = {"e2e", "sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginCSVDataProvider")
    public void loginCSVTest(User user) {
        assertEquals(homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword())
                .getUserName(), "John Smith");
    }

    @Test(description = "Verify valid login",
            groups = {"e2e", "sanity"},
            dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
            dataProvider = "LoginExcelDataProvider",
            retryAnalyzer = com.ui.listeners.CustomRetryAnalyser.class)
    public void loginExcelTest(User user) {
        assertEquals(homePage.goToLoginPage().doLogin(user.getEmailAddress(), user.getPassword())
                .getUserName(), "John Smith");
    }
}
