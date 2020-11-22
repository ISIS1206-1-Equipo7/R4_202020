package model.data_structures;

import java.util.LinkedList;

public interface TablaSimbolosOrdenada <K extends Comparable<K>,V>{
		
	/**
	 * Retornar el número de parejas [Llave,Valor] del árbol
	 * @return int numero de parejas llave valor en el árbol 
	 */
	int size();
	
	/**
	 * Informa si el árbol es vacío
	 * @return True si el árbol esá vacio, False en otro caso
	 */
	boolean isEmpty ();
	
	/**
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se
	 * encuentra se retorna el valor null.
	 * @return V valor asociado a la llave.
	 * @throws IllegalArgumentException si {@code key} es {@code null}
	 */
	LinkedList<V> get (K key);
	
	/**
	 * Retorna la altura del camino desde la raiz para llegar a la llave key 
	 * (si la llave existe). 
	 * @return int altura asociada desde la raiz hasta la llave. valor –1 si la llave No existe.
	 */
	int getHeight(K key);
	
	/**
	 * Indica si la llave key se encuentra en el árbol
	 * @return True si la llave esta contenida en el árbol. False en otro caso.
	 * @throws IllegalArgumentException si {@code key} es {@code null}
	 */
	boolean contains(K key);
	
	/**
	 * Inserta la pareja [key, val] en el árbol. Si la llave ya existe se reemplaza el valor.
	 */
	void put(K key, V val);
	
	/**
	 * Retorna la altura del árbol definida como la altura de la rama más alta.
	 * (aquella que tenga mayor número de enlaces desde la raíz a una hoja).
	 * @return  int altura del arbol.
	 */
	int height();
	
	/**
	 * Retorna la llave más pequeña del árbol. Valor null si árbol vacío
	 * @return K key mas pequena del arbol.
	 */
	K min();
	
	/**
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío.
	 * @return K key mas grande del arbol.
	 */
	K max();
	
	/**
	 * Retorna las llaves del árbol. Para su implementación en BST o RBT
	 * deben retornarse usando un recorrido en Inorden.
	 * @return linkedList<K> con las llaves del arbol en el orden Inorden.
	 */
	Iterable<K> keySet();
		
	/**
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado. 
	 * Las llaves en el rango deben retornarse en orden ascendente. 
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * @return LinkedList<K> con las llaves del arbol en el rango especificado en orden ascendente.
	 */
	Iterable<K> keysInRange(K init, K end);
	
	/**
	 * Retorna todos los valores V en el árbol que estén asociados al rango de llaves dado.
	 * Por eficiencia, debe intentarse No recorrer todo el árbol.
	 * @return LinkedList<K> con los valores del arbol en el rango especificado segun las keys.
	 */
	Iterable<V> valuesInRange(K init, K end);
			
}
