package dominio;

public class Supporter extends Carta {
	
	private int efectorPorturno;

	public Supporter(String nombreCarta, int rareza, String tipo, int efectorPorturno) {
		super(nombreCarta, rareza, tipo);
		this.efectorPorturno = efectorPorturno;
	}

	public int getEfectorPorturno() {
		return efectorPorturno;
	}

	
	
	public void setEfectorPorturno(int efectorPorturno) {
		this.efectorPorturno = efectorPorturno;
	}

	@Override
	public String toString() {
		 return "Supporter | Nombre: " + getNombreCarta()
         + " | Rareza: " + getRareza()
         + " | Efectos por turno: " + getEfectorPorturno();
	}



	@Override
	public double aceptarVisitor(Visitor visitor) {
		return visitor.visitarSupporter(this);
		
	} 
	
	
	

}
