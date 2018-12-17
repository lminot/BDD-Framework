package com.medmen.bdd.configs;

import com.medmen.bdd.utils.FileLoaderUtils;
import org.apache.commons.codec.binary.Base64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {

    private static String CONNECTION_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String DB_DRIVER;
    private static String STAGE_DB_CATALOG;

    public Connection connection = null;

    public void registerJdbcDriver() {
        setDbConfigs();
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Error, MySQL JDBC Driver missing.");
            e.printStackTrace();
        }
    }

    public Connection getJdbcConnection() {
        try {
            connection = DriverManager
                    .getConnection(CONNECTION_URL, DB_USER, new String(Base64.decodeBase64(DB_PASSWORD.getBytes())));
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

    private static void setDbConfigs() {
        FileLoaderUtils fileLoaderUtils = new FileLoaderUtils();
        EnvironmentConfig environmentConfig = new EnvironmentConfig();
        String configFile = environmentConfig.getConfigFile();

        CONNECTION_URL = fileLoaderUtils.getValueFromPropertyFile(configFile, "db.connection.url");
        DB_USER = fileLoaderUtils.getValueFromPropertyFile(configFile, "db.user.name");
        DB_PASSWORD = fileLoaderUtils.getValueFromPropertyFile(configFile, "db.password");
        DB_DRIVER = fileLoaderUtils.getValueFromPropertyFile(configFile, "db.driver");
        STAGE_DB_CATALOG = fileLoaderUtils.getValueFromPropertyFile(configFile, "db.catalog");
    }
}
