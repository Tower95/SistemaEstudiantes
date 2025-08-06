package xyz.Ismael.datos;

import xyz.Ismael.dominio.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static xyz.Ismael.Connection.MyConnection.getConnection;

public class StudentDAO {
  public List<Student> lists() {
    List<Student> students = new ArrayList<>();
    PreparedStatement ps;
    ResultSet rs;

    Connection con = getConnection();

    String sql = "SELECT * FROM estudiante ORDER BY id_estudiante";

    try {
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {
        var student = new Student();

        student.setIdEstudiante(rs.getInt("id_estudiante"));
        student.setNombre(rs.getString("nombre"));
        student.setApellido(rs.getString("apellido"));
        student.setTelefono(rs.getString("telefono"));
        student.setEmail(rs.getString("email"));

        students.add(student);
      }
    } catch (Exception e) {
      System.out.println("Has occurred and Error: " + e.getMessage());
    } finally {
      try {
        con.close();
      } catch (Exception e) {
        System.out.println("Has occurred and Error: " + e.getMessage());
      }
    }

    return students;
  }

  public boolean findById(Student student) {
    PreparedStatement ps;
    ResultSet rs;
    Connection con = getConnection();

    String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
    try {

      ps = con.prepareStatement(sql);
      ps.setInt(1, student.getIdEstudiante());
      rs = ps.executeQuery();

      if (rs.next()) {

        student.setIdEstudiante(rs.getInt("id_estudiante"));
        student.setNombre(rs.getString("nombre"));
        student.setApellido(rs.getString("apellido"));
        student.setEmail(rs.getString("email"));
        student.setTelefono(rs.getString("telefono"));
        return true;

      }

    } catch (Exception e) {
      System.out.println("Has occurred and Error: " + e.getMessage());
    }// end execute query

    finally {
      try {

        con.close();
      } catch (Exception e) {
        System.out.println("Has occured and Error close connection: " + e.getMessage());
      }
    } // end finally

    return false;
  }

  public boolean patchStudent(Student studentMod) {

    int idStudent = studentMod.getIdEstudiante();
    PreparedStatement ps;
    ResultSet rs;
    Connection con = getConnection();

    String sqlSentence = "UPDATE estudiante " +
            "SET " + "nombre = ?," +
            "apellido = ?," +
            "telefono = ?," +
            "email = ?" +
            "WHERE id_estudiante = ? ;";

    System.out.println(sqlSentence);

    if (idStudent > 0) {

      try {

        ps = con.prepareStatement(sqlSentence);
        ps.setString(1, studentMod.getNombre());
        ps.setString(2, studentMod.getApellido());
        ps.setString(3, studentMod.getTelefono());
        ps.setString(4, studentMod.getEmail());
        ps.setInt(5, studentMod.getIdEstudiante());

        ps.execute();
        return true;

      } catch (SQLException e) {

        System.out.println(e.getMessage());
        throw new RuntimeException(e);

      } finally {
        try {

          con.close();

        } catch (SQLException e) {

          System.out.println("Error: Can't close connection! ");
          System.out.println(e.getMessage());
          throw new RuntimeException(e);

        }
      }

    }

    return false;
  }

  public static void main(String[] args) {
    var studentDAO = new StudentDAO();

    // remove student
    var student = new Student(9);
    var mod = studentDAO.findById(student);
    if (mod) {
      studentDAO.deleteStudent(student) ;
    }
    // lists studebts
    System.out.println("Student List: ");
    List<Student> students = studentDAO.lists();
    students.forEach(System.out::println);



    // Mod Student
//    var student = new Student(1);
//    var mod = studentDAO.findById(student);
//    if (mod) {
//      student.setNombre("Ismael");
//      student.setApellido("Martinez");
//      student.setTelefono("64134321");
//      student.setEmail("torrestni@gmail.com");
//
//      studentDAO.patchStudent(student);
//      System.out.println("Student with id : " + 1);
//      System.out.println(student.toString());
//    }
    /* Add student */

//    var newStudent = new Student("Daniel","Siqueiroz", "12345","Danielauditore@mail.com");
//    var added = studentDAO.addStudent(newStudent);
//    if(added){
//      System.out.println("new student was added " + newStudent + "\n SUCCESS");
//    }else {
//      System.out.println("can't add the new student " + newStudent + "\b FAILURE");
//    }

    /* search by id */
//    var student = new Student(2);
//    var finded = studentDAO.findById(student);
//    if(finded){
//      System.out.println("I find the next student:");
//      System.out.println(student.toString());
//    }

  }

  public boolean addStudent(Student student) {

    boolean result = false;

    PreparedStatement ps;
    Connection con = getConnection();
    String sql = "INSERT INTO estudiante (nombre, apellido, telefono, email)" +
            " VALUES (?, ?, ?, ?)";

    try {
      ps = con.prepareStatement(sql);

      /* set parameters */
      ps.setString(1, student.getNombre());
      ps.setString(2, student.getApellido());
      ps.setString(3, student.getTelefono());
      ps.setString(4, student.getEmail());
      ps.execute();
      result = true;

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    } // end try catch

    finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.out.println("Error: Can't close connection! ");
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
      }
    }

    return result;
  }

  public boolean deleteStudent(Student student){

    var result = false;

    Connection con = getConnection();
    PreparedStatement ps;
    String sql = "DELETE FROM estudiante " +
                "WHERE id_estudiante = ?;";

    try {
      ps = con.prepareStatement(sql);

      /* set parameters */
      ps.setInt(1, student.getIdEstudiante());
      ps.execute();
      System.out.println("The student wiht id : " + student.getIdEstudiante()+
              " was deleted\n");
      System.out.println(student);
      result = true;

    } catch (SQLException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException(e);
    } // end try catch

    finally {
      try {
        con.close();
      } catch (SQLException e) {
        System.out.println("Error: Can't close connection! ");
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
      }
    }


    return result;
  }
}
