package standard.lab3;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.Font;

public class PaintApplet extends Applet{
	

	public void paint (Graphics g) {

		int xs [] = {200,500,600,100} ;
		int ys [] = {100,100,300,300} ; 	
		g.drawPolygon(xs,ys,4);

		int x1s []  = {300,400,250,450} ;
		int y1s []  = {300,300,400,400} ; 	
		g.drawPolygon(x1s,y1s,4);		
		
		int x2s []  = {400,500,450,500} ;
		int y2s []  = {400,400,500,500} ;
		g.drawPolygon(x2s,y2s,4);
	}

}