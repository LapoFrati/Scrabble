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
	public void calculatePlacementPoints(String wordPlayed, int row, int column, Direction dir){
		int total = 0;
		int wordMult = 1;
		
		for(int i = 0, len = wordPlayed.length(); i < len; i++) {
			if (board.getLetterAt(row, column) == Board.FREE_LOCATION) {
				wordMult *= Board.wordMultiplier[row][column];
				total += Board.letterMultiplier[row][column] * pool.checkValue(wordPlayed.charAt(i));
			}
			else {
				total += pool.checkValue(wordPlayed.charAt(i));
			}
			if(dir == Direction.VERTICAL)
				row++;
			else
				column++;
		}
		
		total *= wordMult;
		
		if (wordPlayed.length() == 7) {
			total += 50;
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
