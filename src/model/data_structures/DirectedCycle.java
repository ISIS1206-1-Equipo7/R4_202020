package model.data_structures;

import java.util.Stack;

public class DirectedCycle <K extends Comparable<K>, V> {
	private boolean[] marked;
	private Vertex<K,V>[] edgeTo;
	private Stack<Vertex<K,V>> cycle;
	private boolean[] onStack;
	
	public DirectedCycle(DiGraph<K,V> G, Vertex<K, V> id) {
		onStack =  new boolean[G.initialSize];
		edgeTo =  new Vertex[G.initialSize];
		marked = new boolean[G.initialSize];
		for (Vertex<K, V> vertex : G.adjacentVertex(id.getId())) {
			int pos = Integer.parseInt((String)vertex.getId());
			if(!marked[pos])
				dfs(G, vertex);
		}
	}
	
	public void dfs(DiGraph<K,V> G, Vertex<K,V> v) {
		int pos = Integer.parseInt((String)v.getId());
		onStack[pos] = true;
		marked[pos] = true;
		for (Vertex<K, V> w : G.adjacentVertex(v.getId())) {
			int posW = Integer.parseInt((String)w.getId());
			if(this.hasCycle())
				return;
			else if(!marked[posW]) {
				edgeTo[posW] = v;
				dfs(G,w);
			}
			else if(onStack[posW])
			{
				cycle = new Stack<Vertex<K,V>>();
				for (Vertex<K,V> x = v; x!=w ; x = edgeTo[Integer.parseInt((String)x.getId())]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
			onStack[pos] = false;
		}
	}
	
	public boolean hasCycle() {
		return cycle != null;
	}
	
	public Iterable<Vertex<K,V>> cycle(){
		return cycle;
	}
}

