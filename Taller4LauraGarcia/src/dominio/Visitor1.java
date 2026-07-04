package dominio;

public class Visitor1 implements Visitor {

	@Override
	public double visitarEnergy(Energy energy) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double visitarPokemon(Pokemon pokemon) {
		
		return  (((double)  pokemon.getDaño()/ pokemon.getCantidadEnergia()) *100);
		
	}

	@Override
	public double visitarItem(Item item) {
	   return (item.getBonificacion() * 20) ;
	}

	@Override
	public double visitarSupporter(Supporter supporter) {
		return (supporter.getEfectorPorturno() *50) ;
	}

	
}
