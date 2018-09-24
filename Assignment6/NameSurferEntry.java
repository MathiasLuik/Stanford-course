/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

import com.sun.media.jfxmedia.events.PlayerStateListener;

public class NameSurferEntry implements NameSurferConstants {
	private String Name;
	private int[] rankings = new int[NDECADES];
/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		int nameEnd = line.indexOf(" ");
		Name = line.substring(0, nameEnd);
		String allDecadeNumbersStr = line.substring(nameEnd + 1);
		String[] allDecadeNumbersList = allDecadeNumbersStr.split(" ");
		int countingDecade=0;
		for (String decadeNumber : allDecadeNumbersList) {
			
			int popularityRank=Integer.parseInt(decadeNumber);
			rankings[countingDecade] = popularityRank;
			countingDecade++;
		}
		
		
		
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return Name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return rankings[decade];

	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String value = "\"" + Name + " [";
		for(int i = 0; i<NDECADES; i++) {
			value += getRank(i) + " ";
		}
		value += "]\"";
		return value;
		
	}
}

