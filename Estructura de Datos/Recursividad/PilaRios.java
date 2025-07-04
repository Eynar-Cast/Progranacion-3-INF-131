import java.util.Scanner;

public class PilaRios {

    static class Rio {
        String nombre;
        String municipio;
        double alturaActual;
        double alturaNormal;

        Rio(String nombre, String municipio, double alturaActual, double alturaNormal) {
            this.nombre = nombre;
            this.municipio = municipio;
            this.alturaActual = alturaActual;
            this.alturaNormal = alturaNormal;
        }
    }

    static class Pila {
        Rio[] datos;
        int tope;
        int max;

        Pila(int max) {
            this.max = max;
            datos = new Rio[max];
            tope = -1;
        }

        boolean estaVacia() {
            return tope == -1;
        }

        boolean estaLlena() {
            return tope == max - 1;
        }

        void push(Rio r) {
            if (!estaLlena()) {
                datos[++tope] = r;
            }
        }

        Rio pop() {
            if (!estaVacia()) {
                return datos[tope--];
            }
            return null;
        }

        Rio peek() {
            if (!estaVacia()) {
                return datos[tope];
            }
            return null;
        }

        int tamaño() {
            return tope + 1;
        }
    }

    // a) Verificar si "Palca" tiene algún río
    public static boolean existePalca(Pila pila) {
        boolean encontrado = false;
        Pila aux = new Pila(pila.max);

        while (!pila.estaVacia()) {
            Rio r = pila.pop();
            if (r.municipio.equalsIgnoreCase("Palca")) {
                encontrado = true;
            }
            aux.push(r);
        }

        while (!aux.estaVacia()) {
            pila.push(aux.pop());
        }
        return encontrado;
    }

    // b) Verificar si hay río que atraviesa más de un municipio (nombre con coma)
    public static boolean existeMultiplesMunicipios(Pila pila) {
        boolean encontrado = false;
        Pila aux = new Pila(pila.max);

        while (!pila.estaVacia()) {
            Rio r = pila.pop();
            if (r.municipio.contains(",")) {
                encontrado = true;
            }
            aux.push(r);
        }

        while (!aux.estaVacia()) {
            pila.push(aux.pop());
        }
        return encontrado;
    }

    // c) Listar ríos desbordados (alturaActual > alturaNormal)
    public static void listarDesbordados(Pila pila) {
        Pila aux = new Pila(pila.max);
        System.out.println("Ríos desbordados:");
        boolean alguno = false;

        while (!pila.estaVacia()) {
            Rio r = pila.pop();
            if (r.alturaActual > r.alturaNormal) {
                System.out.println("- " + r.nombre + " en " + r.municipio);
                alguno = true;
            }
            aux.push(r);
        }
        if (!alguno) {
            System.out.println("Ninguno");
        }

        while (!aux.estaVacia()) {
            pila.push(aux.pop());
        }
    }

    // d) Río con mayor diferencia (alturaActual - alturaNormal)
    public static void rioMayorDiferencia(Pila pila) {
        Pila aux = new Pila(pila.max);
        Rio mayor = null;
        double diffMax = Double.NEGATIVE_INFINITY;

        while (!pila.estaVacia()) {
            Rio r = pila.pop();
            double diff = r.alturaActual - r.alturaNormal;
            if (diff > diffMax) {
                diffMax = diff;
                mayor = r;
            }
            aux.push(r);
        }

        while (!aux.estaVacia()) {
            pila.push(aux.pop());
        }

        if (mayor != null) {
            System.out.println("Río con mayor diferencia:");
            System.out.println(mayor.nombre + " en " + mayor.municipio + " diferencia: " + diffMax);
        } else {
            System.out.println("No hay ríos registrados.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad de ríos: ");
        int n = sc.nextInt();
        sc.nextLine();

        Pila pila = new Pila(n);

        for (int i = 0; i < n; i++) {
            System.out.println("Río #" + (i + 1));
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Municipio(s) (separar con coma si hay varios): ");
            String municipio = sc.nextLine();
            System.out.print("Altura actual (m): ");
            double alturaActual = sc.nextDouble();
            System.out.print("Altura normal (m): ");
            double alturaNormal = sc.nextDouble();
            sc.nextLine();

            pila.push(new Rio(nombre, municipio, alturaActual, alturaNormal));
        }

        System.out.println("\na) ¿Existe río en municipio Palca? " + (existePalca(pila) ? "Sí" : "No"));
        System.out.println("b) ¿Existe río que atraviesa más de un municipio? " + (existeMultiplesMunicipios(pila) ? "Sí" : "No"));
        System.out.println("c) Lista de ríos desbordados:");
        listarDesbordados(pila);
        System.out.println("d) Río con mayor diferencia de altura:");
        rioMayorDiferencia(pila);

        sc.close();
    }
}
