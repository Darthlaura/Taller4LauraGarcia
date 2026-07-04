package dominio;

public class Energy  extends Carta{
	
	
	private String elemento;

	public Energy(String nombreCarta, int rareza, String tipo, String elemento) {
		super(nombreCarta, rareza, tipo);
		this.elemento = elemento;
	}

	public String getElemento() {
		return elemento;
	}

	@Override
	public String toString() {
		 return "Energy | Nombre: " + getNombreCarta()
         + " | Rareza: " + getRareza()
         + " | Elemento: " + getElemento();
	}



	public void setElemento(String elemento) {
		this.elemento = elemento;
	}

	@Override
	public double aceptarVisitor(Visitor visitor) {
		return visitor.visitarEnergy(this);
		
	} 
	
	 

}
