package model.data_structures;

public interface TablaSimbolos<K extends Comparable <K>,V extends Comparable<V>> {

	/**
	 * Agrega una dupla (k,v) a la tabla. Si la llave (key) K existe, se reemplaza su valor (value) V asociado.
	 * V no puede ser null. En este caso una llave K solo tiene asociado un valor V.
	 * @param key Llave 
	 * @param value Valor
	 */
	public void put (K key,V value);
	
	/**
	 * Obtener el valor (value) V asociado a la llave (key) K. Se obtine null solo si la llave K no existe.
	 * Se usa el comparador sobre las llaves para saber si existe.
	 * @return V - V es el valor asociado a la llave (key) K.
	 */
	public Iterable<V> get(K key);
	
	/**
	 * Borrar la dupla asociada a la llave K. Se obtiene el valor V asociado a llave K.
	 * Se obtiene null solo si la llave K no existe.
	 * @param key Llave
	 * @return V - V es el valor asociado a la llave (key). 
	 */
	public Iterable<V> remove(K key);
	
	/**
	 * Mira si la llave K (key) se encuentra almacenada en la Tabla. 
	 * @param key Llave 
	 * @return true si se encuentra almacenada en la Tabla, false en el caso contrario.
	 */
	public boolean contains (K key);
	
	/**
	 * Mira si la Tabla esta vacia.
	 * @return true si la Tabla NO tiene datos, false en el caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Numero de duplas de la Tabla de Simbolos.
	 * @return numero total de la tabla de simbolos.
	 */
	public int size();
	
	/**
	 * Retorna todas las llaves almacenadas en la Tabla. 
	 * @return La lista de todas las llaves almacenadas en la Tabla.
	 */
	public Lista<K> keySet();
	
	/**
	 * Retorna todos los valores almacenados en la Tabla.
	 * @return La lista de todos los valores almacenados en la Tabla.
	 */
	public Lista<V> valueSet();
	

}
