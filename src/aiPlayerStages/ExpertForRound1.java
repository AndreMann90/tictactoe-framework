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
		final int percent = r.nextInt(100);
		if(percent < 35) {
			//35 percent chance for center
			decisioner.putPossibility(FieldPosition.Center, weight);
		} else if(percent < 75) {
			//40 percent chance for a corner
			decisioner.putPossibility(Positions.randomCorner(r), weight);			
		} else {
			//25 percent chance for a corner
			decisioner.putPossibility(Positions.randomPosBetweenCorners(r), weight);
		}
	}

}
