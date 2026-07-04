package dominio;

public  interface Visitor {
	
	
	
	public double visitarEnergy(Energy energy);
	public double visitarPokemon(Pokemon pokemon);
	public double visitarItem(Item item);
	public double visitarSupporter(Supporter supporter);
	
	
	
}
