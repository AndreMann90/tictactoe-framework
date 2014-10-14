package aiPlayerStages;

import java.util.Random;

import playingField.FieldPosition;
import playingField.PlayingField;
import playingField.Positions;
import aiPlayerStages.ExpertPlayer.ExpertForRound;

public class ExpertForRound1 implements ExpertForRound {

	@Override
	public void useKnowledge(PlayingField field, Decisioner decisioner, int weight) {
		Random r = new Random();
		if(r.nextBoolean()) {
			//50 percent chance for center
			decisioner.putPossibility(FieldPosition.Center, weight);
		} else {
			//50 percent chance for a corner
			decisioner.putPossibility(Positions.randomCorner(r), weight);			
		}
	}

}
