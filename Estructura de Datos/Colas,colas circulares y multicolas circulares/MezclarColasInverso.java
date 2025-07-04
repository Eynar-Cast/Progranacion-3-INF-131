package colas;

import java.util.Scanner;

public class MezclarColasInverso {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad máxima de elementos para cada cola: ");
        int max = sc.nextInt();

        CSNormal A = new CSNormal(max);
        CSNormal B = new CSNormal(max);
        CSNormal C = new CSNormal(max * 2); // Cola mezclada
        CSNormal D = new CSNormal(max * 2); // Cola invertida

        // Ingreso para cola A
        System.out.println("\n--- Ingresar elementos para Cola A ---");
        ingresarElementos(A, sc);

        // Ingreso para cola B
        System.out.println("\n--- Ingresar elementos para Cola B ---");
        ingresarElementos(B, sc);

        // Mezcla alternada: A-B-A-B...
        while (!A.esVacia() || !B.esVacia()) {
            if (!A.esVacia()) C.adicionar(A.eliminar());
            if (!B.esVacia()) C.adicionar(B.eliminar());
        }

        System.out.println("\n--- Cola mezclada (C) ---");
        C.mostrar();

        // Invertir C en D usando nuestra clase Pila
        invertirCola(C, D);

        System.out.println("\n--- Cola final invertida (D) ---");
        D.mostrar();

        sc.close();
    }

    static void ingresarElementos(CSNormal cola, Scanner sc) {
        while (!cola.esLlena()) {
            System.out.print("Elemento (0 para terminar): ");
            int dato = sc.nextInt();
            if (dato == 0) break;
            cola.adicionar(dato);
        }
    }

    static void invertirCola(CSNormal origen, CSNormal destino) {
        Pila aux = new Pila(origen.nElem());
        while (!origen.esVacia()) {
            aux.adicionar(origen.eliminar());
        }
        while (!aux.esVacia()) {
            destino.adicionar(aux.eliminar());
        }
    }
}
