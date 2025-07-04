/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sumanaturales;

/**
 *
 * @author 59162
 */
import java.util.Scanner;

public class SumaNaturales {

    // Función recursiva para sumar números naturales de 1 a n
    public static int sumaNaturales(int n) {
        // Caso base
        if (n == 1) {
            return 1;
        }
        // Llamada recursiva
        else {
            return n + sumaNaturales(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa un número entero positivo: ");
        int n = scanner.nextInt();

        // Validación de entrada
        if (n < 1) {
            System.out.println("Por favor, ingresa un número mayor o igual a 1.");
        } else {
            int resultado = sumaNaturales(n);
            System.out.println("La suma de los números naturales de 1 a " + n + " es: " + resultado);
        }

        scanner.close();
    }
}
