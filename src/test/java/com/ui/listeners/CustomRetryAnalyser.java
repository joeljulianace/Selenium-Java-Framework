package com.ui.listeners;

import com.constants.Environment;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class CustomRetryAnalyser implements IRetryAnalyzer {
//    private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtil.readProperty(Environment.QA, "MAX_NUMBER_OF_ATTEMPTS"));
    private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJson(Environment.QA).getMAX_NUMBER_OF_ATTEMPTS();
    private static int currentAttempt = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
            currentAttempt++;
            return true;
        }
        return false;
    }
}
