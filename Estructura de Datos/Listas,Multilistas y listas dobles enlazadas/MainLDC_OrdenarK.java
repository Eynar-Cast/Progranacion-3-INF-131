import java.util.Scanner;
public class MainLDC_OrdenarK {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LDCircular lista = new LDCircular();
        System.out.print("Ingrese la cantidad de elementos: ");
        int n = sc.nextInt();
        System.out.println("Ingrese los valores enteros:");
        for (int i = 0; i < n; i++) {
            int valor = sc.nextInt();
            lista.adiFin(valor); // Usamos tu método personalizado
        }
        System.out.println("Lista original:");
        lista.mostrar();
        System.out.print("\nIngrese el valor de k: ");
        int k = sc.nextInt();
        if (k <= 0 || lista.esVacia()) {
            System.out.println("Lista vacía o k inválido.");
            return;
        }
        int elementos = lista.nElem();
        if (k > elementos) k = elementos;
        // Ordenar los primeros k nodos usando burbuja descendente (solo intercambiando datos)
        for (int i = 0; i < k - 1; i++) {
            NodoD actual = lista.getCabecera();
            for (int j = 0; j < k - i - 1; j++) {
                NodoD siguiente = actual.getSig();
                int val1 = (int) actual.getDato();
                int val2 = (int) siguiente.getDato();

                if (val1 < val2) {
                    // Intercambiar los datos (NO los nodos)
                    actual.setDato(val2);
                    siguiente.setDato(val1);
                }
                actual = siguiente;
            }
        }
        System.out.println("\nLista después de ordenar los primeros " + k + " elementos en orden descendente:");
        lista.mostrar();
    }
}
//.Dada una lista doble circular ordenar de manera descendente los primeros k nodos. Descripción breve Solución La solución lee una lista doblemente circular y k, ordena los k primeros nodos en orden descendente intercambiando datos (usando burbuja) sin crear nuevos nodos, y mantiene la circularidad. Imprime la lista original y la resultante.