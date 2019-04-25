package com.codecool.shop.config;

import com.codecool.shop.dao.implementation.jdbc.DBconfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        //read database config from file
        DBconfig dBconfig = DBconfig.getInstance();

        dBconfig.setupConfig("configDB.cfg");



    }
}
