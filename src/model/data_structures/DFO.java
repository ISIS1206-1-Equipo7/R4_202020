package model.data_structures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFO <K extends Comparable<K>, V> {
	// Atributos
	private boolean [] marked;
	private Queue<Vertex<K,V>> pre;
	private Queue<Vertex<K,V>> post;
	private Stack<Vertex<K,V>> reversePost;
	
	public DFO(DiGraph<K,V> G) {
		pre = new LinkedList<Vertex<K,V>>();
		post = new LinkedList<Vertex<K,V>>();
		reversePost = new Stack<Vertex<K,V>>();
		marked = new boolean[G.initialSize];
		for (Vertex<K, V> vertice : G.vertices()) {
			if(!marked[Integer.parseInt((String)vertice.getId())]);
			dfs(G, vertice);
		}
	}
	
	public void dfs(DiGraph<K,V> G, Vertex<K,V> v) {
		pre.add(v);
		marked[Integer.parseInt((String)v.getId())] = true;
		for (Vertex<K,V> adj : G.adjacentVertex(v.getId())) {
			if(!marked[Integer.parseInt((String)adj.getId())])
					dfs(G,adj);
		post.add(v);
		reversePost.push(v);
		}
	}
	
	public Iterable<Vertex<K, V>> pre(){
		return pre;
	}
	public Iterable<Vertex<K, V>> post(){
		return post;
	}
	public Iterable<Vertex<K, V>> reversePost(){
		return reversePost;
	}

}
