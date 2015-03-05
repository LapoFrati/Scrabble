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
			System.out.println("Legal options:\nQUIT\nHELP\nPASS\nEXCHANGE <letters>\n<grid ref> <across/down> <word>  e.g. A1 A HELLO");
		}
		return ActionFactory.buildAction(nextAction);
	}
	
	public void promptActivePlayer(String activePlayer){
		System.out.println(activePlayer + "turn (enter \"HELP\" for help)");
	}
	
	public boolean validateInput(String userInput) {
		return userInput.matches("QUIT|PASS|HELP|EXCHANGE [A-Z]{1,7}|[A-O]([1-9]|1[0-5]) [AD] [A-Z ]{1,15}");
	}
}
