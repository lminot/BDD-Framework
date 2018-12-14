package com.medmen.bdd.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    final static String CONNECTION_URL = "jdbc:mysql://medmen.cf4ubqfpjaby.us-west-2.rds.amazonaws.com?autoReconnect=true&useSSL=false";
    final static String DB_USER = "medmen";
    final static String DB_PASSWORD = "AnJXHCUDHqkL349Z";
    final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String STAGE_DB_CATALOG = "medmen_development";
    public Connection connection = null;

    public void registerJdbcDriver() {
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
                    .getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
            //todo control db flow here
            connection.setCatalog(STAGE_DB_CATALOG);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("Connected successfully to: " + STAGE_DB_CATALOG);
        } else {
            System.out.println("ERROR, Failed to make connection to: " + STAGE_DB_CATALOG);
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
