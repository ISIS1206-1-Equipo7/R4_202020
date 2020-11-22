package model.data_structures;

import java.util.LinkedList;
import java.util.NoSuchElementException;


public class BinarySearchTree<K extends Comparable<K>,V> implements TablaSimbolosOrdenada<K,V>
{
	private Node root;             // raiz del BST

	// clase interna privada para definir los nodos.
    private class Node
    {
        private K key;           
        private LinkedList<V> values; // La lista de valores asociados (Accidentes)        
        private Node left, right;     // sub arbol izquierdo y derecho.
        private int size;             // numero de nodos en el subarbol
        
        public Node(K key, V val, int size) {
            this.key = key;
            this.values = new LinkedList<V>();
            this.values.add(val);
            this.size = size;
            
        }
    }
	
    /**
     * Inicializa el árbol binario de busqueda vacio.
     */
    public BinarySearchTree() {
    	
    }
    
	public int size() {

		return size(root);
	}
	
	// retorna el numero de parejas key-value para las cuales su raiz es el nodo que entra por parametro.
    private int size(Node nodoInteres) {
        if (nodoInteres == null) return 0;
        else return nodoInteres.size;
    }

	
	public boolean isEmpty() {
	
		return size()==0;
	}

	
	public LinkedList<V> get(K key) {
		return (LinkedList<V>) get(root, key);
	}
	
	private Iterable<V> get(Node nodo, K key) {
        if (key == null) throw new IllegalArgumentException("la llave no puede ser null");
        if (nodo == null) return null;
        int cmp = key.compareTo((K) nodo.key);
        if      (cmp < 0) return get(nodo.left, key); // llave de interes es menor a la llave del nodo. Busca por el sub arbol izquierdo. 
        else if (cmp > 0) return get(nodo.right, key);//llave de interes es mayor a la llave del nodo. Busca por el sub arbol derecho.
        else              return nodo.values;        // llave encontrada. RETORNA LISTA ENCADENADA DE VALORES.
    }

	
	public boolean contains(K key) {
		
		if (key == null) throw new IllegalArgumentException(" la key no puede ser null");
        return get(key) != null;
	}

	
	public void put(K key, V val) {
		if (key == null) throw new IllegalArgumentException("la key no puede ser null");
        if (val == null) {
            return;
        }
        root = put(root, key, val);
		
	}
	
	 private Node put(Node x, K key, V val) {
	        if (x == null) return new Node(key, val, 1); 
	        int cmp = key.compareTo((K) x.key);
	        if      (cmp < 0) x.left  = put(x.left,  key, val);
	        else if (cmp > 0) x.right = put(x.right, key, val);
	        else {
	        	x.values.add(val);
	        }
	        x.size = x.values.size() + size(x.left) + size(x.right);
	        return x;
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
		        	lista.add(val); // si la llave del nodo actual está dentro del rango, entonces la agrega
	        	}
	        }
	        if (cmpend > 0) valuesInRange(nodo.right, lista, init, end); // si la key del nodo actual es menor a la key end entonces se mueve mas a la derecha.
	    } 
	 
}
