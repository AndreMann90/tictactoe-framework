package aiPlayerStages;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound2 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner, int weight) {
		final FieldPosition firstMove = field.getPosFromHistory(1);
		
		if(Positions.isCenter(firstMove)) {
			decisioner.putPossibility(Positions.randomCorner(), weight);
		} else {
			decisioner.putPossibility(FieldPosition.Center, weight);
		}
	}

}
