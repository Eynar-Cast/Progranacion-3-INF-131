package ejercicios;

import java.util.Scanner;

public class EliminarTresMayoresConPila {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cantidad máxima de elementos en la pila: ");
        int max = sc.nextInt();

        Pila pila = new Pila(max);

        System.out.println("Ingrese números enteros únicos para la pila (0 para terminar):");
        while (true) {
            System.out.print("Elemento: ");
            int num = sc.nextInt();
            if (num == 0) break;

            // Verificar si ya está en la pila
            if (!estaEnPila(pila, num)) {
                pila.adicionar(num);
            } else {
                System.out.println("Número repetido, ingrese otro.");
            }
        }

        System.out.println("\nPila original:");
        pila.mostrar();

        if (pila.nElem() < 3) {
            System.out.println("\nNo hay suficientes elementos para eliminar tres mayores.");
            sc.close();
            return;
        }

        // Sacar todos los elementos a un arreglo para ordenarlos
        int n = pila.nElem();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) pila.eliminar();
        }

        // Ordenar el arreglo descendente
        ordenarDescendente(arr);

        // Los 3 mayores:
        int[] tresMayores = new int[3];
        System.arraycopy(arr, 0, tresMayores, 0, 3);

        // Reconstruir pila sin los tres mayores
        Pila aux = new Pila(max);
        for (int i = 3; i < n; i++) {
            aux.adicionar(arr[i]);
        }

        // Pasar de aux a pila para mantener orden original
        pila = new Pila(max);
        while (!aux.esVacia()) {
            pila.adicionar(aux.eliminar());
        }

        System.out.println("\nPila después de eliminar los tres números más grandes:");
        pila.mostrar();

        sc.close();
    }

    // Método para verificar si un número está en la pila (sin modificarla)
    static boolean estaEnPila(Pila pila, int num) {
        boolean encontrado = false;
        Pila aux = new Pila(pila.getMax());
        while (!pila.esVacia()) {
            int val = (int) pila.eliminar();
            if (val == num) encontrado = true;
            aux.adicionar(val);
        }
        while (!aux.esVacia()) {
            pila.adicionar(aux.eliminar());
        }
        return encontrado;
    }

    // Ordena un arreglo de enteros en forma descendente (burbuja simple)
    static void ordenarDescendente(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n -1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
