import acm.program.ConsoleProgram;

public class IsDivisible extends ConsoleProgram{

//Finding out if numbers are dividible
	private boolean isDivisibleBy(int a, int b) {
		if (a>=b && a>0 && b>0 && a*b!=0 && a%b==0) {
			return true;
		}
		else {
			println("not dividible");
		}
		return false;
	}

	// This run method executes a barrage of tests.
	public void run() {
		println("Welcome to the IsDivisible program.");
		getLine("Press enter to start");
		println("");
		
		println("Running automatic tests...");
		runTest(10, 2, true);
		runTest(9, 3, true);
		runTest(5, 1, true);
		runTest(3, 9, false);
		runTest(10, 0, false);
		runTest(-10, 2, false);
		runTest(10, -2, false);
		
		println("\n\nRunning manual tests...");
		while(true) {
			runManualTest();
		}
	}


	private void runTest(int a, int b, boolean expectedReturn) {
		// call your method!
		boolean actualReturn = isDivisibleBy(a, b);

		// print out the results
		println("Input:           " + "a = " + a + ", b = " + b);
		println("Expected return: " + expectedReturn);
		println("Actual return:   " + actualReturn);


		// don't forget to use .equals when comparing strings
		if(expectedReturn == actualReturn) {
			println("Test passed");
		} else {
			println("Test did not pass");
		}

		// this adds a blank line so each test looks like a
		// paragraph of text
		println("");
	}

	
	private void runManualTest() {
		println("Enter two numbers (a and b) and isDivisibleBy will tell you");
		println("If a is divisible by b");
		int a = readInt("a: ");
		int b = readInt("b: ");
		boolean result = isDivisibleBy(a, b);
		println("isDivisibleBy returned: " + result);
		println("");

	}



}
