package colas;

public class CCCircular {
    private int ncc;              // número de colas creadas
    private CSCircular[] ccc;     // arreglo de colas circulares

    public CCCircular(int maxSucursales) {
        ccc = new CSCircular[maxSucursales];
        ncc = 0;
    }

    public int getNcc() {
        return ncc;
    }

    public CSCircular[] getCcc() {
        return ccc;
    }

    public void creaCSCircular(int i, int capacidad) {
        if (i >= 0 && i < ccc.length) {
            ccc[i] = new CSCircular(capacidad);
            if (i >= ncc) {
                ncc = i + 1;
            }
        } else {
            System.out.println("Índice fuera de rango para crear cola circular");
        }
    }

    private boolean validarIndice(int i) {
        if (i < 0 || i >= ncc) {
            System.out.println("Índice fuera de rango: " + i);
            return false;
        }
        if (ccc[i] == null) {
            System.out.println("Cola circular no inicializada en índice: " + i);
            return false;
        }
        return true;
    }

    public boolean esVacia(int i) {
        if (validarIndice(i)) {
            return ccc[i].esVacia();
        }
        return true; // Consideramos vacía si no existe o índice inválido
    }

    public boolean esLlena(int i) {
        if (validarIndice(i)) {
            return ccc[i].esLlena();
        }
        return false;
    }

    public void adicionar(Object dato, int i) {
        if (validarIndice(i)) {
            ccc[i].adicionar(dato);
        }
    }

    public Object eliminar(int i) {
        if (validarIndice(i)) {
            return ccc[i].eliminar();
        }
        return null;
    }

    public void mostrar(int i) {
        if (validarIndice(i)) {
            System.out.println("Cola " + (i + 1) + ":");
            ccc[i].mostrar();
        }
    }

    public void mostrar() {
        for (int i = 0; i < ncc; i++) {
            mostrar(i);
        }
    }

    public int nElem(int i) {
        if (validarIndice(i)) {
            return ccc[i].nElem();
        }
        return 0;
    }

    public int nElem() {
        int total = 0;
        for (int i = 0; i < ncc; i++) {
            total += nElem(i);
        }
        return total;
    }

    public void sucuMasAte() {
        int max = 0;
        for (int i = 0; i < ncc; i++) {
            max = Math.max(max, nElem(i));
        }

        for (int i = 0; i < ncc; i++) {
            if (nElem(i) == max) {
                System.out.println("Sucursal con más atención: " + (i + 1));
            }
        }
    }

    public void totalDepoRe(int i) {
        if (!validarIndice(i)) return;

        float totalDepositos = 0;
        float totalRetiros = 0;

        int cantidad = nElem(i);

        for (int j = 0; j < cantidad; j++) {
            Cliente cli = (Cliente) eliminar(i);
            if (cli != null) {
                adicionar(cli, i);
                if (cli.getTope().equalsIgnoreCase("d")) {
                    totalDepositos += cli.getImpo();
                } else if (cli.getTope().equalsIgnoreCase("r")) {
                    totalRetiros += cli.getImpo();
                }
            }
        }

        System.out.println("Sucursal " + (i + 1));
        System.out.println("Total Depósitos: " + totalDepositos);
        System.out.println("Total Retiros: " + totalRetiros);
    }

    public void clienteX(String nombre) {
        for (int i = 0; i < ncc; i++) {
            if (!validarIndice(i)) continue;

            int cantidad = nElem(i);
            for (int j = 0; j < cantidad; j++) {
                Cliente cli = (Cliente) eliminar(i);
                if (cli != null) {
                    adicionar(cli, i);
                    if (cli.getNom().equalsIgnoreCase(nombre)) {
                        System.out.println(nombre + " hizo transacciones en la sucursal " + (i + 1));
                        System.out.println("Tipo de transacción: " + cli.getTope());
                    }
                }
            }
        }
    }

    public void masDepositos() {
        int maxDeposito = 0;

        // Buscar el depósito mayor
        for (int i = 0; i < ncc; i++) {
            if (!validarIndice(i)) continue;

            int cantidad = nElem(i);
            for (int j = 0; j < cantidad; j++) {
                Cliente cli = (Cliente) eliminar(i);
                if (cli != null) {
                    adicionar(cli, i);
                    if (cli.getTope().equalsIgnoreCase("d") && cli.getImpo() > maxDeposito) {
                        maxDeposito = (int) cli.getImpo();
                    }
                }
            }
        }

        // Mostrar clientes con ese depósito
        for (int i = 0; i < ncc; i++) {
            if (!validarIndice(i)) continue;

            int cantidad = nElem(i);
            for (int j = 0; j < cantidad; j++) {
                Cliente cli = (Cliente) eliminar(i);
                if (cli != null) {
                    adicionar(cli, i);
                    if (cli.getTope().equalsIgnoreCase("d") && cli.getImpo() == maxDeposito) {
                        System.out.println("Cliente con mayor depósito: " + cli.getNom());
                    }
                }
            }
        }
    }

    public static class Cliente {
        private String nom;    // Nombre del cliente
        private String tope;   // Tipo de operación: "d" para depósito, "r" para retiro
        private float impo;    // Importe de la operación

        public Cliente(String nom, String tope, float impo) {
            this.nom = nom;
            this.tope = tope.toLowerCase(); // asegurar minusculas
            this.impo = impo;
        }

        public String getNom() {
            return nom;
        }

        public String getTope() {
            return tope;
        }

        public float getImpo() {
            return impo;
        }

        @Override
        public String toString() {
            return "Cliente{" +
                    "nombre='" + nom + '\'' +
                    ", tipo='" + tope + '\'' +
                    ", importe=" + impo +
                    '}';
        }
    }
}
