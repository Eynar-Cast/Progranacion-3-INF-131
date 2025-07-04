//1.	Dada una lista doble normal que contiene caracteres trasladar al comienzo las vocales y luego deben aparecer las consonantes. Nota no crear nuevos nodos.
import java.util.Scanner;
public class MainLDD_VocalesAlInicio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LDNormal lista = new LDNormal();
        System.out.print("Ingrese la cantidad de caracteres: ");
        int n = sc.nextInt();
        System.out.println("Ingrese los caracteres uno por uno:");
        for (int i = 0; i < n; i++) {
            char letra = sc.next().charAt(0);
            lista.adiFin(letra);
        }
        System.out.println("Lista original:");
        lista.mostrar();
        // Mover vocales al inicio, sin crear nodos, solo con punteros
        NodoD actual = lista.getCabecera();
        NodoD nuevoInicio = lista.getCabecera();
        while (actual != null) {
            NodoD siguiente = actual.getSig();
            char c = Character.toLowerCase((char) actual.getDato());
            if ("aeiou".indexOf(c) != -1 && actual != nuevoInicio) {
                // Desconectar nodo actual
                if (actual.getAnt() != null) {
                    actual.getAnt().setSig(actual.getSig());
                }
                if (actual.getSig() != null) {
                    actual.getSig().setAnt(actual.getAnt());
                }
                // Insertar antes del nuevoInicio
                actual.setAnt(null);
                actual.setSig(nuevoInicio);
                nuevoInicio.setAnt(actual);
                // Actualizar el nuevo inicio de la lista
                nuevoInicio = actual;
                lista.setCabecera(nuevoInicio);
            }
            actual = siguiente;
        }
        System.out.println("\nLista después de mover vocales al inicio:");
        lista.mostrar();
    }
}
