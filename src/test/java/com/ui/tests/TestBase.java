package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.constants.Browser.*;

public class TestBase {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private boolean isLambdaTest;
//    private boolean isHeadless = false;

    @Parameters({"browser", "isLambdaTest", "isHeadless"})
    @BeforeMethod(description = "Load the home page of the website")
    public void setUp(
            @Optional("chrome") String browser,
            @Optional("false") boolean isLambdaTest,
            @Optional("false") boolean isHeadless, ITestResult result){
        WebDriver ltDriver;
        this.isLambdaTest = isLambdaTest;
        if(isLambdaTest) {
            ltDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage = new HomePage(ltDriver);
        } else {
            homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless);
            logger.info("Loading HomePage of the website");
        }
    }

    public BrowserUtility getInstance(){
        return homePage;
    }

    @AfterMethod(description = "Tear Down the browser")
    public void tearDown(){
        if(isLambdaTest) {
            LambdaTestUtility.quitSession();
        } else {
            homePage.quit();
        }
    }
}
