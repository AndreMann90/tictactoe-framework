package aiPlayerStages;

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
			myfirstMoveWasCorner(field, decisioner, weight);
		} else {
			myfirstMoveWasBetweenCorner(field, decisioner, weight);
		}
	}

	private void myfirstMoveWasCenter(PlayingField field,
			Decisioner decisioner, int weight) {
		
		final FieldPosition oponnent = field.getPosFromHistory(2);
		
		if(Positions.isCorner(oponnent)) {
			/*
			 * o--
			 * -x-
			 * ---
			 */
			decisioner.putPossibility(Positions.opposite(oponnent), weight);
		}
	}

	private void myfirstMoveWasCorner(PlayingField field,
			Decisioner decisioner, int weight) {

		final FieldPosition oponnent = field.getPosFromHistory(2);
	}

	private void myfirstMoveWasBetweenCorner(PlayingField field,
			Decisioner decisioner, int weight) {

		final FieldPosition oponnent = field.getPosFromHistory(2);
	}

}
