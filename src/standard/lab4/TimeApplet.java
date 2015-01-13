package standard.lab4;


import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;

public class TimeApplet extends Applet implements Runnable{
	
	public void init() {
		Thread th = new Thread (this);

		th.start();

	}

	public void paint (Graphics g) {

		g.drawString(new Date()+"",250,100);
	}

	public void run () {
		while(true) {

			repaint();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}