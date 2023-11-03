package uniandes.dpoo.taller4.interfaz;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

import javax.swing.JFrame;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

public class Interfaz extends JFrame {
    private PanelConfiguracion configuracionPanel;
    private PanelTablero tableroPanel;
    private PanelOpcionesJuego botonesPanel;
    private PanelJugador jugadorPanel;
    
    private VentanaNombreJugador ventanaNombreJugador;
    private VentanaVictoria ventanaVictoria;
    private VentanaTop10 ventanaTop10;
    
    private Tablero tablero;
    private Top10 top10;

    public static void main(String[] args) {
        Interfaz interfaz = new Interfaz();
    }

    public Interfaz() {
        top10 = new Top10();
        String nombreArchivoTop = "data\\top10.csv";
        File archivoTop = new File(nombreArchivoTop);
        top10.cargarRecords(archivoTop);
        Collection<RegistroTop10> registros = top10.darRegistros();
        
        ventanaTop10 = new VentanaTop10(registros); 
        
        configuracionPanel = new PanelConfiguracion(this);
        botonesPanel = new PanelOpcionesJuego(this);
        jugadorPanel = new PanelJugador();

        int tamaño = configuracionPanel.getTamanoTablero();
        tablero = new Tablero(tamaño);
        tableroPanel = new PanelTablero(tablero.darTablero(), this);
        
        setBackground(Color.LIGHT_GRAY);
        setSize(new Dimension(850, 750));
        setLayout(new BorderLayout());
        
        add(tableroPanel, BorderLayout.WEST);
        add(jugadorPanel, BorderLayout.SOUTH);
        add(configuracionPanel, BorderLayout.NORTH);
        add(botonesPanel, BorderLayout.EAST);

        setResizable(false);
        setTitle("LightsOut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setEnabled(false);
        
        ventanaNombreJugador = new VentanaNombreJugador(this);
        
        while (ventanaNombreJugador.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        tablero.desordenar(tamaño);
        tableroPanel.actualizarTablero();
        setEnabled(true);
        
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                try {
                    File archivoTop = new File(nombreArchivoTop);
                    top10.salvarRecords(archivoTop);
                } catch (FileNotFoundException | UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void nuevo(int nuevoTamaño, int dificultad) {
        Tablero nuevoTablero = new Tablero(nuevoTamaño);
        setTablero(nuevoTablero);
        tablero.desordenar(dificultad);
        tableroPanel.setTablero(tablero.darTablero());
        tableroPanel.actualizarTablero();
        jugadorPanel.actualizarPuntaje(0);
        jugadorPanel.actualizarJugadas(tablero.darJugadas());
    }

    public void nuevo() {
        Tablero nuevoTablero = new Tablero(configuracionPanel.getTamanoTablero());
        setTablero(nuevoTablero);
        tablero.desordenar(configuracionPanel.getDificultad());
        tableroPanel.setTablero(tablero.darTablero());
        tableroPanel.actualizarTablero();
        jugadorPanel.actualizarJugadas(tablero.darJugadas());
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void nuevoJugador(String nombre) {
        jugadorPanel.actualizarJugador(nombre);
    }

    public void jugar(int i, int j) {
        tablero.jugar(i, j);
        jugadorPanel.actualizarJugadas(tablero.darJugadas());
        int puntaje = tablero.calcularPuntaje();
        jugadorPanel.actualizarPuntaje(puntaje);
        if (tablero.tableroIluminado()) {
            ventanaVictoria = new VentanaVictoria(this);
            if (top10.esTop10(puntaje)) {
                top10.agregarRegistro(jugadorPanel.getNombreJugador(), puntaje);
                Collection<RegistroTop10> registros = top10.darRegistros();
                ventanaTop10 = new VentanaTop10(registros);
                System.out.println("Nuevo Record");
            }
            setEnabled(false);
        }
    }

    public void reiniciar() {
        tablero.reiniciar();
        tableroPanel.actualizarTablero();
    }

    public void cambiarJugador() {
        ventanaNombreJugador = new VentanaNombreJugador(this);
    }

    public void mostrarTop10() {
        ventanaTop10.setVisible(true);
    }
}
