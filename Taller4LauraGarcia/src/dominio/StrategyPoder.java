package dominio;

import java.util.ArrayList;

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
