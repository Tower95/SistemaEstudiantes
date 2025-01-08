package xyz.Ismael.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
  public static Connection getConnection (){

    Connection  connection = null;

    var dataBase = "estudiantes_db";
    var url = "jdbc:mysql://localhost:3306/" + dataBase;
    var user = "root";
    var password = "example";

    // Load the class driver for Mysql in memory in execution time
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, user, password);

    } catch (ClassNotFoundException | SQLException e){
      System.out.println("And ERROR has occurre wiht Connection " + e.getMessage());
    }
    return connection;
  }
  public static void main(String[] args){
    var connection = MyConnection.getConnection();
    if(connection != null){
      System.out.println("SUCCESS Connection: " + connection);
    }
  }
}
