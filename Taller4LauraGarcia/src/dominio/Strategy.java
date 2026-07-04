package dominio;
//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 


import java.util.ArrayList;

public interface Strategy {

	
	/**
	 * Metodo que ordena una lista de cartas según una estrategia específica.
	 *
	 * @param listaCarta lista de cartas a ordenar
	 * @return nueva lista de cartas ordenada
	 */
	public ArrayList<Carta> ordenarCarta(ArrayList<Carta> listaCarta);

	
	
	
}
