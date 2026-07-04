package dominio;

public class Pokemon  extends Carta{
	
	
	private int daño; 
	private int cantidadEnergia;
	
	
	public Pokemon(String nombreCarta, int rareza, String tipo, int daño, int cantidadEnergia) {
		super(nombreCarta, rareza, tipo);
		this.daño = daño;
		this.cantidadEnergia = cantidadEnergia;
	}


	public int getDaño() {
		return daño;
	}


	public int getCantidadEnergia() {
		return cantidadEnergia;
	}


	public void setDaño(int daño) {
		this.daño = daño;
	}


	public void setCantidadEnergia(int cantidadEnergia) {
		this.cantidadEnergia = cantidadEnergia;
	}


	@Override
	public String toString() {
		 return "Pokémon | Nombre: " + getNombreCarta()
         + " | Rareza: " + getRareza()
         + " | Daño: " + getDaño()
         + " | Energías: " + getCantidadEnergia();
	}




	@Override
	public double aceptarVisitor(Visitor visitor) {
		return visitor.visitarPokemon(this);
		
	}

	
	
}
