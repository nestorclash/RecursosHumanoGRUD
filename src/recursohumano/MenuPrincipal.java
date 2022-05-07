package recursohumano;

import java.sql.SQLException; //Libreria para conexiones con base de datos
import java.util.Scanner;

public class MenuPrincipal {
    //throws especifica el tipo de excepcion que puede ocasionar
    public static void main(String[] args) throws SQLException {
        desplegarMenu();
    }
    
public static void desplegarMenu() throws SQLException {
    Scanner opcionSeleccionada = new Scanner (System.in);
    String opcionMenu;
    //Menu a desplegar  
    System.out.println("*****************************************************");
    System.out.println("|      CRUD DE JAVA EN CONSOLA                      |");
    System.out.println("*****************************************************");
    System.out.println("|  Opciones:                                        |");
    System.out.println("|               1. Crear registros                  |");
    System.out.println("|               2. Consultar registros              |");
    System.out.println("|               3. Actualizar registros             |");
    System.out.println("|               4. Eliminar registros               |");
    System.out.println("|               5. Salir                            |");
    System.out.println("*****************************************************");
    System.out.println("Seleccionar opcion: ");
    opcionMenu = opcionSeleccionada.next();
    
    //Despliegue de menu basado en las opcion seleccionada
    switch (opcionMenu) {
        case "1":
            
            break;
        case "2":
            
            break;
        case "3":
            
            break;
        case "4":
            
            break;
        case "5":
            System.exit(0);
            break;
        default:
            System.out.println("Seleccion invalidad!");
            break;
        
            }
            
        }
    }
