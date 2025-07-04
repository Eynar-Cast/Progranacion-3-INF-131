import java.util.Scanner;
public class ejer4 {
    static class Empleado {
        String nombre;
        Empleado enlace;
        Empleado(String nombre) {
            this.nombre = nombre;
            this.enlace = null;
        }    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empleado inicio = null, fin = null;
        System.out.print("Ingrese la cantidad de empleados: ");
        int total = sc.nextInt();
        sc.nextLine();  // limpiar buffer
        System.out.println("Ingrese los nombres:");
        for (int i = 0; i < total; i++) {
            String nombreIngresado = sc.nextLine();
            Empleado nuevo = new Empleado(nombreIngresado);
            if (inicio == null) {
                inicio = nuevo;
                fin = nuevo;
            } else {
                fin.enlace = nuevo;
                fin = nuevo;
            }
        }
        if (fin != null) {
            fin.enlace = inicio;  
        }
        System.out.print("Lista original: ");
        Empleado cursor = inicio;
        for (int i = 0; i < total; i++) {
            System.out.print(cursor.nombre);
            if (i < total - 1) System.out.print(" -> ");
            cursor = cursor.enlace;
        }
        System.out.println();
        System.out.println("Nombres que terminan en vocal:");
        cursor = inicio;
        for (int i = 0; i < total; i++) {
            char ult = Character.toLowerCase(cursor.nombre.charAt(cursor.nombre.length() - 1));
            if ("aeiou".indexOf(ult) != -1) {
                System.out.println(cursor.nombre);
            }
            cursor = cursor.enlace;
        }
        Empleado actual = inicio;
        Empleado previo = fin;
        int n = total;
        int i = 0;
        while (i < n) {
            char ult = Character.toLowerCase(actual.nombre.charAt(actual.nombre.length() - 1));
            Empleado siguiente = actual.enlace;
            if ("aeiou".indexOf(ult) != -1) {
                if (actual == inicio) {
                    inicio = siguiente;
                    fin.enlace = inicio;
                }
                if (actual == fin) {
                    fin = previo;
                    fin.enlace = inicio;
                }
                previo.enlace = siguiente;
                total--;
            } else {
                previo = actual;
            }
            actual = siguiente;
            i++;
        }
        System.out.print("Lista después de eliminar: ");
        if (total == 0) {
            System.out.println("Vacía");
        } else {
            Empleado mostrar = inicio;
            for (int j = 0; j < total; j++) {
                System.out.print(mostrar.nombre);
                if (j < total - 1) System.out.print(" -> ");
                mostrar = mostrar.enlace;
            }
            System.out.println();
        }
        sc.close();
    }
}


//4 Dada una lista simple circular con nombres de empleados se pide mostrar aquellos empleados cuyos nombres terminan con vocal, luego eliminarlos. Nota. No crear nuevos nodos adicionales.
//Descripción breve Solución
//La solución lee una lista circular de nombres de empleados y muestra aquellos cuyos nombres terminan en vocal (a, e, i, o, u). Luego, elimina esos nodos ajustando punteros para mantener la circularidad, sin crear nuevos nodos. Imprime la lista original, los nombres seleccionados, y la lista resultante (o "Vacia" si queda sin nodos).
