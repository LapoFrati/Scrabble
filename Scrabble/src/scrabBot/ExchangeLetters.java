package scrabBot;

public class ExchangeLetters extends Action {
	private String lettersToChange;
	
	ExchangeLetters(Choice choice, String letters){
		super(choice);
		lettersToChange = letters;		
	}
	
	String getLettersToChange(){
		return lettersToChange;
	}
}
