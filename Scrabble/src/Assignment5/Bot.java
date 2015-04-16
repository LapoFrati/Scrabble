/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien 14209388
 */

package Assignment5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Bot {

	private Word word = new Word();
	private String letters;
	private LinkedList<Word> legalWords;
	private String inputFileName = "sowpods.txt";
	
	private class GADDAG {
		
		private class dagNode {
			
			public char letter;
			public ArrayList<dagNode> children;
			
			public dagNode (char letter) {
				this.letter = letter;
				children = new ArrayList<dagNode>();
			}
			
			public void addChild (char letter) {
				children.add(new dagNode(letter));
			}
			
			public int hasChild (char letter) {
				for (dagNode child : children) {
					if (child.letter == letter)
						return children.indexOf(child);
				}
				return -1;
			}
			
			public dagNode getLastChild() {
				return children.get(children.size()-1);
			}
			
			public void print (int tabs) {
				System.out.println(letter);
				for (dagNode child : children) {
					for (int i = 0; i < tabs; i++) {
						System.out.print('\t');
					}
					child.print(tabs+1);
				}
			}
			
		}
		
		private dagNode root;
		
		public GADDAG () throws FileNotFoundException {
			
			root = new dagNode('@');
			String word = "";
			File inputFile = new File(inputFileName);
			Scanner in = new Scanner(inputFile);
			int i = 0;
			while (in.hasNextLine()) {
				word = in.nextLine();
				add(root, word);
				i++;
				System.out.println(i);
			}
			in.close();
		}
		
		public void add(dagNode node, String word) {
			for (int i = 1; i <= word.length(); i++) {
				String newWord = new StringBuilder(word.substring(0, i)).reverse().toString() + "@" + word.substring(i);
				recAdd(node, newWord);
			}
		}
		
		private void recAdd(dagNode node, String word) {
			if (word.length() != 0) {
				node.addChild(word.charAt(0));
				recAdd(node.getLastChild(), word.substring(1));
			}
		}
		
		public void print () {
			root.print(1);
		}
		
	}
	
	public Bot () throws FileNotFoundException {
		GADDAG gad = new GADDAG();
		gad.print();
		word.setWord(0, 0, Word.HORIZONTAL, "HELLO");
		letters = "XYZ";
	}
	
	public int getCommand (Player player, Board board) {
		// make a decision on the play here
		// use board.getSqContents to check what is on the board
		// use Board.SQ_VALUE to check the multipliers
		// use frame.getAllTiles to check what letters you have
		// return the corresponding commandCode from UI
		// if a play, put the start position and letters into word
		// if an exchange, put the characters into letters
		return(UI.COMMAND_PASS);
	}
	
	public Word getWord () {
		// should not change
		return(word);
	}
		
	public String getLetters () {
		// should not change
		return(letters);
	}
}
