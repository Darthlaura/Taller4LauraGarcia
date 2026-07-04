package logica;
//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 



import java.io.FileNotFoundException;
import java.util.ArrayList;

import dominio.*;
public class SistemaImple implements Sistema {

	private static SistemaImple instancia;
	private ArrayList<Carta> listaCarta;  
	
	
	
	private SistemaImple() {
		this.listaCarta = new ArrayList<Carta>();
		
		
	}
    
	/**
	 * Metodo que retorna la única instancia del sistema.
	 * Si aún no existe, la crea.
	 *
	 * @return instancia única de SistemaImple
	 */

    public static SistemaImple getInstancia() {
    	if(instancia == null) {
    		instancia = new SistemaImple(); 
    	
    	}
    	
    	return instancia;
    }

    /**
     * Metodo que carga las cartas desde el archivo de datos inicial.
     */

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
	/**
	 * Metodo que elimina una carta de la colección usando su índice.
	 *
	 * @param indice posición de la carta dentro de la lista
	 * @return true si la carta fue eliminada, false si el índice no existe
	 */

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
				supporter.setEfectosPorTurno(nuevoEfectosPorTurno);
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
	
	
	
	/**
	 * Metodo que guarda la colección actual de cartas en el archivo sobres.txt.
	 */

	public void guardarCambios() {
		
		try {
	
			GestorArchivo.guardarArchivo("sobres.txt", listaCarta);
			System.out.println("Cambios guardados correctamente");
			
			
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo guardar el archivo");
		}
		
	}
	
	/**
	 * Metodo que ordena la colección de cartas utilizando una estrategia recibida por parámetro.
	 *
	 * @param estrategia estrategia de ordenamiento a utilizar
	 * @return lista de cartas ordenada
	 */
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
