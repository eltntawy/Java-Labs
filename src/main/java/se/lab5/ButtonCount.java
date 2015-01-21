package main.java.se.lab5;


import java.applet.Applet;
import java.awt.*;
import java.awt.event.* ;

public class ButtonCount extends Applet {

	Image image;
	Graphics graphics;

	int counter = 0 ;
	

	@Override
	public void init() {

		Button btnInc = new Button("Incr");
		Button btnDec = new Button("Decre");
		

		btnInc.addActionListener(new incAction());
		btnDec.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				counter--;
				repaint();
			}
		});

		
		add(btnInc);
		add(btnDec);

		setSize(1000, 500);

	}

	
	class incAction implements ActionListener {

		public  void actionPerformed(ActionEvent e) {

			counter++;
			repaint();

		}
	}

	@Override
	public void paint (Graphics g) {

		g.drawString("Counter is : "+counter ,100,100);
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