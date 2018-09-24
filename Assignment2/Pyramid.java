/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final double BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final double BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	private static final int BRICKS_WIDTH_START = 100;
	private static final int BRICKS_HEIGHT_START = 200;
	
	public void run() {
		for (int i=0; i<BRICKS_IN_BASE; i++){
			for (int j=0; j<BRICKS_IN_BASE-i; j++){	
				//builds bricks  to the right and up, makes a pyramid out of bricks
				GRect BRICKS_TO_RIGHT = new GRect(BRICKS_WIDTH_START+j*(BRICK_WIDTH)+BRICK_WIDTH*i/2, BRICKS_HEIGHT_START-BRICK_HEIGHT*i, BRICK_WIDTH, BRICK_HEIGHT);
				add(BRICKS_TO_RIGHT);
			}
			
		}
	}
}

