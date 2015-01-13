package standard.lab1;

import java.applet.Applet;
import java.awt.Graphics;

public class HelloApplet extends Applet {
	


	public void init() {


	}

	public void start () {

	}


	public void paint(Graphics g) {

		//g.drawString ("HelloApplet",400,200);


		String param1 = getParameter("param1");
		String param2 = getParameter("param2");

		g.drawString("HelloJava "+param1+ " "+param2 ,100,100);

	}

	public void stop () {


	}

	public void destroy () {


	}

}