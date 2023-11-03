package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PanelJugador extends JPanel {
	
	private JLabel lbJugadas;
	private JLabel lbJugador;
	private JLabel lbPuntaje;
	
	private JTextField txtJugadas;
	private JTextField txtJugador;
	private JTextField txtPuntaje;
	
	public PanelJugador() {
		
		setPreferredSize(new Dimension(190, 50));
		setBackground(Color.GRAY);
		setLayout(new GridLayout(1, 4));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		
		lbJugadas = new JLabel("Jugadas: ");
		lbJugadas.setHorizontalAlignment(SwingConstants.CENTER);
		txtJugadas = new JTextField();
		txtJugadas.setText("0");
		txtJugadas.setEditable(false);
		txtJugadas.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtJugadas.setBackground(Color.LIGHT_GRAY);
		txtJugadas.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbJugador = new JLabel("Jugador: ");
		lbJugador.setHorizontalAlignment(SwingConstants.CENTER);
		txtJugador = new JTextField();
		txtJugador.setText(" ");
		txtJugador.setEditable(false);
		txtJugador.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtJugador.setBackground(Color.LIGHT_GRAY);
		txtJugador.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbPuntaje = new JLabel("Puntaje: ");
		lbPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		txtPuntaje = new JTextField();
		txtPuntaje.setText("0");
		txtPuntaje.setEditable(false);
		txtPuntaje.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), BorderFactory.createEmptyBorder(5, 10, 5, 10)));
		txtPuntaje.setBackground(Color.LIGHT_GRAY);
		txtPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lbJugadas);
		add(txtJugadas);
		add(lbJugador);
		add(txtJugador);
		add(lbPuntaje);
		add(txtPuntaje);
	}

	public void actualizarJugador(String nombre) {
		
		txtJugador.setText(nombre);
	}
	
	public void actualizarJugadas(int nJugadas) {
		
		txtJugadas.setText(String.valueOf(nJugadas));
	}
	
	public void actualizarPuntaje(int puntaje) {
		
		txtPuntaje.setText(String.valueOf(puntaje));
	}
	
	public String getNombreJugador() {
		
		return txtJugador.getText();
	}
}