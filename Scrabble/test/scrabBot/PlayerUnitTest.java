package scrabBot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerUnitTest {
	Player testPlayer;
	Pool testPool;
	final static int INITIAL_SCORE = 0;
	final static int EXPECTED_SCORE = 42;
	final static int SOME_SCORE = 42;
	final static int EMPTY_FRAME_SIZE = 0;
	final static String playerName = "Foo";
	
	@Before
	public void setUp(){
		testPlayer = new Player();
		testPool = new Pool();
	}
	
	@Test
	public void testGetPlayerName() {
		assertTrue(testPlayer.getPlayerName().matches("(Player)[0-9]*"));
	}
	
	@Test
	public void testResetPlayerScore() {
		testPlayer.increasePlayerScoreBy(SOME_SCORE);
		testPlayer.resetPlayerData();
		assertEquals(testPlayer.getPlayerScore(), INITIAL_SCORE);
	}
	
	@Test
	public void testResetPlayerFrame() {
		testPlayer.getPlayerFrame().refillFrame(testPool);
		testPlayer.resetPlayerData();
		assertEquals(testPlayer.getPlayerFrame().getFrameSize(), EMPTY_FRAME_SIZE);
	}

	@Test
	public void testSetPlayerName() {
		testPlayer.setPlayerName(playerName);
		assertEquals(testPlayer.getPlayerName(), playerName);
	}
	
	@Test(expected=NullNameException.class)
	public void testSetPlayer_raiseNullNameException(){
		testPlayer.setPlayerName(null);
	}

	@Test
	public void testIncreasePlayerScoreBy() {
		testPlayer.increasePlayerScoreBy(SOME_SCORE);
		assertEquals(testPlayer.getPlayerScore(), EXPECTED_SCORE);
	}
	
	@Test(expected=NegativeScoreException.class)
	public void testIncreasePlayerScoreBy_raiseNegativeScoreException() {
		testPlayer.increasePlayerScoreBy(-1);
	}
	
	@Test
	public void testGetPlayerScore() {
		assertEquals(testPlayer.getPlayerScore(), INITIAL_SCORE);
	}

}
