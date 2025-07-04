import java.util.Scanner;

public class ListaSimple {

    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    static Nodo cabeza = null;

    // Inserta al final de la lista
    public static void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    // Imprime la lista
    public static void imprimir(Nodo nodo) {
        if (nodo == null) {
            System.out.println();
            return;
        }
        System.out.print(nodo.dato + " ");
        imprimir(nodo.siguiente);
    }

    // Función recursiva para invertir la lista
    public static Nodo invertir(Nodo nodo) {
        if (nodo == null || nodo.siguiente == null) {
            return nodo;
        }
        Nodo nuevoCabeza = invertir(nodo.siguiente);
        nodo.siguiente.siguiente = nodo;
        nodo.siguiente = null;
        return nuevoCabeza;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Cantidad de elementos en la lista: ");
        int n = scanner.nextInt();

        System.out.println("Ingresa los números:");
        for (int i = 0; i < n; i++) {
            insertar(scanner.nextInt());
        }

        System.out.println("Lista original:");
        imprimir(cabeza);

        cabeza = invertir(cabeza);

        System.out.println("Lista invertida:");
        imprimir(cabeza);

        scanner.close();
    }
}
