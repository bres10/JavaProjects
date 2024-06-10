/* Escribir un programa que convierta unidades de medida por ejemplo, metros a pies, kilómetros a millas*/

import java.util.Scanner;

public class convUnidades {
    public static void main(String[] args) {
    

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("Bienvenido amigo, te ayudare en tu proxima conversion! ");

        while (continuar){
            System.out.println("Seleccione una de las opciones: ");
            System.out.println("1. Kilometros a Millas  ");
            System.out.println("2. Metros a Kilometros ");
            System.out.println("3. Centimetros a Metros");
            System.out.println("Ingrese una de las opciones: ");
            int opcion = scanner.nextInt();

          double resultado = 0;

            switch (opcion) {
                case 1:
                System.out.println("Ingrese el numero a convertir en kilometros ");
                    double num1 = scanner.nextDouble();
                   resultado = num1 * 0.62137;
                System.out.println("Su resultado es: " + resultado);
                    break;
                case 2: 
                    System.out.println("Ingrese el numero a convertir en metros ");
                    double num2 = scanner.nextDouble();
                   resultado = num2 * 0.001;
                   System.out.println("Su resultado es: " + resultado);
                    break;
                case 3: 
                    System.out.println("Ingrese el numero a convertir en centimetros ");
                    double num3 = scanner.nextDouble();
                   resultado = num3 * 0.62137;
                   System.out.println("Su resultado es: " + resultado);
                    break;
                default:
                    System.out.println("Error de seleccion.");
                    break;
            }

            
            
            System.out.print("¿Desea realizar otra operación? (si/no): ");
            String respuesta = scanner.next();

            if (!respuesta.equalsIgnoreCase("si")) {
            continuar = false;
            System.out.println("Adiós.");
            }
        

        } 
    

        scanner.close();

    
    }
}
