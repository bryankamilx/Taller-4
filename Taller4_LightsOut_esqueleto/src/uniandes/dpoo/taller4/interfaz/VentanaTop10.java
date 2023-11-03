package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collection;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import uniandes.dpoo.taller4.modelo.RegistroTop10;

public class VentanaTop10 extends JDialog{
	
	private JList<JPanel> lstTOP;
	private JScrollPane pnlScroll;
	private JLabel lbRecords;
	
	public VentanaTop10(Collection<RegistroTop10> registroTop10){
		
		setBackground(Color.WHITE);
		setSize(new Dimension(400, 550));
		setLayout(new FlowLayout());
        setResizable(false);
        setTitle("Top 10");
		
        lbRecords = new JLabel("# - JUGADOR - PUNTAJE");
		lbRecords.setPreferredSize(new Dimension(350, 50));
		lbRecords.setHorizontalAlignment(SwingConstants.CENTER);
        
		pnlScroll = new JScrollPane();
        pnlScroll.setPreferredSize(new Dimension(300, 400));
        
        add(lbRecords);
		
        int i = 1;
        
        DefaultListModel<JPanel> elementos = new DefaultListModel<>();
		
		for (RegistroTop10 registro : registroTop10) {
		    
			String name = registro.darNombre(); 
		    String points = Integer.toString(registro.darPuntos());
		    
		    JPanel panelAux = new JPanel();
		    panelAux.setLayout(new FlowLayout());
		    
		    JLabel lbPosicion = new JLabel(Integer.toString(i));
		    lbPosicion.setPreferredSize(new Dimension(50, 40));
		    lbPosicion.setHorizontalAlignment(SwingConstants.CENTER);
		    lbPosicion.setForeground(Color.RED);
		    
		    JLabel lbNombre = new JLabel(name);
		    lbNombre.setPreferredSize(new Dimension(100, 40));
		    lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		    if (i <= 3){
		    	lbNombre.setForeground(Color.YELLOW);
		    }
		    else {
		    	lbNombre.setForeground(Color.BLACK);
		    }
		    
		    JLabel lbPuntaje = new JLabel(points);
		    lbPuntaje.setPreferredSize(new Dimension(100, 40));
		    lbPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		    lbPuntaje.setForeground(Color.MAGENTA);
		    
		    panelAux.add(lbPosicion);
		    panelAux.add(lbNombre);
		    panelAux.add(lbPuntaje);
		    elementos.addElement(panelAux);
		    i++;
		}
		
		lstTOP = new JList<JPanel>(elementos);
		lstTOP.setCellRenderer(new PanelListCellRenderer());
		lstTOP.setSelectionBackground(Color.LIGHT_GRAY);
		lstTOP.setBackground(Color.LIGHT_GRAY);
		pnlScroll.setViewportView(lstTOP);
		
		add(pnlScroll);
		setVisible(false);
		
	}
	
	private class PanelListCellRenderer extends DefaultListCellRenderer {
	    public Component getListCellRendererComponent(JList<?> list,
	            Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        if(c instanceof JLabel) {
	            JPanel panel = (JPanel) value;
	            panel.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
	            panel.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
	            return panel;
	        }
	        return c;
	    }
	}
}
