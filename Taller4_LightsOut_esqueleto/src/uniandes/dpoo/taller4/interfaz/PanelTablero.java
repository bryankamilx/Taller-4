package uniandes.dpoo.taller4.interfaz;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

public class PanelTablero extends JPanel implements MouseListener{
	
	private Interfaz interfaz;
	private boolean[][] tablero;
	
	public PanelTablero(boolean[][] tablero, Interfaz interfaz){
		
		this.interfaz = interfaz;
		this.tablero = tablero;
		
		setPreferredSize(new Dimension(610, 610));
		setBackground(Color.BLACK);
		addMouseListener(this);
	}
	
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

        int casillas = tablero.length;
        int casilla = (610-(casillas)*5)/casillas;
        
        
        for (int i = 0; i < casillas; i++){
        	boolean[] casy = tablero[i];
            for (int j = 0; j < casillas; j++){
            	boolean actual = casy[j];
                int x = j*(casilla+5)+1;
                int y = i*(casilla+5)+1;
                RoundRectangle2D.Double cuadrado = new RoundRectangle2D.Double(x, y, casilla, casilla,20,20);
                
                if (actual){
                	g2d.setColor(Color.YELLOW);                	
               }else{
                	g2d.setColor(Color.DARK_GRAY);
               }
                
                g2d.draw(cuadrado);
        		g2d.fill(cuadrado);
           }
       }
	}
	
	
	private int[] convertirCoordenadasAactual(int x, int y){
		
		int casillas = tablero.length;
        int casilla = (610-(casillas)*5)/casillas;
        int casy = y/(casilla+5);
        int casx = x/(casilla+5);
        return new int[]{casy, casx};
	}
	
	public void setTablero(boolean[][] tbl){
		
		this.tablero = tbl;
	}
	
    public void actualizarTablero(){
    	
    	repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e){
		
		int click_x = e.getX();
		int click_y = e.getY();
		
		int casillas = tablero.length;
        int casilla = (610-(casillas)*5)/casillas;
        int rango = (casilla*casillas+5*(casillas-1))+1;
        
        if (click_x <= rango && click_y <= rango){       	
        	int[] actual = convertirCoordenadasAactual(click_x, click_y);
        	interfaz.jugar(actual[0], actual[1]);
        	repaint();
       }
	}

	@Override
	public void mouseReleased(MouseEvent e){
		//TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e){
		//TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e){
		//TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e){
		//TODO Auto-generated method stub
	}
	
	
	
}
