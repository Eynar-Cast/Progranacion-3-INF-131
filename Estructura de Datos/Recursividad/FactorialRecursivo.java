import java.util.Scanner;

public class FactorialRecursivo {

    public static long factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un número entero no negativo: ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("El número debe ser mayor o igual a 0.");
        } else {
            long resultado = factorial(n);
            System.out.println("El factorial de " + n + " es: " + resultado);
        }

        scanner.close();
    }
}
