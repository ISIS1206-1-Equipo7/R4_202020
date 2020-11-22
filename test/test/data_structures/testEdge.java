package test.data_structures;

import static org.junit.Assert.*;
import org.junit.*;
import model.data_structures.Edge;
import model.data_structures.Vertex;

public class testEdge {
	
	
	private Edge<String, String> arco;
	//private Vertex<Integer, Integer> vertice;
	
	@Before
	public void Setup1() {
		Vertex<String, String> source = new Vertex<String, String>("1", "Uno");
		Vertex<String,String> dest = new Vertex<String, String>("2", "Dos");
		double weight = 0.5;
		arco = new Edge<>(source, dest, weight);
	}
	
	@Test
	public void testGetSource() {
		assertTrue(arco.getSource().getId()=="1");
		assertTrue(arco.getSource().getInfo().equals("Uno"));
	}
	
	@Test
	public void testGetDest() {
		assertTrue(arco.getDest().getId()=="2");
		assertTrue(arco.getDest().getInfo().equals("Dos"));
	}
	
	@Test
	public void testWeight() {
		assertTrue(arco.weight()==0.5);
	}
	
	@Test
	public void testSetWeight() {
		arco.setWeight(3.5);
		assertTrue(arco.weight()==2.0);
		
	}
	
	
}
