package scrabBot;

public class MockScrabble extends Scrabble {
	
	public MockScrabble(){
		P1 = new MockPlayer();
		P2 = new MockPlayer();
		board = new MockBoard();
		pool = new MockPool();
		activePlayer = P1;
		ui = new UI();
		keepPlaying = true;
	}
}
