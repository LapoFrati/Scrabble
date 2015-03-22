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
		pool = new Pool();
		board = new Board();
		P1 = new Player();
		P2 = new Player();
		P1.getPlayerFrame().refillFrame(pool);
		P2.getPlayerFrame().refillFrame(pool);
		activePlayer = P1;
		ui = new UI();
		keepPlaying = true;
	}
	

	public void startGame(){
		while(keepPlaying){
			ui.gameInfo(this);
			ui.promptActivePlayer(activePlayer,board);
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
		int total = 0, otherWordsTotal = 0, wordMult = 1, lettersUsed = 0;
		
		for(int i = 0; i < wordPlayed.length(); i++) {
			if (board.getLetterAt(row, column) == Board.FREE_LOCATION) {
				lettersUsed++;
				wordMult *= Board.wordMultiplier[row][column];
				total += Board.letterMultiplier[row][column] * pool.checkValue(wordPlayed.charAt(i));
				if (dir == Direction.VERTICAL && (board.getLetterAt(row, column-1) != Board.FREE_LOCATION || board.getLetterAt(row, column+1) != Board.FREE_LOCATION)) {
					otherWordsTotal += calculateOtherWordsPoints(wordPlayed.charAt(i), row, column, Direction.HORIZONTAL);
				}
				else if (board.getLetterAt(row-1, column) != Board.FREE_LOCATION || board.getLetterAt(row+1, column) != Board.FREE_LOCATION) {
					otherWordsTotal += calculateOtherWordsPoints(wordPlayed.charAt(i), row, column, Direction.VERTICAL);
				}
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
		
		total += otherWordsTotal;
		
		if (lettersUsed == 7) {
			total += 50;
		}

		activePlayer.increasePlayerScoreBy(total);
	}
	
	private void quitGame(){
		keepPlaying = false;
	}
	
	private void displayHelp(){
		System.out.println("Legal options:\nQUIT\nHELP\nPASS\nEXCHANGE <letters>\n<grid ref> <across/down> <word>  e.g. A1 A HELLO");
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
	
	private int calculateOtherWordsPoints(char startingLetter, int row, int column, Direction dir){
		int total = 0, newRow = row, newColumn = column, diff = 0;
		char newChar;
		String newWord = "";
		boolean finished = false, notPlacedLetterPassed = false;
		
		if (dir == Direction.HORIZONTAL) {
			int tempColumn;
			while (board.getLetterAt(row, newColumn-1) != Board.FREE_LOCATION)
				newColumn--;
			diff = column - newColumn;
			tempColumn = newColumn;
			while (!finished) {
				if ((newChar = board.getLetterAt(row, tempColumn)) == Board.FREE_LOCATION) {
					if (notPlacedLetterPassed)
						finished = true;
					else {
						notPlacedLetterPassed = true;
						newWord += startingLetter;
					}
				}
				else
					newWord += newChar;
				tempColumn++;
			}
		}
		else {
			int tempRow;
			while (board.getLetterAt(newRow-1, column) != Board.FREE_LOCATION)
				newRow--;
			diff = row - newRow;
			tempRow = newRow;
			while (!finished) {
				if ((newChar = board.getLetterAt(tempRow, column)) == Board.FREE_LOCATION) {
					if (notPlacedLetterPassed)
						finished = true;
					else {
						notPlacedLetterPassed = true;
						newWord += startingLetter;
					}
				}
				else
					newWord += newChar;
				tempRow++;
			}
		}
		
		for(int i = 0; i < newWord.length(); i++) {
			if (i==diff)
				total += pool.checkValue(newWord.charAt(i)) * Board.letterMultiplier[row][column];
			else
				total += pool.checkValue(newWord.charAt(i));
			if(dir == Direction.VERTICAL)
				newRow++;
			else
				newColumn++;
		}
		
		total *= Board.wordMultiplier[row][column];

		return total;
	}
}
