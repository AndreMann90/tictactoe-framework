package aiStagesTest;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aiPlayerStages.ThreeInARowPlayer;
import exceptions.InvalidSyntaxException;
import players.PlayerID;
import testUtil.TestDriver;


public class ThreeInARowPlayerTest {


	@Test
	public void testFindFinishingPositionTopRow() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionTopRow");
		boolean success = TestDriver.likeExpected(
				  "xex"
				+ "o--"
				+ "-o-", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "exx"
				+ "o--"
				+ "-o-", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "xxe"
				+ "o--"
				+ "-o-", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
		
	}

	@Test
	public void testFindFinishingPositionBottomRow() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionBottomRow");
		boolean success = TestDriver.likeExpected(
				  "xxo"
				+ "oo-"
				+ "xex", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "xxo"
				+ "oo-"
				+ "exx", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "xox"
				+ "oo-"
				+ "xxe", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
	}

	@Test
	public void testFindFinishingPositionLeftRow() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionLeftRow");
		boolean success = TestDriver.likeExpected(
				  "xxo"
				+ "eo-"
				+ "xox", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "xxo"
				+ "xo-"
				+ "eox", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "exo"
				+ "x--"
				+ "xox", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
	}

	@Test
	public void testFindFinishingPositionRightRow() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionRightRow");
		boolean success = TestDriver.likeExpected(
				  "o-x"
				+ "x-e"
				+ "oox", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "o-e"
				+ "xox"
				+ "-ox",
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "o-x"
				+ "xox"
				+ "ooe", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
	}

	@Test
	public void testFindFinishingPositionCrossRow1() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionCrossRow1");
		boolean success = TestDriver.likeExpected(
				  "x--"
				+ "-eo"
				+ "o-x", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "e--"
				+ "ox-"
				+ "o-x",
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "xoo"
				+ "-x-"
				+ "--e", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
	}

	@Test
	public void testFindFinishingPositionCrossRow2() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionCrossRow2");
		boolean success = TestDriver.likeExpected(
				  "--x"
				+ "-eo"
				+ "x-o", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "--e"
				+ "ox-"
				+ "x-o",
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "oox"
				+ "-x-"
				+ "e--", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
	}

	@Test
	public void testFindFinishingPositionCrossRow3() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionCrossRow3");
		boolean success = TestDriver.likeExpected(
				  "-x-"
				+ "-eo"
				+ "-xo", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "-xo"
				+ "-x-"
				+ "oeo",
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "oeo"
				+ "-x-"
				+ "ox-", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
	}

	@Test
	public void testFindFinishingPositionCrossRow4() throws InvalidSyntaxException {
		System.out.println("testFindFinishingPositionCrossRow4");
		boolean success = TestDriver.likeExpected(
				  "-o-"
				+ "xex"
				+ "-oo", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 1", success);
		
		success = TestDriver.likeExpected(
				  "--o"
				+ "xxe"
				+ "o-o",
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 2", success);
		
		success = TestDriver.likeExpected(
				  "o-o"
				+ "exx"
				+ "o--", 
				(field, dec) -> ThreeInARowPlayer.findFinishingPosition(field, PlayerID.Player1, dec));		
		assertTrue("Part 3", success);
	}

}
