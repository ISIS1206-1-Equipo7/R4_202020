package test.data_structures;

import static org.junit.Assert.*;

import org.junit.*;

import model.data_structures.Edge;
import model.data_structures.Vertex;

public class testVertex {

	private Vertex<String, String> vertice;
	
	private Vertex<String, String> source;
	private Vertex<String, String> source2;
	private Vertex<String, String> source3;

	private Edge<String, String> arco;
	private Edge<String, String> arco2;
	private Edge<String, String> arco3;
	
	@Before
	public void Setup1() {
		vertice = new Vertex<>("1", "Uno");
		source = new Vertex<String, String>("2", "Dos");
		Vertex<String, String> dest = new Vertex<String, String>("3", "Tres");
		double weight = 0.5;
		arco = new Edge<>(source, dest, weight);
		source2 = new Vertex<String, String>("4", "Cuatro");
		Vertex<String, String> dest2 = new Vertex<String, String>("5", "Cinco");
		double weight2 = 0.10;
		arco2 = new Edge<>(source2, dest2, weight2);
		source3 = new Vertex<String, String>("6", "Seis");
		Vertex<String, String> dest3 = new Vertex<String, String>("7", "Siete");
		double weight3 = 0.15;
		arco3 = new Edge<>(source3, dest3, weight3);
	}
	
	@Test
	public void testGetId(){
		assertTrue(vertice.getId()=="1");
	}
	
	@Test
	public void testGetInfo() {
		assertTrue(vertice.getInfo().equals("Uno"));
	}
	
	@Test
	public void testGetMark() {
		assertFalse(vertice.getMark());
		vertice.mark();
		assertTrue(vertice.getMark());
		vertice.unmark();
		assertFalse(vertice.getMark());
	}
	
	@Test
	public void testAddEdge() {
		assertTrue(vertice.edges().isEmpty()); //La lista de arcos debe estar vacia
		assertTrue(vertice.vertices().isEmpty());//La lista de vertices
		source.addEdge(arco);				   //Se agrega el arco, arco
		assertFalse(source.edges().isEmpty());//La lista de arcos no debe estar vacia
		assertFalse(source.vertices().isEmpty());//La lista de vertices ya no debe estar vacia
		assertTrue(source.edges().contains(arco));//La lista debe contener el arco previamente agregado
		assertFalse(source.edges().contains(arco2));// La lista no debe contener el arco no agregado
		assertTrue(source.vertices().getFirst()==arco.getDest());
		source2.addEdge(arco2);
		source3.addEdge(arco3);
		assertTrue(source2.edges().size()==1);
		assertTrue(source2.edges().contains(arco2));//La lista debe contener el arco previamente agregado
		assertTrue(source3.edges().size()==1);
		assertTrue(source3.edges().contains(arco3));//La lista debe contener el arco previamente agregado

	}
	
	@Test
	public void testGetEdge() {
		assertNull(vertice.getEdge("1"));
		source.addEdge(arco);
		source2.addEdge(arco2);
		source3.addEdge(arco3);
		assertTrue(source.getEdge("3")==arco);
		assertTrue(source2.getEdge("5")==arco2);
		assertTrue(source3.getEdge("7")==arco3);
	}
}
