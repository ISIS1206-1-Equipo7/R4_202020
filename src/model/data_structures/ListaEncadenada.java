package model.data_structures;

import java.util.*;

public class ListaEncadenada<T extends Comparable<T>> implements Lista<T> {

	// ---------
	// ATRIBUTOS
	// ---------
	
	/**
	 * La cabeza (primer nodo) de la lista
	 */
	private Nodo<T> cabeza;
	
	/**
	 * La cola (último nodo) de la lista
	 */
	private Nodo<T> cola;
	
	/**
	 * La cola (último nodo) de la lista
	 */
	private int tamano;
	
	// -----------
	// CONSTRUCTOR
	// -----------
	
	/**
	 * Crea una nueva lista encadenada
	 */
	public ListaEncadenada() {
		this.cabeza = null;
		this.cola = this.cabeza;
		this.tamano = 0;
	}
	
	// -------
	// METODOS
	// -------
	
	public void agregarInicio(T elemento) {
		Nodo<T> nuevaCabeza = new Nodo<>( elemento );
		if (!this.isEmpty()) {
			nuevaCabeza.setSiguiente(this.cabeza);
		}
		else {
			this.cola = nuevaCabeza;
		}
		this.cabeza = nuevaCabeza;
		this.tamano ++;
	}

	public void agregarFinal(T elemento) {
		Nodo<T> nuevoNodo = new Nodo<>( elemento );
		if (!this.isEmpty()) {
			this.cola.setSiguiente(nuevoNodo);
		}
		else {
			this.cabeza = nuevoNodo;
		}
		this.cola = nuevoNodo;
		this.tamano ++;
	}

	public void insertarElemento(T elemento, int posicion) {
		Nodo<T> nuevoNodo = new Nodo<>( elemento );
		boolean insertado = false;
		if (!this.isEmpty()) {
			if (posicion == 1) {
				this.agregarInicio(elemento);
				insertado = true;
			}
			else {
				int c = 2;
				Nodo<T> nodoActual = this.cabeza;
				while (nodoActual.tieneSiguiente()) {
					if (c == posicion) {
						Nodo<T> siguiente = nodoActual.darSiguiente();
						nodoActual.setSiguiente(nuevoNodo);
						nuevoNodo.setSiguiente(siguiente);
						insertado = true;
						break;
					}
					c++;
					nodoActual = nodoActual.darSiguiente();
				}
			}
		}
		else {
			if (posicion == 1) {
				this.agregarInicio(elemento);
				insertado = true;
			}
		}
		
		// ¿Que se hace cuando se ingresa una pos invalida?
		if (insertado) {
			this.tamano ++;
		} else {
			System.out.println("Posicion inválida");
		}
	}

	public T eliminarPrimero() {
		T elementoEliminado = null;
		if (!this.isEmpty()) {
			elementoEliminado = this.cabeza.darElemento();
			Nodo<T> nuevaCabeza = this.cabeza.darSiguiente();
			this.cabeza = nuevaCabeza;
			this.tamano --;
		}
		if (this.isEmpty()) {
			this.cola = null;
		}
		return elementoEliminado;
	}

	public T eliminarUltimo() {
		T elementoEliminado = null;
		if (!this.isEmpty()) {
			if (!this.cabeza.tieneSiguiente()) {
				elementoEliminado = this.cabeza.darElemento();
				this.cabeza = null;
				this.cola = this.cabeza;
			}
			else {
				Nodo<T> nodoActual = this.cabeza;
				while (nodoActual.darSiguiente().tieneSiguiente()){
					nodoActual = nodoActual.darSiguiente();
				}
				elementoEliminado = nodoActual.darSiguiente().darElemento();
				nodoActual.saltarSiguiente();
				this.cola = nodoActual;
			}
			this.tamano --;
		}
		return elementoEliminado;
	}

	public T eliminarElemento(int posicion) {
		T elemEliminado = null;
		int tamano = this.darTamano();
		if (!this.isEmpty()) {
			if (posicion == 1) {
				if (tamano == 1) {
					this.cola = null;
				}
				elemEliminado = this.cabeza.darElemento();
				this.cabeza = this.cabeza.darSiguiente();
			}
			else {
				int c = 2;
				Nodo<T> nodoActual = this.cabeza;
				while (nodoActual.tieneSiguiente()) {
					if (c == posicion) {
						Nodo<T> nodoEliminado = nodoActual.darSiguiente();
						elemEliminado = nodoEliminado.darElemento();
						nodoActual.saltarSiguiente();
						break;
					}
					c ++;
					nodoActual = nodoActual.darSiguiente();
				}
			}
		}
		
		if (elemEliminado != null) {
			this.tamano --;
		}
		
		return elemEliminado;
	}

	public T darPrimerElemento() {
		T primerElemento = null;
		if (!this.isEmpty()) {
			primerElemento = this.cabeza.darElemento();
		}
		
		return primerElemento;
	}

	public T darUltimoElemento() {
		T ultimoElemento = null;
		if (!this.isEmpty()) {
			ultimoElemento = this.cola.darElemento();
		}
		
		return ultimoElemento;
	}

	public T darElemento(int posicion) {
		T elemento = null;
		if (!this.isEmpty()) {
			int c = 1;
			Nodo<T> nodoActual = this.cabeza;
			while (nodoActual != null) {
				if (c == posicion) {
					elemento = nodoActual.darElemento();
					break;
				}
				c ++;
				nodoActual = nodoActual.darSiguiente();
			}
		}
		
		return elemento;
	}

	public int darTamano() {
		return this.tamano;
	}

	public boolean isEmpty() {
		return this.tamano == 0;
	}

	public int isPresent(T elemento) {
		int pos = -1;
		boolean encontrado = false;
		if (!this.isEmpty()) {
			pos = 0;
			Nodo<T> nodoActual = this.cabeza;
			while (nodoActual != null) {
				pos ++;
				if (nodoActual.darElemento().compareTo(elemento) == 0) {
					encontrado = true;
					break;
				}
				nodoActual = nodoActual.darSiguiente();
			}
			if (!encontrado) {
				pos = -1;
			}
		}
		
		return pos;
	}

	public void intercambiar(int pos1, int pos2) {
		if (pos1 != pos2) {			
			T elem1 = this.darElemento(pos1);
			T elem2 = this.darElemento(pos2);
			this.cambiarInfo(pos1, elem2);
			this.cambiarInfo(pos2, elem1);
		}
	}

	public void cambiarInfo(int pos, T elem) {
		if (!this.isEmpty()) {
			int c = 1;
			Nodo<T> nodoActual = this.cabeza;
			while (nodoActual != null) {
				if (c == pos) {
					nodoActual.setElemento(elem);
					break;
				}
				c ++;
				nodoActual = nodoActual.darSiguiente();
			}
		}
	}
}
