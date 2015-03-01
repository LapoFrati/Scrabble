package scrabBot;

import java.io.InputStream;
import java.util.Scanner;

public class UI {
	
	private Scanner sc;
	private String nextAction;

	public UI() {}
	
	public Action getUserInput(InputStream source, String player){
		sc = new Scanner(source);
		boolean correctInput = false;
		while(!correctInput) {
			nextAction = sc.nextLine();
			correctInput = nextAction.matches("QUIT|PASS|HELP|EXCHANGE [A-Z]{1,7}|[A-O]([1-9]|1[0-5]) [AD] [A-Z ]{1,15}");
			if(!correctInput)
				System.out.println("Legal options:\nQUIT\nHELP\nPASS\nEXCHANGE <letters>\n<grid ref> <across/down> <word>  e.g. A1 A HELLO");
		}
		return ActionFactory.buildAction(nextAction);
	}
	
	public void promptActivePlayer(String activePlayer){
		System.out.println(activePlayer + "turn (enter \"HELP\" for legal options)");
	}
}
