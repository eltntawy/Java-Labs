package standard.lab3;
import java.applet.Applet;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

public class ImageApplet extends Applet{
	





	public void paint (Graphics g) {


		Image img = getImage(getCodeBase(),"simple.gif");

		g.drawImage(img,0,0,getSize().width,getSize().height,new Color(10,10,10),this);

	}

}