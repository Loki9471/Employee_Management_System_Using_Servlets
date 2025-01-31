


package com.codegnan.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
  public static Connection connection;

  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adjava", "root", "2212");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    return connection;
  }
}

