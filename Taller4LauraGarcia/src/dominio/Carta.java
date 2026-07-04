//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 




package dominio;

public  abstract class Carta {
	
	
 	protected String nombreCarta;
    protected int rareza; 
    protected String tipo;
    protected String rutaCarta;
    
    
    
	public Carta(String nombreCarta, int rareza, String tipo) {
		this.nombreCarta = nombreCarta;
		this.rareza = rareza;
		this.tipo = tipo;
        this.rutaCarta =  "imagenes/"+ nombreCarta + ".png";  
	}



	public String getNombreCarta() {
		return nombreCarta;
	}



	public int getRareza() {
		return rareza;
	}



	public String getTipo() {
		return tipo;
	}



	public String getRutaCarta() {
		return rutaCarta;
	}



	public void setRutaCarta(String rutaCarta) {
	    if (rutaCarta != null && !rutaCarta.trim().equals("")) {
	        this.rutaCarta = rutaCarta;
	    }
	
	}
		
	/**
	 * Metodo abstracto que permite aplicar un visitor sobre la carta para realizar una operación
	 *
	 * @param visitor visitante que realiza la operación sobre la carta
	 * @return resultado numérico entregado por el visitor
	 */
	public abstract double aceptarVisitor(Visitor visitor);
		

	

    
	
	
	
    
    
}
