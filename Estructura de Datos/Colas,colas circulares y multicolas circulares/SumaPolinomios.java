package colas;

import java.util.Scanner;

public class SumaPolinomios {

    // Clase interna para representar un término del polinomio
    static class Termino {
        private int coef;
        private int exp;

        public Termino(int coef, int exp) {
            this.coef = coef;
            this.exp = exp;
        }

        public int getCoef() {
            return coef;
        }

        public int getExp() {
            return exp;
        }

        public void setCoef(int coef) {
            this.coef = coef;
        }

        public String toString() {
            return coef + "x^" + exp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad máxima de términos en cada polinomio: ");
        int max = sc.nextInt();

        CSCircular A = new CSCircular(max + 1);
        CSCircular B = new CSCircular(max + 1);
        CSCircular C = new CSCircular((max * 2) + 1); // Resultado

        System.out.println("\n--- Ingresar términos del Polinomio A (coef y exp) ---");
        ingresarPolinomio(A, sc);

        System.out.println("\n--- Ingresar términos del Polinomio B (coef y exp) ---");
        ingresarPolinomio(B, sc);

        System.out.println("\nPolinomio A:");
        A.mostrar();

        System.out.println("\nPolinomio B:");
        B.mostrar();

        // Sumar los polinomios A y B
        while (!A.esVacia() && !B.esVacia()) {
            Termino ta = (Termino) A.eliminar();
            Termino tb = (Termino) B.eliminar();

            if (ta.getExp() == tb.getExp()) {
                int sumaCoef = ta.getCoef() + tb.getCoef();
                if (sumaCoef != 0) {
                    C.adicionar(new Termino(sumaCoef, ta.getExp()));
                }
            } else if (ta.getExp() > tb.getExp()) {
                C.adicionar(ta);
                B.adicionar(tb); // devolver tb
            } else {
                C.adicionar(tb);
                A.adicionar(ta); // devolver ta
            }
        }

        // Si quedan elementos en A o B
        while (!A.esVacia()) {
            C.adicionar(A.eliminar());
        }
        while (!B.esVacia()) {
            C.adicionar(B.eliminar());
        }

        System.out.println("\n--- Polinomio resultado (A + B): ---");
        C.mostrar();

        sc.close();
    }

    static void ingresarPolinomio(CSCircular cola, Scanner sc) {
        while (!cola.esLlena()) {
            System.out.print("Coeficiente (0 para terminar): ");
            int coef = sc.nextInt();
            if (coef == 0) break;
            System.out.print("Exponente: ");
            int exp = sc.nextInt();
            cola.adicionar(new Termino(coef, exp));
        }
    }
}
