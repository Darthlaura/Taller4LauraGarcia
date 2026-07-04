package dominio;

//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 


import java.util.ArrayList;

/**
 * Clase contexto del patrón Strategy.
 * Mantiene una estrategia de ordenamiento y permite cambiarla en tiempo de ejecución.
 */

public class ContextoStrategy {

	private Strategy estrategiaDeOrden;

	public ContextoStrategy(Strategy estrategiaDeOrden) {
		this.estrategiaDeOrden = estrategiaDeOrden;
	} 
	
	/**
	 * Metodo que ordena la lista de cartas usando la estrategia configurada.
	 *
	 * @param listaCarta lista de cartas a ordenar
	 * @return lista ordenada según la estrategia seleccionada
	 */
	public ArrayList<Carta> ordenar(ArrayList<Carta> listaCarta){
		return estrategiaDeOrden.ordenarCarta(listaCarta); 
	
		
	}
	
	/**
	 * Metodo que cambia la estrategia de ordenamiento utilizada por el contexto.
	 *
	 * @param estrategiaOrden nueva estrategia de ordenamiento
	 */

	public void setEstrategiaOrden(Strategy estrategiaOrden) {
		this.estrategiaDeOrden = estrategiaOrden; 
		
	}
	
	
	
	
}
