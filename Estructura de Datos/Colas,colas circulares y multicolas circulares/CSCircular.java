package colas;

public class CSCircular extends CSimple {

    public CSCircular(int cc) {
        super(cc);
    }

    @Override
    public boolean esVacia() {
        return ini == fin;
    }

    @Override
    public boolean esLlena() {
        return (fin + 1) % max == ini;
    }

    @Override
    public void adicionar(Object ele) {
        if (esLlena()) {
            System.out.println("Cola llena");
        } else {
            fin = (fin + 1) % max;
            v[fin] = ele;
        }
    }

    @Override
    public Object eliminar() {
        if (esVacia()) {
            System.out.println("Cola vacía");
            return null;
        } else {
            ini = (ini + 1) % max;
            return v[ini];
        }
    }

    @Override
    public void mostrar() {
        int ne = nElem();
        for (int i = 0; i < ne; i++) {
            Object dato = eliminar();
            System.out.println("\t" + dato);
            adicionar(dato);
        }
    }

    @Override
    public int nElem() {
        return (fin - ini + max) % max;
    }

    public void vaciar(CSCircular aux) {
        while (!aux.esVacia()) {
            adicionar(aux.eliminar());
        }
    }
}
