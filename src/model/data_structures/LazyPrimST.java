package model.data_structures;

import java.util.*;

public class LazyPrimST <K extends Comparable<K>, V> {
	
	private boolean [] marked;
	private Queue<Edge<K,V>> st;
	private Queue<Edge<K,V>> pq;
	
	public LazyPrimST(DiGraph<K,V> G, Vertex<K,V> v) {
		marked = new boolean[G.initialSize];
		st = new LinkedList<Edge<K,V>>();
		
	}
}
