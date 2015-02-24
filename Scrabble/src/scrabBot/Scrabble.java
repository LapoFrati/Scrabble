package scrabBot;

public class Scrabble {
	
	protected Player P1;
	protected Player P2;
	protected Board board;
	protected Pool pool;
	protected int scoreP1, scoreP2, row;
	protected Player activePlayer;
	protected UI ui;
	protected boolean keepPlaying;
	protected Action playerChoice;
	
	public Scrabble(){
		P1 = new Player();
		P2 = new Player();
		board = new Board();
		pool = new Pool();
		scoreP1 = scoreP2 = 0;
		activePlayer = P1;
		ui = new UI();
		keepPlaying = true;
	}
	

	public void startGame(){
		while(keepPlaying){
			//Main loop of the game
		}
	}
	
	/* updates the score of the active player
	 * 
	 * remember the bonus points for using all the letters!
	 * 
	 * */
	public void calculatePlacementPoints(){

	}
	
	private void quitGame(){
		keepPlaying = false;
	}
	
	//changes the active player
	private void passTurn(){
		if(activePlayer == P1)
			activePlayer = P2;
		else
			activePlayer = P1;
	}
	
	private void displayHelp(){
		//TODO: ask what information has to be printed
	}
	
	//use the frame of the active player
	private void exchangeLetters( String oldLetters ){
		
	}	
}
