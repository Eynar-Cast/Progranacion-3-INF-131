import java.util.Scanner;

public class EnergiaAbeja {

    static class Nodo {
        int energia;
        Nodo siguiente;

        Nodo(int energia) {
            this.energia = energia;
            this.siguiente = null;
        }
    }

    static class Cola {
        Nodo frente, fin;

        Cola() {
            frente = fin = null;
        }

        void encolar(int energia) {
            Nodo nuevo = new Nodo(energia);
            if (fin == null) {
                frente = fin = nuevo;
            } else {
                fin.siguiente = nuevo;
                fin = nuevo;
            }
        }

        boolean estaVacia() {
            return frente == null;
        }
    }

    public static int calcularEnergia(Nodo nodo, boolean sumar) {
        if (nodo == null) return 0;
        if (sumar)
            return nodo.energia + calcularEnergia(nodo.siguiente, false);
        else
            return -nodo.energia + calcularEnergia(nodo.siguiente, true);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad de flores: ");
        int n = sc.nextInt();

        Cola cola = new Cola();

        System.out.println("Ingresa la energía de cada flor:");
        for (int i = 0; i < n; i++) {
            cola.encolar(sc.nextInt());
        }

        int resultado = calcularEnergia(cola.frente, true);
        System.out.println("Total de energía recolectada: " + resultado);

        sc.close();
    }
}
