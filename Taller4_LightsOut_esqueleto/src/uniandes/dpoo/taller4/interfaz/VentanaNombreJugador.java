package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VentanaNombreJugador extends JDialog implements ActionListener{
	
	private Interfaz interfaz;
	
	private JLabel lbJugador;
	private JTextField txtJugador;
	private JLabel lbMax;
	private JButton btnJugar;
	
	public VentanaNombreJugador(Interfaz interfaz) {
		
		this.interfaz = interfaz;
		setSize(new Dimension(100, 200));
		setLayout(new GridBagLayout());
        setResizable(false);
        setLocation(305, 305);
        
        lbJugador = new JLabel("NOMBRE");
        lbJugador.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtJugador = new JTextField();
        txtJugador.setPreferredSize(new Dimension(100, 35));
        
        btnJugar = new JButton("JUGAR");
        btnJugar.setForeground(Color.WHITE);
        btnJugar.setBackground(Color.BLUE);
        btnJugar.setActionCommand("JUGAR");
		btnJugar.addActionListener(this);
        
		lbMax = new JLabel("Max. 3 Caracteres");
        lbMax.setHorizontalAlignment(SwingConstants.CENTER);
        lbMax.setVerticalAlignment(SwingConstants.BOTTOM);
        lbMax.setPreferredSize(new Dimension(100, 10));
        
        GridBagConstraints b = new GridBagConstraints();
		b.gridx = 0;
		b.gridy = 0;
		b.fill = GridBagConstraints.HORIZONTAL;
		b.weightx = 1;
		b.insets = new Insets(5, 5, 5, 5);
		add(lbJugador, b);
		
		b.gridx = 0;
		b.gridy = 1;
		add(lbMax, b);
		
		b.gridx = 0;
		b.gridy = 2;
		add(txtJugador, b);
		
		b.gridx = 0;
		b.gridy = 3;
		add(btnJugar, b);

		setVisible(true);

	}
	
    public void actionPerformed(ActionEvent e) {
    	String cmd = e.getActionCommand();
    	if (cmd.equals("JUGAR")) {
    		String nombre = txtJugador.getText();
    		if (nombre.length() <= 3) {    			
    			interfaz.nuevoJugador(nombre);
    			dispose();
    		}
    	}
    }
    
}
