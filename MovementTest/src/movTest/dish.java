package movTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class dish extends JPanel {

	private final int PARACOUNT = 3; // The amount of initial paramecium
	private ArrayList<Paramecium> paraList; // The list of paramecium
	private Paramecium galvanis;
	
	/** Constructor
	 * Adds a certain amount of paramecium and begins their movement
	 */
	public dish(){
		setBackground(Color.CYAN);
		paraList = new ArrayList<Paramecium>();
		for(int i=0;i<PARACOUNT;i++){ // Add the paramecium to the list
			paraList.add(Paramecium.paraGen());
		}
		galvanis = Paramecium.paraGen();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new tTask(), 0, 30);
	}
	
	private class tTask extends TimerTask{
		public void run() {
			repaint();
		}
	}
	
	/** Paint 
	 * 
	 */
	public void paint(Graphics g){
		super.paint(g);
		galvanis.move();
		galvanis.draw(g);
		g.drawRect(30, 30, 600, 400);
		for(Paramecium p : paraList){
			p.move();
			p.draw(g);
		}
	}

	
}
