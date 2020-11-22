package model.data_structures;

public interface Lista <T extends Comparable<T>> {

	/**
	 * Agrega un elemento al inicio de la lista.
	 * @param elemento Elemento a agregar.
	 */
	void agregarInicio(T elemento);

	/**
	 * Agrega un elemento al final de la lista.
	 * @param elemento Elemento a agregar
	 */
	void agregarFinal(T elemento);

	/**
	 * Inserta un elemento en una posicion valida.
	 * OJO: Las posicones de datos en esta estructura de datos se van
	 * a nombrar a partir de la posicion 1, asi el primer elemento tiene
	 * la posicion 1 aunque en su representacion interna se encuentre en 
	 * la posicion 0.
	 * @param elemento Elemento a agregar.
	 * @param posicion Posicion del elemento.
	 */
	void insertarElemento(T elemento, int posicion);

	/**
	 * Elimina el elemento ubicado al inicio.
	 * @return Elemento que se ha eliminado.
	 */
	Comparable<T> eliminarPrimero();

	/**
	 * Elimina el elemento ubicado al final.
	 * @return Elemento que se ha eliminado.
	 */
	Comparable<T> eliminarUltimo();

	/**
	 * Elimina el elemento de una posici�n v�lida.
	 * @param posicion Posicion del elemento a eliminar.
	 * @return Elemento eliminado.
	 */
	T eliminarElemento(int posicion);

	/**
	 * Retorna el primer elemento.
	 * @return Elemento en la primera posci�n.
	 */
	T darPrimerElemento();

	/**
	 * Retorna el �ltimo elemento.
	 * @return Elemento en la �ltima posici�n.
	 */
	T darUltimoElemento();

	/**
	 * Retorna el elemento en una posici�n v�lida. La posici�n del
	 * primer elemento es 1, la del segundo es 2 y as� sucesivamente.
	 * @param posicion Ubicaci�n del elemento a buscar.
	 * @return Elemento en la posici�n dada por par�metro.
	 */
	T darElemento(int posicion);

	/**
	 * Retorna el n�mero de datos de la lista.
	 * @return N�mero entero de datos en la lista.
	 */
	int darTamano();

	/**
	 * Retorna true si la lista no tiene datos.
	 * False en caso contrario.
	 * @return True, no hay datos. False, hay datos.
	 */
	boolean isEmpty();

	/**
	 * Retorna la posici�n de un elemento. Si no se encuentra, debe retorna -1.
	 * @param elemento Elemento a buscar.
	 * @return Posicion donde se encuentra el elemento, -1 si no se encuentra en la lista.
	 */
	int isPresent(T elemento);

	/**
	 * Intercambia la informacion de los elementos en dos posicones validas.
	 * @param pos1 posicion del primer elemento.
	 * @param pos2 posicion del segundo elemento.
	 */
	void intercambiar(int pos1, int pos2);

	/**
	 * Actualiza el elemento en una posici�n v�lida.
	 * @param pos 
	 * @param elem
	 */
	void cambiarInfo(int pos, T elem);
}
