package scrabBot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.runner.JUnitCore;

public class RunTests
{
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		JUnitCore runner = new JUnitCore();
		Board board = new Board();
		Pool emptyTestPool;
		final Character[] emptyState = {};
		emptyTestPool = new Pool(emptyState);
		
		board.displayBoard();
		runner.addListener(new ExecutionListener());
		runner.run(AllTests.class);
		
		checkException_noParameters(emptyTestPool, "drawRandomTile", "scrabBot.EmptyPoolException");
	}
	
	public static void assertEqual(String testName, Object expected, Object actual){
		System.out.println("Testing " + testName + " :");
		System.out.println("expected = "+ expected + "\n  actual = " + actual);
		if(expected.equals(actual))
			System.out.println("\t\t\t\t\tOK");
		else
			System.out.println("\t\t\t\t\tFAIL");
	}
	
	public static void checkException_noParameters(Object owner, String methodToTest, String expectedException) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		Method method = owner.getClass().getMethod( methodToTest , (Class<?>[])null);
		System.out.println("Testing " + methodToTest+ "\nexpected exception "+ expectedException);
		try{
			method.invoke(owner, (Object[])null);
			System.out.println("\t\tFAIL, no exception thrown.");
		} catch(InvocationTargetException e){
			if(e.getCause().getClass().getName().equals(expectedException))
				System.out.println("\t\t\tOK");
			else
				System.out.println("FAIL, wrong exception "+ e.getCause().getClass().getName());
		}
	}
	
}
