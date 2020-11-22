package model.data_structures;

import java.util.Iterator;
import java.util.LinkedList;

public class TablaHashSeparateChaining<K extends Comparable<K>,V extends Comparable<V>> implements TablaSimbolos<K,V>
{
	
	private static final int INIT_CAPACITY = 9973;
	public int M =9973;
	private int n; // number of Key, Value pairs
	private int reHashes = 0;
	
	
	private static class Node<K extends Comparable<K>,V extends Comparable<V>>
	{
		private K key;
		private V val;
		
		
		public Node(K pKey, V pValue) 
		{
			// TODO Auto-generated constructor stub
			this.key = pKey;
			this.val = pValue;
		}
		
	}
	
	private LinkedList<Node<K,V>>[] st;
    
	public TablaHashSeparateChaining() {
		this(9973);
	}
	
	public TablaHashSeparateChaining(int m) {
		this.M = m;
		st = new LinkedList[M];
		for(int i=0; i<M; i++) {
            st[i] = null;
        }
	}
    
    /**
     * Retorna el hashCode de la llave que entra por parámetro
     * @param key llave asociada al valor que se desea obtener.
     * @return int valor asociado a esa llave
     */
    public int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    } 
    

	public void put(K key, V value) {
		
		//verifica que las entradas sean validas:
		if (key == null) throw new IllegalArgumentException("la llave no puede ser Null");
		if(value==null) throw new IllegalArgumentException("el valor que se intenta agregar no puede ser Null");
		
		// duplica el tamaño de la tabla si n>= 5*M (factor de carga máximo permitido).
        if (n >= 5*M) reHash(5*M);
        
		int i = hash(key); 
		LinkedList<Node<K,V>> items = st[i]; //accede la lista en la posicion del hash(key).
		 
        if(items == null) {                  // si no hay elementos en la chain, crea una chain y agregar el primer elemento.
            items = new LinkedList<Node<K,V>>();
 
            Node<K,V> item = new Node<K,V>(key, value);
            items.addFirst(item);
            n++;
            st[i] = items;
        }
        else {                              // si ya hay elementos en la chain, no reemplaza sino que no agrega AL INICIO.

        	
            Node<K,V> item = new Node<K,V>(key, value);
            items.addFirst(item);
            n++;
        }
	}

	@Override
	public Iterable<V> get(K key) {
		// TODO Auto-generated method stub
		int i =hash(key);
		LinkedList<Node<K,V>> items = st[i]; //accede a la chain en la posicion hash(key)
		
		if(items == null) // si está vacia retorna null.
            return null;
		LinkedList<V> values = new LinkedList<V>();
        for(Node<K,V> item : items) {
        	
            if(item.key.compareTo(key)==0)
                values.addFirst(item.val);           // agrega los valores que coindicen con la key parametro a una lista iterable para retornar.
        
        }
        return (Iterable<V>) values;
				
	}


	@Override
	public Iterable<V> remove(K key) {
		// TODO Auto-generated method stub
		if (key == null) throw new IllegalArgumentException("la llave no puede ser Null");
		
//		 //parte en 1/5 el tamaño de la lista si el número de datos en la tabla es <5*M
//        if (M > INIT_CAPACITY && n < 5*M) reHash(M/5);
        
		int i = hash(key);
        LinkedList<Node<K,V>> items = st[i]; // accede a la chain en la posicion hash(key)
 
        if(items == null) // si está vacia retorna null.
            return null;
        
        LinkedList<V> deletedValues = new LinkedList<V>();
        for(Node<K,V> item : items) {
            if (item.key.compareTo(key)==0) {
                deletedValues.addFirst(item.val);    // agrega los valores borrados a una lista iterable para retornar.
            	items.remove(item);                 // remueve el Node<K,V> de la chain.
            	n--;
                
            }
       }
        return (Iterable<V>) deletedValues;
	}
        
		
	
	@Override
	public boolean contains(K key) {
		// TODO Auto-generated method stub
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	public int size() {
		// return number of key-value pairs in symbol table
	        return n;
	}

	@Override
	public Lista<K> keySet() {
		// TODO Auto-generated method stub
		ArregloDinamico<K> llaves =  new ArregloDinamico<K>(9973);
		
		for (int i = 0; i < M; i++) {
			
        	LinkedList<Node<K,V>>  items = st[i];
        	if(items!=null) {
        	for(Node<K,V> item : items) {
        		llaves.agregarFinal(item.key);  // agrega al final porque es mas eficiente de esta manera (para Arreglo Dinamico)
        		}
        	}
        }
		return llaves;
	}

	@Override
	public Lista<V> valueSet() {
		// TODO Auto-generated method stub
		ArregloDinamico<V> values =  new ArregloDinamico<V>(9973);
		
		for (int i = 0; i < M; i++) {
			
        	LinkedList<Node<K,V>>  items = st[i];
        	if(items!=null) {
	        	for(Node<K,V> item : items) {
	        		values.agregarFinal(item.val);  // agrega al final porque es mas eficiente de esta manera (para ArregloDinamico)
	        		}
	        	}
        }
		return values;
	}

	/**
	 * crear una nueva tabla de hash de mayor capacidad cuando se llega a la máxima
	 * capacidad en la tabla de hash actual permitida, esta es N/M>=5
	 * @param chains
	 */
	public void reHash(int chains) {
		// TODO Auto-generated method stub
		reHashes++;
		TablaHashSeparateChaining<K,V> temp = new TablaHashSeparateChaining<K,V>(chains);
		
        for (int i = 0; i < M; i++) {             // accede a cada posición de la tabla original
        	LinkedList<Node<K,V>>  items = st[i]; 
        	
        	if (items != null) {
        		for(Node<K,V> item : items) {    //pasa sus valores a la nueva tabla de tamaño chains.
            		temp.put(item.key, item.val);
            		}
            }
        }
        this.M  = temp.M;
        this.n  = temp.n;
        this.st = temp.st;
    }
	
	public int darM() {
		return M;
	}
	
	public int darN() {
		return n;
	}
	
	public int darFactorCarga() {
		return n/M;
	}
	
	public int darReHashes() {
		return reHashes;
	}

}
