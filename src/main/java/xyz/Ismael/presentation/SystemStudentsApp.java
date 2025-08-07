package xyz.Ismael.presentation;
import xyz.Ismael.datos.StudentDAO;

import java.util.Scanner;

public class SystemStudentsApp {


  public static void main (String[] args){

    StudentDAO students = new StudentDAO();
    Scanner consol = new Scanner(System.in);

    menu();
    int option = Integer.parseInt(consol.nextLine());
    System.out.println(option);

  }

  private static void menu (){
    System.out.println("*** System students ***");
    System.out.println("1.- Listar.");
    System.out.println("2.- Buscar.");
    System.out.println("3.- Agregar.");
    System.out.println("4.- Modificar.");
    System.out.println("5.- Eliminar.");
    System.out.println("6.- Salir.");
    System.out.print("Seleccione una opcion: ");
  }
}

