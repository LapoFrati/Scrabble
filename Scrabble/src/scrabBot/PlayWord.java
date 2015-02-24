package scrabBot;

public class PlayWord extends Action {
	private int row, column;
	private Direction dir;
	private String word;
	
	PlayWord(Choice choice, int row, char column, Direction dir, String word){
		super(choice);
		this.row = row;
		this.column = (int)(column - 'A') + 1; //board is indexed starting from 1
		this.dir = dir;
		this.word = word;
	}
	
	public int getRow(){
		return row;
	}
	
	public int getColumn(){
		return column;
	}
	
	public Direction getDirection(){
		return dir;
	}
	
	public String getWord(){
		return word;
	}
}
