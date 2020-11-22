package controller;


import java.io.FileNotFoundException;
import java.util.Scanner;


import model.logic.Modelo;
import view.View;

public class Controller {

	/* declaracion del Modelo*/
	private Modelo modelo;

	/* declaracion de la Vista*/
	private View view;
	
	/**
	 * boolean para verificar si los datos fueron previamente cargados
	 */
	private boolean cargados;

	/**
	 * Crea la vista y el modelo del proyecto
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	/** 
	 * metodo run de la Clase Controller
	 * inicia la aplicacion
	 */
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		while( !fin )
		{
			view.printMenu();
			
			int option=0;
			try {
				 option = lector.nextInt();
			}
			catch(Exception e)
			{
				view.printMessage("Opcion invalida. Debe digitar un numero. Vuelva a intentar");
				lector.nextLine();
				option = lector.nextInt();
			}
			
			switch(option)
			{

			// Importa los datos
			case 0:
				
				if(cargados == true ) {
					modelo.limpiarConsulta();
				}
				
				view.printMessage("Escriba la pareja separada por una coma (e.g: 1,2 | 2,3 | 3,4 | 1,2,3,4)");
				String pareja = lector.next();
				modelo.leerDatos(pareja);
				cargados = true;
				
				break;

			// resuelve el requerimiento 1 del reto: Cantidad de clusters de viajes.
			case 1:
				
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				
				view.printMessage("Digite el id de la primera estacion" );
				String id1 = lector.next();
				view.printMessage("Digite el id de la segunda estacion");
				String id2 = lector.next();
				
				view.printMessage("Consulta terminada.");
				break;
				
			// resuelve el requerimiento 2 del reto: Ruta turistica circular.
			case 2:
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				// cuerpo

				
				view.printMessage("Consulta terminada.");
				break;
				
			// resuelve el requerimiento 3 del reto: Estaciones criticas.
			case 3:
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				// cuerpo

				
				view.printMessage("Consulta terminada.");
				
				break;
				
			
			// resuelve el requerimiento 4 del reto: Ruta turistica por resistencia.
			case 4:
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				// cuerpo

				
				view.printMessage("Consulta terminada.");
				
				break;
				
			// resuelve el requerimiento 5 del reto: Recomendador de rutas.
			case 5:
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				// cuerpo

				
				view.printMessage("Consulta terminada.");
				
				break;
				
			// resuelve el requerimiento 6 del reto: Ruta de interés turistico.
			case 6:
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				// cuerpo

				
				view.printMessage("Consulta terminada.");
				
				break;
				
			// resuelve el requerimiento 7 del reto (Bono): Identificacion de estaciones para publicidad.
			case 7: 
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				// cuerpo

				
				view.printMessage("Consulta terminada.");
				
				break;
				
			// resuelve el requerimiento 8 del reto (Bono): Identificacion de bicicletas para mantenimiento.
			case 8:
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				// cuerpo

				
				view.printMessage("Consulta terminada.");
				
				break;
				
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				fin = true;
				break;
			}
		}

		lector.close();

	}	
}