package playerTest;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import testUtil.TestDriver;
import aiPlayerStages.RandomPlayer;
import exceptions.InvalidSyntaxException;


public class RandomPlayerTest {

	@Test
	public void testDetermineRandomPosition() throws InvalidSyntaxException {
		boolean success = TestDriver.likeExpected(
				  "eee"
				+ "eee"
				+ "eee", 
				(field, dec) -> RandomPlayer.determineRandomPosition(field, dec));		
		assertTrue("EmptyField", success);
		
		success = TestDriver.likeExpected(
				  "xox"
				+ "oxo"
				+ "xxo", 
				(field, dec) -> RandomPlayer.determineRandomPosition(field, dec));		
		assertTrue("Full Field", success);
		
		success = TestDriver.likeExpected(
				  "oox"
				+ "oxo"
				+ "xxe", 
				(field, dec) -> RandomPlayer.determineRandomPosition(field, dec));		
		assertTrue("One Pos is missing Field", success);
		
		success = TestDriver.likeExpected(
				  "xeo"
				+ "oxe"
				+ "oxe", 
				(field, dec) -> RandomPlayer.determineRandomPosition(field, dec));		
		assertTrue("normal Field", success);
	}

}
