/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien 14209388
 */
package scrabBot;

public class Board {	
	public static final int CENTER_ROW = 8, CENTER_COLUMN = 8, MAX_COLUMN = 15, MAX_ROW = 15, PADDING = 2;
	public static final char FREE_LOCATION = '#';
	
	private boolean firstPlacement;

	private static final int wordMultiplier[][] =
		{{0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }, 
		{ 0 , 3 , 1 , 1 , 1 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 1 , 1 , 1 , 3 , 0 },
		{ 0 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 0 },
		{ 0 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 3 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 3 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 0 },
		{ 0 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 0 },
		{ 0 , 3 , 1 , 1 , 1 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 1 , 1 , 1 , 3 , 0 },
		{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }};
	
	private static final int letterMultiplier[][] =
		{{0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 },
		{ 0 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 0 },
		{ 0 , 1 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 2 , 1 , 1 , 0 },
		{ 0 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 3 , 1 , 1 , 1 , 1 , 1 , 0 },
		{ 0 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 2 , 1 , 1 , 1 , 0 },
		{ 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 }};
	
	private char board[][];
	
	public enum Direction {
		VERTICAL, HORIZONTAL
	}
	
	public enum CheckResult {
		OK,
		LACK_NECESSARY_LETTERS,
		OUT_OF_BOUNDS,
		CONFLICT,
		NO_LETTER_USED,
		FIRST_NOT_CENTRED,
		NOT_CONNECTED,
		NOT_IN_DICTIONARY
	}
	
	public Board(){
		resetBoard();
	}
	
	public Board(char board[][]){
		firstPlacement = false;
		this.board = board;
	}
	
	public void resetBoard(){
		firstPlacement = true;
		board = new char[MAX_ROW + PADDING][MAX_COLUMN + PADDING];
		for(int counterX = 1; counterX <= MAX_ROW; counterX++)
			for(int counterY = 1; counterY <= MAX_COLUMN; counterY++)
			{
					board[counterX][counterY] = FREE_LOCATION;
			}
	}

	public CheckResult checkPlacement(String word, int row, int column, Direction dir, Player player) {
		boolean letterUsed = false, connectedToAnotherWord = false;
		Frame frame = new Frame(player.getPlayerFrame());
		int incrementedRow = row, incrementedColumn = column, i=0, j=0, len = word.length();
		char c;
		
		// --> bounds checks
		if	(outOfBounds(dir, row, column, len))
			return CheckResult.OUT_OF_BOUNDS;
		
		// --> check if the player has the required letters, not counting the letters already on the board
		for( Character letter : word.toCharArray() ){
			if (board[row+i][column+j] == FREE_LOCATION ){
				letterUsed = true;
				if(!frame.removeLetter(letter)) // removeLetter returns false if the letter is not present in the frame
					return CheckResult.LACK_NECESSARY_LETTERS;
			}
			if (dir == Direction.VERTICAL) 
				i++;
			else
				j++;
		}
		
		// --> check that at least one letter has been used
		if (!letterUsed)
			return CheckResult.NO_LETTER_USED;
		
		if (firstPlacement){
			// --> check placement of first word
			if (notCentered(dir, row, column, len))
				return CheckResult.FIRST_NOT_CENTRED;	
		}
		else {
			for( Character letter : word.toCharArray() ){
				// --> check if the word connects to another one looking at surrounding positions
				if	(connectedToAnotherWord == false && checkIfConnectedToAnotherWord(dir, incrementedRow, incrementedColumn))
					connectedToAnotherWord = true;
				
				c = board[incrementedRow][incrementedColumn];
				if (c != FREE_LOCATION && c != letter )
					return CheckResult.CONFLICT;
				
				if (dir == Direction.VERTICAL)
					incrementedRow++;
				else
					incrementedColumn++;
			}
			
			// --> check if word has connected to at least one word 
			if (!connectedToAnotherWord)
				return CheckResult.NOT_CONNECTED;
		}

		// the placement passed all the tests
		return CheckResult.OK;
	}
	
	public void placeWord(String word, int row, int column, Direction dir){
		if(firstPlacement)
			firstPlacement = false;
		
		word = word.toUpperCase();
		
		for(int i = 0; i<word.length(); i++)
			if(dir == Direction.VERTICAL)
				board[row+i][column] = word.charAt(i);
			else
				board[row][column+i] = word.charAt(i);
			
	}
	
	public char getLetterAt( int row, int column ){
		return board[row][column];
	}
	
	public void displayBoard()
	{
		System.out.println("    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15");
		for(int counterX = 1; counterX <= MAX_ROW; counterX++)
		{
			System.out.println("   ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----");
			if(counterX < 10)
				System.out.print(counterX + " ");
			else
				System.out.print(counterX);
			
			for(int counterY = 1; counterY <= MAX_COLUMN; counterY++)
			{
				if(counterY == 1)
				{
					if(board[counterX][counterY] != FREE_LOCATION)
						System.out.print("| " + board[counterX][counterY] + "  | ");
					else if (wordMultiplier[counterX][counterY] != 1)
						System.out.print("| " + wordMultiplier[counterX][counterY] + "W" +  " | ");
					else if (letterMultiplier[counterX][counterY] != 1)
						System.out.print("| " + letterMultiplier[counterX][counterY] + "L" + " | ");
					else
						System.out.print("|    | ");
				}
				else 
					if(board[counterX][counterY] != FREE_LOCATION)
						System.out.print(board[counterX][counterY] + "  | ");
					else if(counterX == 8 & counterY == 8)
						System.out.print("**" + " | ");
					else if (wordMultiplier[counterX][counterY] != 1)
						System.out.print(wordMultiplier[counterX][counterY] + "W" + " | ");
					else if (letterMultiplier[counterX][counterY] != 1)
						System.out.print(letterMultiplier[counterX][counterY] + "L" + " | ");
					else
						System.out.print("   | ");
			}
			System.out.println("");
		}
		System.out.println("   ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ---- ----");	
	}
	
	private boolean outOfBounds (Direction dir, int row, int column, int len) {
		if	(  row < 1 || column < 1 // check the left and top bounds
				|| (dir == Direction.VERTICAL && row + len > MAX_ROW) // check the right bound
				|| (dir == Direction.HORIZONTAL && column + len > MAX_COLUMN)) // check the bottom bound
			return true;
		else
			return false;
	}
	
	private boolean checkIfConnectedToAnotherWord (Direction dir, int row, int column) {
		if	(	
					board[row][column+1] != FREE_LOCATION
				||  board[row][column-1] != FREE_LOCATION
				||	board[row-1][column] != FREE_LOCATION
				||	board[row+1][column] != FREE_LOCATION
			)
			return true;
		else
			return false;
	}

	private boolean notCentered (Direction dir, int row, int column, int len) {
		if (	(dir == Direction.VERTICAL) && ( column != CENTER_COLUMN || CENTER_ROW < row || row + len < CENTER_ROW)
				||	(dir == Direction.HORIZONTAL) && (row != CENTER_ROW || CENTER_COLUMN < column || column + len < CENTER_COLUMN))
			return true;
		else
			return false;
	}
}
