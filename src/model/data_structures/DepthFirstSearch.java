package model.data_structures;

import java.util.Stack;

public class DepthFirstSearch <K extends Comparable<K>, V> {
	private boolean [] marked;
	private Vertex<K,V>[] edgetTo;
	private Vertex<K,V> source;
	
	public DepthFirstSearch(DiGraph<K,V> G, Vertex<K,V> source){
		marked =  new boolean [G.initialSize];
		edgetTo =  new Vertex [G.initialSize];

		this.source = source;
		dfs(G, source);				
	}
	
	public void dfs(DiGraph<K,V> G, Vertex<K,V> dest) {
		marked[Integer.parseInt((String)dest.getId())] = true;
		for (Vertex<K, V> vertex : G.adjacentVertex(dest.getId())) {
			if(!marked[Integer.parseInt((String)vertex.getId())])
			{
				edgetTo[Integer.parseInt((String) vertex.getId())] = dest;
				dfs(G,vertex);
			}
		}
	}
	
	public Vertex<K,V> [] ruta(){
		return edgetTo;		
	}
	

	
	public boolean hasPathTo(Vertex<K,V> v) {
		int pos =  Integer.parseInt((String)v.getId());
		return marked[pos];
	}
	
	public Iterable<Vertex<K,V>> pathTo(Vertex<K,V> v){
		if(!hasPathTo(v))
			return null;
		Stack<Vertex<K,V>> path =  new Stack<Vertex<K,V>>();
		for (Vertex<K, V> x = v; x != source; x = edgetTo[Integer.parseInt((String)x.getId())]) {
			path.push(x);
		}
		path.push(source);
		return path;
	}
}