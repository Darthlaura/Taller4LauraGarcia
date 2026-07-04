package logica;
import gui.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SistemaImple sistema = SistemaImple.getInstancia(); 
		sistema.cargarCartas();; 

		
		Ventana ventana = new Ventana(); 

	}

}
