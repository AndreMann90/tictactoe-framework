package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.exceptions.InvalidSyntaxException;
import de.mann.tictactoeframework.testUtil.TestDriver;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RandomPlayerTest {

	@Test
	public void testDetermineRandomPosition() throws InvalidSyntaxException {
		boolean success = TestDriver.likeExpected(
				  "eee"
				+ "eee"
				+ "eee",
				RandomPlayer::determineRandomPosition);
		assertTrue("EmptyField", success);
		
		success = TestDriver.likeExpected(
				  "xox"
				+ "oxo"
				+ "xxo",
				RandomPlayer::determineRandomPosition);
		assertTrue("Full Field", success);
		
		success = TestDriver.likeExpected(
				  "oox"
				+ "oxo"
				+ "xxe",
				RandomPlayer::determineRandomPosition);
		assertTrue("One Pos is missing Field", success);
		
		success = TestDriver.likeExpected(
				  "xeo"
				+ "oxe"
				+ "oxe",
				RandomPlayer::determineRandomPosition);
		assertTrue("normal Field", success);
	}

}
