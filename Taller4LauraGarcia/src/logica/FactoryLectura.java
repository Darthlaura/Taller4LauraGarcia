package logica;

import dominio.*;


public class FactoryLectura {

	
	public static Carta separarObjeto(String linea) {
		
		String[] partes = linea.split(";"); 
		
	    String nombreCarta =partes[0]; 
	    int rareza=Integer.parseInt(partes[1]);  
	    String tipo=partes[2];
	    
	    if (tipo.equalsIgnoreCase("pokemon")) {
	    	int dato = Integer.parseInt(partes[3]);
	    	int cantidadEnergias =  Integer.parseInt(partes[4]);
	    	return new Pokemon(nombreCarta, rareza, tipo, dato, cantidadEnergias); 
	    }else if  (tipo.equalsIgnoreCase("item")) {
	    	int bonificacion = Integer.valueOf(partes[3]); 
	    	return new Item(nombreCarta, rareza, tipo, bonificacion); 
	     	
	    }else if (tipo.equalsIgnoreCase("supporter")) {
	    	int efectorPorTurno = Integer.parseInt(partes[3]); 
	    	return new Supporter(nombreCarta, rareza, tipo, efectorPorTurno);
	    	
	    }else if (tipo.equalsIgnoreCase("energy")){
	    	String elemento = partes[3];
	    	return new Energy(nombreCarta, rareza, tipo, elemento);
	    }
		
		return null;
	}
	
	
	
}
