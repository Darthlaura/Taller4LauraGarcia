package gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

import dominio.*;
import logica.*;

public class Ventana extends JFrame {

    private SistemaImple sistema;
    private JTextArea areaColeccion;

    public Ventana() {

        sistema = SistemaImple.getInstancia();

        setTitle("Colección Pokémon TCG");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaColeccion = new JTextArea(25, 70);
        areaColeccion.setEditable(false);
        areaColeccion.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaColeccion);

        JTabbedPane pestanas = new JTabbedPane();

        JPanel panelAdministracion = new JPanel();
        panelAdministracion.setLayout(new BorderLayout());

        JPanel panelColeccion = new JPanel();
        panelColeccion.setLayout(new BorderLayout());

        JButton botonAgregar = new JButton("Agregar Carta");
        JButton botonEliminar = new JButton("Eliminar Carta");
        JButton botonModificar = new JButton("Modificar Carta");

        JButton botonVerColeccion = new JButton("Ver Colección");
        JButton botonOrdenarNombre = new JButton("Ordenar por Nombre");
        JButton botonOrdenarRareza = new JButton("Ordenar por Rareza");
        JButton botonOrdenarPoder = new JButton("Ordenar por Poder");
        JButton botonVerDetalle = new JButton("Ver Detalle");

        JPanel panelBotonesAdministracion = new JPanel();
        panelBotonesAdministracion.add(botonAgregar);
        panelBotonesAdministracion.add(botonEliminar);
        panelBotonesAdministracion.add(botonModificar);

        JTextArea instrucciones = new JTextArea();
        instrucciones.setEditable(false);
        instrucciones.setFont(new Font("Monospaced", Font.PLAIN, 14));
        instrucciones.setText("=========ADMINISTRACIÓN DE CARTAS=========\n\n"  + "- Agregar una carta nueva.\n" + "- Eliminar una carta usando su número.\n"
                + "- Modificar una carta.\n\n"  );

        panelAdministracion.add(panelBotonesAdministracion, BorderLayout.NORTH);
        panelAdministracion.add(instrucciones, BorderLayout.CENTER);

        JPanel panelBotonesColeccion = new JPanel();
        panelBotonesColeccion.add(botonVerColeccion);
        panelBotonesColeccion.add(botonOrdenarNombre);
        panelBotonesColeccion.add(botonOrdenarRareza);
        panelBotonesColeccion.add(botonOrdenarPoder);
        panelBotonesColeccion.add(botonVerDetalle);

        panelColeccion.add(panelBotonesColeccion, BorderLayout.NORTH);
        panelColeccion.add(scroll, BorderLayout.CENTER);

        pestanas.addTab("Administración", panelAdministracion);
        pestanas.addTab("Ver Colección", panelColeccion);

        add(pestanas);

        botonAgregar.addActionListener(e -> {

            String nombreCarta = pedirTexto("Ingrese el nombre de la carta:");

            if (nombreCarta == null) {
                return;
            }

            int rareza = pedirEntero("Ingrese la rareza:");

            if (rareza < 0) {
                return;
            }

            String tipo = pedirTexto("Ingrese el tipo de carta: Pokemon, Item, Supporter o Energy");

            if (tipo == null) {
                return;
            }

            Carta nuevaCarta = null;

            if (tipo.equalsIgnoreCase("Pokemon")) {

                int dano = pedirEntero("Ingrese el daño del Pokémon:");

                if (dano < 0) {
                    return;
                }

                int cantEnergias = pedirEntero("Ingrese la cantidad de energías:");

                if (cantEnergias <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad de energías debe ser mayor que 0.");
                    return;
                }

                nuevaCarta = new Pokemon(nombreCarta, rareza, "Pokemon", dano, cantEnergias);

            } else if (tipo.equalsIgnoreCase("Item")) {

                int bonificacion = pedirEntero("Ingrese la bonificación:");

                if (bonificacion < 0) {
                    return;
                }

                nuevaCarta = new Item(nombreCarta, rareza, "Item", bonificacion);

            } else if (tipo.equalsIgnoreCase("Supporter")) {

                int efectosPorTurno = pedirEntero("Ingrese los efectos por turno:");

                if (efectosPorTurno < 0) {
                    return;
                }

                nuevaCarta = new Supporter(nombreCarta, rareza, "Supporter", efectosPorTurno);

            } else if (tipo.equalsIgnoreCase("Energy")) {

                String elemento = pedirTexto("Ingrese el elemento:");

                if (elemento == null) {
                    return;
                }

                nuevaCarta = new Energy(nombreCarta, rareza, "Energy", elemento);

            } else {

                JOptionPane.showMessageDialog(this, "Tipo de carta no válido.");
                return;
            }

            sistema.agregarCarta(nuevaCarta);
            sistema.guardarCambios();

            actualizarArea(sistema.getListaCarta());

            pestanas.setSelectedIndex(1);

            JOptionPane.showMessageDialog(this, "Carta agregada correctamente.");
        });

        botonEliminar.addActionListener(e -> {

            actualizarArea(sistema.getListaCarta());

            int indice = pedirEntero("Ingrese el número de la carta que desea eliminar:");

            if (indice < 0) {
                return;
            }

            boolean eliminado = sistema.eliminarCartaPorIndice(indice);

            if (eliminado == true) {

                sistema.guardarCambios();

                actualizarArea(sistema.getListaCarta());

                pestanas.setSelectedIndex(1);

                JOptionPane.showMessageDialog(this, "Carta eliminada correctamente.");

            } else {

                JOptionPane.showMessageDialog(this, "No existe una carta con ese número.");
            }
        });

        botonModificar.addActionListener(e -> {

            actualizarArea(sistema.getListaCarta());

            int indice = pedirEntero("Ingrese el número de la carta que desea modificar:");

            if (indice < 0) {
                return;
            }

            ArrayList<Carta> lista = sistema.getListaCarta();

            if (indice >= lista.size()) {
                JOptionPane.showMessageDialog(this, "No existe una carta con ese número.");
                return;
            }

            Carta carta = lista.get(indice);

            boolean modificado = false;

            if (carta instanceof Pokemon) {

                int nuevoDano = pedirEntero("Ingrese el nuevo daño del Pokémon:");

                if (nuevoDano < 0) {
                    return;
                }

                int nuevaCantidadEnergias = pedirEntero("Ingrese la nueva cantidad de energías:");

                if (nuevaCantidadEnergias <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad de energías debe ser mayor que 0.");
                    return;
                }

                modificado = sistema.modificarCarta(indice, "" + nuevoDano, "" + nuevaCantidadEnergias);

            } else if (carta instanceof Item) {

                int nuevaBonificacion = pedirEntero("Ingrese la nueva bonificación:");

                if (nuevaBonificacion < 0) {
                    return;
                }

                modificado = sistema.modificarCarta(indice, "" + nuevaBonificacion, "");

            } else if (carta instanceof Supporter) {

                int nuevoEfectosPorTurno = pedirEntero("Ingrese los nuevos efectos por turno:");

                if (nuevoEfectosPorTurno < 0) {
                    return;
                }

                modificado = sistema.modificarCarta(indice, "" + nuevoEfectosPorTurno, "");

            } else if (carta instanceof Energy) {

                String nuevoElemento = pedirTexto("Ingrese el nuevo elemento:");

                if (nuevoElemento == null) {
                    return;
                }

                modificado = sistema.modificarCarta(indice, nuevoElemento, "");
            }

            if (modificado == true) {

                sistema.guardarCambios();

                actualizarArea(sistema.getListaCarta());

                pestanas.setSelectedIndex(1);

                JOptionPane.showMessageDialog(this, "Carta modificada correctamente.");

            } else {

                JOptionPane.showMessageDialog(this, "No se pudo modificar la carta.");
            }
        });

        botonVerColeccion.addActionListener(e -> {

            actualizarArea(sistema.getListaCarta());
        });

        botonOrdenarNombre.addActionListener(e -> {

            ArrayList<Carta> listaOrdenada = sistema.ordenarCartas(new StrategyNombre());

            actualizarArea(listaOrdenada);
        });

        botonOrdenarRareza.addActionListener(e -> {

            ArrayList<Carta> listaOrdenada = sistema.ordenarCartas(new StrategyRareza());

            actualizarArea(listaOrdenada);
        });

        botonOrdenarPoder.addActionListener(e -> {

            ArrayList<Carta> listaOrdenada = sistema.ordenarCartas(new StrategyPoder());

            actualizarArea(listaOrdenada);
        });

        botonVerDetalle.addActionListener(e -> {

            actualizarArea(sistema.getListaCarta());

            int indice = pedirEntero("Ingrese el número de la carta que desea ver en detalle:");

            if (indice < 0) {
                return;
            }

            ArrayList<Carta> lista = sistema.getListaCarta();

            if (indice >= lista.size()) {
                JOptionPane.showMessageDialog(this, "No existe una carta con ese número.");
                return;
            }

            Carta carta = lista.get(indice);

            Visitor1 visitor = new Visitor1();

            double poder = carta.aceptarVisitor(visitor);

            JOptionPane.showMessageDialog(this,
                    "DETALLE DE CARTA\n\n"
                    + carta.toString() + "\n"
                    + "Poder calculado: " + poder + "\n"
                    + "Ruta imagen: " + carta.getRutaCarta()
            );
        });

        actualizarArea(sistema.getListaCarta());

        setVisible(true);
    }

    private void actualizarArea(ArrayList<Carta> lista) {

        areaColeccion.setText("");

        for (int i = 0; i < lista.size(); i++) {
            areaColeccion.append(i + " - " + lista.get(i).toString() + "\n");
        }
    }

    private String pedirTexto(String mensaje) {

        String texto = JOptionPane.showInputDialog(this, mensaje);

        if (texto == null) {
            return null;
        }

        if (texto.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un dato.");
            return null;
        }

        return texto.trim();
    }

    private int pedirEntero(String mensaje) {

        String texto = JOptionPane.showInputDialog(this, mensaje);

        if (texto == null) {
            return -1;
        }

        if (texto.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un número.");
            return -1;
        }

        int numero = -1;

        try {
            numero = Integer.parseInt(texto.trim());
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un número válido.");
            return -1;
        }

        return numero;
    }
}