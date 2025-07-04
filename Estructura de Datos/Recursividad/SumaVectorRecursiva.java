import java.util.Scanner;

public class SumaVectorRecursiva {

    public static int sumaVector(int[] arreglo, int n) {
        if (n == 0) {
            return 0;
        } else {
            return arreglo[n - 1] + sumaVector(arreglo, n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el tamaño del vector: ");
        int tamaño = scanner.nextInt();

        int[] arreglo = new int[tamaño];
        System.out.println("Ingrese los elementos del vector:");
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = scanner.nextInt();
        }

        int suma = sumaVector(arreglo, tamaño);
        System.out.println("La suma de los elementos es: " + suma);

        scanner.close();
    }
}
