package scrabBot;

import java.io.InputStream;

public class UI {
	
	public UI(){ }
	
	public Action getUserInput(InputStream source, String player){
		return null;
	}
	
	/*legal options:
	*	QUIT
	*	HELP
	*	PASS
	*	EXCHANGE <letters>
	*	<grid ref> <across/down> <word>  e.g.“A1 A HELLO"
	*/
	public boolean validateInput(String inputString){
		return false;
	}
	
	private void promptActivePlayer(String activePlayer){
		
	}
}
