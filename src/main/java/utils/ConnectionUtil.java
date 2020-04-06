package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
  private static final String URL = "jdbc:mysql://localhost:3306/parking_lot_system?useUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong";
  private static final String USER = "root";
  private static final String PASSWORD = "gyqpass";

  private ConnectionUtil() {}

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}