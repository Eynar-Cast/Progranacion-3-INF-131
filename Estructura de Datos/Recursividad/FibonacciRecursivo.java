import java.util.Scanner;

public class FibonacciRecursivo {

    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa un número entero no negativo: ");
        int n = scanner.nextInt();

        if (n < 0) {
            System.out.println("El número debe ser mayor o igual a 0.");
        } else {
            System.out.println("Fibonacci de " + n + " es: " + fibonacci(n));
        }

        scanner.close();
    }
}
