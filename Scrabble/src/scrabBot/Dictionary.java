package scrabBot;

import java.util.HashSet;

public class Dictionary {
	HashSet<String> scrabbleDictionary;
	
	public Dictionary(){
		scrabbleDictionary = new HashSet<String>();
	}
	
	public boolean loadDictionary(){
		return false;
	}
	
	public boolean dictionaryCheck(String word){
		return true;
	}
}
