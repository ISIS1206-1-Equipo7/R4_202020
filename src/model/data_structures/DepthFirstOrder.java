package model.data_structures;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import model.logic.Station;

public class DepthFirstOrder <K extends Comparable<K>, V> {

	//--------------
	// ATRIBUTOS
	//--------------
	
	 private int[] pre;                 // pre[v]    = preorder  number of v
	 private int[] post;                // post[v]   = postorder number of v
	 private LinkedList<Vertex<K,V>> preorder;   // vertices in preorder
	 private LinkedList<Vertex<K,V>> postorder;  // vertices in postorder
	 private int preCounter;            // counter or preorder numbering
	 private int postCounter;           // counter for postorder numbering


	 //------------
	 // CONSTRUCTOR
	 //------------

	 /**
	  * Determines a depth-first order for the  edge-weighted digraph {@code G}.
	  * @param G the edge-weighted digraph.
	  */
	 public DepthFirstOrder(DiGraph<K,V> G) {
		 pre    = new int[G.initialSize];
		 post   = new int[G.initialSize];
		 postorder = new LinkedList<Vertex<K,V>>();
		 preorder  = new LinkedList<Vertex<K,V>>();

//	        marked    = new boolean[G.V()];
//	        for (int v = 0; v < G.V(); v++)
//	            if (!marked[v]) dfs(G, v);
//		 
		 for(Vertex<K,V> vertex : G.vertices()) {
			  
			 if (vertex.getMark()==false) dfs(G, vertex);
		 }
	 }


	 // run DFS in edge-weighted digraph G from vertex v and compute preorder/postorder
	 private void dfs(DiGraph<K,V> G, Vertex<K,V> v) {
		 
		 v.mark(); // marca el vertice como visitado
		 
		 int posv = Integer.parseInt((String) v.getId());
		 pre[posv] = preCounter++;
		 preorder.add(v);	//enqueue el vertice en la LinkedList de preorden
		 
		 for (Edge<K,V> edge : G.adjacentEdges(v.getId())) {
			 Vertex<K,V> w = edge.getDest();
			 if (w.getMark()==false) {
				 dfs(G, w);
			 }
		 }
		 postorder.add(v);  //enqueue el vertice en la LinkedList de postorden
		 post[posv] = postCounter++;
	 }


	 /**
	  * Returns the preorder number of vertex {@code v}.
	  * @param  v the vertex
	  * @return the preorder number of vertex {@code v}
	  * @throws IllegalArgumentException unless {@code 1 < v < V}
	  */
	 public int pre(Vertex<K,V> v) {
		 
		 int pos = Integer.parseInt((String) v.getId());
		 if(pos<0 || pos> pre.length) {
			 throw new IllegalArgumentException("el vertice al que se desea consultar el preorden number no tiene un id valido");
		 }
		 return pre[pos];
	 }

	 
	 /**
	  * Returns the postorder number of vertex {@code v}.
	  * @param  v the vertex
	  * @return the postorder number of vertex {@code v}
	  * @throws IllegalArgumentException unless {@code 1 < v < V}
	  */
	 public int post(Vertex<K,V> v) {
		 
		 int pos = Integer.parseInt((String) v.getId());
		 if(pos<0 || pos> pre.length) {
			 throw new IllegalArgumentException("el vertice al que se desea consultar el preorden number no tiene un id valido");
		 }
		 
		 return post[pos];
	 }
	 
	 /**
	  * Returns the vertices in preorder.
	  * @return the vertices in preorder, as an iterable of vertices
	  */
	 public LinkedList<Vertex<K,V>> pre() {
		 return preorder;
	 }


	 /**
	  * Returns the vertices in postorder.
	  * @return the vertices in postorder, as an iterable of vertices
	  */
	 public LinkedList<Vertex<K,V>> post() {
		 return postorder;
	 }

	 
	 /**
	  * Returns the vertices in reverse postorder.
	  * @return the vertices in reverse postorder, as an iterable of vertices
	  */
	 public Stack<Vertex<K,V>> reversePost() {
		 
		 Stack<Vertex<K,V>> reverse = new Stack<Vertex<K,V>>();
	
		 for (Vertex<K,V> vertex : postorder)
			 reverse.push(vertex);
		 
		 return reverse;
	 }


}
