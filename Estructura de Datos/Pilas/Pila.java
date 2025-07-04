package ejercicios;
public class Pila {
	private int tope,max;
	Object v[]=new Object [100];
	Pila(int ca)
	{max=ca;tope=0;}

	boolean esLlena()
	{
		if(tope==max) return true;
		else return false;
		
	}
	boolean esVacia()
	{
		if(tope==0) return true;
		else return false;
		
	}
	void adicionar(Object item)
	{
		if(!esLlena())
		{
			tope=tope+1;
			v[tope]=item;
		}
		else System.out.println("pila llena");	
		}
	Object eliminar()
	{
		Object item=null;
		if(!esVacia()) {item=v[tope];tope--;}
		else System.out.println("pila vacia");
		return item;
	}
	void mostrar()
	{
		Object item;
		Pila aux=new Pila(max);
		while(!esVacia())
			aux.adicionar(eliminar());
		while(!aux.esVacia())
		{
			item=aux.eliminar();
			System.out.println(item);
			adicionar(item);
		}
	}
	void vaciar(Pila B)
	{
		while(!B.esVacia())
			adicionar(B.eliminar());
	}
		int nElem()
		{
			return tope;
		}
		int getMax()
		{
			return max;
		}	

}
