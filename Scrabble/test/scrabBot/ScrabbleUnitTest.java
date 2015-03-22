/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien 14209388
 */
package scrabBot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ScrabbleUnitTest {
	Scrabble scrabble;
	
	@Before
	public void setUp(){
		scrabble = new MockScrabble();
	}
	
	@Test
	public void testCalculatePlacementPoints_1 () {
		scrabble.calculatePlacementPoints("BOBCAT", Board.CENTER_ROW, Board.CENTER_COLUMN - 7, Direction.HORIZONTAL);
		assertEquals(scrabble.activePlayer.getPlayerScore(), 15);
	}
	
	@Test
	public void testCalculatePlacementPoints_2 () {
		scrabble.calculatePlacementPoints("BOBCAT", Board.CENTER_ROW, Board.CENTER_COLUMN - 7, Direction.HORIZONTAL);
		assertEquals(scrabble.activePlayer.getPlayerScore(), 15);
	}
}
