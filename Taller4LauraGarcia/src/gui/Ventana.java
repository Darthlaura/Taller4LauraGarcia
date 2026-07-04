package gui;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import dominio.*;
import logica.*;


//Autor: Laura Garcia 
//Rut 26427429-k
//Paralelo C2 


public class Ventana extends JFrame {

	private SistemaImple sistema;
	private JList<Carta> listaVisual;
	private DefaultListModel<Carta> modeloLista;

    public Ventana() {

        sistema = SistemaImple.getInstancia();

        setTitle("Colección Pokémon TCG");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloLista = new DefaultListModel<Carta>();

        listaVisual = new JList<Carta>(modeloLista);
        listaVisual.setFont(new Font("Monospaced", Font.PLAIN, 14));
        listaVisual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(listaVisual);

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

            Carta cartaSeleccionada = listaVisual.getSelectedValue();

            if (cartaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una carta de la colección.");
                return;
            }

            mostrarDetalleCarta(cartaSeleccionada);
        });
        actualizarArea(sistema.getListaCarta());
        
        listaVisual.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    Carta cartaSeleccionada = listaVisual.getSelectedValue();

                    if (cartaSeleccionada != null) {
                        mostrarDetalleCarta(cartaSeleccionada);
                    }
                }
            }
        });
        
        

        setVisible(true);
    }

    
    /**
     * Metodo que actualiza el área de texto que muestra la colección de cartas.
     * @param lista
     */
    private void actualizarArea(ArrayList<Carta> lista) {

        modeloLista.clear();

        for (int i = 0; i < lista.size(); i++) {
            modeloLista.addElement(lista.get(i));
        }
    }
    /**
     * Metodo que solicita un texto al usuario mediante una ventana de entrada.
     * @param mensaje
     * @return
     */
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
    /**
     * Metodo que solicita un número entero al usuario mediante una ventana de entrada.
     * @param mensaje
     * @return
     */
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
    
    /**
     * Metodo que muestra una vista ampliada de una carta, incluyendo sus atributos,
     * su poder calculado y su imagen correspondiente.
     * Si la imagen no existe, utiliza la imagen por defecto.
     *
     * @param carta carta que se desea visualizar en detalle
     */
    private void mostrarDetalleCarta(Carta carta) {
 

        Visitor1 visitor = new Visitor1();
        double poder = carta.aceptarVisitor(visitor);

        String rutaImagen = carta.getRutaCarta();

        File archivoImagen = new File(rutaImagen);

        if (!archivoImagen.exists()) {
            rutaImagen = "imagenes/default.png";
        }

        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);

        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(220, 300, Image.SCALE_SMOOTH);

        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);

        JLabel etiquetaImagen = new JLabel(iconoEscalado);

        JTextArea areaDetalle = new JTextArea();
        areaDetalle.setEditable(false);
        areaDetalle.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaDetalle.setText(
                "DETALLE DE CARTA\n\n"
                + carta.toString() + "\n"
                + "Poder calculado: " + poder + "\n"
                + "Ruta imagen: " + rutaImagen
        );

        JPanel panelDetalle = new JPanel();
        panelDetalle.setLayout(new BorderLayout());

        panelDetalle.add(etiquetaImagen, BorderLayout.WEST);
        panelDetalle.add(areaDetalle, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, panelDetalle, "Detalle de Carta", JOptionPane.PLAIN_MESSAGE);
    }
}