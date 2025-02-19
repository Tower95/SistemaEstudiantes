package xyz.Ismael.datos;

import xyz.Ismael.dominio.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import static xyz.Ismael.Connection.MyConnection.getConnection;

public class StudentDAO {
  public List<Student> lists(){
    List<Student> students = new ArrayList<>();
    PreparedStatement ps;
    ResultSet rs;

    Connection con = getConnection();

    String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";

    try {
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();

      while(rs.next()){
        var student = new Student();

        student.setIdEstudiante(rs.getInt("id_estudiante"));
        student.setNombre(rs.getString("nombre"));
        student.setApellido(rs.getString("apellido"));
        student.setTelefono(rs.getString("telefono"));
        student.setEmail(rs.getString("email"));

        students.add(student);
      }
    } catch (Exception e){
      System.out.println("Has occurred and Error: " + e.getMessage());
    }

    finally {
      try {
        con.close();
      } catch (Exception e) {
        System.out.println("Has occurred and Error: " + e.getMessage());
      }
    }

    return students;
  }

  public boolean findById(Student student ){
    PreparedStatement ps;
    ResultSet rs;
    Connection con = getConnection();

    String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
    try {

      ps = con.prepareStatement(sql);
      ps.setInt(1,student.getIdEstudiante());
      rs = ps.executeQuery();

      if(rs.next()){
        student.setNombre(rs.getString("nombre"));
        student.setApellido(rs.getString("apellido"));
        student.setEmail(rs.getString("email"));
        student.setTelefono(rs.getString("telefono"));
        return true;

      }

    }  catch (Exception e){
      System.out.println("Has occurred and Error: " + e.getMessage());
    }// end execute query

    finally {
      try {

        con.close();
      } catch (Exception e){
        System.out.println("Has occured and Error close connection: " + e.getMessage());
      }
    } // end finally

    return  false;
  }

  public static void main(String[] args) {
    var studentDAO = new StudentDAO();
    // lists studebts
    System.out.println("Student List: ");
    List<Student> students = studentDAO.lists();
    students.forEach(System.out::println);

    // search by id
    var student = new Student(2);
    var finded = studentDAO.findById(student);
    if(finded){
      System.out.println("I find the next student:");
      System.out.println(student.toString());
    }

  }
}
