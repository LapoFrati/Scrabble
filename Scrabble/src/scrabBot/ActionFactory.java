package scrabBot;

public class ActionFactory {
	public static Action buildCar(Choice choice) {
        Action action = null;
        
        switch (choice) {
        case QUIT:
            action = new Quit(choice);
            break;
 
        case PASSTURN:
            action = new PassTurn(choice);
            break;
 
        case GETHELP:
            action = new GetHelp(choice);
            break;
 
        default:
            // throw some exception
            break;
        }
        
        return action;
    }
	
	public static Action buildCar(Choice choice, String letters) {
        Action action = null;
        
        if( choice == Choice.EXCHANGELETTERS )
        	action = new ExchangeLetters(choice, letters);
        else
        	System.out.println("Error");
            // throw some exception
            
        return action;
    }
	
	public static Action buildCar(Choice choice, int row, char column, Direction dir, String word) {
        Action action = null;
        
        if( choice == Choice.PLAYWORD )
        	action = new PlayWord(choice, row, column, dir, word);
        else
        	System.out.println("Error");
            // throw some exception
            
        return action;
    }
}
