/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien 14209388
 */
package scrabBot;

import java.io.InputStream;
import java.util.Scanner;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import action.Action;
import action.ActionFactory;

public class UI {
	
	private Scanner sc;
	private String nextAction;
	private InputStream source;

	public UI(InputStream source) {
		this.source = source;
	}
	
	public Action getUserInput(){
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
	
	public String getPlayerName(int currentPlayer){
		System.out.print("Player " + (currentPlayer+1) + " name: ");
		sc = new Scanner(source);
		String name = sc.nextLine();
		while (name.length() > 20) {
			System.out.println("Error: use a name shorter than 20 characters");
			System.out.print("Player " + (currentPlayer+1) + " name: ");
			name = sc.nextLine();
		}
		return name;
	}
	
	public void gameInfo(Scrabble scrabble){
		scrabble.board.displayBoard();
		System.out.println("Pool's size: "+scrabble.pool.getPoolSize());
		for (Player player : scrabble.turn)
			System.out.println(player.getPlayerName()+" : "+player.getPlayerScore());
		System.out.println();
	}
	
	public int checkChallenge(Player[] players, int currentPlayer){
		boolean decided = false;
		int challengerNumber = -1;
		System.out.println("Challenge? Y your_name / N");
		sc = new Scanner(source);
		while (!decided) {
			String answer = sc.nextLine();
			while (!answer.matches("Y *|N")) {
				System.out.println("Error: incorrect answer format");
				System.out.println("Challenge? Y your_name / N");
				answer = sc.nextLine();
			}
			if (answer == "N")
				decided = true;
			else {
				answer = answer.substring(2);
				for (int i=0; i<players.length; i++)
					if (answer == players[i].getPlayerName()) {
						challengerNumber = i;
						decided = true;
					}
			}
		}
		return challengerNumber;
	}
	
	public int getNumberOfPlayers(){
		System.out.print("Number of players: ");
		sc = new Scanner(source);
		String numOfP = sc.nextLine();
		while (!numOfP.matches("[2-4]")) {
			System.out.println("Error: insert a number between 2 and 4");
			System.out.print("Number of players: ");
			numOfP = sc.next();
		}
		return Integer.parseInt(numOfP);
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
