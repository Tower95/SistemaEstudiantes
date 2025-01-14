package xyz.Ismael.datos;

import xyz.Ismael.dominio.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

  public static void main(String[] args) {
    var studentsDAO = new StudentDAO();
    // lists studebts
    System.out.println("Student List: ");
    List<Student> students = studentsDAO.lists();
    students.forEach(System.out::println);
  }
}
