package testUtil;


import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import aiPlayerStages.Decisioner;
import exceptions.InvalidMoveException;
import exceptions.InvalidSyntaxException;
import players.PlayerID;
import playingField.PlayingField;
import playingField.Positions;

public class TestDriver {

	/**
	 * TODO
	 * @param testSetUp a sequence to represent the current Field and expected move: x for Player1, o for Player2 and e for expected
	 * @param testFunction the function that determines the expected move
	 * @return true if move like expected
	 * @throws InvalidSyntaxException invalid String testSetUp
	 */
	public static boolean likeExpected(String testSetUp, AiTest testFunction) throws InvalidSyntaxException {
		
		if(testSetUp.length() != 9 || testSetUp.matches("[xXoOeE_-]*") == false) {
			throw new InvalidSyntaxException();
		}
		
		
		List<Point> expected = new LinkedList<Point>();
		PlayingField field = new PlayingField();
		Decisioner decisioner = new Decisioner();
		
		testSetUp = testSetUp.toLowerCase();
		for(int i = 0; i < testSetUp.length(); i++) {
			char current = testSetUp.charAt(i);
			Point curPos = new Point(i/3, i%3);
			if(current == 'x') {
				try {
					field.makeMove(curPos, Positions.fromPointToType(curPos), PlayerID.Player1);
				} catch (InvalidMoveException e) {
					e.printStackTrace();
				}
			} else if(current == 'o') {
				try {
					field.makeMove(curPos, Positions.fromPointToType(curPos), PlayerID.Player2);
				} catch (InvalidMoveException e) {
					e.printStackTrace();
				}

			} else if(current == 'e') {
				expected.add(curPos);
			}
		}
		
		
		testFunction.function(field, decisioner);
		
		Optional<Point> result = decisioner.decide();
		
		if(result.isPresent()) {
			System.out.println(result.get().x + "," + result.get().y);
		} else { 
			System.out.println("No Point");
		}
		
		return (result.isPresent() == true && expected.contains(result.get()))
				|| (result.isPresent() == false && expected.isEmpty() == true);
	}
	
	@FunctionalInterface
	public interface AiTest {
		void function(PlayingField field, Decisioner decicioner);
	}
	
}
