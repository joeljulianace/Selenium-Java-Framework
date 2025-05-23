package com.ui.dataproviders;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
        Gson gson = new Gson();
        File file = new File(System.getProperty("user.dir") + "/testData/loginData.json");
        FileReader fileReader = new FileReader(file);
        TestData data = gson.fromJson(fileReader, TestData.class);
        List<Object[]> testData = new ArrayList<Object[]>();
        for (User user : data.getData()) {
            testData.add(new Object[] {user});
        }

        return testData.iterator();
    }

    @DataProvider(name = "LoginCSVDataProvider")
    public Iterator<User> loginCSVDataProvider() {
        return CSVReaderUtility.readCSVFile("loginData.csv");
    }

    @DataProvider(name = "LoginExcelDataProvider")
    public Iterator<User> loginExcelDataProvider() {
        return ExcelReaderUtility.readExcelFile("loginData.xlsx");
    }
}
