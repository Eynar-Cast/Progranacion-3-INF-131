package colas;

public abstract class CSimple {
    protected int ini;
    protected int fin;
    protected int max;
    protected Object[] v;

    public CSimple(int ca) {
        ini = 0;
        fin = 0;
        max = ca;
        v = new Object[max];
    }

    public abstract boolean esVacia();
    public abstract boolean esLlena();
    public abstract void adicionar(Object ele);
    public abstract Object eliminar();
    public abstract void mostrar();
    public abstract int nElem();
}
