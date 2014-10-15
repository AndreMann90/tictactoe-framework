package aiPlayerStages;

import java.util.Random;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound3 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner,
			int weight) {
		
		final FieldPosition myMove = field.getPosFromHistory(1);

		if (Positions.isCenter(myMove)) {
			myfirstMoveWasCenter(field, decisioner, weight);
		} else if (Positions.isCorner(myMove)) {
			myfirstMoveWasCorner(field, decisioner, weight, myMove);
		} else {
			myfirstMoveWasBetweenCorner(field, decisioner, weight, myMove);
		}
	}

	private void myfirstMoveWasCenter(PlayingField field,
			Decisioner decisioner, int weight) {
		
		final FieldPosition oponnent = field.getPosFromHistory(2);
		
		if(Positions.isCorner(oponnent)) {
			/*
			 * o--
			 * -x-
			 * --e
			 */
			decisioner.putPossibility(Positions.opposite(oponnent), weight);
		} else { // between corners
			/*
			 * eoe
			 * -x-
			 * e-e
			 */
			decisioner.putPossibility(Positions.randomCorner(), weight);
		}
	}

	private void myfirstMoveWasCorner(PlayingField field,
			Decisioner decisioner, int weight, FieldPosition myCorner) {

		Random r = new Random();
		final FieldPosition oponnent = field.getPosFromHistory(2);
		
		if(Positions.isCenter(oponnent)) {
			/*
			 * x--
			 * -oe
			 * -ee
			 */
			final int rotateBy = 3 + r.nextInt(3);
			decisioner.putPossibility(Positions.rotateRight(myCorner, rotateBy), weight);
			
		} else if(Positions.opposite(myCorner).equals(oponnent)) {
			/*
			 * x-e
			 * ---
			 * e-o
			 */
			final int rotateBy = r.nextBoolean() ? 2 : 6;
			decisioner.putPossibility(Positions.rotateRight(myCorner, rotateBy), weight);
			
		} else if(Positions.isCorner(oponnent)) { //not opposite (case before)
			/*
			 * x-o
			 * ---
			 * e-e TODO opposite from opponent does it as well (but more possibilities)
			 */
			decisioner.putPossibility(Positions.opposite(myCorner), weight);
			
		} else { // opponent made move between corners
			
			/*
			 * case 1:
			 * xo-
			 * -e-
			 * e--
			 */
			
			/*
			 * case 2:
			 * x--
			 * -eo
			 * e-- 
			 */
			final FieldPosition position;
			
			if(r.nextBoolean()) {
				position = FieldPosition.Center;
			} else {
				//Next line, case 1 and case 2 differ:
				final int rotateBy = Positions.areNeighbours(myCorner, oponnent) ? 1 : 3;
				final boolean clockwise = Positions.rotateRight(myCorner, rotateBy).equals(oponnent) == false;
				
				position = Positions.rotate(clockwise, myCorner, 2);
			}
			decisioner.putPossibility(position, weight);
			
		}
	}

	private void myfirstMoveWasBetweenCorner(PlayingField field,
			Decisioner decisioner, int weight, FieldPosition myMove) {

		Random r = new Random();
		final FieldPosition oponnent = field.getPosFromHistory(2);
		
		if(Positions.isCenter(oponnent) || Positions.opposite(myMove).equals(oponnent)) {
			/*
			 * case 1:
			 * -x-
			 * eoe
			 * e-e
			 */
			
			/*
			 * case 2:
			 * -x-
			 * e-e
			 * eoe
			 */
			int rotateBy = r.nextBoolean() ? 2 : 5;
			rotateBy = rotateBy + r.nextInt(2);
			
			decisioner.putPossibility(Positions.rotateRight(myMove, rotateBy), weight);
			
		} else if(Positions.isBetweenCorners(oponnent)) {
			/*
			 * -xe
			 * --o
			 * ---
			 */
			final boolean clockwise = Positions.rotateRight(myMove, 2).equals(oponnent);
			decisioner.putPossibility(Positions.rotate(clockwise, myMove, 1), weight);
		}
		//TODO two possibilties left
	}

}
