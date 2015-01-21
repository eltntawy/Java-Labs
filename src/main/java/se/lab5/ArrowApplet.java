package main.java.se.lab5;


import java.applet.Applet;
import java.awt.*;
import java.awt.event.* ;

public class ArrowApplet extends Applet {

	Image image;
	Graphics graphics;

	int x=50,y=50;

	@Override
	public void init() {

		setFocusable(true);
        //requestFocus();
        
		addKeyListener(new KeyListener () {

		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode());
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				y++;
				repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				y--;
				repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				x++;
				repaint();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				x--	;
				repaint();
			}
		}
		          
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {


		}
		          

		});

		setSize(1000, 500);

	}

	
	

	@Override
	public void paint (Graphics g) {

		g.drawString("Java World !!" ,x,y);
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


}