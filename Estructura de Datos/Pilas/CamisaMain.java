package ejercicios;

import java.util.Scanner;

public class CamisaMain {

    // Clase interna Camisa
    static class Camisa {
        private int codigo;
        private String marca;
        private String tamanio;
        private String color;
        private String industria;
        private float precio;

        public Camisa(int codigo, String marca, String tamanio, String color, String industria, float precio) {
            this.codigo = codigo;
            this.marca = marca;
            this.tamanio = tamanio;
            this.color = color;
            this.industria = industria;
            this.precio = precio;
        }

        public int getCodigo() { return codigo; }
        public String getMarca() { return marca; }
        public String getTamanio() { return tamanio; }
        public String getColor() { return color; }
        public String getIndustria() { return industria; }
        public float getPrecio() { return precio; }

        @Override
        public String toString() {
            return "Camisa{" +
                    "codigo=" + codigo +
                    ", marca='" + marca + '\'' +
                    ", tamanio='" + tamanio + '\'' +
                    ", color='" + color + '\'' +
                    ", industria='" + industria + '\'' +
                    ", precio=" + precio +
                    '}';
        }
    }

    // Clase interna Pila
    static class Pila {
        private int tope, max;
        private Object[] v;

        public Pila(int ca) {
            max = ca;
            tope = 0;
            v = new Object[max + 1];
        }

        public boolean esLlena() {
            return tope == max;
        }

        public boolean esVacia() {
            return tope == 0;
        }

        public void adicionar(Object item) {
            if (!esLlena()) {
                tope++;
                v[tope] = item;
            } else {
                System.out.println("Pila llena");
            }
        }

        public Object eliminar() {
            Object item = null;
            if (!esVacia()) {
                item = v[tope];
                tope--;
            }
            return item;
        }

        public void mostrar() {
            Object item;
            Pila aux = new Pila(max);
            while (!esVacia()) {
                item = eliminar();
                System.out.println(item);
                aux.adicionar(item);
            }
            while (!aux.esVacia()) {
                adicionar(aux.eliminar());
            }
        }

        public int nElem() {
            return tope;
        }

        public int getMax() {
            return max;
        }
    }

    // Métodos para resolver los ejercicios

    // a) Contar camisas color k y marca q
    public static int contarCamisas(Pila pila, String color, String marca) {
        int count = 0;
        Pila aux = new Pila(pila.getMax());

        while (!pila.esVacia()) {
            Camisa c = (Camisa) pila.eliminar();
            if (c.getColor().equalsIgnoreCase(color) && c.getMarca().equalsIgnoreCase(marca)) {
                count++;
            }
            aux.adicionar(c);
        }
        while (!aux.esVacia()) {
            pila.adicionar(aux.eliminar());
        }
        return count;
    }

    // b) Vender (eliminar) la primera camisa de color z
    public static boolean venderPrimeraCamisaColor(Pila pila, String color) {
        boolean vendido = false;
        Pila aux = new Pila(pila.getMax());

        while (!pila.esVacia()) {
            Camisa c = (Camisa) pila.eliminar();
            if (!vendido && c.getColor().equalsIgnoreCase(color)) {
                vendido = true;
                System.out.println("Camisa vendida: " + c);
            } else {
                aux.adicionar(c);
            }
        }
        while (!aux.esVacia()) {
            pila.adicionar(aux.eliminar());
        }
        if (!vendido) System.out.println("No se encontró camisa del color " + color);
        return vendido;
    }

    // c) Inversión total de camisas color k
    public static float inversionCamisasColor(Pila pila, String color) {
        float suma = 0;
        Pila aux = new Pila(pila.getMax());

        while (!pila.esVacia()) {
            Camisa c = (Camisa) pila.eliminar();
            if (c.getColor().equalsIgnoreCase(color)) {
                suma += c.getPrecio();
            }
            aux.adicionar(c);
        }
        while (!aux.esVacia()) {
            pila.adicionar(aux.eliminar());
        }
        return suma;
    }

    // d) Camisas con precio más alto + mostrar cuántas + industria y tamaño
    public static void camisasPrecioMasAlto(Pila pila) {
        float maxPrecio = -1;
        int cantidad = 0;
        String industria = "";
        String tamanio = "";
        Pila aux = new Pila(pila.getMax());

        // Primero: obtener precio máximo
        while (!pila.esVacia()) {
            Camisa c = (Camisa) pila.eliminar();
            if (c.getPrecio() > maxPrecio) {
                maxPrecio = c.getPrecio();
            }
            aux.adicionar(c);
        }
        while (!aux.esVacia()) {
            pila.adicionar(aux.eliminar());
        }

        // Segundo: contar camisas con ese precio máximo
        aux = new Pila(pila.getMax());
        while (!pila.esVacia()) {
            Camisa c = (Camisa) pila.eliminar();
            if (c.getPrecio() == maxPrecio) {
                cantidad++;
                industria = c.getIndustria();
                tamanio = c.getTamanio();
            }
            aux.adicionar(c);
        }
        while (!aux.esVacia()) {
            pila.adicionar(aux.eliminar());
        }

        System.out.println("Precio más alto: " + maxPrecio);
        System.out.println("Cantidad de camisas con precio más alto: " + cantidad);
        System.out.println("Industria de estas camisas: " + industria);
        System.out.println("Tamaño de estas camisas: " + tamanio);
    }

    // Main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Cantidad máxima de camisas en la pila: ");
        int max = sc.nextInt();
        sc.nextLine();

        Pila pilaCamisas = new Pila(max);

        System.out.println("Ingrese camisas (codigo, marca, tamaño, color, industria, precio).");
        System.out.println("Para terminar ingrese código <=0");

        while (true) {
            System.out.print("Código: ");
            int codigo = sc.nextInt();
            if (codigo <= 0) break;
            sc.nextLine();

            System.out.print("Marca: ");
            String marca = sc.nextLine();

            System.out.print("Tamaño: ");
            String tamanio = sc.nextLine();

            System.out.print("Color: ");
            String color = sc.nextLine();

            System.out.print("Industria: ");
            String industria = sc.nextLine();

            System.out.print("Precio: ");
            float precio = sc.nextFloat();
            sc.nextLine();

            Camisa c = new Camisa(codigo, marca, tamanio, color, industria, precio);
            pilaCamisas.adicionar(c);

            if (pilaCamisas.esLlena()) {
                System.out.println("Pila llena, no se pueden agregar más camisas.");
                break;
            }
        }

        System.out.println("\nPila completa de camisas:");
        pilaCamisas.mostrar();

        // a)
        System.out.print("\nIngrese color para contar camisas: ");
        String colorQ = sc.nextLine();

        System.out.print("Ingrese marca para contar camisas: ");
        String marcaQ = sc.nextLine();

        int total = contarCamisas(pilaCamisas, colorQ, marcaQ);
        System.out.println("Cantidad de camisas de color '" + colorQ + "' y marca '" + marcaQ + "': " + total);

        // b)
        System.out.print("\nIngrese color para vender la primera camisa de ese color: ");
        String colorVenta = sc.nextLine();
        venderPrimeraCamisaColor(pilaCamisas, colorVenta);

        System.out.println("\nPila después de la venta:");
        pilaCamisas.mostrar();

        // c)
        System.out.print("\nIngrese color para calcular inversión total: ");
        String colorInv = sc.nextLine();
        float inversion = inversionCamisasColor(pilaCamisas, colorInv);
        System.out.println("Inversión total en camisas de color '" + colorInv + "': " + inversion);

        // d)
        System.out.println("\nCamisas con precio más alto:");
        camisasPrecioMasAlto(pilaCamisas);

        sc.close();
    }
}
