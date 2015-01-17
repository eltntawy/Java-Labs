package main.java.standard.lab3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.GraphicsEnvironment;
import java.util.Locale;
import java.awt.Font;

public class FontToolKit2 extends Applet{
	
	public void paint (Graphics g) {

		GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();

		String fontArray [] = genv.getAvailableFontFamilyNames();
		int counter = 1;
		for(String s : fontArray) {
			g.setFont(new Font(s,Font.PLAIN , 18));
			g.drawString(s+"\n",100,counter*20);
			counter++;

		}

		
	}

}