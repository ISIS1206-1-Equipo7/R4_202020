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
        DepthFirstOrder<K,V> dfs = new DepthFirstOrder<K,V>(G.reverse());

        // desmarca todos los vertices para poder hacer el DFS de nuevo 
        /*for(Vertex<K,V> vtx : dfs.reversePost()) {
        	vtx.unmark();
        } */     
        
        // run DFS on G, using reverse postorder to guide calculationSS
        id = new int[G.initialSize];
        marked = new boolean[G.initialSize];
        //Vertex<K,V> vertex;
        for (Vertex v : dfs.reversePost()) {
        	if(!marked[Integer.parseInt((String)v.getId())]) {
        		dfs(G, v);
        		count++;
        	}
		}
       /* int reversePostSize = dfs.reversePost().size();
        for(int i=0; i< reversePostSize; i++) {
        	vertex =  dfs.reversePost().pop();
        	if(!marked[Integer.parseInt((String)vertex.getId())]) {
        		dfs(G, vertex);
        		count++;
        	}
        	if(vertex.getMark()==false) {
        		dfs(G,vertex);
        		count ++;
        	}
        }*/
        // check that id[] gives strong components
        //assert check(G);
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
    
    public boolean stronglyConnected(Vertex v, Vertex w) {
    	return id[Integer.parseInt((String) v.getId())] == id[Integer.parseInt((String) w.getId())];
    }
    
    public int id(Vertex v) {
    	return id[Integer.parseInt((String) v.getId())];
    }
    
    public int count() {
    	return count;
    }
    
    
    

}
