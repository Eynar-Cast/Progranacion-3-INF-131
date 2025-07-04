package colas;

public class CSNormal extends CSimple {

    public CSNormal(int vc) {
        super(vc);
    }

    @Override
    public boolean esLlena() {
        return fin == max;
    }

    @Override
    public boolean esVacia() {
        return ini == fin;
    }

    @Override
    public void adicionar(Object ele) {
        if (!esLlena()) {
            v[fin] = ele;
            fin++;
        } else {
            System.out.println("Cola llena");
        }
    }

    @Override
    public Object eliminar() {
        if (esVacia()) {
            System.out.println("Cola vacía");
            return null;
        } else {
            Object dato = v[ini];
            ini++;
            if (ini == fin) {
                ini = 0;
                fin = 0;
            }
            return dato;
        }
    }

    @Override
    public void mostrar() {
        CSNormal aux = new CSNormal(max);
        while (!esVacia()) {
            Object da = eliminar();
            System.out.print("\t" + da);
            aux.adicionar(da);
        }
        while (!aux.esVacia()) {
            adicionar(aux.eliminar());
        }
        System.out.println();
    }

    @Override
    public int nElem() {
        return fin - ini;
    }
    
}
