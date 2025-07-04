package colas;

public class PilaMul {
	  private Pila[] p;  // Arreglo de pilas
	    private int np;    // Número de pilas

	    // Constructor
	    public PilaMul(int npilas) {
	        this.np = npilas;
	        this.p = new Pila[npilas + 1]; // Se suma 1 porque usamos índices desde 1
	    }

	    // Método para crear una pila individual en la multipila
	    public void creaPila(int i, int cantElementos) {
	        if (i >= 1 && i <= np) {
	            p[i] = new Pila(cantElementos);
	        } else {
	            System.out.println("Índice fuera de rango para crear pila");
	        }
	    }

	    // Método para adicionar un elemento a una pila específica
	    public void adicionar(int i, Object elemento) {
	        if (i >= 1 && i <= np && p[i] != null) {
	            p[i].adicionar(elemento);
	        } else {
	            System.out.println("La pila " + i + " no existe o índice fuera de rango");
	        }
	    }

	    // Método para eliminar un elemento de una pila específica
	    public Object eliminar(int i) {
	        if (i >= 1 && i <= np && p[i] != null && !p[i].esVacia()) {
	            return p[i].eliminar();
	        } else {
	            System.out.println("La pila " + i + " no existe, está vacía o índice fuera de rango");
	            return null;
	        }
	    }

	    // Método para verificar si una pila específica está vacía
	    public boolean esVacia(int i) {
	        if (i >= 1 && i <= np && p[i] != null) {
	            return p[i].esVacia();
	        } else {
	            System.out.println("La pila " + i + " no existe o índice fuera de rango");
	            return true; // Consideramos como vacía si no existe
	        }
	    }

	    // Método que muestra todas las pilas de la multipila
	    public void mostrar() {
	        for (int i = 1; i <= np; i++) {
	            System.out.println("Pila " + i + ":");
	            if (p[i] != null) {
	                // Crear pila auxiliar para no perder los datos
	                Pila aux = new Pila(p[i].getMax());
	                
	                // Si la pila está vacía, indicarlo
	                if (p[i].esVacia()) {
	                    System.out.println("  (Vacía)");
	                } else {
	                    // Mostrar elementos sacándolos y guardándolos en auxiliar
	                    while (!p[i].esVacia()) {
	                        Object elem = p[i].eliminar();
	                        System.out.println("  " + elem);
	                        aux.adicionar(elem);
	                    }
	                    
	                    // Restaurar la pila original
	                    while (!aux.esVacia()) {
	                        p[i].adicionar(aux.eliminar());
	                    }
	                }
	            } else {
	                System.out.println("  (No inicializada)");
	            }
	        }
	    }

	    // Método para obtener el número de pilas
	    public int getNp() {
	        return np;
	    }
	    public boolean esLlena(int i) {
	        if (i >= 1 && i <= np && p[i] != null) {
	            return p[i].esLlena();
	        } else {
	            System.out.println("La pila " + i + " no existe o índice fuera de rango");
	            return false;
	        }
	    }

}
