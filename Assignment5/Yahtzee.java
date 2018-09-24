/*
 * File: Yahtzee.java
 * ------------------
 * This program will play the Yahtzee game.
 */

import java.util.ArrayList;

import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.xml.internal.bind.util.Which;

import acm.io.*;
import acm.program.*;
import acm.util.*;


public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	//int[] dice = {4,3,3,2,6};
	private int[] dice; 
	
	//size of background
	private static final int backgroundWidht = 400;
	private static final int backgroundHeigth = 400;
	//playerTurnList - which player turn it is 1,2,3,1,2,3,
	//playerScoreList - what score was scored 18,5,50,30,20,11
	//usedCategoryList - which categories have already been used 1,3,2,13,4,5
	private ArrayList<Integer> playerTurnList = new ArrayList<Integer>();
	private ArrayList<Integer> playerScoreList = new ArrayList<Integer>();
	private ArrayList<Integer> usedCategoryList = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
		
	}
	private void playGame() {
		setSize(backgroundWidht,backgroundHeigth); 
		//starting game Which rolls And player turns
		doingSelection();
		// calculating lasts scores in the table as final thing in the game
		calculateFinalResults();
	}
	private void doingSelection() {
		//gives 13(scoring_categories) tries for everyone
		for (int i = 1; i <= n_scoring_categories; i++) { 
			//gives each player one turn to roll 3 times. First letting player to roll then counting the results and after updating table
			for(int whichPlayerTurn = 1; whichPlayerTurn<=nPlayers ; whichPlayerTurn++) {
				display.waitForPlayerToClickRoll(whichPlayerTurn);	
				countingRolls();
				updatingTable(whichPlayerTurn);
			}
		}	
	}
	//counting rolls and adding results to dice int
	private void countingRolls() {
		dice= new int[5];
		for (int i = 0;i < n_rolls; i++) {
			display.displayDice(generatingNumbers(dice));
			if (i!=2){
				display.waitForPlayerToSelectDice();
			}
		}
	}
	// generating random numbers for dice rolls between 1 and 6
	private int[] generatingNumbers(int[] dice) {
		for (int i = 0;i < n_dice; i++) {	
			if (dice[i]==0 || display.isDieSelected(i)) {
				dice[i]=rgen.nextInt(1, 6);
			}					
		}
		return dice;
	}
	// doing table update. If category is already been clicked before then you have to click again on some other category. 
	private void updatingTable(int whichPlayerTurn) {
		int category = display.waitForPlayerToSelectCategory();
		int calculatingCategoryScore=countingCategory(dice,category);	
		if(isCategoryUsed(whichPlayerTurn,category)){
			playerTurnList.add(whichPlayerTurn);
			playerScoreList.add(calculatingCategoryScore);
			usedCategoryList.add(category);
			display.updateScorecard(category, whichPlayerTurn, calculatingCategoryScore);
		}
		else{
			display.printMessage("Not the right place for clicking here");
			updatingTable(whichPlayerTurn);
		}	
	}
	// calculating which category is right for person
	private int countingCategory(int[] dice, int category) {
		yahtzeeCategories catNumber= new yahtzeeCategories();
		int countScore=0;
		if (category==ones||category==twos ||category==threes ||category==fours ||category==fives ||category==sixes) {
			countScore+= catNumber.numbers(dice,category);
		}
		else if (category==three_of_a_kind || category==four_of_a_kind || category==yahtzee) {
			countScore+= catNumber.same_of_a_kind(dice,category);
		}
		else if (category==full_house) {
			countScore+= catNumber.full_house(dice,category);
		}
		else if (category==small_straight || category==large_straight) {
			countScore+= catNumber.straight(dice,category);
		}
		else if (category==chance) {
			countScore+= catNumber.chance(dice,category);
		}
		return countScore;
	}
	// looking if cateogory is used before
	private boolean isCategoryUsed(int whichPlayerTurn,int category) {
		for(int i = 0; i < playerTurnList.size() ; i++) {
			if(playerTurnList.get(i)==whichPlayerTurn&&usedCategoryList.get(i)==category) {
				return false;
			}
		}
	return true;
	}
	// doing the very final steps, updating results and calculating how many scores and player has got
	private void calculateFinalResults() {
		for(int whichPlayerTurn = 1; whichPlayerTurn <= nPlayers ; whichPlayerTurn++) {
			int lowerScore=0;
			int upperScore=0;
			int upperBonus=0;
			for(int i = 0; i < playerTurnList.size() ; i++) {
				if(playerTurnList.get(i) == whichPlayerTurn && usedCategoryList.get(i)<=6) {
					upperScore+=playerScoreList.get(i);
				}if(playerTurnList.get(i) == whichPlayerTurn && usedCategoryList.get(i)>=9 && usedCategoryList.get(i)<=15) {
					lowerScore+=playerScoreList.get(i);
				}
				if(63<=upperScore) {
				upperBonus=35;
				}
			}
			updateFinalResultTable(upperScore,upperBonus,lowerScore,whichPlayerTurn);
		}	
	}	
	public void updateFinalResultTable(int upperScore,int upperBonus,int lowerScore, int whichPlayerTurn) {
		display.updateScorecard(upper_score, whichPlayerTurn, upperScore);
		display.updateScorecard(upper_bonus, whichPlayerTurn, upperBonus);
		display.updateScorecard(lower_score, whichPlayerTurn, lowerScore);
		display.updateScorecard(total, whichPlayerTurn, upperScore+lowerScore+upperBonus);
	}
	public ArrayList<Integer> getPlayerTurnList() {
		return playerTurnList;
	}

	public void setPlayerTurnList(ArrayList<Integer> playerTurnList) {
		this.playerTurnList = playerTurnList;
	}

	public ArrayList<Integer> getPlayerScoreList() {
		return playerScoreList;
	}

	public void setPlayerScoreList(ArrayList<Integer> playerScoreList) {
		this.playerScoreList = playerScoreList;
	}

	public ArrayList<Integer> getUsedCategoryList() {
		return usedCategoryList;
	}

	public void setUsedCategoryList(ArrayList<Integer> usedCategoryList) {
		this.usedCategoryList = usedCategoryList;
	}
}
