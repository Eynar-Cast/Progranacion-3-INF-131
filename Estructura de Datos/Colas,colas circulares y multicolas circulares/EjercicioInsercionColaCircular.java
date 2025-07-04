package colas;

import java.util.Scanner;

// Cola circular simple
class CSCircular {
    protected int ini, fin, max;
    protected Object[] v;

    CSCircular(int cc) {
        max = cc;
        ini = 0;
        fin = 0;
        v = new Object[max];
    }

    boolean esVacia() {
        return ini == fin;
    }

    boolean esLlena() {
        return (fin + 1) % max == ini;
    }

    void adicionar(Object ele) {
        if (esLlena()) {
            System.out.println("Cola llena");
        } else {
            fin = (fin + 1) % max;
            v[fin] = ele;
        }
    }

    Object eliminar() {
        if (esVacia()) {
            System.out.println("Cola vacía");
            return null;
        } else {
            ini = (ini + 1) % max;
            return v[ini];
        }
    }

    void mostrar() {
        int i = (ini + 1) % max;
        while (i != (fin + 1) % max) {
            System.out.println("\t" + v[i]);
            i = (i + 1) % max;
        }
    }

    int nElem() {
        return (fin - ini + max) % max;
    }
}

// Conjunto de colas circulares
class CCCircular {
    int ncc, max;
    CSCircular[] ccc;

    CCCircular(int cc) {
        max = cc;
        ncc = 0;
        ccc = new CSCircular[max + 1]; // usar índices desde 1
    }

    void creaCSCircular(int i, int cai) {
        if (i >= 1 && i <= max) {
            ccc[i] = new CSCircular(cai + 1); // +1 para evitar cola llena con tope
            if (i > ncc) ncc = i;
        }
    }

    boolean esVacia(int i) {
        return ccc[i] != null && ccc[i].esVacia();
    }

    boolean esLlena(int i) {
        return ccc[i] != null && ccc[i].esLlena();
    }

    void adicionar(Object da, int i) {
        if (ccc[i] != null)
            ccc[i].adicionar(da);
    }

    Object eliminar(int i) {
        if (ccc[i] != null)
            return ccc[i].eliminar();
        return null;
    }

    void mostrar(int i) {
        if (ccc[i] != null) {
            System.out.println("\nCola " + i + ":");
            ccc[i].mostrar();
        }
    }

    int nElem(int i) {
        if (ccc[i] != null)
            return ccc[i].nElem();
        return 0;
    }
}

public class EjercicioInsercionColaCircular {

    public static void insertarOrdenadoDescendente(CSCircular cola, int dato) {
        int n = cola.nElem();
        CSCircular aux = new CSCircular(cola.max);
        boolean insertado = false;

        for (int i = 0; i < n; i++) {
            int actual = (int) cola.eliminar();
            if (!insertado && dato >= actual) {
                aux.adicionar(dato);
                insertado = true;
            }
            aux.adicionar(actual);
        }

        if (!insertado) {
            aux.adicionar(dato);
        }

        while (!aux.esVacia()) {
            cola.adicionar(aux.eliminar());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Cantidad de colas circulares a crear: ");
        int n = sc.nextInt();
        CCCircular conjunto = new CCCircular(n); // crea arreglo de tamaño n + 1

        for (int i = 1; i <= n; i++) {
            System.out.print("Cantidad máxima de elementos para cola circular " + i + ": ");
            int max = sc.nextInt();
            conjunto.creaCSCircular(i, max);

            System.out.println("Ingresa elementos enteros descendentes para cola " + i + " (termina con número negativo):");
            while (true) {
                int val = sc.nextInt();
                if (val < 0) break;
                if (conjunto.esLlena(i)) {
                    System.out.println("Cola llena");
                    break;
                }
                conjunto.adicionar(val, i);
            }
        }

        System.out.println("\nColas originales:");
        for (int i = 1; i <= n; i++) {
            conjunto.mostrar(i);
        }

        System.out.print("\nIngresa el índice de la cola donde quieres insertar un nuevo dato: ");
        int idx = sc.nextInt();
        if (idx < 1 || idx > n) {
            System.out.println("Índice inválido.");
            return;
        }

        System.out.print("Ingresa el dato a insertar: ");
        int dato = sc.nextInt();

        insertarOrdenadoDescendente(conjunto.ccc[idx], dato);

        System.out.println("\nColas después de insertar dato:");
        for (int i = 1; i <= n; i++) {
            conjunto.mostrar(i);
        }

        sc.close();
    }
}
