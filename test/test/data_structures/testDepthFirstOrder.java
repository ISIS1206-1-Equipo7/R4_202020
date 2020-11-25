package test.data_structures;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

import org.junit.*;

import model.data_structures.DepthFirstOrder;
import model.data_structures.DiGraph;
import model.data_structures.Edge;
import model.data_structures.Vertex;


public class testDepthFirstOrder {
	
	// primero creo el grafo sobre el cual se va a ejecutar la prueba
	private DiGraph<String, String> grafo;

	
	@Before
	public void SetUp1() {

		grafo = new DiGraph<String, String>(20);

		// crea los vertices
		grafo.insertVertex("0", "cero");
		grafo.insertVertex("1", "uno");
		grafo.insertVertex("2", "dos");
		grafo.insertVertex("3", "tres");
		grafo.insertVertex("4", "cuatro");
		grafo.insertVertex("5", "cinco");
		grafo.insertVertex("6", "seis");
		grafo.insertVertex("7", "siete");
		grafo.insertVertex("8", "ocho");
		grafo.insertVertex("9", "nueve");
		grafo.insertVertex("10", "diez");
		grafo.insertVertex("11", "once");
		grafo.insertVertex("12", "doce");


		// crea los arcos:
		grafo.addEdge("0", "1", 0.5, false);
		grafo.addEdge("0", "5", 2.5, false);
		grafo.addEdge("2", "0", 1,false);
		grafo.addEdge("2", "3", 0.5,false);
		grafo.addEdge("3", "2", 0.5,false);
		grafo.addEdge("3", "5", 1,false);
		grafo.addEdge("4", "2", 1,false);
		grafo.addEdge("4", "3", 0.5,false);
		grafo.addEdge("5", "4", 0.5,false);
		grafo.addEdge("6", "0", 3.0,false);
		grafo.addEdge("6", "4", 1.0,false);
		grafo.addEdge("6", "9", 1.5,false);
		grafo.addEdge("7", "6", 0.5,false);
		grafo.addEdge("7", "8", 0.5,false);
		grafo.addEdge("8", "7", 0.5,false);
		grafo.addEdge("8", "9", 0.5,false);
		grafo.addEdge("9", "10", 0.5,false);
		grafo.addEdge("9", "11", 1,false);
		grafo.addEdge("10", "12", 1.0,false);
		grafo.addEdge("11", "4", 3.5,false);
		grafo.addEdge("11", "12", 0.5,false);
		grafo.addEdge("12", "9", 1.5,false);


	}
	
	@Test
	public void depthFirstOrder() {
		
		DepthFirstOrder dfs = new DepthFirstOrder(grafo);
		

//        for(Vertex<String, String> vertex : grafo.vertices()) {
//        	int pos = Integer.parseInt((String) vertex.getId());
//        	 System.out.println("Pos: " + pos + " dfs.pre " + dfs.pre(vertex) + " dfs.post " + dfs.post(vertex));
//        	 
//        }
        
        System.out.println("Preorder:  ");
        LinkedList<Vertex<String,String>> preorden = dfs.pre();
        for(Vertex<String, String> vertex : preorden) {
        	
        	System.out.println(vertex.getId());
        }
        
        System.out.println();

        System.out.println("Postorder: ");
        LinkedList<Vertex<String,String>> postorden = dfs.post();
        for(Vertex<String, String> vertex : postorden) {
        	
        	System.out.println(vertex.getId());
        }
        
        System.out.println();

        System.out.println("Reverse postorder: ");
        Stack<Vertex<String,String>> reversePost = dfs.reversePost();
        int reversePostSize = reversePost.size();
        
        for(int i=0; i < reversePostSize; i++){
        	
        	System.out.println(reversePost.pop().getId());
        }
        
	}
	
}
