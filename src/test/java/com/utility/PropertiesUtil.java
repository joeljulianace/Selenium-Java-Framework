package com.utility;

import com.constants.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static String readProperty(Environment environment, String propertyName){
        File propertiesFiles = new File(System.getProperty("user.dir") + "/config/" + environment + ".properties");
        FileReader fileReader = null;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(propertiesFiles);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value = properties.getProperty(propertyName.toUpperCase());
        return value;
    }
}
