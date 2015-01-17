package main.java.standard.lab5;


import java.applet.Applet;
import java.util.Date;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.*;
import java.awt.event.* ;

public class BallMouseApplet extends Applet {

	Image image;
	Graphics graphics;

	Ball ball = new Ball(100,100);
	
	

	public void init() {

		
		addMouseMotionListener(new MouseMotionListener () {

			public void mouseMoved (MouseEvent e) {}
			public void mouseDragged (MouseEvent e) {
				
				if( 0 < e.getX() && e.getX() < getWidth()-50 && 0 < e.getY() && e.getY() < getHeight()-50 )

				if ( ball.x -50 <= e.getX() && e.getX() <= ball.x +50 &&  
			 		 ball.y -50 <= e.getY() && e.getY() <= ball.y +50 ) {

			 		ball.x = e.getX()-25;
				 	ball.y = e.getY()-25;
			 	}
			 	repaint();
			 	System.out.println("X : "+e.getX() + " - " + "Y : "+e.getY());
				}
			          

      		});
		
		setSize(1000, 500);


	}

	class Ball {
		int x=50,y=50;
		public static final int radius = 50;

		int xDir = -1;
		int yDir = -1;

		public Ball (int x , int y) {
			this.x = x ;
			this.y = y;
		}

		int getX() {return x; }
		int getY() {return y;}

	}

	public void paint (Graphics g) {
		
			g.setColor(Color.green);

        	g.fillOval(ball.getX(), ball.getY() , Ball.radius, Ball.radius);

	}
	

	public void update (Graphics g) {
		
	        image = createImage(getWidth(), getHeight());
	        graphics = image.getGraphics();


	    graphics.setColor(getBackground());
	    graphics.fillRect(0,  0,  getWidth(),  getHeight());
	    graphics.setColor(getForeground());
	    paint(graphics);
	    g.drawImage(image, 0, 0, this);
	}

}