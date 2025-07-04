import java.util.Scanner;

public class ListaDobleCircular {

    static class Nodo {
        String numero;
        char operacion;
        Nodo siguiente;
        Nodo anterior;

        Nodo(String numero, char operacion) {
            this.numero = numero;
            this.operacion = operacion;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    static Nodo cabeza = null;

    // Inserta al final y mantiene la circularidad
    public static void insertar(String numero, char operacion) {
        Nodo nuevo = new Nodo(numero, operacion);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            Nodo ultimo = cabeza.anterior;
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
        }
    }

    // Convierte String a double con manejo básico
    public static double convertir(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0; // o lanzar error si prefieres
        }
    }

    // Función recursiva para evaluar la lista desde nodo actual
    // acumulado: resultado parcial
    // inicio: nodo donde se empezó para detectar ciclo
    // primero: indica si es la primera llamada para manejar acumulado inicial
    public static double evaluarRecursivo(Nodo actual, Nodo inicio, double acumulado, boolean primero) {
        if (actual == inicio && !primero) {
            return acumulado;
        }

        double num = convertir(actual.numero);

        if (primero) {
            acumulado = num; // Primer número inicia acumulado
            return evaluarRecursivo(actual.siguiente, inicio, acumulado, false);
        }

        switch (actual.operacion) {
            case '+': acumulado += num; break;
            case '-': acumulado -= num; break;
            case '*': acumulado *= num; break;
            case '/': acumulado /= num; break;
            default: break; // Operación inválida, ignorar o lanzar error
        }

        return evaluarRecursivo(actual.siguiente, inicio, acumulado, false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cantidad de nodos: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Número (String): ");
            String numero = scanner.nextLine();
            System.out.print("Operación (+, -, *, /): ");
            char op = scanner.nextLine().charAt(0);
            insertar(numero, op);
        }

        if (cabeza == null) {
            System.out.println("La lista está vacía.");
        } else {
            double resultado = evaluarRecursivo(cabeza, cabeza, 0, true);
            System.out.println("Resultado final: " + resultado);
        }

        scanner.close();
    }
}
