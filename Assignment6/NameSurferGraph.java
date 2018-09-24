/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	private ArrayList <NameSurferEntry> entriesDisplayed;
	

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		entriesDisplayed = new ArrayList<NameSurferEntry>();
		// You fill in the rest //
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		entriesDisplayed.clear();
		// You fill this in //
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		entriesDisplayed.add(entry);
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
		removeAll();
		addLines();
	}
	private void addLines() {
		drawingVerticalLines(); 
		drawingHorizontalLines(); 
		drawingDecades();
	}
	private void drawingDecades() {
		System.out.println("I'm drawing decades now");
		for(int i = 0; i<=NDECADES; i++) {
			int x=getWidth()*i/NDECADES;
			int y=getHeight();
			int whichDecade=START_DECADE;
			whichDecade=+whichDecade+10*i;
			String label = Integer.toString(whichDecade);
			GLabel displayedDecade = new GLabel(label,x,y);
			add(displayedDecade);
		}
		
	}
	private void drawingHorizontalLines() {
		System.out.println("I'm drawing Hosisontal Lines");
		int x1=0;
		int x2=getWidth();
		int y1=GRAPH_MARGIN_SIZE;
		int y2=getHeight()-GRAPH_MARGIN_SIZE;
		
			
		GLine upperLine = new GLine(x1, y1, x2, y1);
		GLine lowerLine = new GLine(x1, y2, x2, y2);
		add(upperLine);	
		add(lowerLine);
	}
	private void drawingVerticalLines() {
		System.out.println("I'm drawing vertical Lines");
		for(int i = 0; i<=NDECADES; i++) {
			int y1=0;
			int y2=getHeight();
			int x=getWidth()*i/NDECADES;
			GLine line = new GLine(x, y1, x, y2);
			add(line);
		}
		//int x=APPLICATION_HEIGHT/
		//System.out.println(N);
		//APPLICATION_WIDTH
		//APPLICATION_HEIGHT
		//GLine line = new GLine(x, y1, x, y2);
		//GRAPH_MARGIN_SIZE
		//START_DECADE
		//MAX_RANK
		//NDECADES
		//START_DECADE
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
