package logica;

import java.util.ArrayList;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import dominio.*;

public class GestorArchivo {

	public static ArrayList<Carta> lecturaArchivo(String nombreArchivo) throws FileNotFoundException {
		ArrayList<Carta> listaCarta = new ArrayList<Carta>();
		File archivo = new File(nombreArchivo);
		Scanner lectura = new Scanner(archivo);

		while (lectura.hasNextLine()) {
			String linea = lectura.nextLine();

			Carta cartaLectura = FactoryLectura.separarObjeto(linea);

			if (cartaLectura != null) {

				listaCarta.add(cartaLectura);
			}

		}
		lectura.close();

		return listaCarta;
	}

	public static String convertirCartaALinea(Carta carta) {

		String linea = "";

		if (carta instanceof Pokemon) {

			Pokemon pokemon = (Pokemon) carta;

			linea = pokemon.getNombreCarta() + ";" + pokemon.getRareza() + ";" + pokemon.getTipo() + ";"
					+ pokemon.getDaño() + ";" + pokemon.getCantidadEnergia();

		} else if (carta instanceof Item) {

			Item item = (Item) carta;

			linea = item.getNombreCarta() + ";" + item.getRareza() + ";" + item.getTipo() + ";"
					+ item.getBonificacion();

		} else if (carta instanceof Supporter) {

			Supporter supporter = (Supporter) carta;

			linea = supporter.getNombreCarta() + ";" + supporter.getRareza() + ";" + supporter.getTipo() + ";"
					+ supporter.getEfectorPorturno();

		} else if (carta instanceof Energy) {

			Energy energy = (Energy) carta;

			linea = energy.getNombreCarta() + ";" + energy.getRareza() + ";" + energy.getTipo() + ";"
					+ energy.getElemento();
		}

		return linea;
	}

	public static void verLista(ArrayList<Carta> listaCarta) {

		for (int i = 0; i < listaCarta.size(); i++) {
			System.out.println(listaCarta.get(i));

		}

	}

	public static void guardarArchivo(String nombreArchivo, ArrayList<Carta> listaCarta) throws FileNotFoundException {

		PrintWriter escritor = new PrintWriter(nombreArchivo);
		for (int i = 0; i < listaCarta.size(); i++) {

			String linea = convertirCartaALinea(listaCarta.get(i));
			escritor.println(linea);

		}
		escritor.close();
	}

}
