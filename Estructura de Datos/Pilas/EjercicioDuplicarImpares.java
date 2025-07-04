package ejercicios;

import java.util.Scanner;

public class EjercicioDuplicarImpares {

    static class Pila {
        private int tope, max;
        private Object[] v;

        Pila(int ca) {
            max = ca;
            tope = 0;
            v = new Object[max + 1];
        }

        boolean esLlena() {
            return tope == max;
        }

        boolean esVacia() {
            return tope == 0;
        }

        void adicionar(Object item) {
            if (!esLlena()) {
                tope++;
                v[tope] = item;
            } else {
                System.out.println("pila llena");
            }
        }

        Object eliminar() {
            Object item = null;
            if (!esVacia()) {
                item = v[tope];
                tope--;
            }
            return item;
        }

        void mostrar() {
            Object item;
            Pila aux = new Pila(max);
            while (!esVacia()) {
                item = eliminar();
                System.out.println(item);
                aux.adicionar(item);
            }
            while (!aux.esVacia()) {
                adicionar(aux.eliminar());
            }
        }

        int nElem() {
            return tope;
        }

        int getMax() {
            return max;
        }
    }

    public static void duplicarImparesRepetidos(Pila z) {
        Pila aux = new Pila(z.getMax());
        int[] conteo = new int[101]; // rango 0-100

        // Contar ocurrencias de impares
        while (!z.esVacia()) {
            int val = (int) z.eliminar();
            if (val >= 0 && val < 101 && val % 2 != 0) {
                conteo[val]++;
            }
            aux.adicionar(val);
        }

        // Restaurar pila original
        while (!aux.esVacia()) {
            z.adicionar(aux.eliminar());
        }

        // Duplicar impares que se repiten
        while (!z.esVacia()) {
            int val = (int) z.eliminar();
            aux.adicionar(val);
            if (val >= 0 && val < 101 && val % 2 != 0 && conteo[val] > 1) {
                aux.adicionar(val);  // duplicar el impar repetido
            }
        }

        // Pasar de nuevo a la pila original
        while (!aux.esVacia()) {
            z.adicionar(aux.eliminar());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad máxima de elementos en la pila: ");
        int max = sc.nextInt();

        Pila z = new Pila(max);

        System.out.println("Ingresa los elementos enteros (0-100). Ingresa un número fuera de rango para terminar.");

        while (true) {
            if (z.esLlena()) {
                System.out.println("Pila llena, no se pueden agregar más elementos.");
                break;
            }
            System.out.print("Elemento: ");
            int val = sc.nextInt();
            if (val < 0 || val > 100) {
                break;
            }
            z.adicionar(val);
        }

        System.out.println("\nPila original:");
        z.mostrar();

        duplicarImparesRepetidos(z);

        System.out.println("\nPila después de duplicar impares repetidos:");
        z.mostrar();

        sc.close();
    }
}
