package de.mann.tictactoeframework.aiPlayerStages;

import de.mann.tictactoeframework.aiPlayerStages.ExpertPlayer.ExpertForRound;
import de.mann.tictactoeframework.playingField.FieldPosition;
import de.mann.tictactoeframework.playingField.PlayingField;

class ExpertForRound2 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner) {
		final FieldPosition firstMove = field.getPosFromHistory(1);
		
		if(firstMove.isCenter()) {
			decisioner.putPossibilityForEachCorner(1);
		} else if (firstMove.isCorner())  {
			/*
			 * x--
			 * -e-
			 * ---
			 */
			decisioner.putPossibility(FieldPosition.Center, 1);
		} else {
			/*
			 * exe
			 * -e-
			 * ---
			 */
			decisioner.putPossibility(FieldPosition.Center, 2);
			decisioner.putPossibility(firstMove.rotateLeft(1), 1);
			decisioner.putPossibility(firstMove.rotateRight(1), 1);
		}
	}

}
