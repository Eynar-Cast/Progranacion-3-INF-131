package ejercicios;

import java.util.Scanner;
import colas.CSNormal; // Nuestra clase de cola simple normal

public class EliminarTresMayoresSinStack {

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

            if (!estaEnPila(pila, num)) {
                pila.adicionar(num);
            } else {
                System.out.println("Número repetido. Ingrese otro.");
            }
        }

        System.out.println("\nPila original:");
        pila.mostrar();

        if (pila.nElem() < 3) {
            System.out.println("\nNo hay suficientes elementos para eliminar tres mayores.");
            return;
        }

        // Pasar a cola para conservar el orden
        CSNormal cola = new CSNormal(max);
        while (!pila.esVacia()) {
            cola.adicionar(pila.eliminar());
        }

        // Extraer todos a un arreglo para identificar los 3 mayores
        int n = cola.nElem();
        int[] datos = new int[n];
        for (int i = 0; i < n; i++) {
            datos[i] = (int) cola.eliminar();
        }

        // Ordenar descendente (burbuja simple)
        ordenarDescendente(datos);

        int[] tresMayores = new int[3];
        System.arraycopy(datos, 0, tresMayores, 0, 3);

        // Reconstruir pila sin los 3 mayores
        Pila pilaFinal = new Pila(max);
        for (int i = 0; i < n; i++) {
            int actual = datos[i];
            if (!esUnoDe(actual, tresMayores)) {
                pilaFinal.adicionar(actual);
            }
        }

        System.out.println("\nPila después de eliminar los tres números más grandes:");
        pilaFinal.mostrar();
    }

    // Verifica si un número está en la pila (sin perder datos)
    static boolean estaEnPila(Pila pila, int num) {
        boolean encontrado = false;
        Pila aux = new Pila(pila.getMax());
        while (!pila.esVacia()) {
            int val = (int) pila.eliminar();
            if (val == num) encontrado = true;
            aux.adicionar(val);
        }
        pila.vaciar(aux);
        return encontrado;
    }

    // Verifica si un número está en un arreglo
    static boolean esUnoDe(int val, int[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            if (val == arreglo[i]) return true;
        }
        return false;
    }

    // Ordenar arreglo descendente (burbuja)
    static void ordenarDescendente(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n -1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
