package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import model.data_structures.DepthFirstSearch;
import model.data_structures.DiGraph;
import model.data_structures.Edge;
import model.data_structures.KosarajuSharirSCC;
import model.data_structures.Quicksort;
import model.data_structures.Vertex;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	//-----------
	// CONSTANTES:
	//-----------
	
	/**
	 * Archivo 1
	 */
	private static final String ruta1 = "data/201801-1-citibike-tripdata.csv";

	/**
	 * Archivo 2
	 */
	private static final String ruta2 = "data/201801-2-citibike-tripdata.csv";
	
	/**
	 * Archivo 3
	 */
	private static final String ruta3 = "data/201801-3-citibike-tripdata.csv";
	
	/**
	 * Archivo 4
	 */
	private static final String ruta4 = "data/201801-4-citibike-tripdata.csv";

	//----------
	// ATRIBUTOS:
	//----------
	
	/**
	 * Estructura del grafo
	 */
	private DiGraph<String,Station> grafo;
	private KosarajuSharirSCC<String, Station> Kosaraju;
	private DepthFirstSearch<String, Station> DepthFS;

	/**
	 * Quicksort
	 */
	private Quicksort sort;
	
	//------------
	// CONSTRUCTOR:
	//------------
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		grafo = new DiGraph<String,Station>(3665);
		sort = new Quicksort();
	}
	
	
	//------------
	// METODO:
	//------------
	
	/**
	 * Importa, lee y guarda los datos necesarios
	 */
	public void leerDatos(String pareja)
	{			
		long tiempoI;
		long tiempoF;
		double tiempoT;
		
		tiempoI = System.nanoTime();
		
		// ------------------------------------------------
		
		int viajes = 0;
		
		if (pareja.equals("1,2,3,4")) {
			viajes += this.leerArchivo(ruta1);
			viajes += this.leerArchivo(ruta2);
			viajes += this.leerArchivo(ruta3);
			viajes += this.leerArchivo(ruta4);
		} else if (pareja.equals("1,2")){
			viajes += this.leerArchivo(ruta1);
			viajes += this.leerArchivo(ruta2);
		} else if (pareja.equals("2,3")){
			viajes += this.leerArchivo(ruta2);
			viajes += this.leerArchivo(ruta3);
		} else if (pareja.equals("3,4")){
			viajes += this.leerArchivo(ruta3);
			viajes += this.leerArchivo(ruta4);
		}
		else {
			System.out.println("Formato incorrecto");
			return;
		}
		
		// ------------------------------------------------
		
		tiempoF = System.nanoTime();
		tiempoT = (double) (tiempoF-tiempoI)/1000000000;
		
		// IMPRIMIR
		
		Edge<String,Station> minEdge =  this.getMinEdge();
		Edge<String, Station> maxEdge = this.getMaxEdge();
		
		if(minEdge==null || maxEdge==null) // si los datos no fueron previamente adjuntados a la carpeta, esto pasa.
			return;
	
		System.out.println("***** Informacion de la lectura de datos de archivos: " + pareja + " *****");
		System.out.println("- Numero total de viajes leidos de los archivos: " + viajes);
		System.out.println("- Numero total de estaciones (vertices) en el grafo: " + grafo.numVertices());
		System.out.println("- Numero total de arcos entre estaciones: " + grafo.numEdges());
		System.out.println("- El arco con el peso MINIMO tiene un valor de: " + minEdge.weight() + " y sus vertices son: " + minEdge.getSource().getId() + "-" + minEdge.getDest().getId());
		System.out.println("- El arco con el peso MAXIMO tiene un valor de: " + maxEdge.weight() + " y sus vertices son: " + maxEdge.getSource().getId() + "-" + maxEdge.getDest().getId());
		System.out.println("Datos importados correctamente.");
	}
	
	/**
	 * Lee un archivo por ruta especificada
	 * @param ruta
	 */
	private int leerArchivo(String ruta) {
		String lineaDatos;
		int contador = 0;

		try
		{			
			FileReader fr = new FileReader(ruta);
			BufferedReader br = new BufferedReader(fr);
			br.readLine();
			
			while ( (lineaDatos = br.readLine()) != null )
			{
				String[] camposDatos = lineaDatos.split(",");

				// ------
				// CAMPOS
				// ------
				
				// tripDuration
				String tripDuration = camposDatos[0];
				// startTime
				String startTime = camposDatos[1];
				// stopTime
				String stopTime = camposDatos[2];
				//startID
				String startID = camposDatos[3];
				// startName
				String startName = camposDatos[4];
				// startLatitude
				String startLatitude = camposDatos[5];
				// startLongitude
				String startLongitude = camposDatos[6];
				// endID
				String endID = camposDatos[7];
				// endName
				String endName = camposDatos[8];
				// endLatitude
				String endLatitude = camposDatos[9];
				// endLongitude
				String endLongitude = camposDatos[10];
				// bikeID
				String bikeID = camposDatos[11];
				// userType
				String userType = camposDatos[12];
				// birthYear
				String birthYear = camposDatos[13];
				// gender
				String gender = camposDatos[14];
				
				Station iStation = new Station(startTime, startID, startName, startLatitude, startLongitude);
				Station fStation = new Station(stopTime, endID, endName, endLatitude, endLongitude);
				
				//User user = new User(bikeID, userType, birthYear, gender);
				//Trip trip = new Trip(iStation, fStation, tripDuration, user);
				
				// si alguna de las estaciones (vertices) no esta en el grafo, la agrega:
				if (grafo.containsVertex(startID) == false) {
					grafo.insertVertex(startID, iStation);
				}
				
				if (grafo.containsVertex(endID) == false) {
					grafo.insertVertex(endID, fStation);
				}
				
				// luego de agregar las estaciones agrega el nuevo arco o modifica el peso del arco existente:
				// Esta modificacion se hace de la siguiente manera: si el arco entre ambas estaciones ya existe,
				// llama a setWeight para modificar el peso del arco. Este peso es un promedio de todos los tiempos
				// entre estas dos estaciones.
				
				double duration = Double.parseDouble(tripDuration);
				
				try {
					grafo.addEdge(startID, endID, duration);
				}
				catch(IllegalArgumentException e) {		
					if(e.getMessage().equals("el arco que se intenta agregar ya existe")) {  // el arco ya se habia creado
						grafo.getVertex(startID).getEdge(endID).setWeight(duration); // recalcula el peso del arco existente
					}
					else {
						e.printStackTrace();
					}
				}
				catch(IndexOutOfBoundsException i) {		// error en la carga de datos
					i.printStackTrace();
				}
				
				contador ++;
			}

			// Cierra el lector
			br.close();
			
			return contador;
			
		} catch (Exception e)
		{
			System.out.println("Error al cargar los datos: ");
			System.out.println("Debe adjuntar los datos a la carpeta data primero.");

			return contador;
		}
	}
	
	/**
	 * Retorna el arco con peso minimo
	 */
	private Edge<String,Station> getMinEdge() {
		
		double min = 9999.0;
		Edge<String,Station> minEdge = null;
		
		if(grafo.edges().size()>0) {
			for(Edge<String,Station> edge : (LinkedList<Edge<String,Station>>) grafo.edges()) {
				if (edge.weight() < min) {
					min = edge.weight();
					minEdge = edge;
				}
			}
		}
		return minEdge;
	}
	
	/**
	 * Retorna el arco con peso maximo
	 * @return Edge con el maximo tripduration.
	 */
	private Edge<String,Station> getMaxEdge() {
		
		double max = 0;
		Edge<String,Station> maxEdge = null;
		if(grafo.edges().size()>0) {
			for(Edge<String,Station> edge : (LinkedList<Edge<String,Station>>) grafo.edges()) {
				if (edge.weight() > max) {
					max = edge.weight();
					maxEdge = edge;
				}
			}
		}
		return maxEdge;
	}
	
	/**
	 * Resuelve el req. 1 del taller 6
	 * @return Edge con el minimo tripduration.
	 */
	public void consultarGrado(String id) {
		try {
			if (grafo.containsVertex(id)) {
				int in = grafo.indegree(id);
				int out = grafo.outdegree(id);
				System.out.println("La estacion con ID: " + id + " tiene:\n- " + in + " grados de entrada\n- " + out + " grados de salida");
				
			} else {							// el id que se busca no existe en el grafo.
				System.out.println("ID invalido.");
			}
		} catch (Exception e) {					// el id que se busca esta fuera de los limites del grafo.
			System.out.println("ID invalido.");
		}
	}
	
	/**
	 * limpia un consulta previa para permitir que el usuario realice una nueve consulta con un conjunto diferente de datos.
	 */
	public void limpiarConsulta() {
		
		grafo = new DiGraph<String, Station>(3665);
	}
	
	/**
	 * resuelve el requerimiento 1 del reto: Cantidad de clusters de viajes.
	 * @param id1  estacion 1 de interes.
	 * @param id2 estacion 2 de interes.
	 */
	public void cantidadClustersViajes(String id1, String id2) {
		Kosaraju =  new KosarajuSharirSCC<String, Station>(grafo);
		System.out.println("El total de clusters presentes en el grafo son: " + Kosaraju.count());
		if(Kosaraju.stronglyConnected(grafo.getVertex(id1), grafo.getVertex(id2)))
			System.out.println("La estacion " + id1 + " y la estacion " + id2 + "pertenecen al mismo cluster.");
		System.out.println("La estacion " + id1 + " y la estacion " + id2 + " no pertenecen al mismo cluster.");		
	}
	
	/**
	 * Resuelve el req. 2
	 */
	public void rutaTuristicaCircular() {
		
	}
	
	/**
	 * Resuelve el req. 3
	 */
	public void estacionesCriticas() {		
		Vertex<String, Station>[] estaciones = grafo.verticesArray();
		int N = estaciones.length;
		
		sort.byInDegree(estaciones, 0, N-1);
		System.out.println("Top 3 estaciones de llegada (indegree):");
		this.printTop3InDegree(estaciones);
		
		sort.byOutDegree(estaciones, 0, N-1);
		System.out.println("Top 3 estaciones de salida (outdegree):");
		this.printTop3OutDegree(estaciones);
		
		sort.bySumDegree(estaciones, 0, N-1);
		System.out.println("Top 3 estaciones menos utilizadas:");
		this.printTop3SumDegree(estaciones);
	}
	
	/**
	 * inDegree Array
	 * @param pArray
	 */
	private void printTop3InDegree(Vertex<String, Station>[] pArray) {
		Vertex<String, Station> estacion;
		for(int i = 0; i < 3; i++) {
			estacion = pArray[i];
			System.out.println((i+1) + ") " + estacion.getInfo().getName() + " - " + estacion.indegree() + " llegadas");
		}
	}
	
	/**
	 * outDegree Array
	 * @param pArray
	 */
	private void printTop3OutDegree(Vertex<String, Station>[] pArray) {
		Vertex<String, Station> estacion;
		for(int i = 0; i < 3; i++) {
			estacion = pArray[i];
			System.out.println((i+1) + ") " + estacion.getInfo().getName() + " - " + estacion.outdegree() + " salidas");
		}
	}
	
	/**
	 * sumDegree Array
	 * @param pArray
	 */
	private void printTop3SumDegree(Vertex<String, Station>[] pArray) {
		Vertex<String, Station> estacion;
		int suma;
		for(int i = 0; i < 3; i++) {
			estacion = pArray[i];
			suma = estacion.indegree() + estacion.outdegree();
			System.out.println((i+1) + ") " + estacion.getInfo().getName() + " - " + suma + " llegadas y salidas");
		}
	}
	
	/**
	 * Resuelve el req. 4
	 * @param tiempo el timpo maximo de resistencia
	 * @param id el id de la estacion inicial
	 */
	public void rutaPorResistencia(String tiempo, String id) {
		int resistencia = (Integer.parseInt(tiempo))*60;
		int estacionInicial = (Integer.parseInt(id));
		int contador = 0;
		
		DepthFS = new DepthFirstSearch<String, Station>(grafo, grafo.getVertex(id));
		
		for (Vertex<String, Station> vertice : grafo.adjacentVertex(id)) {
			
		}
		
	}
	
	/**
	 * Resuelve el req. 5
	 * @param rangoEdad el rango de edad del turista
	 */
	public void recomendadorRutasPorEdad(String rangoEdad) {
		
	}
	
}