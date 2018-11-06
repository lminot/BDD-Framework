package com.medmen.bdd.configs;

import org.openqa.selenium.safari.SafariOptions;

import java.sql.*;

public class DataBaseConfig {

    final static String CONNECTION_URL = "jdbc:mysql://medmen.cf4ubqfpjaby.us-west-2.rds.amazonaws.com?autoReconnect=true&useSSL=false";
    final static String DB_USER = "medmen";
    final static String DB_PASSWORD = "AnJXHCUDHqkL349Z";
    final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
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
