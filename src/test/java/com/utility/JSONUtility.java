package com.utility;

import com.google.gson.Gson;
import com.ui.pojo.Config;
import com.ui.pojo.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JSONUtility {
    public static Environment readJson(com.constants.Environment env) {
        Gson gson = new Gson();
        File jsonFile = new File(System.getProperty("user.dir") + "/config/config.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(jsonFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Config config = gson.fromJson(fileReader, Config.class);
        Environment environment = config.getEnvironments().get(env.toString());
        return environment;
    }
}
