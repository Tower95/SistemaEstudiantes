package xyz.Ismael.dominio;

public class Student {

  private int idEstudiante;
  private String nombre;
  private String apellido;
  private String telefono;
  private String email;

  // Constructors.
  public Student(){}

  public Student(int idEstudiante){
    this.idEstudiante = idEstudiante;
  }

  public Student (
    String nombre,
    String apellido,
    String telefono,
    String email
  )
  {

    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;

  }
  public Student (
          int idEstudiante,
          String nombre,
          String apellido,
          String telefono,
          String email
  )
  {

    this.idEstudiante = idEstudiante;
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;

  }
  // End Constructors

  // GETTERS SETTERS
  public int getIdEstudiante() {
    return idEstudiante;
  }

  public void setIdEstudiante(int idEstudiante) {
    this.idEstudiante = idEstudiante;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  @Override
  public String toString() {
    return "Student{" +
            "idEstudiante=" + idEstudiante +
            ", nombre='" + nombre + '\'' +
            ", apellido='" + apellido + '\'' +
            ", telefono='" + telefono + '\'' +
            ", email='" + email + '\'' +
            "}";
  }
}
