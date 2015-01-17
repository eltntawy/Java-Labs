package main.java.standard.lab5;


import java.applet.Applet;
import java.util.Date;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.*;
import java.awt.event.* ;

public class LineMouseApplet5 extends Applet {

	Image image;
	Graphics graphics;

	
	class Line {
		int x1,y1,x2,y2;

		public Line () {

		}
		public Line(int x1,int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = y2;
			this.y2 = y2;
		}
	}
	
	Line lines [] = {new Line(),new Line(),new Line(),new Line(),new Line()};
	int count = 0;

	boolean releaseLine = false ;

	public void init() {

		addMouseListener(new MouseListener () {

			
		public void mouseClicked(MouseEvent e) {}
		public void	mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			
			if(count < 5) {
				lines[count].x1 = e.getX();
				lines[count].y1 = e.getY();
			}
		 	

		}

		public void mouseReleased(MouseEvent e) {
				
				if(count < 5) {
					lines[count].x2 = e.getX();
					lines[count].y2 = e.getY();
									
					count++;
				}
				repaint();

		}
	});
		
		addMouseMotionListener(new MouseMotionListener () {

			public void mouseMoved (MouseEvent e) {
				repaint();
			}
			public void mouseDragged (MouseEvent e) {
				
				if(count < 5) {
					lines[count].x2 = e.getX();
					lines[count].y2 = e.getY();
					
				}
				repaint();
			}
      		});
		
		setSize(1000, 500);


	}

	public void paint (Graphics g) {
		
			g.setColor(Color.green);

			System.out.println(count);
			for ( int i = 0 ; i < lines.length ; i++ ) {
				g.drawLine(lines[i].x1,lines[i].y1,lines[i].x2,lines[i].y2);	
			}
        	

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