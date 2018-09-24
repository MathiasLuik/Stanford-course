/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int number=readInt("Enter a number: ");
		decider(number);
	}
	public void decider(int number) {
		int GetNumberInfo=number%2;
		if (GetNumberInfo==1) {
			notEven(number);
		}else if (GetNumberInfo==0){
			even(number);
		}else {
			println("something is fuc*ed");
		}
	}
	public void notEven(int number) {
		number=3*number+1;
		println(((number-1)/3) +" is odd, so I make 3n+1: "+number);
		decider(number);
	}
	public void even(int number) {
		number=number/2;
		println(number*2 +" is even, so I take half: "+number);
		if (number==1) {
			println("finish");
			pause(125125);
		}
		decider(number);
	}		
}

