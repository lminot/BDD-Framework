package com.medmen.bdd.configs;

import org.openqa.selenium.safari.SafariOptions;

import java.sql.*;

public class DataBaseConfig {

    final static String CONNECTION_URL = "";
    final static String DB_USER = "";
    final static String DB_PASSWORD = "";
    final static String DB_DRIVER = "";
    public Connection connection = null;

    public void registerJdbcDriver(){
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is the MySQL JDBC Driver?");
            e.printStackTrace();
        }
    }

    public Connection getJdbcConnection() {
        try {
            connection = DriverManager
                    .getConnection(CONNECTION_URL,DB_USER, DB_PASSWORD);
            //todo control db flow here
            connection.setCatalog("medmen_development");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
