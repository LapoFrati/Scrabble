/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien 14209388
 */
package scrabBot;

import java.io.InputStream;
import java.util.Scanner;

public class UI {
	
	private Scanner sc;
	private String nextAction;

	public UI() {}
	
	public Action getUserInput(InputStream source){
		sc = new Scanner(source);
		nextAction = sc.nextLine();
		while(!validateInput(nextAction)) {
			nextAction = sc.nextLine();
		}
		return ActionFactory.buildAction(nextAction);
	}
	
	public void printMessage(String msg, boolean newline){
		if(newline)
			System.out.println(msg);
		else
			System.out.print(msg);
	}
	
	public String getPlayerName(){
		return "TODO";
	}
	
	public void gameInfo(Scrabble scrabble){
		scrabble.board.displayBoard();
		System.out.println("Pool's size: "+scrabble.pool.getPoolSize());
		System.out.println(scrabble.P1.getPlayerName()+" : "+scrabble.P1.getPlayerScore());
		System.out.println(scrabble.P2.getPlayerName()+" : "+scrabble.P2.getPlayerScore());
		System.out.println();
	}
	
	public void promptActivePlayer(Player activePlayer,Board board){
		System.out.println(activePlayer.getPlayerName() + "'s turn. (enter \"HELP\" for help)");
		System.out.print("Frame: ");
		activePlayer.getPlayerFrame().showFrame();
	}
	
	public boolean validateInput(String userInput) {
		return userInput.matches("QUIT|PASS|HELP|EXCHANGE [A-Z&]{1,7}|[A-O]([1-9]|1[0-5]) [AD] [A-Z&]{1,15}");
	}
}
