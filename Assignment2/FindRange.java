/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import acm.program.*;
//Continue adding values in list. It will show you the maximum and minimum number in List"
public class FindRange extends ConsoleProgram {
	public void run() {
		println("Continue adding values, if you want to break enter 0. It will show you the maximum and minimum number in List");
		
		List<Integer> num = new ArrayList<>();
		int n1=readInt("Enter number: ");
		int n2=n1;
		num.add(n1);
		while(n2!=0) {
			n2=readInt("Enter number: ");
			num.add(n2);
		}
	
	    int min = (int) Collections.min(num);
	    int max = (int) Collections.max(num);
	    println("Min number: " + min);
	    println("Max number: " + max);
			
	}
	   
}

