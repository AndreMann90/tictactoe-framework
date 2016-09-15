package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.aiPlayerStages.ExpertPlayer.ExpertForRound;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

class ExpertForRound3 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner) {
		
		final FieldPosition myMove = field.getPosFromHistory(1);

		if (myMove.isCenter()) {
			myfirstMoveWasCenter(field, decisioner, 1);
		} else if (myMove.isCorner()) {
			myfirstMoveWasCorner(field, decisioner, 1, myMove);
		} else {
			myFirstMoveWasBetweenCorner(field, decisioner, 1, myMove);
		}
	}

	private void myfirstMoveWasCenter(PlayingField field,
			Decisioner decisioner, int weight) {
		
		final FieldPosition opponent = field.getPosFromHistory(2);
		
		if(opponent.isCorner()) {
			/*
			 * o--
			 * -x-
			 * --e
			 */
			decisioner.putPossibility(opponent.opposite(), weight);
		} else { // between corners
			/*
			 * eoe
			 * -x-
			 * e-e
			 */
			decisioner.putPossibilityForEachCorner(weight);
		}
	}

	private void myfirstMoveWasCorner(PlayingField field,
			Decisioner decisioner, int weight, FieldPosition myCorner) {

		final FieldPosition oponnent = field.getPosFromHistory(2);
		
		if(oponnent.isCenter()) {
			/*
			 * x--
			 * -oe
			 * -ee
			 */
			decisioner.putPossibility(myCorner.rotateRight(3), weight);
			decisioner.putPossibility(myCorner.rotateRight(4), weight);
			decisioner.putPossibility(myCorner.rotateRight(5), weight);
			
		} else if(myCorner.opposite().equals(oponnent)) {
			/*
			 * x-e
			 * ---
			 * e-o
			 */
			decisioner.putPossibility(myCorner.rotateRight(2), weight);
			decisioner.putPossibility(myCorner.rotateRight(6), weight);
			
		} else if(oponnent.isCorner()) { //not opposite (case before)
			/*
			 * x-o
			 * ---
			 * e-e 
			 */
			decisioner.putPossibility(myCorner.opposite(), weight);
			decisioner.putPossibility(oponnent.opposite(), weight);
			
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
			final int rotateBy = FieldPosition.areNeighbors(myCorner, oponnent) ? 1 : 3;
			final boolean clockwise = !myCorner.rotateRight(rotateBy).equals(oponnent);
			decisioner.putPossibility(myCorner.rotate(clockwise, 2), weight);

			decisioner.putPossibility(FieldPosition.Center, weight);
		}
	}

	private void myFirstMoveWasBetweenCorner(PlayingField field,
                                             Decisioner decisioner, int weight, FieldPosition myMove) {

		final FieldPosition oponnent = field.getPosFromHistory(2);
		
		if(oponnent.isCenter() || myMove.opposite().equals(oponnent)) {
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
			
			decisioner.putPossibility(myMove.rotateRight(2), weight);
            decisioner.putPossibility(myMove.rotateRight(3), weight);
            decisioner.putPossibility(myMove.rotateRight(5), weight);
            decisioner.putPossibility(myMove.rotateRight(6), weight);
			
		} else if(!FieldPosition.areNeighbors(myMove, oponnent)) {
			/*
			 * case 1:
			 * -xe
			 * --o
			 * ---
			 */
			/*
			 * case 2:
			 * -xe
			 * ---
			 * --o
			 */
			
			//Next line, case 1 and case 2 differ:
			final int rotateBy = oponnent.isBetweenCorners() ? 2 : 3;
			final boolean clockwise = myMove.rotateRight(rotateBy)
					.equals(oponnent);
			decisioner.putPossibility(myMove.rotate(clockwise, 1), weight);
		} else { //else there are neighbors but center is free
			/*
			 * -xo
			 * --e
			 * e-e 
			 */
			final boolean clockwise = myMove.rotateRight(1)
					.equals(oponnent);
			decisioner.putPossibility(myMove.rotate(clockwise, 2), weight);
            decisioner.putPossibility(myMove.rotate(clockwise, 3), weight);
            decisioner.putPossibility(myMove.rotate(clockwise, 5), weight);
		}
	}

}
