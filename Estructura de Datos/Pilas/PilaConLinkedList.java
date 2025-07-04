package ejercicios;

import java.util.LinkedList;
import java.util.Scanner;

public class PilaConLinkedList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Integer> pila = new LinkedList<>();

        System.out.println("Ingrese números enteros únicos para la pila (0 para terminar):");
        while (true) {
            System.out.print("Elemento: ");
            int num = sc.nextInt();
            if (num == 0) break;
            if (!pila.contains(num)) {
                pila.addFirst(num);  // Apilamos al frente
            } else {
                System.out.println("Número repetido, ingrese otro.");
            }
        }

        System.out.println("\nPila original (tope a fondo):");
        mostrarPila(pila);

        if (pila.isEmpty()) {
            System.out.println("Pila vacía, nada que hacer.");
            sc.close();
            return;
        }

        // Buscar máximo y eliminar elemento que sigue
        int max = pila.getFirst();
        for (int val : pila) {
            if (val > max) max = val;
        }

        // Encontrar posición del máximo (de arriba a abajo)
        int posMax = pila.indexOf(max);

        // Eliminar el que sigue al máximo si existe
        if (posMax + 1 < pila.size()) {
            int eliminado = pila.remove(posMax + 1);
            System.out.println("\nElemento eliminado que sigue al máximo (" + max + "): " + eliminado);
        } else {
            System.out.println("\nNo existe elemento que siga al máximo. Pila sin cambios.");
        }

        System.out.println("\nPila después de la operación (tope a fondo):");
        mostrarPila(pila);

        sc.close();
    }

    private static void mostrarPila(LinkedList<Integer> pila) {
        // Mostrar desde tope (inicio de LinkedList) hasta fondo (final)
        for (int val : pila) {
            System.out.println(val);
        }
    }
}
