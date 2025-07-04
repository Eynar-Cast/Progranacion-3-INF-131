import java.util.Scanner;
public class M_ej_3 {
    static class Elemento {
        int numero;
        Elemento enlace;

        Elemento(int numero) {
            this.numero = numero;
            this.enlace = null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Elemento inicio = null, fin = null;

        System.out.print("Cantidad de elementos iniciales (orden ascendente): ");
        int cantidadInicial = sc.nextInt();

        System.out.println("Ingrese los " + cantidadInicial + " valores ordenados:");
        for (int i = 0; i < cantidadInicial; i++) {
            int num = sc.nextInt();
            Elemento nuevo = new Elemento(num);

            if (inicio == null) {
                inicio = nuevo;
                fin = nuevo;
            } else {
                fin.enlace = nuevo;
                fin = nuevo;
            }
        }

        // Mostrar lista original
        System.out.print("Lista original: ");
        Elemento recorrer = inicio;
        while (recorrer != null) {
            System.out.print(recorrer.numero);
            if (recorrer.enlace != null) System.out.print(" -> ");
            recorrer = recorrer.enlace;
        }
        System.out.println();

        // Leer K e insertar K nuevos elementos manteniendo el orden
        System.out.print("Ingrese cuántos elementos desea insertar (k): ");
        int k = sc.nextInt();

        System.out.println("Ingrese los " + k + " nuevos valores:");
        for (int i = 0; i < k; i++) {
            int valorNuevo = sc.nextInt();
            Elemento nuevoNodo = new Elemento(valorNuevo);

            if (inicio == null || valorNuevo < inicio.numero) {
                nuevoNodo.enlace = inicio;
                inicio = nuevoNodo;
            } else {
                Elemento actual = inicio;
                Elemento anterior = null;
                while (actual != null && actual.numero <= valorNuevo) {
                    anterior = actual;
                    actual = actual.enlace;
                }
                nuevoNodo.enlace = actual;
                anterior.enlace = nuevoNodo;
            }
        }

        // Mostrar lista resultante
        System.out.print("Lista después de insertar: ");
        recorrer = inicio;
        while (recorrer != null) {
            System.out.print(recorrer.numero);
            if (recorrer.enlace != null) System.out.print(" -> ");
            recorrer = recorrer.enlace;
        }
        System.out.println();

        sc.close();
    }
}

