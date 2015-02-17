package scrabBot;

import org.junit.runner.JUnitCore;

public class RunUnitTests
{
	public static void main(String[] args){
		JUnitCore runner = new JUnitCore();
		
		runner.addListener(new ExecutionListener());
		runner.run(AllTests.class);
		
	}
	
	public static void assertEqual(String testName, Object expected, Object actual){
		System.out.println("Testing " + testName + " :");
		System.out.println("expected = "+ expected + "\n  actual = " + actual);
		if(expected.equals(actual))
			System.out.println("\t\t\t\t\tOK");
		else
			System.out.println("\t\t\t\t\tFAIL");
	}	
}
