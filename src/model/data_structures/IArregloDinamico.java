package model.data_structures;

public interface IArregloDinamico <T extends Comparable<T>>
{	
	/**
	 * Retornar el numero de elementos maximo en el arreglo
	 * @return
	 */
	int darCapacidad( );

	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return
	 */
	int darTamano( );
	
	/**
	 * retorna el primer elemento
	 * @return elemento
	 */
	T darPrimerElemento();
	
	/**
	 *retorna el ultimo elemento
	 * @return elemento
	 */
	T darUltimoElemento();
	
	/**
	 * Retornar el elemento en la posicion pos
	 * @param pos posicion de consulta (empieza en 1)
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	T darElemento( int pos );
	
	/**
     * Agrega un elemento al final del arreglo
     * @param elemento a agregar.
     */
	 void agregarInicio(T dato);
	
	/**
	 * Agregar un dato de forma compacta (en la primera casilla disponible) 
	 * Caso Especial: Si el arreglo esta lleno debe aumentarse su capacidad, agregar el nuevo dato y deben quedar multiples casillas disponibles para futuros nuevos datos.
	 * @param dato nuevo elemento
	 */
	 void agregarFinal( T dato );
		
	/**
	 * Buscar un dato en el arreglo.
	 * @param dato Objeto de busqueda en el arreglo
	 * @return elemento encontrado en el arreglo (si existe). null si no se encontro el dato.
	 */
	T buscar(T dato);
	
	/**
	 * Eliminar un dato del arreglo dada una posición valida.
	 * Los datos restantes deben quedar "compactos" desde la posicion 0.
	 * este metodo sirve para eliminar al inicio, al final o en cualquier posición del arreglo.
	 * @param posicion valida
	 * @return dato eliminado
	 */
	T eliminarElemento( int pos );
	
	/**
	 * elimina el primer elemento.
	 * @return el elemento eliminado
	 */
	Comparable<T> eliminarPrimero();
	 /**
	  * elimina el ultimo elemento.
	  * @return el elemento eliminado.
	  */
	Comparable<T> eliminarUltimo();
	
	/**
     * Se encarga de expandir el arreglo si este se llena
     */
	 void expandirArreglo();
	
	/**
	 * inserta un elemento en una posicion valida del arreglo. 
	 * Las posiciones válidas son posiciones donde ya hay un elemento en el arreglo. 
	 * La posición del primer elemento es 1, la del segundo es 2 y así sucesivamente.
	 * @param elemento
	 * @param pos (empieza en 1)
	 */
	 void insertarElemento(T elemento, int pos);
	
	/**
	 * Retorna true si el arreglo No tiene datos. false en caso contrario.
	 * @return True o False
	 */
	 boolean isEmpty();

	/**
	 * retorna la posicion valida (pos) de un elemento.
	 * pos va de 1 a tamanoAct.
	 * si el elemento no existe retorna -1
	 * @return pos
	 */
	 int isPresent(T elemento); 
	
	/**
	 * Intercambia la información de los elementos en dos posiciones válidas.
	 * Las posiciones de los datos en esta estructura de datos se van a nombrar a partir de la posición 1.
	 * @param pos1
	 * @param pos2
	 */
	 void intercambiar(int pos1, int pos2);
	/**
	 * Actualiza el elemento en una posición válida.
	 * @param pos1 (empieza en 1)
	 * @param elemento
	 */
	 void cambiarInfo(int pos1, T elemento);
	

}
