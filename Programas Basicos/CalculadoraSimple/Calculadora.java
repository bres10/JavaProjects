import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione una operación:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.print("Ingrese su opción: ");
            int opcion = scanner.nextInt();

            System.out.print("Ingrese el primer número: ");
            double numero1 = scanner.nextDouble();
            System.out.print("Ingrese el segundo número: ");
            double numero2 = scanner.nextDouble();

            double resultado = 0;
            boolean operacionValida = true;

            switch (opcion) {
                case 1:
                    resultado = numero1 + numero2;
                    break;
                case 2:
                    resultado = numero1 - numero2;
                    break;
                case 3:
                    resultado = numero1 * numero2;
                    break;
                case 4:
                    if (numero2 != 0) {
                        resultado = numero1 / numero2;
                    } else {
                        System.out.println("Error: División por cero no permitida.");
                        operacionValida = false;
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
                    operacionValida = false;
                    break;
            }

            if (operacionValida) {
                System.out.println("El resultado es: " + resultado);
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
