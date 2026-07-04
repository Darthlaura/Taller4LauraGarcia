package dominio;
//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 


import java.util.ArrayList;


/**
 * Estrategia que ordena las cartas por poder calculado de mayor a menor.
 * Utiliza Visitor1 para obtener el poder de cada carta.
 */

public class StrategyPoder implements Strategy {

	
	
	@Override
	public ArrayList<Carta> ordenarCarta(ArrayList<Carta> listaCarta) {
		ArrayList<Carta> listaCopia = new ArrayList<Carta>();
		for (int i = 0; i < listaCarta.size(); i++) {
			listaCopia.add(listaCarta.get(i));
		}

		Visitor1 poderVisitor = new Visitor1();

		for (int a = 0; a < listaCopia.size() - 1; a++) {
			for (int b = 0; b < listaCopia.size() - 1 - a; b++) {
				Carta cartaActual = listaCopia.get(b);
				Carta cartaSiguiente = listaCopia.get(b + 1);
				double poderActual = cartaActual.aceptarVisitor(poderVisitor);
				double poderSiguiente = cartaSiguiente.aceptarVisitor(poderVisitor);

				if (poderActual < poderSiguiente) {

					listaCopia.set(b, cartaSiguiente);
					listaCopia.set(b + 1, cartaActual);

				}

			}

		}

		return listaCopia;
	}

}
