import java.util.Scanner;

public class PepeTareas {

    public static void ordenar(int[] arr, int n) {
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static int calcularTiempo(int[] tareas, int actual, int indice, int n) {
        if (indice == n) return 0;
        if (tareas[indice] != actual) {
            return calcularTiempo(tareas, actual + 1, indice, n);
        } else {
            int mov = (indice < n - 1) ? 1 : 0;
            return 1 + mov + calcularTiempo(tareas, actual + 1, indice + 1, n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad de tareas: ");
        int n = sc.nextInt();
        int[] tareas = new int[n];

        System.out.println("Ingresa las tareas:");
        for (int i = 0; i < n; i++) {
            tareas[i] = sc.nextInt();
        }

        ordenar(tareas, n);

        int tiempo = calcularTiempo(tareas, 1, 0, n);
        System.out.println("Tiempo total para completar las tareas: " + tiempo);

        sc.close();
    }
}
