package dominio;
//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 


public class Supporter extends Carta {

    private int efectosPorTurno;

    public Supporter(String nombreCarta, int rareza, String tipo, int efectosPorTurno) {
        super(nombreCarta, rareza, tipo);
        this.efectosPorTurno = efectosPorTurno;
    }

    public int getEfectosPorTurno() {
        return efectosPorTurno;
    }

    public void setEfectosPorTurno(int efectosPorTurno) {
        this.efectosPorTurno = efectosPorTurno;
    }

    @Override
    public String toString() {
        return "Supporter | Nombre: " + getNombreCarta()
                + " | Rareza: " + getRareza()
                + " | Efectos por turno: " + getEfectosPorTurno();
    }

    @Override
    public double aceptarVisitor(Visitor visitor) {
        return visitor.visitarSupporter(this);
    }
}