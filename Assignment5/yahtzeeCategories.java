import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
/*
Here are all yahtzee category to try them out.
**/
public class yahtzeeCategories extends Yahtzee implements YahtzeeConstants{
	//public static final int ones = 1;
	public int numbers(int[] dice, int category) {
		int countScore=0;
		for (int i = 0;i < n_dice; i++) {
			if(dice[i]==category) {
				countScore=countScore+dice[i];
			}
		}
		return countScore;
	}
	public int same_of_a_kind(int[] dice, int category) {
		int countScore=0;
		List<Integer> list = Arrays.stream(dice).boxed().collect(Collectors.toList());
	    for (int item : list) {
	    	// if same number is displayed 5 times and clicked category is yahtzee then it will provide static 50 point
	    	if (category==14 && Collections.frequency(list, item) == 5 ) {
	        	countScore=50;
	        }
	    	if (category==10 && (Collections.frequency(list, item) == 4) || Collections.frequency(list, item) == 5){
	        	countScore=4*item;
	        }
	    	if (category==9 && (Collections.frequency(list, item) == 3 || Collections.frequency(list, item) == 4 || Collections.frequency(list, item) == 5)) {
	            countScore=3*item;
	        }
	    }
		return countScore;
	}
	public int straight(int[] dice, int category) {
		int countScore=0;
		boolean contains1= contains(dice,1);
		boolean contains2= contains(dice,2);
		boolean contains3= contains(dice,3);
		boolean contains4= contains(dice,4);
		boolean contains5= contains(dice,5);
		boolean contains6= contains(dice,6);
		if (contains1&&contains2&&contains3&&contains4&&contains5) {
			countScore+=30;
		}
		if (contains2&&contains3&&contains4&&contains5&&contains6) {
			countScore+=40;
		}
		return countScore;
	}
	public static boolean contains(int[] dice, int key) {
	    return Arrays.stream(dice).anyMatch(i -> i == key);
	}
	public int chance(int[] dice, int category) {
		int countScore=0;
		for (int i = 0;i < n_dice; i++) {	
			countScore=countScore+dice[i];	
		}
		return countScore;
	}
	public int full_house(int[] dice, int category) {
		int countScore=0;
		Arrays.sort(dice);
		if ((dice[0] == dice[1] && dice[1] == dice[2] && dice[2] != dice[3] && dice[3] == dice[4]) || (dice[0] == dice[1] && dice[1] != dice[2] && dice[2] == dice[3] && dice[3] == dice[4])) {
				countScore+=25;
		}
		return countScore;
		
	}	
}
