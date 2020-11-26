package test.data_structures;

import static org.junit.Assert.assertTrue;

import org.junit.*;

import model.data_structures.DiGraph;
import model.data_structures.KosarajuSharirSCC;
import model.data_structures.Vertex;

public class testKosaraju {

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
		//crea los arcos
		grafo.addEdge("0", "1", 0.0);
		grafo.addEdge("0", "5", 0.0);
		grafo.addEdge("2", "0", 0.0);
		grafo.addEdge("2", "3", 0.0);
		grafo.addEdge("3", "2", 0.0);
		grafo.addEdge("3", "5", 0.0);
		grafo.addEdge("4", "2", 0.0);
		grafo.addEdge("4", "3", 0.0);
		grafo.addEdge("5", "4", 0.0);
		grafo.addEdge("6", "0", 0.0);
		grafo.addEdge("6", "4", 0.0);
		grafo.addEdge("6", "9", 0.0);
		grafo.addEdge("7", "6", 0.0);
		grafo.addEdge("7", "8", 0.0);
		grafo.addEdge("8", "7", 0.0);
		grafo.addEdge("8", "9", 0.0);
		grafo.addEdge("9", "10", 0.0);
		grafo.addEdge("9", "11", 0.0);
		grafo.addEdge("10", "12", 0.0);
		grafo.addEdge("11", "4", 0.0);
		grafo.addEdge("11", "12", 0.0);
		grafo.addEdge("12", "9", 0.0);
	}

	@Test
	public void Kosaraju() {
		KosarajuSharirSCC ks = new KosarajuSharirSCC(grafo);
		System.out.println(ks.stronglyConnected(grafo.getVertex("0"), grafo.getVertex("1")));
		System.out.println(ks.count());


		//System.out.println(ks.stronglyConnected(grafo.getVertex("1"), grafo.getVertex("6")));
		//System.out.println(ks.id().length);

	}
}
