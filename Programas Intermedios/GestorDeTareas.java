import java.util.ArrayList;
import java.util.Scanner;


/* Crear un programa que permita al usuario agregar, eliminar y ver tareas. Utiliza una lista para almacenar las tareas. */


public class GestorDeTareas {
    public static void main(String[] args) {
        ArrayList<String> tareas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        while(true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Eliminar tarea");
            System.out.println("3. Ver todas las tareas");
            System.out.println("4. Salir");
            
            System.out.println();
            System.out.println("Tu Opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer
            
            switch(opcion) {
                case 1:
                    System.out.print("Ingrese la tarea: ");
                    String tarea = scanner.nextLine();
                    tareas.add(tarea);
                    System.out.println("\nTarea agregada.");
                    break;
                case 2:
                    System.out.print("Ingrese el número de la tarea a eliminar: ");
                    int index = scanner.nextInt();
                    if(index >= 0 && index < tareas.size()) {
                        tareas.remove(index);
                        System.out.println("\nTarea eliminada.");
                    } else {
                        System.out.println("\nNúmero de tarea no válido.");
                    }
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Lista de tareas:");
                    System.out.println();
                    for(int i = 0; i < tareas.size(); i++) {
                        System.out.println((i + 1) + ". " + tareas.get(i));
                    }
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Saliendo...");
                    System.out.println();
                    scanner.close();
                    return;
                default:
                    System.out.println();
                    System.out.println("Opción no válida.");
                    System.out.println();

            }
        }
    }
}



            
