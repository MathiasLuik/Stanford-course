/*
 * File: DrawCenteredRect.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the DrawCenteredRect problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class DrawCenteredRect extends GraphicsProgram {
	
	/** Size of the centered rect */
	private static final int WIDTH = 350;
	private static final int HEIGHT = 270;

	public void run() {
		setSize(2*WIDTH, 2*HEIGHT);
		//GRect label = new GRect(getWidth()/2-WIDTH,getHeight()/2-HEIGHT,WIDTH,HEIGHT);
		GRect label = new GRect(WIDTH/2,HEIGHT/2,WIDTH,HEIGHT);
		label.setFilled(true);
		label.setColor(Color.blue);
		add(label);
		
	}
	
	
}

