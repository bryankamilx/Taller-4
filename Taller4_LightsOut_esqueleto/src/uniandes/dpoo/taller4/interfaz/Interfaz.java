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

public class Interfaz extends JFrame{
	
	private PanelConfiguracion panelEspeficaciones;
	private PanelTablero panelTablero;
	private PanelOpcionesJuego panelBotones;
	private PanelJugador panelJugador;
	
	private VentanaNombreJugador ventanaJugador;
	private VentanaVictoria ventanaVictoria;
	private VentanaTop10 ventanaTop10;
	
	private Tablero tablero;
	private Top10 top10;
	
	
	
	public static void main(String[] args) {
		
		Interfaz interfaz = new Interfaz();
	}
	
	public Interfaz() {
		
		top10 = new Top10();
		String nameArchivoTop = "data\\top10.csv";
		File archivoTop = new File(nameArchivoTop);
		top10.cargarRecords(archivoTop);
		Collection<RegistroTop10> records = top10.darRegistros();
		
		ventanaTop10 = new VentanaTop10(records); 
		
		panelEspeficaciones = new PanelConfiguracion(this);
		panelBotones = new PanelOpcionesJuego(this);
		panelJugador = new PanelJugador();

		int size = panelEspeficaciones.getTamanoTablero();
		tablero = new Tablero(size);
		panelTablero = new PanelTablero(tablero.darTablero(), this);
		
		setBackground(Color.LIGHT_GRAY);
		setSize(new Dimension(850, 750));
		setLayout(new BorderLayout());
		
		add(panelTablero, BorderLayout.WEST);
		
		add(panelJugador, BorderLayout.SOUTH);
		
		add(panelEspeficaciones, BorderLayout.NORTH);
		
		add(panelBotones, BorderLayout.EAST);
		

		setResizable(false);
		setTitle("LightsOut");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setEnabled(false);
		
		ventanaJugador = new VentanaNombreJugador(this);
		
		while (ventanaJugador.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
		
		tablero.desordenar(size);
		panelTablero.actualizarTablero();
		setEnabled(true);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				try {
					File archivoTop = new File(nameArchivoTop);
					top10.salvarRecords(archivoTop);
				} catch (FileNotFoundException | UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void nuevo(int newSize, int dificultad) {
		Tablero newTablero = new Tablero(newSize);
		setTablero(newTablero);
		tablero.desordenar(dificultad);
		panelTablero.setTablero(tablero.darTablero());
		panelTablero.actualizarTablero();
		panelJugador.actualizarPuntaje(0);
		panelJugador.actualizarJugadas(tablero.darJugadas());
	}
	
	public void nuevo() {
		Tablero newTablero = new Tablero(panelEspeficaciones.getTamanoTablero());
		setTablero(newTablero);
		tablero.desordenar(panelEspeficaciones.getDificultad());
		panelTablero.setTablero(tablero.darTablero());
		panelTablero.actualizarTablero();
		panelJugador.actualizarJugadas(tablero.darJugadas());
	}
	
	public void setTablero(Tablero tablero) {
		
		this.tablero = tablero;
	}

	public void nuevoJugador(String nombre) {
		
		panelJugador.actualizarJugador(nombre);
	}

	public void jugar(int i, int j) {
		tablero.jugar(i, j);
		panelJugador.actualizarJugadas(tablero.darJugadas());
		int puntaje = tablero.calcularPuntaje();
		panelJugador.actualizarPuntaje(puntaje);
		if (tablero.tableroIluminado()) {
			ventanaVictoria = new VentanaVictoria(this);
			if (top10.esTop10(puntaje)) {
				top10.agregarRegistro(panelJugador.getNombreJugador(), puntaje);
				Collection<RegistroTop10> records = top10.darRegistros();
				ventanaTop10 = new VentanaTop10(records);
				System.out.println("Nuevo Record");
			}
			setEnabled(false);
		}
	}
	
	public void reiniciar() {
		tablero.reiniciar();
		panelTablero.actualizarTablero();
	}
	
	public void cambiarJugador() {
		ventanaJugador = new VentanaNombreJugador(this);
	}
	
	public void mostrarTop10() {
		ventanaTop10.setVisible(true);
	}
	
	
}







