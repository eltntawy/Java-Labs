package standard.lab4;


import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;

public class MarqueeApplet extends Applet implements Runnable{
	

	private int x=10,y=10;

	public void init() {
		Thread th = new Thread (this);

		th.start();

	}

	public void paint (Graphics g) {

		g.drawString("Java World",x,y);
	}

	public void run () {
		while(true) {

			repaint();
			x++;

			if(x > getWidth()) {
				y +=10;
				x = 10;
			}

			if( y > getHeight() ) {
				y = 10;
			}

			try {
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}