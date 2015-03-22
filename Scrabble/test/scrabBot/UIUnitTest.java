/* Team: Random2
 * Members: Lapo Frati 14202439, Simone Pignotti 14202498, Brennan O'Brien 14209388
 */
package scrabBot;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

public class UIUnitTest {
	UI testUI;
	
	@Before
	public void setUp(){
		testUI = new UI();
	}
	
	@Test
	public void getUserInput_QUIT(){
		byte[] data = "QUIT".getBytes();

        InputStream input = new ByteArrayInputStream(data);

        assertEquals(Choice.QUIT, testUI.getUserInput(input).getChoice());
	}
	
	@Test
	public void getUserInput_PASSTURN(){
		byte[] data = "PASS".getBytes();

        InputStream input = new ByteArrayInputStream(data);

        assertEquals(Choice.PASSTURN, testUI.getUserInput(input).getChoice());
	}
	
	@Test
	public void validateInput_PASSTURN_TRUE(){
        assertTrue( testUI.validateInput("PASS"));
	}
	
	@Test
	public void validateInput_PASSTURN_FALSE(){
        assertFalse( testUI.validateInput("PAS"));
	}
	
	@Test
	public void getUserInput_GETHELP(){
		byte[] data = "HELP".getBytes();

        InputStream input = new ByteArrayInputStream(data);

        assertEquals(Choice.GETHELP, testUI.getUserInput(input).getChoice());
	}
	
	@Test
	public void validateInput_GETHELP_TRUE(){
        assertTrue( testUI.validateInput("HELP"));
	}
	
	@Test
	public void validateInput_GETHELP_FALSE(){
        assertFalse( testUI.validateInput("HELPP"));
	}
	
	@Test
	public void getUserInput_PLACEWORD(){
		byte[] data = "A1 A HELLO".getBytes();

        InputStream input = new ByteArrayInputStream(data);

        assertEquals(Choice.PLAYWORD, testUI.getUserInput(input).getChoice());
	}
	
	@Test
	public void validateInput_PLACEWORD_TRUE(){
        assertTrue( testUI.validateInput("A1 A HELLO"));
	}
	
	@Test
	public void validateInput_PLACEWORD_FALSE_WRONGREF(){
        assertFalse( testUI.validateInput("Z13 A HELLO"));
	}
	
	@Test
	public void validateInput_PLACEWORD_FALSE_WRONGDIR(){
        assertFalse( testUI.validateInput("A1 P HELLO"));
	}
	
	@Test
	public void validateInput_PLACEWORD_FALSE_WRONGWORD(){
        assertFalse( testUI.validateInput("A1 A @@@@"));
	}
	
	@Test
	public void validateInput_PLACEWORD_TRUE_SPACE(){
        assertTrue( testUI.validateInput("A1 A HELLO WORLD"));
	}
	
	@Test
	public void getUserInput_EXCHANGELETTERS(){
		byte[] data = "EXCHANGE ABC".getBytes();

        InputStream input = new ByteArrayInputStream(data);

        assertEquals(Choice.EXCHANGELETTERS, testUI.getUserInput(input).getChoice());
	}
	
	@Test
	public void validateInput_EXCHANGELETTERS_TRUE(){
        assertTrue( testUI.validateInput("EXCHANGE ABC"));
	}
	
	@Test
	public void validateInput_EXCHANGELETTERS_FALSE_WRONGCHOICE(){
        assertFalse( testUI.validateInput("EXCHANG ABC"));
	}
	
	@Test
	public void validateInput_EXCHANGELETTERS_FALSE_WRONGWORD(){
        assertFalse( testUI.validateInput("EXCHANGE @@@@"));
	}
	
	@Test
	public void validateInput_EXCHANGELETTERS_FALSE_WORDTOOLONG(){
        assertFalse( testUI.validateInput("EXCHANGE ABCDEFGHIJKLMNOPQRSTUVXYZ"));
	}
}
