//Enunciado 
//1. Dadas dos listas simples normales se pide insertar la lista más pequeña en la lista más grande. No crear nuevos nodos. 

import java.util.Scanner;
 
public class ejer1 {     static class Nodo {         int dato; 
        Nodo siguiente; 
         
        Nodo(int dato) {             this.dato = dato;             this.siguiente = null; 
        } 
    } 
     
    public static void main(String[] args) { 
        Scanner entrada = new Scanner(System.in); 
         
        Nodo cabeza1 = null, cola1 = null; 
        Nodo cabeza2 = null, cola2 = null; 
         
        System.out.println("Ingrese el numero de elementos de la primera lista:");         int tamano1 = entrada.nextInt(); 
        System.out.println("Ingrese los elementos de la primera lista:");         for (int i = 0; i < tamano1; i++) {             int valor = entrada.nextInt(); 
            Nodo nuevo = new Nodo(valor);             if (cabeza1 == null) {                 cabeza1 = nuevo;                 cola1 = nuevo; 
            } else {                 cola1.siguiente = nuevo;                 cola1 = nuevo; 
            } 
        } 
         
        System.out.println("Ingrese el numero de elementos de la segunda lista:");         int tamano2 = entrada.nextInt(); 
        System.out.println("Ingrese los elementos de la segunda lista:");         for (int i = 0; i < tamano2; i++) {             int valor = entrada.nextInt(); 
            Nodo nuevo = new Nodo(valor);             if (cabeza2 == null) {                 cabeza2 = nuevo;                 cola2 = nuevo;             } else {                 cola2.siguiente = nuevo;                 cola2 = nuevo; 
            } 
        } 
         
        System.out.println("Lista 1 original:");         Nodo actual = cabeza1;         while (actual != null) {             System.out.print(actual.dato);             if (actual.siguiente != null) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        System.out.println("Lista 2 original:");         actual = cabeza2;         while (actual != null) {             System.out.print(actual.dato);             if (actual.siguiente != null) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        int contador1 = 0;         actual = cabeza1;         while (actual != null) {             contador1++;             actual = actual.siguiente; 
        } 
         
        int contador2 = 0;         actual = cabeza2;         while (actual != null) {             contador2++;             actual = actual.siguiente; 
        } 
         
        if (contador1 >= contador2) {             if (cabeza2 != null) {                 actual = cabeza1;                 while (actual.siguiente != null) {                     actual = actual.siguiente; 
                } 
                actual.siguiente = cabeza2;                 cabeza2 = null; 
            } 
        } else {             if (cabeza1 != null) {                 actual = cabeza2;                 while (actual.siguiente != null) {                     actual = actual.siguiente; 
                } 
                actual.siguiente = cabeza1;                 cabeza1 = null; 
            } 
        } 
         
        System.out.println("Lista 1 despues de la insercion:");         actual = cabeza1;         while (actual != null) { 
            System.out.print(actual.dato);             if (actual.siguiente != null) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        System.out.println("Lista 2 despues de la insercion:");         actual = cabeza2;         while (actual != null) { 
            System.out.print(actual.dato);             if (actual.siguiente != null) { 
                System.out.print(" -> "); 
            } 
            actual = actual.siguiente; 
        } 
        System.out.println(); 
         
        entrada.close(); 
    } 
} 

