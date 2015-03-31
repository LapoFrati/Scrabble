/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien 14209388
 */
package scrabBot;

public class MockScrabble extends Scrabble {
	
	public MockScrabble(){
		P1 = new MockPlayer();
		P2 = new MockPlayer();
		board = new MockBoard();
		pool = new MockPool();
		activePlayer = P1;
		ui = new UI(System.in);
		keepPlaying = true;
		dict = new Dictionary();
	}
}
