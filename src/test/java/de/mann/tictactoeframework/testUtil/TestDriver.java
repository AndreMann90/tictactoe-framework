package de.mann.tictactoeframework.testUtil;

import de.mann.tictactoeframework.aiPlayerStages.Decisioner;
import de.mann.tictactoeframework.exceptions.InvalidMoveException;
import de.mann.tictactoeframework.exceptions.InvalidSyntaxException;
import de.mann.tictactoeframework.players.PlayerID;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;
import de.mann.tictactoeframework.playingField.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestDriver {

	/**
	 * This is a test driver to set up easily the playing field by defining a
	 * String that represents the field. Moreover, the String specifies the
	 * expected result of the testFunction.
	 * 
	 * @param fieldSetUp
	 *            a sequence to represent the current Field and expected move: 'x'
	 *            for Player1 (begins), 'o' for Player2, 'e' for expected result and
	 *            '-' for empty field
	 * @param testFunction
	 *            the function that determines the expected move
	 * @return true if testFunction behaved like expected
	 * @throws InvalidSyntaxException
	 *             invalid String fieldSetUp: only use "xXoOeE_-", length must be 9,
	 *             Player1 must have as many positions as Player2 or one more.
	 */
	public static boolean likeExpected(String fieldSetUp, AiTest testFunction) throws InvalidSyntaxException {

		if (fieldSetUp.length() != 9
				|| !fieldSetUp.matches("[xXoOeE_-]*")) {
			throw new InvalidSyntaxException();
		}

		/*
		 * setting up the playing field
		 */

		List<Point> expected = new LinkedList<>();
		List<Point> firstPlayerPositions = new LinkedList<>();
		List<Point> secondPlayerPositions = new LinkedList<>();
		PlayingField field = new PlayingField();

		fieldSetUp = fieldSetUp.toLowerCase();
		for (int i = 0; i < fieldSetUp.length(); i++) {
			char current = fieldSetUp.charAt(i);
			Point curPos = new Point(i / 3, i % 3);
			if (current == 'x') {
				firstPlayerPositions.add(curPos);
			} else if (current == 'o') {
				secondPlayerPositions.add(curPos);
			} else if (current == 'e') {
				expected.add(curPos);
			}
		}

		final int diff = firstPlayerPositions.size()
				- secondPlayerPositions.size();
		if (diff != 0 && diff != 1) {
			/*
			 * since x-Player (first Player) and o-Player (first Player) make
			 * their moves alternating, x-Player has as many positions as
			 * o-Player or one more
			 */
			throw new InvalidSyntaxException();
		}

		// remember: firstPlayer always begins
		try {

			for (int i = 0; i < secondPlayerPositions.size(); i++) {
				final Point movePlayerOne = firstPlayerPositions.get(i);
				field.makeMove(movePlayerOne, PlayerID.Player1);

				final Point movePlayerTwo = secondPlayerPositions.get(i);
				field.makeMove(movePlayerTwo, PlayerID.Player2);
			}

			if (diff == 1) {
				final Point lastMove = firstPlayerPositions
						.get(firstPlayerPositions.size() - 1);
				field.makeMove(lastMove, PlayerID.Player1);
			}

		} catch (InvalidMoveException e) {
			e.printStackTrace();
			return false;
		}

		/*
		 * After setting up the playing field it follows the actual test
		 */
		Decisioner decisioner = new Decisioner(new Random());
		
		testFunction.function(field, decisioner);

		FieldPosition result = decisioner.decide();

		if (result.isEmpty()) {
			System.out.println("No Point and does expect one point: " + !expected.isEmpty());
		} else {
			System.out.println("Got " + result.getPoint() + " and expect one of these: " + expected);
		}

		return (!result.isEmpty() && expected.contains(result.getPoint()))
				|| (result.isEmpty() && expected.isEmpty());
	}

	@FunctionalInterface
	public interface AiTest {
		void function(PlayingField field, Decisioner decicioner);
	}

}
