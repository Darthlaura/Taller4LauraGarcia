package logica;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import dominio.*;
public class SistemaImple implements Sistema {

	private static SistemaImple instancia;
	private ArrayList<Carta> listaCarta;  
	
	
	
	private SistemaImple() {
		this.listaCarta = new ArrayList<Carta>();
		
		
	}

    public static SistemaImple getInstancia() {
    	if(instancia == null) {
    		instancia = new SistemaImple(); 
    	
    	}
    	
    	return instancia;
    }


	@Override
	public void cargarCartas() {
		
		try {
			listaCarta= GestorArchivo.lecturaArchivo("sobres.txt");
			GestorArchivo.verLista(listaCarta);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		System.out.println("hola");
		
	}
	
	
	
	public void agregarCarta(Carta carta) {
		listaCarta.add(carta); 
	
		
	}
	
	public boolean eliminarCartaPorIndice( int indice) {
		
		if (indice >=0 && indice <listaCarta.size() ) {
			//debe ser un indice mayor a cero y menor que el largo la lista
			listaCarta.remove(indice);
		return true;
		}
		return false; 
	}
	
	/**
	 * Metodo para modificar los valores de los elementos por consola 
	 * @param indice
	 * @param dato1
	 * @param dato2
	 * @return
	 */
	public boolean modificarCarta(int indice, String dato1, String dato2) {
		//si es indice es menor a cero o mas largo que la lista 
		if (indice <0 || indice >= listaCarta.size()) {
			return false; 
		}
		Carta carta = listaCarta.get(indice); // se modificara segun el indice que se introdusca
		
		try {
			
			if (carta instanceof Pokemon) {
				
				Pokemon pokemon =  (Pokemon)carta; 
				
				int nuevoDato = Integer.parseInt(dato1); 
				int nuevaCantidadEnergias = Integer.parseInt(dato2);
				pokemon.setDaño(nuevoDato);
				pokemon.setCantidadEnergia(nuevaCantidadEnergias);
				
				return true;
			}else if ( carta instanceof Item) {
				
				Item item = (Item) carta;
				
				int nuevaBonificacion = Integer.parseInt(dato1);
				item.setBonificacion(nuevaBonificacion);
				return true; 
				
			}else if (carta instanceof Supporter) {
				
				Supporter supporter = (Supporter) carta; 
				
				int nuevoEfectosPorTurno = Integer.parseInt(dato1); 
				supporter.setEfectorPorturno(nuevoEfectosPorTurno);
				return true;
				
			}else if ( carta instanceof Energy) {
                
				Energy energy = (Energy) carta; 
				String nuevoElemento = dato1; 
				energy.setElemento(nuevoElemento);
				return true;
				
				
				
			}
			
			
			
			
		}catch (NumberFormatException e) {
			System.out.println("Error; ingrese un numero valido");
			return false;
		}
		return false;
	}
	
	
	
	
	public void guardarCambios() {
		
		try {
	
			GestorArchivo.guardarArchivo("sobres.txt", listaCarta);
			System.out.println("Cambios guardados correctamente");
			
			
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo guardar el archivo");
		}
		
	}
	
	public ArrayList<Carta> ordenarCartas(Strategy estrategia) {

	    ContextoStrategy contexto = new ContextoStrategy(estrategia);

	    ArrayList<Carta> listaOrdenada = contexto.ordenar(listaCarta);

	    return listaOrdenada;
	}
	
	
	
	public void monstrarCartaConIndice() {
		
		for(int i=0; i < listaCarta.size(); i++) {
			System.out.println(i + " - " + listaCarta.get(i));
			
			
		}
	}

	public ArrayList<Carta> getListaCarta() {
		return listaCarta;
	}
	

}
