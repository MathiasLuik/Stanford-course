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

import com.sun.org.apache.bcel.internal.generic.ReturnaddressType;

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
		if (entriesDisplayed.size() >= 0) {
			for(int i = 0; i < entriesDisplayed.size(); i++) {
				//System.out.println(entriesDisplayed.size());
				NameSurferEntry entries = entriesDisplayed.get(i); 
				//drawEntry(entries, i);
				drawingLines(entries,i);
				//System.out.println(entries);
			}
		}
	}
	private void addLines() {
		drawingVerticalLines(); 
		drawingHorizontalLines(); 
		drawingDecades();
	}
	private void drawingLines(NameSurferEntry entries, int entryNumber ){
		for(int i = 0; i<NDECADES- 1; i++) {
			int ranking1 = entries.getRank(i);
			int ranking2 = entries.getRank(i+1);
			double x1 = i * (getWidth()/NDECADES);
			double x2 = (i+1) * (getWidth()/NDECADES);
			double y1 = 0;
			double y2 = 0;
			if(ranking1 != 0 && ranking2 != 0) {
				y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * ranking1/MAX_RANK;
				y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * ranking2/MAX_RANK;
			}
			else if(ranking1 == 0 && ranking2 == 0) {
				y1 = getHeight() - GRAPH_MARGIN_SIZE;
				y2 = getHeight() - GRAPH_MARGIN_SIZE;
			}
			else if (ranking1 == 0){
				y1 = getHeight() - GRAPH_MARGIN_SIZE;
				y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * ranking2/MAX_RANK;
			}
			else if(ranking2 == 0) {
				y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * ranking1/MAX_RANK;
				y2 = getHeight() - GRAPH_MARGIN_SIZE;
			}
			
			GLine line = new GLine(x1, y1, x2, y2);
			line.setColor(selectColor(entryNumber));
			add(line);
		}	
		addName(entries,entryNumber);
	}
	private Color selectColor(int entryNumber) {
		if(entryNumber%4 == 1) {
			return (Color.RED);
		}
		else if(entryNumber%4 == 2) {
			return (Color.BLUE);
		}
		else if(entryNumber%4 == 3) {
			return (Color.MAGENTA);
		}
		return Color.black;
	}
	private void addName(NameSurferEntry entries, int entryNumber) {
		for(int i = 0; i<NDECADES; i++) {
			String name = entries.getName();
			int rank = entries.getRank(i);
			String rankString = Integer.toString(rank);
			String label = name + " " + rankString;
			double x = i * (getWidth()/NDECADES) + 5;
			double y = 0;
			if(rank != 0) {
				y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * rank/MAX_RANK - 5;
			}
			else{
				label = name + " *";
				y = getHeight() - GRAPH_MARGIN_SIZE - 5;
			}
			GLabel nameLabel = new GLabel(label, x, y);
			nameLabel.setColor(selectColor(entryNumber));
			add(nameLabel);
		}
	}
	private void drawingDecades() {
		//System.out.println("I'm drawing decades now");
		for(int i = 0; i<=NDECADES; i++) {
			double x=getWidth()*i/NDECADES;
			double y=getHeight();
			double whichDecade=NDECADES*i;
			String label = Double.toString(whichDecade);
			GLabel displayedDecade = new GLabel(label,x,y);
			add(displayedDecade);
		}
		
	}
	private void drawingHorizontalLines() {
		//System.out.println("I'm drawing Hosisontal Lines");
		double x1=0;
		double x2=getWidth();
		double y1=GRAPH_MARGIN_SIZE;
		double y2=getHeight()-GRAPH_MARGIN_SIZE;
		
			
		GLine upperLine = new GLine(x1, y1, x2, y1);
		GLine lowerLine = new GLine(x1, y2, x2, y2);
		add(upperLine);	
		add(lowerLine);
	}
	private void drawingVerticalLines() {
		//System.out.println("I'm drawing vertical Lines");
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
