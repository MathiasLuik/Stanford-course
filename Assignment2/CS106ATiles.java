/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {
	
	/** Amount of space (in pixels) between tiles */
	private static final int tileSpace = 20;
	private static final int width = 200;
	private static final int height = 200;
	private static final int tileWidth = 100;
	private static final int tileHeight = 50;
	

	public void run() {
		setSize(2*width+2*tileWidth+tileSpace, 2*height+2*tileHeight+tileSpace);
		GLabel centre = new GLabel(".",(2*width+2*tileWidth+tileSpace)/2, (2*height+2*tileHeight+tileSpace)/2);
		add(centre);
		drawingTile(width,height,tileWidth,tileHeight);
		drawingTile(width+tileWidth+tileSpace,height,tileWidth,tileHeight);
		drawingTile(width,height+tileHeight+tileSpace,tileWidth,tileHeight);
		drawingTile(width+tileWidth+tileSpace,height+tileHeight+tileSpace,tileWidth,tileHeight);
	
	}
	private void drawingTile(int width, int height, int tileWidth,int tileHeight){
		
		GLabel sizeMatters = new GLabel("Pretty",1, 1);
		double widthOfText=sizeMatters.getWidth();
		double fontSizeOfText=sizeMatters.getAscent();
		double widthInTheMiddleOfBox=(tileWidth-widthOfText)/2;
		println(sizeMatters.getWidth());
		println(sizeMatters.getAscent());
		double heightInTheMiddleOfBox=(tileHeight+fontSizeOfText)/2;
		
		GLabel text = new GLabel("Pretty",width+widthInTheMiddleOfBox, height+heightInTheMiddleOfBox);
		GRect rect = new GRect( width, height,tileWidth, tileHeight);
		add(text);
		add(rect);
		
	}
}

