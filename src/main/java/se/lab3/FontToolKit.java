package main.java.se.lab3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.Font;

public class FontToolKit extends Applet{
	

	@Override
	public void paint (Graphics g) {

		Toolkit tk = Toolkit.getDefaultToolkit();

		String fontArray [] = tk.getFontList();
		int counter = 1;
		for(String s : fontArray) {
			g.setFont(new Font(s,Font.PLAIN , 18));
			g.drawString(s+"\n",100,counter*20);
			counter++;
		}

		
	}

}