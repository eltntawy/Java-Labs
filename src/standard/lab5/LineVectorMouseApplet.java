package standard.lab5;


import java.applet.Applet;
import java.util.Date;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.*;
import java.awt.event.* ;
import java.util.Vector;

public class LineVectorMouseApplet extends Applet {

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
	
	Vector <Line> lineVector   = new Vector<Line>();
	
	Line lineTemp = new Line();

	boolean releaseLine = false ;

	public void init() {

		addMouseListener(new MouseListener () {

			
		public void mouseClicked(MouseEvent e) {}
		public void	mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
			
			
				lineTemp.x1 = e.getX();
				lineTemp.y1 = e.getY();

				lineVector.add(lineTemp);		 	

		}

		public void mouseReleased(MouseEvent e) {
				
				
					lineTemp.x2 = e.getX();
					lineTemp.y2 = e.getY();
					
					lineTemp = new Line();
					repaint();


			for ( int i = 0 ; i < lineVector.size() ; i ++) {
				System.out.println(lineVector.get(i).x1+" "+lineVector.get(i).y1+" "+lineVector.get(i).x2 
					+" " +lineVector.get(i).y2);
			}				
					
					
				
		}
	});
		
		addMouseMotionListener(new MouseMotionListener () {

			public void mouseMoved (MouseEvent e) {
				repaint();
			}
			public void mouseDragged (MouseEvent e) {
				
				
					lineTemp.x2 = e.getX();
					lineTemp .y2 = e.getY();
					repaint();
				
				
			}
      		});
		
		setSize(1000, 500);


	}

	public void paint (Graphics g) {
		
			g.setColor(Color.green);

			for ( int i = 0 ; i < lineVector.size() ; i ++) {
				g.drawLine(lineVector.get(i).x1,lineVector.get(i).y1,lineVector.get(i).x2,lineVector.get(i).y2);	
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