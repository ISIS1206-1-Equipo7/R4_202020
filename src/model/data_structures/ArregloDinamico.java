package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico <T extends Comparable<T>> implements Lista<T>
{
	
	
		/**
		 * Capacidad maxima del arreglo
		 * tamanoMax >0
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private Comparable<T> elementos[ ];

        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico( int max )
        {
               elementos = new Comparable[max];
               tamanoMax = max;
               tamanoAct = 0;
        }
		
		public void expandirArreglo()
		{
			
            tamanoMax = 2*tamanoMax;
            Comparable<T> [ ] copia = elementos;
            elementos = new Comparable[tamanoMax];
            for ( int i = 0; i < tamanoAct; i++)
            {
             	 elementos[i] = copia[i];
            } 
    	    
		}
		
		
		public void agregarInicio(T dato) 
		{
			
			if ( tamanoAct == tamanoMax )
            {  
         	   expandirArreglo();
            }	
			// empieza a popular el arreglo desde una (1) posicion más adelante, y lo popula con el elemento que
			//estaba una (1) pocisión atras en el mismo arreglo.
			for(int i= tamanoAct; i>0; i--)
			{
				elementos[i] = elementos[i-1];
			}
			elementos[0]= dato;
			tamanoAct ++;
			
		}
		
		
		public void agregarFinal( T dato )
        {
               if ( tamanoAct == tamanoMax )
               {  
            	   expandirArreglo();
               }	
               elementos[tamanoAct] = dato;
               tamanoAct++;
       }
		
		public void insertarElemento(T elemento, int pos)
		{
			//verifica que la posicion sea valida:
			if(darElemento(pos-1)!= null && pos<=tamanoAct && 0<pos)
			{
				if(pos==1)
				{
					agregarInicio(elemento);
				}
				else if(pos==tamanoAct)
				{
					agregarFinal(elemento);
				}
				else
				{
					
					if(tamanoAct == tamanoMax)
					{
						expandirArreglo();
					}
					
					int tamDerecha = tamanoAct -pos +1;
					Comparable<T> [] derechos = new Comparable[tamDerecha];
					int i;
					for(i=0; i<tamDerecha; i++)
					{
						derechos[i] = elementos[pos-1+i];
					}
					
					elementos[pos-1]=elemento;
					tamanoAct ++;
					int j;
					for(i=pos,j=0;j<tamDerecha; i++, j++)
					{
						
						elementos[i] = derechos[j];

					}
					

				}
				
			}
			
		}
		
		public int darCapacidad() 
		{
			return tamanoMax;
		}
		
		public int darTamano() 
		{
			return tamanoAct;
		}
		
		public T darElemento(int pos) 
		{
			// TODO implementar
			
			if(pos<1 || pos> tamanoAct)
			{
				return null;
			}
			else
			{
				return (T) elementos[pos-1];
			}
			
		}
		
		public T darPrimerElemento()
		{
			return (T) elementos[0];
		}
		
		public T darUltimoElemento()
		{
			return (T) elementos[tamanoAct-1];
		}
		
		public boolean isEmpty()
		{
			return (elementos[0]==null);
		}

		public T buscar(T dato) 
		{
			// TODO implementar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			int i=0;
			boolean encontro = false;
			T datoB = null;
			while( i< tamanoAct && encontro == false)
			{
				if(dato.compareTo((T) elementos[i])==0)
				{
					encontro = true;
					datoB = (T) elementos[i];
				}
				i++;
			}
				
			return (T) datoB;
		}

		public T eliminarElemento( int pos) 
		{
			T datoE = darElemento(pos);
			
			if(datoE!= null)
			{
				Comparable<T>[] nuevo = new Comparable[tamanoAct-1];
				int j=0;
				
				for(int i=0; i< tamanoAct; i++)
				{
					if(i != (pos -1))
					{
						nuevo[j] = (T) elementos[i];
						j++;
					}
					else
					{
						
					}
				}
				
				elementos = nuevo;
				tamanoAct = tamanoAct-1;
				
			}
			return datoE;
			
		}
		
		public int isPresent(T elemento)
		{
			int i=0;
			boolean encontro = false;
			int pos= -1;
			while( i< tamanoAct && encontro == false)
			{
				if(elemento.compareTo((T) elementos[i])==0)
				{
					encontro = true;
					pos = i+1;
				}
				i++;
			}
				
			return pos;
		}
		
		public void intercambiar(int pos1, int pos2)
		{
			if(pos1>=1 && pos2 >=1 && pos1 <=tamanoAct && pos2<=tamanoAct && pos1!=pos2)
			{
				Comparable<T> temp = null;
				temp= elementos[pos1-1];
				elementos[pos1-1]= elementos[pos2-1];
				elementos[pos2-1]= temp;
			}
			
		}
		
		public void cambiarInfo(int pos, T elemento)
		{
			elementos[pos-1] = elemento;
		}

		
		public Comparable<T> eliminarPrimero() 
		{
			Comparable<T> datoE = null;
			
			if(isEmpty()==false)
			{
				Comparable<T>[] nuevo = new Comparable[tamanoAct-1];
				for(int i=0; i< tamanoAct-1; i++)
				{
					nuevo[i] = elementos[i+1];
				}
				datoE = elementos[0];
				elementos = nuevo;
				tamanoAct --;
			}
			
			return  datoE;
		}
		
		public Comparable<T> eliminarUltimo()
		{
			Comparable<T> datoE = null;
			
			if(isEmpty()==false)
			{
				Comparable<T>[] nuevo = new Comparable[tamanoAct-1];
				for(int i=0; i< tamanoAct-1; i++)
				{
					nuevo[i] = elementos[i];
				}
				datoE = elementos[tamanoAct-1];
				elementos = nuevo;
				tamanoAct --;
			}
			return datoE;
		}

}
