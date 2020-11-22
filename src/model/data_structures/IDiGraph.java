package model.data_structures;

import java.util.LinkedList;

public interface IDiGraph <K extends Comparable<K>, V>
{
	
	/**
	 * Retorna true si el vertice (Nodo) con id suministrado esta en el grafo
	 * @param id
	 * @return True si el vertice (Nodo) está en el grafo False de lo contrario.
	 */
	boolean containsVertex(K id);
	
	
	/**
	 * Devuelve el numero de vertices en el grafo.
	 * @return Int numero de vertices en el grafo.
	 */
	int numVertices();
	
	/**
	 * Devuelve el numero de arcos en el grafo
	 * @return Int numero de arcos en el grafo.
	 */
	int numEdges();
	
	/**
	 * Annade un vertice al grafo con su identificador y valor.
	 * @param id identificador del vertice (Nodo) 
	 * @param value Valor asociado a ese vertice.
	 */
	void insertVertex(K id, V value);
	
	/**
	 * Annade un arco dirigido pesado entre el vertice source y dest, con peso weight (tiempo). 
	 * Si el arco YA existe se modifica su peso.
	 * @param source
	 * @param dest
	 * @param weight
	 */
	void addEdge(K source, K dest, double weight, boolean reverse);
	
	/**
	 * Retorna el vertice a partir de su identificador unico (Id de la estacion).
	 * @param id
	 * @return Vertex (nodo) especificado por parametro.
	 */
	Vertex<K,V> getVertex(K id);
	
	/**
	 * Retorna el arco entre los vertices idS (id source) y idD (id destiny) si existe. Retorna null si no existe.
	 * @param idS
	 * @param idD
	 * @return Edge (arco) entre los vertices especificados por parametro. Null si no existe.
	 */
	Edge<K,V> getEdge(K idS, K idD);
	
	/**
	 * Devuelve una lista de arcos adyacentes (salientes) al vertice con id dado.
	 * @param id
	 * @return LinkedList de arcos adyacentes.
	 */
	LinkedList<Edge<K,V>> adjacentEdges(K id);
	
	/**
	 * Devuelve una lista de vertices adyacentes (salientes) al vertice con id
	 * @param id
	 * @return LinkedList de vertices adyacentes.
	 */
	LinkedList<Vertex<K,V>> adjacentVertex(K id);
	
	/**
	 * Devuelve el grado de entrada del vertice vertex (numero de arcos entrantes)
	 * @param vertex
	 * @return Int numero de arcos entrantes al vertice pasado por parametro.
	 */
	int indegree(K vertex);
	
	/**
	 * Devuelve el grado de salida del vertice vertex (numero de arcos salientes)
	 * @param vertex
	 * @return Int numero de arcos salientes al vertice pasado por parametro.
	 */
	int outdegree(K vertex);
	
	/**
	 * Devuelve una lista con todos los arcos del grafo.
	 * @return LinkedList con todos los Edges (arcos) del grafo.
	 */
	LinkedList<Edge<K,V>> edges();
	
	/**
	 * Devuelve una lista con los vértices del grafo
	 * @return Lista con todos los vertices del grafo.
	 */
	LinkedList<Vertex<K,V>> vertices();
	
}




