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

			// resuelve el requerimiento 1
			case 1:
				if (!cargados) {
					view.printMessage("Debe cargar los datos primero.");
					break;
				}
				
				view.printMessage("Digite el ID de la estacion que desea consultar.");
				String id = lector.next();
				
				modelo.consultarGrado(id);
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