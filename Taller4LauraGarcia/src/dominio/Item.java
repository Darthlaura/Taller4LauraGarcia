package dominio;

public class Item  extends Carta {
	
	private int bonificacion;

	public Item(String nombreCarta, int rareza, String tipo, int bonificacion) {
		super(nombreCarta, rareza, tipo);
		this.bonificacion = bonificacion;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	
	
	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}

	@Override
	public String toString() {
		 return "Item | Nombre: " + getNombreCarta()
         + " | Rareza: " + getRareza()
         + " | Bonificación: " + getBonificacion();
	}


	@Override
	public double aceptarVisitor(Visitor visitor) {
	   return visitor.visitarItem(this);
		
	} 
	
	
	

}
