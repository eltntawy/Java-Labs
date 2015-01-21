package main.java.se.lab4;


import java.applet.Applet;
import java.awt.*;

public class BallApplet extends Applet implements Runnable{

	Image image;
	Graphics graphics;

	Ball ballArray [] = {
		new Ball(1000,1000), 
		new Ball(10,-1000),
		new Ball(-200,250) ,
		new Ball(300,-600),
		new Ball(-400,400),
		new Ball(500,-500) ,
		new Ball(-600,600),
		new Ball(700,-700),
		new Ball(-800,800),
		new Ball(900,-900) ,
		new Ball(-100,1000),
		new Ball(1010,-1010),
		new Ball(-920,120) ,
		new Ball(830,-130),
		new Ball(-640,140),
		new Ball(550,-150) ,
		new Ball(-160,160),
		new Ball(270,-170),
		new Ball(-480,180),
		new Ball(590,-190) ,
		new Ball(-600,200)
	};
	

	@Override
	public void init() {

		Button b = new Button();
		
		
		setSize(1000, 500);
		Thread th = new Thread (this);

		th.start();

	}

	class Ball {
		int x=50,y=50;
		public static final int radius = 20;

		int xDir = -1;
		int yDir = -1;

		public Ball (int x , int y) {
			this.x = x ;
			this.y = y;
		}

		int getX() {return x; }
		int getY() {return y;}

	}

	@Override
	public void paint (Graphics g) {


		boolean is= false;

		
		for(int i = 0 ; i < ballArray.length ; i++) {
			if(is) {
        		g.setColor(Color.red);	
        	} else {
        		g.setColor(Color.green);
        	}

        	g.fillOval(ballArray[i].getX(), ballArray[i].getY() , Ball.radius, Ball.radius);
        	is = !is;
        }
	}
	

	@Override
	public void update (Graphics g) {
		
	        image = createImage(getWidth(), getHeight());
	        graphics = image.getGraphics();


	    graphics.setColor(getBackground());
	    graphics.fillRect(0,  0,  getWidth(),  getHeight());
	    graphics.setColor(getForeground());
	    paint(graphics);
	    g.drawImage(image, 0, 0, this);
	}

	public void run () {
		while(true) {
			//getHeight()
			//getWidth()

			repaint();
		for(int i = 0 ; i < ballArray.length ; i++) {
			if( ballArray[i].x == 0  || ballArray[i].x == getWidth()) {
				ballArray[i].xDir *=-1;
			}
			if(ballArray[i].y == 0 || ballArray[i].y == getHeight()) {
				ballArray[i].yDir *=-1;
			}

			
			if(ballArray[i].xDir > 0) {
				ballArray[i].x--;
			} else {
				ballArray[i].x++;
			}
		

		
			if(ballArray[i].yDir > 0 ) {
				ballArray[i].y--;
			} else {
				ballArray[i].y++;
			}
		
			if ( ballArray[i].x > getWidth() ) {
				ballArray[i].x = getWidth()-1;
			}
			if(ballArray[i].y > getHeight()) {
				ballArray[i].y = getHeight()-1;
			}

			if(ballArray[i].x < 0 ) {
				ballArray[i].x = 0;
			}
			if (ballArray[i].y < 0) {
				ballArray[i].y = 0;
			}


			for(int j = 0 ; j < ballArray.length ; j++) {
				for(int k = j ; k < ballArray.length ; k++) {

					double dx = (ballArray[k].x + Ball.radius) - (ballArray[j].x + Ball.radius);
					double dy = (ballArray[k].y + Ball.radius) - (ballArray[j].y + Ball.radius);
					double distance = Math.sqrt(dx * dx + dy * dy);
					
					//System.out.println(ballArray[k].x + " " + ballArray[k].y +" - " +ballArray[j].x +" "+ ballArray[j].y);

					if(distance <= Ball.radius ) {
						
						ballArray[j].xDir *= -1;
						ballArray[k].xDir *= -1;

						ballArray[j].yDir *= -1;
						ballArray[k].yDir *= -1;
						
					}
				}
			}

		}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	boolean between (int x, int y , int size ) {

		if( x < y -10 && x <= y +10) {
			return true;
		}
		return false;
	}

}