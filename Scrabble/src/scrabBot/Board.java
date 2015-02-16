/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien ?
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
		boolean letter_used = false, connected = false;
		Frame frame = new Frame(player.getPlayerFrame());
		int i = 0, incrementedRow = row, incrementedColumn = column;
		char c;
		
		int len = word.length();
		
		// --> 	first check for not empty word
		
		if (len == 0)
			return CheckResult.NO_LETTER_USED;
		
		// --> bounds checks
		
			// check the left and top bounds
		if	(  row < 1 || column < 1
			// check the right bound
			|| (dir == Direction.VERTICAL && row + len > MAX_ROW)
			// check the bottom bound
			|| (dir == Direction.HORIZONTAL && column + len > MAX_COLUMN))
			return CheckResult.OUT_OF_BOUNDS;
		
		if (firstPlacement){
			// --> check if the word is the first word is properly centered
			if (   (dir == Direction.VERTICAL) && ( column != CENTER_COLUMN || CENTER_ROW < row || row + len < CENTER_ROW)
				|| (dir == Direction.HORIZONTAL) && (row != CENTER_ROW || CENTER_COLUMN < column || column + len < CENTER_COLUMN))
				return CheckResult.FIRST_NOT_CENTRED;
			
			// --> check if the player has the required letters
			
			if (len > frame.getFrameSize())
				return CheckResult.LACK_NECESSARY_LETTERS;
			
			while (i < len) {
				if (!frame.removeLetter(word.charAt(i))) //removeLetter returns false if the letter is not present in the frame
					return CheckResult.LACK_NECESSARY_LETTERS;
				i++;
			}
		}
		
		/*
		 * Controls yet to perform:
		 *  - Necessary letters
		 *  - No letter used
		 *  - Conflict
		 *  - Connection
		 */
		
		else {
			
			if  ( (dir == Direction.VERTICAL && board[row-1][column] != FREE_LOCATION)
				|| (dir == Direction.HORIZONTAL && board[row][column-1] != FREE_LOCATION))
				connected = true;
			
			while (i < len) {
				
				if	((	dir == Direction.VERTICAL
						&&(		board[incrementedRow+1][column] != FREE_LOCATION
							||	board[incrementedRow][column-1] != FREE_LOCATION
							||	board[incrementedRow][column+1] != FREE_LOCATION))
					||(	dir == Direction.HORIZONTAL
						&&(		board[row][incrementedColumn+1] != FREE_LOCATION
							||	board[row-1][incrementedColumn] != FREE_LOCATION
							||	board[row+1][incrementedColumn] != FREE_LOCATION)))
					connected = true;
				
				c = board[incrementedRow][incrementedColumn];
				
				if (c == FREE_LOCATION) {
					if (!frame.removeLetter(word.charAt(i)))
						return CheckResult.LACK_NECESSARY_LETTERS;
					else
						letter_used = true;
				}
				else {
					if (c != word.charAt(i))
						return CheckResult.CONFLICT;
				}
				
				if (dir == Direction.VERTICAL)
					incrementedRow++;
				if(dir == Direction.HORIZONTAL)
					incrementedColumn++;
				i++;
			}
			
			if (!letter_used)
				return CheckResult.NO_LETTER_USED;
			if (!connected)
				return CheckResult.NOT_CONNECTED;
		}

		// the placement passed all the tests
		return CheckResult.OK;
	}
	
	public void placeWord(String word, int row, int column, Direction dir){
		if(firstPlacement)
			firstPlacement = false;
		
		word = word.toUpperCase();
		
		if(dir == Direction.VERTICAL)
			for(int i = 0; i<word.length(); i++) board[row+i][column] = word.charAt(i);
		if(dir == Direction.HORIZONTAL)
			for(int i = 0; i<word.length(); i++) board[row][column+i] = word.charAt(i);
			
	}
	
	public char getLetterAt( int row, int column ){
		return board[row][column];
	}
	
	public void displayBoard()
	{
		System.out.println("    1    2    3    4    5    6    7    8    9    10   11   12   13   14   15");
		for(int counterX = 1; counterX <= MAX_ROW; counterX++)
		{
			System.out.println("   ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯");
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
		System.out.println("   ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯ ¯¯¯¯");	
	}

}
