package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaVictoria extends JDialog{
	
	private Interfaz interfaz;
	private JLabel lbWin;
	
	
	public VentanaVictoria(Interfaz interfaz) {
		
		this.interfaz = interfaz;
		
		setBackground(Color.BLUE);
		setSize(new Dimension(400, 100));
		setLayout(new GridBagLayout());
        setResizable(false);
        setLocation(200, 300);
        setTitle("LO LOGRASTE YAY!");
        
        lbWin = new JLabel("¡¡¡Ganaste!!!");
        lbWin.setHorizontalAlignment(SwingConstants.CENTER);
        
        GridBagConstraints b = new GridBagConstraints();
		b.gridx = 0;
		b.gridy = 0;
		b.fill = GridBagConstraints.HORIZONTAL;
		b.weightx = 1;
		b.insets = new Insets(5, 5, 5, 5);
		add(lbWin, b);
		setVisible(true);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	setVisible(false);
                dispose();
                interfaz.nuevo();
                interfaz.setEnabled(true);
            }
        });
	}

}
