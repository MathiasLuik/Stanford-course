/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	
	// game width size in pixels
	public static final int canvasWidth = 420;
	// game heigth size in pixels
	public static final int canvasHeight = 600;
	// Number of bricks in each row
	public static final int nBrickColumns = 10;
	// Number of rows of bricks
	public static final int nBrickRows = 10;
	// Separation between neighboring bricks, in pixels
	public static final int brickSeparator = 4;
	// Height of each brick, in pixels
	public static final int brickHeight = 8;
	// Offset of the top brick row from the top, in pixels
	public static final int brickYOffset = 70;
	// Dimensions of the paddle
	// Offset of the paddle up from the bottom 
	public static final int paddleYOffset = 30;
	//racket width
	public static final int racketWidth = 60;
	//racket height
	public static final int racketHeight = 10;
	// Radius of the ball in pixels
	public static final int ballRadius = 10;
	// The ball's vertical velocity.
	public static final int velocityY = 3;
	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final int velocityXMin = 1;
	public static final int velocityXMax = 3;
	/* Initial speed in the x direction */
	private static final int initialVY = 2;
	// Animation delay or pause time between ball moves (ms)
	/* How many ms to pause between "heartbeats" */
	public static final double delay = 300.0 / 60.0;  //1000.0 / 60.0
	// Number of turns 
	public static int nTurns = 3;
	//random number generator
	private RandomGenerator rgen = RandomGenerator.getInstance();
	//generated the racket
	private GRect racket = new GRect (racketWidth, racketHeight);
	/* The color of the ball */
	private static final Color ballColor = Color.BLUE;
	
	public void run() {
		// setup
		setSize(canvasWidth,canvasHeight);
		GOval ball = makeBall();
		makeBricks();
		addRacket();
		addMouseListeners();
		waitForClick();
		bouncingBall(ball);
	}
	/**
	 * records the ball movements and if it hits some object it will go different direction. Including it will remove bricks.
	 */
	private void bouncingBall(GOval ball) {
		int vx = rgen.nextInt(velocityXMin, velocityXMax);
		int vy = initialVY;
		int liveLeft=0;
		int bricksLeft=nBrickColumns*nBrickRows;
		while(true) {
			if (leftWallCollider(ball)) {
				vx = -vx;
			}
			else if (rightWallCollider(ball)) {
				vx = -vx;	
			}
			else if (racketCollider(ball)) {
				vy = -vy;
			}
			else if (topWallCollider(ball)) {
				vy = -vy;
			}
			else if (ballLeftCollider(ball)) {
				vx = -vx;
				bricksLeft = howManyBricksLeft(bricksLeft);
			}
			else if (ballTopCollider(ball) ) {
				vy = -vy;
				bricksLeft = howManyBricksLeft(bricksLeft);
			}
			else if (ballBottomCollider(ball)) {
				vy = -vy;
				bricksLeft = howManyBricksLeft(bricksLeft);
			}
			else if (ballRightCollider(ball)) {
				vx = -vx;
				bricksLeft = howManyBricksLeft(bricksLeft);
			}
			else if (losingLife(ball)) {
				liveLeft=liveLeft+1;
				howManyLivesLeft(liveLeft,ball);
			}
			
			// update visualization
			ball.move(vx, vy);
			pause(delay);
		}
	}
	/**
	 * Returns whether or not ball should bounce off brick on top
	 */
	private boolean ballTopCollider(GOval ball) {
		GObject hitTopSide = getElementAt(ball.getX()+ballRadius, ball.getY());
		if (hitTopSide != null && ball.getY()<getHeight()-brickYOffset-racketHeight-10) {
			remove(hitTopSide);
			return true;
		}
		return false;
	}
	/**
	 * Returns whether or not ball should bounce off brick on right side
	 */
	private boolean ballRightCollider(GOval ball) {
		GObject hitRightSide = getElementAt((ball.getX()+(ballRadius*2)) , (ball.getY()+(ballRadius)));
		if (hitRightSide != null && ball.getY()<getHeight()-brickYOffset-racketHeight-10) {
			remove(hitRightSide);
			return true;
		}
		return false;
	}
	/**
	 * Returns whether or not ball should bounce off brick on left side
	 */
	private boolean ballLeftCollider(GOval ball) {
		GObject hitLeftSide = getElementAt(ball.getX(),  ball.getY() + (ballRadius));
		if (hitLeftSide != null && ball.getY()<getHeight()-brickYOffset-racketHeight-10) {
			remove(hitLeftSide);
			return true;
		}
		return false;
	}
	/**
	 * Returns whether or not ball should bounce off brick on bottom
	 */
	private boolean ballBottomCollider(GOval ball) {
		GObject hitBottomSide = getElementAt(ball.getX() + (ballRadius), ball.getY()+(ballRadius*2));
		if (hitBottomSide != null && ball.getY()<getHeight()-brickYOffset-racketHeight-10) {
			remove(hitBottomSide);
			return true;
		}
		return false;
	}
	/**
	 * Returns whether or not the given ball should bounce off
	 * of the top wall of the window.
	 */
	private boolean topWallCollider(GOval ball) {
		return ball.getY() <= 0 ;
	}
	/**
	 * Returns whether or not the given ball should bounce off
	 * of the right wall of the window.
	 */
	private boolean rightWallCollider(GOval ball) {
		return ball.getX() >= getWidth() - ball.getWidth();
	}
	/**
	 * Returns whether or not the given ball should bounce off
	 * of the left wall of the window.
	 */
	private boolean leftWallCollider(GOval ball) {
		return ball.getX() <= 0;
	}
	/**
	 *if ball is below half of the gametable and hits the racket then it will bounce back 
	 */
	private boolean racketCollider(GOval ball) {
		GObject hitBottomRight = getElementAt(ball.getX() + (ballRadius), ball.getY() + (ballRadius * 2));
		if (hitBottomRight != null && ball.getY()>getHeight()-brickYOffset-racketHeight-ballRadius * 2-10) {
			//remove(hitBottomRight);
			return true;
		}
		return false;
	}
	/**
	 *if ball goes below racket it will lose a life
	 */
	private boolean losingLife(GOval ball) {
		return ball.getY() > getHeight() - paddleYOffset;
	}
	/**
	 * waites for new game if life has been lost
	 */
	private void waitingForNewGame(GOval ball) {
		ball.setLocation(generateXSpotForStartingBall(),generateYSpotForStartingBall());
		addRacket();
		waitForClick();
	}
	/**
	 * calculated how many lives player has lost and how many are left. If player has lost 3 lives, game will paused
	 */
	private void howManyLivesLeft(int livesLost,GOval ball) {
		GLabel printingLivesLeftOnScreen;
		printingLivesLeftOnScreen = new GLabel( "You have " + (nTurns-livesLost) + " more tries", getWidth()/4, getHeight()/2);
		printingLivesLeftOnScreen.setColor(Color.RED);
		printingLivesLeftOnScreen.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		add(printingLivesLeftOnScreen);
		pause(2500);
		remove(printingLivesLeftOnScreen);
		if (livesLost==nTurns) {
			printingLivesLeftOnScreen = new GLabel( "You are finished, my friend", getWidth()/4, getHeight()/2);
			remove(ball);
			add(printingLivesLeftOnScreen);
			stop();
			pause(150000);	
		}
		waitingForNewGame(ball);
	}

	/**
	 * int calculation about how long the brick_width should be to fit required amount of bricks in the table
	 */
	private double calculateBrickWidth() {
		double BRICK_WIDTH = Math.floor((getWidth() - (nBrickColumns - 1) * brickSeparator) / nBrickColumns);
		return BRICK_WIDTH;
	}
	/**
	 * add colors to the bricks and makes the bricks to the table
	 */
	private void makeBricks() {
		for (int i=0; i<nBrickColumns; i++){
			for (int j=0; j<nBrickRows; j++){	
				GRect BRICK = new GRect(i*calculateBrickWidth()+brickSeparator*i,j*brickHeight+brickSeparator*j+brickYOffset,calculateBrickWidth(),brickHeight);
				BRICK.setFilled(true);
				switch(j) {
					case 0:case 1:BRICK.setColor(Color.RED);
						break;
					case 2:case 3:BRICK.setColor(Color.ORANGE);
						break;
					case 4:case 5:BRICK.setColor(Color.GREEN);
						break;
					case 6:case 7: BRICK.setColor(Color.CYAN);
						break;
					case 8:case 9:BRICK.setColor(Color.BLUE);
						break;
				}
				add(BRICK);	
			}
		}
	}
	/**
	 *count how many bricks have been removed
	 */
	private int howManyBricksLeft(int bricksLeft) {
		bricksLeft=bricksLeft-1;
		gameOver(bricksLeft);
		return bricksLeft;
	}
	/**
	 * if removed bricks amount is equal to bricks at start, will monitor you won the game
	 */
	private void gameOver(int bricksLeft) {
		if (bricksLeft==0) {
			GLabel winningText = new GLabel( "You have won, my friend", getWidth()/4, getHeight()/2);
			winningText.setColor(Color.GREEN);
			winningText.setFont(new Font("TimesRoman", Font.PLAIN, 25));
			add(winningText);
			pause(100000);
		}
	}
	/**
	 *Creates a ball, adds it to the screen, and returns it so
	 *that the ball can be used for animation.
	 */
	public GOval makeBall() {
		int size = ballRadius * 2;
		GOval r = new GOval(size, size);
		r.setFilled(true);
		r.setColor(ballColor);
		add(r, generateXSpotForStartingBall(), generateYSpotForStartingBall());
		return r;
	}
	/**
	 * generates starting Y position for ball
	 */
	private int generateXSpotForStartingBall(){
		int ballXStartingCordinates = rgen.nextInt(0, getWidth()-2*ballRadius);
		return ballXStartingCordinates;
	}
	/** 
	 * generates starting X position for ball
	 */
	private int generateYSpotForStartingBall(){
		int ballYStartingCordinates=nBrickRows *(brickHeight+brickSeparator)+brickYOffset;
		return ballYStartingCordinates;

	}
	/**
	 * Greates a racket into screen, idea is to hit the ball with the racket
	 */
	private void addRacket() {
		racket.setFilled(true);
		int x = getWidth()/2;
		int y = getHeight()- racketHeight - paddleYOffset;
		add(racket, x, y);
	}
	/**
	 * triggered my MouseListener. tracking mouse movement and adding the racket to it
	 */
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		if (x > (getWidth() - racketWidth)) {
			x = getWidth() - racketWidth;
		} 
		int y = getHeight()- racketHeight - paddleYOffset;
		racket.setLocation(x,y);
	}
}
