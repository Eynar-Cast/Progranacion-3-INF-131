//Dada una lista simple circular se pide ordenar los k primeros elementos 
//Sin restricción 
//No crear nuevos nodos adicionales 

public class ejer2 {
    static class Nodo {         int dato; 
        Nodo siguiente; 
         
        Nodo(int dato) {             this.dato = dato;             this.siguiente = null; 
        } 
    } 
     
    public static void main(String[] args) { 
        Scanner entrada = new Scanner(System.in); 
         
        Nodo cabeza = null, cola = null; 
         
        System.out.println("Ingrese el numero de elementos de la lista circular:");         int tamano = entrada.nextInt(); 
        System.out.println("Ingrese los elementos de la lista circular:");         for (int i = 0; i < tamano; i++) {             int valor = entrada.nextInt(); 
            Nodo nuevo = new Nodo(valor);             if (cabeza == null) {                 cabeza = nuevo;                 cola = nuevo;             } else {                 cola.siguiente = nuevo;                 cola = nuevo; 
            } 
        } 
        cola.siguiente = cabeza; 
         
        System.out.println("Ingrese el valor de k:");         int k = entrada.nextInt(); 
         
        System.out.println("Lista original:");         Nodo actual = cabeza;         for (int i = 0; i < tamano; i++) {             System.out.print(actual.dato);             if (i < tamano - 1) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        if (k > tamano) k = tamano;         if (k <= 0 || cabeza == null) { 
            System.out.println("Lista despues de ordenar:");             actual = cabeza;             for (int i = 0; i < tamano; i++) {                 System.out.print(actual.dato);                 if (i < tamano - 1) { 
                    System.out.print(" -> "); 
                }                 actual = actual.siguiente; 
            } 
            System.out.println();             entrada.close();             return; 
        } 
         
        Nodo nuevaCabeza = null, nuevaCola = null;         actual = cabeza;         for (int i = 0; i < k; i++) { 
            Nodo nuevo = new Nodo(actual.dato);             if (nuevaCabeza == null) {                 nuevaCabeza = nuevo;                 nuevaCola = nuevo;             } else {                 nuevaCola.siguiente = nuevo;                 nuevaCola = nuevo; 
            } 
            actual = actual.siguiente; 
        } 
         
        Nodo temp = nuevaCabeza; 
        for (int i = 0; i < k - 1; i++) {             Nodo siguiente = temp.siguiente;             while (siguiente != null) {                 if (temp.dato > siguiente.dato) {                     int aux = temp.dato;                     temp.dato = siguiente.dato;                     siguiente.dato = aux; 
                } 
                siguiente = siguiente.siguiente; 
            } 
            temp = temp.siguiente; 
        } 
         
        if (k == tamano) {             cabeza = nuevaCabeza;             nuevaCola.siguiente = cabeza; 
        } else {             actual = cabeza;             for (int i = 0; i < k - 1; i++) {                 actual = actual.siguiente; 
            } 
            Nodo resto = actual.siguiente;             actual.siguiente = nuevaCabeza;             nuevaCola.siguiente = resto;             cabeza = nuevaCabeza; 
        } 
         
        System.out.println("Lista despues de ordenar:");         actual = cabeza;         for (int i = 0; i < tamano; i++) {             System.out.print(actual.dato);             if (i < tamano - 1) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        entrada.close(); 
    } 
} 
//Sin crear nuevos nodos import java.util.Scanner; 
 
public class Main {     static class Nodo {         int dato; 
        Nodo siguiente; 
         
        Nodo(int dato) {             this.dato = dato;             this.siguiente = null; 
        } 
    } 
     
    public static void main(String[] args) { 
        Scanner entrada = new Scanner(System.in); 
         
        Nodo cabeza = null, cola = null; 
         
        System.out.println("Ingrese el numero de elementos de la lista circular:");         int tamano = entrada.nextInt(); 
        System.out.println("Ingrese los elementos de la lista circular:");         for (int i = 0; i < tamano; i++) {             int valor = entrada.nextInt(); 
            Nodo nuevo = new Nodo(valor);             if (cabeza == null) {                 cabeza = nuevo;                 cola = nuevo;             } else {                 cola.siguiente = nuevo;                 cola = nuevo; 
            } 
        } 
        cola.siguiente = cabeza; 
         
        System.out.println("Ingrese el valor de k:");         int k = entrada.nextInt(); 
         
        System.out.println("Lista original:");         Nodo actual = cabeza; 
for (int i = 0; i < tamano; i++) {             System.out.print(actual.dato);             if (i < tamano - 1) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        if (k > tamano) k = tamano;         if (k <= 0 || cabeza == null) { 
            System.out.println("Lista despues de ordenar:");             actual = cabeza; 
            for (int i = 0; i < tamano; i++) {                 System.out.print(actual.dato);                 if (i < tamano - 1) { 
                    System.out.print(" -> "); 
                }                 actual = actual.siguiente; 
            } 
            System.out.println();             entrada.close();             return; 
        } 
         
        for (int i = 0; i < k - 1; i++) {             actual = cabeza; 
            for (int j = 0; j < k - i - 1; j++) {                 Nodo siguiente = actual.siguiente;                 if (actual.dato > siguiente.dato) {                     int aux = actual.dato;                     actual.dato = siguiente.dato;                     siguiente.dato = aux; 
                }                 actual = actual.siguiente; 
            } 
        } 
         
        System.out.println("Lista despues de ordenar:");         actual = cabeza; 
        for (int i = 0; i < tamano; i++) {             System.out.print(actual.dato);             if (i < tamano - 1) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        entrada.close(); 
    } 

}
