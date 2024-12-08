package com.makemytrip.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private Properties properties;

    //step 2: write property manager to get the properties url and browser.
    public PropertyManager(String path) throws IOException {
        properties=new Properties();
        FileInputStream fileInputStream=new FileInputStream(path);
        properties.load(fileInputStream);
    }
    public String geturl()
    {
        return properties.getProperty("url");
    }
    public String getbrowser()
    {
        return properties.getProperty("browser");
    }
}
