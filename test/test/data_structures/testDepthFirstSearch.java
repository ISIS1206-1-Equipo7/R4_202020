package test.data_structures;

import org.junit.*;

import model.data_structures.DepthFirstSearch;
import model.data_structures.DiGraph;
import model.data_structures.Vertex;

public class testDepthFirstSearch {
	private DiGraph<String, String> grafo;
	
	@Before
	public void setUp1() {
		grafo =  new DiGraph<>(13);
		
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
		
		//crear arcos
		grafo.addEdge("0", "1", 0.2);
		grafo.addEdge("1", "0", 0.0);
		grafo.addEdge("0", "2", 0.0);
		grafo.addEdge("2", "0", 0.0);
		grafo.addEdge("0", "5", 0.0);
		grafo.addEdge("5", "0", 0.0);
		grafo.addEdge("5", "3", 0.0);
		grafo.addEdge("3", "5", 0.0);
		grafo.addEdge("5", "4", 0.0);
		grafo.addEdge("4", "5", 0.0);
		grafo.addEdge("3", "4", 0.0);
		grafo.addEdge("4", "3", 0.0);
		grafo.addEdge("4", "6", 0.0);
		grafo.addEdge("6", "4", 0.0);
		grafo.addEdge("6", "0", 0.0);
		grafo.addEdge("0", "6", 0.0);
		
		grafo.addEdge("7", "8", 0.0);
		grafo.addEdge("8", "7", 0.0);

		grafo.addEdge("9", "10", 0.0);
		grafo.addEdge("10", "9", 0.0);
		grafo.addEdge("9", "11", 0.0);
		grafo.addEdge("11", "9", 0.0);
		grafo.addEdge("9", "12", 0.0);
		grafo.addEdge("12", "9", 0.0);
		grafo.addEdge("11", "12", 0.0);
		grafo.addEdge("12", "11", 0.0);
	}
	
	@Test
	public void depthFirstSearch(){
		DepthFirstSearch<String, String> depthfs =  new DepthFirstSearch<>(grafo, grafo.getVertex("0"));	
		System.out.println(depthfs.hasPathTo(grafo.getVertex("7")));
		for (Vertex<String, String> vertex : depthfs.pathTo(grafo.getVertex("3"))) {
			System.out.println(vertex.getId());
		}
		
		/*for (Vertex<String, String> v : depthfs.ruta2()) {
			System.out.println(v.getId() );
		}*/
		
		/*for (int i = 0; i < depthfs.ruta().length; i++) {
			System.out.println(depthfs.ruta()[i].getId());
		}*/
		
	}
}
