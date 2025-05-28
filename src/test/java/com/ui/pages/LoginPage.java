package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {

    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_BUTTON_LOCATOR = By.id("SubmitLogin");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[contains(@class, 'alert alert-danger')]/ol/li");

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public MyAccountPage doLogin(String username, String password) {
        enterText(EMAIL_TEXT_BOX_LOCATOR, username);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        MyAccountPage myAccountPage = new MyAccountPage(getWebDriver());
        return myAccountPage;
    }

    public LoginPage doLoginWithInvalidCredentials(String username, String password) {
        enterText(EMAIL_TEXT_BOX_LOCATOR, username);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_BUTTON_LOCATOR);
        LoginPage loginPage = new LoginPage(getWebDriver());
        return loginPage;
    }

    public String getErrorMessage(){
        return getVisibleText(ERROR_MESSAGE_LOCATOR);
    }
}
