package model.data_structures;

import java.util.LinkedList;


public class RedBlackTree<K extends Comparable<K>,V> implements TablaSimbolosOrdenada<K,V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	public Node root; //Raiz del BST

	public class Node{
		public K key;					// Llave
		public LinkedList<V> values;	// La lista de valores asociados (Accidentes)
		public Node left, right;		// Nodos Izquierdo y Derecho
		public boolean color; 			// Color del enlace padre
		public int size; 				// Tamano de los subarboles

		public Node(K key, V val, boolean color, int size) {
			this.key = key;
			this.values = new LinkedList<V>();
			this.values.add(val);
			this.color = color;
			this.size = size;
		}
	}

	/**
	 * Inicializa una tabla de simbolos vacio
	 */
	public RedBlackTree() {
	}

	/**
	 * Verifica si el nodo dado por parametro es de color Rojo.
	 * @param x Nodo de quien se quiere saber su color.
	 * @return Retorna true si es rojo, false de lo contrario.
	 */
	public boolean isRed(Node x) {
		if(x==null)
			return false;
		return x.color == RED;
	}


	public int size() {
		return size(root);
	}

	/**
	 * Retorna el tamano del nodo x
	 * @param x Nodo
	 * @return Tamano del nodo x
	 */
	public int size(Node x) {
		if(x == null) {
			return 0;
		}
		return x.size;
	}

	public boolean isEmpty() {
		return root==null;
	}

	public LinkedList<V> get(K key) {
		if (key == null) throw new IllegalArgumentException("La llave no puede ser null");
		return (LinkedList<V>) get(root, key);
	}

	/**
	 * Retorna los valores que estan en la lista
	 * @param nodo Nodo donde se ubican los valores
	 * @param key Llave 
	 * @return La lista encadenada de valores
	 */
	private Iterable<V> get(Node nodo,K key ){
		if (nodo == null) return null;
		int cmp = key.compareTo((K) nodo.key);
		if      (cmp < 0) return get(nodo.left, key); // llave de interes es menor a la llave del nodo. Busca por el sub arbol izquierdo. 
		else if (cmp > 0) return get(nodo.right, key);//llave de interes es mayor a la llave del nodo. Busca por el sub arbol derecho.
		else              return nodo.values;        // llave encontrada. RETORNA LISTA ENCADENADA DE VALORES.
	}


	public boolean contains(K key) {
		if(key == null) throw new IllegalArgumentException("La llave no puede ser null");
		return get(key) != null;
	}

	public void put(K key, V val) {	
		if (key == null) throw new IllegalArgumentException("La llave no puede ser null");
		if (val == null) {
			return;
		}

		root = put(root, key, val);
		root.color = BLACK;
	}

	private Node put(Node h, K key, V val) {
		if(h == null)
			return new Node(key, val, RED, 1);
		int cmp = key.compareTo(h.key);
		if 		(cmp < 0) h.left  = put(h.left,key,val); // si key parametro es menor, intenta por la izquierda.
		else if (cmp > 0) h.right = put(h.right,key,val); // si key parametro es mayor, intenta por la derecha.
		else 			  h.values.add(val);			  // si la key parametro ya esta en el arbol, agrega el valor a la lista de valores del nodo.
		
		// verificar y corregir la estructura
		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h); // si nodoActual.derecha quedo rojo y nodoActual.izquierda no quedo rojo
		if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h); // si nodoActual.izquierda quedo rojo pero nodoActual.izquieda.izquierda tambien
		if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);      //si nodoActual.izquieda quedo rojo y NodoActual.derecha tambien
		h.size = size(h.left) + size(h.right) + h.values.size();

		return h;
	}

	private Node rotateRight(Node h) {
        // assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + h.values.size();
        return x;
    }
    private Node rotateLeft(Node h) {
        // assert (h != null) && isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + h.values.size();
        return x;
    }
    private void flipColors(Node h) {
        // h must have opposite color of its two children
        // assert (h != null) && (h.left != null) && (h.right != null);
        // assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
        //    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
    
    public int height() {
		return height(root);
	}
	
    private int height(Node nodo) {
        if (nodo == null) return -1;
        return 1 + Math.max(height(nodo.left), height(nodo.right));
    }
    
	public int getHeight(K key) {
        if (key == null) throw new IllegalArgumentException("la llave no puede ser null");
        return  getHeight(root, key);
	}
	
	private int getHeight(Node nodo, K key) {
		if(nodo == null) return -1;
		int cmp = key.compareTo(nodo.key);
		if      (cmp < 0) return 1 + getHeight(nodo.left, key); // llave de interes es menor a la llave del nodo. Busca por el sub arbol izquierdo. 
        else if (cmp > 0) return 1 + getHeight(nodo.right, key);//llave de interes es mayor a la llave del nodo. Busca por el sub arbol derecho.
        else              return 0;        
	}
	
	public K min() {
		if (isEmpty()) return null;
        return min(root).key;
	}
	
	 private Node min(Node nodo) { 
	        if (nodo.left == null) return nodo; 
	        else return min(nodo.left); 
	    } 

	 
	public K max() {
		if (isEmpty()) return null;
        return max(root).key;
	}
	
    private Node max(Node nodo) {
        if (nodo.right == null) return nodo; 
        else return max(nodo.right); 
    } 

	// devuelve TODA la lista de llaves en orden INORDEN.
	public Iterable<K> keySet() {
		if (isEmpty()) return new LinkedList<K>(); // si no hay elementos en la tabla de simbolos retorna la lista vacia.
        return keysInRange(min(), max());
	}
		
	
	// devuelve una lista en orden ASCENDENTE (Inorden).
	public Iterable<K> keysInRange(K init, K end) {
        if (init == null) throw new IllegalArgumentException("el primer argumento para keysInRange() es null");
        if (end == null) throw new IllegalArgumentException("el segundo argumento para KeysInRange() es null");

        LinkedList<K> lista = new LinkedList<K>();
        keysInRange(root, lista, init, end);
        return lista;
	}
	
	 private void keysInRange(Node nodo, LinkedList<K> lista, K init, K end) { 
	        if (nodo == null) return; 
	        int cmpinit = init.compareTo(nodo.key); // comparacion entre el nodo actual y la llave del rango inferior. 
	        int cmpend = end.compareTo(nodo.key);  //  comparacion entre el nodo actual y la llave del rango superior. 
	        if (cmpinit < 0) keysInRange(nodo.left, lista, init, end); // si la key del nodo actual es mayor a la key  init entonces se mueve mas a la izquierda.
	        if (cmpinit <= 0 && cmpend >= 0) lista.add(nodo.key); // si la llave del nodo actual está adentro del rango, entonces la agrega
	        if (cmpend > 0) keysInRange(nodo.right, lista, init, end); // si la key del nodo actual es menor a la key end entonces se mueve mas a la derecha.
	    } 
	 
	// devuelve una lista en orden ASCENDENTE (Inorden).
	public Iterable<V> valuesInRange(K init, K end) {
		
     if (init == null) throw new IllegalArgumentException("el primer argumento para valuesInRange() es null");
     if (end == null) throw new IllegalArgumentException("el segundo argumento para valuesInRange() es null");

     LinkedList<V> lista = new LinkedList<V>();
     valuesInRange(root, lista, init, end);
     return lista;
	}
	
	 private void valuesInRange(Node nodo, LinkedList<V> lista, K init, K end) { 
	        if (nodo == null) return; 
	        int cmpinit = init.compareTo(nodo.key); // comparacion entre el nodo actual y la llave del rango inferior. 
	        int cmpend = end.compareTo(nodo.key);  //  comparacion entre el nodo actual y la llave del rango superior. 
	        if (cmpinit < 0) valuesInRange(nodo.left, lista, init, end); // si la key del nodo actual es mayor a la key  init entonces se mueve mas a la izquierda
	        if (cmpinit <= 0 && cmpend >= 0) {
	        	if(nodo.values!=null) {
		        	for(V val: nodo.values)
		        		lista.add(val); // si el valor del nodo actual está dentro del rango, entonces la agrega
	        	}
	        }
	        if (cmpend > 0) valuesInRange(nodo.right, lista, init, end); // si la key del nodo actual es menor a la key end entonces se mueve mas a la derecha.
	    }
	 
	 

}
