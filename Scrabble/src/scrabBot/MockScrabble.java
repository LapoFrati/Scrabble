package scrabBot;

public class MockScrabble extends Scrabble {
	
	public MockScrabble(){
		P1 = new MockPlayer();
		P2 = new MockPlayer();
		board = new MockBoard();
		pool = new MockPool();
		scoreP1 = scoreP2 = 0;
		activePlayer = P1;
		ui = new UI();
		keepPlaying = true;
	}
}
