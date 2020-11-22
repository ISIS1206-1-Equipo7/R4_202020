package model.data_structures;

public class Edge <K extends Comparable<K>, V>
{
	//---------------------
	// ATRIBUTOS
	//---------------------
	private Vertex<K,V> source;
	private Vertex<K,V> dest;
	private double weight;
	
	// ESTOS SON  ATRIBUTOS QUE NECESITEMOS PARA CALCULAR EL PESO:
	private double cumWeight;
	private int repetitions;
	
	//---------------------
	// CONSTRUCTOR
	//---------------------
	/**
	 * Crea el arco desde el vertice source al vertice dest con peso weight
	 * @param source
	 * @param dest
	 * @param weight
	 */
	public Edge(Vertex<K,V> pSource, Vertex<K,V> pDest, double pWeight) {
		this.source = pSource;
		this.dest = pDest;
		this.cumWeight = pWeight;
		this.repetitions = 1;
		this.weight = cumWeight/repetitions;
	}
	
	
	//---------------------
	// METODOS
	//---------------------
	/**
	 * Devuelve el vertice origen
	 * @return Vertex (Nodo) de origen.
	 */
	public Vertex<K,V> getSource(){
		return source;
	}
	
	/**
	 * Devuelve el vertice destino.
	 * @return vertice de destino.
	 */
	public Vertex<K,V> getDest(){
		return dest;
		
	}
	
	/**
	 * Devuelve el peso promediado del arco 
	 * @return peso (tiempo) del arco.
	 */
	public double weight() {
		return weight;
		
	}
	
	/**
	 * Modifica el peso del arco.
	 * @param weight
	 */
	public void setWeight(double pWeight) {
		
		cumWeight = cumWeight + pWeight;	// suma de todos los pesos acumulados hasta ahora.
		repetitions ++;						// suma de todas las veces que se ha ajustado el peso para este vertice
		
		weight = cumWeight/repetitions;  // saca el promedio del tiempo hasta el momento.
		
	}
	
	/**
	 *  compara dos arcos para saber si conectan a la misma pareja de vertices.
	 * @param that
	 * @return int 0 si los dos arcos son iguales, 1 de lo contrario.
	 */
	public int compareTo(Edge<K,V> that) {
		
		if(this.getSource().getId().equals(that.getSource().getId()) && this.getDest().getId().equals(that.getDest().getId()))
			return 0;
		else {
			return 1;
		}
	}
	
}
