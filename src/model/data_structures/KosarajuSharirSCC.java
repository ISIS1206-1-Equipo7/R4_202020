package model.data_structures;

import java.util.Queue;

public class KosarajuSharirSCC <K extends Comparable<K>, V> {

	
	//---------------
	// ATRIBUTOS
	//--------------
	
	private int[] id;             // id[v] = id of strong component containing v
    private int count;            // number of strongly-connected components
    private boolean[] marked;
    


    //--------------
    // CONSTRUCTOR
    //--------------
    
    /**
     * Computes the strong components of the digraph {@code G}.
     * @param G the digraph
     */
    public KosarajuSharirSCC(DiGraph<K,V> G) {

        // compute reverse postorder of reverse graph
        //DepthFirstOrder<K,V> dfs = new DepthFirstOrder<K,V>(G.reverse()); 
        DFO<K, V> dfs2 = new DFO<K,V>(G.reverse());
        
        // run DFS on G, using reverse postorder to guide calculationSS
        id = new int[G.initialSize];
        marked = new boolean[G.initialSize];
        //Vertex<K,V> vertex;
        
        for (Vertex<K,V> v : dfs2.reversePost()) {
        	if(!marked[Integer.parseInt((String)v.getId())]) {
        		dfs(G, v);
        		count++;
        	}
		}
        /*for (Vertex<K,V> v : dfs.reversePost()) {
        	if(!marked[Integer.parseInt((String)v.getId())]) {
        		dfs(G, v);
        		count++;
        	}
		}*/
    }
    
    /**
     * Hace un dfs
     * @param G
     * @param v
     */
    private void dfs(DiGraph<K,V> G, Vertex<K,V> v) {
    	marked[Integer.parseInt((String)v.getId())] = true;
    	id[Integer.parseInt((String)v.getId())] = count;
    	for (Vertex<K, V> adj : G.adjacentVertex(v.getId())) {
    		if(!marked[Integer.parseInt((String)adj.getId())])
    			dfs(G,adj);			
		}
    }
    
    public boolean stronglyConnected(Vertex<K,V> v, Vertex<K,V> w) {
    	return id[Integer.parseInt((String) v.getId())] == id[Integer.parseInt((String) w.getId())];
    }
    
    public int id(Vertex<K,V> v) {
    	return id[Integer.parseInt((String) v.getId())];
    }
    
    public int count() {
    	return count;
    }
    
    public int[] ids(){
    	return id;
    }
    
    

}
