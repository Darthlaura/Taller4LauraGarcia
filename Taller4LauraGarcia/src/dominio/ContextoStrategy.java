package dominio;


import java.util.ArrayList;

public class ContextoStrategy {

	private Strategy estrategiaDeOrden;

	public ContextoStrategy(Strategy estrategiaDeOrden) {
		this.estrategiaDeOrden = estrategiaDeOrden;
	} 
	
	
	public ArrayList<Carta> ordenar(ArrayList<Carta> listaCarta){
		return estrategiaDeOrden.ordenarCarta(listaCarta); 
	
		
	}
	
	public void setEstrategiaOrden(Strategy estrategiaOrden) {
		this.estrategiaDeOrden = estrategiaOrden; 
		
	}
	
	
	
	
}
