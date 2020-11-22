package model.data_structures;

import java.util.LinkedList;

public class Vertex <K extends Comparable<K>, V>
{

	//---------------------
	// ATRIBUTOS
	//---------------------
	
	private LinkedList<Edge<K,V>> edges;
	private LinkedList<Vertex<K,V>> vertices;
	
	private int inDegree;
	private K id;
	private V value;
	private boolean isMarked;
	
	//auxiliares para el reverso del grafo
	private LinkedList<Edge<K,V>> reverseEdges;
	private LinkedList<Vertex<K,V>> reverseVertices;
	private int reverseInDegree;
	
	
	//---------------------
	// CONSTRUCTOR
	//---------------------
	/**
	 * Crea un vertice con identificador unico y valor (informacion asociada), el vertice inicia desmarcado
	 * @param id
	 * @param value
	 */
	public Vertex(K id, V value) {
		
		this.inDegree =0;
		this.reverseInDegree =0; // para el reverso.
		this.id = id;
		this.value = value;
		this.isMarked = false;
		
		// inicializa listas:
		this.edges = new LinkedList<Edge<K,V>>();
		this.vertices  = new LinkedList<Vertex<K,V>>();
		
		// lista para el reverso:
		this.reverseEdges = new LinkedList<Edge<K,V>>();
		this.reverseVertices = new LinkedList<Vertex<K,V>>();
		
		
	}
	
	
	//---------------------
	// METODOS
	//---------------------
	/**
	 * devuelve el identificador del vertice.
	 * @return K id del vertice (Nodo)
	 */
	public K getId()
	{
		return id;
	}
	
	/**
	 * Devuelve el valor asociado al vértice
	 * @return V valor asociado al vertice (Nodo)
	 */
	public V getInfo() {
		
		return value;
	}
	
	/**
	 * Retorna si el vertice esta marcado o no
	 * @return True si el vertice esta marcado, false si no.
	 */
	public boolean getMark() {
		return isMarked;
		
	}
	/**
	 * Agrega un arco adyacente al vertice
	 * @param edge
	 */
	public void addEdge(Edge<K,V> edge) {
		
		// verifica que el arco que se intenta agregar no exista.
		for( Edge<K,V> arco : edges) {
			
			if(arco.compareTo(edge)== 0) {
				throw new IllegalArgumentException("el arco que se intenta agregar ya existe");
			}
		}
		
		if(edge.getSource().getId().compareTo(this.id)!=0)
			throw new IllegalArgumentException("el arco que se intenta agregar no incluye este vertice");

		
		edges.add(edge); // agrega el arco a la lista de arcos salientes de este vertice
		Vertex<K,V> adjacente = edge.getDest();
		vertices.add(adjacente); // agrega el vertice destino a la lista de vertices adyacentes a este.
		
		
	}
	
	/**
	 * Marca el vertice
	 */
	public void mark() {
		isMarked = true;
	}
	
	/**
	 * Desmarca el vertice
	 */
	public void unmark() {
		isMarked = false;
	}
	
	/**
	 * Retorna el numero de arcos (salientes) del vertice,
	 * @return Int numero de arcos salientes (que salen)  del vertice.
	 */
	public int outdegree() {
		return edges.size();
	}
	
	/**
	 * agrega un grado mas para representar un nuevo arco entrante a este nodo.
	 */
	public void addInDegree() {
		 
		inDegree ++;
	}
	
	/**
	 * Retorna el numero de arcos (entrantes) del vertice
	 * @return Int numero de arcos entrantes del vertice.
	 */
	public int indegree() {
		return inDegree;
	}
	
	/**
	 * Retorna el arco entre este vertice y el vertice pasado por parametro (si existe). Retorna null si no existe.
	 * @param vertex
	 * @return El arco entre este vertice y el que se pasa por parametro.
	 */
	public Edge<K,V> getEdge(K idVertex){
		
		
		if(!edges.isEmpty()) {
			
			for(Edge<K,V> edge : edges) {
				
				if(edge.getDest().getId().equals(idVertex))
					return edge;
					
			}
			
		}
		return null;
	}
	
	/**
	 * Retorna una lista con sus vertices adyacentes 
	 * @return LinkedList con los vertices adyacentes
	 */
	public LinkedList<Vertex<K,V>> vertices(){
		return vertices;
		
	}
	/**
	 * Retorna una lista con sus arcos adyacentes (salientes)
	 * @return LinkedList con los arcos adyacentes (salientes) de este vertice (Nodo)
	 */
	public LinkedList<Edge<K,V>> edges(){
		return edges;
		
	}
	
	//---------------
	// metodos para calcualar el reverso del grafo
	//---------------
	
	/**
	 *  agrega un grado al reverso de este vertice para el reverso del grafo
	 */
	public void addReverseInDegree() {
		
		reverseInDegree ++;
	}
	
	/**
	 * Agrega un arco adyacente al vertice reverso.
	 * @param edge
	 */
	public void reverseAddEdge(Edge<K,V> edge){
		
		// verifica que el arco que se intenta agregar no exista.
		for( Edge<K,V> arco : reverseEdges) {
			
			if(arco.compareTo(edge)== 0) {
				throw new IllegalArgumentException("el arco que se intenta agregar ya existe");
			}
		}
		
		if(edge.getSource().getId().compareTo(this.id)!=0)
			throw new IllegalArgumentException("el arco que se intenta agregar no incluye este vertice");

		
		reverseEdges.add(edge); // agrega el arco a la lista de arcos salientes de este vertice
		Vertex<K,V> adjacente = edge.getDest();
		reverseVertices.add(adjacente); // agrega el vertice destino a la lista de vertices adyacentes a este.
		
	}
	
	public int reverseOutDegree () {
		return reverseEdges.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public int reverseInDegree() {
		return reverseInDegree;
	}
	
	
	/**
	 * Retorna el arco entre este vertice y el vertice pasado por parametro (si existe). Retorna null si no existe.
	 * @param vertex
	 * @return El arco entre este vertice y el que se pasa por parametro.
	 */
	public Edge<K,V> getReverseEdge(K idVertex){
		
		
		if(!reverseEdges.isEmpty()) {
			
			for(Edge<K,V> edge : reverseEdges) {
				
				if(edge.getDest().getId().equals(idVertex))
					return edge;
					
			}
			
		}
		return null;
	}
	
	/**
	 * Retorna una lista con sus vertices REVERSOS adyacentes 
	 * @return LinkedList con los vertices adyacentes REVERSOS.
	 */
	public LinkedList<Vertex<K,V>> reverseVertices(){
		return reverseVertices;
		
	}
	
	/**
	 * Retorna una lista con sus arcos adyacentes REVERSOS (salientes)
	 * @return LinkedList con los arcos adyacentes REVERSOS (salientes) de este vertice (Nodo)
	 */
	public LinkedList<Edge<K,V>> reverseEdges(){
		return reverseEdges;
		
	}
	

}
