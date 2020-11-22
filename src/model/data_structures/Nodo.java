package model.data_structures;

public class Nodo<T> {
	
	// ---------
	// ATRIBUTOS
	// ---------
	
	/**
	 * Elemento del nodo
	 */
	private T elemento;
	
	/**
	 * Nodo siguiente del nodo actual
	 */
	private Nodo siguiente;
	
	
	// -----------
	// CONSTRUCTOR
	// -----------
	
	/**
	 * Crea un nuevo nodo
	 * @param elemento, elemento del nodo
	 */
	public Nodo(T elemento) {
		this.elemento = elemento;
		this.siguiente = null;
	}
	
	// -------
	// METODOS
	// -------
	
	/**
	 * Elimina el nodo siguiente del nodo actual
	 * @pre Se debe verificar que el nodo siguiente exista. siguiente != null
	 */
	public void saltarSiguiente() {
		Nodo<T> nuevoSiguiente = this.darSiguiente().darSiguiente();
		this.setSiguiente(nuevoSiguiente);
	}
	
	/**
	 * Verifica si el nodo actual apunta a un nodo
	 * @return true si tiene, false si no
	 */
	public boolean tieneSiguiente() {
		return this.siguiente != null ? true : false;
	}
	
	// -----------------
	// GETTERS & SETTERS
	// -----------------
	
	/**
	 * Retorna el elementod del nodo
	 * @return el elemento del nodo
	 */
	public T darElemento() {
		return this.elemento;
	}
	
	/**
	 * Actualiza el elemento del nodo
	 * @param elemento, nuevo elemento del nodo
	 */
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
	
	/**
	 * Retorna el siguiente nodo del nodo actual
	 * @return el nodo siguiente del nodo actual
	 */
	public Nodo<T> darSiguiente() {
		return this.siguiente;
	}
	
	/**
	 * Actualiza el nodo siguiente del nodo actual
	 * @param siguiente, el nodo siguiente
	 */
	public void setSiguiente(Nodo<T> siguiente) {
		this.siguiente = siguiente;
	}	
}
