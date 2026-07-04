package logica;
//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 


import dominio.*;

/**
 * La clase Factory será la encargada de crear objetos Carta a partir de una línea de texto.
 * Aplica el patrón Factory para crear una carta según el tipo.
 */
public class FactoryLectura {

    /**
     *  Metodo para separar una línea del archivo sobres.txt y crear el objeto  de carta correspondiente.
     *
     * @param 
     * @return una Carta de tipo Pokemon, Item, Supporter o Energy. Retorna null si la línea no es válida.
     */
    public static Carta separarObjeto(String linea) {

        if (linea == null || linea.trim().equals("")) {
            return null;
        }

        String[] partes = linea.split(";");

        if (partes.length < 4) {
            System.out.println("Línea incompleta: " + linea);
            return null;
        }

        try {
            String nombreCarta = partes[0];
            int rareza = Integer.parseInt(partes[1]);
            String tipo = partes[2];

            if (tipo.equalsIgnoreCase("Pokemon")) {

                if (partes.length < 5) {
                    System.out.println("Línea Pokemon incompleta: " + linea);
                    return null;
                }

                int daño = Integer.parseInt(partes[3]);
                int cantidadEnergias = Integer.parseInt(partes[4]);

                if (cantidadEnergias <= 0) {
                    System.out.println("Cantidad de energías inválida: " + linea);
                    return null;
                }

                return new Pokemon(nombreCarta, rareza, tipo, daño, cantidadEnergias);

            } else if (tipo.equalsIgnoreCase("Item")) {

                int bonificacion = Integer.parseInt(partes[3]);
                return new Item(nombreCarta, rareza, tipo, bonificacion);

            } else if (tipo.equalsIgnoreCase("Supporter")) {

                int efectosPorTurno = Integer.parseInt(partes[3]);
                return new Supporter(nombreCarta, rareza, tipo, efectosPorTurno);

            } else if (tipo.equalsIgnoreCase("Energy")) {

                String elemento = partes[3];
                return new Energy(nombreCarta, rareza, tipo, elemento);

            } else {
                System.out.println("Tipo de carta no válido: " + linea);
                return null;
            }

        } catch (NumberFormatException error) {
            System.out.println("Error numérico en la línea: " + linea);
            return null;
        }
    }
}