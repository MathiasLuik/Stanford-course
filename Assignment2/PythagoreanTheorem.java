/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute the Pythagorean theorem");
		int n1=readInt("Enter a: ");
		int n2=readInt("Enter b: ");
		double total = Math.sqrt(n1*n1+n2*n2);
		println("the c is "+total+ " !");
		/* You fill this in */
	}
}
