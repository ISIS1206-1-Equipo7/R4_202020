package model.data_structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class DiGraph <K extends Comparable<K>, V> implements IDiGraph< K, V>
{
	
	
	//--------------------
	//ATRIBUTOS
	//--------------------

    /**
     * estructura del grafo
     */
    private ArrayList<Vertex<K,V>> grafo;
    
    /**
     * capacidad maxima del grafo.
     */
    private int initialSize;
    
    /**
     * lista de vertices (auxiliar para el metodo vertices).
     */
    private LinkedList<Vertex<K,V>> listaVertices;
    
    /**
     * lista de arcos (auxiliar para el metodo edges).
     */
    private LinkedList<Edge<K,V>> listaArcos;
    
	//--------------------
	//CONSTRUCTOR
	//--------------------
    
    public DiGraph (int size) {
    	
    	grafo = new ArrayList<Vertex<K,V>>(size);

    	for( int i=0; i < size; i++) {
    		grafo.add(null);
    	}
    	initialSize = size;
    	listaVertices = new LinkedList<Vertex<K,V>>();
    	listaArcos = new LinkedList<Edge<K,V>>();
    }
    
	//--------------------
	//METODOS
	//--------------------
	@Override
	public boolean containsVertex(K id) {
		
		int pos = Integer.parseInt((String) id);
		return grafo.get(pos)!=null;
	
	}

	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return listaVertices.size();
	}

	@Override
	public int numEdges() {
		
		return listaArcos.size();
		
	}

	@Override
	public void insertVertex(K id, V value) {
		
		Vertex<K,V> nuevo = new Vertex<K,V>(id, value);
		int pos = Integer.parseInt((String) id);
		
		if(grafo.get(pos)!= null)
			throw new IllegalArgumentException("el vertice que se intenta agregar ya existe en la estructura.");
		
		grafo.set(pos, nuevo); // agrega a la lista en la posicion del Id, automticamente se actualiza el numVertices.
		listaVertices.add(nuevo); // guarda el vertice en una lista con todos los vertices.
	}

	@Override
	public void addEdge(K source, K dest, double weight) {
		
		int inicio = Integer.parseInt((String) source);
		int destino = Integer.parseInt((String) dest);

		if(inicio <0 || destino <0 || inicio >= initialSize || destino >= initialSize ) // valida entradas de parametros
			throw new  IndexOutOfBoundsException("Id de inicio o de fin tienen que estar dentro de los limites.");
		
		Vertex<K,V> nodoSource = grafo.get(inicio);
		Vertex<K,V> nodoDestino = grafo.get(destino);
		
		if(nodoSource == null || nodoDestino == null)
			throw new  IllegalArgumentException(" alguno de los vertices del arco que se intenta crear es null");
		
		Edge<K,V> nuevoArco = new Edge<K,V>(nodoSource, nodoDestino, weight); // crea el objeto arco
		
		nodoSource.addEdge(nuevoArco);// pasa el objeto arco al nodo origen para que se guarde la asociacion entre ambos vertices.
		nodoDestino.addInDegree();	 // suma al numero de arcos entrantes al nodoDestino.
		
		listaArcos.add(nuevoArco);	// agrega el nuevo arco a una lista con todos los arcos del grafo.
		
	}

	@Override
	public Vertex<K, V> getVertex(K id) {
		
		int pos = Integer.parseInt((String) id);
		if(pos < 0 || pos >= initialSize)
			throw new IllegalArgumentException("el id del vertice que se busca esta fuera de los limites de grafo");
		
		Vertex<K,V> vertex;
		if((vertex = grafo.get(pos))==null)
			throw new IllegalArgumentException("el arco que se busca no existe");
		
		return vertex;
	}

	@Override
	public Edge<K, V> getEdge(K idS, K idD) {
		
		
		int posSource = Integer.parseInt((String) idS);
		int posDestiny = Integer.parseInt((String) idD);
		
		if(posSource <0 || posDestiny <0 || posSource >= initialSize || posDestiny >= initialSize )
			throw new  IllegalArgumentException("Id de inicio o de fin tienen que estar dentro de los limites.");
		
		
		if(grafo.get(posSource)==null || grafo.get(posDestiny)==null)
			throw new IllegalArgumentException("el arco que se busca no existe");
			
		Vertex<K,V> source = grafo.get(posSource); // guarda el vertice source en una variable
									
		return source.getEdge(idD);				// busca y retorna el arco entre este vertice y el idD. 
		
	}

	@Override
	public LinkedList<Edge<K, V>> adjacentEdges(K id) {
		
		int pos = Integer.parseInt((String)id);
		Vertex<K,V> vertex;
		
		if((vertex = grafo.get(pos))==null)
			throw new IllegalArgumentException("el vertice de interes no existe");
		
		return vertex.edges();
	}

	@Override
	public LinkedList<Vertex<K, V>> adjacentVertex(K id) {
		
		int pos = Integer.parseInt((String)id);
		
		Vertex<K,V> vertex;
		
		if((vertex = grafo.get(pos))==null)
			throw new IllegalArgumentException("el vertice de interes no existe");
		
		return vertex.vertices();
	}

	@Override
	public int indegree(K idVertex) {
		int pos = Integer.parseInt((String) idVertex);
		return grafo.get(pos).indegree();
	}

	@Override
	public int outdegree(K idVertex) {
		int pos = Integer.parseInt((String) idVertex);
		return grafo.get(pos).outdegree();
	}

	@Override
	public LinkedList<Edge<K, V>> edges() {

		return listaArcos;
	}

	@Override
	public LinkedList<Vertex<K, V>> vertices() {
		
		return listaVertices;
		
	}
}
