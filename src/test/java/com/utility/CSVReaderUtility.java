package com.utility;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReaderUtility {

    public static Iterator<User> readCSVFile(String fileName) {
        File file = new File(System.getProperty("user.dir") + "/testData/" + fileName);
        FileReader fileReader = null;
        CSVReader csvReader = null;
        String[] line = null;
        List<User> userList = null;
        User userData = null;
        try {
            fileReader = new FileReader(file);
            csvReader = new CSVReader(fileReader);
            csvReader.readNext(); //Reading to skip the column names
            userList = new ArrayList<>();
            while((line = csvReader.readNext()) != null) {
                userData = new User(line[0], line[1]);
                userList.add(userData);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList.iterator();
    }
}
