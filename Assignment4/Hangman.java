/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import acmx.export.javax.swing.text.Element;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import javax.management.loading.PrivateClassLoader;
import javax.print.attribute.standard.PrinterLocation;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.xml.internal.security.c14n.CanonicalizerSpi;
import com.sun.org.apache.xml.internal.security.utils.UnsyncBufferedOutputStream;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 *              CONSTANTS                                  *
	 ***********************************************************/
	
	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 10;


	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";
	
	private static final int backgroundWidht = 1200;
	private static final int backgroundHeigth = 600;
	
	private static final int KAREL_Y = 230;
	
	
	private Karel karel = new Karel();
	
	//private StringBuilder a = new StringBuilder();
	/* An object that can produce pseudo random numbers */
	private RandomGenerator rgen = new RandomGenerator();
	
	private GCanvas canvas = new GCanvas();
	
	private String setOfRightCharacters;
	private String setOfWrongCharacters;
	
	
	public String getSetOfRightCharacters() {
		return setOfRightCharacters;
	}
	public void setSetOfRightCharacters(String setOfRightCharacters) {
		this.setOfRightCharacters = setOfRightCharacters;
	}
	public String getSetOfWrongCharacters() {
		return setOfWrongCharacters;
	}
	public void setSetOfWrongCharacters(String setOfWrongCharacters) {
		this.setOfWrongCharacters = setOfWrongCharacters;
	}
	
	public void init() {
		add(canvas);
	}
	/**
	 * Initializing the game. Setting game window size. Adding amount of tries to game. Triggering possibility to add words with words();
	 */
	public void run() {
		println("This program counts letter frequencies.");
		println("Enter a blank line to indicate the end of the text.");
		setSize(backgroundWidht,backgroundHeigth);
		addCanvasObject(N_GUESSES);
		println("welcome to Hangman");
		words();
		
//		this.canvas.getSize()
	}	
	/**
	 * main program to handle drawing. Especially important for drawing and showing characters which are used
	 */
	private void addCanvasObject(int n) {
		canvas.removeAll();
		drawBackground();
		drawStrings(n);
		drawParachute();
		drawKarel();
		drawingRightCharacters(getSetOfRightCharacters());
		drawingUsedCharacters(getSetOfWrongCharacters());
	}
	/**
	 * drawing characters which are correct characters for the word
	 */
	private void drawingRightCharacters(String arrayCharacter) {
		if(arrayCharacter!=null) {	
			GLabel justForFontSize = new GLabel(arrayCharacter, backgroundWidht/4, PARTIALLY_GUESSED_Y);
			GLabel winningText = new GLabel(arrayCharacter, backgroundWidht/4-justForFontSize.getWidth(), PARTIALLY_GUESSED_Y);
			winningText.setFont(PARTIALLY_GUESSED_FONT);
			winningText.setColor(Color.GREEN);
			canvas.add(winningText);
		}
	}
	/**
	 * drawing characters which were entered as a line but not correct character for the word
	 */
	private void drawingUsedCharacters(String arrayCharacter) {
		if(arrayCharacter!=null) {			
			GLabel justForFontSize = new GLabel(arrayCharacter, backgroundWidht/4, PARTIALLY_GUESSED_Y);
			GLabel userCharacters = new GLabel(arrayCharacter, backgroundWidht/4-justForFontSize.getWidth()-50, INCORRECT_GUESSES_Y);
			userCharacters.setFont(INCORRECT_GUESSES_FONT);
			userCharacters.setColor(Color.GREEN);
			canvas.add(userCharacters);
		}
	}
	/**
	 * drawing string which are connecting parachute and Karel
	 * @return 
	 */
	private void drawStrings(int n) {
		int gap;
		if (n==0){
			gap = PARACHUTE_WIDTH;
		}
		if (n==1){
			gap = PARACHUTE_WIDTH/2;
		
		}else{
			gap = PARACHUTE_WIDTH / (n-1);
		}
		for(int i = 0; i < n; i++) {
			int delta = i * gap;
			GLine l = new GLine( backgroundWidht/4 , KAREL_Y , backgroundWidht/4 - PARACHUTE_WIDTH/2 + delta , PARACHUTE_Y + PARACHUTE_HEIGHT );
			canvas.add(l);
		}
	}
		/**
		private String getRealWorld() {
			
			return "Boobies";
		}
		private String getMaskedWord() {
			char[] letters = this.getRealWord().toCharArray();
		}
	 
		private void guessLetter(String letter) {
			for(int i = 0; i < this.getRealWorld().length(); i++) {
				if (this.getRealWorld().charAt(i) === letter) {					
				}
			}
		}
		*/
	/**
	 * MAIN PROGRAM. All logic over here. trying to validate if insterted character is a character. 
	 * counting lives how many left if wrong character
	 * if no lives left, game is over
	 */
	private void words() {
		String chosenWord=readingFile().toLowerCase();
		ArrayList<Character> arrayWithRightCharacter = new ArrayList<Character>();
		ArrayList<Character> arrayWithWrongCharacter = new ArrayList<Character>();
		ArrayList<String> correctCharaterString = new ArrayList<String>();
		for(int i = 0; i < chosenWord.length(); i++) {
		   correctCharaterString.add("-");
		}
		int countLivesLeft = 0;
		while (true) {
			String line = readLine();
			char c = line.charAt(0);
			if(chosenWord.indexOf(c)!=-1 && lineContainsRight(line)) { //indexOf parameter is searching charAt(o) if its inside
				arrayWithRightCharacter.add(c);			
				println("Your character " + c + " is listed" );
				for(int k = 0; k < chosenWord.length(); k++){
				    if (chosenWord.charAt(k) == c) {
				    	correctCharaterString.set(k, String.valueOf(c));
				    	findOutIfGameIsWon(correctCharaterString);
				    }
				}
				setOfRightCharacters=correctCharaterString.toString().replace(",", "").replace("]", "").replace("[", "");;	
			} else {
				if(lineContainsRight(line)) {
					arrayWithWrongCharacter.add(c);
				}
				setOfWrongCharacters=arrayWithWrongCharacter.toString().replace(",", "").replace("]", "").replace("[", "");;
				println("There is no character like this");
				countLivesLeft=countLivesLeft+1;					
				if (countLivesLeft>=N_GUESSES) {
					println("I'm sorry, you were bad. Game Is Over");
					canvas.remove(this.karel);
					drawDeadKarel();
					println("Your word was - "+chosenWord);
				}
			}
			addCanvasObject(N_GUESSES-countLivesLeft);
		}
	}
	/**
	 *at first the word is [-,-,-,-,-,-], if there are not "-" left then it will say all the charcters have been found and you won the game
	 */
	private void findOutIfGameIsWon(ArrayList<String> arrayCharacter) {
		int countHowManyCharactersNeededToWin=0;
		for(int i = 0; i < arrayCharacter.size(); i++){
		    if (arrayCharacter.get(i) != "-") {
		    	countHowManyCharactersNeededToWin=countHowManyCharactersNeededToWin+1;
		    	if (countHowManyCharactersNeededToWin==arrayCharacter.size()) {
		    		println("Congratz, You have won the game");
		    	}
		    }
		}
	}
	/**
	 * Boolean to check is the enter word has one character and it contain character
	 */
	private boolean lineContainsRight(String line) {
		if (line.length() == 1 && Character.isLetter(line.charAt(0)))  {
			return true;	
		}else {
			println("You failed with entering, enter new character");
		}
		return false;
	}
	/**
	 * randomly choosing word from file
	 */
	private int generateRandomNumber(int amountOfLinesInText){
		int randomLineGen = rgen.nextInt(0, amountOfLinesInText);
		return randomLineGen;
	}
	/**
	 * readig hangman lexicon to find random word. choosing the word and returning it as a variable 
	 */
	private String readingFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			int amountOfLinesInFile=0;
            String readLine = "";
            ArrayList<String> arrayWithAllWords = new ArrayList<String>();
            while ((readLine = br.readLine()) != null) {
                amountOfLinesInFile=amountOfLinesInFile+1;
                arrayWithAllWords.add(readLine);
            }
            String chosenWordForHangman = arrayWithAllWords.get(generateRandomNumber(amountOfLinesInFile));
            br.close();
            return chosenWordForHangman;
		} catch (IOException e) {
			e.printStackTrace();
			println("An error occurred: " + e);
		}
		return null;
	}
	/**
	 * drawing background 
	 */
	private void drawBackground() { 
		GImage bg = new GImage("background.jpg");
		bg.setSize(canvas.getWidth(), canvas.getHeight());
		canvas.add(bg, 0, 0);
	}
	/**
	 *  drawing parachute
	 */
	private void drawParachute() { 
		GImage bg = new GImage("parachute.png");
		bg.setSize(PARACHUTE_WIDTH, PARACHUTE_HEIGHT);
		canvas.add(bg, backgroundWidht/4-PARACHUTE_WIDTH/2, PARACHUTE_Y);
		
		// ParachusteObject a = new ParachuteObject(this.canvas);
		//ParachuteObject.setVisible();
	}
	/**
	 * drawing karel
	 */
	private void drawKarel() {
		canvas.add(this.karel, backgroundWidht/4-this.karel.getImageSize()/2, KAREL_Y);
	}
	/**
	 * if triggered will, show dead karel
	 */
	private void drawDeadKarel() { 
		this.karel.setKarelDead();
		canvas.add(this.karel, backgroundWidht/4-this.karel.getImageSize()/2, KAREL_Y);
	}
}