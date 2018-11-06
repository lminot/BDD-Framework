package com.medmen.bdd.utils;

import com.medmen.bdd.configs.DataBaseConfig;

import java.sql.*;

public class JdbcUtils {

  private DataBaseConfig dbc = new DataBaseConfig();

  public ResultSet executeSelectQuery(String selectSql) throws SQLException {
    dbc.registerJdbcDriver();
    Connection conn = dbc.getJdbcConnection();
    ResultSet resultSet = null;

    try {
      Statement stmt = conn.createStatement();
      resultSet = stmt.executeQuery(selectSql);

    } catch (SQLException e) {
      dbc.closeConnection();
      e.printStackTrace();
    }
    return resultSet;
  }

  public ResultSet executeQuery(String selectSql) {
    dbc.registerJdbcDriver();
    Connection conn = dbc.getJdbcConnection();
    ResultSet resultSet = null;

    try {
      Statement stmt = conn.createStatement();
      if (stmt.execute(selectSql)) {
        System.out.println("valid query string, executing : " + selectSql);
        resultSet = stmt.getResultSet();
      } else {
        System.out.println("invalid query string!");
        dbc.closeConnection();
      }

    } catch (SQLException e) {

      e.printStackTrace();
    }
    return resultSet;
  }

  public int executeUpdateQuery(String insertSql) {
    dbc.registerJdbcDriver();
    Connection conn = dbc.getJdbcConnection();
    int rowsUpdated = 0;

    try {
      Statement stmt = conn.createStatement();
      rowsUpdated = stmt.executeUpdate(insertSql);
      if (rowsUpdated > 0) {
        System.out.println("inserted successfully : " + insertSql);
      } else {
        System.out.println("insertion failed!");
        dbc.closeConnection();
      }

    } catch (SQLException e) {
      //
      e.printStackTrace();
    }
    return rowsUpdated;
  }
}
