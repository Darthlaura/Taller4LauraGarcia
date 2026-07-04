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
		if (rutaCarta== null) {
			this.rutaCarta = rutaCarta;
			
			
		}
	}
		
	
	
	public abstract double aceptarVisitor(Visitor visitor);
		

	

    
	
	
	
    
    
}
