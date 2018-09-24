/*
 * File: YahtzeeConstants.java
 * ---------------------------
 * This file declares several constants that are shared by the
 * different modules in the Yahtzee game.
 */

public interface YahtzeeConstants {

/** the width of the application window */
	public static final int application_width = 600;

/** the height of the application window */
	public static final int application_height = 350;

/** the number of dice in the game */
	public static final int n_dice = 5;
	
/** the number of rolls */
	public static final int n_rolls = 3;

/** the maximum number of players */
	public static final int max_players = 4;

/** the total number of categories */
	public static final int n_categories = 17;

/** the number of categories in which the player can score */
	public static final int n_scoring_categories = 13;

/** the constants that specify categories on the scoresheet */
	public static final int ones = 1;
	public static final int twos = 2;
	public static final int threes = 3;
	public static final int fours = 4;
	public static final int fives = 5;
	public static final int sixes = 6;
	public static final int upper_score = 7;
	public static final int upper_bonus = 8;
	public static final int three_of_a_kind = 9;
	public static final int four_of_a_kind = 10;
	public static final int full_house = 11;
	public static final int small_straight = 12;
	public static final int large_straight = 13;
	public static final int yahtzee = 14;
	public static final int chance = 15;
	public static final int lower_score = 16;
	public static final int total = 17; 
}
