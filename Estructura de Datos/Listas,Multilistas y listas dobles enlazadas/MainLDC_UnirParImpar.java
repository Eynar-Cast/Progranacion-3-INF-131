import java.util.Scanner;
public class MainLDC_UnirParImpar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LDCircular lista1 = new LDCircular();
        LDCircular lista2 = new LDCircular();
        LDCircular resultado = new LDCircular();
        System.out.print("Tamaño de la primera lista: ");
        int n1 = sc.nextInt();
        System.out.println("Ingrese " + n1 + " valores:");
        for (int i = 0; i < n1; i++) {
            int x = sc.nextInt();
            lista1.adiFin(x);
        }
        System.out.print("Tamaño de la segunda lista: ");
        int n2 = sc.nextInt();
        System.out.println("Ingrese " + n2 + " valores:");
        for (int i = 0; i < n2; i++) {
            int x = sc.nextInt();
            lista2.adiFin(x);
        }
        int total = lista1.nElem() + lista2.nElem();
        int[] datos = new int[total];
        int idx = 0;
        // Copiar elementos de lista1
        if (!lista1.esVacia()) {
            NodoD aux = lista1.getCabecera();
            do {
                datos[idx++] = (int) aux.getDato();
                aux = aux.getSig();
            } while (aux != lista1.getCabecera());
        }
        // Copiar elementos de lista2
        if (!lista2.esVacia()) {
            NodoD aux = lista2.getCabecera();
            do {
                datos[idx++] = (int) aux.getDato();
                aux = aux.getSig();
            } while (aux != lista2.getCabecera());
        }
        // Separar en pares e impares
        int[] pares = new int[total];
        int[] impares = new int[total];
        int cp = 0, ci = 0;
        for (int i = 0; i < total; i++) {
            if (datos[i] % 2 == 0)
                pares[cp++] = datos[i];
            else
                impares[ci++] = datos[i];
        }
        // Ordenar pares ascendente (burbuja)
        for (int i = 0; i < cp - 1; i++) {
            for (int j = 0; j < cp - i - 1; j++) {
                if (pares[j] > pares[j + 1]) {
                    int t = pares[j];
                    pares[j] = pares[j + 1];
                    pares[j + 1] = t;
                }
            }
        }
        // Ordenar impares descendente (burbuja)
        for (int i = 0; i < ci - 1; i++) {
            for (int j = 0; j < ci - i - 1; j++) {
                if (impares[j] < impares[j + 1]) {
                    int t = impares[j];
                    impares[j] = impares[j + 1];
                    impares[j + 1] = t;
                }
            }
        }
        // Insertar pares en resultado
        for (int i = 0; i < cp; i++) {
            resultado.adiFin(pares[i]);
        }
        // Insertar impares en resultado
        for (int i = 0; i < ci; i++) {
            resultado.adiFin(impares[i]);
        }
        System.out.println("Lista resultante:");
        resultado.mostrar();
        sc.close();
    }
}

//3.Dadas dos listas circulares con datos numéricos unir en una tercera ambas listas de modo que al principio queden los pares ordenados ascendentemente y al final aparezcan los impares ordenados descendentemente.
//Descripción breve Solución
//El programa une dos listas circulares en una tercera, ordenando los pares ascendentemente y los impares descendentemente. Los elementos se combinan en un arreglo, se separan en pares e impares, se ordenan con burbuja y se insertan en una nueva lista circular
//