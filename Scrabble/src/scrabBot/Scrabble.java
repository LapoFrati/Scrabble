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
			board.displayBoard();
			activePlayer.getPlayerFrame();
			if(activePlayer == P1)
			{
				ui.promptActivePlayer("Player 1");
			}
			if(activePlayer == P2)
			{
				ui.promptActivePlayer("Player 2");
			}
		}
	}
	
	/* updates the score of the active player
	 * 
	 * remember the bonus points for using all the letters!
	 * 
	 * */
	public void calculatePlacementPoints(String wordPlayed, int xCord, int yCord, Direction dir){
		int total = 0;
		int y = yCord;
		int x = xCord;
		int wordMult = 1;
		if(wordPlayed.length() == 7)
		{
			total = 50;
		}
		
			if(dir == Direction.VERTICAL)
				x++;
			else
				y++;
		for(int i = 0; i < wordPlayed.length(); i++)
		{
			if(dir == Direction.VERTICAL)
			{
			if(Board.wordMultiplier[x][y] != 1)
			{
				wordMult = Board.wordMultiplier[x][y];
				Board.wordMultiplier[x][y] = 1; //So this word multiplier is only applied once if other words use same letter
			}
			x++;
			}
			else
			{
				if(Board.wordMultiplier[x][y] != 1)
				{
					wordMult = Board.wordMultiplier[x][y];
					Board.wordMultiplier[x][y] = 1; //So this word multiplier is only applied once if other words use same letter
				}
				y++;
			}
		}
		
	x = xCord;
	y = yCord;
	
		for(int j = 0; j < wordPlayed.length(); j++)
		{
			if(dir == Direction.VERTICAL)
			{
					total = total + pool.checkValue(board.getLetterAt(x,y)) * Board.letterMultiplier[x][y] * wordMult;
					Board.letterMultiplier[x][y] = 1; //So this letter multiplier is only applied once if other words use same letter
					x++;
			}
			else
			{
				total = total + pool.checkValue(board.getLetterAt(x,y)) * Board.letterMultiplier[x][y] * wordMult;
				Board.letterMultiplier[x][y] = 1; //So this letter multiplier is only applied once if other words use same letter
				y++;
			}
		}
		activePlayer.increasePlayerScoreBy(total);
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
