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
	public void testCalculatePlacementPoints_JOIN () {
		scrabble.calculatePlacementPoints("BOBCAT", Board.CENTER_ROW, Board.CENTER_COLUMN - 7, Direction.HORIZONTAL);
		assertEquals(15, scrabble.activePlayer.getPlayerScore());
	}
	
	@Test
	public void testCalculatePlacementPoints_BONUS_AND_NEAR () {
		scrabble.calculatePlacementPoints("SKYJACK", 3, 4, Direction.VERTICAL);
		assertEquals(118, scrabble.activePlayer.getPlayerScore());
	}
	
	@Test
	public void testCalculatePlacementPoints_CROSS () {
		scrabble.calculatePlacementPoints("CYAN", 6, 9, Direction.VERTICAL);
		assertEquals(14, scrabble.activePlayer.getPlayerScore());
	}
	
	@Test
	public void testCalculatePlacementPoints_CONTINUE () {
		scrabble.calculatePlacementPoints("CATS", 8, 8, Direction.HORIZONTAL);
		assertEquals(6, scrabble.activePlayer.getPlayerScore());
	}
	
	@Test
	public void testCalculatePlacementPoints_MIDDLE_CROSS () {
		scrabble.calculatePlacementPoints("CAT", 7, 7, Direction.VERTICAL);
		assertEquals(17, scrabble.activePlayer.getPlayerScore());
	}

}
