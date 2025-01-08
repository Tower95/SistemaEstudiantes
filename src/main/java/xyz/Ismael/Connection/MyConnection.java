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
    //Cargamos la clase del driver de mysql en memoria

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, user, password);

    } catch (ClassNotFoundException | SQLException e){
      System.out.println("Ocurrio un error en la conexion: " + e.getMessage());
    }
    return connection;
  }

}
