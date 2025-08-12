package xyz.Ismael.presentation;
import xyz.Ismael.datos.StudentDAO;
import xyz.Ismael.dominio.Student;

import java.text.BreakIterator;
import java.util.Scanner;

public class SystemStudentsApp {



  public static void main (String[] args){

    StudentDAO studentsDAO = new StudentDAO();
    Scanner consol = new Scanner(System.in);
    boolean exit = false;

    while(!exit) {

      printMenu();
      exit = selectOption(consol, studentsDAO);
      waitImput(consol);

    }

  }

  private static void printMenu (){
    System.out.println("*** System students ***");
    System.out.println("1.- Listar.");
    System.out.println("2.- Buscar.");
    System.out.println("3.- Agregar.");
    System.out.println("4.- Modificar.");
    System.out.println("5.- Eliminar.");
    System.out.println("6.- Salir.");
    System.out.print("Seleccione una opcion: ");
  }

  private static boolean selectOption (Scanner consola, StudentDAO studentsDAO){

    Student student;

    var option = Integer.parseInt(consola.nextLine());

    var id = 0;
    var name ="" ;
    var lastName = "";
    var phoneNumber = "" ;
    var email = "";

    boolean exit = false;
      switch (option) {

        case 1:
          System.out.println(studentsDAO.lists());
          break;

        case 2:
          System.out.println("Ingrese el id del Estudiante que quiere encontrar");

           id = Integer.parseInt(consola.nextLine());

          student = new Student(id);
          studentsDAO.findById(student);

          if(student.getNombre() == null){
            System.out.println(
                    "El estudiante con el id: " +
                    student.getIdEstudiante() +
                    " No exist"
            );
          } else {
            System.out.println(student);
          }
          break;

        case 3:

          System.out.println("Ingrese los datos del studiante: ");

          System.out.println("Nombre: ");
          name = (String) consola.nextLine();

          System.out.println("Apellido: ");
          lastName = (String) consola.nextLine();

          System.out.println("Telefono: ");
          phoneNumber = (String) consola.nextLine();

          System.out.println("Email: ");
          email = (String) consola.nextLine();

          student = new Student(name,lastName,phoneNumber,email);
          studentsDAO.addStudent(student);

          System.out.println(student);
          break;

        case 4:

          System.out.println("Ingrese el id del estudiante a actualizar: ");
          id = Integer.parseInt(consola.nextLine());

          student = new Student(id);

          if(!studentsDAO.findById(student)){
            System.out.println("No encontre el estudiante con el id : " + student.getIdEstudiante());
            break;
          }

          System.out.println("Nombre: ");
          name = (String) consola.nextLine();

          System.out.println("Apellido: ");
          lastName = (String) consola.nextLine();

          System.out.println("Telefono: ");
          phoneNumber = (String) consola.nextLine();

          System.out.println("Email: ");
          email = (String) consola.nextLine();

          student.setNombre(name);
          student.setApellido(lastName);
          student.setTelefono(phoneNumber);
          student.setEmail(email);

          if(studentsDAO.patchStudent(student)){
            System.out.println("Se acutualizo el esutdiante:");
            System.out.println(student);
          }

          break;

        case 5:
          System.out.println("Ingrese el id del estudiante a eliminar: ");
          id = Integer.parseInt(consola.nextLine());

          student = new Student(id);

          if(!studentsDAO.findById(student)){
            System.out.println("No encontre el estudiante con el id : " + student.getIdEstudiante());
            break;
          }

          if(studentsDAO.deleteStudent(student)){
            System.out.println("Se elimino el studiante con el id: " +
                    student.getIdEstudiante()
                    );
          }

          break;

        case 6:
          exit = true;
          break;

        default:
          System.out.println("Sorry thats is imposible.");
          break;
      }

    return exit;
  }

  private static void waitImput(Scanner console){
    System.out.println("Precione Enter para continuar");
    console.nextLine();
  }
}
