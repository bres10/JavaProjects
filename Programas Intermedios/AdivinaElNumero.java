/*Desarrollar un juego en el que el programa seleccione un número al azar y el usuario tenga que adivinarlo. 
El programa dará pistas ("más alto", "más bajo") después de cada intento del usuario. */

import java.util.Scanner;
import java.util.Random;


public class AdivinaElNumero {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    int numSecret = random.nextInt(50)+1;
    int intentos = 0;
    boolean adivinado = false;


    System.out.println("¡Bienvenido al juego Adivina el Número!");
    System.out.println("He escogido un número entre 1 y 50. Intenta adivinarlo.");

    while (!adivinado) {
        System.out.print("\nIntroduce tu numero: ");
        int adivinanza = scanner.nextInt();
        intentos++;

        if (adivinanza < numSecret) {
            System.out.println("\nCerca, el número es más alto");
        } else if (adivinanza > numSecret) {
            System.out.println("\n Cerca, Más bajo");
        } else {
            adivinado = true;
            System.out.println("¡Correcto! Has adivinado el número en " + intentos + " intentos.");
        }
    }

    scanner.close();

  }  
}
