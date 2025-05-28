package com.ui.tests;

import com.ui.pojo.User;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Listeners({com.ui.listeners.TestListener.class})
public class InvalidLoginTest extends TestBase {

    private static final String INVALID_EMAIL_ADDRESS = "bitoti9737@nutrv.com";
    private static final String INVALID_PASSWORD = "Pawijds@1234";


    @Test(description = "Verify invalid login",
    groups = {"e2e", "sanity"})
    public void invalidLoginTest() {
        assertEquals(homePage.goToLoginPage()
                .doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD)
                .getErrorMessage(), "Authentication failed.");
    }
}
