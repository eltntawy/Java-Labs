package main.java.se.lab4;


import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;

public class TimeApplet extends Applet implements Runnable{
	
	@Override
	public void init() {
		Thread th = new Thread (this);

		th.start();

	}

	@Override
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