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
		grafo.addEdge("15", "19", 2.0,false); //23
		grafo.addEdge("3", "3", 0, false);		// anomalia
		
		

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
			grafo.addEdge("15", "19", 3.0, false); //24
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
		assertTrue(reverse.getVertex("0").getReverseEdge("2").weight()==1.0);
		assertTrue(reverse.getVertex("0").getReverseEdge("6").weight()==3.0);
		assertTrue(reverse.getVertex("1").getReverseEdge("0").weight()==0.5);
		assertTrue(reverse.getVertex("2").getReverseEdge("4").weight()==1.0);
		assertTrue(reverse.getVertex("3").getReverseEdge("2").weight()==0.5);
		assertTrue(reverse.getVertex("3").getReverseEdge("4").weight()==0.5);
		assertTrue(reverse.getVertex("4").getReverseEdge("6").weight()==1);
		assertTrue(reverse.getVertex("4").getReverseEdge("5").weight()==0.5);
		assertTrue(reverse.getVertex("5").getReverseEdge("0").weight()==2.5);
		assertTrue(reverse.getVertex("5").getReverseEdge("3").weight()==1);
		assertTrue(reverse.getVertex("6").getReverseEdge("7").weight()==0.5);
		assertTrue(reverse.getVertex("7").getReverseEdge("8").weight()==0.5);
		assertTrue(reverse.getVertex("8").getReverseEdge("7").weight()==0.5);
		assertTrue(reverse.getVertex("9").getReverseEdge("6").weight()==1.5);
		assertTrue(reverse.getVertex("9").getReverseEdge("8").weight()==0.5);
		assertTrue(reverse.getVertex("9").getReverseEdge("12").weight()==1.5);
		assertTrue(reverse.getVertex("10").getReverseEdge("9").weight()==0.5);
		assertTrue(reverse.getVertex("11").getReverseEdge("9").weight()==1);
		assertTrue(reverse.getVertex("12").getReverseEdge("11").weight()==0.5);
		assertTrue(reverse.getVertex("12").getReverseEdge("10").weight()==1.0);
		assertTrue(reverse.getVertex("3").getReverseEdge("3")==null);
		
		//sizes:
		assertTrue(reverse.getVertex("0").reverseEdges().size()==2);
		assertTrue(reverse.getVertex("1").reverseEdges().size()==1);
		assertTrue(reverse.getVertex("2").reverseEdges().size()==2);
		assertTrue(reverse.getVertex("3").reverseEdges().size()==2);
		assertTrue(reverse.getVertex("4").reverseEdges().size()==3);
		assertTrue(reverse.getVertex("5").reverseEdges().size()==2);
		assertTrue(reverse.getVertex("6").reverseEdges().size()==1);
		assertTrue(reverse.getVertex("7").reverseEdges().size()==1);
		assertTrue(reverse.getVertex("8").reverseEdges().size()==1);
		assertTrue(reverse.getVertex("9").reverseEdges().size()==3);
		assertTrue(reverse.getVertex("10").reverseEdges().size()==1);
		assertTrue(reverse.getVertex("11").reverseEdges().size()==1);
		assertTrue(reverse.getVertex("12").reverseEdges().size()==2);
		assertTrue(reverse.edges().size() == 23);
		assertTrue(reverse.vertices().size() ==15);


	}


}
