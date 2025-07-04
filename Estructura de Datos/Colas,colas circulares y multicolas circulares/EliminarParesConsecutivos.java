package colas;

import java.util.Scanner;

public class EliminarParesConsecutivos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Número de colas circulares: ");
        int n = sc.nextInt();

        CCCircular conjunto = new CCCircular(n + 1);

        // Crear y llenar las colas
        for (int i = 1; i <= n; i++) {
            System.out.print("\nCantidad de elementos para la cola circular " + i + ": ");
            int cant = sc.nextInt();

            conjunto.creaCSCircular(i, cant + 10); // cola con tamaño extra
            System.out.println("Ingrese " + cant + " elementos para la cola " + i + ":");

            for (int j = 0; j < cant; j++) {
                System.out.print("Elemento " + (j + 1) + ": ");
                int valor = sc.nextInt();
                conjunto.adicionar(valor, i);
            }
        }

        System.out.println("\n--- Colas antes de eliminar pares consecutivos ---");
        conjunto.mostrar();

        // Procesar cada cola del conjunto
        for (int i = 1; i <= conjunto.getNcc(); i++) {
            eliminarParesConsecutivos(conjunto, i);
        }

        System.out.println("\n--- Colas después de eliminar pares consecutivos ---");
        conjunto.mostrar();

        sc.close();
    }

    // Método que elimina pares consecutivos de una cola específica del conjunto
    public static void eliminarParesConsecutivos(CCCircular cc, int index) {
        CSCircular original = new CSCircular(100);
        int ne = cc.nElem(index);

        // Transferimos a auxiliar para analizar
        for (int i = 0; i < ne; i++) {
            Object dato = cc.eliminar(index);
            original.adicionar(dato);
        }
        

        CSCircular resultado = new CSCircular(100);
        Integer anterior = null;

        while (!original.esVacia()) {
            int actual = (int) original.eliminar();

            if (anterior != null && anterior + 1 == actual) {
                // Detecta par consecutivo, elimina ambos
                anterior = null; // reset para siguiente
            } else {
                if (anterior != null) {
                    resultado.adicionar(anterior);
                }
                anterior = actual;
            }
        }

        // Añadir último si no fue parte de un par
        if (anterior != null) {
            resultado.adicionar(anterior);
        }

        // Cargar el resultado de vuelta en la cola original del conjunto
        while (!resultado.esVacia()) {
            cc.adicionar(resultado.eliminar(), index);
        }
    }
}
