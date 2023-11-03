package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class PanelConfiguracion extends JPanel implements ActionListener{
	
	private Interfaz interfaz;
	
	private ButtonGroup btnGrupo;
	
	private JRadioButton rBtnFacil;
	private JRadioButton rBtnNormal;
	private JRadioButton rBtnDificil;
	
	private JComboBox<String> cBxTablero;
	
	private JLabel lbSize;
	private JLabel lbDificultad;
	
	
	private int facil;
	private int normal;
	private int dificil;
	
	public PanelConfiguracion(Interfaz interfaz) {
		
		this.interfaz = interfaz;
		
		facil = 1;
		normal = 2;
		dificil = 3;
		
		setPreferredSize(new Dimension(850, 50));
		setLayout(new FlowLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.BLUE);
		
		
		lbSize = new JLabel("Tamaño: ");
		
		String[] sizeOpciones = {"4x4", "5x5", "6x6", "7x7"};
		
		cBxTablero = new JComboBox<String>(sizeOpciones);
		cBxTablero.setPreferredSize(new Dimension(150, 25));
		cBxTablero.setActionCommand("SIZE");
        cBxTablero.addActionListener(this);
		
		lbDificultad = new JLabel("Dificultad: ");
		
		rBtnFacil = new JRadioButton("Fácil");
		rBtnFacil.setActionCommand("FACIL");
		rBtnFacil.addActionListener(this);
		
		rBtnNormal = new JRadioButton("Normal");
		rBtnNormal.setActionCommand("NORMAL");
		rBtnNormal.addActionListener(this);
		
		rBtnDificil = new JRadioButton("Dificil");
		rBtnDificil.setActionCommand("DIFICIL");
		rBtnDificil.addActionListener(this);
		
		rBtnNormal.setSelected(true);
		
		btnGrupo = new ButtonGroup();
		btnGrupo.add(rBtnFacil);
		btnGrupo.add(rBtnNormal);
		btnGrupo.add(rBtnDificil);
		
        lbSize.setForeground(Color.WHITE);
        
        lbDificultad.setForeground(Color.WHITE);
        
        rBtnFacil.setBackground(Color.BLUE);
        rBtnFacil.setForeground(Color.WHITE);
        
        rBtnNormal.setBackground(Color.BLUE);
        rBtnNormal.setForeground(Color.WHITE);
        
        rBtnDificil.setBackground(Color.BLUE);
        rBtnDificil.setForeground(Color.WHITE);
        
		add(lbSize); 
		add(cBxTablero);
		add(lbDificultad);
		add(rBtnFacil);
		add(rBtnNormal);
		add(rBtnDificil);
	}
	
	public int getTamanoTablero() {
		
		String sizeNxN = (String) cBxTablero.getSelectedItem();
		int size;
		if (sizeNxN.length() == 3) {
			size = Integer.parseInt(sizeNxN.substring(0, 1));
		} else {
			size = Integer.parseInt(sizeNxN.substring(0, 2));
		}
		
		return size;
	}
	
	public int getDificultad(){
		
		int dif;
		int cBxTablero = getTamanoTablero();
		ButtonModel seleccion = btnGrupo.getSelection();
		String dificultad = seleccion.getActionCommand();
		if (dificultad.equals("FACIL")) {
			dif = facil;
		} else if(dificultad.equals("NORMAL")){
			dif = normal;
		} else if (dificultad.equals("DIFICIL")){
			dif = dificil;
		}
		
		dif = cBxTablero;
		
		return dif;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		if(cmd.equals("SIZE")) {
			interfaz.nuevo(getTamanoTablero(), getDificultad());
		} else{
			interfaz.nuevo(getTamanoTablero(), getDificultad());
		}
	}
}
