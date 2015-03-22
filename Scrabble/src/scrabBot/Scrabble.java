package scrabBot;

public class Scrabble {
	
	protected Player P1;
	protected Player P2;
	protected Board board;
	protected Pool pool;
	protected Player activePlayer;
	protected UI ui;
	protected boolean keepPlaying, proceed;
	protected Action playerChoice;
	
	public Scrabble(){
		P1 = new Player();
		P2 = new Player();
		board = new Board();
		pool = new Pool();
		activePlayer = P1;
		ui = new UI();
		keepPlaying = true;
	}
	

	public void startGame(){
		while(keepPlaying){
			
			ui.promptActivePlayer(activePlayer.getPlayerName());
			proceed = false;
			while(!proceed){
				playerChoice = ui.getUserInput(System.in);
				
				switch(playerChoice.getChoice()){
					case PLAYWORD:	PlayWord wordToPlace = (PlayWord) playerChoice;
									CheckResult result = board.checkPlacement(wordToPlace.getWord(), 
											wordToPlace.getRow(), 
											wordToPlace.getColumn(), 
											wordToPlace.getDirection(),
											activePlayer);
									if(result == CheckResult.OK){
										
										calculatePlacementPoints(wordToPlace.getWord(), 
																 wordToPlace.getRow(),
																 wordToPlace.getColumn(), 
																 wordToPlace.getDirection());
										board.placeWord(wordToPlace.getWord(), 
														wordToPlace.getRow(), 
														wordToPlace.getColumn(), 
														wordToPlace.getDirection());
										proceed = true;	
									}
									else
										System.out.println("Invalid Placement. Error: "+result.name());
									break;
					case PASSTURN: 	proceed = true;
									break;
					case GETHELP:	displayHelp();
									break;
					case EXCHANGELETTERS:	proceed = exchangeLetters(((ExchangeLetters)playerChoice).getLettersToChange());
											break;
					case QUIT: 		quitGame();;
									proceed = true;
									System.out.println(activePlayer.getPlayerName() + " surrendered.");
									break;
				}
			}
			
			passTurn();
			
		}
	}
	
	public void passTurn(){
		activePlayer = (activePlayer.equals(P1) ? P2 : P1 );
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
	
	private void displayHelp(){
		//TODO: ask what information has to be printed
	}
	
	
	//use the frame of the active player
	private boolean exchangeLetters( String lettersToExchange ){
		Frame frame = activePlayer.getPlayerFrame();
		boolean result;
		result = frame.containsLetters(lettersToExchange);
		if(result){
			frame.removeLetters(lettersToExchange);
			frame.refillFrame(pool);
		}
		return result;
	}	
}
