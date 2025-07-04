package colas;

import java.util.Scanner;

public class CajeroATM {

    static class ClienteATM {
        String carnet;
        String nombre;
        int edad;
        char sexo; // 'M' o 'F'
        String tipoOperacion; // R, D, E
        double monto;
        String moneda; // Bs o USD

        ClienteATM(String carnet, String nombre, int edad, char sexo, String tipoOperacion, double monto, String moneda) {
            this.carnet = carnet;
            this.nombre = nombre;
            this.edad = edad;
            this.sexo = sexo;
            this.tipoOperacion = tipoOperacion;
            this.monto = monto;
            this.moneda = moneda;
        }

        public String toString() {
            return nombre + " [" + carnet + "] - " + tipoOperacion + " " + monto + " " + moneda;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CSNormal cola = new CSNormal(50); // máximo 50 clientes

        System.out.println("REGISTRO DE CLIENTES EN EL CAJERO AUTOMÁTICO (ATM)");

        // Ingreso de clientes
        while (!cola.esLlena()) {
            System.out.print("\n¿Deseas registrar un cliente? (s/n): ");
            String resp = sc.next();
            if (!resp.equalsIgnoreCase("s")) break;

            System.out.print("Carnet: ");
            String carnet = sc.next();
            sc.nextLine(); // limpiar buffer

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Edad: ");
            int edad = sc.nextInt();

            System.out.print("Sexo (M/F): ");
            char sexo = sc.next().toUpperCase().charAt(0);

            System.out.print("Tipo de operación (R=Retiro, D=Depósito, E=Extracto): ");
            String tipoOperacion = sc.next().toUpperCase();

            double monto = 0;
            String moneda = "-";
            if (tipoOperacion.equals("R") || tipoOperacion.equals("D")) {
                System.out.print("Monto: ");
                monto = sc.nextDouble();

                System.out.print("Moneda (Bs/USD): ");
                moneda = sc.next().toUpperCase();
            }

            ClienteATM cliente = new ClienteATM(carnet, nombre, edad, sexo, tipoOperacion, monto, moneda);
            cola.adicionar(cliente);
        }

        // Variables para cálculos
        CSNormal aux = new CSNormal(50);
        double totalRetiros = 0;
        int contRetiros = 0;

        double mayorDeposito = 0;
        String clienteMayorDeposito = "";

        int mujeres = 0;
        int sumaEdadHombres = 0;
        int cantHombres = 0;

        // Para (d) operaciones por carnet
        System.out.print("\nCarnet para verificar operaciones: ");
        String carnetBuscado = sc.next();

        System.out.println("\n--- Procesando datos ---");

        while (!cola.esVacia()) {
            ClienteATM c = (ClienteATM) cola.eliminar();

            // a) Retiros
            if (c.tipoOperacion.equals("R")) {
                contRetiros++;
                totalRetiros += c.monto;
            }

            // b) Mayor depósito
            if (c.tipoOperacion.equals("D") && c.monto > mayorDeposito) {
                mayorDeposito = c.monto;
                clienteMayorDeposito = c.nombre + " (" + c.monto + " " + c.moneda + ")";
            }

            // c) Mujeres
            if (c.sexo == 'F') mujeres++;

            // e) Edad promedio hombres
            if (c.sexo == 'M') {
                sumaEdadHombres += c.edad;
                cantHombres++;
            }

            // d) Buscar por carnet
            if (c.carnet.equals(carnetBuscado)) {
                System.out.println("Operación de " + c.nombre + ": " + c.tipoOperacion + " " + c.monto + " " + c.moneda);
            }

            aux.adicionar(c);
        }

        // Restaurar cola original
        while (!aux.esVacia()) {
            cola.adicionar(aux.eliminar());
        }

        // Resultados
        System.out.println("\n(a) Total de clientes que retiraron: " + contRetiros);
        System.out.println("    Monto total retirado: " + totalRetiros);

        System.out.println("\n(b) Cliente que más dinero depositó: " + clienteMayorDeposito);

        System.out.println("\n(c) Total de clientas mujeres: " + mujeres);

        System.out.println("\n(e) Edad promedio de hombres: " +
                (cantHombres > 0 ? (sumaEdadHombres / (double) cantHombres) : "No hay hombres registrados"));

        sc.close();
    }
}
