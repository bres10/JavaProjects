package AgendaContactos;
/*Desarrollar una aplicación que permita al usuario agregar,
 eliminar y buscar contactos, almacenando la información en un archivo de texto. */

 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Scanner;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 class Contacto {
     private String nombre;
     private String telefono;
     private String email;
 
     public Contacto(String nombre, String telefono, String email) {
         this.nombre = nombre;
         this.telefono = telefono;
         this.email = email;
     }
 
     public String getNombre() {
         return nombre;
     }
 
     public void setNombre(String nombre) {
         this.nombre = nombre;
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
         return nombre + "," + telefono + "," + email;
     }
 
     public static Contacto fromString(String linea) {
         String[] partes = linea.split(",");
         return new Contacto(partes[0], partes[1], partes[2]);
     }
 }
 
 public class AgendaContactos {
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         List<Contacto> contactos = new ArrayList<>();
         
         cargarContactos(contactos);
 
         while (true) {
             System.out.println("\nSeleccione una opción:");
             System.out.println("1. Agregar contacto");
             System.out.println("2. Eliminar contacto");
             System.out.println("3. Ver todos los contactos");
             System.out.println("4. Editar contacto");
             System.out.println("5. Salir");
             
             int opcion = scanner.nextInt();
             scanner.nextLine();  // Limpiar el buffer
             
             switch(opcion) {
                 case 1:
                     System.out.print("\nIngrese el nombre del contacto: ");
                     String nombre = scanner.nextLine();
                     System.out.print("Ingrese el número de teléfono del contacto: ");
                     String telefono = scanner.nextLine();
                     System.out.print("Ingrese el email del contacto: ");
                     String email = scanner.nextLine();
                     contactos.add(new Contacto(nombre, telefono, email));
                     guardarContactos(contactos);
                     System.out.println("Contacto agregado.");
                     break;
                 case 2:
                     System.out.print("\nIngrese el número del contacto a eliminar: ");
                     int indexEliminar = scanner.nextInt() - 1;
                     if (indexEliminar >= 0 && indexEliminar < contactos.size()) {
                         contactos.remove(indexEliminar);
                         guardarContactos(contactos);
                         System.out.println("Contacto eliminado.");
                     } else {
                         System.out.println("\nNúmero de contacto no válido.");
                     }
                     break;
                 case 3:
                     System.out.println("\nLista de contactos:");
                     for (int i = 0; i < contactos.size(); i++) {
                         Contacto c = contactos.get(i);
                         System.out.println((i + 1) + ". " + c.getNombre() + " - " + c.getTelefono() + " - " + c.getEmail());
                     }
                     break;
                 case 4:
                     System.out.print("\nIngrese el número del contacto a editar: ");
                     int indexEditar = scanner.nextInt() - 1;
                     scanner.nextLine();  // Limpiar el buffer
                     if (indexEditar >= 0 && indexEditar < contactos.size()) {
                         Contacto contactoAEditar = contactos.get(indexEditar);
                         System.out.println("Editando contacto: " + contactoAEditar.getNombre());
                         System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
                         String nuevoNombre = scanner.nextLine();
                         if (!nuevoNombre.isBlank()) {
                             contactoAEditar.setNombre(nuevoNombre);
                         }
                         System.out.print("Nuevo número de teléfono (dejar en blanco para no cambiar): ");
                         String nuevoTelefono = scanner.nextLine();
                         if (!nuevoTelefono.isBlank()) {
                             contactoAEditar.setTelefono(nuevoTelefono);
                         }
                         System.out.print("Nuevo email (dejar en blanco para no cambiar): ");
                         String nuevoEmail = scanner.nextLine();
                         if (!nuevoEmail.isBlank()) {
                             contactoAEditar.setEmail(nuevoEmail);
                         }
                         guardarContactos(contactos);
                         System.out.println("Contacto editado.");
                     } else {
                         System.out.println("\nNúmero de contacto no válido.");
                     }
                     break;
                 case 5:
                     System.out.println("\nSaliendo...");
                     scanner.close();
                     return;
                 default:
                     System.out.println("Opción no válida.");
             }
         }
     }
 
     public static void cargarContactos(List<Contacto> contactos) {
         try (BufferedReader br = new BufferedReader(new FileReader("contactos.txt"))) {
             String linea;
             while ((linea = br.readLine()) != null) {
                 contactos.add(Contacto.fromString(linea));
             }
         } catch (IOException e) {
             System.out.println("Error al cargar los contactos: " + e.getMessage());
         }
     }
 
     public static void guardarContactos(List<Contacto> contactos) {
         try (PrintWriter pw = new PrintWriter(new FileWriter("contactos.txt"))) {
             for (Contacto contacto : contactos) {
                 pw.println(contacto);
             }
         } catch (IOException e) {
             System.out.println("Error al guardar los contactos: " + e.getMessage());
         }
     }
 }
 