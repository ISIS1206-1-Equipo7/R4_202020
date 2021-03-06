package test.data_structures;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.*;
import model.data_structures.DiGraph;
import model.data_structures.Edge;
import model.data_structures.Vertex;


public class testDiGraph {

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
		grafo.insertVertex("15", "quince");
		grafo.insertVertex("19", "diecinueve");
		
		// crea los arcos:
		grafo.addEdge("0", "1", 0.5);
		grafo.addEdge("0", "5", 2.5);
		grafo.addEdge("2", "0", 1);
		grafo.addEdge("2", "3", 0.5);
		grafo.addEdge("3", "2", 0.5);
		grafo.addEdge("3", "5", 1);
		grafo.addEdge("4", "2", 1);
		grafo.addEdge("4", "3", 0.5);
		grafo.addEdge("5", "4", 0.5);
		grafo.addEdge("6", "0", 3.0);
		grafo.addEdge("6", "4", 1.0);
		grafo.addEdge("6", "9", 1.5);
		grafo.addEdge("7", "6", 0.5);
		grafo.addEdge("7", "8", 0.5);
		grafo.addEdge("8", "7", 0.5);
		grafo.addEdge("8", "9", 0.5);
		grafo.addEdge("9", "10", 0.5);
		grafo.addEdge("9", "11", 1);
		grafo.addEdge("10", "12", 1.0);
		grafo.addEdge("11", "4", 3.5);
		grafo.addEdge("11", "12", 0.5);
		grafo.addEdge("12", "9", 1.5);
		grafo.addEdge("15", "19", 2.0); //23
		grafo.addEdge("3", "3", 0);		// anomalia
		
		

	}
	
	@Test
	public void testContainsVertex() {
		
		assertFalse(grafo.containsVertex("13"));
		assertTrue(grafo.containsVertex("9"));
	}
	
	@Test
	public void testNumEdges() {

		assertTrue(grafo.numEdges()==23);
		
		try {
			grafo.addEdge("15", "19", 3.0); //24
		}
		catch(Exception e) {
			
			assertTrue(e.getMessage().equals("el arco que se intenta agregar ya existe"));
			
			grafo.getVertex("15").getEdge("19").setWeight(3.0);
			assertTrue(grafo.getVertex("15").getEdge("19").weight()==2.5);
		}
		
	}
	
	@Test
	public void testNumVertices() {
		assertTrue(grafo.numVertices()== 15);
	}
	
	@Test
	public void testInsertVertex() {
		
		
		try {
			grafo.insertVertex("12", "PruebaError");
		}
		catch( Exception e) {
			
			assertTrue(e.getMessage().equals("el vertice que se intenta agregar ya existe en la estructura."));
			assertTrue(grafo.numVertices()==15);
		}
	}
	
	@Test
	public void testGetVertex() {
		
		assertTrue(grafo.getVertex("4").getInfo().equals("cuatro"));
		assertTrue(grafo.getVertex("19").getInfo().equals("diecinueve"));
		try {
			 grafo.getVertex("13");
		}
		catch(IllegalArgumentException e) {
			
		}

	}
	
	@Test
	public void testGetEdge() {
		
		assertTrue(grafo.getEdge("4","2").weight()==1);
		assertTrue(grafo.getEdge("15", "19").weight()==2.0);
		assertTrue(grafo.getEdge("15", "0")== null);

	}
	
	@Test
	public void testAdjacentEdges() {
		
		assertTrue(grafo.adjacentEdges("3").size()==2);
		assertTrue(grafo.adjacentEdges("3").getFirst().weight()==0.5);
		assertTrue(grafo.adjacentEdges("3").getLast().weight()==1);

		assertTrue(grafo.adjacentEdges("15").size() ==1 );
		assertTrue(grafo.adjacentEdges("15").getFirst().weight() == 2 );
		
	}
	
	@Test
	public void testAdjacentVertex() {
		
		LinkedList<Vertex<String,String>> vertices = grafo.adjacentVertex("3");
		assertTrue(vertices.size()==2);
		assertTrue(vertices.getFirst().getInfo().equals("dos"));
		assertTrue(vertices.getLast().getInfo().equals("cinco"));
		
	}
	
	@Test
	public void testOutDegree() {
	
		assertTrue(grafo.outdegree("6") == 3);
		assertTrue(grafo.outdegree("15") == 1);
		assertTrue(grafo.outdegree("19") == 0);
		
	}
	
	
	@Test
	public void testInDegree() {
		
		assertTrue(grafo.indegree("6") == 1);
		assertTrue(grafo.indegree("2") == 2);
		assertTrue(grafo.indegree("15") == 0);

		
	}
	
	@Test
	public void testReverse() {
		
		DiGraph<String, String> reverse = grafo.reverse();
		
		// check that reverse Edges were corrrectly added.
		assertTrue(reverse.getVertex("0").getEdge("2").weight()==1.0);
		assertTrue(reverse.getVertex("0").getEdge("6").weight()==3.0);
		assertTrue(reverse.getVertex("1").getEdge("0").weight()==0.5);
		assertTrue(reverse.getVertex("2").getEdge("4").weight()==1.0);
		assertTrue(reverse.getVertex("3").getEdge("2").weight()==0.5);
		assertTrue(reverse.getVertex("3").getEdge("4").weight()==0.5);
		assertTrue(reverse.getVertex("4").getEdge("6").weight()==1);
		assertTrue(reverse.getVertex("4").getEdge("5").weight()==0.5);
		assertTrue(reverse.getVertex("5").getEdge("0").weight()==2.5);
		assertTrue(reverse.getVertex("5").getEdge("3").weight()==1);
		assertTrue(reverse.getVertex("6").getEdge("7").weight()==0.5);
		assertTrue(reverse.getVertex("7").getEdge("8").weight()==0.5);
		assertTrue(reverse.getVertex("8").getEdge("7").weight()==0.5);
		assertTrue(reverse.getVertex("9").getEdge("6").weight()==1.5);
		assertTrue(reverse.getVertex("9").getEdge("8").weight()==0.5);
		assertTrue(reverse.getVertex("9").getEdge("12").weight()==1.5);
		assertTrue(reverse.getVertex("10").getEdge("9").weight()==0.5);
		assertTrue(reverse.getVertex("11").getEdge("9").weight()==1);
		assertTrue(reverse.getVertex("12").getEdge("11").weight()==0.5);
		assertTrue(reverse.getVertex("12").getEdge("10").weight()==1.0);
		assertTrue(reverse.getVertex("3").getEdge("3")==null);
		
		//sizes (outDegree)
		assertTrue(reverse.getVertex("0").edges().size()==2);
		assertTrue(reverse.getVertex("1").edges().size()==1);
		assertTrue(reverse.getVertex("2").edges().size()==2);
		assertTrue(reverse.getVertex("3").edges().size()==2);
		assertTrue(reverse.getVertex("4").edges().size()==3);
		assertTrue(reverse.getVertex("5").edges().size()==2);
		assertTrue(reverse.getVertex("6").edges().size()==1);
		assertTrue(reverse.getVertex("7").edges().size()==1);
		assertTrue(reverse.getVertex("8").edges().size()==1);
		assertTrue(reverse.getVertex("9").edges().size()==3);
		assertTrue(reverse.getVertex("10").edges().size()==1);
		assertTrue(reverse.getVertex("11").edges().size()==1);
		assertTrue(reverse.getVertex("12").edges().size()==2);
		assertTrue(reverse.edges().size() == 23);
		assertTrue(reverse.vertices().size() ==15);
		
		
		// inDegrees
		assertTrue(reverse.getVertex("0").indegree()==2);
		assertTrue(reverse.getVertex("1").indegree()==0);
		assertTrue(reverse.getVertex("2").indegree()==2);
		assertTrue(reverse.getVertex("3").indegree()==2);
		assertTrue(reverse.getVertex("4").indegree()==2);
		assertTrue(reverse.getVertex("5").indegree()==1);
		assertTrue(reverse.getVertex("6").indegree()==3);
		assertTrue(reverse.getVertex("7").indegree()==2);
		assertTrue(reverse.getVertex("8").indegree()==2);
		assertTrue(reverse.getVertex("9").indegree()==2);
		assertTrue(reverse.getVertex("10").indegree()==1);
		assertTrue(reverse.getVertex("11").indegree()==2);
		assertTrue(reverse.getVertex("12").indegree()==1);

	}


}
