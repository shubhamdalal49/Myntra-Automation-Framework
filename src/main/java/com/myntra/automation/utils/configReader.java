package com.myntra.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configReader {

    private Properties prop;

    public Properties init_prop() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("./src/main/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
