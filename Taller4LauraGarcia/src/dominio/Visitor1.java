package dominio;
//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 



public class Visitor1 implements Visitor {

    /**
     * Metodo para calcular el poder de una carta Energy.
     * 
     *
     * @param energy carta de tipo Energy
     * @return poder de la carta Energy
     */
    @Override
    public double visitarEnergy(Energy energy) {
        return 1;
    }

    /**
     * Metodo para calcular el poder de una carta Pokemon.
     *
     * @param pokemon carta de tipo Pokemon
     * @return poder calculado del Pokemon
     */
    @Override
    public double visitarPokemon(Pokemon pokemon) {

        if (pokemon.getCantidadEnergia() <= 0) {
            return 0;
        }

        return ((double) pokemon.getDaño() / pokemon.getCantidadEnergia()) * 100;
    }

    /**
     * Metodo para calcular el poder de una carta Item.
     *
     * @param item carta de tipo Item
     * @return poder calculado del Item
     */
    @Override
    public double visitarItem(Item item) {
        return item.getBonificacion() * 20;
    }

    /**
     * Metodo para calcular el poder de una carta Supporter.
     * @param supporter carta de tipo Supporter
     * @return poder calculado del Supporter
     */
    @Override
    public double visitarSupporter(Supporter supporter) {
        return supporter.getEfectosPorTurno() * 50;
    }
}