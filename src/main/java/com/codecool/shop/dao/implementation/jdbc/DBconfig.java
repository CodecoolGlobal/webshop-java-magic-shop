package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.config.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconfig {
    private static DBconfig instance = new DBconfig();
    private String db_name;
    private String username;
    private String password;
    private String URL;

    private DBconfig() {

    }

    public void setupConfig(String filepath) {
        ConfigReader configReader = new ConfigReader(filepath);
        this.setUsername(configReader.getUsername());
        this.setPassword(configReader.getUserPassword());
        this.setDb_name(configReader.getDatabasename());
        this.setURL(configReader.getURL());
    }

    public static DBconfig getInstance() {
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getURL() {
        return URL;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getDb_name() {
        return db_name;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                URL,
                username,
                password);
    }
}
