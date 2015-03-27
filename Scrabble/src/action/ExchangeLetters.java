package action;


public class ExchangeLetters extends Action {
	private String lettersToChange;
	
	ExchangeLetters(String letters) {
		super(Choice.EXCHANGELETTERS);
		lettersToChange = letters;		
	}
	
	String getLettersToChange() {
		return lettersToChange;
	}
}
