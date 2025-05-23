package com.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {
    public static Iterator<User> readExcelFile(String fileName) {
        //XLXS
        File file = new File(System.getProperty("user.dir") + "/testData/" + fileName);
        XSSFWorkbook xssfWorkbook = null;
        List<User> userList = null;
        User user = null;
        Row row = null;
        Cell username = null;
        Cell password = null;
        try {
            xssfWorkbook = new XSSFWorkbook(file);
            XSSFSheet xssfSheet = xssfWorkbook.getSheet("LoginTestData");
            Iterator<Row> rowIterator = xssfSheet.iterator();
            rowIterator.next(); //Skipping the column names
            userList = new ArrayList<>();
            while(rowIterator.hasNext()) {
                row = rowIterator.next();
                username = row.getCell(0);
                password = row.getCell(1);
                user = new User(username.toString(), password.toString());
                userList.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }finally {
            try {
                xssfWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return userList.iterator();
    }
}
