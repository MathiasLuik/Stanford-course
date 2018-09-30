/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import acm.util.*;
import java.awt.event.*;

import javax.management.loading.PrivateClassLoader;
import javax.swing.*;
import java.awt.*;
public class NameSurfer extends Program implements NameSurferConstants {
	private JTextField nameField = null;
	private static final int TEXT_FIELD_SIZE = 10;
	private NameSurferDataBase nameFromDatabase;
	private NameSurferGraph graph;
	private JButton graphButton;
	private JLabel label;
	private JButton cleanButton;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */	
	public void init() {
		

		graph = new NameSurferGraph();
		add(graph);		
		
		label = new JLabel("Name ");
		add(label, NORTH);
		
		nameField = new JTextField(TEXT_FIELD_SIZE);
		nameField.addActionListener(this);
		add(nameField, NORTH);
		
		graphButton = new JButton("Graph");
		add(graphButton, NORTH);
		
		cleanButton = new JButton("Clear");
		add(cleanButton, NORTH);
		
		addActionListeners();
		nameFromDatabase = new NameSurferDataBase(NAMES_DATA_FILE);
		setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}
		else  {
			String insertedName=nameField.getText();
			NameSurferEntry rankings = nameFromDatabase.findEntry(insertedName);
			if (rankings!=null){
				graph.addEntry(rankings);
				graph.update();
			}
			
			
		}
		
	}
}
