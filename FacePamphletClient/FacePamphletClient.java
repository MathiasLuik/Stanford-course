/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

public class FacePamphletClient extends GraphicsProgram 
implements FacePamphletConstants {

	private static final String HOST = "http://localhost:8000/";

	/** 
	 * Init is called before the window is created 
	 */
	public void init() {
		// your code here
	}
	
	/** 
	 * Run is called after the window is created 
	 */
	public void run() {
		// your code here
		pingTheServer();
		
	}

	/**
	 * This method is called when a button is clicked.
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		println(cmd);
	}

	/**
	 * This method is an example of sending a request to the server.
	 * You can delete it when you are ready.
	 */
	private void pingTheServer() {
		try {
			// Lets prepare ourselves a new request with command "ping".
			Request example = new Request("ping");
			// We are ready to send our request
			String result = SimpleClient.makeRequest(HOST, example);
			drawCenteredLabel(result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Again, this is a helper method that we wrote for the "pingTheServer"
	 * example (above). You should not include it in your final submission
	 */
	private void drawCenteredLabel(String text) {
		GLabel label = new GLabel(text);
		label.setFont("courier-24");
		double x = (getWidth() - label.getWidth())/2;
		double y = (getHeight() - label.getAscent())/2;
		add(label, x, y);
	}

}
