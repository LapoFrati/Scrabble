package Assignment5;

public class Bot {

	private Word word = new Word();
	private String letters;
	
	Bot () {
		word.setWord(0, 0, Word.HORIZONTAL, "HELLO");
		letters = "XYZ";
	}
	
	public int getCommand (Player player, Board board) {
		// make a decision on the play here
		// use board.getSqContents to check what is on the board
		// use Board.SQ_VALUE to check the multipliers
		// use frame.getAllTiles to check what letters you have
		// return the corresponding commandCode from UI
		// if a play, put the start position and letters into word
		// if an exchange, put the characters into letters
		return(UI.COMMAND_PASS);
	}
	
	public Word getWord () {
		// should not change
		return(word);
	}
		
	public String getLetters () {
		// should not change
		return(letters);
	}
}
