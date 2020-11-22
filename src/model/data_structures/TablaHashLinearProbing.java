package model.data_structures;

import java.util.LinkedList;

public class TablaHashLinearProbing<K extends Comparable <K>,V extends Comparable<V>> implements TablaSimbolos<K,V>{

	/**
	 * Atributos de la clase.
	 */
	private int N; //N�mero de duplas key-value en la tabla
	private int M; //Tama�o de la tabla (LinearProbing)
	private K[] keys; //Las Keys (llaves)
	private V[] vals; //Los Values (valores)
	private int reHashes = 0;


	/**
	 * Constructor de la clase
	 * Inicializa el arreglo de llaves y valores
	 * @param capacidad La capacidad de la tabla de hash
	 */
	public TablaHashLinearProbing(int capacidad)
	{
		M = capacidad;
		N = 0;
		keys = (K[]) new Comparable[M];
		vals = (V[]) new Comparable[M];

		
	}

	/**
	 * Retorna el hashCode de la llave que entra por par�metro
	 * @param key Llave que se quiere buscar el hashCode
	 * @return El valor hashCode de la llave.
	 */
	private int hash(K key)
	{
		return (key.hashCode() & 0x7fffffff) %M;
	}

	public void put(K key, V value)
	{
		if (key == null) throw new IllegalArgumentException("La llave que quiere insetar es null");
		//Si el valor es null elimina la llave de la Tabla
		if(value == null)
		{
			remove(key);
			return;
		}
		//Aumenta el tamano de la tabla si la mitad esta llena
		if (N>=M/2)
			reHash(2*M);
		//Busca el indice por la llave y si existe actualiza el valor
		int i;
		LinkedList <V> pelis = new LinkedList<>();
		
		for (i = hash(key); keys[i] != null; i = (i+1) %M) 
		{
			if(keys[i].equals(key))
			{
				vals [i] = value;
				return;
			}
		}
		keys[i] = key;
		vals[i] = value;
		N++;

	}

	public Iterable<V> get(K key) {
		if (key == null) throw new IllegalArgumentException("la llave que busca es null");
		LinkedList<V> items = new LinkedList<V>();
		for (int i = hash(key); keys[i] != null; i = (i+1) %M) {
			if(keys[i].equals(key))
			{
				items.add(vals[i]);
			}
				return items;
		}
		return null;
	}

	public Iterable<V> remove(K key) {
		if (key == null) throw new IllegalArgumentException("la llave que intenta borrar es null");
		//Si la llave no existe no hace nada
		if(!contains(key)) 
		{
			return null;
		}
		//Busca la posicion i de la llave
		int i = hash(key);
		//Crea la lista encadenada
		LinkedList<V> items = new LinkedList<V>();
		while(!key.equals(keys[i]))
		{
			i = (i+1)%M;
		}
		//Borra la dupla llave-valor
		items.add(vals[i]);
		keys[i] = null;
		vals[i] = null;
		//Hace el reHash en el bloque
		i = (i+1)%M;
		//Borra la llave en keys[i] y la hace el reHash
		while(keys[i] != null)
		{
			K kReHash = keys[i];
			V vReHash = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(kReHash, vReHash);
			i = (i+1) % M;
		}
		N--;
		if(N > 0 && N <= M/8)
			reHash(M/2);
		return items;
	}

	public boolean contains(K key) 
	{
		if (key == null) throw new IllegalArgumentException("la llave que busca es null");
		return get(key) != null;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() 
	{
		return N;
	}

	public Lista<K> keySet() {
		ArregloDinamico<K> lista = new ArregloDinamico<K>(9973);
		for (int i = 0; i < M; i++) {
			if(keys[i] != null)
				lista.agregarFinal(keys[i]);	// agrega al final porque para ArregloDinamico es más eficiente que agregar al inicio.
		}
		return lista;
	}

	public Lista<V> valueSet() {
		ArregloDinamico<V> lista = new ArregloDinamico<V>(9973);
		for (int i = 0; i < M; i++) {
			if(vals[i] != null)
				lista.agregarFinal(vals[i]);	
		}
		return lista;
	}

	public void reHash(int capacidad) 
	{
		//Crea la nueva tabla de Hash para hacer el reHash 
		reHashes++;
		TablaHashLinearProbing<K, V> nueva = new TablaHashLinearProbing<K, V>(capacidad);
		//Hace el reHash
		for (int i = 0; i < M ; i++) 
		{
			if(keys[i] != null)
				try {
					nueva.put(keys[i], vals[i]);
				} catch (Exception e) {			
					e.printStackTrace();
				}
		}
		//Establace los nuevos valores de la nueva tabla a la tabla principal
		keys = nueva.keys;
		vals = nueva.vals;
		M = nueva.M;
	}
	
	public int darM() {
		return M;
	}
	
	public int darN() {
		return N;
	}
	
	public int darFactorCarga() {
		return N/M;
	}
	
	public int darReHashes() {
		return reHashes;
	}

}
