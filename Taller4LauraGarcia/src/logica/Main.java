package logica;
import gui.*;


//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SistemaImple sistema = SistemaImple.getInstancia(); 
		sistema.cargarCartas();; 

		
		Ventana ventana = new Ventana(); 

	}

}
