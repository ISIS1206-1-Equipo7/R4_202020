package view;


public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
	    /**
	     * Imprime el menu
	     */
		public void printMenu()
		{
			System.out.println("********* Escriba el número correspondiente a lo que desea hacer *********");
			System.out.println("[0] Importar Datos.");
			System.out.println("[1] Conocer la cantidad de clusters de viajes.");  //req. 1.
			System.out.println("[2] Conocer rutas turisticas circulares.");			//req. 2.
			System.out.println("[3] Conocer las estaciones mas criticas.");			// req. 3.
			System.out.println("[4] Conocer una ruta turistica por resistencia.");	// req. 4.
			System.out.println("[5] Recomendador de rutas.");						// req. 5.
			System.out.println("[6] Conocer una ruta de interes turistico.");		// req. 6.
			System.out.println("[7] Identificar estaciones para publicidad.");		// req. 7. Bono.
			System.out.println("[8] Identificar bicicletas para mantenimiento.");	// req. 8. Bono.





		}

		/**
		 * Imprime un mensaje pasado por parametro
		 * @param mensaje
		 */
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}
		
		
}