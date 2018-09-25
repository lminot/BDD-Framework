package com.medmen.bdd.configs;

import org.openqa.selenium.safari.SafariOptions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    final static String CONNECTION_URL = "medmen.cf4ubqfpjaby.us-west-2.rds.amazonaws.com";
    final static String DB_USER = "medmen";
    final static String DB_PASSWORD = "AnJXHCUDHqkL349Z";
    private Connection connection = null;

    public void setJdbcDriver(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is the MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }
    }

    public void jdbcConnection() {
        try {
            connection = DriverManager
                    .getConnection(CONNECTION_URL,DB_USER, DB_PASSWORD);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
