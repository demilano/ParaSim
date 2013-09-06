package movTest;

import java.awt.Color;
import java.awt.Graphics;

public class Paramecium {
	
	private double x; // Co-ordinates on screen
	private double y;
	private int coX; // Real co-ordinates
	private int coY;
	private double speed; // Velocity
	private double ang; // Current angle
	private double scX; // cosine of angle
	private double scY; // sin of angle
	private double vX;
	private double vY;
	private double res; // resistance

	/** Random paramecium
	 * 
	*/
	public Paramecium(){
		this.x = Math.random()*300;
		this.y = Math.random()*300;
		this.speed = 5;
		this.ang = Math.random()*(2*Math.PI);
		res = 0.1;
		flux = 0;
	}
	
	/** Paramecium with assigned co-ordinates
	 * 
	 */
	public Paramecium(int x, int y){
		
	}
	
	public static Paramecium paraGen() {
		return new Paramecium();
	}

	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillOval((int)x, (int)y, 27, 27);

		g.setColor(Color.YELLOW);
		g.fillOval((int)(x-(scX*30+(flux*swayX))), (int)(y-(scY*30+(flux*swayY))), 30, 30);
		g.drawString("Speed: " + speed, 100, 420);
	}
	
	private double flux; // Fluxuates between -1 and 1
	private double swayX;
	private double swayY;
	private double fluxInc = 0.1;
	
	/**
	 * Speed should reduce to quite low if feeding
	 * Collision detection should be implemented for the front circle and rear circle
	 * The change in angle for when trying a new angle should be gradual
	 * Possibly try alternating angles, but not til later. Atm, just go clockwise (but allow for different implementation later)
	 * Eventually everything will be determined by outside stimulus: food, currents perhaps, acidity, light
	 * Nothing in this simulation save initialisation shall be random.
	 */
	public void move() {
		// Calculate scales
		scX = Math.cos(ang);
		scY = Math.sin(ang);
		// Calculate fluxuation
		swayX = Math.cos(ang+(Math.PI/2));
		swayY = Math.sin(ang+(Math.PI/2));
		// Calculate velocities
		vX = (speed * scX) + (flux * swayX);
		vY = (speed * scY) + (flux * swayY);
		// Make sure flucuates between 1 and -1
		flux+=fluxInc;
		if(flux>=1 || flux<=-1){
			fluxInc = -fluxInc;
		}
		// To change. If reversing,
		if(speed<0){
			speed = speed + 0.3; // Keep trying to go forward
			if(speed>(-5)){ // If gone backwards far enough, 
				ang = ang + 0.5; // Try new angle
				speed = 0.1; // Go forward starting at speed ...
			}
		}
		if(speed>0 && speed<5){ // If going forward and not at max speed, accelerate
			speed+=0.5;
		}
		
		// Calculate new x and y positions (on screen)
		x = x + vX;
		y = y + vY;
		
		// Make sure it doesn't overstep boundaries (only edge boundaries)
		if((y<30 || x<30 || x>600 || y>400 ) && speed>0){ // or has collided
			speed = -(speed+2);
		}
		if(x<30){ x=30; }
		if(x>600){ x=600; }
		if(y<30){ y=30; }
		if(y>400){ y=400; }
		// This will eventually change to be entirely vector based
		// With transformations for rotation and scale
		// This will streamline it immensely, and allow for better graphics
		// Each vertex may also represent individual cillia in a way, to better detect collision etc
		// Also, the fluctuation will not be fake. It will actually rotate (screw) around an axis
	}



}
