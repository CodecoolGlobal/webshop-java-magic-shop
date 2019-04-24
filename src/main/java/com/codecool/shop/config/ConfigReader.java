package com.codecool.shop.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private String filename;
    private Properties properties = new Properties();
    private InputStream is;

    public ConfigReader(String filename) {
        this.filename = filename;
        is = getClass().getClassLoader().getResourceAsStream(filename);
        try {
            properties.load(is);
        } catch (IOException e) {
            System.out.println("io lol");
        }

    }

    public String getURL() {
        return properties.getProperty("db.URL");
    }

    public String getUsername() {
        return properties.getProperty("db.username");

    }

    public String getUserPassword() {
        return properties.getProperty("db.password");
    }

    public String getDatabasename() {
        return properties.getProperty("db.db_name");
    }
}
