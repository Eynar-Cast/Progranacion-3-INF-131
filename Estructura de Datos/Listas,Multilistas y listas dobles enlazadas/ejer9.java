import java.util.Scanner;
//4.Implementar en Java el siguiente problema: La empresa "El buen vino" que se dedica a la venta de una variedad de vinos además se conoce que tiene varias sucursales donde trabajan varios empleados.
//Se pide:
//a)	Diseñar la estructura de clases respectiva respectivo
//b)	¿Cuántas sucursales tiene la empresa?
// 
//c)	
//d)	¿Cuantos empleados tiene la sucursal de nombre M
//e)	¿Cuál es la edad promedio de los empleados en toda la empresa?
//f)	¿Cuál es la sucursal que vendió más productos?
//g)	El gerente decidió despedir al empleado Z
//h)	Implementar la venta de un vino
//i)	¿Cuál es el nombre de la sucursal donde existen más vinos?
//j)	¿Cuál es el producto más vendido?
//k)	Adicionar a un empleado de modo que la nómina de empleados permanezca ordenada.
//
public class MainEmpresaVinos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empresa empresa = new Empresa();
        // Registrar sucursales, empleados y vinos
        System.out.print("Ingrese la cantidad de sucursales: ");
        int cantSucursales = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < cantSucursales; i++) {
            System.out.print("Nombre de la sucursal #" + (i + 1) + ": ");
            String nombreSuc = sc.nextLine();
            empresa.agregarSucursal(nombreSuc);

            Sucursal suc = empresa.buscarSucursal(nombreSuc);

            System.out.print("Cantidad de empleados en " + nombreSuc + ": ");
            int cantEmp = sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < cantEmp; j++) {
                System.out.print("  Nombre del empleado #" + (j + 1) + ": ");
                String nomEmp = sc.nextLine();
                System.out.print("  Edad del empleado: ");
                int edadEmp = sc.nextInt();
                sc.nextLine();
                suc.getEmpleados().insertar(new Empleado(nomEmp, edadEmp), 0, 0);
            }

            System.out.print("Cantidad de vinos en " + nombreSuc + ": ");
            int cantVinos = sc.nextInt();
            sc.nextLine();

            for (int j = 0; j < cantVinos; j++) {
                System.out.print("  Nombre del vino #" + (j + 1) + ": ");
                String nomVino = sc.nextLine();
                System.out.print("  Cantidad vendida: ");
                int cantVendida = sc.nextInt();
                sc.nextLine();
                suc.getVinos().insertar(new Vino(nomVino, cantVendida), 0, 0);
            }
        }
        System.out.println("\nDatos cargados correctamente.");

        //inciso B
        
        System.out.println("-------- INCISO b --------");
        int totalSucursales = empresa.getSucursales().nElem();
        System.out.println("Cantidad de sucursales: " + totalSucursales);
        //inciso C
        System.out.println("\n-------- INCISO c --------");
        System.out.print("Ingrese el nombre de la sucursal para contar empleados: ");
        String nombreBuscar = sc.nextLine();
        Sucursal sucM = empresa.buscarSucursal(nombreBuscar);
        if (sucM != null) {
            int totalEmpleados = sucM.getEmpleados().nElem();
            System.out.println("La sucursal '" + nombreBuscar + "' tiene " + totalEmpleados + " empleado(s).");
        } else {
            System.out.println("Sucursal no encontrada.");
        }
        //inciso D
        System.out.println("\n-------- INCISO d --------");

        int sumaEdades = 0;
        int totalEmpleados = 0;

        NodoS nodoSucursal = empresa.getSucursales().getCabecera();
        while (nodoSucursal != null) {
            Sucursal suc = (Sucursal) nodoSucursal.getDato();
            NodoS nodoEmp = suc.getEmpleados().getCabecera();
            while (nodoEmp != null) {
                Empleado emp = (Empleado) nodoEmp.getDato();
                sumaEdades += emp.getEdad();
                totalEmpleados++;
                nodoEmp = nodoEmp.getSig();
            }
            nodoSucursal = nodoSucursal.getSig();
        }

        if (totalEmpleados > 0) {
            double promedio = (double) sumaEdades / totalEmpleados;
            System.out.printf("La edad promedio de todos los empleados es: %.2f años\n", promedio);
        } else {
            System.out.println("No hay empleados registrados.");
        }
        
        System.out.println("\n-------- INCISO e --------");

        String sucursalMaxVentas = null;
        int maxVentas = -1;

        nodoSucursal = empresa.getSucursales().getCabecera();
        while (nodoSucursal != null) {
            Sucursal suc = (Sucursal) nodoSucursal.getDato();
            int sumaVentas = 0;

            NodoS nodoVino = suc.getVinos().getCabecera();
            while (nodoVino != null) {
                Vino vino = (Vino) nodoVino.getDato();
                sumaVentas += vino.getCantidadVendida();
                nodoVino = nodoVino.getSig();
            }

            if (sumaVentas > maxVentas) {
                maxVentas = sumaVentas;
                sucursalMaxVentas = suc.getNombre();
            }

            nodoSucursal = nodoSucursal.getSig();
        }

        if (sucursalMaxVentas != null) {
            System.out.println("La sucursal que vendió más productos fue: " + sucursalMaxVentas + " con " + maxVentas + " ventas.");
        } else {
            System.out.println("No se registraron ventas.");
        }
        
        System.out.println("\n-------- INCISO f --------");
        System.out.print("Ingrese el nombre de la sucursal del empleado a despedir: ");
        String sucursalDespedir = sc.nextLine();
        System.out.print("Ingrese el nombre del empleado a despedir: ");
        String nombreDespedido = sc.nextLine();

        Sucursal suc = empresa.buscarSucursal(sucursalDespedir);

        if (suc != null) {
            NodoS actual = suc.getEmpleados().getCabecera();
            NodoS anterior = null;
            boolean encontrado = false;

            while (actual != null) {
                Empleado emp = (Empleado) actual.getDato();
                if (emp.getNombre().equalsIgnoreCase(nombreDespedido)) {
                    if (anterior == null) {
                        // despedir al primero
                        suc.getEmpleados().setCabecera(actual.getSig());
                    } else {
                        anterior.setSig(actual.getSig());
                    }
                    encontrado = true;
                    break;
                }
                anterior = actual;
                actual = actual.getSig();
            }

            if (encontrado) {
                System.out.println("Empleado '" + nombreDespedido + "' fue despedido correctamente.");
            } else {
                System.out.println("Empleado no encontrado en la sucursal.");
            }
        } else {
            System.out.println("Sucursal no encontrada.");
        }
        
        System.out.println("\n-------- INCISO g --------");
        System.out.print("Ingrese el nombre de la sucursal donde se realizó la venta: ");
        String sucursalVenta = sc.nextLine();
        System.out.print("Ingrese el nombre del vino vendido: ");
        String vinoVendido = sc.nextLine();
        System.out.print("Ingrese la cantidad vendida: ");
        int cantidadVendida = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        suc = empresa.buscarSucursal(sucursalVenta);

        if (suc != null) {
            NodoS nodoVino = suc.getVinos().getCabecera();
            boolean encontrado = false;

            while (nodoVino != null) {
                Vino vino = (Vino) nodoVino.getDato();
                if (vino.getNombre().equalsIgnoreCase(vinoVendido)) {
                    vino.vender(cantidadVendida); // suma la cantidad vendida
                    encontrado = true;
                    break;
                }
                nodoVino = nodoVino.getSig();
            }

            if (!encontrado) {
                suc.getVinos().insertar(new Vino(vinoVendido, cantidadVendida), 0, 0);
                System.out.println("Vino no existía, fue añadido con la venta registrada.");
            } else {
                System.out.println("Venta registrada al vino existente.");
            }
        } else {
            System.out.println("Sucursal no encontrada.");
        }
        System.out.println("\n-------- INCISO h --------");

        String nombreSucursalMaxVinos = null;
        int maxCantidadVinos = -1;

         nodoSucursal = empresa.getSucursales().getCabecera();
        while (nodoSucursal != null) {
         suc = (Sucursal) nodoSucursal.getDato();
            int cantidadVinos = suc.getVinos().nElem();

            if (cantidadVinos > maxCantidadVinos) {
                maxCantidadVinos = cantidadVinos;
                nombreSucursalMaxVinos = suc.getNombre();
            }

            nodoSucursal = nodoSucursal.getSig();
        }

        if (nombreSucursalMaxVinos != null) {
            System.out.println("La sucursal con más vinos es: " + nombreSucursalMaxVinos + " (" + maxCantidadVinos + " vinos)");
        } else {
            System.out.println("No hay sucursales registradas.");
        }
        
        System.out.println("\n-------- INCISO i --------");

        String nombreVinoMasVendido = null;
         maxVentas = -1;

         nodoSucursal = empresa.getSucursales().getCabecera();
        while (nodoSucursal != null) {
         suc = (Sucursal) nodoSucursal.getDato();
            NodoS nodoVino = suc.getVinos().getCabecera();

            while (nodoVino != null) {
                Vino vino = (Vino) nodoVino.getDato();
                if (vino.getCantidadVendida() > maxVentas) {
                    maxVentas = vino.getCantidadVendida();
                    nombreVinoMasVendido = vino.getNombre();
                }
                nodoVino = nodoVino.getSig();
            }

            nodoSucursal = nodoSucursal.getSig();
        }

        if (nombreVinoMasVendido != null) {
            System.out.println("El vino más vendido es: " + nombreVinoMasVendido + " con " + maxVentas + " ventas.");
        } else {
            System.out.println("No se registraron ventas de vinos.");
        }
        
        System.out.println("\n-------- INCISO j --------");
        System.out.print("Ingrese el nombre de la sucursal donde agregar al nuevo empleado: ");
        String sucursalAgregar = sc.nextLine();
        System.out.print("Nombre del nuevo empleado: ");
        String nombreNuevo = sc.nextLine();
        System.out.print("Edad del nuevo empleado: ");
        int edadNuevo = sc.nextInt();
        sc.nextLine();

         suc = empresa.buscarSucursal(sucursalAgregar);

        if (suc != null) {
            Empleado nuevoEmp = new Empleado(nombreNuevo, edadNuevo);
            NodoS actual = suc.getEmpleados().getCabecera();
            NodoS anterior = null;
            NodoS nuevoNodo = new NodoS();
            nuevoNodo.setDato(nuevoEmp);

            while (actual != null && ((Empleado) actual.getDato()).getNombre().compareToIgnoreCase(nombreNuevo) < 0) {
                anterior = actual;
                actual = actual.getSig();
            }

            if (anterior == null) {
                // Insertar al inicio
                nuevoNodo.setSig(suc.getEmpleados().getCabecera());
                suc.getEmpleados().setCabecera(nuevoNodo);
            } else {
                // Insertar en medio o al final
                anterior.setSig(nuevoNodo);
                nuevoNodo.setSig(actual);
            }

            System.out.println("Empleado '" + nombreNuevo + "' agregado en orden correctamente.");
        } else {
            System.out.println("Sucursal no encontrada.");
        }       
    }
}

Clases 
package Multilistas;
public class Vino {
    private String nombre;
    private int cantidadVendida;

    public Vino(String nombre, int cantidadVendida) {
        this.nombre = nombre;
        this.cantidadVendida = cantidadVendida;
    }

    public String getNombre() { return nombre; }
    public int getCantidadVendida() { return cantidadVendida; }
    public void vender(int cantidad) { this.cantidadVendida += cantidad; }

    @Override
    public String toString() {
        return nombre + " [" + cantidadVendida + "]";
    }
}

package Multilistas;
public class Empleado {
    private String nombre;
    private int edad;

    public Empleado(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}

package Multilistas;
public class Sucursal {
    private String nombre;
    private LSimple empleados;  // lista de Empleado
    private LSimple vinos;      // lista de Vino

    public Sucursal(String nombre) {
        this.nombre = nombre;
        this.empleados = new LSimple();
        this.vinos = new LSimple();
    }

    public String getNombre() { return nombre; }
    public LSimple getEmpleados() { return empleados; }
    public LSimple getVinos() { return vinos; }

    @Override
    public String toString() {
        return nombre;
    }
}

package Multilistas;
class Empresa {
    private LSimple sucursales;

    public Empresa() {
        sucursales = new LSimple();
    }

    public LSimple getSucursales() {
        return sucursales;
    }

    public void agregarSucursal(String nombre) {
        sucursales.insertar(new Sucursal(nombre), 0, 0); // Datos irrelevantes, se usan como wrapper
    }

    public Sucursal buscarSucursal(String nombre) {
        NodoS aux = sucursales.getCabecera();
        while (aux != null) {
            Sucursal s = (Sucursal) aux.getDato();
            if (s.getNombre().equals(nombre)) return s;
            aux = aux.getSig();
        }
        return null;
    }
}

package Multilistas;
public class LSimple {
    private NodoS cab;

    public LSimple() {
        cab = null;
    }

    public NodoS getCabecera() {
        return cab;
    }

    public void setCabecera(NodoS n) {
        cab = n;
    }

    public boolean esVacia() {
        return cab == null;
    }

    public void insertar(Object elem, double nota, int ci) {
        NodoS nuevo = new NodoS();
        nuevo.setDato(elem); // aquí puede ser Sucursal, Empleado, Vino, etc.
        if (esVacia()) {
            cab = nuevo;
        } else {
            NodoS aux = cab;
            while (aux.getSig() != null) {
                aux = aux.getSig();
            }
            aux.setSig(nuevo);
        }
    }

    public int nElem() {
        int cont = 0;
        NodoS aux = cab;
        while (aux != null) {
            cont++;
            aux = aux.getSig();
        }
        return cont;
    }

    public void mostrar() {
        NodoS aux = cab;
        while (aux != null) {
            System.out.print(aux.getDato() + "\t");
            aux = aux.getSig();
        }
        System.out.println();
    }
}

package Multilistas;
public class NodoS {
    private Object dato;
    private NodoS sig;

    public NodoS() {
        dato = null;
        sig = null;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoS getSig() {
        return sig;
    }

    public void setSig(NodoS sig) {
        this.sig = sig;
    }
}

